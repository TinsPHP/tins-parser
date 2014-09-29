/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class ANTLRNoCaseFileStream from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.antlrmod;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CharStream;

import java.io.IOException;

/**
 * An ANTLRFileStream which overrides the LA method in order to have case insensitive tokens.
 */
@SuppressWarnings("checkstyle:abstractclassname")
public class ANTLRNoCaseFileStream extends ANTLRFileStream
{

    public ANTLRNoCaseFileStream(final String fileName) throws IOException {
        super(fileName);
    }

    public ANTLRNoCaseFileStream(final String fileName, final String encoding) throws IOException {
        super(fileName, encoding);
    }

    @Override
    @SuppressWarnings({ "PMD.MethodNamingConventions", "PMD.ShortMethodName", "checkstyle:methodname" })
    public int LA(final int lookAhead) {
        int token = super.LA(lookAhead);
        if (token != 0 && token != CharStream.EOF) {
            token = Character.toLowerCase(token);
        }
        return token;
    }
}
