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
                "B",
                "a\\C",
                "a\\b\\A",
                "\\e",
                "\\f\\D",
                "\\g\\b\\A"
        };
    }

    public static List<String> getAllTypesWithoutMixed() {
        List<String> collection = new ArrayList<>();
        collection.addAll(Arrays.asList(getScalarTypes()));
        collection.add("array");
        collection.add("resource");
        collection.addAll(Arrays.asList(getClassInterfaceTypes()));
        return collection;
    }

    public static List<String> getAllTypesWithoutScalar() {
        List<String> collection = new ArrayList<>();
        collection.addAll(Arrays.asList(getClassInterfaceTypes()));
        collection.add("array");
        collection.add("resource");
        collection.add("mixed");
        return collection;
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
        return getAllTypesWithModifier(prefix, appendix, prefixExpect, appendixExpect, cmMod, true);
    }

    public static List<Object[]> getAllTypesWithoutMixedWithModifier(String prefix, String appendix,
            String prefixExpect, String appendixExpect, String cmMod) {
        return getAllTypesWithModifier(prefix, appendix, prefixExpect, appendixExpect, cmMod, false);
    }

    private static List<Object[]> getAllTypesWithModifier(String prefix, String appendix,
            String prefixExpect, String appendixExpect, String cmMod, boolean withMixed) {
        String tMod = cmMod.isEmpty() ? "tMod" : "(tMod " + cmMod + ")";
        String tModInclCast = cmMod.isEmpty() ? "(tMod cast)" : "(tMod cast " + cmMod + ")";
        String tModInclNullable = cmMod.isEmpty() ? "(tMod ?)" : "(tMod ? " + cmMod + ")";
        String tModInclFalseable = cmMod.isEmpty() ? "(tMod !)" : "(tMod ! " + cmMod + ")";
        String tModInclCastNullable = cmMod.isEmpty() ? "(tMod cast ?)" : "(tMod cast ? " + cmMod + ")";
        String tModInclCastFalseable = cmMod.isEmpty() ? "(tMod cast !)" : "(tMod cast ! " + cmMod + ")";
        String tModInclNullableFalseable = cmMod.isEmpty() ? "(tMod ! ?)" : "(tMod ! ? " + cmMod + ")";
        String tModInclCastNullableFalseable = cmMod.isEmpty() ? "(tMod cast ! ?)" : "(tMod cast ! ? " + cmMod + ")";

        List<Object[]> collection = new ArrayList<>();
        String[] types = getScalarTypes();
        for (String type : types) {
            collection.addAll(Arrays.asList(new String[][]{
                    {prefix + type + appendix, prefixExpect + "(type " + tMod + " " + type + ")" + appendixExpect},
                    {
                            prefix + "cast " + type + appendix,
                            prefixExpect + "(type " + tModInclCast + " " + type + ")" + appendixExpect
                    },
                    {
                            prefix + type + "?" + appendix,
                            prefixExpect + "(type " + tModInclNullable + " " + type + ")" + appendixExpect
                    },
                    {
                            prefix + type + "!" + appendix,
                            prefixExpect + "(type " + tModInclFalseable + " " + type + ")" + appendixExpect
                    },
                    {
                            prefix + "cast " + type + "?" + appendix,
                            prefixExpect + "(type " + tModInclCastNullable + " " + type + ")" + appendixExpect
                    },

                    {
                            prefix + "cast " + type + "!" + appendix,
                            prefixExpect + "(type " + tModInclCastFalseable + " " + type + ")" + appendixExpect
                    },
                    {
                            prefix + type + "!?" + appendix,
                            prefixExpect + "(type " + tModInclNullableFalseable + " " + type + ")" + appendixExpect
                    },
                    {
                            prefix + "cast " + type + "!?" + appendix,
                            prefixExpect + "(type " + tModInclCastNullableFalseable + " " + type + ")" + appendixExpect
                    }
            }));
        }

        collection.addAll(Arrays.asList(new String[][]{
                {
                        prefix + "array" + appendix,
                        prefixExpect + "(type " + tMod + " array)" + appendixExpect
                },
                {
                        prefix + "cast array" + appendix,
                        prefixExpect + "(type " + tModInclCast + " array)" + appendixExpect
                },
                {
                        prefix + "array!" + appendix,
                        prefixExpect + "(type " + tModInclFalseable + " array)" + appendixExpect
                },
                {
                        prefix + "cast array!" + appendix,
                        prefixExpect + "(type " + tModInclCastFalseable + " array)" + appendixExpect
                }
        }));

        types = getClassInterfaceTypes();
        for (String type : types) {
            collection.addAll(Arrays.asList(new String[][]{
                    {
                            prefix + type + appendix,
                            prefixExpect + "(type " + tMod + " " + type + ")" + appendixExpect
                    },
                    {
                            prefix + "cast " + type + appendix,
                            prefixExpect + "(type " + tModInclCast + " " + type + ")" + appendixExpect
                    },
                    {
                            prefix + type + "!" + appendix,
                            prefixExpect + "(type " + tModInclFalseable + " " + type + ")" + appendixExpect
                    },
                    {
                            prefix + "cast " + type + "!" + appendix,
                            prefixExpect + "(type " + tModInclCastFalseable + " " + type + ")" + appendixExpect
                    }
            }));
        }
        collection.addAll(Arrays.asList(new String[][]{
                {
                        prefix + "resource" + appendix,
                        prefixExpect + "(type " + tMod + " resource)" + appendixExpect
                },
                {
                        prefix + "cast resource" + appendix,
                        prefixExpect + "(type " + tModInclCast + " resource)" + appendixExpect
                },
                {
                        prefix + "resource!" + appendix,
                        prefixExpect + "(type " + tModInclFalseable + " resource)" + appendixExpect
                },
                {
                        prefix + "cast resource!" + appendix,
                        prefixExpect + "(type " + tModInclCastFalseable + " resource)" + appendixExpect
                }
        }));

        if (withMixed) {
            collection.add(new String[]{
                    prefix + "mixed" + appendix,
                    prefixExpect + "(type " + tMod + " mixed)" + appendixExpect
            });
        }
        return collection;
    }
}
