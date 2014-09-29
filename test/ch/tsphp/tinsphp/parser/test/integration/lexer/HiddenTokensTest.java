/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class HiddenTokensTest from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.lexer;

import ch.tsphp.tinsphp.parser.antlr.TinsPHPLexer;
import ch.tsphp.tinsphp.parser.test.integration.testutils.ALexerTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class HiddenTokensTest extends ALexerTest
{

    public HiddenTokensTest(String methodName, String testString, int type) {
        super(methodName, testString, type, TinsPHPLexer.HIDDEN);
    }

    @Test
    public void testTokens() throws Exception {
        analyseAndCheckForError();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testStrings() {
        return Arrays.asList(new Object[][]{
                {"mComment", "//bla", TinsPHPLexer.Comment},
                {"mComment", "// bla \t !", TinsPHPLexer.Comment},
                {"mComment", "// bla \r\n", TinsPHPLexer.Comment},
                {"mComment", "// bla \n", TinsPHPLexer.Comment},
                {"mComment", "#bla", TinsPHPLexer.Comment},
                {"mComment", "# bla \t !", TinsPHPLexer.Comment},
                {"mComment", "# bla \r\n", TinsPHPLexer.Comment},
                {"mComment", "# bla \n", TinsPHPLexer.Comment},
                {"mComment", "/* \n a \n bla bla */", TinsPHPLexer.Comment},
                {"mComment", "/** \n* a \n* bla bla \t **/", TinsPHPLexer.Comment},
                {"mWhitespace", " ", TinsPHPLexer.Whitespace},
                {"mWhitespace", "\n", TinsPHPLexer.Whitespace},
                {"mWhitespace", "\t", TinsPHPLexer.Whitespace},
                {"mWhitespace", "\r", TinsPHPLexer.Whitespace},
        });
    }
}
