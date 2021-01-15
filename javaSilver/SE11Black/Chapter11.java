/**
 * @title javaSilver / SE11Black / Chapter11 / 第11章 モジュール
 * @content
 * @see 志賀澄人『徹底攻略 Java SE11 Silver 問題集[1ZO-815]』(黒本),インプレス, 2019
 * @author shika
 * @date 2021-01-15 / 09:28-09:34 (5分)
 * @correctRate ①42.86 ％ ( 〇3問 / 全7問 )
 */
package javaSilver.SE11Black;

import javaSilver.AnswerMaker;

public class Chapter11 {

    public static void main(String[] args) {
        new AnswerMaker();
    }//main()

}//class

/*
//====== １回目 / 2021-01-15 ======
〇 1: A, B
× 2: E => 〇: D
    -> --module-pathで指定するルートディレクトリは、モジュールより１つ上のディレクトリ
    -> -m(-module)で指定するモジュール名/クラスの完全修飾名は「/」で区切る
× 3: B => 〇: D
    -> exports, requiresは、「s」付き
    -> 複数ファイルのコンパイルは「/」で区切る
× 4: B => 〇: D
    -> requiers transitive module3;
    -> module1でmodule3の記述をしても使えるようにはならない
    -> module2のmodule-info.javaで上記のようなtransitiveを付ける
    -> transitive(推移的): 自分が必要とするモジュールを、自分を必要とするモジュールにも利用させる
〇 5: B
× 6: D => 〇: A, C
〇 7: A

開始時刻 09:28
終了時刻 09:34
所要時間 5 分

正答率 42.86 ％ ( 〇3問 / 全7問 )
*/