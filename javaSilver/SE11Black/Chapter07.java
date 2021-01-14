/**
 * @title javaSilver / SE11Black / Chapter07 / 第７章 継承、インターフェイス
 * @content 継承、インターフェイス、抽象クラス、Override、
 *           ポリモーフィズム、参照型の型変換、this、super
 *
 * @see 志賀澄人『徹底攻略 Java SE11 Silver 問題集[1ZO-815]』(黒本),インプレス, 2019
 * @author shika
 * @date 2021-01-14 / 06:43-07:16 (32分)
 * @correctRate ①90.48 ％ ( 〇19問 / 全21問 )
 */
package javaSilver.SE11Black;

import javaSilver.AnswerMaker;

public class Chapter07 {

    public static void main(String[] args) {
        new AnswerMaker();
    }//main()

}//class

/*
//====== １回目 / 2021-01-14 ======
〇 1: D
〇 2: C
〇 3: A, E
〇 4: A
〇 5: A
   -> default method()は、subInterfaceで@Override可。
   -> java.lang.Objectのクラスを defaultでOverrideするとコンパイルエラー。
   -> ◆Objectクラスのメソッド
          clone(),finalaize(),equals(),getClass(),hashCose(),toString(),compareTo()
〇 6: E
   -> インターフェイス名.super.method();で、defaultを呼び出す。
   -> 直接実装関係にあるクラスでしか使えない。
〇 7: C
   -> 同じシグニチャのdefaultは Main()に A.super.test()と
      どちらのtest()を呼び出すかを明記しないとコンパイルエラー
〇 8: A, C, D
   -> interface.fieldは static finalだが、abstractクラス.fieldは変数も可
〇 9: A
   -> test()は 抽象メソッドではなく@Overrideした具象のほうが呼び出される
〇 10: A
   -> ◆共変戻り値
   @Overrideは、同じ型かそのサブクラスもＯＫ。

   -> Overrideは上書き(overwrite)ではなく再定義。
      superには元のメソッドがそのまま存在し続ける
〇 11: C
〇 12: B
   -> フィールド参照 変数の型で決まる(コンパイル時に決定)
   -> メソッド参照 インスタンスならオブジェクト型のメソッド(実行時に決定)、staticなら変数型()
〇 13: C
〇 14: A
× 15: D => 〇: F
   -> A()はinterfaceでインスタンス不可。A()は絶対安全と思い込んでた。
〇 16: F
× 17: A => 〇: D
   -> ダウンキャストでもキャスト式しているのでコンパイルエラーはない。
   -> A a = new A(); B b = (B)a; b.hello();
   -> B型のbにはA()が入っている。 A.hello()にはBの差分は含まれておらず
      A()のインスタンスを B()のインスタンスに変換できないため ClassCastException(in runtime)
〇 18: A, D
〇 19: B
〇 20: C
〇 21: A

開始時刻 06:43
終了時刻 07:16
所要時間 32 分

正答率 90.48 ％ ( 〇19問 / 全21問 )
*/

