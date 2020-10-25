/**
 * @title javaPractice / exercise / Exercise12Ans
 * @content 配列, Random, if, printf
 * @author shika
 * @date 2020-10-25
 */
/*
 * ◆Exercise12
 * 下記の実行結果になるような「じゃんけんゲーム」を作成せよ。
 * ただし、"グー","チョキ","パー"は、最初に１回だけ配列に記入し
 * 表示する際は配列から取り出すこと。
 *
 * また勝敗判定は switch((com - user + 3) % 3)で可能だが、
 * 練習のため 上記の式は使わず、if文で勝敗あいこの条件を考えましょう。
 *
◇実行結果
数字を入力してください。
[0] グー, [1] チョキ, [2] パー,
1
【YOU: チョキ】 vs 【COM : グー】

【あなたの負け】
--------------------------------
数字を入力してください。
[0] グー, [1] チョキ, [2] パー,
2
【YOU: パー】 vs 【COM : グー】

【あなたの勝ち】
--------------------------------
数字を入力してください。
[0] グー, [1] チョキ, [2] パー,
0
【YOU: グー】 vs 【COM : グー】

【あいこ】
--------------------------------
数字を入力してください。
[0] グー, [1] チョキ, [2] パー,
5
0～2で入力してください。
--------------------------------
 */
package javaPractice.exercise;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Exercise12Ans {

    public static void main(String[] args) {
        String[] finger = new String[] {"グー","チョキ","パー"};

        //==== comの乱数 ====
        Random rdm = new Random();
        int com = rdm.nextInt(3);

        //==== userの入力 ====
        System.out.println("数字を入力してください。");
        for(int i = 0; i < finger.length; i++) {
            System.out.printf("[%d] %s, ", i, finger[i]);
        }
        System.out.println();

        Scanner scn = new Scanner(System.in);
        int user = -1;

        //----不正なinputをチェック----
        //try-catchは、問題の課題ではないので気しなくていいです。
        try {
            user = scn.nextInt();

        } catch (InputMismatchException e) {
            System.out.println("整数で入力してください。");
            System.exit(0);
        } finally {
            scn.close();
        }

        //==== userと comの表示 ====
        //----不正な値をチェック----
        if(0 <= user && user <= 2) {
            System.out.printf("【YOU: %s】 vs 【COM : %s】\n\n",
                finger[user], finger[com]);
        } else {
            System.out.println("0～2で入力してください。");
        }

        //==== 勝敗判定 ====
        String result = "";
        for (int i = 0; i < finger.length; i++) {
            if (user == i && com == i) {
                result = "【あいこ】";
            }
        }//for i

        if((user == 0 && com == 1)
            ||(user == 1 && com == 2)
            ||(user == 2 && com == 0)){
            result = "【あなたの勝ち】";

        } else if ((user == 0 && com == 2)
                    ||(user == 1 && com == 0)
                    ||(user == 2 && com == 1)){
            result = "【あなたの負け】";
        }

        //==== 勝敗結果の表示 ====
        System.out.println(result);

    }//main()

}//class

/*
《解説》
・printf()の解説は Exercise04〔ヒント〕参照。
  String結合「+」演算子でも同様の表示は可能です。長くなるけど

・for文による配列の表示は、よくある決まった型なので、
  配列とfor文をいつもセットで使うことに慣れておきましょう。

・配列を使う際に、ArrayIndexOutOfBoundsExceptionに気を付けましょう。
  この例外については Exercise10〔ヒント〕参照。

・配列のindexを扱う前に、不正な値が入らないようチェックしましょう。
  そうすれば、上記の例外は防げます。

・問題文に書いた勝敗判定式は庄司先生の「じゃんけんゲーム」に掲載。
  コードにすると、こんな感じ。とてもシンプルに勝敗あいこを判定できます。

    String result = "";

    switch ((com - user + 3) % 3 ) {
        case 0:
            result = "【DRAW: あいこ 】";
            break;

        case 1:
            result = "【YOU WIN: 勝ち 】";
            break;

        case 2:
            result = "【YOU LOSE: 負け 】";
            break;
    }//switch


//==== 実行結果 ====
数字を入力してください。
[0] グー, [1] チョキ, [2] パー,
1
【YOU: チョキ】 vs 【COM : グー】

【あなたの負け】

数字を入力してください。
[0] グー, [1] チョキ, [2] パー,
2
【YOU: パー】 vs 【COM : グー】

【あなたの勝ち】

数字を入力してください。
[0] グー, [1] チョキ, [2] パー,
0
【YOU: グー】 vs 【COM : グー】

【あいこ】

数字を入力してください。
[0] グー, [1] チョキ, [2] パー,
数字
整数で入力してください。

数字を入力してください。
[0] グー, [1] チョキ, [2] パー,
5
0～2で入力してください。

*/