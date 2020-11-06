/**
 * @title javaPractice / exercise / Exercise17
 * @content private, field, constracter, instance, getter, setter
 * @author shika
 * @date 2020-11-06
 */
/*
 * ◆Exercise17
 * 前問 Exercise16の Zakクラスのフィールドを privateにしたい。
 * (同名クラスは作れないので ZaKPrivateにする)
 *
 * すると、フィールドに直接アクセスできなくなり、Exercise16をそのままコピーして
 * ここに貼り付けるとコンパイルエラーの表示が出た。
 * どのように修正すれば、前問と同じ動作を実現できるか？
 * 両クラスとも加筆して修正せよ。
 *
 * ◇実行結果
 * 名称: ザク
 * 火力: 1200
 * 装甲: 2800
 */

public class Exercise17 {

    public static void main(String[] args) {
        ZakPrivate zak = new ZakPrivate("ザク", 1200, 2800);

        
    }//main()

}//class Exercise17Ans


class ZakPrivate{
    private String name;//名称
    private int power;  //火力(攻撃力)
    private int armor;  //装甲(防御力)

    public ZakPrivate() {}

    public ZakPrivate(String name,int power, int armor) {
        setName(name);
        setPower(power);
        setArmor(armor);
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