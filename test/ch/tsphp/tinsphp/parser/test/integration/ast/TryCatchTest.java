/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class TryCatchTest from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.ast;

import ch.tsphp.tinsphp.parser.test.integration.testutils.AAstTest;
import ch.tsphp.tinsphp.parser.test.integration.testutils.TypeHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class TryCatchTest extends AAstTest
{

    public TryCatchTest(String testString, String expectedResult) {
        super(testString, expectedResult);
    }

    @Test
    public void test() throws Exception {
        compareAst();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testStrings() {
        List<Object[]> collection = new ArrayList<>();
        String[] types = TypeHelper.getClassInterfaceTypes();
        for (String type : types) {
            collection.add(new Object[]{
                    "try{$a=1;}catch(" + type + " $e){}",
                    "(try "
                            + "(cBlock (expr (= $a 1))) "
                            + "(catch " + type + " $e cBlock)"
                            + ")"
            });
        }
        collection.addAll(Arrays.asList(new Object[][]{
                {
                        "try{$a=1;}catch(\\Exception $e){} catch(\\a\\MyException $e){$a=1;$b=2;}",
                        "(try "
                                + "(cBlock (expr (= $a 1))) "
                                + "(catch \\Exception $e cBlock) "
                                + "(catch \\a\\MyException $e (cBlock (expr (= $a 1)) (expr (= $b " +
                                "2))))"
                                + ")"
                },
                {
                        "try{$a=1;}catch(a $e){} catch(b $e){$a=1;$b=2;}catch(c $e){}",
                        "(try "
                                + "(cBlock (expr (= $a 1))) "
                                + "(catch a $e cBlock) "
                                + "(catch b $e (cBlock (expr (= $a 1)) (expr (= $b 2)))) "
                                + "(catch c $e cBlock)"
                                + ")"
                }
        }));
        return collection;
    }
}
