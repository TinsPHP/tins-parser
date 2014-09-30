/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class ClassInterfaceTypeWithoutMixedTest from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.parser;

import ch.tsphp.tinsphp.parser.test.integration.lexer.TokenTest;
import ch.tsphp.tinsphp.parser.test.integration.testutils.AParserTest;
import org.antlr.runtime.RecognitionException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class NamespaceIdTest extends AParserTest
{

    public NamespaceIdTest(String testString) {
        super(testString);
    }

    @Test
    public void test() throws Exception {
        parseAndCheckForExceptions();
    }

    @Override
    protected void run() throws RecognitionException {
        result = parser.namespaceId();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testStrings() {
        List<Object[]> collection = new ArrayList<>();
        Collection<Object[]> idTestStrings = TokenTest.getIDTestStrings();
        for (Object[] obj : idTestStrings) {
            collection.add(new String[]{obj[1] + ""});
            collection.add(new String[]{obj[1] + "\\" + obj[1]});
            collection.add(new String[]{obj[1] + "\\" + obj[1] + "\\" + obj[1]});
        }
        return collection;
    }
}
