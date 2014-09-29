/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class TokenExceptionTest from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.lexer;

import ch.tsphp.tinsphp.parser.antlr.TinsPHPLexer;
import ch.tsphp.tinsphp.parser.test.integration.testutils.ALexerTest;
import ch.tsphp.tinsphp.parser.test.integration.testutils.IdentifierHelper;
import ch.tsphp.tinsphp.parser.test.integration.testutils.VariationHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class TokenTest extends ALexerTest
{

    public TokenTest(String methodName, String testString, int type) {
        super(methodName, testString, type);
    }

    @Test
    public void testTokens() throws Exception {
        analyseAndCheckForError();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testStrings() {
        List<Object[]> collection = new ArrayList<>(1700);
        collection.addAll(Arrays.asList(new Object[][]{
                {"mAbstract", "abstract", TinsPHPLexer.Abstract},
                {"mArrow", "=>", TinsPHPLexer.Arrow},
                {"mAs", "as", TinsPHPLexer.As},
                {"mAssign", "=", TinsPHPLexer.Assign},
                {"mAt", "@", TinsPHPLexer.At},
                {"mBackslash", "\\", TinsPHPLexer.Backslash},
                {"mBitwiseAnd", "&", TinsPHPLexer.BitwiseAnd},
                {"mBitwiseAndAssign", "&=", TinsPHPLexer.BitwiseAndAssign},
                {"mBitwiseNot", "~", TinsPHPLexer.BitwiseNot},
                {"mBitwiseOr", "|", TinsPHPLexer.BitwiseOr},
                {"mBitwiseOrAssign", "|=", TinsPHPLexer.BitwiseOrAssign},
                {"mBitwiseXor", "^", TinsPHPLexer.BitwiseXor},
                {"mBitwiseXorAssign", "^=", TinsPHPLexer.BitwiseXorAssign},
                {"mBool", "true", TinsPHPLexer.Bool},
                {"mBool", "false", TinsPHPLexer.Bool},
                {"mBreak", "break", TinsPHPLexer.Break},
                {"mCase", "case", TinsPHPLexer.Case},
                {"mCast", "cast", TinsPHPLexer.Cast},
                {"mCatch", "catch", TinsPHPLexer.Catch},
                {"mClass", "class", TinsPHPLexer.Class},
                {"mClone", "clone", TinsPHPLexer.Clone},
                {"mColon", ":", TinsPHPLexer.Colon},
                {"mComma", ",", TinsPHPLexer.Comma},
                {"mConst", "const", TinsPHPLexer.Const},
                {"mConstruct", "__construct", TinsPHPLexer.Construct},
                {"mContinue", "continue", TinsPHPLexer.Continue},
                {"mDefault", "default", TinsPHPLexer.Default},
                {"mDestruct", "__destruct", TinsPHPLexer.Destruct},
                {"mDivide", "/", TinsPHPLexer.Divide},
                {"mDivideAssign", "/=", TinsPHPLexer.DivideAssign},
                {"mDo", "do", TinsPHPLexer.Do},
                {"mDollar", "$", TinsPHPLexer.Dollar},
                {"mDot", ".", TinsPHPLexer.Dot},
                {"mDotAssign", ".=", TinsPHPLexer.DotAssign},
                {"mDoubleColon", "::", TinsPHPLexer.DoubleColon},
                {"mEcho", "echo", TinsPHPLexer.Echo},
                {"mElse", "else", TinsPHPLexer.Else},
                {"mEqual", "==", TinsPHPLexer.Equal},
                {"mExtends", "extends", TinsPHPLexer.Extends},
                {"mExit", "exit", TinsPHPLexer.Exit},
                {"mFinal", "final", TinsPHPLexer.Final},
                {"mFunction", "function", TinsPHPLexer.Function},
                {"mFor", "for", TinsPHPLexer.For},
                {"mForeach", "foreach", TinsPHPLexer.Foreach},
                {"mGreaterThan", ">", TinsPHPLexer.GreaterThan},
                {"mGreaterEqualThan", ">=", TinsPHPLexer.GreaterEqualThan},
                {"mIdentical", "===", TinsPHPLexer.Identical},
                {"mIf", "if", TinsPHPLexer.If},
                {"mImplements", "implements", TinsPHPLexer.Implements},
                {"mInstanceof", "instanceof", TinsPHPLexer.Instanceof},
                {"mInterface", "interface", TinsPHPLexer.Interface},
                {"mLeftCurlyBrace", "{", TinsPHPLexer.LeftCurlyBrace},
                {"mLeftParenthesis", "(", TinsPHPLexer.LeftParenthesis},
                {"mLeftSquareBrace", "[", TinsPHPLexer.LeftSquareBrace},
                {"mLessThan", "<", TinsPHPLexer.LessThan},
                {"mLessEqualThan", "<=", TinsPHPLexer.LessEqualThan},
                {"mLogicAnd", "&&", TinsPHPLexer.LogicAnd},
                {"mLogicAndWeak", "and", TinsPHPLexer.LogicAndWeak},
                {"mLogicNot", "!", TinsPHPLexer.LogicNot},
                {"mLogicOr", "||", TinsPHPLexer.LogicOr},
                {"mLogicOrWeak", "or", TinsPHPLexer.LogicOrWeak},
                {"mLogicXorWeak", "xor", TinsPHPLexer.LogicXorWeak},
                {"mMinus", "-", TinsPHPLexer.Minus},
                {"mMinusAssign", "-=", TinsPHPLexer.MinusAssign},
                {"mMinusMinus", "--", TinsPHPLexer.MinusMinus},
                {"mModulo", "%", TinsPHPLexer.Modulo},
                {"mModuloAssign", "%=", TinsPHPLexer.ModuloAssign},
                {"mMultiply", "*", TinsPHPLexer.Multiply},
                {"mMultiplyAssign", "*=", TinsPHPLexer.MultiplyAssign},
                {"mNamespace", "namespace", TinsPHPLexer.Namespace},
                {"mNew", "new", TinsPHPLexer.New},
                {"mNotEqual", "!=", TinsPHPLexer.NotEqual},
                {"mNotIdentical", "!==", TinsPHPLexer.NotIdentical},
                {"mNull", "null", TinsPHPLexer.Null},
                {"mObjectOperator", "->", TinsPHPLexer.ObjectOperator},
                {"mParent", "parent", TinsPHPLexer.Parent},
                {"mParentColonColon", "parent::", TinsPHPLexer.ParentColonColon},
                {"mPlus", "+", TinsPHPLexer.Plus},
                {"mPlusAssign", "+=", TinsPHPLexer.PlusAssign},
                {"mPlusPlus", "++", TinsPHPLexer.PlusPlus},
                {"mPrivate", "private", TinsPHPLexer.Private},
                {"mProtected", "protected", TinsPHPLexer.Protected},
                {"mProtectThis", "this", TinsPHPLexer.ProtectThis},
                {"mPublic", "public", TinsPHPLexer.Public},
                {"mQuestionMark", "?", TinsPHPLexer.QuestionMark},
                {"mReturn", "return", TinsPHPLexer.Return},
                {"mRightCurlyBrace", "}", TinsPHPLexer.RightCurlyBrace},
                {"mRightParenthesis", ")", TinsPHPLexer.RightParenthesis},
                {"mRightSquareBrace", "]", TinsPHPLexer.RightSquareBrace},
                {"mSelf", "self", TinsPHPLexer.Self},
                {"mSelfColonColon", "self::", TinsPHPLexer.SelfColonColon},
                {"mSemicolon", ";", TinsPHPLexer.Semicolon},
                {"mShiftLeft", "<<", TinsPHPLexer.ShiftLeft},
                {"mShiftLeftAssign", "<<=", TinsPHPLexer.ShiftLeftAssign},
                {"mShiftRight", ">>", TinsPHPLexer.ShiftRight},
                {"mShiftRightAssign", ">>=", TinsPHPLexer.ShiftRightAssign},
                {"mStatic", "static", TinsPHPLexer.Static},
                {"mSwitch", "switch", TinsPHPLexer.Switch},
                {"mThis", "$this", TinsPHPLexer.This},
                {"mThrow", "throw", TinsPHPLexer.Throw},
                {"mTry", "try", TinsPHPLexer.Try},
                {"mTypeArray", "array", TinsPHPLexer.TypeArray},
                {"mTypeBool", "bool", TinsPHPLexer.TypeBool},
                {"mTypeAliasBool", "boolean", TinsPHPLexer.TypeAliasBool},
                {"mTypeAliasFloat", "double", TinsPHPLexer.TypeAliasFloat},
                {"mTypeFloat", "float", TinsPHPLexer.TypeFloat},
                {"mTypeInt", "int", TinsPHPLexer.TypeInt},
                {"mTypeAliasInt", "integer", TinsPHPLexer.TypeAliasInt},
                {"mTypeMixed", "mixed", TinsPHPLexer.TypeMixed},
                {"mTypeResource", "resource", TinsPHPLexer.TypeResource},
                {"mTypeString", "string", TinsPHPLexer.TypeString},
                {"mUse", "use", TinsPHPLexer.Use},
                {"mVoid", "void", TinsPHPLexer.Void},
                {"mWhile", "while", TinsPHPLexer.While}
        }));

        String[] floatStrings = getFloatTestStrings();
        for (String floatString : floatStrings) {
            collection.add(new Object[]{"mFloat", floatString, TinsPHPLexer.Float});
            collection.add(new Object[]{"mTokens", floatString, TinsPHPLexer.Float});
        }

        Collection<Object[]> intCollection = FragmentsTest.getIntFragments();
        for (Object[] obj : intCollection) {
            collection.add(new Object[]{"mInt", obj[1], TinsPHPLexer.Int});
        }

        Collection<Object[]> stringCollection = FragmentsTest.getStringFragments();
        for (Object[] obj : stringCollection) {
            collection.add(new Object[]{"mString", obj[1], TinsPHPLexer.String});
        }

        Collection<Object[]> ids = getIDTestStrings();

        for (Object[] obj : ids) {
            collection.add(new Object[]{"mVariableId", "$" + obj[1], TinsPHPLexer.VariableId});
            collection.add(new Object[]{obj[0], obj[1] + "" + obj[1], TinsPHPLexer.Identifier});
            collection.add(new Object[]{obj[0], obj[1] + "" + obj[1] + "" + obj[1], TinsPHPLexer.Identifier});
        }
        collection.addAll(Arrays.asList(new Object[][]{
                {"mIdentifier", IdentifierHelper.getAllLowerCaseCharacters()
                        + IdentifierHelper.getAllUpperCaseCharacters() + IdentifierHelper.getAllDigits()
                        + IdentifierHelper.getAllSpecialCharacters() + "_", TinsPHPLexer.Identifier},
                {"mIdentifier", IdentifierHelper.getAllUpperCaseCharacters()
                        + IdentifierHelper.getAllLowerCaseCharacters() + IdentifierHelper.getAllDigits()
                        + IdentifierHelper.getAllSpecialCharacters() + "_", TinsPHPLexer.Identifier},
                {"mIdentifier", IdentifierHelper.getAllSpecialCharacters() + IdentifierHelper
                        .getAllUpperCaseCharacters() + IdentifierHelper.getAllLowerCaseCharacters() +
                        IdentifierHelper.getAllDigits() + "_", TinsPHPLexer.Identifier},
                {"mIdentifier", "_" + IdentifierHelper.getAllSpecialCharacters() + IdentifierHelper
                        .getAllUpperCaseCharacters() + IdentifierHelper.getAllLowerCaseCharacters() +
                        IdentifierHelper.getAllDigits(), TinsPHPLexer.Identifier}
        }));

        String[] nullCombinations = VariationHelper.getUppercaseCombinations("null");
        for (String combination : nullCombinations) {
            collection.add(new Object[]{"mNull", combination, TinsPHPLexer.Null});
        }

        String[] trueCombinations = VariationHelper.getUppercaseCombinations("true");
        for (String combination : trueCombinations) {
            collection.add(new Object[]{"mBool", combination, TinsPHPLexer.Bool});
        }

        String[] falseCombinations = VariationHelper.getUppercaseCombinations("false");
        for (String combination : falseCombinations) {
            collection.add(new Object[]{"mBool", combination, TinsPHPLexer.Bool});
        }

        //add all tokens again this time with mTokens as method
        List<Object[]> argumentsArray = new ArrayList<>(collection);
        for (Object[] arguments : argumentsArray) {
            collection.add(new Object[]{"mTokens", arguments[1], arguments[2]});
        }

        return collection;
    }

    public static String[] getFloatTestStrings() {
        return new String[]{
                "5867491023e7845120963",
                "5867491023e+7538964120",
                "5867491023e-9510236478",
                "1234567890.19876543201",
                "1234567890.19876543201e7845120963",
                "1234567890.19876543201e+7538964120",
                "1234567890.19876543201e-9510236478",
                ".4567891203",
                ".4567891203e7418520963",
                ".4567891203e+0147258369",
                ".4567891203e-1024578963",
                ".0",
                "0.0",
                "0.0e0",
                "0.0e+0",
                "0.0e-0"
        };
    }

    public static Collection<Object[]> getIDTestStrings() {
        List<Object[]> collection = new ArrayList<>();
        for (int i = 'a'; i < 'z'; ++i) {
            collection.add(new Object[]{"mIdentifier", IdentifierHelper.asciiToString(i), TinsPHPLexer.Identifier});
        }
        for (int i = 'A'; i < 'Z'; ++i) {
            collection.add(new Object[]{"mIdentifier", IdentifierHelper.asciiToString(i), TinsPHPLexer.Identifier});
        }
        for (int i = 127; i <= 255; ++i) {
            collection.add(new Object[]{"mIdentifier", IdentifierHelper.asciiToString(i), TinsPHPLexer.Identifier});
        }
        collection.add(new Object[]{"mIdentifier", "_", TinsPHPLexer.Identifier});
        return collection;
    }
}
