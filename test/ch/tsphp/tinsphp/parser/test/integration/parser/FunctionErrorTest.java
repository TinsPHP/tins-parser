/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class FunctionErrorTest from the TSPHP project.
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
public class FunctionErrorTest extends AParserParserExceptionTest
{

    public FunctionErrorTest(String testString, int character, int position) {
        super(testString, character, position, RecognitionException.class);
    }

    @Test
    public void test() throws Exception {
        parseExpectingException();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testStrings() {
        List<Object[]> collection = new ArrayList<>();
        collection.add(new Object[]{"function a();", TinsPHPParser.Semicolon, 12});

        String fixture = "function foo($a, $b=";
        int fixtureLength = fixture.length();
        collection.addAll(Arrays.asList(new Object[][]{
                {fixture + "true or false" + "){}", TinsPHPParser.LogicOrWeak, fixtureLength + 5},
                {fixture + "true || false" + "){}", TinsPHPParser.LogicOr, fixtureLength + 5},
                {fixture + "true ? 1:2" + "){}", TinsPHPParser.QuestionMark, fixtureLength + 5},
                {fixture + "1 & 1" + "){}", TinsPHPParser.BitwiseAnd, fixtureLength + 2},
                {fixture + "1 == 1" + "){}", TinsPHPParser.Equal, fixtureLength + 2},
                {fixture + "1 < 1" + "){}", TinsPHPParser.LessThan, fixtureLength + 2},
                {fixture + "1 >> 1" + "){}", TinsPHPParser.ShiftRight, fixtureLength + 2},
                {fixture + "1 + 1" + "){}", TinsPHPParser.Plus, fixtureLength + 2},
                {fixture + "1 * 1" + "){}", TinsPHPParser.Multiply, fixtureLength + 2},
                {fixture + "!true" + "){}", TinsPHPParser.LogicNot, fixtureLength},
                {fixture + "1 instanceof 2" + "){}", TinsPHPParser.Instanceof, fixtureLength + 2},
                {fixture + "~1" + "){}", TinsPHPParser.BitwiseNot, fixtureLength},
                {fixture + "(bool) 1" + "){}", TinsPHPParser.LeftParenthesis, fixtureLength},
                {fixture + "(int) 1" + "){}", TinsPHPParser.LeftParenthesis, fixtureLength},
                {fixture + "(string) 1" + "){}", TinsPHPParser.LeftParenthesis, fixtureLength},
                {fixture + "(array) 1" + "){}", TinsPHPParser.LeftParenthesis, fixtureLength},
                {fixture + "(Foo) 1" + "){}", TinsPHPParser.LeftParenthesis, fixtureLength},
                {fixture + "@1" + "){}", TinsPHPParser.At, fixtureLength},
                {fixture + "new Foo()" + "){}", TinsPHPParser.New, fixtureLength},
                {fixture + "clone $a" + "){}", TinsPHPParser.Clone, fixtureLength},
                {fixture + "$a" + "){}", TinsPHPParser.VariableId, fixtureLength},
                {fixture + "foo()" + "){}", TinsPHPParser.LeftParenthesis, fixtureLength + 3},
                {fixture + "$a[0]" + "){}", TinsPHPParser.VariableId, fixtureLength},
                {fixture + "$a->a" + "){}", TinsPHPParser.VariableId, fixtureLength}
        }));

        return collection;
    }
}
