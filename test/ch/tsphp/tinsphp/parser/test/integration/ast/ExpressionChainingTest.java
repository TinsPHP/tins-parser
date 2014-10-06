/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class ExpressionChainingTest from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.ast;

import ch.tsphp.tinsphp.parser.test.integration.testutils.AAstTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ExpressionChainingTest extends AAstTest
{

    public ExpressionChainingTest(String testString, String expectedResult) {
        super(testString + ";", "(expr " + expectedResult + ")");
    }

    @Test
    public void test() throws Exception {
        compareAst();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testStrings() {
        return Arrays.asList(new Object[][]{
                {"$a or $b or $c", "(or (or $a $b) $c)"},
                {"$a or $b or $c or $d", "(or (or (or $a $b) $c) $d)"},
                {"$a xor $b xor $c", "(xor (xor $a $b) $c)"},
                {"$a xor $b xor $c xor $d", "(xor (xor (xor $a $b) $c) $d)"},
                {"$a and $b and $c", "(and (and $a $b) $c)"},
                {"$a and $b and $c and $d", "(and (and (and $a $b) $c) $d)"},

                {"$a = $b = $c", "(= $a (= $b $c))"},
                {"$a = $b = $c = $d", "(= $a (= $b (= $c $d)))"},
                {"$a += $b += $c", "(+= $a (+= $b $c))"},
                {"$a -= $b -= $c", "(-= $a (-= $b $c))"},
                {"$a *= $b *= $c", "(*= $a (*= $b $c))"},
                {"$a /= $b /= $c", "(/= $a (/= $b $c))"},
                {"$a &= $b &= $c", "(&= $a (&= $b $c))"},
                {"$a ^= $b ^= $c", "(^= $a (^= $b $c))"},
                {"$a %= $b %= $c", "(%= $a (%= $b $c))"},
                {"$a .= $b .= $c", "(.= $a (.= $b $c))"},
                {"$a |= $b |= $c", "(|= $a (|= $b $c))"},
                {"$a <<= $b <<= $c", "(<<= $a (<<= $b $c))"},
                {"$a >>= $b >>= $c", "(>>= $a (>>= $b $c))"},
                {"$a += $b += $c += $d", "(+= $a (+= $b (+= $c $d)))"},
                {"$a -= $b -= $c -= $d", "(-= $a (-= $b (-= $c $d)))"},
                {"$a *= $b *= $c *= $d", "(*= $a (*= $b (*= $c $d)))"},
                {"$a /= $b /= $c /= $d", "(/= $a (/= $b (/= $c $d)))"},
                {"$a &= $b &= $c &= $d", "(&= $a (&= $b (&= $c $d)))"},
                {"$a ^= $b ^= $c ^= $d", "(^= $a (^= $b (^= $c $d)))"},
                {"$a %= $b %= $c %= $d", "(%= $a (%= $b (%= $c $d)))"},
                {"$a .= $b .= $c .= $d", "(.= $a (.= $b (.= $c $d)))"},
                {"$a |= $b |= $c |= $d", "(|= $a (|= $b (|= $c $d)))"},
                {"$a <<= $b <<= $c <<= $d", "(<<= $a (<<= $b (<<= $c $d)))"},
                {"$a >>= $b >>= $c >>= $d", "(>>= $a (>>= $b (>>= $c $d)))"},
                //chaining up simple and compound assignments
                {
                        "$a = $b = $c += $d -= $e = $f *= $g",
                        "(= $a (= $b (+= $c (-= $d (= $e (*= $f $g))))))"
                },
                {
                        "$a *= $b = $c += $d = $e -= $f ^= "
                                + "$g >>= $h <<= $i &= $j |= $k .= $l = "
                                + "$m &= $n %= $o -= $p = $q /= $r = $t",
                        "(*= $a (= $b (+= $c (= $d (-= $e (^= $f "
                                + "(>>= $g (<<= $h (&= $i (|= $j (.= $k (= $l "
                                + "(&= $m (%= $n (-= $o (= $p (/= $q (= $r $t"
                                + "))))))"
                                + "))))))"
                                + "))))))"
                },

                //ternary is left associative - different than TSPHP
                {"true ? $a ? $b : $c : $d", "(? true (? $a $b $c) $d)"},
                {"true ? $a : $b ? $c : $d", "(? (? true $a $b) $c $d)"},
                {"true ? $a ? $b ? $c : $d : $e : $f", "(? true (? $a (? $b $c $d) $e) $f)"},
                {"true ? $a ? $b : $c ? $d : $e : $f", "(? true (? (? $a $b $c) $d $e) $f)"},
                {"true ? $a : $b ? $c ? $d : $e : $f", "(? (? true $a $b) (? $c $d $e) $f)"},
                {"true ? $a : $b ? $c : $d ? $e : $f", "(? (? (? true $a $b) $c $d) $e $f)"},

                {"$a || $b || $c", "(|| (|| $a $b) $c)"},
                {"$a && $b && $c", "(&& (&& $a $b) $c)"},
                {"$a || $b || $c || $d", "(|| (|| (|| $a $b) $c) $d)"},
                {"$a && $b && $c && $d", "(&& (&& (&& $a $b) $c) $d)"},
                {"$a | $b | $c", "(| (| $a $b) $c)"},
                {"$a ^ $b ^ $c", "(^ (^ $a $b) $c)"},
                {"$a & $b & $c", "(& (& $a $b) $c)"},
                {"$a | $b | $c | $d", "(| (| (| $a $b) $c) $d)"},
                {"$a ^ $b ^ $c ^ $d", "(^ (^ (^ $a $b) $c) $d)"},
                {"$a & $b & $c & $d", "(& (& (& $a $b) $c) $d)"},

                //cannot chain equality and comparison operators, hence there are missing here

                {"1 << 2 << 3", "(<< (<< 1 2) 3)"},
                {"1 >> 2 >> 3", "(>> (>> 1 2) 3)"},
                {"1 << 2 << 3 << 4", "(<< (<< (<< 1 2) 3) 4)"},
                {"1 >> 2 >> 3 >> 4", "(>> (>> (>> 1 2) 3) 4)"},

                {"$a + $b + $c", "(+ (+ $a $b) $c)"},
                {"$a - $b - $c", "(- (- $a $b) $c)"},
                {"$a . $b . $c", "(. (. $a $b) $c)"},
                {"$a + $b + $c + $d", "(+ (+ (+ $a $b) $c) $d)"},
                {"$a - $b - $c - $d", "(- (- (- $a $b) $c) $d)"},
                {"$a . $b . $c . $d", "(. (. (. $a $b) $c) $d)"},

                {"$a * $b * $c", "(* (* $a $b) $c)"},
                {"$a / $b / $c", "(/ (/ $a $b) $c)"},
                {"$a % $b % $c", "(% (% $a $b) $c)"},
                {"$a * $b * $c * $d", "(* (* (* $a $b) $c) $d)"},
                {"$a / $b / $c / $d", "(/ (/ (/ $a $b) $c) $d)"},
                {"$a % $b % $c % $d", "(% (% (% $a $b) $c) $d)"},


                //cannot chain up instanceof operator, thus it is missing here

                {"(int) (float) $a", "(casting (type tMod int) (casting (type tMod float) $a))"},
                {
                        "(int) (string) (array) $a",
                        "(casting (type tMod int) (casting (type tMod string) (casting (type tMod array) $a)))"
                },
                {"@@$a", "(@ (@ $a))"},
                {"~~$a", "(~ (~ $a))"},
                {"!!$a", "(! (! $a))"},
                {"@@@$a", "(@ (@ (@ $a)))"},
                {"~~~$a", "(~ (~ (~ $a)))"},
                {"!!!$a", "(! (! (! $a)))"},
                {"+ +$a", "(uPlus (uPlus $a))"},
                {"- -$a", "(uMinus (uMinus $a))"},
                {"+ + +$a", "(uPlus (uPlus (uPlus $a)))"},
                {"- - -$a", "(uMinus (uMinus (uMinus $a)))"},
                //chaining up different operators
                {"- ~ - ~ $a", "(uMinus (~ (uMinus (~ $a))))"},
                {"(bool) @ ~ ! + -$a", "(casting (type tMod bool) (@ (~ (! (uPlus (uMinus $a))))))"},
                {
                        "+ ~ (int) ! - @ (double) @ ~ ! + -$a",
                        "(uPlus (~ (casting (type tMod int) (! (uMinus (@ "
                                + "(casting (type tMod float) (@ (~ (! (uPlus (uMinus $a))))))))))))"
                },

                {"clone clone $a", "(clone (clone $a))"},
                {"clone clone clone $a", "(clone (clone (clone $a)))"},

                //TODO rstoll TINS-108 - class, TINS-109 - interface
//                {"$a->a->b", "(fieAccess (fieAccess $a a) b)"},
//                {"$a->a->b->c", "(fieAccess (fieAccess (fieAccess $a a) b) c)"},
//                {"$a->a()->b()", "(mpCall (mCall $a a() args) b() args)"},
//                {"$a->a()->b()->c()", "(mpCall (mpCall (mCall $a a() args) b() args) c() args)"},
                {"$a[0][1]", "(arrAccess (arrAccess $a 0) 1)"},
                {"$a[0][1][2]", "(arrAccess (arrAccess (arrAccess $a 0) 1) 2)"},
        });
    }
}

