/**
 * @title javaSilver / SE11Black / Examin02
 * @content
 * @see 志賀澄人『徹底攻略 Java SE11 Silver 問題集[1ZO-815]』(黒本),インプレス, 2019
 * @author shika
 * @date 2021-01-18 / 07:52-10:29 (157分)
 * @correctRate ①73.75 ％ ( 〇59問 / 全80問 )
 */
package javaSilver.SE11Black;

import javaSilver.AnswerMaker;

public class Examin02 {

    public static void main(String[] args) {
        new AnswerMaker();
    }//main()

}//class

/*
//====== １回目 / 2021-01-18 ======
〇 1: D
〇 2: A, B, D
× 3: C => 〇: A
    float -> intは キャストが必要
× 4: D, E, F => 〇: C, E, F
    D: package-privateなので全て参照可は間違い
    C: CacheTrash.add()は明記されていないが、他の選択肢の間違いに気付けば、これを選べる。
〇 5: C
〇 6: C
〇 7: A
〇 8: D
〇 9: E
× 10: C, E  => 〇: A, E
    var List list = new ArrayList<>();は defaultだと <Object>になり、varもＯＫ。
〇 11: B
× 12: A, D => 〇: C, D
    var a = { 1.0, 2.0, 3.0, 4.0 };は、型を明記していないのでコンパイルエラー。
    (double値の配列と推論できるけど、 floatかもしれない。この曖昧さがエラーにした理由かも)

× 13: D => 〇: C
    (分ってて選択肢の入力ミスや)もったいない。気を付けよう。
〇 14: D
    「super.super.method();」はコンパイルエラー。一つ上のsuperのみアクセス可。
〇 15: B
〇 16: D
× 17: C, E => 〇: A, E
    ◆無名配列 sample(new int[]{ 1, 2, 3 });
        メソッドの引数を渡すために名前を付けなくてもインスタンスできる。
        int[] array;
        array = new int[]{ };要素数を未定義に見えるが、{ }で要素0と定義できる。

    C: new Double[3]{ };初期化子を付けたら[3]要素数を記述するとコンパイルエラー。

〇 18: E
× 19: D => 〇: B
    「D: 22 + 22 + 2 = 46」してたわ。a=10なので 54ね。(初歩的なミス)
〇 20: B
〇 21: A
〇 22: D
〇 23: C
〇 24: A
〇 25: D
〇 26: C
〇 27: B
〇 28: B
    Consumer<>は戻り値を返さないので returnで値を返すとコンパイルエラー。
    x -> { return x }はＯＫ。
    x -> return xはＮＧ。
〇 29: D
× 30: E => 〇: C
    interface { void method();} -> 暗黙で public abstract -> これ間違えやすい
× 31: B => 〇: A
    ◆Map<key, value>
        keySet(): keyを取り出し Set型を戻す。
        value(): valueを取り出し、Collection型をの参照を返す。

    set(Collection c)を set(List<String> list)では呼び出せない。

× 32: C => 〇: B
    default で Overrideすることは可能。
    共変戻り値: Overrideでは、同じ型かそのサブクラスの戻り値が可能。
× 33: A => 〇: C
    int[] -> Ocject[]は互換性がない。
    int[] -> Objectで受ける。
    int[] -> long[]の暗黙の型変換があるが、32bit-> 64bitの処理が必要なため、Objectのが近い型。

× 34: D => 〇: A
    int[][] ary = new int[2][4];と定義した配列に
    ary[0] = { 1, 2, 3, 4 };
    ary[1] = { 1, 2 }; はＯＫ。非対称な多次元配列。

〇 35: D
〇 36: D
× 37: A => 〇: C
    String strは、ローカル変数。初期化はされないので、これを使うとコンパイルエラー。
    fieldなら、nullで初期化と混同しないように。
〇 38: E
〇 39: D
〇 40: A
〇 41: A
× 42: D => 〇: C
    ◆try-catch-resource文の順序
    ① close()を自動的に呼び出しリソースを解放
        -> close()が Overrideされてていればそちらを呼び出し
    ②catch
    ③finally

× 43: B, C => 〇: C, D
    モジュールを実行するときは class-pathが通るので、モジュール内のディレクトリにアクセス可能。
〇 44: A
× 45: B, C, D => 〇: B, D, E
    抽象クラスは、具象メソッドだけでも可。
    C: abstract で { }を付けるとコンパイルエラー。
    A: abstractは自動付記されないので、明記する。
〇 46: C
〇 47: A
〇 48: D, E
〇 49: C
〇 50: C, D
〇 51: C
〇 52: D, G, H
〇 53: A
〇 54: A, E
〇 55: E
〇 56: B
〇 57: A
〇 58: C
× 59: C => 〇: A
    オートアンボクシング int Integer.intValue(Integer)
〇 60: A
〇 61: B
〇 62: A
    ststic fieldは、インスタンス変数を利用して呼び出しても
    コンパイル時にクラス名へ変換される。
    a.num -> Main.num
〇 63: D, E
〇 64: C
〇 65: D
× 66: B => 〇: E
    null.nameでフィールドを呼び出したら NullPo
〇 67: B
× 68: E, F => 〇: B, F
    共変戻り値に型パラメタ<Object>の中身は適用しない
        List<Number>, ArrayList<Number>はＯＫ。
        List<Number>, List<Integer>はＮＧ。
    Overrideだけじゃなくシグニチャを変えてオーバーロードも可能。
        Set<CharSequence>, Set<String>はシグニチャを変えたことにならない。完全一致でもない
        Set<CharSequence>, TreeSet<CharSequence>ならオーバーロード成立。
        ただし、@Overrideを付記するとコンパイルエラー。
〇 69: C
    array.length: 配列の要素数
    array[i].length(): 配列の中身である要素の String.length()
〇 70: A
〇 71: C, D
〇 72: C
〇 73: C
× 74: A => 〇: C
    ◆2次元配列のclone(): new 新しい配列を作り、要素の参照先をコピー。(シャローコピー「浅いコピー」)
    ◆1次元配列のclone(): new 新しい配列を作り、要素の値をコピー(ディープコピー)。元の配列と参照先が違う。
    ◆Object.equals(): defaultで同一性(参照先)で判定
        array2 = array1.clone();により、
        要素の参照は同じでも、別の配列 -> 配列の参照先は違う。

〇 75: C
× 76: A, B ? => 〇: B, E
    メソッド参照は ()を付けない String::toUpperCase ならＯＫ。
〇 77: A
〇 78: B
〇 79: A
    ◇74参照 1次元配列 clone()は要素のコピー。変更しても元の配列に影響しない。
    Object.clone()なので全てのクラスで使える。
〇 80: A

開始時刻 07:52
終了時刻 10:29
所要時間 157 分

正答率 73.75 ％ ( 〇59問 / 全80問 )
*/

