/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class AParserTest from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.testutils;

import ch.tsphp.common.AstHelperRegistry;
import ch.tsphp.common.ITSPHPAstAdaptor;
import ch.tsphp.common.TSPHPAstAdaptor;
import ch.tsphp.tinsphp.parser.antlrmod.ANTLRNoCaseStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.ParserRuleReturnScope;
import org.junit.Assert;
import org.junit.Ignore;


@Ignore
public abstract class AParserTest extends ATest
{

    protected TestTinsPHPParser parser;
    protected TestTinsPHPLexer lexer;
    protected ParserRuleReturnScope result;
    private final ITSPHPAstAdaptor adaptor;

    public AParserTest(String testString) {
        super(testString);
        adaptor = new TSPHPAstAdaptor();
        AstHelperRegistry.set(new ch.tsphp.common.AstHelper(adaptor));
    }

    public void parseAndCheckForExceptions() throws Exception {
        parse();
        checkForExceptions();
    }
    protected void checkForExceptions(){
        Assert.assertFalse(testString + " failed, lexer threw exception(s) - see output", lexer.hasFoundError());
        Assert.assertFalse(testString + " failed, parser threw exception(s) - see output", parser.hasFoundError());
    }

    protected void modifyParser(){}

    protected void parse() throws Exception {
        CharStream stream = new ANTLRNoCaseStringStream(testString);
        lexer = new TestTinsPHPLexer(stream);
        lexer.setErrorReporting(isErrorReportingOn);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        parser = createTestParser(tokens);
        parser.setTreeAdaptor(adaptor);
        parser.setErrorReporting(isErrorReportingOn);

        modifyParser();
        run();
    }

    protected TestTinsPHPParser createTestParser(CommonTokenStream tokens) {
        return new TestTinsPHPParser(tokens);
    }

    protected void run() throws Exception {
        result = parser.compilationUnit();
    }
}
