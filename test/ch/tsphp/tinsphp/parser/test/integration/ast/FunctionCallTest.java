/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class FunctionCallTest from the TSPHP project.
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
public class FunctionCallTest extends AAstTest
{

    public FunctionCallTest(String testString, String expectedResult) {
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
                {"foo();", "(expr (fCall foo() args))"},
                {"\\foo();", "(expr (fCall \\foo() args))"},
                {"a\\foo();", "(expr (fCall a\\foo() args))"},
                {"a\\a\\foo();", "(expr (fCall a\\a\\foo() args))"},
                {"a\\a\\b\\foo();", "(expr (fCall a\\a\\b\\foo() args))"},
                {"\\a\\foo();", "(expr (fCall \\a\\foo() args))"},
                {"\\a\\b\\foo();", "(expr (fCall \\a\\b\\foo() args))"},
                {"\\a\\b\\c\\foo();", "(expr (fCall \\a\\b\\c\\foo() args))"},
                //TODO rstoll TINS-108 - class, TINS-109 - interface
//                {
//                        "foo()->bar(2,2);",
//                        "(expr (mpCall "
//                                + "(fCall foo() args)"
//                                + " bar() (args 2 2)))"
//                },
//                {
//                        "foo()->bar(2,2)->asdf(1);",
//                        "(expr (mpCall (mpCall "
//                                + "(fCall foo() args)"
//                                + " bar() (args 2 2)) asdf() (args 1)))"
//                },
        }));
        List<String[]> expressions = ExpressionHelper.getAstExpressions();
        for (Object[] expression : expressions) {
            collection.add(new Object[]{
                    "foo(" + expression[0] + ");",
                    "(expr (fCall foo() (args " + expression[1] + ")))"
            });
            collection.add(new Object[]{
                    "foo(" + expression[0] + "," + expression[0] + "," + expression[0] + ");",
                    "(expr (fCall foo() (args "
                            + "" + expression[1] + " " + expression[1] + " " + expression[1] + ""
                            + ")))"
            });
        }
        return collection;
    }
}
