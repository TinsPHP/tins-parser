/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

package ch.tsphp.tinsphp.parser.test.unit.config;

import ch.tsphp.common.ITSPHPAstAdaptor;
import ch.tsphp.tinsphp.common.IParser;
import ch.tsphp.tinsphp.common.config.IParserInitialiser;
import ch.tsphp.tinsphp.parser.config.HardCodedParserInitialiser;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

public class HardCodedParserInitialiserTest
{

    @Test
    public void getParser_SecondCall_ReturnsSameInstanceAsFirstCall() {
        IParserInitialiser parserInitialiser = createParserInitialiser();
        IParser firstCall = parserInitialiser.getParser();

        IParser result = parserInitialiser.getParser();

        assertThat(result, is(firstCall));
    }

    @Test
    public void getParser_SecondCallAfterReset_ReturnsSameInstanceAsFirstCallBeforeReset() {
        IParserInitialiser parserInitialiser = createParserInitialiser();
        IParser firstCall = parserInitialiser.getParser();
        parserInitialiser.reset();

        IParser result = parserInitialiser.getParser();

        assertThat(result, is(firstCall));
    }

    private IParserInitialiser createParserInitialiser() {
        return createParserInitialiser(mock(ITSPHPAstAdaptor.class));
    }

    protected IParserInitialiser createParserInitialiser(ITSPHPAstAdaptor astAdaptor) {
        return new HardCodedParserInitialiser(astAdaptor);
    }

}
