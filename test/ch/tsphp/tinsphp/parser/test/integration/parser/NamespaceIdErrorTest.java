/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

package ch.tsphp.tinsphp.parser.test.integration.parser;

import ch.tsphp.tinsphp.parser.antlr.TinsPHPParser;
import ch.tsphp.tinsphp.parser.test.integration.lexer.TokenTest;
import ch.tsphp.tinsphp.parser.test.integration.testutils.AParserParserExceptionTest;
import org.antlr.runtime.RecognitionException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class NamespaceIdErrorTest extends AParserParserExceptionTest
{

    public NamespaceIdErrorTest(String testString, int token, int position) {
        super(testString, token, position, RecognitionException.class);
    }

    @Test
    public void test() throws Exception {
        parseExpectingException();
    }

    @Override
    protected void run() throws RecognitionException {
        result = parser.namespaceId();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> vars() {
        List<Object[]> collection = new ArrayList<>();
        Collection<Object[]> idTestStrings = TokenTest.getIDTestStrings();
        for (Object[] obj : idTestStrings) {
            collection.add(new Object[]{"\\" + obj[1], TinsPHPParser.Backslash, 0});
            collection.add(new Object[]{"\\" + obj[1] + "\\" + obj[1], TinsPHPParser.Backslash, 0});
            collection.add(new Object[]{"\\" + obj[1] + "\\" + obj[1] + "\\" + obj[1], TinsPHPParser.Backslash, 0});
        }
        return collection;
    }
}
