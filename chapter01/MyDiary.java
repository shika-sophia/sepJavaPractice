/**
 * @title javaPractice / chapter01 / code1-1 (p036) => MyDiary
 * @content StringBuilder
 * @author shika
 * @date 2020-10-05
 */
/*
 * 《解説》
 *     Stringクラスの"～"で囲んだ部分は、"～"の数だけオブジェクトが生成される。
 *     そしてStringの結合に用いる「+」演算子は、
 *     「"あ" + "い" -> "あい"」 の 3つ分のオブジェクトが生成される。
 *
 *     StringBuilderは、「+」演算子を使わずに文字列を結合させる方法。
 */
package javaPractice.chapter01;

public class MyDiary {

    public static void main(String[] args) {
        StringBuilder bld = new StringBuilder();

        bld.append("RPG: スッキリ魔王征伐\n");
        bld.append("Ver.0.1 by 湊\n");
        bld.append("＜ただいま鋭意学習・制作中＞\n");
        bld.append("プログラム終了\n");

        System.out.println(bld.toString());
    }//main()

}//class

/*
RPG: スッキリ魔王征伐
Ver.0.1 by 湊
＜ただいま鋭意学習・制作中＞
プログラム終了
 */
