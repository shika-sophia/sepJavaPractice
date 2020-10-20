/**
 * @title javaPractice / exercise / Exercise07Ans
 * @content for, nested for, i--, 《解説》プログラムの汎用性
 * @author shika
 * @date 2020-10-20
 */
/*
◆Exercise07
for文を用いて下記のグラフを作図せよ。
ただし「●」の表示は「System.out.print("●");」のみで行うこと。

問１
●
●●
●●●
●●●●
●●●●●

問２
●●●●●
●●●●
●●●
●●
●

問３
●
●●
●●●
●●●●
●●●●●
●●●●
●●●
●●
●
 */
package javaPractice.exercise;

public class Exercise07Ans {

    public static void main(String[] args) {
        //==== 問１解答 ====
        for(int i = 1; i <= 5; i++) {
            for(int j = 1; j <= i; j++) {
                System.out.print("●");
            }//for j
            System.out.println();
        }//for i
        System.out.println();

        //==== 問２解答 ====
        for(int i = 5; i >= 1; i--) {
            for(int j = 1; j <= i; j++) {
                System.out.print("●");
            }//for j
            System.out.println();
        }//for i
        System.out.println();

        //==== 問３解答 ====
        int count = 0;
        for(int i = 1; i <= 5; i++) {
            for(int j = 1; j <= i; j++) {
                System.out.print("●");
            }//for j
            count++;
            System.out.println();
        }//for i

        for(int i = (count-1); i >= 1; i--) {
            for(int j = 1; j <= i; j++) {
                System.out.print("●");
            }//for j
            System.out.println();
        }//for i

    }//main()

}//class

/*
◆《解説》
＊問１
for iで行を作り、for jで横の「●」の並びを作るのは、九九と同じ構造。
「●」をひとつずつ増やさないといけないので、for jを回す回数をひとつずつ増やす。
for jの終了条件式〔= for( ; ; )の真ん中の式〕が iの値と同じであることに気付くと
「j <= i;」を思いつく。

＊問２
今度は減らさなければいけないので、これまで慣れてきた i++ではなく、
初期値が大きくて ひとつずつ減らす i--。終了条件式の不等号の向きに注意。
何度も実行をしてみて、思うような結果になるよう修正してみよう。

＊問３
問１と２を合わせればいい。
countを使わず下段を for(int i = 4; …)から始めてもできるのだが、
上段のforブロックと下段の forブロックを繋ぐには countをforブロックの外で宣言し、
上段でカウントした値を下段で使っている。この書き方も覚えておこう

また「最大列の●を10個に変更」と言った場合、countで繋いであるコードは１か所 5 -> 10に変更すればいい。
countを使わず 独立したのfor文を２つを並べた場合は２か所を変更せねばならない。

《プログラムの汎用性》
のちのちの変更に強いコードにするためには、
なるべく定数(固定値)を使わず、変数を用いて記述すると良い。

このようにして作っていくと汎用性のある(= 他でも使える)コードになっていく。
なるべく汎用できるプログラムにしていくことが、プログラム作りの大事な考え方です。


◇実行結果
●
●●
●●●
●●●●
●●●●●

●●●●●
●●●●
●●●
●●
●

●
●●
●●●
●●●●
●●●●●
●●●●
●●●
●●
●

＊おまけ for(int i = 1; i <= 10; i++)
●
●●
●●●
●●●●
●●●●●
●●●●●●
●●●●●●●
●●●●●●●●
●●●●●●●●●
●●●●●●●●●●
●●●●●●●●●
●●●●●●●●
●●●●●●●
●●●●●●
●●●●●
●●●●
●●●
●●
●

*/