
/*
 * 制作：上野
 * 制作開始：R2/10/09
 * 期限：席替えまで
 * 内容：席替えアプリ
 *
 * 人数入力後、席かぶりがないように指定。
 * 対応する席順で表示させる。
 */
package javaPractice.chapter04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SeatingPlan {

	public static void main(String[] args) {
		List<String> name = new ArrayList<String>();

		name = Arrays.asList("|岩田|", "|植田|", "|上野|", "|岡本|", "|川島|", "|澤井|", "|住田|", "|辻谷|", "|道満|",
				"|中山|", "|西尾|", "|畑中|", "|東出|", "|松本|", "|山本|", "|米田|", "|臨泉|");
		System.out.println(name);
		Collections.shuffle(name);

		System.out.println(
				"\n\n"
						+ "|大鹿|" + "| 空 |" + name.get(0) + name.get(1) + name.get(2) + name.get(3) + "\n"
						+ name.get(4) + name.get(5) + name.get(6) + name.get(7) + name.get(8) + name.get(9) + "\n"
						+ name.get(10) + name.get(11) + name.get(12) + name.get(13) + name.get(14) + name.get(15) + "\n"
						+ "|　　||　　||　　||　　|" + name.get(16));



	}

}
/*[|岩田|, |植田|, |上野|, |岡本|, |川島|, |澤井|, |住田|, |辻谷|, |道満|, |中山|, |西尾|, |畑中|, |東出|, |松本|, |山本|, |米田|, |臨泉|]


|大鹿|| 空 ||辻谷||岡本||松本||植田|
|山本||澤井||臨泉||東出||住田||岩田|
|西尾||道満||畑中||中山||米田||川島|
|　　||　　||　　||　　||上野|

◆Shuffle()だけだと前席と被る可能性もある(例: 植田さん)
|大鹿|| 空 ||松本||住田||道満||澤井|
|西尾||上野||山本||辻谷||畑中||植田|
|岩田||東出||米田||臨泉||川島||中山|
|　　||　　||　　||　　||岡本|

*/