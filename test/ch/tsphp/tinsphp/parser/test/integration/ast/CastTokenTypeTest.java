/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class CastTest from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.ast;

import ch.tsphp.tinsphp.common.gen.TokenTypes;
import ch.tsphp.tinsphp.parser.test.integration.testutils.AParserTest;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CastTokenTypeTest extends AParserTest
{
    private int tokenType;

    public CastTokenTypeTest(String testString, int theTokenType) {
        super(testString);
        tokenType = theTokenType;
    }

    @Test
    public void test() throws Exception {
        parseAndCheckForExceptions();
        CommonTree tree = (CommonTree) result.getTree();
        if (tree != null) {
            Assert.assertEquals(testString + " failed.", tokenType, tree.getChild(1).getType());
        } else {
            Assert.fail(testString + " failed. Tree was empty.");
        }
    }

    @Override
    protected void run() throws RecognitionException {
        result = parser.scalarTypesInclArrayWithModifier();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testStrings() {
        return Arrays.asList(new Object[][]{
                {"bool", TokenTypes.TypeBool},
                {"boolean", TokenTypes.TypeBool},
                {"int", TokenTypes.TypeInt},
                {"integer", TokenTypes.TypeInt},
                {"float", TokenTypes.TypeFloat},
                {"double", TokenTypes.TypeFloat},
                {"real", TokenTypes.TypeFloat},
                {"string", TokenTypes.TypeString},
                {"array", TokenTypes.TypeArray},
        });
    }
}
