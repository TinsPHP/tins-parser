/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class ForTest from the TSPHP project.
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
public class ForTest extends AAstTest
{

    public ForTest(String testString, String expectedResult) {
        super(testString, expectedResult);
    }

    @Test
    public void test() throws Exception {
        compareAst();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testStrings() {
        List<Object[]> collection = new ArrayList<>();
        collection.addAll(Arrays.asList(new Object[][]{
                {
                        "for($a=1     ; true ; ++$i  ) $a=1;",
                        "(for (exprs (= $a 1)) (exprs true) (exprs (preIncr $i)) (cBlock (expr (= $a 1))))"
                },
                {
                        "for(         ; true ; ++$i  ) $a=1;",
                        "(for exprs (exprs true) (exprs (preIncr $i)) (cBlock (expr (= $a 1))))"
                },
                {
                        "for(         ;      ; $i+=1 ) $a=1;",
                        "(for exprs exprs (exprs (+= $i 1)) (cBlock (expr (= $a 1))))"
                },
                {
                        "for(         ; true ;       ) $a=1;",
                        "(for exprs (exprs true) exprs (cBlock (expr (= $a 1))))"
                },
                {
                        "for(         ;      ;       ) $a=1;",
                        "(for exprs exprs exprs (cBlock (expr (= $a 1))))"
                }
        }));

        List<String[]> expressions = ExpressionHelper.getAstExpressions();
        for (Object[] expression : expressions) {
            collection.add(new Object[]{
                    "for(" + expression[0] + ";" + expression[0] + ";" + expression[0] + ") $a=1;",
                    "(for "
                            + "(exprs " + expression[1] + ") "
                            + "(exprs " + expression[1] + ") "
                            + "(exprs " + expression[1] + ") "
                            + "(cBlock (expr (= $a 1)))"
                            + ")"
            });
            collection.add(new Object[]{
                    "for("
                            + expression[0] + "," + expression[0] + ";"
                            + expression[0] + "," + expression[0] + ";"
                            + expression[0] + "," + expression[0] + " "
                            + ") $a^=1;",
                    "(for "
                            + "(exprs " + expression[1] + " " + expression[1] + ") "
                            + "(exprs " + expression[1] + " " + expression[1] + ") "
                            + "(exprs " + expression[1] + " " + expression[1] + ") "
                            + "(cBlock (expr (^= $a 1)))"
                            + ")"
            });
        }
        return collection;
    }
}
