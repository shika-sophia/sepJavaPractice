/**
 * @title javaSilver / SE11Violet / VioletChapter06.java
 * @reference 山本道子『JavaSilver SE11 [1Z0-815]』翔泳社,2019 (紫本)
 * @content 第６章 継承、ポリモーフィズム(多態性)
 * @author shika
 * @date ①2021-01-09, ②2021-01-11
 * @correctRate ①32.00 ％ ( 〇8問 / 全25問 )
 * @correctRate ②88.00 ％ ( 〇22問 / 全25問 )
 */
package javaSilver.SE11Violet;

import javaSilver.AnswerMaker;

public class VioletChapter06 {

    public static void main(String[] args) {
        new AnswerMaker();
    }//main()

}//class

/*
//====== １回目 / 2021-01-09 ======
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

①正答率 32.00 ％ ( 〇8問 / 全25問 )
*/
/*
//====== ２回目 / 2021-01-11 ======
〇 1: B, F
〇 2: D / A[]に new B()は入る
〇 3: A / 100
〇 4: E / x()は class Bに実装済とみなす
× 5: E / 12行目 D instanceof A はコンパイルエラー => 〇: B
  -> extends, implemntsできない型で比較するとコンパイルエラー
  -> D と A は実装関係にはないが、Aはimplements可能な型なので、false
〇 6: B / 0 0 50 60 / methodA(){ x = x; y = y;}はフィールドに入らず
〇 7: C / 7行目 B()にsuper()が補われ、A()にそれがなくコンパイルエラー
〇 8: C / Supper(), this(5)の一方または両方
〇 9: B / super(i)が先
〇 10: E / 16行目 super()の受け先なし
〇 11: A, B
〇 12: D / コンパイル成功 defaultでprivate不可。staticはprivate可
〇 13: A, E / Cは publicじゃないと。extendsは×
〇 14: D? LectureとFaciは実装関係。AudioとFaciも同様。コンパイルは成功する
          f1にはLectureが入っているため ClassCastException(in runtime)
〇 15: A, B, C, G
〇 16: D / Gurrr Gurrr Animal[]だから
〇 17: C / Gurrr Moo / Animal[]だが非staticでOverride
× 18: C / 2,6行目 staticはOverride不可 => 〇: A static同士は可
〇 19: A / Object
〇 20: D / 6行目
〇 21: A, F
〇 22: A / 0
〇 23: E / 10
〇 24: C
× 25: D => 〇: E UnsupportedOperationException(in runtime)

②正答率 88.00 ％ ( 〇22問 / 全25問 )
*/
