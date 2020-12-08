/**
 * @title javaPractice / exercise / Exercise24Ans
 * @content Exercise21～25 カレンダー作成
 * @content
 * @author shika
 * @date 2020-12-08
 */
/*
◆Exercise24
前問までのプログラムを利用し、年数を入力すると
その年の１月～12月のカレンダーを表示するプログラムを作成せよ。

◇実行結果
年を入力してください。2020

2020年1月
 日   月   火   水   木   金   土
 　   　   　    1    2    3    4
  5    6    7    8    9   10   11
 12   13   14   15   16   17   18
 19   20   21   22   23   24   25
 26   27   28   29   30   31

2020年2月
 日   月   火   水   木   金   土
 　   　   　   　   　   　    1
  2    3    4    5    6    7    8
  9   10   11   12   13   14   15
 16   17   18   19   20   21   22
 23   24   25   26   27   28   29


2020年3月
 日   月   火   水   木   金   土
  1    2    3    4    5    6    7
  8    9   10   11   12   13   14
 15   16   17   18   19   20   21
 22   23   24   25   26   27   28
 29   30   31

2020年4月
 日   月   火   水   木   金   土
 　   　   　    1    2    3    4
  5    6    7    8    9   10   11
 12   13   14   15   16   17   18
 19   20   21   22   23   24   25
 26   27   28   29   30

2020年5月
 日   月   火   水   木   金   土
 　   　   　   　   　    1    2
  3    4    5    6    7    8    9
 10   11   12   13   14   15   16
 17   18   19   20   21   22   23
 24   25   26   27   28   29   30
 31

2020年6月
 日   月   火   水   木   金   土
 　    1    2    3    4    5    6
  7    8    9   10   11   12   13
 14   15   16   17   18   19   20
 21   22   23   24   25   26   27
 28   29   30

2020年7月
 日   月   火   水   木   金   土
 　   　   　    1    2    3    4
  5    6    7    8    9   10   11
 12   13   14   15   16   17   18
 19   20   21   22   23   24   25
 26   27   28   29   30   31

2020年8月
 日   月   火   水   木   金   土
 　   　   　   　   　   　    1
  2    3    4    5    6    7    8
  9   10   11   12   13   14   15
 16   17   18   19   20   21   22
 23   24   25   26   27   28   29
 30   31

2020年9月
 日   月   火   水   木   金   土
 　   　    1    2    3    4    5
  6    7    8    9   10   11   12
 13   14   15   16   17   18   19
 20   21   22   23   24   25   26
 27   28   29   30

2020年10月
 日   月   火   水   木   金   土
 　   　   　   　    1    2    3
  4    5    6    7    8    9   10
 11   12   13   14   15   16   17
 18   19   20   21   22   23   24
 25   26   27   28   29   30   31


2020年11月
 日   月   火   水   木   金   土
  1    2    3    4    5    6    7
  8    9   10   11   12   13   14
 15   16   17   18   19   20   21
 22   23   24   25   26   27   28
 29   30

2020年12月
 日   月   火   水   木   金   土
 　   　    1    2    3    4    5
  6    7    8    9   10   11   12
 13   14   15   16   17   18   19
 20   21   22   23   24   25   26
 27   28   29   30   31

 */
package javaPractice.exercise;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Exercise24Ans {
    private static int year;
    private static int month;
    private static int lastDay;
    private static int dayWeek;
    private static String[] dayWeekArr;

    public static void main(String[] args) {
        InputLoopIntExercise24 inLoop = new InputLoopIntExercise24();
        int[] questAns = inLoop.inputLoop();
        year = questAns[0];

        dayWeekArr = new String[] {
                "日","月","火","水","木","金","土"
        };

        for (month = 1; month <= 12; month++) {
            LocalDate ldtFirst = LocalDate.of(year, month, 1);

            //月1 火2・・日7 / 1-7
            dayWeek = ldtFirst.get(ChronoField.DAY_OF_WEEK);
            if (dayWeek == 7) {
                dayWeek = 0;
            }

            lastDay = ldtFirst.lengthOfMonth();

            printCalendar();
            System.out.println();
        }//for month

    }//main()

    private static void printCalendar() {
        StringBuilder bld = new StringBuilder();
        bld.append(String.format("%d年%d月\n", year, month));

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

            bld.append(String.format(" %2d ", i)).append(" ");

            if((i + dayWeek) % 7 == 0) {
                bld.append("\n");
            }//if
        }//for 日付

        System.out.println(bld.toString());

    }//printCalendar

}//class


class InputLoopIntExercise24 {
    private String fixed = "を入力してください。";
    private final String[] quest = new String[] {"年"};
    private int questNum = quest.length;
    int lastDay;
    private int[] rangeLow = new int[]{1};
    private int[] rangeHigh = new int[]{3000};

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
【註】
ここでは入力数が１つのため、InputLoopを配列にする必要はないが
汎用のクラスとしてInputLoopクラスを作ったので
length == 1 の配列として利用する。
*/