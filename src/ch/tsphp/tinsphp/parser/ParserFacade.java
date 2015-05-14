/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class ParserFacade from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser;

import ch.tsphp.common.AstHelper;
import ch.tsphp.common.AstHelperRegistry;
import ch.tsphp.common.ITSPHPAst;
import ch.tsphp.common.ITSPHPAstAdaptor;
import ch.tsphp.common.ParserUnitDto;
import ch.tsphp.common.TSPHPErrorAst;
import ch.tsphp.common.exceptions.TSPHPException;
import ch.tsphp.parser.common.ANTLRNoCaseFileStream;
import ch.tsphp.parser.common.ANTLRNoCaseInputStream;
import ch.tsphp.parser.common.ANTLRNoCaseStringStream;
import ch.tsphp.tinsphp.common.IParser;
import ch.tsphp.tinsphp.common.issues.EIssueSeverity;
import ch.tsphp.tinsphp.common.issues.IIssueLogger;
import ch.tsphp.tinsphp.common.issues.IssueReporterHelper;
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
import java.util.EnumSet;

//TODO rstoll TINS-176 Use ParserFacade from tsphp-parser-common

/**
 * Represents the TSPHP Parser providing different methods to parse some input and generating a ParserUnitDto.
 */
public class ParserFacade implements IParser, IIssueLogger
{
    private final ITSPHPAstAdaptor astAdaptor;
    private final Collection<IIssueLogger> issueLoggers = new ArrayDeque<>();
    private EnumSet<EIssueSeverity> foundIssues = EnumSet.noneOf(EIssueSeverity.class);

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
    public boolean hasFound(EnumSet<EIssueSeverity> severities) {
        return IssueReporterHelper.hasFound(foundIssues, severities);
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
        for (IIssueLogger logger : issueLoggers) {
            lexer.registerIssueLogger(logger);
        }
        lexer.registerIssueLogger(this);
        TokenStream tokenStream = new CommonTokenStream(lexer);

        ErrorReportingTinsPHPParser parser = new ErrorReportingTinsPHPParser(tokenStream);
        for (IIssueLogger logger : issueLoggers) {
            parser.registerIssueLogger(logger);
        }
        parser.registerIssueLogger(this);

        parser.setTreeAdaptor(astAdaptor);
        ITSPHPAst ast = parser.compilationUnit().getTree();

        return new ParserUnitDto("", ast, tokenStream);
    }

    @Override
    public void registerIssueLogger(IIssueLogger iIssueLogger) {
        issueLoggers.add(iIssueLogger);
    }

    @Override
    public void reset() {
        foundIssues = EnumSet.noneOf(EIssueSeverity.class);
    }

    @Override
    public void log(TSPHPException exception, EIssueSeverity severity) {
        foundIssues.add(severity);
    }

    private void informErrorLogger(RecognitionException exception) {
        TSPHPException ex = new TSPHPException(exception);
        log(ex, EIssueSeverity.FatalError);
        for (IIssueLogger logger : issueLoggers) {
            logger.log(ex, EIssueSeverity.FatalError);
        }
    }
}
