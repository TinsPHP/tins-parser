/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class WhileTest from the TSPHP project.
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
public class WhileTest extends AAstTest
{

    public WhileTest(String testString, String expectedResult) {
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
                {"while( true  ) $a=1;", "(while true (cBlock (expr (= $a 1))))"},
                {"while( true  ){$a=1;}", "(while true (cBlock (expr (= $a 1))))"},
                {
                        "while( true  ){$a=1; $b=2;}",
                        "(while true (cBlock (expr (= $a 1)) (expr (= $b 2))))"
                },
                {"do $a=1; while( true  );", "(do (block (expr (= $a 1))) true)"},
                {"do {$a=1;} while( true  );", "(do (block (expr (= $a 1))) true)"},
                {"do {$a=1;$b=2;}while( true  );", "(do (block (expr (= $a 1)) (expr (= $b 2))) true)"}
        }));
        List<String[]> expressions = ExpressionHelper.getAstExpressions();
        for (Object[] expression : expressions) {
            collection.add(new Object[]{
                    "while(" + expression[0] + ") $a=1;",
                    "(while " + expression[1] + " (cBlock (expr (= $a 1))))"
            });
            collection.add(new Object[]{
                    "do $a=1; while(" + expression[0] + ");",
                    "(do (block (expr (= $a 1))) " + expression[1] + ")"
            });
        }
        return collection;
    }
}
