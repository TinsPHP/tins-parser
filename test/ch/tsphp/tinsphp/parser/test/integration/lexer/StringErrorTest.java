/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class StringErrorTest from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.lexer;

import ch.tsphp.parser.common.ANTLRNoCaseStringStream;
import ch.tsphp.tinsphp.parser.test.integration.testutils.ALexerTest;
import ch.tsphp.tinsphp.parser.test.integration.testutils.TestTinsPHPLexer;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.RecognitionException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class StringErrorTest extends ALexerTest
{

    public StringErrorTest(String methodName, String testString) {
        super(methodName, testString, 0);
    }

    @Test
    public void testTokens() throws Exception {
        CharStream stream = new ANTLRNoCaseStringStream(testString);
        lexer = new TestTinsPHPLexer(stream);
        lexer.setErrorReporting(isErrorReportingOn);
        Method method = lexer.getClass().getMethod(methodName);
        method.invoke(lexer);
        try {
            isErrorReportingOn = false;
            method.invoke(lexer);
            analyseToken();
            Assert.fail(methodName + " - " + testString + " failed, no exception occurred");
        } catch (RecognitionException ex) {
            //that's fine, we expect a RecognitionException
        } catch (InvocationTargetException ex) {
            //should contain a RecognitionException - the InvocationTargetException occurs due to the method call using
            //reflection
            if (!(ex.getTargetException() instanceof RecognitionException)) {
                System.err.printf(methodName + " - " + testString + " failed");
                ex.printStackTrace();
                Assert.fail(methodName + " - " + testString + " failed, an unexpected exception occurred - see output");
            }
        }
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testStrings() {
        return Arrays.asList(new Object[][]{
                {"mSTRING_DOUBLE_QUOTED", "\"foo\"\""},
                {"mSTRING_DOUBLE_QUOTED", "\"\\\"\""},
                {"mSTRING_SINGLE_QUOTED", "'foo''"},
                {"mSTRING_SINGLE_QUOTED", "'\\''"},
        });
    }
}
