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

package ch.tsphp.tinsphp.parser.test.integration.parser;

import ch.tsphp.tinsphp.parser.antlr.TinsPHPParser;
import ch.tsphp.tinsphp.parser.test.integration.testutils.AParserParserExceptionTest;
import org.antlr.runtime.RecognitionException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class PHPValidButNotInTSPHPTest extends AParserParserExceptionTest
{

    public PHPValidButNotInTSPHPTest(String testString, int character, int position) {
        super(testString, character, position, RecognitionException.class);

    }

    @Test
    public void test() throws Exception {
        parseExpectingException();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testStrings() {
        return Arrays.asList(new Object[][]{
                //switch with multiple default blocks
                //TODO rstoll TINS-68 parser procedural - control structures
//            {"switch($a){case 1: default: ; break; case 2: default: ; break;}", TinsPHPParser.Default, 49},
//            {"$a <> $b", TinsPHPParser.GreaterThan, 4},
                //use outside of semicolon namespace
                {"use a\\a; namespace a;", TinsPHPParser.Namespace, 9},

        });
        //See TokenExceptionTest for misuse of keywords
    }
}
