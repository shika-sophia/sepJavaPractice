/**
 * @title javaSilver / Chapter04 / 第４章 制御構造
 * @content while, for, 二重ループ, 無限ループ, 拡張for, break, continue, ラベル
 * @see 志賀澄人『徹底攻略 Java SE11 Silver 問題集[1ZO-815]』(黒本),インプレス, 2019
 * @date 2020-09-13 / 1930-2130
 * @correctRate ① 9 / 17 = 52.9％
 */
/*
 * 〇1. A (b < 5)
 * 〇2. C do while( a < 5)
 * 〇3. E コンパイルエラー doブロックは省略不可?
 *          => 〔解〕{}は省略可能だが複数行を記述するとコンパイルエラー
 * ×4. B 246810
 *        => 〇Cコンパイルエラー 初期化文は複数定義できるが同じ型でなければいけない。
 * 〇5. E コンパイルエラー totalは未定義。for句内は外から参照不可。
 * ×6. C ; 0 < i; => 〇B i==0 基本問題
 * ×7. B 「0,2,4」=> 〇D 条件文に「,」不可。論理演算子で並立すべき。
 * ×8. C 「0,1,2」=> 〇A 「0,1,2,」
 * 〇9. C (int j = i; array[i].length; j++)
 * 〇10. A, C, D
 * ×11. B ( String str : array ) => 〇A (Object obj : array)
 *           => 本来は(String[] str : array)。
 *           2次元配列を拡張forで取り出すと、1次元配列となる。
 * 〇12. B「ABC」

 * ×13. D while(num++ <= 10){ => 〇E
 *           num++;
 *       }
 *       => Dはwhile内に入るが条件文に戻って更に+1され、13となりNG
 *
 * 〇14. A 「AA」
 * 〇15. B 9
 * ×16. B => 〇F
 * ◆ラベル可
 * ・コードブロック
 * ・ループ
 * ・分岐 if, switch
 * ・式
 * ・代入
 * ・return
 * ・try
 * ・throw
 *
 * ×17. A 6 =>〇B => 二重ループは表を作成して整理
 */
package javaSilver;

public class Chapter04 {

    public static void main(String[] args) {

    }
}
