/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class VariationHelper from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.testutils;


import ch.tsphp.tinsphp.parser.antlr.TinsPHPParser;

import java.util.ArrayList;
import java.util.List;

public class VariationHelper
{
    private VariationHelper() {
    }

    public static List<Object[]> getUpperCaseVariations(String[] texts, String prefix, String appendix) {
        List<Object[]> collection = new ArrayList<>();
        int prefixLength = prefix.length();
        for (String text : texts) {
            int length = text.length();
            String tmp = text.toLowerCase();
            for (int i = 0; i < length; ++i) {
                String before = i != 0 ? tmp.substring(0, i) : "";

                String upper = tmp.substring(i, i + 1).toUpperCase();

                String after = i + 1 != length ? tmp.substring(i + 1, length) : "";

                collection.add(new Object[]{
                        prefix + before + upper + after + appendix,
                        TinsPHPParser.Identifier,
                        prefixLength
                });
            }
        }
        return collection;
    }

    public static String[] getUppercaseCombinations(String token) {
        int tokenLength = token.length();
        String character = token.substring(0, 1);
        String characterUpperCase = character.toUpperCase();

        String[] combinations;
        if (tokenLength > 1) {
            combinations = new String[(int) Math.pow(2, tokenLength)];
            String[] subCombinations = getUppercaseCombinations(token.substring(1));
            for (int i = 0; i < subCombinations.length; ++i) {
                combinations[i * 2] = character + subCombinations[i];
                combinations[i * 2 + 1] = characterUpperCase + subCombinations[i];
            }

        } else {
            combinations = new String[]{character, characterUpperCase};
        }

        return combinations;
    }
}
