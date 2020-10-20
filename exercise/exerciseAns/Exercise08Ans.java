/**
 * @title javaPractice / exercise / Exercise08Ans
 * @content for, if, 剰余「%」演算子, 偶数, 奇数, 倍数
 * @author shika
 * @date 2020-10-20
 */
/*
◆Exercise08
for文を用いて下記の図形を作図せよ。

問１
    ●
   ●●
  ●●●
 ●●●●
●●●●●

問２
    ●
   ★★
  ●●●
 ★★★★
●●●●●

問３
         ●
        ●●
       ★★★
      ●●●●
     ●●●●●
    ★★★★★★
   ●●●●●●●
  ●●●●●●●●
 ★★★★★★★★★
●●●●●●●●●●

 */
package javaPractice.exercise;

public class Exercise08Ans {

    public static void main(String[] args) {
        //==== 問１解答 ====
        int row = 5; //行数を指定する変数
        for (int i = 1; i <= row; i++) {
            for(int space = (row - i); space > 0; space--) {
                System.out.print(" ");
            }//for space

            for(int j = 1; j <= i; j++) {
                System.out.print("●");
            }//for j
            System.out.println();
        }//for i
        System.out.println();

        //==== 問２解答 ====
        row = 5;
        for (int i = 1; i <= row; i++) {
            for(int space = (row - i); space > 0; space--) {
                System.out.print(" ");
            }//for space

            for(int j = 1; j <= i; j++) {
                if(i % 2 == 0) {
                    System.out.print("★");
                } else {
                    System.out.print("●");
                }

            }//for j
            System.out.println();
        }//for i
        System.out.println();

        //==== 問３解答 ====
        row = 10;
        for (int i = 1; i <= row; i++) {
            for(int space = (row - i); space > 0; space--) {
                System.out.print(" ");
            }//for space

            for(int j = 1; j <= i; j++) {
                if(i % 3 == 0) {
                    System.out.print("★");
                } else {
                    System.out.print("●");
                }

            }//for j
            System.out.println();
        }//for i

    }//main()

}//class

/*
《解説》
＊問１
Exercise07の問１を少し修正し「●」の前に半角スペースを挿入して作図。
空白を挿入する個数を指定する際、if文で if (i == 1), if(i == 2),…としないように。
if文の中の条件式は１ずつ増え、挿入するスペースは１ずつ減っているので、for文を使うことを考える。

for文の中で定義する iや jをカウンター変数という。
慣例的に i,j,k…を用いるが、複数あって混乱しそうなときは意味の分かる変数名をつけてもよい。spaceなど。


＊問２
問１に加えて、奇数行に「●」偶数行に「★」を表示。
◆剰余「%」演算子: 割り算の余りを返す。
「偶数」=> if(n % 2 == 0) ２で割って割り切れる(余り 0)
「奇数」=> if(n % 2 == 1) ２で割って余り１
この条件式はよく使うので定型として覚えてしまう。nは任意の整数や変数。


＊問３
今度は「３の倍数のとき」、「３行ごとに」★にする。
「〇の倍数」「〇ごとに」という場合も上記の剰余「%」演算子を使う。

行数は 変数rowの値を変えるだけで機能するように作った(なるべく定数部分を変数に置き換えた)ので、
row = 10を代入して変更。
汎用性〔Exercise07《解説》参照〕があるとコピペもしやすいのだ。


◇実行結果
    ●
   ●●
  ●●●
 ●●●●
●●●●●

    ●
   ★★
  ●●●
 ★★★★
●●●●●

         ●
        ●●
       ★★★
      ●●●●
     ●●●●●
    ★★★★★★
   ●●●●●●●
  ●●●●●●●●
 ★★★★★★★★★
●●●●●●●●●●

*/