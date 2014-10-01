/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class PostFixWithCallAtTheEndTest from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
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
public class PostFixWithCallAtTheEndTest extends AAstTest
{

    public PostFixWithCallAtTheEndTest(String testString, String expectedResult) {
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
                //TODO rstoll TINS-108 - class, TINS-109 - interface
//                {"$a->foo()", "(mCall $a foo() args)"},
//                {"$this->foo()", "(mCall $this foo() args)"},
//                {"self::foo()", "(mCall self foo() args)"},
//                {"parent::foo()", "(mCall parent foo() args)"},
//                {"Foo::foo()", "(smCall Foo foo() args)"},

                {"$a[0]", "(arrAccess $a 0)"},
                //TODO rstoll TINS-108 - class, TINS-109 - interface
//                {"$this[0]", "(arrAccess $this 0)"},

                //TODO rstoll TINS-108 - class, TINS-109 - interface
//                {"$this->a", "(fieAccess $this a)"},
//                {"self::$a", "(sMemAccess self $a)"},
//                {"parent::$a", "(sMemAccess parent $a)"},
//                {"Foo::$a", "(sMemAccess Foo $a)"},
//                {"\\bar\\Foo::$a", "(sMemAccess \\bar\\Foo $a)"},
        };
        List<Object[]> tmp4 = getVariations(tmp1);

        for (Object[] expression : tmp4) {
            collection.addAll(Arrays.asList(new String[][]{
                    {expression[0] + ";", "(expr " + expression[1] + ")"},
                    {expression[0] + "[$i+$j%2];", "(expr (arrAccess " + expression[1] + " (+ $i (% $j 2))))"},
                    //TODO rstoll TINS-108 - class, TINS-109 - interface
//                    {expression[0] + "->c;", "(expr (fieAccess " + expression[1] + " c))"},
//                    {expression[0] + "->foo($a + $b);", "(expr (mpCall " + expression[1] + " foo() (args (+ $a $b))
// ))"}
            }));
        }

        return collection;
    }

    public static List<Object[]> getVariations(String[][] tmp1) {
        List<Object[]> tmp2 = new ArrayList<>();
        for (String[] expression : tmp1) {
            tmp2.add(expression);
            tmp2.addAll(Arrays.asList(new String[][]{
                    {expression[0] + "[0]", "(arrAccess " + expression[1] + " 0)"},
                    //TODO rstoll TINS-108 - class, TINS-109 - interface
//                    {expression[0] + "->a", "(fieAccess " + expression[1] + " a)"},
//                    {expression[0] + "->foo('hello')", "(mpCall " + expression[1] + " foo() (args 'hello'))"}
            }));
        }

        List<Object[]> tmp3 = new ArrayList<>();
        for (Object[] expression : tmp2) {
            tmp3.add(expression);
            tmp3.addAll(Arrays.asList(new String[][]{
                    {expression[0] + "[$i]", "(arrAccess " + expression[1] + " $i)"},
                    //TODO rstoll TINS-108 - class, TINS-109 - interface
//                    {expression[0] + "->b", "(fieAccess " + expression[1] + " b)"},
//                    {expression[0] + "->foo(1,2)", "(mpCall " + expression[1] + " foo() (args 1 2))"}
            }));
        }

        List<Object[]> tmp4 = new ArrayList<>();
        for (Object[] expression : tmp3) {
            tmp4.add(expression);
            tmp4.addAll(Arrays.asList(new String[][]{
                    {expression[0] + "[$i]", "(arrAccess " + expression[1] + " $i)"},
                    //TODO rstoll TINS-108 - class, TINS-109 - interface
//                    {expression[0] + "->b", "(fieAccess " + expression[1] + " b)"},
//                    {expression[0] + "->foo(1,2)", "(mpCall " + expression[1] + " foo() (args 1 2))"}
            }));
        }
        return tmp4;
    }
}
