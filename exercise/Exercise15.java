/**
 * @title javaPractice / exercise / Exercise15
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
 * インスタンスは
 *   ArrayList<String> list = new ArrayList<>();
 *   ArrayList<Integer> list = new ArrayList<>(); intではなく Integerを用いることに注意
 *
 * ・終端に値を代入      list.add(値)
 * ・indexを指定して代入 list.add(index, 値)
 * ・indexの値を置換     list.set(index, 値)
 * ・値を取得            list.get(index)
 * ・リストの要素数      list.size()
 *
 * メソッドと使い方を覚えてしまえばそれほど難しくはないです。
 * むしろ配列より優先的に Listを使うほうが値の追加に強いコードになります。
 *
 *
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Exercise15{

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        final int BOUND = 100;
        
    }//main()

}//class

/*
◇実行結果
素数 73 を見つけました。
調べた数の記録[72, 54, 42, 69, 94, 22, 64, 96, 76, 73]
*/