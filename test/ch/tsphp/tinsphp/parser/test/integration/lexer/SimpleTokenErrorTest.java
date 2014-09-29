/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class SimpleTokenErrorTest from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.lexer;

import ch.tsphp.tinsphp.parser.antlr.TinsPHPLexer;
import ch.tsphp.tinsphp.parser.test.integration.testutils.ALexerTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class SimpleTokenErrorTest extends ALexerTest
{
    
    public SimpleTokenErrorTest(String methodName) {
        //` is not valid for any token as first letter
        super(methodName, "`", 0);
    }
    
    @Test
    public void testTokens() throws Exception {
        super.checkForMismatch();
    }
    
    @Parameterized.Parameters
    public static Collection<Object[]> testStrings() {
        List<Object[]> collection = new ArrayList<>();
        Method[] methods = TinsPHPLexer.class.getMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (isTokenMethodOrSynpredFragment(methodName)) {
                collection.add(new Object[]{methodName});
            }
        }
        return collection;
    }

    private static boolean isTokenMethodOrSynpredFragment(String methodName) {
        return methodName.charAt(0) == 'm' && methodName.charAt(1) >= 'A' && methodName.charAt(1) <= 'Z'
                || (methodName.length() > 10 && methodName.substring(0, 7).equals("synpred") 
                && methodName.substring(methodName.length()-8, methodName.length()).equals("fragment"));
    }
}
