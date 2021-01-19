/**
 * @title javaSilver / SE11Black / Examin01
 * @content
 * @see 志賀澄人『徹底攻略 Java SE11 Silver 問題集[1ZO-815]』(黒本),インプレス, 2019
 * @author shika
 * @date 2021-01-17 / 08:01-10:11 (129分)
 * @correctRate ①86.25 ％ ( 〇69問 / 全80問 )
 */
package javaSilver.SE11Black;

import javaSilver.AnswerMaker;

public class Examin01 {

    public static void main(String[] args) {
        new AnswerMaker();
        //String className = new Object(){ }.getClass().getName();
        //System.out.println("className @main(): " + className);
    }//main()

}//class

/*
className @main(): javaSilver.SE11Black.Examin01$1
*/
/*
//====== １回目 / 2021-01-17 ======
〇 1: D
    -> javac a/A.java Aの完全修飾名
    -> main()のあるAをコンパイルすると自動的に Bもクラスファイルが生成。
〇 2: A
〇 3: C
〇 4: E
〇 5: C
× 6: A, B => 〇: A, D
    相互依存のモジュールはコンパイラがループし、コンパイルエラー。
〇 7: C
〇 8: E
× 9: A, B => 〇: B, E
    jmod はモジュールを作成する。
    依存関係は、jdeps, java --show-module-resolution
〇 10: B
    Cは Bを継承しているので、x( )を持っている
〇 11: A
〇 12: D
〇 13: D
    switch(null) -> NullPo
    switch(String A) 内部的に A.hashCode()で分岐
〇 14: B
× 15: C => 〇: B
    -cp カレント以外にあるクラスファイルを実行するとき --class-path( -cp )を付記。
    buildはコマンドではなく、ディレクトリ名。
〇 16: D
    LocalDate.now().with("TUESDAY").getDayOfWeek()
    now(): 現在の日付を取得
    with(): 来週の火曜日の日付に変更 (< ! > 火曜日の文字列を付け加えるのではない)
    getDayOfWeek(): その日付の曜日を取得。enum<DayOfWeek>を返す。
× 17: A, C => 〇: A,E
    staticは インスタンスとは別の領域なので、インスタンス変数に付けると意味が変わる
    inner classは、public～private 全て可能
〇 18: D
〇 19: D
〇 20: D
〇 21: A, E
〇 22: A
〇 23: A, C, E
〇 24: D
    ◆菱形継承問題(ダイアモンド継承)
    super()が B,Cのどちらを指すのか分からずコンパイルエラー
    B.super.sample() / C.super.sample()と記す。
〇 25: C / 要public、引数も違う。@Overrideではなく、オーバーロード。
〇 26: D
〇 27: B
× 28: A, E, F => 〇: A, B, F
    Bは new B()されているので (ここでは)abstract不可。
    interfaceに abstractを付けても可。
× 29: D, E => 〇: A, D
    -> アプリの使うJREのモジュールを含めると、Javaがインストールされていないプラットフォームでも
    アプリを実行でき、JREの必要なものだけをリンクさせられる。
    -> アプリの堅牢性とは、エラーになるような事態でもユーザーに影響を与えない能力のこと。
    モジュールの課題ではなく、アプリの非機能要件の課題。
     〔紫本の記述は間違いと論破している〕
〇 30: C
〇 31: C
〇 32: A
× 33: A => 〇: E
    List.of()で不変リスト -> keys.clear()で変更を加えているので UnsuppotedOperationException(in runtime)
〇 34: A
〇 35: A
    privateに @Overrideを付けるとコンパイルエラー。
〇 36: A
〇 37: C, E
〇 38: D / Unsuppoted
〇 39: B
〇 40: C
〇 41: C
〇 42: C
〇 43: D
〇 44: E
× 45: D => 〇: B
    implementsをせずに、@Overrideを付記するとコンパイルエラー
〇 46: A
〇 47: A
〇 48: A
〇 49: A
〇 50: D
〇 51: A, C
〇 52: C
〇 53: A
× 54: C => 〇: D
    Overrideに見えるが、前のclassで具象済なのでOverrideなくても良い。
    引数を変えたオーバーロード
〇 55: A, B, D, F
    char型は文字番号に相当する数字で管理されている。
    ( )キャスト式を付ければ、short s = (short)'c'; やその逆も可能。
〇 56: B
〇 57: C
〇 58: A
〇 59: A
〇 60: C
    long + Integerの計算は 暗黙的にアンボクシングされ
    long + int -> long + longで計算される
〇 61: A
〇 62: A
〇 63: C
〇 64: C
〇 65: A
〇 66: D
    メソッド参照の ()を付けるとメソッドの呼び出しと解釈される
    static を (new Sample()::print)で参照不可。
× 67: B => 〇: A
    Javaアプリは OSを選ばず実行可能だが
    JDKは、開発環境なので OSごとのセットアップが必要で、OSごとのJDKが用意されている。
〇 68: A, E
〇 69: D / java.lang.OutOfMemoryError
〇 70: C
〇 71: D
〇 72: B
〇 73: B
〇 74: D
〇 75: B
〇 76: B
〇 77: E
〇 78: A, D
× 79: B, D => 〇: A, B
    モジュール化されていないアプリは、無名モジュールとして扱われる
    無名モジュールは、全てのモジュールを読み込み(requires)全てのパッケージを開放する(exports)
〇 80: A

開始時刻 08:01
終了時刻 10:11
所要時間 129 分

正答率 86.25 ％ ( 〇69問 / 全80問 )
*/

