/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

package ch.tsphp.tinsphp.parser.test.integration;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class TokensFileIntegrityTest
{
    @Test
    public void testTinsPHPsTokensFileIsSameASTSPHPs() throws IOException {
        Set<String> exceptions = new HashSet<>();
        exceptions.add("'\\\\'");

        String path = "src/ch/tsphp/tinsphp/parser/antlr/";
        BufferedReader bufferedReaderTinsPHP = null;
        BufferedReader bufferedReaderTSPHP = null;
        try {
            bufferedReaderTinsPHP = new BufferedReader(new FileReader(new File(path + "TinsPHP.tokens")));
            bufferedReaderTSPHP = new BufferedReader(new FileReader(new File(path + "TSPHP.tokens")));
            int count = 0;
            String lineTinsPHP;
            String lineTSPHP;
            while ((lineTinsPHP = bufferedReaderTinsPHP.readLine()) != null) {
                lineTSPHP = bufferedReaderTSPHP.readLine();
                if (lineTSPHP == null) {
                    Assert.fail("TSPHP tokens file ended sooner at line " + count);
                }
                String token = lineTinsPHP.split("=")[0];
                if (!exceptions.contains(token)) {
                    if (!lineTinsPHP.equals(lineTSPHP)) {
                        Assert.fail("line number " + count + " was different."
                                + "\nTinsPHP: " + lineTinsPHP
                                + "\nTSPHP  : " + lineTSPHP);
                    }
                }
                ++count;
            }
            if (bufferedReaderTSPHP.readLine() != null) {
                Assert.fail("TSPHP tokens file still had content at line " + count);
            }
        } finally {
            if (bufferedReaderTinsPHP != null) {
                bufferedReaderTinsPHP.close();
            }
            if (bufferedReaderTSPHP != null) {
                bufferedReaderTSPHP.close();
            }
        }
    }
}
