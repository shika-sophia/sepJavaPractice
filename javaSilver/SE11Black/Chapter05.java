/**
 * @title javaSilver / SE11Black / Chapter05 / 第５章 配列
 * @content array, new, multi-array, clone()
 * @see 志賀澄人『徹底攻略 Java SE11 Silver 問題集[1ZO-815]』(黒本),インプレス, 2019
 * @date 2020-09-14 / 2130-2230
 * @date 2020-09-15 / 2100-2230
 * @date 2021-01-13 / 14:38-14:50
 * @correctRate ① 5.3 / 10 = 53.0％
 * @correctRate ②60.00 ％ ( 〇6問 / 全10問 )
 */
package javaSilver.SE11Black;

import javaSilver.AnswerMaker;

public class Chapter05 {
    public static void main(String[] args) {
        new AnswerMaker();
    }//main()
}//class

/*
 * 〇1. E ハッシュコード
 * ◆Oblect.toString()
 * public String toString(){
 *     return getClass().getName() + @ + Integer.toHexString(hashCode());
 * }
 * ×2. F 7,8行目 int[] e[],int[][] f[] => 〇G 全て誤りではない
 * 〇3. E 全て誤り
 * 4. 〇A, 〇B, 〇E
 * ◆２次元配列の２段階生成
 * int[][] array = new int[3][]
 * array[0] = new int[3];
 * array[1] = new int[3];
 * array[2] = new int[3];
 *
 * ×int[][] array = new int[][3]
 * 		１次元目の要素数を指定せずに、２次元目の要素数を指定するとコンパイルエラー
 *
 * ×5. C 300 => 〇E 実行時に例外
 * 		=> Item[] items = new Item[3];は配列オブジェクトを作っているだけで
 * 		   配列の要素には何も代入していないし、Itemクラスのインスタンスを作っているわけではない。
 * 		=> 実行時に NullPointerException
 *
 * ◆配列のdefault値
 * ・整数型: 0
 * ・浮動小数点型: 0.0
 * ・真偽値型: false
 * ・文字型: \u0000
 * ・オブジェクト型: null
 *
 * 〇6. C nullBCD
 *
 * 7, ×A, 〇B, ×E => 〇B,C,D
 *   => ×int[] array = new int[2]{2,3}; newと同時に初期化子{2, 3}と要素を指定するとコンパイルエラー
 *   => {2, 3}と要素を指定することで要素数を自動的に決め配列オブジェクトを生成する。
 *   => {}だけだと要素数 0の配列ができる。
 *   => newがなときの初期化子が必要な次元数も計算(変数の次元数に合わせる)。
 *
 * ×8. B 5 => 〇E 実行時に例外
 * 		=> 要素nullは参照先が何もないことを示すリテラル。要素数0の配列ではない。
 * 		=> NullPointerException
 *
 * ×9. A 3行目でコンパイルエラー classCとA[]は互換性がない => 〇D
 * 		=> 継承関係にあるsuper型の配列は A[] array = new B[] { new B(), new B() };が可能。
 *
 * 〇10.B 12
 */
/*
//====== ２回目 / 2021-01-13 ======
〇 1: E
× 2: F => 〇: G
  -> 配列の型宣言は、型と変数の両方に[]可。
  -> 分けたときは次元を加算。int[][] f[] -> 3次元配列
× 3: D => 〇: E
  -> 配列型の変数宣言(前半)に、配列の大きさを宣言するのは全て誤り。
〇 4: A, B, F
〇 5: E / NullPo
〇 6: C
× 7: A, C, E => 〇: B, C, D
 -> [2]と{ 2, 3 }の両方指定するのはコンパイルエラー
 -> int b[][] = { };空の初期化子はＯＫ。
     -> 要素数 0の配列インスタンスが生成される。
 -> 初期化子は変数宣言と同時にしか使えない。
 -> 変数の型だけ宣言するのはＯＫ。
 -> インスタンス時に要素数・次元が分からない記述はコンパイルエラー。
〇 8: E / NullPo
 -> 配列の lengthはフィールドではなく、
    コンパイル時に要素数を数える命令に置き換わるためメソッドに近い働きをする。
× 9: C => 〇: D
〇 10: B

開始時刻 14:38
終了時刻 14:50
所要時間 12 分

正答率 60.00 ％ ( 〇6問 / 全10問 )
*/

