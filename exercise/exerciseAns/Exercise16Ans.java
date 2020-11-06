/**
 * @title javaPractice / exercise / Exercise16Ans
 * @content field, constracter, instance
 * @author shika
 * @date 2020-11-06
 */
/*
 * ◆Exercise16
 * Zakクラスというものを以下のように自己定義した。
 * main()で 名称: ザク, 火力1200, 装甲2800の Zakインスタンスを生成し、
 * Zakクラスのフィールドにアクセスし値を取得、下記のように表示せよ。
 *
 * ただし、表示の際に固定値を書き込むのは なしです。
 *
 * ◇実行結果
 * 名称: ザク
 * 火力: 1200
 * 装甲: 2800
 */
package javaPractice.exercise;

public class Exercise16Ans {

    public static void main(String[] args) {
        Zak zak = new Zak("ザク", 1200, 2800);

        System.out.println("名称: " + zak.name);
        System.out.println("火力: " + zak.power);
        System.out.println("装甲: " + zak.armor);

    }//main()

}//class Exercise16Ans


class Zak{
    String name;//名称
    int power;  //火力(攻撃力)
    int armor;  //装甲(防御力)

    public Zak() {}

    public Zak(String name,int power, int armor) {
        this.name = name;
        this.power = power;
        this.armor = armor;
    }

    public void attack() {

    }//attack()

    public void defense() {

    }//defense()

}//class Zak

/*
◇実行結果

名称: ザク
火力: 1200
装甲: 2800
 */