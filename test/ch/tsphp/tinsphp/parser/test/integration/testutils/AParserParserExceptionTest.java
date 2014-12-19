/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class AParserParserExceptionTest from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.testutils;

import ch.tsphp.tinsphp.common.issues.EIssueSeverity;
import org.antlr.runtime.RecognitionException;
import org.junit.Ignore;

import java.util.EnumSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Ignore
public abstract class AParserParserExceptionTest extends AParserExceptionTest
{

    public AParserParserExceptionTest(String testString, int character, int position,
            Class<? extends RecognitionException> type) {
        super(testString, character, position, type);
    }

    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    public void parseExpectingException() throws Exception {
        parse();

        assertFalse(testString + " failed - lexer.exceptions is not empty ",
                lexer.hasFound(EnumSet.allOf(EIssueSeverity.class)));

        assertTrue(testString + " failed - exceptions is empty ",
                parser.hasFound(EnumSet.allOf(EIssueSeverity.class)));
        List<Exception> exceptions = parser.getExceptions();
        RecognitionException ex = (RecognitionException) exceptions.get(0);
        assertTrue(testString + " failed - wrong type, expected " + exceptionType.getName()
                + " actual " + ex.getClass().getName(), exceptionType.isInstance(ex));
        assertEquals(testString + " failed - token wrong", token, ex.c);
        assertEquals(testString + " failed - position wrong", position, ex.charPositionInLine);

    }
}
