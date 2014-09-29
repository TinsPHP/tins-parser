/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class AAstErrorTest from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.testutils;

import org.junit.Ignore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Ignore
public abstract class AAstErrorTest extends AAstTest
{

    public AAstErrorTest(String testString, String theExpectedResult) {
        super(testString, theExpectedResult);
        noErrorsOnOutput();
    }

    @Override
    protected void checkForExceptions() {
        assertFalse(testString + " failed, lexer threw exception(s) - see output", lexer.hasFoundError());
        assertTrue(testString + " failed, parser exception expected but none occurred", parser.hasFoundError());
        assertEquals(testString + " failed - more than one parser exception occurred", 1,
                parser.getExceptions().size());
    }

    protected static String getErrorNodeText(int index, int start, int stop, String wrongToken, int expectedToken,
            String resyncTokens) {
        return "<unexpected: [@" + index + "," + start + ":" + stop + "='" + wrongToken
                + "',<" + expectedToken + ">,1:" + start + "], resync=" + resyncTokens + ">";
    }
}
