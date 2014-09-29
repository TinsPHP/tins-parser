/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class NamespaceSemicolonInstructionTest from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.parser.coverage;

import ch.tsphp.tinsphp.parser.test.integration.testutils.AParserTest;
import ch.tsphp.tinsphp.parser.test.integration.testutils.InstructionHelper;
import org.antlr.runtime.RecognitionException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class NamespaceSemicolonInstructionTest extends AParserTest
{

    public NamespaceSemicolonInstructionTest(String testString) {
        super(testString);
    }

    @Test
    public void test() throws Exception {
        parseAndCheckForExceptions();
    }

    protected void run() throws RecognitionException {
        result = parser.namespaceSemicolon();
    }


    @Parameterized.Parameters
    public static Collection<Object[]> testStrings() {
        List<Object[]> collection = new ArrayList<>();
        collection.addAll(InstructionHelper.getInstructions("namespace a; ", ""));
        return collection;
    }
}
