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
import java.util.Collection;
import java.util.List;

public class ConstantHelper
{

    private ConstantHelper() {
    }

    public static Collection<Object[]> testStrings(String prefix, String appendix,
            String prefixExpected, String appendixExpected) {
        List<Object[]> collection = new ArrayList<>();

        String[] types = TypeHelper.getClassInterfaceTypes();
        for (String type : types) {
            collection.add(new Object[]{
                prefix + "const int b=" + type + "::a" + ", c=" + type + "::d;" + appendix,
                prefixExpected
                + "(consts (type (tMod public static final) int) (b# (sMemAccess " + type + " a#)) "
                + "(c# (sMemAccess " + type + " d#)))"
                + appendixExpected
            });
        }

        types = TypeHelper.getScalarTypes();
        for (String type : types) {
            collection.add(new Object[]{
                prefix + "const " + type + " a=true;" + appendix,
                prefixExpected + "(consts (type (tMod public static final) " + type + ") (a# true))" + appendixExpected
            });
            collection.add(new Object[]{
                prefix + "const " + type + " a=true, b=false;" + appendix,
                prefixExpected + "(consts (type (tMod public static final) " + type + ") "
                + "(a# true) (b# false))" + appendixExpected
            });
            collection.add(new Object[]{
                prefix + "const " + type + " a=1,b=2;" + appendix,
                prefixExpected + "(consts (type (tMod public static final) " + type + ") "
                + "(a# 1) (b# 2))" + appendixExpected
            });
            collection.add(new Object[]{
                prefix + "const " + type + " a=1.0,b=2.0,c=null;" + appendix,
                prefixExpected + "(consts (type (tMod public static final) " + type + ") "
                + "(a# 1.0) (b# 2.0) (c# null))" + appendixExpected
            });
            collection.add(new Object[]{
                prefix + "const " + type + " a=1,b=\"2\",c=null,d='2';" + appendix,
                prefixExpected + "(consts (type (tMod public static final) " + type + ") "
                + "(a# 1) (b# \"2\") (c# null) (d# '2'))" + appendixExpected
            });
        }
        return collection;

    }
}
