/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class SwitchTest from the TSPHP project.
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
public class SwitchTest extends AAstTest
{

    public SwitchTest(String testString, String expectedResult) {
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
                    "switch(" + expression[0] + "){}",
                    "(switch " + expression[1] + ")"
            });
        }

        collection.add(new Object[]{"switch(true){}", "(switch true)"});

        String[][] caseLabels = new String[][]{
                {"case 1", "case 2", "case 3", "case 4"},
                {"default", "case 2", "case 3", "case 4"},
                {"case 1", "default", "case 3", "case 4"},
                {"case 1", "case 2", "default", "case 4"},
                {"case 1", "case 2", "case 3", "default"},
        };
        for (String[] labels : caseLabels) {
            String label1 = labels[0];
            String label2 = labels[1];
            String label3 = labels[2];
            String label4 = labels[3];

            collection.addAll(Arrays.asList(new Object[][]{
                    {
                            "switch($a){ " + label1 + ": $a=1; }",
                            "(switch $a (cases" + getCases(label1) + ") (cBlock (expr (= $a 1))))"
                    },
                    {
                            "switch($a){ " + label1 + ": $a=1; " + label2 + ": $b=1;}",
                            "(switch $a"
                                    + " (cases" + getCases(label1) + ") (cBlock (expr (= $a 1)))"
                                    + " (cases" + getCases(label2) + ") (cBlock (expr (= $b 1)))"
                                    + ")"
                    },
                    {
                            "switch($a){ " + label1 + ": $a=1; " + label2 + ": $b = 1; " + label3 + ": $c=1; }",
                            "(switch $a"
                                    + " (cases" + getCases(label1) + ") (cBlock (expr (= $a 1)))"
                                    + " (cases" + getCases(label2) + ") (cBlock (expr (= $b 1)))"
                                    + " (cases" + getCases(label3) + ") (cBlock (expr (= $c 1)))"
                                    + ")"
                    },
                    {
                            "switch($a){ "
                                    + label1 + ": $a=1; "
                                    + label2 + ": $b=1; "
                                    + label3 + ": $c=1; "
                                    + label4 + ": $d=1; "
                                    + "}",
                            "(switch $a"
                                    + " (cases" + getCases(label1) + ") (cBlock (expr (= $a 1)))"
                                    + " (cases" + getCases(label2) + ") (cBlock (expr (= $b 1)))"
                                    + " (cases" + getCases(label3) + ") (cBlock (expr (= $c 1)))"
                                    + " (cases" + getCases(label4) + ") (cBlock (expr (= $d 1)))"
                                    + ")"
                    },

                    //first falls through
                    {
                            "switch($a){ " + label1 + ":" + label2 + ": $a=1;}",
                            "(switch $a (cases" + getCases(label1, label2) + ") (cBlock (expr (= $a 1))))"
                    },
                    {
                            "switch($a){ " + label1 + ":" + label2 + ": $a=1; " + label3 + ": $b=1; }",
                            "(switch $a"
                                    + " (cases" + getCases(label1, label2) + ") (cBlock (expr (= $a 1)))"
                                    + " (cases" + getCases(label3) + ") (cBlock (expr (= $b 1)))"
                                    + ")",
                    },
                    {
                            "switch($a){"
                                    + label1 + ":" + label2 + ": $a=1;"
                                    + label3 + ": $b=1;"
                                    + label4 + ": $c=1;"
                                    + "}",
                            "(switch $a"
                                    + " (cases" + getCases(label1, label2) + ") (cBlock (expr (= $a 1)))"
                                    + " (cases" + getCases(label3) + ") (cBlock (expr (= $b 1)))"
                                    + " (cases" + getCases(label4) + ") (cBlock (expr (= $c 1)))"
                                    + ")"
                    },
                    {
                            "switch($a){ " + label1 + ":" + label2 + ": " + label3 + ": $a=1; }",
                            "(switch $a (cases" + getCases(label1, label2, label3) + ") (cBlock (expr (= $a 1))))"
                    },
                    {
                            "switch($a){ "
                                    + label1 + ":" + label2 + ": " + label3 + ": $a=1;"
                                    + label4 + ": $b=1; "
                                    + "}",
                            "(switch $a"
                                    + " (cases" + getCases(label1, label2, label3) + ") (cBlock (expr (= $a 1)))"
                                    + " (cases" + getCases(label4) + ") (cBlock (expr (= $b 1)))"
                                    + ")"
                    },
                    {
                            "switch($a){ " + label1 + ": " + label2 + ": " + label3 + ":" + label4 + ": $a=1; }",
                            "(switch $a"
                                    + " (cases" + getCases(label1, label2, label3, label4) + ")"
                                    + " (cBlock (expr (= $a 1)))"
                                    + ")"
                    },
                    //second falls through
                    {
                            "switch($a){ " + label1 + ": $a=1; " + label2 + ":" + label3 + ": $b=1; }",
                            "(switch $a"
                                    + " (cases" + getCases(label1) + ") (cBlock (expr (= $a 1)))"
                                    + " (cases" + getCases(label2, label3) + ") (cBlock (expr (= $b 1)))"
                                    + ")"
                    },
                    {
                            "switch($a){ "
                                    + label1 + ": $a=1; "
                                    + label2 + ":" + label3 + ": $b=1; "
                                    + label4 + ": $c=1; "
                                    + "}",
                            "(switch $a"
                                    + " (cases" + getCases(label1) + ") (cBlock (expr (= $a 1)))"
                                    + " (cases" + getCases(label2, label3) + ") (cBlock (expr (= $b 1)))"
                                    + " (cases" + getCases(label4) + ") (cBlock (expr (= $c 1)))"
                                    + ")"
                    },
                    {
                            "switch($a){ "
                                    + label1 + ": $a=1; "
                                    + label2 + ":" + label3 + ": " + label4 + ": $b=1;"
                                    + "}",
                            "(switch $a"
                                    + " (cases" + getCases(label1) + ") (cBlock (expr (= $a 1)))"
                                    + " (cases" + getCases(label2, label3, label4) + ") (cBlock (expr (= $b 1)))"
                                    + ")"
                    },
                    //third falls through
                    {
                            "switch($a){"
                                    + label1 + ": $a=1; "
                                    + label2 + ": $b=1; "
                                    + label3 + ":" + label4 + ": $c=1; "
                                    + "}",
                            "(switch $a"
                                    + " (cases" + getCases(label1) + ") (cBlock (expr (= $a 1)))"
                                    + " (cases" + getCases(label2) + ") (cBlock (expr (= $b 1)))"
                                    + " (cases" + getCases(label3, label4) + ") (cBlock (expr (= $c 1)))"
                                    + ")"
                    },
                    //first and third fall through
                    {
                            "switch($a){"
                                    + label1 + ":" + label2 + ": $a=1;"
                                    + label3 + ":" + label4 + ": $b=1;"
                                    + "}",
                            "(switch $a"
                                    + " (cases" + getCases(label1, label2) + ") (cBlock (expr (= $a 1)))"
                                    + " (cases" + getCases(label3, label4) + ") (cBlock (expr (= $b 1)))"
                                    + ")"
                    },

                    //switches which do nothing
                    {
                            "switch($a){ " + label1 + ": }",
                            "(switch $a (cases" + getCases(label1) + ") cBlock)"
                    },
                    {
                            "switch($a){ " + label1 + ":" + label2 + ": }",
                            "(switch $a (cases" + getCases(label1, label2) + ") cBlock)"
                    },
                    {
                            "switch($a){ " + label1 + ":" + label2 + ":" + label3 + ":}",
                            "(switch $a (cases" + getCases(label1, label2, label3) + ") cBlock)"
                    },
                    {
                            "switch($a){ " + label1 + ":" + label2 + ":" + label3 + ":" + label4 + ":}",
                            "(switch $a (cases" + getCases(label1, label2, label3, label4) + ") cBlock)"
                    },
                    {
                            "switch($a){ "
                                    + label1 + ":$a=1;"
                                    + label2 + ":"
                                    + "}",
                            "(switch $a"
                                    + " (cases" + getCases(label1) + ") (cBlock (expr (= $a 1)))"
                                    + " (cases" + getCases(label2) + ") cBlock"
                                    + ")"
                    },
                    {
                            "switch($a){ "
                                    + label1 + ":$a=1;"
                                    + label2 + ":" + label3 + ":"
                                    + "}",
                            "(switch $a"
                                    + " (cases" + getCases(label1) + ") (cBlock (expr (= $a 1)))"
                                    + " (cases" + getCases(label2, label3) + ") cBlock"
                                    + ")"
                    },
                    {
                            "switch($a){ "
                                    + label1 + ":$a=1;"
                                    + label2 + ":" + label3 + ":" + label4 + ":"
                                    + "}",
                            "(switch $a"
                                    + " (cases" + getCases(label1) + ") (cBlock (expr (= $a 1)))"
                                    + " (cases" + getCases(label2, label3, label4) + ") cBlock"
                                    + ")"
                    },
                    {
                            "switch($a){ "
                                    + label1 + ":$a=1;"
                                    + label2 + ":$b=2;"
                                    + label3 + ":"
                                    + "}",
                            "(switch $a"
                                    + " (cases" + getCases(label1) + ") (cBlock (expr (= $a 1)))"
                                    + " (cases" + getCases(label2) + ") (cBlock (expr (= $b 2)))"
                                    + " (cases" + getCases(label3) + ") cBlock"
                                    + ")"
                    },
                    {
                            "switch($a){ "
                                    + label1 + ":$a=1;"
                                    + label2 + ":$b=2;"
                                    + label3 + ":" + label4 + ":"
                                    + "}",
                            "(switch $a"
                                    + " (cases" + getCases(label1) + ") (cBlock (expr (= $a 1)))"
                                    + " (cases" + getCases(label2) + ") (cBlock (expr (= $b 2)))"
                                    + " (cases" + getCases(label3, label4) + ") cBlock"
                                    + ")"
                    },
            }));
        }
        return collection;
    }

    private static String getCases(String... labels) {
        boolean hasDefault = false;
        StringBuilder stringBuilder = new StringBuilder();
        int caseLength = "case ".length();
        for (String label : labels) {
            if (!label.equals("default")) {
                stringBuilder.append(" ").append(label.substring(caseLength));
            } else {
                hasDefault = true;
            }
        }
        if (hasDefault) {
            stringBuilder.append(" default");
        }
        return stringBuilder.toString();
    }
}
