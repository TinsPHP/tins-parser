/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class FloatErrorTest from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.lexer;


import ch.tsphp.tinsphp.parser.test.integration.testutils.ALexerTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FloatErrorTest extends ALexerTest
{

    public FloatErrorTest(String methodName, String testString) {
        super(methodName, testString, 0);
    }

    @Test
    public void testTokens() throws Exception {
        super.checkForMismatch();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testStrings() {
        return Arrays.asList(new Object[][]{
                {"mFloat", "0xA"},
                {"mFloat", "0,1"},
                {"mFloat", "0.1ee1"},
                {"mFloat", "0.1EE1"},
                {"mFloat", ",1"},
                {"mFloat", ".1ee2"},
                {"mFloat", ".1EE2"},
        });
    }
}

