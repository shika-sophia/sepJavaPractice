/**
 * @title javaSilver / SE11Violet / VioletChapter07.java
 * @reference 山本道子『JavaSilver SE11 [1Z0-815]』翔泳社,2019 (紫本)
 * @content 第７章 例外処理
 * @author shika
 * @date 2021-01-11
 * @correctRate ①56.25 ％ ( 〇9問 / 全16問 )
 */
package javaSilver.SE11Violet;

import javaSilver.AnswerMaker;

public class VioletChapter07 {

    public static void main(String[] args) {
        new AnswerMaker();
    }//main()

}//class

/*
〇 1: B, C
〇 2: A, D, E
× 3: B, F => 〇: A, D 成功時と失敗時の２通り(初歩的なミス)
〇 4: B, E
〇 5: D
× 6: B, E => 〇: C,D 問３同様の初歩的なミス
× 7: G / NumberFormatExcption => 〇: F iの変数スコープここで聞くか
〇 8: D / ArrayIndexOutOfBoundsException
〇 9: E
〇 10: A
× 11: C => 〇: A method()の下の"try"は要らんよな(初歩的なミス)
× 12: A => 〇: E Integerのdefault値は null
〇 13: E
〇 14: G
× 15: C => 〇: D NullPoは runtime, IOExceptionは checked
× 16: D /  => 〇: E bar()を囲むtry-catchがなくそこで強制終了。ただしfinallyは実行

正答率 56.25 ％ ( 〇9問 / 全16問 )
*/
