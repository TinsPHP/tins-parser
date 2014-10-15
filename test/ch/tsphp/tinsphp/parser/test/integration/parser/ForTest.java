/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class ForTest from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.parser;

import ch.tsphp.tinsphp.parser.test.integration.testutils.AParserTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ForTest extends AParserTest
{

    public ForTest(String testString) {
        super(testString);
    }

    @Test
    public void test() throws Exception {
        parseAndCheckForExceptions();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testStrings() {
        return Arrays.asList(new Object[][]{
                //for with init without assignment
                {"for($a;;){$a=1;}"},
                {"for($a=1,$b;;){$a=1;}"},
                {"for(;;$a){$a=1;}"},
                {"for(;;$a++,$b){$a=1;}"},
                //for with useless expressions
                {"for($a=1,1+1-2;;){$a=1;}"},
                {"for($a=1;;1+1-2){$a=1;}"},
                //
                {"for($a=1 ; true ; ++$i  ) $a=1;"},
                {"for(     ; true ; ++$i  ) $a=1;"},
                {"for(     ;      ; $i+=1 ) $a=1;"},
                {"for(     ; true ;       ) $a=1;"},
                {"for(     ;      ;       ) $a=1;"},
                {"for($a=1 ; true ;       ) $a=1;"},
                {"for($a=1 ;      ; ++$i  ) $a=1;"},
                {"for($a=1 ;      ;       ) $a=1;"},
                //block
                {"for($a=1 ; true ; ++$i  ) {$a=1;}"},
                {"for(     ; true ; ++$i  ) {$a=1;}"},
                {"for(     ;      ; $i+=1 ) {$a=1;}"},
                {"for(     ; true ;       ) {$a=1;}"},
                {"for(     ;      ;       ) {$a=1;}"},
                {"for($a=1 ; true ;       ) {$a=1;}"},
                {"for($a=1 ;      ; ++$i  ) {$a=1;}"},
                {"for($a=1 ;      ;       ) {$a=1;}"},
                //comma
                {"for($a=1,$b=1      ; $a < 1         ; ++$a ) {$a=1;}"},
                {"for($a=1,$b=1,$c=3 ; $a=1,$a < 1    ; ++$a ) {$a=1;}"},
                {"for($a=1,$b=1      ; ++$a,$a,$a < 1 ; ++$a,$b-- ) {$a=1;}"},
                {"for($a=1           ; ++$a,$a < 1    ; $a++,$b+=1,$c/=3) {$a=1;}"},
                {"for($a=1           ; ++$a,$a < 1    ; ++$a,$b-- ) {$a=1;}"},
                {"for($a=1           ; ++$a,$a < 1    ; ++$a,$b-- ) {$a=1;}"},
                {"for($a=1,$b=1      ; $a < 1         ; ++$a ) {$a=1;}"}
        });
    }
}
