/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class KeywordErrorTest from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.parser;

import ch.tsphp.tinsphp.parser.antlr.TinsPHPParser;
import ch.tsphp.tinsphp.parser.test.integration.testutils.AParserParserExceptionTest;
import ch.tsphp.tinsphp.parser.test.integration.testutils.VariationHelper;
import org.antlr.runtime.RecognitionException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class KeywordErrorTest extends AParserParserExceptionTest
{

    public KeywordErrorTest(String testString, int token, int position) {
        super(testString, token, position, RecognitionException.class);
    }

    @Test
    public void test() throws Exception {
        parseExpectingException();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> vars() {
        List<Object[]> collection = new ArrayList<>(9600);
        Object[][] keywords = getKeywords();
        for (Object[] keyword : keywords) {
            String[] keywordVariants = VariationHelper.getUppercaseCombinations((String) keyword[0]);
            for (String keywordVariant : keywordVariants) {
                collection.add(new Object[]{"function " + keywordVariant + "(){}", keyword[1], 9});
            }
        }
        return collection;
    }

    public static Object[][] getKeywords() {
        return new Object[][]{
                {"abstract", TinsPHPParser.Abstract},
                {"and", TinsPHPParser.LogicAndWeak},
                {"array", TinsPHPParser.TypeArray},
                {"as", TinsPHPParser.As},
                {"bool", TinsPHPParser.TypeBool},
                {"boolean", TinsPHPParser.TypeAliasBool},
                {"break", TinsPHPParser.Break},
                {"case", TinsPHPParser.Case},
                {"cast", TinsPHPParser.Cast},
                {"catch", TinsPHPParser.Catch},
                {"class", TinsPHPParser.Class},
                {"clone", TinsPHPParser.Clone},
                {"const", TinsPHPParser.Const},
                {"__construct", TinsPHPParser.Construct},
                {"continue", TinsPHPParser.Continue},
                {"__destruct", TinsPHPParser.Destruct},
                {"default", TinsPHPParser.Default},
                {"do", TinsPHPParser.Do},
                {"double", TinsPHPParser.TypeAliasFloat},
                {"echo", TinsPHPParser.Echo},
                {"else", TinsPHPParser.Else},
                {"exit", TinsPHPParser.Exit},
                {"extends", TinsPHPParser.Extends},
                {"final", TinsPHPParser.Final},
                {"float", TinsPHPParser.TypeFloat},
                {"for", TinsPHPParser.For},
                {"foreach", TinsPHPParser.Foreach},
                {"function", TinsPHPParser.Function},
                {"if", TinsPHPParser.If},
                {"implements", TinsPHPParser.Implements},
                {"int", TinsPHPParser.TypeInt},
                {"integer", TinsPHPParser.TypeAliasInt},
                {"instanceof", TinsPHPParser.Instanceof},
                {"interface", TinsPHPParser.Interface},
                {"namespace", TinsPHPParser.Namespace},
                {"new", TinsPHPParser.New},
                {"null", TinsPHPParser.Null},
                {"mixed", TinsPHPParser.TypeMixed},
                {"object", TinsPHPParser.TypeObject},
                {"or", TinsPHPParser.LogicOrWeak},
                {"parent", TinsPHPParser.Parent},
                {"private", TinsPHPParser.Private},
                {"protected", TinsPHPParser.Protected},
                {"public", TinsPHPParser.Public},
                {"real", TinsPHPParser.TypeAliasFloat2},
                {"resource", TinsPHPParser.TypeResource},
                {"return", TinsPHPParser.Return},
                {"static", TinsPHPParser.Static},
                {"self", TinsPHPParser.Self},
                {"string", TinsPHPParser.TypeString},
                {"switch", TinsPHPParser.Switch},
                {"this", TinsPHPParser.ProtectThis},
                {"throw", TinsPHPParser.Throw},
                {"try", TinsPHPParser.Try},
                {"use", TinsPHPParser.Use},
                {"void", TinsPHPParser.Void},
                {"while", TinsPHPParser.While},
                {"xor", TinsPHPParser.LogicXorWeak},
        };
    }
}
