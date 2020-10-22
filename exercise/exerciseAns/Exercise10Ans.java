/**
 * @title javaPractice / exercise / Exercise10Ans
 * @content 配列,(別解 二次元配列), ArraysIndexOutOfBoundsException, Scanner,
 *           while(true), break, continue, StringBuilder,
 *           フィールド, クラスファイルの実行
 * @author shika
 * @date 2020-10-22
 */
package javaPractice.exercise;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Exercise10Ans {
    private static int point; //駒の現在位置
    private static String[] square; //横４×縦３のマス目

    public static void main(String[] args) {
        //==== 初期設定 ====
        square = new String[12];

        //squareに全て「□」を入れて初期化
        for(int i = 0; i < square.length; i++) {
            square[i] = "□";
        }//for i

        //6番に◆を入れて駒とする
        point = 6;
        square[point] = "◆";

        //マス目の表示
        printSquare();

        //==== 移動の入力ループ ====
        loop:
        while(true) {
            Scanner scn = new Scanner(System.in);
            int input = -1;

            try {
                System.out.println("数字を入力すると移動します。");
                input = scn.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("整数で入力してください");
                continue loop;
            }

            //---- switch 入力分岐 ----
            //break loop, continue loopをするため、ここのメソッド分け不可
            int move = 0;

            switch (input) {
            case 0: //[0] 終了
                scn.close();
                break loop; //while loopを break

            case 2: //[2] ↓下へ移動
                move = +4;
                break;
            case 4: //[4] ←左へ移動
                move = -1;
                break;
            case 6: //[6] →右へ移動
                move = +1;
                break;
            case 8: //[8] ↑上へ移動
                move = -4;
                break;

            default: //上記以外の不正入力
                System.out.println("[2][4][6][8][0] の数字で入力してください。");
                continue loop;
            }//switch

            //移動処理メソッド
            movePoint(move);

            //squareをセット
            //putPoint();

            //マス目を表示
            printSquare();
        }//while loop

        System.out.println("お疲れさまでした。");
    }//main()


    //移動処理メソッド
    private static void movePoint(int move) {
        boolean movable = checkMove();

        if (movable) {
            point += move;
        } else {

        }
    }// movePoint()

    //移動チェックのメソッド
    //point 現在位置によって移動処理が変わるのでそれを判定
    private static boolean checkMove() {


        return false;
    }//checkMove()


    //マス目の表示
    private static void printSquare() {
        StringBuilder bld = new StringBuilder();
        bld.append("　　 [↑8]\n");
        bld.append("[←4] 　　[6→]\n");
        bld.append("　　 [↓2] 　　[0]終了\n\n");

        bld.append("　　");
        for(int i = 0; i < square.length; i++) {

            bld.append(square[i]);

            if((i + 1) % 4 == 0) {
                bld.append("\n");
                bld.append("　　");
            }
        }//for i

        System.out.println(bld.toString());
    }//printSquare()

}//class

/*
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　　□□□□
　　□□◆□
　　□□□□
　　
*/