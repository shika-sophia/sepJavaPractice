/**
 * @title javaSilver / SE11Violet / VioletChapter05.java
 * @reference 山本道子『JavaSilver SE11 [1ZO-815]』翔泳社,2019 (紫本)
 * @content 第５章 クラス, インスタンス
 * @author shika
 * @date 2021-01-08
 * @correctRate ①77.78 ％ ( 〇14問 / 全18問 )
 */
package javaSilver.SE11Violet;

import javaSilver.AnswerMaker;

public class VioletChapter05 {

    public static void main(String[] args) {
        new AnswerMaker();
    }//main()

}//class

/*
〇 1: B / class Test2{ }
× 2: A / finalは初期値の宣言必須 => 〇: C コンストラクタで初期化してたわ
〇 3: A / 5 11
〇 4: A / 10
〇 5: C / 40
〇 6: B, D, E / data2はstaticでそのまま参照可。num1はnewが必要。
〇 7: A, C, D, F
〇 8: A, B
× 9: C / 5行目 staticから非staticメソッドは参照できません
     => 〇: C, D, H
〇 10: A / Goo : Goo
× 11: D / false false => 〇: C / false true
〇 12: B, C
〇 13: E / 1. 500 3. 500 2. 100
〇 14: E / 10 9 10 8
〇 15: E / 7 6 7 8 / staticフィールドは各インスタンス共通で1つ
〇 16: A / 3行目をstatic
× 17: C / 7行目 s = new String()の時点で前のインスタンスは不要になる。
    => 〇: D フィールドに前のインスタンス残ってたわ。nullを代入したときが正解
〇 18: C, D

正答率 77.78 ％ ( 〇14問 / 全18問 )
*/

