/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class PHPValidButNotInTSPHPTest from the TSPHP project.
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
public class PHPValidButNotInTinsPHPTest extends ALexerTest
{

    public PHPValidButNotInTinsPHPTest(String methodName, String testString) {
        super(methodName, testString, 0);
    }

    @Test
    public void testTokens() throws Exception {
        super.checkForMismatch();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testStrings() {
        return Arrays.asList(new Object[][]{
                    // vars in quoted strings are not allowed in TSPHP - use ".$a." instead
                    {"mSTRING_DOUBLE_QUOTED", "\"$a\""},
                    // single $ are allowed in PHP but not in TSPHP
                    {"mSTRING_DOUBLE_QUOTED", "\"10 $\""},
                    {"mSTRING_DOUBLE_QUOTED", "\" $ \""},
                    {"mSTRING_DOUBLE_QUOTED", "\" \\$$ \""},
                    {"mSTRING_DOUBLE_QUOTED", "\" \\\\$$ \""},
                    // $0 cannot be a variable and is therefore allowed in PHP, but not in TSPHP
                    {"mSTRING_DOUBLE_QUOTED", "\" $0123456789 \""},
                });
    }
}
