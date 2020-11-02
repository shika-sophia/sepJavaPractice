/**
 * @title javaPractice / exercise / Exercise14Ans
 * @content 配列, for, while(true),
 * @see javaPractice.chapter03.Practice3_6
 * @author shika
 * @date 2020-10-30
 */
/*
◆Exercise14
問１
テキストの練習問題３－６を発展させ、[0～99]の数当てゲームを作りたい。
ユーザーの入力は全７回で、配列を用いて入力情報を保持し、正解の数を当てる。
ヒントに、ユーザーの入力した数は正解より[大きい / 小さい]を表示する。
最後に正解と

問２
上記の問１の上限値を final int BOUND = 99;とし、同様の動きをするアプリを作成せよ。
上限値の変更は final宣言したここ１か所を変更すればできるようにしてほしい。
上限値が増えたら入力回数が何回あれば正解可能かもプログラム上で計算し、
入力回数も変数で調整すること。

◇実行結果
◆(1回目 / 全7回)の回答 [0～99] 50
あなたの回答 50 は、正解より小さいです。

◆(2回目 / 全7回)の回答 [0～99] 75
あなたの回答 75 は、正解より大きいです。

◆(3回目 / 全7回)の回答 [0～99] 60
あなたの回答 60 は、正解より大きいです。

◆(4回目 / 全7回)の回答 [0～99] 55
あなたの回答 55 は、正解より小さいです。

◆(5回目 / 全7回)の回答 [0～99] 57
アタリ！
正解は 57
回答の履歴: [ 50, 75, 60, 55, 57, ]
------------------------------------
全7回 終了です｡
正解は 21
回答の履歴: [ 0, 0, 0, 0, 0, 0, 0, ]
 */
/*
 //###### 問１解答 / 上限99 ArrayList Version ######
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Practice3_6 {

    public static void main(String[] args) {
        System.out.println("【数当てゲーム 0 ～ 99】８回で当ててください。");
        List<Integer> inputList = new ArrayList<>();

        //---- answer ランダム生成 ----
        Random rdm = new Random();
        int answer = rdm.nextInt(100);

        //---- user input ----
        Scanner scn = new Scanner(System.in);
        int input = -1;

        userInput:
        for(int i = 1; i <= 8; i++) {

            loop:
            while(true) {
                try {
                    System.out.printf("◆あなたの %d回目の回答を入力してください。[ 0 ～ 99 ] ", i);
                    input = scn.nextInt();

                    System.out.println();//改行

                } catch (InputMismatchException e) {
                    System.out.println("[エラー] 整数で入力してください。\n");
                    System.exit(0);
                }

                //---- judge adaptable input / inputの適合判定 ----
                if (0 <= input && input <= 99) {
                    inputList.add(input);
                    break loop;
                } else {
                    System.out.println("[エラー] 0 ～ 99で入力してください。\n");
                    continue loop;
                }
            }//while loop

            //---- answer と input の正解判定・大小比較 ----
            String message = "";

            if (input == answer) {
                System.out.println("アタリ！");
                break userInput;

            } else if (input > answer) {
                message = String.format("あなたの回答 %d は、正解より大きいです。\n", input);

            } else if (input < answer) {
                message = String.format("あなたの回答 %d は、正解より小さいです。\n", input);

            }
            System.out.println(message);

        }//for

        //---- 最終表示 ----
        System.out.printf("あなたの回答数 %d回: ",inputList.size());
        System.out.println(inputList);
        System.out.printf("◎正解は %d でした。\n", answer);

        scn.close();
    }//main()

}//class
 */
