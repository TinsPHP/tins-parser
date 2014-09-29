/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class NamespaceTokenTest from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.ast;


import ch.tsphp.tinsphp.parser.test.integration.testutils.AAstTokenTest;
import ch.tsphp.tinsphp.parser.test.integration.testutils.AstHelper;
import org.antlr.runtime.RecognitionException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static ch.tsphp.tinsphp.parser.antlr.TinsPHPParser.DEFAULT_NAMESPACE;
import static ch.tsphp.tinsphp.parser.antlr.TinsPHPParser.EXPRESSION;
import static ch.tsphp.tinsphp.parser.antlr.TinsPHPParser.NAMESPACE_BODY;
import static ch.tsphp.tinsphp.parser.antlr.TinsPHPParser.Namespace;
import static ch.tsphp.tinsphp.parser.antlr.TinsPHPParser.TYPE_NAME;

@RunWith(Parameterized.class)
public class NamespaceTokenTest extends AAstTokenTest
{

    public NamespaceTokenTest(String testString, List<Integer> expectedTokens) {
        super(testString, expectedTokens);
    }

    @Test
    public void test() throws Exception {
        compareAst();
    }

    @Override
    protected void run() throws RecognitionException {
        result = parser.compilationUnit();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testStrings() {
        return Arrays.asList(new Object[][]{
                {"namespace a; ", Arrays.asList(AstHelper.DOWN, Namespace, TYPE_NAME, NAMESPACE_BODY, AstHelper.UP)},
                {"namespace a {}", Arrays.asList(AstHelper.DOWN, Namespace, TYPE_NAME, NAMESPACE_BODY, AstHelper.UP)},
                //default
                {
                        "namespace{}",
                        Arrays.asList(AstHelper.DOWN, Namespace, DEFAULT_NAMESPACE, NAMESPACE_BODY, AstHelper.UP)
                },
                //without namespace
                {
                        ";",
                        Arrays.asList(AstHelper.DOWN, Namespace, DEFAULT_NAMESPACE, AstHelper.DOWN, NAMESPACE_BODY,
                                EXPRESSION,
                                AstHelper.UP, AstHelper.UP)
                },});
    }
}
