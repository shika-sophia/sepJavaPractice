/**
 * @title javaPractice / exercise / Exercise15Ans
 * @content List, ArrayList, while(true), Random
 * @author shika
 * @date 2020-11-05
 */
/*
 * ◆Exercise15
 * 0～99の乱数を生成し、その数が素数かどうかを確認し
 * 0, 1 以外の素数が出るまで乱数を作り続ける。
 * 途中、どの数を調べたかを記録し、最後に表示する。
 *
 * ◇実行結果
 * 素数 73 を見つけました。
 * 調べた数の記録[72, 54, 42, 69, 94, 22, 64, 96, 76, 73]
 *
 * 〔ヒント〕
 *  ＊素数: 1とその数自身しか約数を持たない数。
 *          2～(その数-1)で割って割り切れる数があれば素数ではない。
 *
 * 〔ヒント〕
 * ＊ArrayList: 可変配列
 *     配列の場合は あらかじめ要素の個数を宣言してからでないと使えないが、
 *     この問題は、開始時点で いくつの数値を格納すればいいか分からないので、
 *     そういうときに要素をいくつでも追加できる ArrayListを使う。
 *
 * 《Arraylistの使い方》
 * ・終端に値を代入      add(値)
 * ・indexを指定して代入 add(index, 値)
 * ・値を取得            get(index)
 * ・リストの要素数      list.size()
 *
 * メソッドは違っても配列と同じように使える。
 *
 * インスタンスは
 *   ArrayList<String> list = new ArrayList<>();
 *   ArrayList<Integer> list = new ArrayList<>();
 *
 *   intではなく Integerを用いることに注意
 */
package javaPractice.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Exercise15Ans {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        final int BOUND = 100;
        int num;
        loop:
        while(true) {
            //0～(BOUND - 1)までのランダム
            Random rdm = new Random();
            num = rdm.nextInt(BOUND);

            // 0, 1を除外
            if (num == 0 || num == 1) {
                continue loop;
            } else {
                //出た数をListに登録
                list.add(num);
            }

            //素数かどうかの判定(1とその数以外の約数を持たない)
            int count = 0;
            for(int i = 2; i < num; i++) {
                if(num % i == 0) {
                    count++;
                }
            }//for i

            if (count == 0) {
                break loop;
            }
        }//while loop

        System.out.printf("素数 %d を見つけました。\n", num);
        System.out.println("調べた数の記録" + list);

    }//main()

}//class

/*
◇実行結果
素数 73 を見つけました。
調べた数の記録[72, 54, 42, 69, 94, 22, 64, 96, 76, 73]
*/