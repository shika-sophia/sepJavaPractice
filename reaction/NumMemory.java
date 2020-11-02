/**
 * @title javaPractice / NumMemory
 * @content
 * @author Iwata-san, SepJava2020, [Num.java]
 * @author forked from Iwata-san / Revision by shika
 * @date 2020-10-26
 */

package javaPractice.reaction;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class NumMemory {

    public static void main(String[] args) {
        //ルール説明表示
        final int BOUND = 10; //0～いくつまでの数値か
        printRule(BOUND);

        //userとcomの数字を記憶するList
        List<Integer> pastNum = new ArrayList<>();

        //comランダムの準備
        Random rdm = new Random();
        int com = -1;

        //userとcomを合わせての宣言回数
        int count = 1;
        //過去の値とhitしたか真偽値
        boolean hit = false;

        //不正入力時にループ
        loop:
        while(!hit) { //hit = falseのとき繰り返し
            Scanner scn = new Scanner(System.in);

            //カウンター判定
            if(count == (BOUND + 1)) {
                System.out.println("最後の宣言です。");
            }
            System.out.printf("[0～%d]の数字を宣言してください(%d回目)", BOUND, count);
            int user;

            //不正な値をチェック
            try {
                user = scn.nextInt();
                System.out.println();

            } catch (InputMismatchException e) {
                System.out.println("整数で入力してください。");
                continue loop;
            }

            //不正な値をチェック
            if (0 <= user && user <= BOUND) {
                ;
            } else {
                System.out.printf("[0～%d]の数字で入力してください。\n", BOUND);
                continue loop;
            }

            //初回は過去リストがないため、先に処理
            if (pastNum.size() == 0) {
                hit = false;
            } else {
                //過去リストがある場合、userと被っているかチェック
                for(int num : pastNum) {
                    if (num == user) {
                        System.out.println("残念！あなたの負けです。");
                        pastNum.add(user);
                        hit = true;
                        break loop;
                    }
                }//for num
            }

            //過去リストに登録
            pastNum.add(user);

            //カウンターによる終了判定
            if (pastNum.size() == (BOUND + 1)) {
                System.out.println("おめでとう！あなたの勝ちです。");
                scn.close();
                break loop;
            }

            //comのターン
            boolean comHit;
            com:
            do {
                comHit = false;
                com = rdm.nextInt(BOUND + 1);

                for(int num : pastNum) {
                    if (num == com) {
                        comHit = true;
                        continue com; //下のwhile(comHit)へ移動
                    }
                }//for num

                //com宣言の表示
                count++;//com用カウント
                System.out.printf("COMの宣言(%d回目)", count);
                //「・・・」の表示
                for (int i = 1; i <= 3; i++) {
                    System.out.print("・");
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(com);
                System.out.println();

                //過去リストに登録
                pastNum.add(com);

                //カウンターによる終了判定(BOUNDが奇数のとき)
                if (pastNum.size() >= (BOUND + 1)) {
                    System.out.println("COMの勝ちです。");
                    scn.close();
                    break loop;
                }

                comHit = false;
            } while(comHit);

            count++; //user用カウント。下に置いたのは不正入力時をカウントしないため
        }//while loop

        System.out.println("宣言の記録: " + pastNum);

    }//main


    private static void printRule(int BOUND) {
        //ゲームルールの紹介
        System.out.println("＊＊＊＊数覚えゲーム＊＊＊＊");
        System.out.println("～～～～～ルール～～～～～");
        System.out.printf("0-%dまでの%d個の数字があります。\n", BOUND, BOUND +1);
        System.out.println("その数字の中からプレイヤーとコンピュータが交互に数字を宣言します。");
        System.out.println("すでに出た数字を宣言してしまうと負けとなります。");
        System.out.println("プレイヤーが先行、コンピュータが後攻です");
        System.out.println("最後に残った数字を宣言できればプレイヤーの勝ちです。");
        System.out.println("～～～～～～～～～～～～～～");
        System.out.println();
    }//printRule()

}//class

/*
＊＊＊＊数覚えゲーム＊＊＊＊
～～～～～ルール～～～～～
0-10までの11個の数字があります。
その数字の中からプレイヤーとコンピュータが交互に数字を宣言します。
すでに出た数字を宣言してしまうと負けとなります。
プレイヤーが先行、コンピュータが後攻です
最後に残った数字を宣言できればプレイヤーの勝ちです。
～～～～～～～～～～～～～～

[0～10]の数字を宣言してください(1回目)1

COMの宣言(2回目)・・・5

[0～10]の数字を宣言してください(3回目)0

COMの宣言(4回目)・・・6

[0～10]の数字を宣言してください(5回目)3

COMの宣言(6回目)・・・4

[0～10]の数字を宣言してください(7回目)2

COMの宣言(8回目)・・・7

[0～10]の数字を宣言してください(9回目)8

COMの宣言(10回目)・・・9

最後の宣言です。
[0～10]の数字を宣言してください(11回目)10

おめでとう！あなたの勝ちです。
宣言の記録 [1, 5, 0, 6, 3, 4, 2, 7, 8, 9, 10]

残念！あなたの負けです。
宣言の記録: [0, 8, 1, 3, 2, 4, 5, 6, 6]

[0～10]の数字を宣言してください(3回目)数字
整数で入力してください。
[0～10]の数字を宣言してください(3回目)２４

[0～10]の数字で入力してください。
[0～10]の数字を宣言してください(3回目)3
*/