/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class ArrayDeclarationTest from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.parser;

import ch.tsphp.tinsphp.parser.test.integration.testutils.AParserTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class ArrayDeclarationTest extends AParserTest
{

    public ArrayDeclarationTest(String testString) {
        super(testString);
    }

    @Test
    public void test() throws Exception {
        parseAndCheckForExceptions();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testStrings() {
        List<Object[]> collection = new ArrayList<>();
        String[] strings = getArrayTestStrings();
        for (String string : strings) {
            collection.add(new Object[]{"$a = " + string + ";"});
        }

        //Expressions are covered in ch.tsphp.parser.test.integration.ast.ArrayDeclarationTest

        return collection;
    }

    public static String[] getArrayTestStrings() {
        return new String[]{
                "[]",
                "array()",
                "[1,2.0,'single quoted string', \"double quoted string\",true,false]",
                "array(1,2.0,'single quoted string', \"double quoted string\",true,false)",
                "[[],array()]",
                "array([],array())",
                "[1 => 1, 2.0=>1, 'single quoted key'=>\"hello\", \"double quoted key\"=> 'velo']",
                "array(1 => 1, 2.0=>1, 'single quoted key'=>\"hello\", \"double quoted key\"=> 'velo')",
                "[1=>[],2.0=>array()]",
                "array(1=>[],2.0=>array())",
                "[1 => 1, 2.0=>1, 'single quoted key'=>\"hello\", \"double quoted key\"=> 'velo', "
                        + "1 => array(), 2=>[],1,2.0,'single quoted string', \"double quoted string\", "
                        + "true,false,[],array()]",
                "array(1 => 1, 2.0=>1, 'single quoted key'=>\"hello\", \"double quoted key\"=> 'velo', "
                        + "1 => array(), 2=>[],1,2.0,'single quoted string', \"double quoted string\", "
                        + "true,false,[],array())"
        };
    }
}
