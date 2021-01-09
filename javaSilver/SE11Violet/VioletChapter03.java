/**
 * @title javaSilver / SE11Violet / VioletChapter03.java
 * @reference 山本道子『JavaSilver SE11 [1ZO-815]』翔泳社,2019 (紫本)
 * @content 第３章 演算子と分岐文
 * @author shika
 * @date 2021-01-08
 * @correctRate ①50.00 ％ ( 〇9問 / 全18問 )
 */
/*
〇 1: C ANS:912; ANS:102
〇 2: B 1 整数リテラル同士の計算は整数
× 3: C 4,5,6行目 => B char='a'はint[]に代入可。暗黙の型変換。
× 4: A => C / if(11.00f == 11) -> true; 型が違っても比較可。
× 5: C => B / 「||」の前項trueなら、後項の評価なし。
〇 6: E 99
〇 7: A,D,F / switch -> byte,char,short,int,LapperClass, enum, String
〇 8: E
× 9: B / B C => D case句がStringのため、コンパイルエラー
× 10: A => 「|」はビット演算子「1|2」-> 3。
〇 11: B
〇 12: C
× 13: A false true true
        => C false false true / intern()は文字列プール(new以外で生成された文字列)を参照。
× 14: B *xistence => str.replace('e','*'); -> String.strは不変。再代入していないため existence
〇 15: C / sb.delete(0, sb.length())
× 16: B / 8 => A / 9 delete(2, 5)は index 2 ～ index 4を削除。
〇 17: C / コンパイルエラー
× 18: B => A str.concat()の戻り値を受け取っていないため、String不変。

正答率 50.00 ％ ( 〇9問 / 全18問 )
*/

package javaSilver.SE11Violet;

import javaSilver.AnswerMaker;

public class VioletChapter03 {

    public static void main(String[] args) {
        new AnswerMaker();
    }//main()

}//class
