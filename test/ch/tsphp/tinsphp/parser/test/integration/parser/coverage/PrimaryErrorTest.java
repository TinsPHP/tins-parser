/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This file is part of the TSPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.parser.coverage;

import ch.tsphp.tinsphp.parser.antlr.TinsPHPParser;
import ch.tsphp.tinsphp.parser.test.integration.testutils.AParserParserExceptionTest;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class PrimaryErrorTest extends AParserParserExceptionTest
{

    public PrimaryErrorTest(String testString, int character, int position) {
        super(testString, character, position, NoViableAltException.class);
    }

    @Test
    public void test() throws Exception {
        parseExpectingException();
    }

    protected void run() throws RecognitionException {
        result = parser.primary();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testStrings() {
        return Arrays.asList(new Object[][]{
                {"\\$a", TinsPHPParser.VariableId, 1},
                //TODO rstoll TINS-108 - class, TINS-109 - interface
//                {"A$a ", TinsPHPParser.VariableId, 1},
                //$this as such is valid for primary ->$a would cause a parser error in parent rule
                //{"$this->$a ", TinsPHPParser.VariableId, 7},
                //$a as such is valid for primary ->$a would cause a parser error in parent rule
                //{"$a->$a", TinsPHPParser.VariableId, 4},
                //TODO rstoll TINS-108 - class, TINS-109 - interface
//                {"self::\\", TinsPHPParser.Backslash, 6},
//                {"parent::\\", TinsPHPParser.Backslash, 8},
                {"return", TinsPHPParser.Return, 0},
        });
    }
}

