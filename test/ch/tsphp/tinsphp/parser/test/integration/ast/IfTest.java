/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class IfTest from the TSPHP project.
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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class IfTest extends AAstTest
{

    public IfTest(String testString, String expectedResult) {
        super(testString, expectedResult);
    }

    @Test
    public void test() throws Exception {
        compareAst();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testStrings() {
        List<Object[]> collection = new ArrayList<>();

        List<String[]> expressions = ExpressionHelper.getAstExpressions();
        for (Object[] expression : expressions) {
            collection.add(new Object[]{
                    "if(" + expression[0] + ") $a=1; else $a+=1;",
                    "(if " + expression[1] + " (cBlock (expr (= $a 1))) (cBlock (expr (+= $a 1))))"
            });
        }
        collection.addAll(Arrays.asList(new Object[][]{
                {
                        "if(true) $a=1; else if(false) $b=1; else $c=2;",
                        "(if true (cBlock (expr (= $a 1))) "
                                + "(cBlock (if false (cBlock (expr (= $b 1))) (cBlock (expr (= $c 2))))))"
                },
                {
                        "if(true) $a=1; else if(false) $b=1; else if($a<1) $c=2; else $d*=1;",
                        "(if true (cBlock (expr (= $a 1))) (cBlock "
                                + "(if false (cBlock (expr (= $b 1))) (cBlock "
                                + "(if (< $a 1) (cBlock (expr (= $c 2))) (cBlock (expr (*= $d 1))))"
                                + "))"
                                + "))"
                },}));

        return collection;
    }
}
