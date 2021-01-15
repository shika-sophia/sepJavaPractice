/**
 * @title javaSilver / SE11Violet / VioletExamin01.java
 * @reference 山本道子『JavaSilver SE11 [1Z0-815]』翔泳社,2019 (紫本)
 * @content 模擬試験１
 * @author shika
 * @date 2021-01-15 / 14:58-17:33 (154分)
 * @correctRate ①78.75 ％ ( 〇63問 / 全80問 )
 */
package javaSilver.SE11Violet;

import javaSilver.AnswerMaker;

public class VioletExamin01 {

    public static void main(String[] args) {
        new AnswerMaker();
    }//main()

}//class

/*
//====== １回目 / 2021-01-15 ======
〇 1: C, D
〇 2: E
〇 3: B
〇 4: E
〇 5: A
× 6: E => 〇: A
    -> class宣言時 protected, privateは不可。
〇 7: A
〇 8: A
× 9: A, B, E => 〇: A, B, F
    -> short値をchar代入は不可。(char)'a'-> shortは可。
〇 10: A, B, E
    -> finalは可だが、final var i = 10;の順
〇 11: B
〇 12: D
    -> ローカル変数は初期化せずに使うとコンパイルエラー。(コンパイラで初期化してくれない)
〇 13: C
〇 14: C
    -> 「+」演算子による nullの結合は String "null"に置換して結合。
〇 15: C
〇 16: E
    -> case1: 2:は不可。case 1: case2:なら可。
    case 1 | 2:はビット演算子をコンパイル時に計算し case 3:になる。
× 17: D => 〇: A
    -> switch(ary[0])コンパイル時に値になるのその型が適合すればＯＫ。
    -> case句に変数不可のため finalを付記。
    -> Stringオブジェクトは不変でも、変数に代入されると値が変わる可能性がある。
〇 18: C
〇 19: D
〇 20: B
〇 21: E
〇 22: D
〇 23: C
〇 24: B
〇 25: B, D
    -> 三項演算子は 必ず三項。省略不可。
〇 26: C
〇 27: E
    -> switch(null)は、NullPo
〇 28: C, E
〇 29: B
〇 30: C
〇 31: C
〇 32: A
〇 33: B
〇 34: C
〇 35: A
〇 36: C
〇 37: C
× 38: C => 〇: A
    -> super(num)の protected A(int)はBと継承関係にあるので、コンパイルに成功する。
× 39: D => 〇: A
    -> A.getVal()は、クラス名.メソッド名で、Aのstaticフィールドを呼び出せる。
    -> obj.getVal()なら、obj = null;のため NullPo
〇 40: A
〇 41: F
× 42: E => 〇: D
    -> A()はfinalフィールドを初期化していないためコンパイルエラー。
× 43: B => 〇: C
    -> ２回インスタンス後、フィールド取得しているので static int bは２回分の10が２回とも表示
× 44: E => 〇: B
    -> println(null)は、そのまま nullと表示
    -> println(p2)は toString()が働いてフィールドを表示
〇 45: D
× 46: D => 〇: B
    -> 代入しただけでは参照外れず、どの変数からも参照されなくなったオブジェクトが gcの対象。
× 47: B => 〇: C
    -> ラムダ式は final扱い、変更するとコンパイルエラー
〇 48: D
× 49: D => 〇: C
    -> Overrideは メソッド名、引数型、引数の個数が完全一致。戻り値は継承可。
    -> 引数の型が全く違うとオーバーロード。
    -> Collection< T >と Collection<String>は、型が同じで完全一致でもない
〇 50: A, B
〇 51: A, B, E, G
    -> interfaceのfieldは暗黙で public static final。
〇 52: B
    -> interface#method()は、暗黙でpublic。privateは不可。ststicのみprivate可。
〇 53: D
〇 54: B
〇 55: D
〇 56: D
    -> interface A extends C, D 複数のインターフェイスの継承は可。
〇 57: A
〇 58: B
    -> keyの重複は無視されるのではなく、後からの値で上書きされる。
〇 59: E
    -> メッソド参照「Main::method()」の()はあっても可。
    -> ダウンキャストに(キャスト式)が抜けているのでコンパイルエラー。
〇 60: A
× 61: B => 〇: C
    ラムダ式で (int x, y)は不可。(int x, int y) or (x, y)とすべき
〇 62: A
〇 63: A, B
〇 64: C
× 65: D => 〇: E
    -> 不変リストのため、add()で変更を加えると、UnsupportedOperationException(in runtime)。コンパイルは成功する
〇 66: F
〇 67: B
× 68: C => 〇: B
    -> 不変リストなため変更するといかんのでは？
    -> color.replceAll((String s) -> s.toUpperCase());
    -> replaceAll()って、iteratorあってListとかで使うみたい。
〇 69: C
〇 70: A, C
〇 71: C, E
〇 72: C
× 73: D => 〇: C
    -> C, Dの両方必要では？メソッド定義に throws句は必須らしい。
〇 74: D
× 75: A, B => 〇: A, E
    -> AとEの選択肢は同じいみのような・・
〇 76: A
〇 77: C
× 78: B, D, F => 〇: B, C, F
    -> ◆無名モジュール
        ・module-info.javaを持たない
        ・クラスパス上に存在するクラスファイル
        ・全てのパッケージを公開。

〇 79: D
〇 80: A

開始時刻 14:58
終了時刻 17:33
所要時間 154 分

正答率 78.75 ％ ( 〇63問 / 全80問 )
*/

