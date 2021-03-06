/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class TypeHelper from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.testutils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypeHelper
{

    public static String[] getClassInterfaceTypes() {

        return new String[]{
                //TODO rstoll TINS-108 - class, TINS-109 - interface
//                "B",
//                "a\\C",
//                "a\\b\\A",
//                "\\e",
//                "\\f\\D",
//                "\\g\\b\\A"
        };
    }

    public static String[][] getScalarTypesInclArray() {
        return new String[][]{
                {"bool", "bool"},
                {"boolean", "bool"},
                {"int", "int"},
                {"integer", "int"},
                {"float", "float"},
                {"double", "float"},
                {"real", "float"},
                {"string", "string"},
                {"array", "array"}
        };
    }

    public static List<Object[]> getAllTypesWithModifier(String prefix, String appendix,
            String prefixExpect, String appendixExpect, String cmMod) {
        List<Object[]> collection = new ArrayList<>();
        String tMod = cmMod.isEmpty() ? "tMod" : "(tMod " + cmMod + ")";
        collection.addAll(Arrays.asList(new String[][]{
                {prefix + " " + appendix, prefixExpect + " (type " + tMod + " ?) " + appendixExpect},
                {prefix + " array " + appendix, prefixExpect + " (type " + tMod + " array) " + appendixExpect},
        }));
        String[] types = getClassInterfaceTypes();
        for (String type : types) {
            collection.add(new String[]{
                    prefix + type + appendix,
                    prefixExpect + "(type " + tMod + " " + type + ")" + appendixExpect
            });
        }
        return collection;
    }
}
