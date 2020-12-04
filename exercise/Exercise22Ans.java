/**
 * @title javaPractice / exercise / Exercise21Ans
 * @content Exercise21～25 カレンダー作成
 * @content
 * @author shika
 * @date 2020-12-04
 */
/*
 * ◆Exercise22
 * 年と月を入力すると１日の曜日とその月の最終日は何日かを出力するプログラムを作成せよ。
 *
 */
package javaPractice.exercise;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Exercise22Ans {

    public static void main(String[] args) {
        //---- inputLoop ----
        InputLoopInt inLoop = new InputLoopInt();
        int[] questAns = inLoop.inputLoop();


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
