/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class CloneTest from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.parser;

import ch.tsphp.tinsphp.parser.test.integration.testutils.AParserTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class CloneTest extends AParserTest
{

    public CloneTest(String testString) {
        super(testString);
    }

    @Test
    public void test() throws Exception {
        parseAndCheckForExceptions();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testStrings() {
        List<Object[]> collection = new ArrayList<>();
        //TODO rstoll TINS-108 - class, TINS-109 - interface
//        collection.addAll(MethodCallTest.getCalls("mixed $a = clone $this->"));
//        collection.addAll(MethodCallTest.getCalls("mixed $a = clone $a->"));
//        collection.addAll(MethodCallTest.getCalls("mixed $a = clone self::"));
//        collection.addAll(MethodCallTest.getCalls("mixed $a = clone parent::"));
//        collection.addAll(MethodCallTest.getCalls("mixed $a = clone Foo::"));
//        collection.addAll(MethodCallTest.getCalls("mixed $a = clone $this->a->"));
//        collection.addAll(MethodCallTest.getCalls("mixed $a = clone self::$a->"));
//        collection.addAll(MethodCallTest.getCalls("mixed $a = clone parent::$a->"));
//        collection.addAll(MethodCallTest.getCalls("mixed $a = clone Bar::$a->"));
        collection.addAll(Arrays.asList(new Object[][]{
                {"$a = clone $b;"},
                //TODO rstoll TINS-108 - class, TINS-109 - interface
//                    {"$a = clone $b->a;"},
//                    {"$a = clone $b->a[0];"},
//                    {"$a = clone self::$a;"},
//                    {"$a = clone self::$a[0];"},
//                    {"$a = clone parent::$a;"},
//                    {"$a = clone parent::$a[0];"},
//                    {"$a = clone Foo::$a;"},
//                    {"$a = clone Foo::$a[0];"},
        }));
        return collection;
    }
}
