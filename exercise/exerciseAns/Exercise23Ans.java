/**
 * @title javaPractice / exercise / Exercise23Ans
 * @content Exercise21～25 カレンダー作成
 * @content
 * @author shika
 * @date 2020-12-08
 */
/*
 * ◆Exercise23
 * 年,月,日を入力してもらい、
 * その年月のカレンダーを表示し、指定した日に[]を付ける。
 * 日の入力は上限を考慮し２月30日などは不正値として入力し直してもらうこと。
 *
 * ◇実行結果
 * 年を入力してください。2020
 *
 * 月を入力してください。11
 *
 * 日を入力してください。24
 *
 * 2020年11月24日
 *  日   月   火   水   木   金   土
 *   1    2    3    4    5    6    7
 *   8    9   10   11   12   13   14
 *  15   16   17   18   19   20   21
 *  22   23  [24]  25   26   27   28
 *  29   30
 *
 * 2020年12月24日
 *  日   月   火   水   木   金   土
 *  　   　    1    2    3    4    5
 *   6    7    8    9   10   11   12
 *  13   14   15   16   17   18   19
 *  20   21   22   23  [24]  25   26
 *  27   28   29   30   31
 *
 * ----------------------------------
 * 年を入力してください。2020
 *
 * 月を入力してください。2
 *
 * 日を入力してください。30
 *
 * < ! > [ 1 ～ 29 ]の範囲で入力してください。
 *
 * 日を入力してください。
 */
package javaPractice.exercise;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Exercise23Ans {
    private static int year;
    private static int month;
    private static int day;
    private static int lastDay;
    private static int dayWeek;
    private static String[] dayWeekArr;

    public static void main(String[] args) {
        InputLoopIntExercise23 inLoop = new InputLoopIntExercise23();
        int[] questAns = inLoop.inputLoop();
        year = questAns[0];
        month = questAns[1];
        day = questAns[2];

        LocalDate ldtFirst = LocalDate.of(year, month, 1);

        //月1 火2・・日7 / 1-7
        dayWeek = ldtFirst.get(ChronoField.DAY_OF_WEEK);
        if (dayWeek == 7) {
            dayWeek = 0;
        }

        dayWeekArr = new String[] {
            "日","月","火","水","木","金","土"
        };

        lastDay = inLoop.lastDay;

        printCalendar();
    }//main()

    private static void printCalendar() {
        StringBuilder bld = new StringBuilder();
        bld.append(String.format("\n%d年%d月%d日\n", year, month, day));

        //---- "日" "月" ・・の表示 ----
        for(int i = 0; i < dayWeekArr.length; i++) {
            bld.append(" ").append(dayWeekArr[i]).append(" ").append(" ");
        }
        bld.append("\n");

        //---- 開始前の空白挿入 ----
        for(int i = 0; i < dayWeek; i++) {
            bld.append(" ").append("　").append(" ").append(" ");
        }

        //---- 日付の表示 / 指定日に[]を付与 ----
        for(int i = 1; i <= lastDay; i++) {
            if(i == day){
                bld.append("[");
            } else {
                bld.append(" ");
            }

            bld.append(String.format("%2d", i));

            if(i == day){
                bld.append("]");
            } else {
                bld.append(" ");
            }
            bld.append(" ");

            if((i + dayWeek) % 7 == 0) {
                bld.append("\n");
            }//if
        }//for 日付

        System.out.println(bld.toString());

    }//printCalendar

}//class


class InputLoopIntExercise23 {
    private String fixed = "を入力してください。";
    private final String[] quest = new String[] {
        "年","月","日"
    };
    private int questNum = quest.length;
    int lastDay;
    private int[] rangeLow = new int[]{ 1, 1, 1};
    private int[] rangeHigh = new int[]{ 3000, 12, lastDay};

    public int[] inputLoop() {
        int[] questAns = new int[questNum];
        Scanner scn = null;

        for(int i = 0; i < questNum; i++) {
            if(i == 2) {
                LocalDate ldt = LocalDate.of(questAns[0], questAns[1], 1);
                lastDay = ldt.lengthOfMonth();
                rangeHigh[2] = lastDay;
            }

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

月を入力してください。11

日を入力してください。24


2020年11月24日
 日   月   火   水   木   金   土
  1    2    3    4    5    6    7
  8    9   10   11   12   13   14
 15   16   17   18   19   20   21
 22   23  [24]  25   26   27   28
 29   30


2020年12月24日
 日   月   火   水   木   金   土
 　   　    1    2    3    4    5
  6    7    8    9   10   11   12
 13   14   15   16   17   18   19
 20   21   22   23  [24]  25   26
 27   28   29   30   31

----------------------------------
年を入力してください。2020

月を入力してください。2

日を入力してください。30

< ! > [ 1 ～ 29 ]の範囲で入力してください。

日を入力してください。
*/