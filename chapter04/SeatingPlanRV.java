
/**
 * @title javaPractice / chapter04 / SeatingPlanRV
 * @author 上野
 * @author forked from Ueno-san at 2020-10-16 / Revision wrote by shika
 * @date 2020-10-16
 *
 * @RDD Additional RDD / 追加の要件定義
 * ・大鹿を固定位置にする(旧山本さんの席)
 * ・旧座席と新座席が かぶらないようにする。
 *  (シャローコピー[浅いコピー]に注意:
 *     配列やリストの代入は参照のコピーになるので要素ごとコピー(ディープコピー)する)
 * ・座席表をfor文で自動生成する。
 *
 * ◆Shuffle()だけだと前席と被る可能性もある(例: 植田さん)
 *|大鹿|| 空 ||松本||住田||道満||澤井|
 *|西尾||上野||山本||辻谷||畑中||植田|
 *|岩田||東出||米田||臨泉||川島||中山|
 *|　　||　　||　　||　　||岡本|

 */
package javaPractice.chapter04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/*
//###### All Shuflle checked previous seat Version ######
//旧座席との被らないようチェックした 全シャッフルVersion
public class SeatingPlanRV {

	public static void main(String[] args) {
		List<String> name = new ArrayList<String>();

		//初期値が出席番号順？になっているので現在の座席に改変
		//name = Arrays.asList("|岩田|", "|植田|", "|上野|", "|岡本|", "|川島|", "|澤井|", "|住田|", "|辻谷|", "|道満|",
		//		"|中山|", "|西尾|", "|畑中|", "|東出|", "|松本|", "|山本|", "|米田|", "|臨泉|");
		//System.out.println(name);

		name = Arrays.asList("大鹿","岡本","米田","臨泉","川島","岩田",
				             "東出","辻谷","西尾","松本","澤井","植田",
				             "中山","上野","山本","道満","住田","畑中");

		//旧座席のコピー
		List<String> nameCopy = new ArrayList<>();
		for (String namebit : name) {
			nameCopy.add(namebit);
		}

		//シャッフルのループ
		boolean checked = false;
		loop:
		do {
			Collections.shuffle(name);

			//旧座席と被っていないかチェック
			for (int i = 0; i < name.size(); i++) {
				//旧座席と同じ位置に、同じ名前があったらShuffle()やり直し
				if (name.get(i).equals(nameCopy.get(i))) {
					continue loop;
				}
			}//for i
			checked = true;

		} while (checked);

		//for文で座席表を自動生成
		//今回は18人なので 3人×6列

		//◆StringBuilder
		//「+」演算子は「"あ"+"い" -> "あい"」なら３つのStringオブジェクトを生成する。
		//次々と「+」演算子で繋げると、その度にStringオブジェクトを生成していく。
		//なのでたくさん繋げるときは StringBuilder()を使うとメモリへの負担がわずかながら改善する。
		StringBuilder bld = new StringBuilder();
		bld.append("　　　　　　先生□\n");

		for(int i = 0; i < name.size(); i++) {
			bld.append("|").append(name.get(i)).append("|");
			if((i + 1) % 6 == 0) {
				bld.append("\n");
			}
		}//for

		System.out.println(bld.toString());
	}//main()

}//class
*/
/*
//====== 全シャッフルVersion 実行結果 ======
　　　　　　先生□
|上野||澤井||松本||山本||川島||臨泉|
|米田||中山||畑中||植田||岩田||辻谷|
|東出||西尾||道満||岡本||住田||大鹿|
 */
//###### fix '大鹿' Version ######
public class SeatingPlanRV {

	public static void main(String[] args) {
		List<String> name = new ArrayList<String>();

		name = Arrays.asList("大鹿","岡本","米田","臨泉","川島","岩田",
				             "東出","辻谷","西尾","松本","澤井","植田",
				             "中山","上野","山本","道満","住田","畑中");

		//旧座席のコピー
		List<String> nameCopy = new ArrayList<>();
		for (String namebit : name) {
			nameCopy.add(namebit);
		}

		//シャッフルのループ
		boolean checked = false;
		loop:
		do {
			Collections.shuffle(name);

			//'大鹿'の位置を探して、'大鹿'固定位置と入れ替え
			name = exchange(name);

			//旧座席と被っていないかチェック
			for (int i = 0; i < name.size(); i++) {
				//旧座席と同じ位置に、同じ名前があったらShuffle()やり直し
				if (name.get(i).equals(nameCopy.get(i))) {
					continue loop;
				}
			}//for i

			checked = true;

		} while (checked);

		//for文で座席表を自動生成
		//今回は18人なので 3人×6列
		StringBuilder bld = new StringBuilder();
		bld.append("　　　　　　先生□\n");

		for(int i = 0; i < name.size(); i++) {
			bld.append("|").append(name.get(i)).append("|");

			if((i + 1) % 6 == 0) {
				bld.append("\n");
			}
		}//for

		System.out.println(bld.toString());
	}//main()

	private static List<String> exchange(List<String> name) {
		//大鹿のいる席番を取得
		int target = name.indexOf("大鹿");

		//固定席14の名前を取得
		String targetName = name.get(14);

		//大鹿と入れ替え
		name.set(target, targetName);
		name.set(14, "大鹿");
		return name;
	}//exchange()

}//class
/*
　　　　　　先生□
|道満||米田||西尾||植田||辻谷||岩田|
|臨泉||山本||川島||東出||澤井||岡本|
|中山||畑中||大鹿||住田||松本||上野|

【考察】
たまにループにハマって返って来ないときあり。
原因究明すべし。
旧座席と同じだとシャッフルし直す。
大鹿のいた位置と座席番号14の位置を入れ替えるので
大鹿が座席番号14のひとの旧座席にシャッフル配置されると再シャッフル。

ループにハマりそうなとこがないんだが・・
*/