//###### 問２解答 / final BOUND Version ######
package javaPractice.exercise;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Exercise14Ans {

    public static void main(String[] args) {
        final int BOUND = 99; //0～BOUND answerの上限値
        Scanner scn = null;

        //正答可能な回数の計算
        int inputTimes = calcTimes(BOUND);
        int[] input = new int[inputTimes];

        //---- answer ランダム生成 ----
        Random rdm = new Random();
        int answer = rdm.nextInt(BOUND + 1);

        userInput:
        for (int i = 0; i < input.length; i++) {

            //不正値チェックのためのwhile loop
            loop:
            while(true) {
                scn = new Scanner(System.in);

                //不正値チェック
                try {
                    System.out.printf("◆(%d回目 / 全%d回)の回答 [0～%d] ",
                        (i + 1), input.length,BOUND);
                    input[i] = scn.nextInt();

                } catch (InputMismatchException e) {
                    System.out.println("整数で入力してください。");
                    continue loop;
                }

                //不正値チェック
                if (0 <= input[i] && input[i] <= BOUND) {
                    ; //inputが適正値なら while loopを抜ける
                    break loop;

                } else {
                    System.out.printf(" 0 ～ %dで入力してください。\n", BOUND);
                    continue loop;
                }
            }//while loop

            //---- answer と input の正解判定・大小比較 ----
            String message = "";

            if (input[i] == answer) {
                System.out.println("アタリ！");

                //iが最終回ならこの処理なし
                if (i == (input.length - 1)) {
                    ;
                } else {
                    //以後の input[]は0になるため次のinputから -1に置換
                    for (int j = (i + 1); j < input.length; j++) {
                        input[j] = -1;
                    }//for j
                }//if-else

                break userInput;

            } else if (input[i] > answer) {
                message = String.format("あなたの回答 %d は、正解より大きいです。\n", input[i]);

            } else if (input[i] < answer) {
                message = String.format("あなたの回答 %d は、正解より小さいです。\n", input[i]);

            }
            System.out.println(message);

            //---- 回数による終了表示 ----
            if (i == (input.length - 1)) {
                System.out.printf("全%d回 終了です｡\n", input.length);
            }
        }//for i

        //==== inputの最終表示====
        System.out.println("正解は " + answer);

        System.out.print("回答の履歴: [ ");

        for(int bit: input) {
            //途中終了(アタリ)の場合、配列に-1を入れてあるので非表示
            if(bit == -1) {
                ;
            } else {
                System.out.print(bit + ", ");
            }

        }//for input
        System.out.print("]");

        scn.close();
    }//main()


    private static int calcTimes(int BOUND) {
        int count = 1;
        int devide = BOUND / 2;

        loop:
        while(true) {
        //2で割り続けて商が 1 or 0になるまで続ける。その回数をカウント
        //(その回数分 + 1)をinputできれば、必ずanswerを当てられる。
            devide /= 2;
            count++;

            if (devide == 1 || devide == 0) {
                break loop;
            }
        }//while loop

        return (count + 1);
    }//calcTimes()

}//class

/*
◆(1回目 / 全7回)の回答 [0～99] 50
あなたの回答 50 は、正解より小さいです。

◆(2回目 / 全7回)の回答 [0～99] 75
あなたの回答 75 は、正解より大きいです。

◆(3回目 / 全7回)の回答 [0～99] 60
あなたの回答 60 は、正解より大きいです。

◆(4回目 / 全7回)の回答 [0～99] 55
あなたの回答 55 は、正解より小さいです。

◆(5回目 / 全7回)の回答 [0～99] 57
アタリ！
正解は 57
回答の履歴: [ 50, 75, 60, 55, 57, ]

------------------------------------
全7回 終了です｡
正解は 21
回答の履歴: [ 0, 0, 0, 0, 0, 0, 0, ]
------------------------------------
◆(1回目 / 全7回)の回答 [0～99] 回答
整数で入力してください。
◆(1回目 / 全7回)の回答 [0～99] 100
 0 ～ 99で入力してください。
◆(1回目 / 全7回)の回答 [0～99] -1
 0 ～ 99で入力してください。
◆(1回目 / 全7回)の回答 [0～99] 50
あなたの回答 50 は、正解より小さいです。

◆(2回目 / 全7回)の回答 [0～99]

-------------------------------------
final int BOUND = 200;
◆(1回目 / 全8回)の回答 [0～200] 100
あなたの回答 100 は、正解より小さいです。

◆(2回目 / 全8回)の回答 [0～200] 150
あなたの回答 150 は、正解より大きいです。

◆(3回目 / 全8回)の回答 [0～200] 125
あなたの回答 125 は、正解より小さいです。

◆(4回目 / 全8回)の回答 [0～200] 142
あなたの回答 142 は、正解より大きいです。

◆(5回目 / 全8回)の回答 [0～200] 135
あなたの回答 135 は、正解より小さいです。

◆(6回目 / 全8回)の回答 [0～200] 140
あなたの回答 140 は、正解より小さいです。

◆(7回目 / 全8回)の回答 [0～200] 141
アタリ！
正解は 141
回答の履歴: [ 100, 150, 125, 142, 135, 140, 141, ]
*/