/**
 * @title javaPractice / exercise / Exercise22Ans
 * @content Exercise21～25 カレンダー作成
 * @content
 * @author shika
 * @date 2020-12-04, 12-08
 */
/*
 * ◆Exercise22
 * 年と月を入力すると１日の曜日とその月の最終日は何日かを出力するプログラムを作成せよ。
 * 不正値が入力されても例外表示がでないようにし、
 * 適正な値が入力されるまで質問を繰り返すようループさせよ。
 *
 * 〔ヒント〕
 * 初日の曜日と、その月の末日を出すのに
 * 私はjava.timeパッケージ LocalDateクラスの各メソッドを使いました。
 * CalerdarクラスやDateクラスでも可能です。
 *
 *
 * 年を入力してください。2020
 *
 * 月を入力してください。12
 *
 * 2020年 12月１日は 火曜日です。
 * 2020年 12月は 31日までです。
 *------------------------------
 * 年を入力してください。3020
 *
 * < ! > [ 1 ～ 3000 ]の範囲で入力してください。
 *
 * 年を入力してください。2000
 *
 * 月を入力してください。13
 *
 * < ! > [ 1 ～ 12 ]の範囲で入力してください。
 *
 * 月を入力してください。-1
 *
 * < ! > [ 1 ～ 12 ]の範囲で入力してください。
 *
 * 月を入力してください。師走
 *
 * < ! > 整数で入力してください。
 *
 * 月を入力してください。
 */
package javaPractice.exercise;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Exercise22Ans {

    public static void main(String[] args) {
        //---- inputLoop ----
        InputLoopInt inLoop = new InputLoopInt();
        int[] questAns = inLoop.inputLoop();
        int year = questAns[0];
        int month = questAns[1];

        LocalDate ldt = LocalDate.of(year, month, 1);

        String dayOfWeekFirst = ldt.format(DateTimeFormatter.ofPattern("E曜日"));
        int lastDay = ldt.lengthOfMonth();

        System.out.printf("%d年 %d月１日は %sです。\n", year, month, dayOfWeekFirst);
        System.out.printf("%d年 %d月は %d日までです。\n", year, month, lastDay);
    }//main()
}//class


class InputLoopInt {
    String fixed = "を入力してください。";
    String[] quest = new String[] {
        "年","月"
    };
    int questNum = quest.length;
    int[] rangeLow = new int[]{ 1, 1 };
    int[] rangeHigh = new int[]{ 3000, 12 };


    public int[] inputLoop() {
        int[] questAns = new int[questNum];
        Scanner scn = null;

        for(int i = 0; i < questNum; i++) {

            inputLoop:
            while(true) {
                scn = new Scanner(System.in);

                try {
                    System.out.print(quest[i] + fixed);
                    questAns[i] = scn.nextInt();
                    System.out.println();

                //---- 不正値チェック (非整数) ----
                } catch (InputMismatchException e) {
                    System.out.println("\n< ! > 整数で入力してください。\n");
                    continue inputLoop;

                } catch (NumberFormatException e) {
                    System.out.println("\n< ! > 整数で入力してください。終了します。\n");
                    System.exit(0);
                }

                //---- 不正値チェック (範囲外) ----
                if (rangeLow[i] <= questAns[i] && questAns[i] <= rangeHigh[i]) {
                    ;
                } else {
                    System.out.printf("< ! > [ %d ～ %d ]の範囲で入力してください。\n\n",
                        rangeLow[i], rangeHigh[i]);
                    continue inputLoop;
                }

                break;
            }//while inputLoop

        }//for questNum

        scn.close();
        return questAns;
    }//inputLoop
}//class

/*
年を入力してください。2020

月を入力してください。12

2020年 12月１日は 火曜日です。
2020年 12月は 31日までです。
------------------------------
年を入力してください。3020

< ! > [ 1 ～ 3000 ]の範囲で入力してください。

年を入力してください。2000

月を入力してください。13

< ! > [ 1 ～ 12 ]の範囲で入力してください。

月を入力してください。-1

< ! > [ 1 ～ 12 ]の範囲で入力してください。

月を入力してください。師走

< ! > 整数で入力してください。

月を入力してください。１１

2000年 11月１日は 水曜日です。
2000年 11月は 30日までです。
*/