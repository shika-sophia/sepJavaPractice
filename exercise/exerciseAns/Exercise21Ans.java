/**
 * @title javaPractice / exercise / Exercise21Ans
 * @content Exercise21～25 カレンダー作成
 * @content
 * @author shika
 * @date 2020-11-18
 */
/*
 * ◆Exercise21
 * 年数を入力すると、うるう年かどうかを判定するプログラムを作成せよ。
 *
 * 〔ヒント〕
 * https://boxil.jp/beyond/a5435/
 * うるう年の計算における原則は西暦を4で割ることです。
 *  4で割切れる数字の年はうるう年に当たります。
 * ただし例外があり、100で割り切れる年は平年になります。
 *
 * ◇実行結果
 * 年数を入力してください。2020
 *
 * 2020 年は うるう年です。
 *
 * 年数を入力してください。1800
 *
 * 1800 年は 平年です。
 * -----------------------------------
 * 年数を入力してください。4051
 *
 * < ! > [ 0～4020 ]で入力してください。
 *
 * 年数を入力してください。年数
 *
 * < ! > 整数で入力してください。
 *
 */
package javaPractice.exercise;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Exercise21Ans {

    public static void main(String[] args) {
        Scanner scn = null;
        int year = 0;

        //====== input loop =====
        inputLoop:
        while(true) {
            scn = new Scanner(System.in);

            //---- 不正値チェック ----
            try {
                System.out.print("年数を入力してください。");
                year = scn.nextInt();
                System.out.println();

            } catch(InputMismatchException e) {
                System.out.println("\n< ! > 整数で入力してください。\n");
                continue inputLoop;
            }

            //---- 現在の年数を取得 ----
            LocalDate date = LocalDate.now();
            int currentYear = date.getYear();

            //---- 不正値チェック ----
            if(0 < year && year < currentYear + 2000) {
                ;
            } else {
                System.out.printf("< ! > [ 0～%d ]で入力してください。\n\n", currentYear + 2000);
                continue inputLoop;
            }

            break inputLoop;
        }//while

        //うるう年の判定
        boolean leap = judgeLeap(year);//leap year: うるう年

        //結果の表示
        printResult(year, leap);
        scn.close();
    }//main()

    //====== judge leap year or not ======
    private static boolean judgeLeap(int year) {
        boolean leap = false;

        if (year % 4 == 0) {
            if (year % 100 == 0) {
                leap = false;
            } else {
                leap = true;
            }
        } else {
            leap = false;
        }

        return leap;
    }//judgeLeap()

    //====== print result ======
    private static void printResult(int year, boolean leap) {
        String leapStr = "";

        if (leap) {
            leapStr = "うるう年";
        } else {
            leapStr = "平年";
        }

        System.out.printf("%d 年は %sです。\n", year, leapStr);
    }//printResult()
}//class

/*
◇実行結果
年数を入力してください。2020

2020 年は うるう年です。

年数を入力してください。1800

1800 年は 平年です。
-----------------------------------
年数を入力してください。4051

< ! > [ 0～4020 ]で入力してください。

年数を入力してください。年数

< ! > 整数で入力してください。

*/