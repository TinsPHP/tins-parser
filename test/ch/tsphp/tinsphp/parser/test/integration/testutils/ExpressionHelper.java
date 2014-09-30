/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class ExpressionHelper from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.testutils;

import java.util.Arrays;
import java.util.List;

public class ExpressionHelper
{

    public static List<String[]> getAstExpressions() {
        return Arrays.asList(new String[][]{
                {"$a or $b", "(or $a $b)"},
                {"$a xor $b", "(xor $a $b)"},
                {"$a and $b", "(and $a $b)"},
                //precedence test
                //($a and $b) or ($c xor $d)
                {"$a and $b or $c xor $d", "(or (and $a $b) (xor $c $d))"},
                //($a or (($b and $c) xor $d)
                {"$a or $b and $c xor $d", "(or $a (xor (and $b $c) $d))"},
                //TODO rstoll TINS-108 - class, TINS-109 - interface
                /*
                {"$a = $b", "(= $a $b)"},
                {"$a += $b", "(= $a (+ $a $b))"},
                {"$a -= $b", "(= $a (- $a $b))"},
                {"$a *= $b", "(= $a (* $a $b))"},
                {"$a /= $b", "(= $a (/ $a $b))"},
                {"$a &= $b", "(= $a (& $a $b))"},
                {"$a |= $b", "(= $a (| $a $b))"},
                {"$a ^= $b", "(= $a (^ $a $b))"},
                {"$a %= $b", "(= $a (% $a $b))"},
                {"$a .= $b", "(= $a (. $a $b))"},
                {"$a <<= $b", "(= $a (<< $a $b))"},
                {"$a >>= $b", "(= $a (>> $a $b))"},
                {"$a =() $b", "(cAssign $a $b)"},
                //check that all have the same precedence
                {"$a = $b += $c -= $d *= $e", "(= $a (= $b (+ $b (= $c (- $c (= $d (* $d $e)))))))"},
                {"$e /= $f &= $g |= $h ^= $i", "(= $e (/ $e (= $f (& $f (= $g (| $g (= $h (^ $h $i))))))))"},
                {
                        "$i %= $j .= $k <<= $l >>= $m =() $n",
                        "(= $i (% $i (= $j (. $j (= $k (<< $k (= $l (>> $l (cAssign $m $n)))))))))"
                },
                //precedence tests
                //($a = $b) or $c
                {"$a = $b or $c", "(or (= $a $b) $c)"},
                //(($a += $b) and ($c -= $d)) xor $e
                {"$a += $b and $c -= $d xor $e", "(xor (and (= $a (+ $a $b)) (= $c (- $c $d))) $e)"},
                //($a =() $b) or (($c =() $d) and $e)
                {"$a =() $b or $c =() $d and $e", "(or (cAssign $a $b) (and (cAssign $c $d) $e))"},

                {"true ? $a : $b", "(? true $a $b)"},
                //precedence tests
                //($a and ($b = (false ? 1 or 2 : 3)) or 4
                {"$a and $b = false ? 1 or 2 : 3 or 4", "(or (and $a (= $b (? false (or 1 2) 3))) 4)"},
                //$a = (true ? $c += $d : $e) = $f
                {"$a = true ? $c += $d : $e = $f", "(= $a (= (? true (= $c (+ $c $d)) $e) $f))"},
                {
                        //($a *= true ? ($c /= ($d ? $e &= $f : $g) = $h) : $i) or $j
                        "$a *= true ? $c /= $d ? $e &= $f : $g = $h : $i or $j",
                        "(or (= $a (* $a (? true (= $c (/ $c (= (? $d (= $e (& $e $f)) $g) $h))) $i))) $j)",
                },

                {"$a || $b", "(|| $a $b)"},
                {"$a && $b", "(&& $a $b)"},
                //precedence tests
                //($a && $b) || $c
                {"$a && $b || $c", "(|| (&& $a $b) $c)"},
                //$a || ($b && $c)
                {"$a || $b && $c", "(|| $a (&& $b $c))"},
                //($a || $b) ? 1 : 2
                {"$a || $b ? 1 : 2", "(? (|| $a $b) 1 2)"},
                //($a || ($b && $c)) ? $d : $e
                {"$a || $b && $c ? $d : $e", "(? (|| $a (&& $b $c)) $d $e)"},

                {"$a | $b", "(| $a $b)"},
                {"$a ^ $b", "(^ $a $b)"},
                {"$a & $b", "(& $a $b)"},
                //precedence tests
                //($a & $b) | ($c ^ $d)
                {"$a & $b | $c ^ $d", "(| (& $a $b) (^ $c $d))"},
                //$a | (($b & $c) ^ $d)
                {"$a | $b & $c ^ $d", "(| $a (^ (& $b $c) $d))"},
                //($a | $b) && $c
                {"$a | $b && $c", "(&& (| $a $b) $c)"},
                //(($a | $b) && ($c ^ $d)) || ($e & $f)
                {"$a | $b && $c ^ $d || $e & $f", "(|| (&& (| $a $b) (^ $c $d)) (& $e $f))"},

                {"$a == $b", "(== $a $b)"},
                {"$a === $b", "(=== $a $b)"},
                {"$a != $b", "(!= $a $b)"},
                {"$a !== $b", "(!== $a $b)"},
                //precedence tests
                //($a == $b) & $c
                {"$a == $b & $c", "(& (== $a $b) $c)"},

                {"$a < $b", "(< $a $b)"},
                {"$a <= $b", "(<= $a $b)"},
                {"$a > $b", "(> $a $b)"},
                {"$a >= $b", "(>= $a $b)"},
                //precedence test
                {
                        //(($a == $b) | (($c < $d) & $e)) ? ($f != $g) : ($h === $i)
                        "$a == $b | $c < $d & $e ? $f != $g : $h === $i",
                        "(? (| (== $a $b) (& (< $c $d) $e)) (!= $f $g) (=== $h $i))"
                },

                {"1 << 2", "(<< 1 2)"},
                {"1 >> 2", "(>> 1 2)"},
                //check that both have the same precedence
                {"1 >> 2 << 3 >> 5", "(>> (<< (>> 1 2) 3) 5)"},
                {"1 << 2 << 3 >> 5", "(>> (<< (<< 1 2) 3) 5)"},
                //precedence test
                //$a < ($b << $c)
                {"$a < $b << $c", "(< $a (<< $b $c))"},
                //($a >> $b) >= ($c >> 2)
                {"$a >> $b >= $c >> 2", "(>= (>> $a $b) (>> $c 2))"},

                {"1 + 2", "(+ 1 2)"},
                {"1 - 2", "(- 1 2)"},
                {"$a . $b", "(. $a $b)"},
                //check that all have the same precedence
                {"$a + $b - $c . $d . $e + $f - $g", "(- (+ (. (. (- (+ $a $b) $c) $d) $e) $f) $g)"},
                //precedence test
                {"$a << $b >> $c + $d * $e - $f", "(>> (<< $a $b) (- (+ $c (* $d $e)) $f))"},

                {"$a * $b", "(* $a $b)"},
                {"$a / $b ", "(/ $a $b)"},
                {"$a % $b ", "(% $a $b)"},
                //check that all have the same precedence
                {"$a * $b / $c % $d * $e % $f / $g", "(/ (% (* (% (/ (* $a $b) $c) $d) $e) $f) $g)"},
                //precedence test
                //($a * $b) + $c
                {"$a * $b + $c", "(+ (* $a $b) $c)"},
                //$a + ($b * $c) - ($d % $f).($g / 2)
                {"$a + $b * $c - $d % $f.$g / 2", "(. (- (+ $a (* $b $c)) (% $d $f)) (/ $g 2))"},

                {"$a instanceof MyClass", "(instanceof $a MyClass)"},
                {"$a instanceof $b", "(instanceof $a $b)"},
                //precedence test
                //$a * ($b instanceof $c)
                {"$a * $b instanceof $c", "(* $a (instanceof $b $c))"},

                {"(bool) $a", "(casting (type tMod bool) $a)"},
                {"(int) $a", "(casting (type tMod int) $a)"},
                {"(float) $a", "(casting (type tMod float) $a)"},
                {"(string) $a", "(casting (type tMod string) $a)"},
                {"(array) $a", "(casting (type tMod array) $a)"},
                {"(resource) $a", "(casting (type tMod resource) $a)"},
                {"(Type) $a", "(casting (type tMod Type) $a)"},
                {"(cast Type) $a", "(casting (type (tMod cast) Type) $a)"},
                {"++$a", "(preIncr $a)"},
                {"--$a", "(preDecr $a)"},
                {"@$a", "(@ $a)"},
                {"~$a", "(~ $a)"},
                {"!$a", "(! $a)"},
                {"+$a", "(uPlus $a)"},
                {"+1", "(uPlus 1)"},
                {"-$a", "(uMinus $a)"},
                {"-2", "(uMinus 2)"},
                //precedence test
                {"(int) $a instanceof $b", "(instanceof (casting (type tMod int) $a) $b)"},
                {"++$a * $b ", "(* (preIncr $a) $b)"},
                {"--$a % $b ", "(% (preDecr $a) $b)"},
                {"-$a * $b", "(* (uMinus $a) $b)"},
                {"+$a instanceof $b", "(instanceof (uPlus $a) $b)"},
                {"@$a * $b", "(* (@ $a) $b)"},
                {"~$a / $b", "(/ (~ $a) $b)"},
                {"!$a % $b ", "(% (! $a) $b)"},

                {"clone $a", "(clone $a)"},
                {"new Type", "(new Type args)"},
                //precedence test
                //(clone $a) + $b
                {"clone $a + $b", "(+ (clone $a) $b)"},
                //(new A) + $b
                {"new A + $b", "(+ (new A args) $b)"},
                //((int) (clone $a)) * $b
                {"(int) clone $a * $b", "(* (casting (type tMod int) (clone $a)) $b)"},

                {"$a++", "(postIncr $a)"},
                {"$a--", "(postDecr $a)"},
                {"exit", "exit"},
                {"exit(1)", "(exit 1)"},
                {"$a", "$a"},
                {"$a[0]", "(arrAccess $a 0)"},
                {"$this", "$this"},
                {"$this->a","(fieAccess $this a)"},
                {"$this[0]","(arrAccess $this 0)"},
                {"$a->a", "(fieAccess $a a)"},
                {"\\foo(1,1+2,3)", "(fCall \\foo() (args 1 (+ 1 2) 3))"},
                {"foo()", "(fCall foo() args)"},
                {"self::foo()", "(mCall self foo() args)"},
                {"parent::foo()", "(mCall parent foo() args)"},
                {"Foo::foo()", "(smCall Foo foo() args)"},
                {"\\a\\foo()", "(fCall \\a\\foo() args)"},
                {"$a->foo()", "(mCall $a foo() args)"},
                {"$a->foo(true || false,123*9)", "(mCall $a foo() (args (|| true false) (* 123 9)))"},
                {"self::$a", "(sMemAccess self $a)"},
                {"parent::$a", "(sMemAccess parent $a)"},
                {"Bar::$a", "(sMemAccess Bar $a)"},
                {"self::a", "(sMemAccess self a#)"},
                {"parent::a", "(sMemAccess parent a#)"},
                {"A::a", "(sMemAccess A a#)"},
                {"\\A::a", "(sMemAccess \\A a#)"},
                {"a\\A::a", "(sMemAccess a\\A a#)"},
                */
                {"a", "a#"},
                {"a\\b", "a\\b#"},
                {"true", "true"},
                {"false", "false"},
                {"null", "null"},
                {"1", "1"},
                {"2.123", "2.123"},
                {"'a'", "'a'"},
                {"\"asdf\"", "\"asdf\""},
                {"[1,2,a=>3]", "(array 1 2 (=> a# 3))"},
                {"array(1,2,a=>3)", "(array 1 2 (=> a# 3))"},
                {"($a)", "$a"},
        });
    }

