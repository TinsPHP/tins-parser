/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class IncrementDecrementErrorTest from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.parser;

import ch.tsphp.tinsphp.parser.antlr.TinsPHPParser;
import ch.tsphp.tinsphp.parser.test.integration.testutils.AParserParserExceptionTest;
import org.antlr.runtime.RecognitionException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class IncrementDecrementErrorTest extends AParserParserExceptionTest
{

    public IncrementDecrementErrorTest(String testString, int character, int position) {
        super(testString, character, position, RecognitionException.class);

    }

    @Test
    public void test() throws Exception {
        parseExpectingException();
    }

    @Override
    protected void run() throws RecognitionException {
        parser.instruction();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testStrings() {
        List<Object[]> collection = new ArrayList<>();
        Object[][] operators = new Object[][]{
                {"++", TinsPHPParser.PlusPlus},
                {"--", TinsPHPParser.MinusMinus}
        };
        for (Object[] op : operators) {
            collection.addAll(Arrays.asList(new Object[][]{
                    //TODO delete this once the TODO below is done
                    {op[0] + "foo();", TinsPHPParser.Identifier, 2},
                    //semicolon is wrong since it expects a fieAccess after a mCall
                    {op[0] + "$a->foo();", TinsPHPParser.ObjectOperator, 4},
                    {op[0] + "$a[0]->foo();", TinsPHPParser.ObjectOperator, 7},
                    {op[0] + "$a->a->foo();", TinsPHPParser.ObjectOperator, 4},
                    {op[0] + "$a->bar()->foo();", TinsPHPParser.ObjectOperator, 4},
                    {"$a->foo()" + op[0] + ";", TinsPHPParser.ObjectOperator, 2},
                    {"$a[0]->foo()" + op[0] + ";", TinsPHPParser.ObjectOperator, 5},
                    {"$a->a->foo()" + op[0] + ";", TinsPHPParser.ObjectOperator, 2},
                    {"$a->bar()->foo()" + op[0] + ";", TinsPHPParser.ObjectOperator, 2},
                    //TODO rstoll TINS-108 - class, TINS-109 - interface
                    /*
                    {op[0] + "foo();", TinsPHPParser.LeftParenthesis, 5},
                    //semicolon is wrong since it expects a fieAccess after a mCall
                    {op[0] + "$a->foo();", TinsPHPParser.Semicolon, 11},
                    {op[0] + "$a[0]->foo();", TinsPHPParser.Semicolon, 14},
                    {op[0] + "$a->a->foo();", TinsPHPParser.Semicolon, 14},
                    {op[0] + "$a->bar()->foo();", TinsPHPParser.Semicolon, 18},

                    {op[0] + "$this->foo();", TinsPHPParser.Semicolon, 14},
                    {op[0] + "$this[0]->foo();", TinsPHPParser.Semicolon, 17},
                    {op[0] + "$this->a->foo();", TinsPHPParser.Semicolon, 17},
                    {op[0] + "$this->bar()->foo();", TinsPHPParser.Semicolon, 21},
                    {op[0] + "self::$a->foo();", TinsPHPParser.Semicolon, 17},
                    {op[0] + "self::$a[0]->foo();", TinsPHPParser.Semicolon, 20},
                    {op[0] + "self::$a->a->foo();", TinsPHPParser.Semicolon, 20},
                    {op[0] + "self::$a->bar()->foo();", TinsPHPParser.Semicolon, 24},
                    {op[0] + "parent::$a->foo();", TinsPHPParser.Semicolon, 19},
                    {op[0] + "parent::$a[0]->foo();", TinsPHPParser.Semicolon, 22},
                    {op[0] + "parent::$a->a->foo();", TinsPHPParser.Semicolon, 22},
                    {op[0] + "parent::$a->bar()->foo();", TinsPHPParser.Semicolon, 26},
                    {op[0] + "Asdf::$a->foo();", TinsPHPParser.Semicolon, 17},
                    {op[0] + "Asdf::$a[0]->foo();", TinsPHPParser.Semicolon, 20},
                    {op[0] + "Asdf::$a->a->foo();", TinsPHPParser.Semicolon, 20},
                    {op[0] + "Asdf::$a->bar()->foo();", TinsPHPParser.Semicolon, 24},

                    {"$a->foo()" + op[0] + ";", op[1], 9},
                    {"$a[0]->foo()" + op[0] + ";", op[1], 12},
                    {"$a->a->foo()" + op[0] + ";", op[1], 12},
                    {"$a->bar()->foo()" + op[0] + ";", op[1], 16},
                    {"$this->foo()" + op[0] + ";", op[1], 12},
                    {"$this[0]->foo()" + op[0] + ";", op[1], 15},
                    {"$this->a->foo()" + op[0] + ";", op[1], 15},
                    {"$this->bar()->foo()" + op[0] + ";", op[1], 19},
                    {"self::$a->foo()" + op[0] + ";", op[1], 15},
                    {"self::$a[0]->foo()" + op[0] + ";", op[1], 18},
                    {"self::$a->a->foo()" + op[0] + ";", op[1], 18},
                    {"self::$a->bar()->foo()" + op[0] + ";", op[1], 22},
                    {"parent::$a->foo()" + op[0] + ";", op[1], 17},
                    {"parent::$a[0]->foo()" + op[0] + ";", op[1], 20},
                    {"parent::$a->a->foo()" + op[0] + ";", op[1], 20},
                    {"parent::$a->bar()->foo()" + op[0] + ";", op[1], 24},
                    {"Asdf::$a->foo()" + op[0] + ";", op[1], 15},
                    {"Asdf::$a[0]->foo()" + op[0] + ";", op[1], 18},
                    {"Asdf::$a->a->foo()" + op[0] + ";", op[1], 18},
                    {"Asdf::$a->bar()->foo()" + op[0] + ";", op[1], 22},
                    */
            }));
        }
        return collection;
    }
}