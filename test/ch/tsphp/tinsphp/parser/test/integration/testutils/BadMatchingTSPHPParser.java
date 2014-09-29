/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class BadMatchingTSPHPParser from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.testutils;

import org.antlr.runtime.BitSet;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;

/**
 * Overwrite reportError methods in order that unit tests with parser errors fails at the end.
 */
public class BadMatchingTSPHPParser extends TestTinsPHPParser
{
    private int count = 0;
    protected int numberOfMatchesBeforeFail;

    public BadMatchingTSPHPParser(TokenStream input, int theNumberOfMatchesBeforeFail) {
        super(input);
        numberOfMatchesBeforeFail = theNumberOfMatchesBeforeFail;
    }

    @Override
    public Object match(IntStream input, int ttype, BitSet follow) throws RecognitionException {
        Object result = super.match(input,ttype,follow);
        if (count >= numberOfMatchesBeforeFail) {
            state.failed = true;
        }
        ++count;
        return result;
    }

    public int getCount() {
        return count;
    }
}
