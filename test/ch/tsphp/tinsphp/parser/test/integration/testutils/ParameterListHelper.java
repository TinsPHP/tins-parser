/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class ParameterListHelper from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.testutils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ParameterListHelper
{

    private ParameterListHelper() {
    }

    public static Collection<Object[]> getTestStrings(String prefix, String appendix,
            String prefixExpect, String appendixExpect) {

        //check all types
        List<Object[]> collection = TypeHelper.getAllTypesWithModifier(
                prefix, "$a" + appendix,
                prefixExpect + "(params (pDecl", "$a))" + appendixExpect, "");

        //normal
        collection.addAll(getVariations(
                prefix, "$a", appendix,
                prefixExpect, "(type tMod ?) $a", appendixExpect));

        collection.addAll(getVariationsForOptional(prefix, appendix, prefixExpect, appendixExpect));

        //empty params
        collection.add(new Object[]{prefix + appendix, prefixExpect + "params" + appendixExpect});
        return collection;
    }

    private static Collection<Object[]> getVariations(String prefix, String param, String appendix,
            String prefixExpect, String paramExpect, String appendixExpect) {
        return Arrays.asList(new Object[][]{
                {
                        prefix + param + appendix,
                        prefixExpect + "(params (pDecl " + paramExpect + "))" + appendixExpect
                },
                {
                        prefix + param + ", " + param + appendix,
                        prefixExpect + "(params "
                                + "(pDecl " + paramExpect + ") "
                                + "(pDecl " + paramExpect + ")"
                                + ")" + appendixExpect
                },
                {
                        prefix + param + ", " + param + ", " + param + "" + appendix,
                        prefixExpect + "(params "
                                + "(pDecl " + paramExpect + ") "
                                + "(pDecl " + paramExpect + ") "
                                + "(pDecl " + paramExpect + ")"
                                + ")" + appendixExpect
                }
        });
    }

    private static Collection<Object[]> getVariationsForOptional(String prefix, String appendix,
            String prefixExpect, String appendixExpect) {
        List<Object[]> collection = new ArrayList<>();
        String typeAst = "(type tMod ?)";
        collection.addAll(Arrays.asList(new Object[][]{
                //optional parameter
                {
                        prefix + "$a, $b='hallo'" + appendix,
                        prefixExpect + "(params "
                                + "(pDecl " + typeAst + " $a) "
                                + "(pDecl " + typeAst + " ($b 'hallo'))"
                                + ")" + appendixExpect
                },
                {
                        prefix + "$a, $i, $b=+1" + appendix,
                        prefixExpect + "(params "
                                + "(pDecl " + typeAst + " $a) "
                                + "(pDecl " + typeAst + " $i) "
                                + "(pDecl " + typeAst + " ($b (uPlus 1)))"
                                + ")" + appendixExpect
                },
                {
                        prefix + "$a, $i, $b=-10, $d=2.0" + appendix,
                        prefixExpect + "(params "
                                + "(pDecl " + typeAst + " $a) "
                                + "(pDecl " + typeAst + " $i) "
                                + "(pDecl " + typeAst + " ($b (uMinus 10))) "
                                + "(pDecl " + typeAst + " ($d 2.0))"
                                + ")" + appendixExpect
                },
                {
                        prefix + "$a=null, $b=true, $c=E_ALL" + appendix,
                        prefixExpect + "(params "
                                + "(pDecl " + typeAst + " ($a null)) "
                                + "(pDecl " + typeAst + " ($b true)) "
                                + "(pDecl " + typeAst + " ($c E_ALL#))"
                                + ")" + appendixExpect
                },
                {
                        prefix + "$a, $b=false, $d=null" + appendix,
                        prefixExpect + "(params "
                                + "(pDecl " + typeAst + " $a) "
                                + "(pDecl " + typeAst + " ($b false)) "
                                + "(pDecl " + typeAst + " ($d null))"
                                + ")" + appendixExpect
                },
                {
                        prefix + "$a=1, $b, $d=true" + appendix,
                        prefixExpect + "(params "
                                + "(pDecl " + typeAst + " ($a 1)) "
                                + "(pDecl " + typeAst + " $b) "
                                + "(pDecl " + typeAst + " ($d true))"
                                + ")" + appendixExpect
                },
                {
                        prefix + "$a=1, $b=2, $d" + appendix,
                        prefixExpect + "(params "
                                + "(pDecl " + typeAst + " ($a 1)) "
                                + "(pDecl " + typeAst + " ($b 2)) "
                                + "(pDecl " + typeAst + " $d)"
                                + ")" + appendixExpect
                },
                //See TSPHP-418
                {
                        prefix + "array $a= [2,2], array $b=array(1,'a'=>2)" + appendix,
                        prefixExpect + "(params "
                                + "(pDecl (type tMod array) ($a (array 2 2))) "
                                + "(pDecl (type tMod array) ($b (array 1 (=> 'a' 2))))"
                                + ")" + appendixExpect
                }
        }));


        String[] types = TypeHelper.getClassInterfaceTypes();

        for (String type : types) {
            collection.add(new Object[]{
                    prefix + "$a=" + type + "::a" + appendix,
                    prefixExpect
                            + "(params (pDecl " + typeAst + " ($a (sMemAccess " + type + " a#))))"
                            + appendixExpect
            });
        }
        return collection;
    }
}