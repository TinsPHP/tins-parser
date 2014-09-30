/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class VariableDeclarationTest from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.ast;

import ch.tsphp.tinsphp.parser.test.integration.testutils.AAstTest;
import ch.tsphp.tinsphp.parser.test.integration.testutils.ExpressionHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class VariableDeclarationTest extends AAstTest
{

    public VariableDeclarationTest(String testString, String expectedResult) {
        super(testString, expectedResult);
    }

    @Test
    public void test() throws Exception {
        compareAst();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testStrings() {
        //Variable declaration as they are known in TSPHP do not exist in PHP.
        //Therefore the following code creates an expr AST and not a vars AST as one might expect.

        Collection<Object[]> collection = new ArrayList<>();
        List<String[]> expressions = ExpressionHelper.getAstExpressionWithoutWeakOperators();
        for (Object[] expression : expressions) {
            collection.add(new Object[]{
                    "$a = " + expression[0] + ";",
                    "(expr (= $a " + expression[1] + "))"
            });
        }
        return collection;
    }
}
