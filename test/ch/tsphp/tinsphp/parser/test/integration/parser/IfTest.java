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

package ch.tsphp.tinsphp.parser.test.integration.parser;

import ch.tsphp.tinsphp.parser.test.integration.testutils.AParserTest;
import ch.tsphp.tinsphp.parser.test.integration.testutils.InstructionHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class IfTest extends AParserTest
{

    public IfTest(String testString) {
        super(testString);
    }

    @Test
    public void test() throws Exception {
        parseAndCheckForExceptions();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testStrings() {
        List<Object[]> collection = new ArrayList<>();
        collection.addAll(InstructionHelper.getControlStructuresInNamespaceFunctionAndMethod("if($a){}"));
        collection.addAll(Arrays.asList(new Object[][]{
                {"iF($a) $b=1; else $b=2;"},
                {"if($a){ $b=1;} else $b=2;"},
                {"if($a) $b=1; else{ $b=2;}"},
                {"if($a){ $b=1;} else{ $b=2;}"},
                {"if($a){ $b=1; $b=1;} else{ $b=2; $b=1;}"},
                {"if($a){ $b=1; $b=2;} else{ $b=2; $b=1;}"},
                {"if($a){ $b=1; $b=2;} else if ($a) $b=1;"},
                {"if($a){ $b=1; $b=2;} else if ($a){$b=1;}"},
                {"if($a) $b=1; else if ($a){$b=1;} else $b=2;"},
                {"if($a){ if($b){ $b=2;}} else if ($a){$b=1;} else $b=2;"},
                {"if($a){ $c=2;} else { if($a) $c=3; else if($a){ $b=2;}else $d=0;}"},
        }));
        return collection;
    }
}
