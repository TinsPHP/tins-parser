/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This file is part of the TSPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TSPHP/License
 */

import ch.tsphp.common.AstHelper;
import ch.tsphp.common.AstHelperRegistry;
import ch.tsphp.common.IErrorLogger;
import ch.tsphp.common.IParser;
import ch.tsphp.common.ITSPHPAst;
import ch.tsphp.common.ITSPHPAstAdaptor;
import ch.tsphp.common.ParserUnitDto;
import ch.tsphp.common.TSPHPAstAdaptor;
import ch.tsphp.common.TSPHPErrorAst;
import ch.tsphp.common.exceptions.TSPHPException;
import ch.tsphp.parser.common.ANTLRNoCaseFileStream;
import ch.tsphp.parser.common.ANTLRNoCaseInputStream;
import ch.tsphp.parser.common.ANTLRNoCaseStringStream;
import ch.tsphp.tinsphp.parser.antlrmod.ErrorReportingTinsPHPLexer;
import ch.tsphp.tinsphp.parser.antlrmod.ErrorReportingTinsPHPParser;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Collection;

//TODO rstoll TINS-176 Use ParserFacade from tsphp-parser-common

/**
 * Represents the TSPHP Parser providing different methods to parse some input and generating a ParserUnitDto.
 */
public class ParserFacade implements IParser, IErrorLogger
{

    private final ITSPHPAstAdaptor astAdaptor;
    private final Collection<IErrorLogger> errorLoggers = new ArrayDeque<>();
    private boolean hasFoundError;

    public ParserFacade() {
        this(new TSPHPAstAdaptor());
    }

    public ParserFacade(ITSPHPAstAdaptor anAstAdaptor) {
        astAdaptor = anAstAdaptor;
        AstHelperRegistry.set(new AstHelper(astAdaptor));
    }

    @Override
    public ParserUnitDto parse(String inputStream) {
        return getAstOrErrorAst(new ANTLRNoCaseStringStream(inputStream));
    }

    @Override
    public ParserUnitDto parse(char[] data, int numberOfActualCharsInArray) {
        return getAstOrErrorAst(new ANTLRNoCaseStringStream(data, numberOfActualCharsInArray));
    }

    @Override
    public ParserUnitDto parseInputStream(InputStream inputStream) throws IOException {
        return getAstOrErrorAst(new ANTLRNoCaseInputStream(inputStream));
    }

    @Override
    public ParserUnitDto parseInputStream(InputStream inputStream, int initialBufferSize) throws IOException {
        return getAstOrErrorAst(new ANTLRNoCaseInputStream(inputStream, initialBufferSize));
    }

    @Override
    public ParserUnitDto parseInputStream(InputStream inputStream, String encoding) throws IOException {
        return getAstOrErrorAst(new ANTLRNoCaseInputStream(inputStream, encoding));
    }

    @Override
    public ParserUnitDto parseInputStream(InputStream inputStream, int initialBufferSize, String encoding)
            throws IOException {
        return getAstOrErrorAst(new ANTLRNoCaseInputStream(inputStream, initialBufferSize, encoding));
    }

    @Override
    public ParserUnitDto parseInputStream(
            InputStream inputStream, int initialBufferSize, int readBufferSize, String encoding) throws IOException {

        return getAstOrErrorAst(new ANTLRNoCaseInputStream(inputStream, initialBufferSize, readBufferSize, encoding));
    }

    @Override
    public ParserUnitDto parseFile(String fileName) throws IOException {
        return getAstOrErrorAst(new ANTLRNoCaseFileStream(fileName));
    }

    @Override
    public ParserUnitDto parseFile(String fileName, String encoding) throws IOException {
        return getAstOrErrorAst(new ANTLRNoCaseFileStream(fileName, encoding));
    }

    @Override
    public boolean hasFoundError() {
        return hasFoundError;
    }

    private ParserUnitDto getAstOrErrorAst(CharStream input) {
        try {
            return getAst(input);
        } catch (RecognitionException ex) {
            // should never happen, ErrorReportingTSPHPLexer and ErrorReportingTSPHPParser should catch it already.
            // but just in case and to be complete
            informErrorLogger(ex);
            TokenStream tokenStream = new CommonTokenStream();
            return new ParserUnitDto("", new TSPHPErrorAst(tokenStream, ex.token, ex.token, ex), tokenStream);
        }
    }

    private ParserUnitDto getAst(CharStream input) throws RecognitionException {
        ErrorReportingTinsPHPLexer lexer = new ErrorReportingTinsPHPLexer(input);
        for (IErrorLogger logger : errorLoggers) {
            lexer.registerErrorLogger(logger);
        }
        lexer.registerErrorLogger(this);
        TokenStream tokenStream = new CommonTokenStream(lexer);

        ErrorReportingTinsPHPParser parser = new ErrorReportingTinsPHPParser(tokenStream);
        for (IErrorLogger logger : errorLoggers) {
            parser.registerErrorLogger(logger);
        }
        parser.registerErrorLogger(this);

        parser.setTreeAdaptor(astAdaptor);
        ITSPHPAst ast = parser.compilationUnit().getTree();

        return new ParserUnitDto("", ast, tokenStream);
    }

    @Override
    public void registerErrorLogger(IErrorLogger errorLogger) {
        errorLoggers.add(errorLogger);
    }

    @Override
    public void reset() {
        hasFoundError = false;
    }

    @Override
    public void log(TSPHPException exception) {
        hasFoundError = true;
    }

    private void informErrorLogger(Exception exception) {
        hasFoundError = true;
        for (IErrorLogger logger : errorLoggers) {
            logger.log(new TSPHPException(exception));
        }
    }
}
