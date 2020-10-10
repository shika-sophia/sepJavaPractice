/**
 * @title javaPractice / chapter04 / ChangeSeat
 * @title ～席替えプログラム～
 * @content 配列, List
 * @author shika
 * @date 2020-10-08
 */

package javaPractice.chapter04;

import java.util.ArrayList;
import java.util.List;
/*
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
            seatArray[i] = -1;
        }

        //座席のランダムくじ
        Random rdm = new Random();
        int dice;

        //人数分のランダムくじ開始
        for (int i = 0; i < nameArray.length; i++) {

            //かぶっている座席がなくなるまでループ
            loop:
            while (true) {
                dice = rdm.nextInt(24);


                for(int j = 0; j < nameArray.length; j++) {
                    //diceが他の座席と かぶったら、ランダムやり直し
                    if (dice == seatArray[j]) {
                        continue loop;
                    }
                }//for j

                //座席を登録してループ終了
                seatArray[i] = dice;
                break loop;
            }//while
        }//for i

        for (int i = 0; i < nameArray.length; i++) {
            System.out.printf("%s さんの座席は %2d です。\n",
                nameArray[i], seatArray[i]);
        }
    }//main()

}//class
*/
/*
岩田 さんの座席は 16 です。
植田 さんの座席は 14 です。
畑中 さんの座席は  8 です。
川島 さんの座席は  5 です。
澤井 さんの座席は 23 です。
住田 さんの座席は  4 です。
米田 さんの座席は  7 です。
臨泉 さんの座席は 19 です。
松本 さんの座席は  3 です。
道満 さんの座席は  0 です。
田中 さんの座席は 11 です。
西尾 さんの座席は 18 です。
山本 さんの座席は 12 です。
岡本 さんの座席は  6 です。
辻谷 さんの座席は 13 です。
上野 さんの座席は  1 です。
東出 さんの座席は  2 です。
中山 さんの座席は  9 です。
大鹿 さんの座席は 20 です。

【考察】
ランダムに かぶりなく席替えできたけど、
テーブルごとの偏りはチェックしていないので
平均的に３つのテーブルに振り分ける方法を考える必要がある。
*/
import java.util.Random;

public class ChangeSeat {
    static int length;
    static List<Integer> tableA;
    static List<Integer> tableB;
    static List<Integer> tableC;

    public static void main(String[] args) {
        //名前の配列(敬称略)
        String[] nameArray = new String[] {
            "岩田","植田", "畑中", "川島", "澤井", "住田", "米田",
            "臨泉", "松本", "道満", "田中", "西尾", "山本",
            "岡本", "辻谷","上野", "東出", "中山", "大鹿"
        };

        length = nameArray.length;

        //座席の配列と初期化(現在の席と被らないようにするための初期値)
        int[] seatArray = new int[] {
            1, 2, 3, 5, 6, 7, 8,
            9, 10, 12, 13, 14, 16,
            17, 18, 19, 21, 22, 20
        };

        //---- 座席のランダムくじ ----
        //平均ループ
        average:
        while(true) {
            //座席決定
            seatArray = decideSeat(seatArray);

            //テーブル振り分け
            separateTable(seatArray);

            //テーブル平均チェック
            boolean isAverage = checkTable();

            if (isAverage) {
                break average;
            }
        }//while

        //座席表示
        for (int i = 0; i < length; i++) {
            System.out.printf("%s さんの座席は %2d です。\n",
                nameArray[i], seatArray[i]);
        }
    }//main()


    private static int[] decideSeat(int[] seatArray) {
        //座席のランダムくじ
        Random rdm = new Random();
        int dice;

        //人数分のランダムくじ開始
        for (int i = 0; i < length; i++) {

            //かぶっている座席がなくなるまでループ
            loop:
            while (true) {
                dice = rdm.nextInt(23);

                for(int j = 0; j < length; j++) {
                    //diceが他の座席と かぶったら、ランダムやり直し
                    if (dice == seatArray[j]) {
                        continue loop;
                    }
                }//for j

                //座席を登録してループ終了
                seatArray[i] = dice;
                break loop;

            }//while
        }//for i

        return seatArray;
    }//decideSeat()


    private static void separateTable(int[] seatArray) {
        //テーブル振り分け
        tableA = new ArrayList<>();
        tableB = new ArrayList<>();
        tableC = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            //各テーブルに振り分け
            if (0 <= seatArray[i] && seatArray[i] <= 7) {
                tableA.add(seatArray[i]);

            } else if (8 <= seatArray[i] && seatArray[i] <= 15) {
                tableB.add(seatArray[i]);

            } else if (16 <= seatArray[i] && seatArray[i] <= 23) {
                tableC.add(seatArray[i]);
            }
        }//for i

    }//separateTable()


    private static boolean checkTable() {
        boolean isAverage = false;
        int tableAverage = length / 3;

        //テーブル平均より２より離れていたら false
        //Math.abs():絶対値を取るメソッド
        if (Math.abs(tableA.size() - tableAverage) > 1
            || Math.abs(tableB.size() - tableAverage) > 1
            || Math.abs(tableC.size() - tableAverage) > 1
        ) {
            isAverage = false;

        } else {
            isAverage = true;

            //テスト表示
            System.out.println("tableA.size(): " + tableA.size());
            System.out.println("tableB.size(): " + tableB.size());
            System.out.println("tableC.size(): " + tableC.size());
        }

        return isAverage;
    }//checkAverage()

}//class

/*
tableA.size(): 6
tableB.size(): 6
tableC.size(): 7
岩田 さんの座席は 16 です。
植田 さんの座席は 10 です。
畑中 さんの座席は 12 です。
川島 さんの座席は  2 です。
澤井 さんの座席は 13 です。
住田 さんの座席は 15 です。
米田 さんの座席は 23 です。
臨泉 さんの座席は 11 です。
松本 さんの座席は 14 です。
道満 さんの座席は 17 です。
田中 さんの座席は 18 です。
西尾 さんの座席は 22 です。
山本 さんの座席は  7 です。
岡本 さんの座席は  1 です。
辻谷 さんの座席は  6 です。
上野 さんの座席は 21 です。
東出 さんの座席は  3 です。
中山 さんの座席は  5 です。
大鹿 さんの座席は 19 です。

【考察】
平均化できた！
tableA.size(): 6
tableB.size(): 6
tableC.size(): 7

tableA.size(): 7
tableB.size(): 7
tableC.size(): 5

こりゃあかん→絶対値を取らな
tableA.size(): 8
tableB.size(): 8
tableC.size(): 3

あと追加するなら、
・元テーブルの人と かぶらないようにする。
・Swingで表示する。
・座席表で表示する。
*/