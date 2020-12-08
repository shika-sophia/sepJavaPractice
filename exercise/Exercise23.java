/**
 * @title javaPractice / exercise / Exercise23
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

public class Exercise23 {
    

    public static void main(String[] args) {
        
    }//main()

}//class

