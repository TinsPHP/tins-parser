/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

package ch.tsphp.tinsphp.parser.config;

import ch.tsphp.common.ITSPHPAstAdaptor;
import ch.tsphp.tinsphp.common.IParser;
import ch.tsphp.tinsphp.common.config.IParserInitialiser;
import ch.tsphp.tinsphp.parser.ParserFacade;

public class HardCodedParserInitialiser implements IParserInitialiser
{
    private IParser parser;

    public HardCodedParserInitialiser(ITSPHPAstAdaptor anAstAdaptor) {
        parser = new ParserFacade(anAstAdaptor);
    }

    @Override
    public IParser getParser() {
        return parser;
    }

    @Override
    public void reset() {
        parser.reset();
    }
}
