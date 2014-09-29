/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class FloatDeclarationTest from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.parser;

import ch.tsphp.tinsphp.parser.test.integration.lexer.TokenTest;
import ch.tsphp.tinsphp.parser.test.integration.testutils.AParserTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class FloatDeclarationTest extends AParserTest
{

    public FloatDeclarationTest(String testString) {
        super(testString);
    }

    @Test
    public void test() throws Exception {
        parseAndCheckForExceptions();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testStrings() {
        List<Object[]> collection = new ArrayList<>();
        String[] floatStrings = TokenTest.getFloatTestStrings();
        for (String floatString : floatStrings) {
            collection.add(new Object[]{"$a = " + floatString + ";"});
            collection.add(new Object[]{"$a = +" + floatString + ";"});
            collection.add(new Object[]{"$a = -" + floatString + ";"});
        }
        return collection;
    }
}
