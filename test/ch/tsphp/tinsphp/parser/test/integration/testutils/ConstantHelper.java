/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class ConstantHelper from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.testutils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ConstantHelper
{

    private ConstantHelper() {
    }

    public static Collection<Object[]> testStrings(String prefix, String appendix,
            String prefixExpected, String appendixExpected) {
        List<Object[]> collection = new ArrayList<>();

        final String typeAst = "(type (tMod public static final) ?)";
        String[] types = TypeHelper.getClassInterfaceTypes();
        for (String type : types) {
            collection.add(new Object[]{
                    prefix + "const b=" + type + "::a" + ", c=" + type + "::d;" + appendix,
                    prefixExpected
                            + "(consts " + typeAst + " (b# (sMemAccess " + type + " a#)) "
                            + "(c# (sMemAccess " + type + " d#)))"
                            + appendixExpected
            });
        }
        collection.addAll(Arrays.asList(new Object[][]{
                {
                        prefix + "const a=true;" + appendix,
                        prefixExpected + "(consts " + typeAst + " (a# true))" + appendixExpected
                },
                {
                        prefix + "const a=true, b=false;" + appendix,
                        prefixExpected + "(consts " + typeAst + " (a# true) (b# false))" + appendixExpected
                },
                {
                        prefix + "const a=1, b=2;" + appendix,
                        prefixExpected + "(consts " + typeAst + " (a# 1) (b# 2))" + appendixExpected
                },
                {
                        prefix + "const a=1.2, b=2.0;" + appendix,
                        prefixExpected + "(consts " + typeAst + " (a# 1.2) (b# 2.0))" + appendixExpected
                },
                {
                        prefix + "const a='hi', b='hello';" + appendix,
                        prefixExpected + "(consts " + typeAst + " (a# 'hi') (b# 'hello'))" + appendixExpected
                },
                {
                        prefix + "const a=null, b=false, c=1, d=23.3, e='hm';" + appendix,
                        prefixExpected
                                + "(consts " + typeAst + " (a# null) (b# false) (c# 1) (d# 23.3) (e# 'hm'))"
                                + appendixExpected
                },
        }));
        return collection;

    }
}
