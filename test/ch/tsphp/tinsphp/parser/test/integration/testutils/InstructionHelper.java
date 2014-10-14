/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class InstructionHelper from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.testutils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InstructionHelper
{
    private InstructionHelper() {
    }

    public static List<Object[]> getInstructions(String prefix, String appendix) {
        List<Object[]> collection = new ArrayList<>();
        //TODO rstoll TINS-68 parser procedural - control structures
//        collection.addAll(getControlStructures(prefix, "int $a;", appendix));
        collection.addAll(Arrays.asList(new Object[][]{
                {prefix + "$a;" + appendix},
                {prefix + ";" + appendix},
                //TODO rstoll TINS-68 parser procedural - control structures
//                {prefix + "return;" + appendix},
//                {prefix + "throw new Exception();" + appendix},
//                {prefix + "break;" + appendix},
//                {prefix + "continue;" + appendix},
//                {prefix + "echo 'hello';" + appendix}
        }));
        String[] expressions = ExpressionHelper.getParserExpressions();
        for (String expression : expressions) {
            collection.add(new Object[]{prefix + expression + ";" + appendix});
        }
        return collection;
    }

    public static List<Object[]> getControlStructuresInNamespaceFunctionAndMethod(String instruction) {
        List<Object[]> collection = new ArrayList<>();
        collection.addAll(getControlStructures("", instruction, ""));
        collection.addAll(getControlStructures("function foo(){", instruction, "}"));
        //TODO rstoll TINS-108 - class
//        collection.addAll(getControlStructures("class a{function void foo(){", instruction, "}}"));

        return collection;
    }

    public static List<Object[]> getControlStructures(String prefix, String instruction, String appendix) {
        return Arrays.asList(new Object[][]{
                {prefix + instruction + appendix},
                {prefix + "{" + instruction + "}" + appendix},
                {prefix + "{ {" + instruction + "} }" + appendix},
                {prefix + "if($a)" + instruction + appendix},
                {prefix + "if($a) $a=1; else " + instruction + appendix},
                {prefix + "if($a){" + instruction + "}" + appendix},
                {prefix + "if($a){$a=1;}else{" + instruction + "}" + appendix},
                //TODO rstoll TINS-68 parser procedural - control structures
//                {prefix + "switch($a){case 1: " + instruction + "}" + appendix},
//                {prefix + "switch($a){case 1: $a=1; " + instruction + " default: $a=2; " + instruction + "}" +
//                        appendix},
//                {prefix + "switch($a){case 1:{ $a=1; " + instruction + "} default: $a=2; { " + instruction + "} }" +
//                        appendix},
//                {prefix + "for(;;) " + instruction + appendix},
//                {prefix + "for(;;){ " + instruction + "}" + appendix},
//                {prefix + "foreach([] as int $k)" + instruction + appendix},
//                {prefix + "foreach([] as int $k){" + instruction + "}" + appendix},
//                {prefix + "while(true)" + instruction + appendix},
//                {prefix + "while(true){" + instruction + "}" + appendix},
//                {prefix + "do " + instruction + " while(true);" + appendix},
//                {prefix + "do{ " + instruction + "}while(true);" + appendix},
//                {prefix + "try{" + instruction + "}catch(\\Exception $e){}" + appendix},
//                {prefix + "try{$a=1;}catch(\\Exception $e){" + instruction + "}" + appendix}
        });
    }
}
