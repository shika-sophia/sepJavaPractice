package javaPractice.chapter04;

import java.util.Random;

public class ChangeSeat {

	public static void main(String[] args) {
		//名前の配列(敬称略)
		String[] nameArray = new String[] {
			"岩田","植田", "畑中", "川島", "澤井", "住田", "米田",
			"臨泉", "松本", "道満", "田中", "西尾", "山本",
			"岡本", "辻谷","上野", "東出", "中山", "大鹿"
		};

		//座席の配列と初期化
		int[] seatArray = new int[nameArray.length];

		for (int i = 0; i < nameArray.length; i++) {
			seatArray[i] = 0;
		}

		//座席のランダムくじ
		Random rdm = new Random();
		int dice = -1;

		//人数分のランダムくじ開始
		for (int i = 0; i < nameArray.length; i++) {

			//かぶっている座席がなくなるまでループ
			loop:
			while (true) {
				dice = rdm.nextInt(19);

				for(int j = 0; j < nameArray.length; j++) {
					//diceが他の座席と かぶったら、ランダムやり直し
					if (dice == seatArray[j]) {
						continue loop;
					}

					//j が最後まで行ったら 座席を登録してループ終了
					if (j == nameArray.length - 1) {
						seatArray[i] = dice;
						break loop;
					}
				}//for j
			}//while
		}//for i

		for (int i = 0; i < nameArray.length; i++) {
			System.out.printf("%s さんの座席は %d です。",
				nameArray[i], seatArray[i]);
		}
	}//main()

}//class
