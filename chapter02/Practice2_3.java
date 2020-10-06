/**
 * @title javaPractice / chapter02 / 練習問題２－３ (p096) => Practice2_3
 * @content Scanner, Random, try-catch, Thread.sleep()
 * @author shika
 * @date 2020-10-06
 */

package javaPractice.chapter02;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Practice2_3 {

    public static void main(String[] args) {
        //入力待ち
        Scanner scn = new Scanner(System.in);

        System.out.println("＊ようこそ占いの館へ＊");
        System.out.print("あなたの名前を入力してください。");
        String name = scn.nextLine();
        int age = 0;

        try {
            System.out.print("あなたの年齢を入力してください。");
            age = scn.nextInt();

        } catch (InputMismatchException e) {
            System.out.println("[エラー] 数字で入力してください。");
            System.exit(0);

        } finally {
            scn.close();
        }

        //運勢のランダム生成
        Random rdm = new Random();
        int fortune = rdm.nextInt(4) + 1;

        //運勢を String化
        String result = "";
        switch (fortune) {
        case 1:
            result = fortune + ": 大吉";
            break;

        case 2:
            result = fortune + ": 中吉";
            break;

        case 3:
            result = fortune + ": 吉";
            break;

        case 4:
            result = fortune + ": 凶";
            break;

        }//switch

        //画面に表示
        System.out.print("あなたの占い結果は");

        for (int i = 0; i < 3; i++) {
            System.out.print("・");

            try {
                Thread.sleep(500); //1000で１秒待機

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }//for

        System.out.println(); //改行
        System.out.printf("%sさん(%d歳)の運勢は[ %s ]\n", name, age, result);
    }//main()

}//class

/*
//====== Result ======
＊ようこそ占いの館へ＊
あなたの名前を入力してください。しか
あなたの年齢を入力してください。４７
あなたの占い結果は・・・
しかさん(47歳)の運勢は[ 3: 吉 ]

＊ようこそ占いの館へ＊
あなたの名前を入力してください。しか
あなたの年齢を入力してください。内緒
[エラー] 数字で入力してください。
*/

