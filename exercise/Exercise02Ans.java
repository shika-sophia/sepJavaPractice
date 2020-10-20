/**
 * @title javaPractice / exercise / Exercise02Ans
 * @content import, Scanner, 四則演算
 * @author shika
 * @date 2020-10-19
 */
/*
◆Exercise02
Scannerクラスを importしインスタンスした上で、
x と y の値をユーザーに入力してもらい、四則演算の結果を以下のように表示せよ。

〔ヒント〕
Scanner scn = new Scanner(System.in); で Scannerクラスをインスタンス。
int x = scn.nextInt(); で nextInt()メソッドを使います。

「java.util.」部分は importするので記述不要です。
このように、importしてインスタンスを scnなど(各自で命名可)の変数に入れ、
Scannerクラスの各メソッドは、scn.nextInt()のようにインスタンス変数に「.」を付けて使用します。

こちらの書き方が一般的な慣例なので、
テキストの書き方「java.util.Scanner(System.in).nextInt()」は忘れましょう。
インスタンスもせずに Scannerクラスを使うと後々とても使いづらくなります。


◇実行結果
xの値を入力してください。７
yの値を入力してください。4
x + y = 11
x - y = 3
x * y = 28
x / y = 1

*/
package javaPractice.exercise;

import java.util.Scanner;

public class Exercise02Ans {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.print("xの値を入力してください。");
        int x = scn.nextInt();

        System.out.print("yの値を入力してください。");
        int y = scn.nextInt();

        System.out.println("x + y = " + (x + y));
        System.out.println("x - y = " + (x - y));
        System.out.println("x * y = " + (x * y));
        System.out.println("x / y = " + (x / y));

        scn.close();//これ今は気にしなくていいです。Scanner開いたら必ず閉じないといけなくて。
    }//main()

}//class
