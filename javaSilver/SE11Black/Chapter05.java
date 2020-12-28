/**
 * @title javaSilver / Chapter05 / 第５章 配列
 * @content array, new, multi-array, clone()
 * @see 志賀澄人『徹底攻略 Java SE11 Silver 問題集[1ZO-815]』(黒本),インプレス, 2019
 * @date 2020-09-14 / 2130-2230
 * @date 2020-09-15 / 2100-2230
 * @correctRate ① 5.3 / 10 = 53.0％
 */
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
package javaSilver;

public class Chapter05 {
    public static void main(String[] args) {

    }
}
