/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class FloatErrorTest from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.lexer;

import ch.tsphp.tinsphp.parser.test.integration.testutils.ALexerTest;
import ch.tsphp.tinsphp.parser.test.integration.testutils.IdentifierHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class FragmentsTest extends ALexerTest
{

    public FragmentsTest(String methodName, String testString) {
        super(methodName, testString, 0);
    }

    @Test
    public void testTokens() throws Exception {
        analyseAndCheckForError();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testStrings() {
        List<Object[]> collection = new ArrayList<>();
        collection.addAll(Arrays.asList(new Object[][]{
                {"mEXPONENT", "e0"},
                {"mEXPONENT", "e1234075896"},
                {"mEXPONENT", "e+0"},
                {"mEXPONENT", "e+1234075896"},
                {"mEXPONENT", "e-0"},
                {"mEXPONENT", "e-1234075896"},
        }));
        collection.addAll(getIntFragments());
        collection.addAll(getStringFragments());

        return collection;
    }

    public static Collection<Object[]> getIntFragments() {
        return Arrays.asList(new Object[][]{
                {"mBINARY", "0b000001"},
                {"mBINARY", "0b100001"},
                {"mBINARY", "0b1"},
                {"mBINARY", "0b0"},
                {"mDECIMAL", "0"},
                {"mDECIMAL", "1"},
                {"mDECIMAL", "21023456789"},
                {"mHEXADECIMAL", "0x0"},
                {"mHEXADECIMAL", "0x1"},
                {"mHEXADECIMAL", "0x1012456789"},
                {"mHEXADECIMAL", "0xabcdef"},
                {"mHEXADECIMAL", "0xABEDFC"},
                {"mHEXADECIMAL", "0x1A2de45B7896E3DaFbCcf"},
                {"mHEXADECIMAL", "0X0"},
                {"mHEXADECIMAL", "0X1"},
                {"mHEXADECIMAL", "0X1012456789"},
                {"mHEXADECIMAL", "0Xabcdef"},
                {"mHEXADECIMAL", "0XABEDFC"},
                {"mHEXADECIMAL", "0X1A2de45B7896E3DaFbCcf"},
                {"mOCTAL", "00"},
                {"mOCTAL", "01"},
                {"mOCTAL", "01234567"},
                {"mOCTAL", "01247056311"}
        });
    }

    public static Collection<Object[]> getStringFragments() {

        return Arrays.asList(new Object[][]{
                {"mSTRING_DOUBLE_QUOTED", "\"\""},
                {"mSTRING_DOUBLE_QUOTED", "\"123\""},
                {"mSTRING_DOUBLE_QUOTED", "\"1.3\""},
                {"mSTRING_DOUBLE_QUOTED", "\"1.3e10\""},
                {"mSTRING_DOUBLE_QUOTED", "\"true\""},
                {"mSTRING_DOUBLE_QUOTED", "\"false\""},
                {"mSTRING_DOUBLE_QUOTED", "\"10 \\$\""},
                {"mSTRING_DOUBLE_QUOTED", "\"" + IdentifierHelper.getAllDigits() + "\""},
                {"mSTRING_DOUBLE_QUOTED", "\"" + IdentifierHelper.getAllLowerCaseCharacters() + "\""},
                {"mSTRING_DOUBLE_QUOTED", "\"" + IdentifierHelper.getAllUpperCaseCharacters() + "\""},
                {"mSTRING_DOUBLE_QUOTED", "\""
                        + IdentifierHelper.getCharacterFromStartToEndAsString(0, 33)
                        + IdentifierHelper.asciiToString(35)
                        + IdentifierHelper.getCharacterFromStartToEndAsString(37, 12255)
                        + "\""},
                {"mSTRING_DOUBLE_QUOTED", "\" \\\\ \\\" \\\\\\\" \\\"\\\\ \""},
                {"mSTRING_DOUBLE_QUOTED", "\"\\n \\r \\t \\e \\f \\\" \\$ \\' \""},
                {"mSTRING_DOUBLE_QUOTED", "\"\\\"! \\\"# \\\"[ \\\"] \""},
                {"mSTRING_DOUBLE_QUOTED", "\"\\$! \\$# \\$[ \\$] \""},
                {"mSTRING_DOUBLE_QUOTED", "\"\\! \\# \\[ \\] \\ \""},
                {"mSTRING_DOUBLE_QUOTED", "\"! # [ ] \""},
                {"mSTRING_DOUBLE_QUOTED", "\" \\n \""},
                {"mSTRING_DOUBLE_QUOTED", "\" \\r \""},
                {"mSTRING_DOUBLE_QUOTED", "\" \\t  \""},
                {"mSTRING_DOUBLE_QUOTED", "\" \\v \""},
                {"mSTRING_DOUBLE_QUOTED", "\" \\e \""},
                {"mSTRING_DOUBLE_QUOTED", "\" \\f \""},
                {"mSTRING_DOUBLE_QUOTED", "\" \\\" \""},
                {"mSTRING_DOUBLE_QUOTED", "\" \\$ \""},
                {"mSTRING_DOUBLE_QUOTED", "\" \\00 \\01 \\02 \\03 \\04 \\05 \\06 \\07 \\01234567 \""},
                {"mSTRING_DOUBLE_QUOTED", "\" \\x0 \\x1 \\x2 \\x3 \\x4 \\x5 \\x6 \\x7 \\x8 \\x9 \\XA \\XB \\XC \\XD " +
                        "\\xE \\xF \\Xa \\Xb \\xc \\xd \\xe \\xf \""},
                {"mSTRING_DOUBLE_QUOTED", "\"a \\\\ b \\\\n c \\\\r d \\\\t e \\\\v f \\\\e g \\\\f h \\\"\""},
                {"mSTRING_SINGLE_QUOTED", "''"},
                {"mSTRING_SINGLE_QUOTED", "'456'"},
                {"mSTRING_SINGLE_QUOTED", "'58.0'"},
                {"mSTRING_SINGLE_QUOTED", "'.456'"},
                {"mSTRING_SINGLE_QUOTED", "'1578e10'"},
                {"mSTRING_SINGLE_QUOTED", "'true'"},
                {"mSTRING_SINGLE_QUOTED", "'false'"},
                {"mSTRING_SINGLE_QUOTED", "'10 $'"},
                {"mSTRING_SINGLE_QUOTED", "'" + IdentifierHelper.getAllDigits() + "'"},
                {"mSTRING_SINGLE_QUOTED", "'" + IdentifierHelper.getAllLowerCaseCharacters() + "'"},
                {"mSTRING_SINGLE_QUOTED", "'" + IdentifierHelper.getAllUpperCaseCharacters() + "'"},
                {"mSTRING_SINGLE_QUOTED", "'"
                        + IdentifierHelper.getCharacterFromStartToEndAsString(0, 35)
                        + IdentifierHelper.getCharacterFromStartToEndAsString(37, 38)
                        + IdentifierHelper.getCharacterFromStartToEndAsString(40, 10055)
                        + "'"},
                {"mSTRING_SINGLE_QUOTED", "'\\\\ \\\' \\\\\\\' \\\'\\\\ '"},
                {"mSTRING_SINGLE_QUOTED", "'\\n \\r \\t \\e \\f \\\" \\$'"},
                {"mSTRING_SINGLE_QUOTED", "'\\'& \\'[ \\'] '"},
                {"mSTRING_SINGLE_QUOTED", "' \\& \\[  \\] '"},
                {"mSTRING_SINGLE_QUOTED", "' & [  ] \\ '"},});
    }
}
