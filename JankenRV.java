/**
 * @title javaPractice / JankenRV
 * @content じゃんけん勝敗決定式 switch ((com - user + 3) % 3 )
 * @author Shouji,Recurrent Teacher
 * @author forked from Shouji-san, Revision wrote by shika
 * @date 2020-10-17
 */

package javaPractice;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class JankenRV {

    public static void main(String[] args) {
        System.out.println("＊じゃんけんゲーム＊");
        String[] finger = {"グー","チョキ", "パー"};

        Scanner scn = new Scanner(System.in);
        int user = -1;

        try {
            boolean illegal = true;
            do {
                System.out.println("数字を入力してください");

                for (int i = 0; i < finger.length; i++) {
                    System.out.printf("[%d] %s  ", i, finger[i]);
                }

                user = scn.nextInt();

                if (0 <= user && user <= 2) {
                    illegal = false;
                } else {
                    System.out.println("0～2で入力してください。");
                    illegal = true;
                }
            } while(illegal);

        } catch (InputMismatchException e ) {
            System.out.println("整数で入力してください。");
            System.exit(0);
        }

        //COM finger
        Random rdm = new Random();
        int com = rdm.nextInt(3);

        //print finger
        System.out.printf("\n【YOU: %s】 vs 【COM: %s】\n\n",
            finger[user], finger[com]);

        //勝敗判定メソッド
        String result = judgeResult(user,com);

        //print result
        System.out.println("結果:" + result);
        System.out.println("\n");

        scn.close();
    }//main()


    private static String judgeResult(int user, int com) {
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
        return result;
    }//judgeResult()

}//class
/*
＊じゃんけんゲーム＊
数字を入力してください
[0] グー  [1] チョキ  [2] パー  １

【YOU: チョキ】 vs 【COM: パー】

結果:【YOU WIN: 勝ち 】


＊じゃんけんゲーム＊
数字を入力してください
[0] グー  [1] チョキ  [2] パー  ４
0～2で入力してください。
数字を入力してください
[0] グー  [1] チョキ  [2] パー  -1
0～2で入力してください。
数字を入力してください
[0] グー  [1] チョキ  [2] パー  2

【YOU: パー】 vs 【COM: パー】

結果:【DRAW: あいこ 】


＊じゃんけんゲーム＊
数字を入力してください
[0] グー  [1] チョキ  [2] パー  数字
整数で入力してください。
*/