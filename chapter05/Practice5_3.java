/**
 * @title javaPractice / chapter05 / Practice5-2, 5-3
 * @content
 * @author shika
 * @date 2020-10-07
 */

package javaPractice.chapter05;
/*
public class Practice5_2 {

	public static void main(String[] args) {
		String address = "xxxx@sozosha.com";
		String title = "タイトル";
		String text = "メール本文";

		email(address, title, text);

	}//main()

	private static void email(String address, String title, String text) {
		if (title.equals("") || title == null) {
			title = "無題";
		}

		StringBuilder bld = new StringBuilder();
		bld.append(String.format("%sにメール送信しました。\n", address));
		bld.append(String.format("件名: %s\n", title));
		bld.append(String.format("本文: %s\n", text));

		System.out.println(bld.toString());
	}//email()

}//class

 */
public class Practice5_3 {

	public static void main(String[] args) {
		String address = "xxxx@sozosha.com";
		String title = "";
		String text = "メール本文";

		email(address, title, text);

	}//main()

	private static void email(String address, String title, String text) {
		if (title.equals("") || title == null) {
			title = "無題";
		}

		StringBuilder bld = new StringBuilder();
		bld.append(String.format("%sにメール送信しました。\n", address));
		bld.append(String.format("件名: %s\n", title));
		bld.append(String.format("本文: %s\n", text));

		System.out.println(bld.toString());
	}//email()

}//class

/*
xxxx@sozosha.comにメール送信しました。
件名: 無題
本文: メール本文
*/