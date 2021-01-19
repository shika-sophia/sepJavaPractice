/**
 * @title javaSilver / javaSE11Black / Chapter03 / 第３章 演算子と判定構造
 * @content 演算子, equals() 同一性・同値性, intern(), if, switch
 * @see 志賀澄人『徹底攻略 Java SE11 Silver 問題集[1ZO-815]』(黒本),インプレス, 2019
 * @date 2020-09-13 / 1300-1500, 1700-1900
 * @date 2021-01-12 / 1437-1505
 * @correctRate ① 12.6 / 21 = 60.0％
 * @correctRate ② 76.19 ％ ( 〇16問 / 全21問 )
 */

package javaSilver.SE11Black;

import javaSilver.AnswerMaker;

public class Chapter03 {
    public static void main(String[] args) {
        new AnswerMaker();
    }//main()

}//class
/*
 * ====== 1回目 / 2020-09-13 ======
 * 〇1. C 16
 * 〇2. A 100
 * 3. 〇A byte a = 0b10000000; => ちょうど128 (= 2 ^ 7乗)、byteは-128～127
 *    ×B short b = 128 + 128; => int同士の計算はint => 256はshortの範囲内なのでＯＫ。
 *    〇C int c = 2 * 3L; => long型はintに格納できません
 *    => 〇D 10.0はdefaultで double型となるためfloatに代入不可。
 *
 * 〇4. B 32?
 * ◆インクリメント, デクリメント
 * ・前置 ++a: +1した演算結果を代入
 * ・後置 a++: 演算前の値を代入し、a だけ +1。
 * b = a++ + a + a-- - a-- +   ++a;
 * b = 10 + 11 + 11  - 10      + 10 = 32
 *    (a+1=11)(a-1=10)(a-1=9)(a+1=10)
 *
 * 〇5. C コンパイルエラー
 *          => 「<」「>」「<=」「>=」は数値の比較のみ
 *
 * ｘ6. B 21 => 〇A 20
 * ◆「&」「&&」の違い
 * 「&&」ショートサーキット演算子 => 「右 && 左」右がfalseなら左は判定しない。
 * if (a < 10 && 10 < ++b) => 右がfalseで ++bは判定しないので b==10のまま。
 *
 * 〇7. A 5
 * 〇8. B false
 * ◆同一性・同値性
 * 同一: 同じインスタンスであること (a == b)
 * 同値: インスタンスは異なるが、同じ値であること (a equals( b ))
 *         ※ただしObjectクラスのdefaultは同一性の判定になっているので
 *           ユーザーが独自に @Override して定義する前提。
 *
 * 〇9. A true?
 *         => equals()の定義 s.num == this.num; numが等しいかどうか
 *
 * ×10. A Sampleクラスでコンパイルエラー => 〇D false
 *         => Objectクラスを引数にするとObject.equals()が呼び出され同一性判定 => false
 * 〇11. B false => x.equals(null) => false
 * ｘ12. A false, true => 〇D true, true
 * ◆Stringのコンスタントプール
 * ・Stringインスタンスは(インスタンスではなく)定数値用のメモリ空間に作られ、
 *   同一プログラム内で重複したときは同一参照して使いまわされる。
 * ・""を用いた文字列リテラルだけの機能。
 * ・new String("")を用いた場合インスタンス用のメモリ空間に作られる。
 * 〇13. A false, true
 * ｘ14. ??
 * ◆String#intern()
 * ・コンスタントプールを含むメモリ内の文字列を探して、再利用するメソッド
 * ・new演算子で生成したインスタンスも、intern()でメモリ内の同一参照に戻す。
 *
 * 〇15. A if(num <= 10)
 * ｘ16. D 何も表示されない
 *           => 〇B if文の{}がないためif(false)でスキップされるのは１行目だけ
 * 〇17. C 「B C」
 * 〇18. A 「C」
 * ｘ19. B, C, D, ｘE, F, G (byte, short, int, ｘlong, String, enum) => A char
 * ｘ20. A, D (6行目, 12行目) => 〇A,B
 * switch(int num = 10)
 * A case "10": / B case num: / C case 2 * 5: / D case NUM:
 *
 * ◆switch case
 * ・条件式が戻す値と同じ型か互換性があること
 * ・定数であるかコンパイル時に値が決められる
 * ・nullでないこと
 * ・変数は不可
 *
 * ｘ21. B 「A, B」=> 〇C breakなしのダウンフォールはdefaultも対象に含む。
 */
/*
//====== ２回目 / 2021-01-12 ======
〇 1: C
〇 2: A / 100
〇 3: A, C, D
× 4: C / 33 => 〇: B 32 〔初歩的なミス、計算ミス〕
〇 5: C
〇 6: A
〇 7: A
〇 8: B
× 9: B => 〇: A
    -> a.Object.equals(b)は a == b を returnする
    -> equals()をOverrideし、numの同値性で２つのオブジェクトの同一性を判断している
    -> equals(Sample)で equals(Object)は継承関係のsuperで暗黙の型変換。
    -> Object.equals(Object)なのでシグニチャの変更はできず。

× 10: B => 〇: D
    -> int numでも「this.num == obj.num」は同一性(同じ参照かどうか)を判定する。
〇 11: B
〇 12: D
〇 13: A
× 14: B / 1 => 〇: D 3
    -> intern()は newされたStringも、コンスタントプールを参照する。全て trueとなり countは 3。
〇 15: A
〇 16: B
〇 17: C
〇 18: A
〇 19: A, B, C, D, F, G
× 20: B => 〇: A, B / 2つ選択 -> 問題ちゃんと読もう
〇 21: C

正答率 76.19 ％ ( 〇16問 / 全21問 )
*/
