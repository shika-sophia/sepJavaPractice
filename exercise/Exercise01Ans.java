/**
 * @title javaPractice / exercise / Exercise01Ans
 * @content int, String結合「+」演算子, System.out.println()
 * @author shika
 * @date 2020-10-19
 */
/*
 ◆Exercise01
 問 x = 2, y = 3 のとき、以下の実行結果となるプログラムを作成せよ。

 ◇実行結果
 x: 2
 y: 3
 x + y = 5
 */
package javaPractice.exercise;

public class Exercise01Ans {

	public static void main(String[] args) {
		int x = 2;
		int y = 3;

		System.out.println("x: " + x);
		System.out.println("y: " + y);
		System.out.println("x + y = " + (x + y));//← x+yの()を忘れずに

	}//main()

}//class

/*
◇実行結果
x: 2
y: 3
x + y = 5
*/