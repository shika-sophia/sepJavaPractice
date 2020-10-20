/**
 * @title javaPractice / exercise / Exercise03Ans
 * @content メソッド分け, switch
 * @author shika
 * @date 2020-10-19
 */
/*
 * ◆Exercise03
 * x, yの値をユーザーに入力してもらい、
 * 更に計算方法を選んでもらう。[1]足し算, [2]引き算, [3]掛け算, [4]割り算
 *
 * 計算は mainメソッドとは別のメソッドで行い、
 * 計算結果の表示はmainメソッドで行うプログラムを作成せよ。
 */
package javaPractice.exercise;

import java.util.Scanner;

public class Exercise03Ans {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.print("xの値を入力してください。");
        int x = scn.nextInt();

        System.out.print("yの値を入力してください。");
        int y = scn.nextInt();

        System.out.print("計算方法を数字で選択してください。\n"
            + "[1]足し算, [2]引き算, [3]掛け算, [4]割り算");
        int calc = scn.nextInt();

        //---- switch分岐、計算メソッドの呼び出し ----
        int result = 0;
        switch(calc) {
        case 1:
            result = calcAdd(x, y);
            break;
        case 2:

            result = calcSubstract(x, y);
            break;

        case 3:

            result = calcMultiply(x, y);
            break;
        case 4:

            result = calcDevide(x, y);
            break;

        default:
            System.out.println("1～4の数字で入力してください。");
            System.exit(0);
        }//switch

        System.out.println("計算結果は " + result);
        scn.close();
    }//main()


    private static int calcAdd(int x, int y) {
        return x + y;
    }//calcAdd()

    private static int calcSubstract(int x, int y) {
        return x - y;
    }//calcSubstract()

    private static int calcMultiply(int x, int y) {
        return x * y;
    }//calcMultiply()

    private static int calcDevide(int x, int y) {
        if (y == 0) {
            System.out.println("0で割ることはできません。");
            System.exit(0);
        }//if

        return x / y;
    }//calcDevide()

}//class
/*
xの値を入力してください。4
yの値を入力してください。2
計算方法を数字で選択してください。
[1]足し算, [2]引き算, [3]掛け算, [4]割り算1
計算結果は 6

xの値を入力してください。6
yの値を入力してください。2
計算方法を数字を入力してください。
[1]足し算, [2]引き算, [3]掛け算, [4]割り算2
計算結果は 4

xの値を入力してください。7
yの値を入力してください。0
計算方法を数字を入力してください。
[1]足し算, [2]引き算, [3]掛け算, [4]割り算4
0で割ることはできません。

xの値を入力してください。32
yの値を入力してください。24
計算方法を数字を入力してください。
[1]足し算, [2]引き算, [3]掛け算, [4]割り算 7
1～4の数字で入力してください。
*/