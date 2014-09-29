/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class AAstTokenTest from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.testutils;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.junit.Assert;
import org.junit.Ignore;

import java.util.List;

@Ignore
public abstract class AAstTokenTest extends AParserTest
{

    protected final List<Integer> expectedTokens;

    public AAstTokenTest(String testString, List<Integer> theExpectedTokens) {
        super(testString);
        expectedTokens = theExpectedTokens;
    }

    public void compareAst() throws Exception {
        parseAndCheckForExceptions();
        Assert.assertEquals(testString + " failed.", expectedTokens,
                AstHelper.getTokenTypes((CommonTree) result.getTree()));
    }

    @Override
    protected void run() throws RecognitionException {
        result = parser.statement();
    }
}
