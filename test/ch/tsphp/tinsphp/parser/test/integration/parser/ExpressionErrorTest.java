/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class ExpressionErrorTest from the TSPHP project.
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

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ExpressionErrorTest extends AParserParserExceptionTest
{

    public ExpressionErrorTest(String testString, int character, int position) {
        super(testString, character, position, RecognitionException.class);
    }

    @Test
    public void test() throws Exception {
        parseExpectingException();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testStrings() {
        return Arrays.asList(new Object[][]{
                {"$a ! instanceof $b;", TinsPHPParser.LogicNot, 3},

                //TODO rstoll TINS-108 - class, TINS-109 - interface
                //not yet possible in PHP
//                {"new Foo()[0];", TinsPHPParser.LeftSquareBrace, 9},
                //expressions with only two operands
                {"1 < 2 < 3;", TinsPHPParser.LessThan, 6},
                {"1 < 2 <= 3;", TinsPHPParser.LessEqualThan, 6},
                {"1 < 2 > 3;", TinsPHPParser.GreaterThan, 6},
                {"1 < 2 >= 3;", TinsPHPParser.GreaterEqualThan, 6},
                {"1 <= 2 < 3;", TinsPHPParser.LessThan, 7},
                {"1 <= 2 <= 3;", TinsPHPParser.LessEqualThan, 7},
                {"1 <= 2 > 3;", TinsPHPParser.GreaterThan, 7},
                {"1 <= 2 >= 3;", TinsPHPParser.GreaterEqualThan, 7},
                {"1 > 2 < 3;", TinsPHPParser.LessThan, 6},
                {"1 > 2 <= 3;", TinsPHPParser.LessEqualThan, 6},
                {"1 > 2 > 3;", TinsPHPParser.GreaterThan, 6},
                {"1 > 2 >= 3;", TinsPHPParser.GreaterEqualThan, 6},
                {"1 >= 2 < 3;", TinsPHPParser.LessThan, 7},
                {"1 >= 2 <= 3;", TinsPHPParser.LessEqualThan, 7},
                {"1 >= 2 > 3;", TinsPHPParser.GreaterThan, 7},
                {"1 >= 2 >= 3;", TinsPHPParser.GreaterEqualThan, 7},

                {"1 == 2 == 3;", TinsPHPParser.Equal, 7},
                {"1 == 2 === 3;", TinsPHPParser.Identical, 7},
                {"1 == 2 != 3;", TinsPHPParser.NotEqual, 7},
                {"1 == 2 <> 3;", TinsPHPParser.NotEqualAlternative, 7},
                {"1 == 2 !== 3;", TinsPHPParser.NotIdentical, 7},
                {"1 === 2 == 3;", TinsPHPParser.Equal, 8},
                {"1 === 2 === 3;", TinsPHPParser.Identical, 8},
                {"1 === 2 != 3;", TinsPHPParser.NotEqual, 8},
                {"1 === 2 <> 3;", TinsPHPParser.NotEqualAlternative, 8},
                {"1 === 2 !== 3;", TinsPHPParser.NotIdentical, 8},
                {"1 != 2 == 3;", TinsPHPParser.Equal, 7},
                {"1 != 2 === 3;", TinsPHPParser.Identical, 7},
                {"1 != 2 != 3;", TinsPHPParser.NotEqual, 7},
                {"1 != 2 <> 3;", TinsPHPParser.NotEqualAlternative, 7},
                {"1 != 2 !== 3;", TinsPHPParser.NotIdentical, 7},
                {"1 <> 2 == 3;", TinsPHPParser.Equal, 7},
                {"1 <> 2 === 3;", TinsPHPParser.Identical, 7},
                {"1 <> 2 != 3;", TinsPHPParser.NotEqual, 7},
                {"1 <> 2 <> 3;", TinsPHPParser.NotEqualAlternative, 7},
                {"1 <> 2 !== 3;", TinsPHPParser.NotIdentical, 7},
                {"1 !== 2 == 3;", TinsPHPParser.Equal, 8},
                {"1 !== 2 === 3;", TinsPHPParser.Identical, 8},
                {"1 !== 2 != 3;", TinsPHPParser.NotEqual, 8},
                {"1 !== 2 <> 3;", TinsPHPParser.NotEqualAlternative, 8},
                {"1 !== 2 !== 3;", TinsPHPParser.NotIdentical, 8},

                //TODO rstoll TINS-106 parser procedural - expressions
                //increment/decrement a primitive type
//                {"++1;", TinsPHPParser.Int, 2},
//                {"--1;", TinsPHPParser.Int, 2},
//                {"1++;", TinsPHPParser.PlusPlus, 1},
//                {"1--;", TinsPHPParser.MinusMinus, 1},
//                {"++1.0;", TinsPHPParser.Float, 2},
//                {"--1.0;", TinsPHPParser.Float, 2},
//                {"1.1++;", TinsPHPParser.PlusPlus, 3},
//                {"1.1--;", TinsPHPParser.MinusMinus, 3},
//                {"++'a';", TinsPHPParser.String, 2},
//                {"--'a';", TinsPHPParser.String, 2},
//                {"'a'++;", TinsPHPParser.PlusPlus, 3},
//                {"'a'--;", TinsPHPParser.MinusMinus, 3},
                //TODO rstoll TINS-108 - class, TINS-109 - interface
                //semicolon is expected because the parser thinks its a sMemAccess
//                {"++E_ALL;", TinsPHPParser.Semicolon, 7},
//                {"--E_ALL;", TinsPHPParser.Semicolon, 7},
//                {"E_ALL++;", TinsPHPParser.PlusPlus, 5},
//                {"E_ALL--;", TinsPHPParser.MinusMinus, 5},
                //not allowed casts
                {"(mixed) $a;", TinsPHPParser.TypeMixed, 1},
                //misuse instanceof operator - does not work with primitive types
                {"$a instanceof bool;", TinsPHPParser.TypeBool, 14},
                {"$a instanceof int;", TinsPHPParser.TypeInt, 14},
                {"$a instanceof float;", TinsPHPParser.TypeFloat, 14},
                {"$a instanceof string;", TinsPHPParser.TypeString, 14},
                {"$a instanceof array;", TinsPHPParser.TypeArray, 14},
                {"$a instanceof resource;", TinsPHPParser.TypeResource, 14},
                {"$a instanceof mixed;", TinsPHPParser.TypeMixed, 14}
        });
    }
}
