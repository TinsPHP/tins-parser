/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class EmptyStatementTest from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.ast;

import ch.tsphp.tinsphp.parser.test.integration.testutils.AAstTest;
import org.antlr.runtime.RecognitionException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class NamespaceTest extends AAstTest
{

    public NamespaceTest(String testString, String expectedResult) {
        super(testString, expectedResult);
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
                {"<?php namespace a; \n ; ?>", "(namespace \\a\\ (nBody expr))"},
                {"<?php namespace a { \n ;} ?>", "(namespace \\a\\ (nBody expr))"},
                {"<?php namespace a; ; namespace b; ; ?>", "(namespace \\a\\ (nBody expr)) (namespace \\b\\ (nBody " +
                        "expr))"},
                {"<?php namespace a {;} namespace b{;} ?>", "(namespace \\a\\ (nBody expr)) (namespace \\b\\ (nBody " +
                        "expr))"},
                {"<?php namespace a\\b; \n ; ; ?>", "(namespace \\a\\b\\ (nBody expr expr))"},
                {"<?php namespace a\\b\\c { \n ;} ?>", "(namespace \\a\\b\\c\\ (nBody expr))"},
                {
                        "<?php namespace a\\b\\c; ; namespace d\\e; ; ?>",
                        "(namespace \\a\\b\\c\\ (nBody expr)) (namespace \\d\\e\\ (nBody expr))"
                },
                {
                        "<?php namespace a\\b {;} namespace c\\d\\e{;} ?>",
                        "(namespace \\a\\b\\ (nBody expr)) (namespace \\c\\d\\e\\ (nBody expr))"
                },
                //default
                {"<?php namespace{ ; } ?>", "(namespace \\ (nBody expr))"},
                //without namespace
                {"<?php ; ?>", "(namespace \\ (nBody expr))"},
                //without namespace, without statement
                {"<?php ?>", "(namespace \\ nBody)"},
                //without ?> at the end
                {"<?php ;", "(namespace \\ (nBody expr))"},
        });
    }
}
