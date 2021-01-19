/**
 * @title javaSilver / SE11Black / Chapter10 / 第10章 例外処理
 * @content
 *
 * @see 志賀澄人『徹底攻略 Java SE11 Silver 問題集[1ZO-815]』(黒本),インプレス, 2019
 * @author shika
 * @date 2021-01-15 / 09:05-09:22 (17分)
 * @correctRate ①52.94 ％ ( 〇9問 / 全17問 )
 * @date 2021-01-19 / 13:51-14:06 (14分)
 * @correctRate ②70.59 ％ ( 〇12問 / 全17問 )
 */
package javaSilver.SE11Black;

import javaSilver.AnswerMaker;

public class Chapter10 {

    public static void main(String[] args) {
        new AnswerMaker();
    }//main()

}//class

/*
//====== １回目 / 2021-01-15 ======
〇 1: B
〇 2: B
〇 3: E
× 4: B => 〇: D
    -> try-catch-finallyの順を変えて記述するとコンパイルエラー。
× 5: A => 〇: A
    -> catch{ }内に return;があっても、finally{ }は制御が戻る前に必ず実行される。
    -> finallyが実行されないのは、try/catch内で System.exit()でプログラムが終了したときか
       JVM, OSが クラッシュしたときだけ
× 6: A => 〇: B
    -> finallyは必ず実行されるので、同じ名前の変数に値がある場合は、上書きされる
〇 7: B
    -> 基本型: ローカル変数と戻り値の変数は別物。再代入しないと変更を反映できない。
    -> 参照型: 参照型は同じ参照を持っているので変更可能。
× 8: A => 〇: D
    -> try/finallyは、１つだけしか記述不可。コンパイルエラー。
〇 9: B
    -> 例外発生した場所から最も近い catch{ }が処理をする
× 10: B => 〇: E
    -> try-catch-resourceに finallyを記述可。
        例外時の処理順 ①リソースの解放、②catch、③finally
    -> try内で宣言した変数は、finallyで参照不可。コンパイルエラー
× 11: A => 〇: A, D
    -> RuntimeExceptionは、try-catch/throws句は不要。
× 12: C => 〇: D
    -> Errorは try-catchを記述しても良い。なくてもよい。
〇 13: C
〇 14: A
〇 15: D
× 16: B => 〇: D
    -> nullにメソッドを使おうとすると NullPointerException
    -> 設問の場合 if( str == null)なら機能する
    -> IllegalArgumentExceptionは、JVMが出すの出すのではなく、プログラム開発者の任意で出す例外。
〇 17: B
    -> StackOverflowError: 再帰呼び出しなどで stack領域(メモリ)が足りなくなるとthrow
    -> IllegalStateException: オブジェクトの異常な状態のまま利用したときにthrow。(Scanner.close()後など)
    -> ExceptionInInitializerError: static initializerで何らかの例外が発生したときにthrow。
開始時刻 09:05
終了時刻 09:22
所要時間 17 分

正答率 52.94 ％ ( 〇9問 / 全17問 )
*/
/*
//====== ２回目 / 2021-01-19 ======
〇 1: B
〇 2: B
× 3: A => 〇: E
    catchは、サブクラスから記述する。継承関係を明記してあるので、その設問意図を考えるべき。
× 4: B => 〇: D
    try-catch-finallyの順。
〇 5: C
〇 6: B?
〇 7: B
× 8: A => 〇: D
    finallyは１つだけしか記述できない。
〇 9: B
× 10: C => 〇: E
    try内のローカル変数は catch, finallyからは参照不可。
〇 11: A, D
〇 12: D
〇 13: C
〇 14: A
〇 15: D
× 16: B => 〇: D
    str = null;
    str.equals("")で NullPo -> str == nullならＯＫ。

〇 17: B

開始時刻 13:51
終了時刻 14:06
所要時間 14 分

正答率 70.59 ％ ( 〇12問 / 全17問 )
*/

