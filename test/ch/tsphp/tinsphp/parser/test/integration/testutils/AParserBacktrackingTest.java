/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class AParserBacktrackingTest from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.testutils;

import org.junit.Ignore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@Ignore
public abstract class AParserBacktrackingTest extends AParserTest
{

    protected int startTokenType;
    protected int stopTokenType;

    public AParserBacktrackingTest(String testString, int theStartTokenType, int theStopTokenType) {
        super(testString);
        startTokenType = theStartTokenType;
        stopTokenType = theStopTokenType;
    }

    @Override
    protected void modifyParser() {
        parser.setBacktracking(1);
    }

    public void parseAndCheckResultIsOnlyBacktracking() throws Exception {
        parseAndCheckForExceptions();
        assertNull(testString + " - tree was not null", result.getTree());
        assertNotNull(testString + "- start token was null", result.start);
        assertEquals(testString + " - start token type was different ", startTokenType, result.start.getType());
        assertNotNull(testString + "- stop token was null", result.stop);
        assertEquals(testString + " - stop token type was different ", stopTokenType, result.stop.getType());
        assertTrue(testString + " - memoize is empty ", parser.getState().ruleMemo.length > 0);
    }
}
