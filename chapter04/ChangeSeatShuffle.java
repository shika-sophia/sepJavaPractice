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
import java.util.Random;

/*
//###### List Shuffle Version ######
public class ChangeSeatShuffle {

    public static void main(String[] args) {
        List<String> nameList = new ArrayList<>(Arrays.asList(
            "岩田","植田", "畑中", "川島", "澤井", "住田", "米田",
            "臨泉", "松本", "道満", "田中", "西尾", "山本",
            "岡本", "辻谷","上野", "東出", "中山", "大鹿",
            "(空席)","(空席)","(空席)"
        ));

        Collections.shuffle(nameList);

        int i = 1;
        for(String nameBit : nameList) {
            if(nameBit.equals("(空席)")) {
                System.out.printf("%2d: %s\n", i, nameBit);
            } else {
                System.out.printf("%2d: %sさん\n", i, nameBit);
            }

            i++;
        }//for nameBit
    }//main()

}//class
*/
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
・座席番号の1～19に、詰め詰めになる。→「(空席)」を３つ挿入
・テーブルごとの人数平均は取れていない。
など

まだまだ改良の余地はあるが、あとは人間のほうで適当にバラけて。
(↑結局 それかいっ)

 1: 畑中さん
 2: 住田さん
 3: 岡本さん
 4: 澤井さん
 5: 上野さん
 6: 西尾さん
 7: 辻谷さん
 8: 松本さん
 9: 川島さん
10: 大鹿さん
11: 田中さん
12: 中山さん
13: (空席)
14: 植田さん
15: (空席)
16: 米田さん
17: 東出さん
18: 道満さん
19: (空席)
20: 岩田さん
21: 山本さん
22: 臨泉さん
 */

//###### Table Avarage Version ######
public class ChangeSeatShuffle {

    public static void main(String[] args) {
        //名前のリスト(敬称略)
        List<String> nameList = new ArrayList<>(Arrays.asList(
            "岩田","植田", "畑中", "川島", "澤井", "住田", "米田",
            "臨泉", "松本", "道満", "田中", "西尾", "山本",
            "岡本", "辻谷","上野", "東出", "中山", "大鹿"
        ));

        //シャッフル
        Collections.shuffle(nameList);

       //table集計して平均を取るより「(空席)」をテーブル均等に挿入
       //空席を nameListに挿入
        nameList = insertEmpty(nameList);

        //座席を表示
        int i = 1;
        for(String nameBit : nameList) {
            if(nameBit.equals("(空席)")) {
                System.out.printf("%2d: %s\n", i, nameBit);
            } else {
                System.out.printf("%2d: %sさん\n", i, nameBit);
            }

            if(i % 8 == 0) {
                System.out.println("------------");
            }

            i++;

        }//for nameBit
    }//main()


    //空席を nameListに挿入
    private static List<String> insertEmpty(List<String> nameList) {
        Random rdm = new Random();
        int empty = 22 - nameList.size(); //空席数 = 全座席22 - 名前数

        for(int i = 1; i <= empty; i++) {
            int dice; //ランダム空席番号

            if (i % 3 == 0) {
                dice = rdm.nextInt(6);//tableCは 6人席
            } else {
                dice = rdm.nextInt(8);
            }

            switch(i % 3) {
            case 1://tableA
                nameList.add(dice, "(空席)");
                break;

            case 2://tableB
                nameList.add(dice + 8, "(空席)");
                break;

            case 0://tableC
                nameList.add(dice + 16, "(空席)");
                break;

            }//switch

        }//for

        return nameList;
    }//insertDice()

}//class
/*
 1: 臨泉さん
 2: 澤井さん
 3: 畑中さん
 4: (空席)
 5: 中山さん
 6: 松本さん
 7: 上野さん
 8: 住田さん
------------
 9: 田中さん
10: 辻谷さん
11: 山本さん
12: 岡本さん
13: 植田さん
14: 西尾さん
15: 米田さん
16: (空席)
------------
17: 大鹿さん
18: 川島さん
19: 道満さん
20: 岩田さん
21: (空席)
22: 東出さん

【考察】
テーブル平均もできた。
*/