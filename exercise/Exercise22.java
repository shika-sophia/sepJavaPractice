/**
 * @title javaPractice / exercise / Exercise22
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Exercise22 {

    public static void main(String[] args) {
        
    }//main()
}//class


