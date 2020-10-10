/**
 * @title javaPractice / chapter04 / ChangeSeatShuffle
 * @title ～席替えプログラム～
 * @content List, Collections.shuffle()
 * @Thanks Ueno-san, SepJava2020
 * @author shika
 * @date 2020-10-08
 */

package javaPractice.chapter04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChangeSeatShuffle {

    public static void main(String[] args) {
        List<String> nameList = new ArrayList<>(Arrays.asList(
            "岩田","植田", "畑中", "川島", "澤井", "住田", "米田",
            "臨泉", "松本", "道満", "田中", "西尾", "山本",
            "岡本", "辻谷","上野", "東出", "中山", "大鹿"
        ));

        Collections.shuffle(nameList);

        int i = 1;
        for(String nameBit : nameList) {
            System.out.printf("%2d: %sさん\n", i, nameBit);
            i++;
        }
    }//main()

}//class
/*
 1: 臨泉さん
 2: 岩田さん
 3: 植田さん
 4: 中山さん
 5: 大鹿さん
 6: 岡本さん
 7: 住田さん
 8: 山本さん
 9: 上野さん
10: 東出さん
11: 澤井さん
12: 松本さん
13: 西尾さん
14: 辻谷さん
15: 川島さん
16: 田中さん
17: 畑中さん
18: 米田さん
19: 道満さん

【考察】
Collections.shuffle()を使うと驚くほどシンプルにできた。

このままだと
・前の座席との かぶり判定をしていない。
・座席番号の1～19に、詰め詰めになる。
・テーブルごとの人数平均は取れていない。
など

まだまだ改良の余地はあるが、あとは人間のほうで適当にバラけて。
(↑結局 それかいっ)
 */
