package javaPractice.chapter05;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Practice5_1 {
	private String name;
	private int age;
	private double tall;
	private String zodiac;

	public Practice5_1(String name, int age, double tall, String zodiac) {
		this.name = name;
		this.age = age;
		this.tall = tall;
		this.zodiac = zodiac;
	}

	public static void main(String[] args) {
		//---- 自己紹介 ----
		Practice5_1 cls = new Practice5_1("しか", 47, 168.0, "子");
		String who = "私";
		introduceOneself(cls, who);
		System.out.println();//改行

		//---- user input ----
		Scanner scn = new Scanner(System.in);

		try {
			System.out.print("あなたの名前は？");
			String name = scn.nextLine();

			System.out.print("あなたの年齢は？");
			int age = scn.nextInt();

			System.out.print("あなたの身長は？(小数も可)");
			double tall = scn.nextDouble();

			System.out.print("あなたの干支は？(漢字１文字で)");
			String zodiac = scn.next();
			System.out.println();

			//---- user instance ----
			Practice5_1 cls2 = new Practice5_1(name, age, tall, zodiac);
			who = "あなた";
			introduceOneself(cls2, who);

		} catch (InputMismatchException e) {
			System.out.println("インプットの型エラーです。");
			System.exit(0);
		} finally {
			scn.close();
		}

	}//main()

	private static void introduceOneself(Practice5_1 cls, String who) {
		StringBuilder bld = new StringBuilder();
		bld.append(String.format("%sの名前は %s です。\n", who, cls.name));
		bld.append(String.format("%sの年齢は %d です。\n",who, cls.age));
		bld.append(String.format("%sの身長は %.1f です。\n", who, cls.tall));
		bld.append(String.format("%sの干支は %s です。\n", who, cls.zodiac));

		System.out.println(bld.toString());
	}//introduceOneself();

}//class

/*
私の名前は しか です。
私の年齢は 47 です。
私の身長は 168.0 です。
私の干支は 子 です。


あなたの名前は？shika
あなたの年齢は？24
あなたの身長は？(小数も可)148.5
あなたの干支は？(漢字１文字で)
子
あなたの名前は shika です。
あなたの年齢は 24 です。
あなたの身長は 148.5 です。
あなたの干支は 子 です。
*/