    public static String[] getParserExpressions() {
        return new String[]{
                "true or false",
                "true xor false",
                "true and false",
                "true or false xor true and false",
                //TODO rstoll TINS-108 - class, TINS-109 - interface
                /*
                "$b = 1",
                "$b += 1",
                "$b -= 1",
                "$b *= 1",
                "$b /= 1",
                "$b %= 1",
                "$b .= 1",
                "$b &= 1",
                "$b |= 1",
                "$b ^= 1",
                "$b <<= 1",
                "$b >>= 1",
                "$b >>= 1",
                "$b =() 1",
                "true ? 1:2",
                "true ? $a<$b ? 1:2:2",
                "true ? $a<$b ? 1:2:2+3-4",
                "true || false",
                "true && false",
                "true || false && true ? true:false",
                "14 | 2",
                "14 ^ 2",
                "14 & 2",
                "9 | 9 ^ 12 & 3",
                "$b==$c",
                "$b!=$c",
                "$b===$c",
                "$b!==$c",
                "$a == $b && $c != $c || $c === $d && $a !== $e",
                "$a < $b",
                "$a <= $b",
                "$a > $b",
                "$a >= $b",
                "$a < 4 && 2 <= 7 || 1 > 10 && 2 >= $d",
                "1 << 4",
                "16 >> 4",
                "$a << 2 >> 5",
                "1+1",
                "2-3",
                "'b'.'a'",
                "'hallo'.'welt'.\"blabla bla\".$a",
                "4*5",
                "6/7",
                "6%7",
                "6+7-5*5/(2+1)",
                "6 % 3 + 7-5*5/(2+1)",
                "$a instanceof Foo",
                "$a instanceof $a",
                "(bool) $a",
                "(int) $a",
                "(float) $a",
                "(string) $a",
                "(array) $a",
                "(Foo) $a",
                "(cast Foo) $a",
                "(int) ((bool) $a && $b) + 1",
                "++$a",
                "--$a",
                "@$a",
                "~$a",
                "~~$a",
                "!$a",
                "!!$a",
                "+1",
                "-1",
                "new a",
                "new a()",
                "clone $a",
                "clone $a->a",
                "clone $a[0]",
                "$a++",
                "$a--",
                "exit",
                "$a",
                "$a->a",
                "$a[0]",
                "$this",
                "$this->a",
                "$this[0]",
                "foo()->a",
                "$a[0]->a",
                "$a->foo()->a",
                "+foo()",
                "-foo()",
                "+$a->foo()",
                "-$a->foo()",
                "(1+1)",
                "foo()",
                "a\\foo()",
                "$a->foo()",
                "$a->a->foo()",
                "$a[0]->foo()",
                "$a[0][1]->foo()",
                "self::foo()",
                "self::$a->foo()",
                "parent::foo()",
                "parent::$a->foo()",
                "A::a",
                "\\A::a",
                "a\\A::a",
                */
                "a",
                "a\\a",
                "true",
                "false",
                "null",
                "1",
                "2.123",
                "'a'",
                "\"asdf\"",
                "[1,2,a=>3]",
                "array(1,2,a=>3)",
                "($a)",
        };
    }
}
