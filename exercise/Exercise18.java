/**
 * @title javaPractice / exercise / Exercise18
 * @content extends, @Override, instanceArray, instanceof演算子
 * @author shika
 * @date 2020-11-06
 */
/*
 * ◆Exercise18
 * ZakPrivateクラスを進化させた ZakSpecialクラスを定義する。
 * ZakPrivateクラスは同パッケージに置けばアクセス可能なので、ここに書く必要はない。
 * また、すでに ZakPrivateクラスで記述した内容は記述不要である。
 *
 * しかし ZakSpecialクラスの attack(),defense()は ZakPrivateのそれらとはメソッド内容が違うので
 * アノテーション(=註釈)「@Override」を付ける。メソッドの中身は記述しなくてよい。
 *
 * ZakSpecialで新たに追加されるのは、フィールド int pilotと、メソッド void moveMoment()
 *
 * コンストラクタは今までの引数３つなら、基底クラス(superクラス)のフィールドに入れ、
 * pilot値に defaultで 2000を代入、引数４つのコンストラクタも用意する。
 *
 * 派生クラス(ZakSpecial)の setterは、power, armorには下限を設け
 * power 2000以下の場合は 2000に固定、2000より上の場合は その値を代入する。
 * armor 5000以下の場合は 5000に固定、5000より上の場合は その値を代入する。
 *
 * ZakPrivateを継承した ZakSpecialを完成させ、
 * main()で下記の表示に必要なインスタンスを行い、
 * 以下の実行結果になるよう別メソッド printZak()において 配列とfor文を用いて自動表示せよ。
 *
 * ◇実行結果
 * 名称: ザク1
 * 火力: 1200
 * 装甲: 2800
 *
 * 名称: ザク2
 * 火力: 1200
 * 装甲: 2800
 *
 * 名称: シャア専用
 * 火力: 4800
 * 装甲: 6400
 * 熟練: 12000
 */
package javaPractice.exercise;

public class Exercise18 {

    public static void main(String[] args) {
        

    	
        printZak(zak1, zak2, zakSpecial);
    }//main()

    private static void printZak(ZakPrivate zak1, ZakPrivate zak2, ZakSpecial zakSpecial) {
        

    	
        for(int i = 0; i < zakArray.length; i++) {
                       
        }//for zakArray
    }//printZak()

}//class Exercise18


class ZakSpecial {
    private int pilot; //熟練(乗員のスキル)



}//class ZakSpecial

/*
//###### class ZakPrivate ######
//同パッケージなのでアクセス可
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

    //==== getter, setter ====
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

}//class Zak
*/
/*
◇実行結果
名称: ザク1
火力: 1200
装甲: 2800

名称: ザク2
火力: 1200
装甲: 2800

名称: シャア専用
火力: 4800
装甲: 6400
熟練: 12000

*/