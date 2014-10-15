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
public class WhileTest extends AParserTest
{

    public WhileTest(String testString) {
        super(testString);
    }

    @Test
    public void test() throws Exception {
        parseAndCheckForExceptions();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testStrings() {
        List<Object[]> collection = new ArrayList<>();
        collection.addAll(
                InstructionHelper.getControlStructuresInNamespaceFunctionAndMethod("while(true) $a=1;"));

        collection.addAll(Arrays.asList(new Object[][]{
                {"while(true) $a=1;"},
                {"while(true){$a=1;}"},
                {"while(true){$a=1; $b=2;}"},
                {"do $a=1; while(true);"},
                {"do {$a=1;} while(true);"},
                {"do {$a=1;$b=2;} while( true  );"}
        }));

        return collection;
    }
}
