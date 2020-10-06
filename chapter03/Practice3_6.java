/**
 * @title javaPractice / chapter03 / 練習問題3-6 (p136) => Practice3_6
 * @title 数当てゲーム
 * @content Random, Scanner
 * @author shika
 * @date 2020-10-06
 */
package javaPractice.chapter03;

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
            }//while

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

/*
【数当てゲーム 0 ～ 99】８回で当ててください。
◆あなたの 1回目の回答を入力してください。[ 0 ～ 99 ] 50

あなたの回答 50 は、正解より大きいです。

◆あなたの 2回目の回答を入力してください。[ 0 ～ 99 ] 25

あなたの回答 25 は、正解より小さいです。

◆あなたの 3回目の回答を入力してください。[ 0 ～ 99 ] 30

あなたの回答 30 は、正解より小さいです。

◆あなたの 4回目の回答を入力してください。[ 0 ～ 99 ] 40

あなたの回答 40 は、正解より大きいです。

◆あなたの 5回目の回答を入力してください。[ 0 ～ 99 ] 35

あなたの回答 35 は、正解より大きいです。

◆あなたの 6回目の回答を入力してください。[ 0 ～ 99 ] 100

[エラー] 0 ～ 99で入力してください。

◆あなたの 6回目の回答を入力してください。[ 0 ～ 99 ] 33

あなたの回答 33 は、正解より小さいです。

◆あなたの 7回目の回答を入力してください。[ 0 ～ 99 ] 34

アタリ！
あなたの回答数 7回: [50, 25, 30, 40, 35, 33, 34]
◎正解は 34 でした。

*/