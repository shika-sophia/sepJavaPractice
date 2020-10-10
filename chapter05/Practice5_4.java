/**
 * @title javaPractice / chapter05 / Practice5-4
 * @content Scanner, BigDecimal
 *     doubleの計算は誤差の出る可能性があるので、BigDecimalクラスを使う
 * @author shika
 * @date 2020-10-09
 */

package javaPractice.chapter05;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
//###### Primitive Version / テキストの練習問題そのまま ######
public class Practice5_4 {

	public static void main(String[] args) {
		//---- 三角形 ----
		double height = 10.0;
		double bottom = 5.0;

		double valueTri = calcTriangleArea(height, bottom);
		System.out.println("三角形の面積: " + valueTri);

		//---- 円の面積 ----
		double radius = 5.0;
		double valueCir = calcCircleArea(radius);
		System.out.println("円の面積: " + valueCir);
	}//main()


	private static double calcTriangleArea(double height, double bottom) {

		return height * bottom / 2;
	}

	private static double calcCircleArea(double radius) {

		return radius * radius * 3.14;
	}

}//class
*/

public class Practice5_4 {
	//インスタンスなしで使えるように staticフィールド・staticメソッド
	private static Scanner scn;
	private static String[] angle = {"三角形","四角形","台形","円"};
	private static int angleIndex;
	private static BigDecimal width;
	private static BigDecimal height;
	private static BigDecimal upper;
	private static BigDecimal value;
	//doubleの計算は誤差の出る可能性があるので、BigDecimalクラスを使う

	public static void main(String[] args) {
		//メニュー表示
		StringBuilder bld = new StringBuilder();
		bld.append("[メニュー] 番号を入力してください。\n");

		for(int i = 0; i < angle.length; i++) {
			bld.append(String.format("  %d:%sの面積\n", i+1, angle[i]));
		}//for i

		System.out.println(bld.toString());

		//入力準備。Scannerは他メソッドでも使うのでフィールド化
		scn = new Scanner(System.in);
		String menu = scn.next(); //Stringにしたのは、InputMismatchExceptionを避けるため

		selectMenu(menu);

		//面積の表示
		System.out.printf("%sの面積は", angle[angleIndex]);
		System.out.println(value + "です。");
	}//main()

	private static void selectMenu(String menu) {
		angleIndex = (Integer.parseInt(menu) - 1);
		inputFormat();

		switch (menu) {
		case "1":
		case "１"://三角形: Triangle
			calcTriangleArea();
			break;

		case "2":
		case "２"://四角形: Rectangle
			calcRectangleArea();
			break;

		case "3":
		case "３"://台形: Trapezoid
			calcTrapezoidArea();
			break;

		case "4":
		case "４"://円: Circle
			calcCircleArea();
			break;

		default:
			System.out.println("1～4の数字で入力してください。");
		}//switch
	}//selectMenu()

	private static void inputFormat() {
		try {
			//円
			if (angleIndex == 3) {
				System.out.printf("%sの半径を入力してください。\n",
					angle[angleIndex]);
				width = new BigDecimal(scn.nextDouble());
				return;//呼び出し元に制御を戻す
			}

			//三角形と四角形
			System.out.printf("%sの底辺(横の長さ)を入力してください。\n",
				angle[angleIndex]);
			width = new BigDecimal(scn.nextInt());

			System.out.printf("%sの高さ(縦の長さ)を入力してください。\n",
				angle[angleIndex]);
			height = new BigDecimal(scn.nextInt());

			//台形の質問追加
			if (angleIndex == 2) {
				System.out.printf("%sの上底を入力してください。\n",
					angle[angleIndex]);
				upper = new BigDecimal(scn.nextInt());
			}

		} catch (InputMismatchException e ) {
			System.out.println("整数で入力してください");
		}

	}//inputFormat()


	private static void calcTriangleArea(){
		//width * heigt /2
		final BigDecimal half = new BigDecimal("2");
		value = width.multiply(height).divide(half) ;
	}//calcTriangleArea()


	private static void calcRectangleArea() {
		//width * height
		value = width.multiply(height);
	}//calcRectangleArea()


	private static void calcTrapezoidArea() {
		//(width + height) * height / 2
		final BigDecimal half = new BigDecimal("2");
		value = width.add(upper).multiply(height).divide(half);
	}//calcTrapezoidArea()

	private static void calcCircleArea() {
		//radius * radius * 3.14
		final BigDecimal PI = new BigDecimal("3.14");
		value = width.multiply(width).multiply(PI);
	}
}//class
/*
[メニュー] 番号を入力してください。
  1:三角形の面積
  2:四角形の面積
  3:台形の面積
  4:円の面積

1
三角形の底辺(横の長さ)を入力してください。
10
三角形の高さ(縦の長さ)を入力してください。
20
三角形の面積は100です。

2
四角形の底辺(横の長さ)を入力してください。
10
四角形の高さ(縦の長さ)を入力してください。
10
四角形の面積は100です。

3
台形の底辺(横の長さ)を入力してください。
10
台形の高さ(縦の長さ)を入力してください。
15
台形の上底を入力してください。
6
台形の面積は120です。


4
円の半径を入力してください。
10
円の面積は314.00です。

*/