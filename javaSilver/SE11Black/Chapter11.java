/**
 * @title javaSilver / SE11Black / Chapter11 / 第11章 モジュール
 * @content
 * @see 志賀澄人『徹底攻略 Java SE11 Silver 問題集[1ZO-815]』(黒本),インプレス, 2019
 * @author shika
 * @date 2021-01-15 / 09:28-09:34 (5分)
 * @correctRate ①42.86 ％ ( 〇3問 / 全7問 )
 * @date 2021-01-19 / 14:31-14:35 (4分)
 * @correctRate ②85.71 ％ ( 〇6問 / 全7問 )
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
/*
//====== ２回目 / 2021-01-19 ======
〇 1: A, B
〇 2: D
〇 3: D
〇 4: D
〇 5: B
× 6: C, D => 〇: A, C
    java --describe-module / module-info.javaの設定内容を表示
    jmod describe / module-info.javaの設定内容を表示
    jdeps --list-deps / クラス,メソッド, jar, モジュールの依存関係を調べる
    java --show-module-resolution / プログラム実行時に依存するモジュールがどのように探されているかを表示
〇 7: A
    「javac --add-exports モジュール / パッケージ名」 一時的に公開するパッケージを追加

開始時刻 14:31
終了時刻 14:35
所要時間 4 分

正答率 85.71 ％ ( 〇6問 / 全7問 )
*/