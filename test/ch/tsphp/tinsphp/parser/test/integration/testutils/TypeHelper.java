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

    public static List<String> getAllTypes() {
        List<String> types = new ArrayList<>();
        types.addAll(getPrimitiveTypes());
        types.addAll(Arrays.asList(getClassInterfaceTypes()));
        return types;
    }

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

    public static List<String> getPrimitiveTypes() {
        List<String> collection = new ArrayList<>(7);
        collection.addAll(Arrays.asList(getScalarTypes()));
        collection.add("array");
        collection.add("resource");
        collection.add("mixed");
        return collection;
    }

    public static String[] getScalarTypes() {
        return new String[]{
                "bool",
                "int",
                "float",
                "string"
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
