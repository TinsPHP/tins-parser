/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class AParserExceptionTest from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */
package ch.tsphp.tinsphp.parser.test.integration.testutils;

import org.antlr.runtime.RecognitionException;
import org.junit.Ignore;

@Ignore
public abstract class AParserExceptionTest extends AParserTest
{
    protected final Class<? extends RecognitionException> exceptionType;
    protected final int token;
    protected final int position;

    public AParserExceptionTest(String testString, int aToken, int aPosition,
            Class<? extends RecognitionException> type) {
        super(testString);
        noErrorsOnOutput();
        exceptionType = type;
        token = aToken;
        position = aPosition;
    }
}
