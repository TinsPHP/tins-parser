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
                prefixExpect, "$a", appendixExpect));

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
        collection.addAll(Arrays.asList(new Object[][]{
                //optional parameter
                {
                        prefix + "$a, $b='hallo'" + appendix,
                        prefixExpect + "(params "
                                + "(pDecl $a) "
                                + "(pDecl ($b 'hallo'))"
                                + ")" + appendixExpect
                },
                {
                        prefix + "$a, $i, $b=+1" + appendix,
                        prefixExpect + "(params "
                                + "(pDecl $a) "
                                + "(pDecl $i) "
                                + "(pDecl ($b (uPlus 1)))"
                                + ")" + appendixExpect
                },
                {
                        prefix + "$a, $i, $b=-10, $d=2.0" + appendix,
                        prefixExpect + "(params "
                                + "(pDecl $a) "
                                + "(pDecl $i) "
                                + "(pDecl ($b (uMinus 10))) "
                                + "(pDecl ($d 2.0))"
                                + ")" + appendixExpect
                },
                {
                        prefix + "$a=null, $b=true, $c=E_ALL" + appendix,
                        prefixExpect + "(params "
                                + "(pDecl ($a null)) "
                                + "(pDecl ($b true)) "
                                + "(pDecl ($c E_ALL#))"
                                + ")" + appendixExpect
                },
                {
                        prefix + "$a, $b=false, $d=null" + appendix,
                        prefixExpect + "(params "
                                + "(pDecl $a) "
                                + "(pDecl ($b false)) "
                                + "(pDecl ($d null))"
                                + ")" + appendixExpect
                },
                {
                        prefix + "$a=1, $b, $d=true" + appendix,
                        prefixExpect + "(params "
                                + "(pDecl ($a 1)) "
                                + "(pDecl $b) "
                                + "(pDecl ($d true))"
                                + ")" + appendixExpect
                },
                {
                        prefix + "$a=1, $b=2, $d" + appendix,
                        prefixExpect + "(params "
                                + "(pDecl ($a 1)) "
                                + "(pDecl ($b 2)) "
                                + "(pDecl $d)"
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
                    prefix + "int $a=" + type + "::a" + appendix,
                    prefixExpect
                            + "(params (pDecl (type tMod int) ($a (sMemAccess " + type + " a#))))"
                            + appendixExpect
            });
        }
        return collection;
    }
}