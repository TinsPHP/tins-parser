/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class NamespaceErrorTest from the TSPHP project.
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
public class NamespaceErrorTest extends AParserParserExceptionTest
{

    public NamespaceErrorTest(String testString, int token, int position) {
        super(testString, token, position, RecognitionException.class);

    }

    @Test
    public void test() throws Exception {
        parseExpectingException();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> vars() {
        return Arrays.asList(new Object[][]{
                //namespace afterwards
                {"<?php $a; namespace a; ?>", TinsPHPParser.Namespace, 4},
                //namespace semicolon mixed with curly brace namespace
                {"<?php namespace a; $a=1; namespace b{$a=1;} ?>", TinsPHPParser.LeftCurlyBrace, 30},
                {"<?php namespace a{ $a=1;} namespace b; $a=1; ?>", TinsPHPParser.Semicolon, 31},
                //statements outside of curly brace namespace
                {"<?php namespace a{ $a=1;} $a=1; namespace b{ $a=1;} ?>", TinsPHPParser.VariableId, 20},

        });
    }
}
