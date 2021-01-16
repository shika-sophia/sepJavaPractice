/**
 * @title javaSilver / SE11Violet / VioletExamin02.java
 * @reference 山本道子『JavaSilver SE11 [1Z0-815]』翔泳社,2019 (紫本)
 * @content 模擬試験２
 * @author shika
 * @date 2021-01-16 / 09:45-12:06 (140分)
 * @correctRate ①83.75 ％ ( 〇67問 / 全80問 )
 */
package javaSilver.SE11Violet;

import javaSilver.AnswerMaker;

public class VioletExamin02 {

    public static void main(String[] args) {
        new AnswerMaker();
    }//main()

}//class

/*
//====== １回目 / 2021-01-16 ======
〇 1: C
〇 2: A
〇 3: B
〇 4: B
〇 5: C
〇 6: A
〇 7: D
〇 8: C
〇 9: B
〇 10: A, C, E, G
〇 11: B
〇 12: D
〇 13: B
〇 14: E
〇 15: C
〇 16: C
× 17: D => 〇: D, E ２つ選択
× 18: A => 〇: D
    -> 整数同士の計算は整数になる。1.17 * 100 = 117のためもう一度(float)が必要
〇 19: E
    -> if文の中の初期化は初期化と認められない。ローカル変数を初期化していないとコンパイルエラー。
〇 20: C
× 21: E => 〇: A
    -> substring(start, end); 0からendの手前まで、endは含まず
〇 22: C
〇 23: B
〇 24: B
〇 25: C
〇 26: D
    -> case 1 | 2: case 'a'| 'b': は整数型のためビット演算子ＯＫ。
    -> case "a"|"b": は参照型のため不可。
〇 27: D
〇 28: B
〇 29: A
× 30: D => 〇: E
    -> switch(ary[i])はコンパイル通るのか。要素の型が適合していればＯＫ。
〇 31: B
〇 32: E
〇 33: C
〇 34: E
〇 35: E
〇 36: E
〇 37: D, E
〇 38: A, B
× 39: D => 〇: E
    super(num)なので A()はなくていいんだ。継承なので protectedは そりゃ通る。
× 40: D => 〇: A
    -> Main obj = null;でも、objの型がMainで、static foo()なので正常に呼び出し可。
    -> foo()が 非staticなら、 NullPo。
〇 41: C
〇 42: D
〇 43: B
〇 44: D, E
〇 45: B
    -> メソッド後にフィールドを定義してもＯＫ。
    -> 空のメソッドで throws句もＯＫ。
〇 46: D, E
    -> ◆ポリモーフィズムで行った先のメッソド呼び出し
        A.foo{ print(); }でコンパイル時は同クラスに print()があるかをチェック。
        -> 同クラスになければ、サブクラスにあってもコンパイルエラー
        実行されるのは、同名でオーバーライドされた B.print()のほう。
〇 47: A, B
〇 48: A
〇 49: E
〇 50: C
× 51: B => 〇: C
〇 52: A, B
〇 53: A
    sort((o1, o2) -> o1.compareTo(o2)); 昇順
    sort((o1, o2) -> -o1.compareTo(o2)); 降順
    sort((o1, o2) -> o2.compareTo(o1)); 降順
    sort((o1, o2) -> -o2.compareTo(o1)); 昇順

〇 54: C
〇 55: D
〇 56: E
〇 57: A, C, F
× 58: E => 〇: C
    -> final double num;を A(String), A(int)で初期化しているのは、別インスタンスのためＯＫ。
    -> A()は finalフィールドを初期化していなのでコンパイルエラー
〇 59: B
〇 60: B
× 61: A, C => 〇: B, C
    -> abstractクラスのメソッド定義は明示的に abstract
        一般メッソドも持てるため、abstractは自動付与されない。
    -> abstract内 privateメソッドだけ、意味はないが文法的に間違ってはいない。
〇 62: C
〇 63: B
〇 64: E
〇 65: D
× 66: B => 〇: D
    -> 抽象をインスタンス、C, Dは継承関係にない
〇 67: D
× 68: B => 〇: A
    -> private a()はAクラスなからしかアクセス不可。
    -> B.a()はオーバーライドと見なされず独自の a()を定義している。
    -> ポリモーフィズム先の a()呼び出しは
        (オーバーライドは存在しなので) ローカルの同クラスの a()が呼び出される。
〇 69: A
× 70: A, B => 〇: B, D
    -> 「try or catchの後ろに finally」だと catch-finallyも許容される書き方だけどいいの？
    -> try, catch, finallyに行数の制限はないので私の間違い
〇 71: E
〇 72: B
〇 73: A, D
〇 74: D
〇 75: B, D, E
〇 76: A
× 77: A => 〇: B jdepsや
〇 78: D
〇 79: A
〇 80: D

開始時刻 09:45
終了時刻 12:06
所要時間 140 分

正答率 83.75 ％ ( 〇67問 / 全80問 )
*/

