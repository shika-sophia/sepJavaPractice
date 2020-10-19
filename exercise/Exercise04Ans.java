/**
 * @title javaPractice / exercise / Exercise04Ans
 * @content 配列, for, printf
 * @author shika
 * @date 2020-10-19
 */
/*
◆Exercise04 [応用]
  前問 Exercise03において「計算結果」と表示していた部分を
  選択された計算方法に合わせて「足し算」「引き算」「掛け算」「割り算」と表示したい。
  しかし、同じことを何度も書くのはイヤなので、
  配列を使うと「足し算」「引き算」「掛け算」「割り算」は一度書けば済むという。

  Exercise03をもとにして、上記の要件を満たすように修正せよ。
  ついでに、Scanner入力時のアナウンス
  「[1]足し算, [2]引き算, [3]掛け算, [4]割り算」も配列とfor文を用いて自動生成せよ。

〔ヒント〕
◆printf():
  System.out.printf("[%d] %s \n", i, str);
  printf()というメソッドは"～"に書式を指示し、「,」以下に変数をおくと
  表示するときに変数の値を代入して表示してくれる。
  「%d」: int型を代入できる。
  「%s」: String型を代入できる。
  「\n」: 改行

◇実行結果
xの値を入力してください。4
yの値を入力してください。3
計算方法を数字で選択してください。
[1] 足し算, [2] 引き算, [3] 掛け算, [4] 割り算, 3
x = 4, y = 3: 掛け算の計算結果は 12
 */
package javaPractice.exercise;

import java.util.Scanner;

public class Exercise04Ans {

	public static void main(String[] args) {
		String[] calcStr = {"足し算","引き算","掛け算","割り算"};

		Scanner scn = new Scanner(System.in);
		System.out.print("xの値を入力してください。");
        int x = scn.nextInt();

        System.out.print("yの値を入力してください。");
        int y = scn.nextInt();

        System.out.println("計算方法を数字で選択してください。");

        for (int i = 0; i < calcStr.length; i++) {
        	System.out.printf("[%d] %s, ", (i+1), calcStr[i]);
        }//for

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

        System.out.printf("x = %d, y = %d: %sの計算結果は %d ",
        	x, y, calcStr[calc-1], result);
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
◇実行結果
xの値を入力してください。4
yの値を入力してください。3
計算方法を数字で選択してください。
[1] 足し算, [2] 引き算, [3] 掛け算, [4] 割り算, 3
x = 4, y = 3: 掛け算の計算結果は 12

xの値を入力してください。7
yの値を入力してください。0
計算方法を数字で選択してください。
[1] 足し算, [2] 引き算, [3] 掛け算, [4] 割り算, 5
1～4の数字で入力してください。

yの値を入力してください。0
計算方法を数字で選択してください。
[1] 足し算, [2] 引き算, [3] 掛け算, [4] 割り算, 4
0で割ることはできません。
*/