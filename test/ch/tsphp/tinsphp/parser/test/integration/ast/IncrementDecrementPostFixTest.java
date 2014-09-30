/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This file is part of the TSPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.ast;

import ch.tsphp.tinsphp.parser.test.integration.testutils.AAstTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class IncrementDecrementPostFixTest extends AAstTest
{

    public IncrementDecrementPostFixTest(String testString, String expectedResult) {
        super(testString, expectedResult);
    }

    @Test
    public void test() throws Exception {
        compareAst();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testStrings() {
        List<Object[]> collection = new ArrayList<>();

        String[][] tmp1 = new String[][]{
                {"$a", "$a"},
        };
        List<Object[]> tmp4 = PostFixWithCallAtTheEndTest.getVariations(tmp1);

        for (Object[] expression : tmp4) {
            //see TSPHP-850 postfix in conjunction with pre/post-increment/decrement
            collection.addAll(Arrays.asList(new String[][]{
                    {"++" + expression[0] + "[0];", "(expr (preIncr (arrAccess " + expression[1] + " 0)))"},
                    {"--" + expression[0] + "[0];", "(expr (preDecr (arrAccess " + expression[1] + " 0)))"},
                    {expression[0] + "[0]++;", "(expr (postIncr (arrAccess " + expression[1] + " 0)))"},
                    {expression[0] + "[0]--;", "(expr (postDecr (arrAccess " + expression[1] + " 0)))"},
            }));
        }

        return collection;
    }
}
