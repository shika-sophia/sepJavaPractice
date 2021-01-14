/**
 * @title javaSilver / SE11Black / Chapter08 / 第８章 関数型インターフェイス、ラムダ式
 * @content ラムダ式, Predicate, Supplier, Function, Consumer
 *
 * @see 志賀澄人『徹底攻略 Java SE11 Silver 問題集[1ZO-815]』(黒本),インプレス, 2019
 * @author shika
 * @date 2021-01-14 / 14:21-14:33 (11分)
 * @correctRate ①87.50 ％ ( 〇7問 / 全8問 )
 */
package javaSilver.SE11Black;

import javaSilver.AnswerMaker;

public class Chapter08 {

    public static void main(String[] args) {
        new AnswerMaker();
    }//main()

}//class

/*
//====== １回目 / 2021-01-14 ======
〇 1: B, D
〇 2: B, C
  -> 1つの処理で { }を省略するとreturnは記述できない(記述するとコンパイルエラー)
  -> { }を付ける場合は return必要。(記述しないとコンパイルエラー)
〇 3: C
  -> ローカル変数のスコープ内にある同じ変数名はラムダ式で定義できない。(二重定義)
× 4: C => 〇: D
    ◆「実質的にfinalなローカル変数だけにアクセス可」
        -> finalで修飾されてなくても、変更されていない変数にのみラムダ式で扱える。
        -> ラムダ式でローカル変数を変更するとコンパイルエラー。
        -> ラムダ式の外で変更してもコンパイルエラー。
〇 5: A
〇 6: C
〇 7: B
〇 8: A

開始時刻 14:21
終了時刻 14:33
所要時間 11 分

正答率 87.50 ％ ( 〇7問 / 全8問 )
*/
