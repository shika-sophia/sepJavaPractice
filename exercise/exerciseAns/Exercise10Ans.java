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

//###### variable WIDTH & HEIGHT version ######
//マス目の横×縦を変数にしたversion
public class Exercise10Ans {
    private static int point; //駒の現在位置
    private static int prePoint;//駒の前位置をコピー
    private static int move;
    private static final int WIDTH = 4; //マス目の横幅
    private static final int HEIGHT = 3;//マス目の縦高
    private static String[] square; //横WIDTH×縦HEIGHTのマス目

    public static void main(String[] args) {
        //==== 初期設定 ====
        square = new String[WIDTH * HEIGHT];

        //squareに全て「□」を入れて初期化
        for(int i = 0; i < square.length; i++) {
            square[i] = "□";
        }//for i

        //初期値 6番に◆を入れて駒とする
        point = 6;
        prePoint = point;
        move = 0;
        movePoint();

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

            switch (input) {
            case 0: //[0] 終了
                scn.close();
                break loop; //while loopを break

            case 2: //[2] ↓下へ移動
                checkDownable();
                break;
            case 4: //[4] ←左へ移動
                checkLeftable();
                break;
            case 6: //[6] →右へ移動
                checkRightable();
                break;
            case 8: //[8] ↑上へ移動
                checkUpable();
                break;

            default: //上記以外の不正入力
                System.out.println("[2][4][6][8][0] の数字で入力してください。");
                continue loop;
            }//switch

            //移動処理メソッド
            movePoint();

            //マス目を表示
            printSquare();
        }//while loop

        System.out.println("お疲れさまでした。");
    }//main()


    private static void checkDownable() {
        for (int i = 0; i < WIDTH; i++) {
            //point が最下段にあるとき
            if (point == (WIDTH * (HEIGHT - 1) + i)){
                move = -(WIDTH * (HEIGHT - 1));
                break;
            } else {
                move = WIDTH;
            }
        }//for

    }//checkDownable()

    private static void checkLeftable() {
        for (int i = 0; i < HEIGHT; i++) {
            //pointが最左列にいる場合
            if(point == WIDTH * i) {
                move = (WIDTH - 1);
                break;
            } else {
                move = -1;
            }
        }//for

    }//checkLeftable()

    private static void checkRightable() {
        for (int i = 0; i < HEIGHT; i++) {
            //pointが最右列にいる場合
            if(point == (WIDTH - 1) + WIDTH * i) {
                move =  -(WIDTH - 1);
                break;
            } else {
                move = +1;
            }
        }//for

    }//checkeRightable()

    private static void checkUpable() {
        for (int i = 0; i < WIDTH; i++) {
            //point が最上段にあるとき
            if (point == i ){
                move = WIDTH * (HEIGHT - 1);
                break;
            } else {
                move = -(WIDTH);
            }
        }//for

    }//checkUpable()


    private static void movePoint() {
        prePoint = point;
        point += move;
        square[prePoint] = "□";
        square[point] = "◆";
    }//movePoint()

    //マス目の表示
    private static void printSquare() {
        StringBuilder bld = new StringBuilder();
        bld.append("　　 [↑8]\n");
        bld.append("[←4] 　　[6→]\n");
        bld.append("　　 [↓2] 　　[0]終了\n\n");

        bld.append("　　");
        for(int i = 0; i < square.length; i++) {

            bld.append(square[i]);

            if((i + 1) % WIDTH == 0) {
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
