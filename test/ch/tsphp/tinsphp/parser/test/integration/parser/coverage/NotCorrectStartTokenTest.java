/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class InstructionInstructionTest from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.parser.coverage;

import ch.tsphp.tinsphp.parser.antlr.TinsPHPParser;
import ch.tsphp.tinsphp.parser.test.integration.testutils.AParserParserExceptionTest;
import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.MissingTokenException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class NotCorrectStartTokenTest extends AParserParserExceptionTest
{

    private String methodName;

    public NotCorrectStartTokenTest(String theMethodName, String testString, int aToken, int aPosition,
            Class<? extends RecognitionException> type) {
        super(testString + " - " + theMethodName, aToken, aPosition, type);
        methodName = theMethodName;
    }

    @Test
    public void test() throws Exception {
        parseExpectingException();
    }

    protected void run() throws RecognitionException, NoSuchMethodException, InvocationTargetException,
            IllegalAccessException {
        Method method = TinsPHPParser.class.getMethod(methodName);
        result = (org.antlr.runtime.ParserRuleReturnScope) method.invoke(parser);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testStrings() {

        return Arrays.asList(new Object[][]{
                {"actualParameters", "else", TinsPHPParser.Else, 0, MismatchedTokenException.class},
                {"array", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"arrayContent", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"arrayKeyValue", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"assignment", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"atom", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"bitwiseAnd", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"bitwiseOr", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"bitwiseShift", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"bitwiseXor", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"classInterfaceTypeWithoutMixed", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"cloneOrNew", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"comparison", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"compilationUnit", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"constDefinitionList", "else", TinsPHPParser.Else, 0, MismatchedTokenException.class},
                {"constantAssignment", "else", TinsPHPParser.Else, 0, MismatchedTokenException.class},
                {"definition", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"equality", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"equalityOperator", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"expression", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"expressionList", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"factor", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"formalParameters", "else", TinsPHPParser.Else, 0, MismatchedTokenException.class},
                {"functionCall", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"functionDefinition", "else", TinsPHPParser.Else, 0, MismatchedTokenException.class},
                {"functionIdentifier", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"globalConstant", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"instanceOf", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"instruction", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"logicAnd", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"logicAndWeak", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"logicOr", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"logicOrWeak", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"logicXorWeak", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"methodIdentifier", "else", TinsPHPParser.Else, 0, MismatchedTokenException.class},
                {"namespaceBracket", "else", TinsPHPParser.Else, 0, MismatchedTokenException.class},
                {"namespaceId", "else", TinsPHPParser.Else, 0, MismatchedTokenException.class},
                {"namespaceIdOrEmpty", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"namespaceSemicolon", "else", TinsPHPParser.Else, 0, MismatchedTokenException.class},
                {"paramDeclaration", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"paramList", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"postFixCall", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"postFixVariableWithoutCallAtTheEnd", "else", TinsPHPParser.Else, 0, MissingTokenException.class},
                {"primary", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"primitiveAtomWithConstant", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"scalarTypesInclArrayWithModifier", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"statement", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"termOrStringConcatenation", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"ternary", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"unary", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"unaryPrimitiveAtom", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"useDefinitionList", "else", TinsPHPParser.Else, 0, MismatchedTokenException.class},
                {"useDefinition", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                {"usingType", "else", TinsPHPParser.Else, 0, NoViableAltException.class},
                // withoutNamespace can be empty and since compilationUnit checks if next token is EOF,
                // this test does not fail
                //{"withoutNamespace", "else", TinsPHPParser.Else, 0, MismatchedTokenException.class},
        });
    }
}
