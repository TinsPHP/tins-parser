/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class ArrayDeclarationTest from the TSPHP project.
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
public class ArrayDeclarationTest extends AAstTest
{

    public ArrayDeclarationTest(String testString, String expectedResult) {
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

        List<Object[]> collection = new ArrayList<>();
        collection.add(new Object[]{"$a = [];", "(expr (= $a array))"});
        collection.add(new Object[]{"$a = array();", "(expr (= $a array))"});
        List<String[]> expressions = ExpressionHelper.getAstExpressions();
        for (Object[] expression : expressions) {
            collection.add(new Object[]{
                    "$a = [" + expression[0] + "];",
                    "(expr (= $a (array " + expression[1] + ")))"
            });
            collection.add(new Object[]{
                    "$a = [" + expression[0] + "," + expression[0] + "];",
                    "(expr (= $a (array " + expression[1] + " " + expression[1] + ")))"
            });
            collection.add(new Object[]{
                    "$a = [1 => " + expression[0] + ", $a=>" + expression[0] + "];",
                    "(expr (= $a (array (=> 1 " + expression[1] + ") (=> $a " + expression[1] + "))))"
            });
            collection.add(new Object[]{
                    "$a = array( 'a' => array(" + expression[0] + "), " + expression[0] + "=> 1, 2,3 );",
                    "(expr (= $a (array (=> 'a' (array " + expression[1] + ")) (=> " + expression[1] + " 1) 2 3)))"
            });
        }

        return collection;
    }
}
