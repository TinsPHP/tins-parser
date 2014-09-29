/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class IdentifierHelper from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.testutils;

public class IdentifierHelper
{

    private IdentifierHelper() {
    }

    public static String getAllLowerCaseCharacters() {
        return getCharacterFromStartToEndAsString(97, 122);
    }

    public static String getAllUpperCaseCharacters() {
        return getCharacterFromStartToEndAsString(65, 90);
    }

    public static String getAllDigits() {
        return getCharacterFromStartToEndAsString(48, 57);
    }

    public static String getAllSpecialCharacters() {
        return getCharacterFromStartToEndAsString(127, 255);
    }

    public static String getCharacterFromStartToEndAsString(int start, int end) {
        StringBuilder buffer = new StringBuilder();
        for (int i = start; i <= end; ++i) {
            buffer.append((char) i);
        }
        return buffer.toString();
    }

    public static String asciiToString(int asciiCode) {
        return String.valueOf((char) asciiCode);
    }
}
