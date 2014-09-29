/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class ConstantAccessTest from the TSPHP project.
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
public class ConstantAccessTest extends AAstTest
{

    public ConstantAccessTest(String testString, String expectedResult) {
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
        //TODO rstoll TINS-108 - class, TINS-109 - interface
        /*for(String type: types){
            collection.add(new Object[]{
                "int $a = "+type+"::a;", 
                "(vars (type tMod int) ($a (sMemAccess "+type+" a#)))"
            });
        }*/
        collection.addAll(Arrays.asList(new Object[][]{
                {"$a = a;", "(vars ($a a#))"},
                //TODO rstoll TINS-108 - class, TINS-109 - interface
//                    {"int $a = self::a;", "(vars (type tMod int) ($a (sMemAccess self a#)))"},
//                    {"int $a = parent::a;", "(vars (type tMod int) ($a (sMemAccess parent a#)))"},
        }));
        return collection;
    }
}
