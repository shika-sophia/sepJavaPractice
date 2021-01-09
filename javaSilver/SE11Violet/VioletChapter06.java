/**
 * @title javaSilver / SE11Violet / VioletChapter06.java
 * @reference 山本道子『JavaSilver SE11 [1ZO-815]』翔泳社,2019 (紫本)
 * @content 第６章 継承、ポリモーフィズム(多態性)
 * @author shika
 * @date 2021-01-09
 */
package javaSilver.SE11Violet;

import javaSilver.AnswerMaker;

public class VioletChapter06 {

    public static void main(String[] args) {
        new AnswerMaker();
    }//main()

}//class

/*
//====== １回目 ======
〇 1: B, F
〇 2: D / ともに成功
    -> 派生型の配列にsuperオブジェクトを代入すると ArrayStoreException(in runtime)
× 3: B / 500 => 〇: A 100 -> numは自クラスのフィールドのみ検索
× 4: D / 要Override x() => 〇: E
  -> classBに x()があるため、classCもx()をOverrideしてると見なされる
〇 5: B / obj1 obj2
× 6: D / 10 20 50 60 => 〇: B x = xに thisは補われず、フィールドに代入されない
× 7: A / hello from B => 〇: C
  -> コンストラクタ B()呼び出し時に super()が補われ A()がないためコンパイルエラー
× 8: E / 両方不要 => 〇: C
  -> 上記の理由でsuper()用のコンストラクタが必要。this(5)でsuper()が抑制。両方も可
〇 9: B / super(j)が先
× 10: B / Default, mySuper:3 => 〇: E super()でコンパイルエラー(上記と同じ間違い)
× 11: A, D => 〇: A, B
  -> y()はabstractのため{ }メソッド本体がなくても良い。y();は{ }がないので×
〇 12: D? defaultはprivate不可。staticはＯＫ。要復習
× 13: A, C => 〇: A, E
  -> intreface内の無記は暗黙でpublic、実装メソッドはpublicを付ける
× 14: C? => 〇: D
  -> コンパイルは成功するが LecterとAudioは継承関係になため ClassCastException(in runtime)
× 15: A, C, E, G => 〇: A,B,C,G
  -> サブクラスの変数でsuperをそのまま代入はコンパイルエラー。
     キャストするとコンパイルは通るが、ClassCastException(in runtime)
〇 16: D / Gurrr Gurrr
  -> ポリモーフィズムはstaticメンバに適用されず、変数宣言時のクラス側のメソッドが呼び出される
× 17: D => 〇: C
  -> インスタンスメソッドをOverrideしている場合、サブクラスが優先して呼び出される
× 18: B / Cow Cow => 〇: A staticメソッドは サブクラス優先を適用せず
〇 19: A / Object
  -> Stringのsuperである Objectへ暗黙の型変換
× 20: E? => 〇: D オーバーロードの問題かい
× 21: D, F => 〇: A,F
  【ラムダ式】
    ・「Foo f = d -> -1;」は正しい
    ・「Foo f = d -> {int d = -1; return d;}」引数と{ }内のローカル変数が同じのため誤り
× 22: C / -1 => 〇: A
  -> 【Arrays.mismatch()】
      ・２つの配列を比較し、最初のミスマッチのindexを返す。
      ・比較の要素が同じで順序も同じなら -1 を返す
× 23: C / 8行目 => 〇: E
  「() -> 10」は引数に10ではなく、戻り値に10を返すの意
〇 24: C
× 25: D / NullPo => 〇: E
  -> 「List<String> city = Arrays.asList(ary);」は固定リストとして扱われ
     removeIf()の変更でUnsupportedOperationException(in runtime)
     「List<String> city = new ArrayList<>(Arrays.asList(ary));」なら可変リストでremoveIf()が使える

①正答率 31.25 ％ ( 〇8問 / 全25問 )
*/
