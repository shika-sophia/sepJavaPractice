/**
 * @title javaSilver / SE11Black / Chapter09 / 第９章 ＡＰＩ
 * @content Math, Comperator, Character, LocalDate,
 *           ArrayList, Arrays, Map, forEach(),メソッド参照
 *
 * @see 志賀澄人『徹底攻略 Java SE11 Silver 問題集[1ZO-815]』(黒本),インプレス, 2019
 * @author shika
 * @date 2021-01-14 / 16:09-16:32 (23分)
 * @correctRate ①63.16 ％ ( 〇12問 / 全19問 )
 * @date 2021-01-19 / 11:47-12:03 (16分)
 * @correctRate ②94.74 ％ ( 〇18問 / 全19問 )
 */
package javaSilver.SE11Black;

import javaSilver.AnswerMaker;

public class Chapter09 {

    public static void main(String[] args) {
        new AnswerMaker();
    }//main()

}//class

/*
//====== １回目 / 2021-01-14 ======
〇 1: A
× 2: A => 〇: C
    -> ◆Comparator.compare()
     第１引数を前 -1, 第２引数を前 1, 同列で並び順を変更しないなら 0
〇 3: D
〇 4: B?
    -> ◆Characterクラス
        boolean isAlfabetic(): アルファベットかどうか
        boolean isDigit(): 数字かどうか
        boolean isLowerCase(): 小文字かどうか
〇 5: E
〇 6: D
    -> ◆LocalDate
        ・immutable(不変オブジェクト) 他 String, File, DateTime
            ・値が変更されたか確かめる必要がない
            ・Thread safe
            ・データの複製を考える必要がない
            ・複数クライアントによるデータの共有が可能

        ・TemporalAdjusterインターフェイス
            with(): データを変更。immutableのため新たなインスタンスを追加

× 7: A, B, E => 〇: B, D, E
    -> ◆配列
        ・同じ型/互換性のある型しか扱えない
        ・扱える要素数を最初に決めなければいけない
        ・要素へのアクセスは添え字を使う
        ・要素の扱い時は要素数を超えないよう注意が必要

       ◆ArrayList
        ・オブジェクトであればどのような型も扱える
        ・必要に応じて要素数を自動的に増やす
        ・追加した順に並ぶ
        ・nullも値として追加できる
        ・重複した値も追加できる
        ・スレッドセーフではない

× 8: A => 〇: F
    -> <>が空のときは defaultで <Object>
    -> <Object>は、Object, String, Integerなど異なる型も扱える
    -> 実行時にダウンキャストする際、ClassCastException(in runtime)の可能性がある
× 9: B => 〇: E
    -> ArrayList.add(int, E)は、存在しないindexを指定すると
        IndexOutOfBoundsException(in runtime)
〇 10: A
× 11: D => 〇: B
    -> remove(int)の他に remove(Object)もあり、
        remove()定義内の「this.name.equals(obj.name)」が trueを返すときに削除。
    -> remove()は、合致する対象が複数あっても、最初に適合したものだけを削除

× 12: A => 〇: C
    -> ◆拡張forのカーソル
        remove("B")でBが削除されると Cの位置が繰り上がり、次の要素を探してもないため処理が終了する
× 13: A => 〇: E
    -> ◆読み出し際中の変更 => ConcurrentModificationException (in runtime)
        multiThreadによる変更後や、remove()後の 再読出しで出る

〇 14: A, C
    -> ◆固定長Listの作り方
        ・Arrays.asList(): 配列かリストを生成
        ・static List.of()で インスタンスを生成
    -> add()/remove() すると java.lang.UnsuppotedOparationException (in runtime)
〇 15: D
〇 16: C
〇 17: B
    -> default Collection.removeIf(): Collection.iterator()で 要素を１つずつ取り出して調べ、trueなら削除
    -> Collectionは mutable
    -> ramoveIf()は、コレクションから要素を直接削除。
        immutableのようにインスタンスのコピーを取っているわけではない
〇 18: A, C
    -> java.lang.Itaratable.forEach(Consumer< T >): 拡張forと同じ
        Listインターファイスなら default Iteratable.forEach()を使える
〇 19: B

開始時刻 16:09
終了時刻 16:32
所要時間 23 分

正答率 63.16 ％ ( 〇12問 / 全19問 )
*/
/* 
//====== ２回目 / 2021-01-19 ======
〇 1: A 
〇 2: C 
〇 3: D 
〇 4: B 
× 5: B => 〇: E 
	LocalDateで monthに 0 -> java.time.DateTimeException(in runtime)
〇 6: D 
〇 7: B, D, E 
〇 8: F 
〇 9: E 
〇 10: A 
〇 11: B 
〇 12: C 
〇 13: E 
〇 14: A, C 
〇 15: D 
〇 16: C 
〇 17: B 
〇 18: A, C 
〇 19: B 

開始時刻 11:47
終了時刻 12:03
所要時間 16 分

正答率 94.74 ％ ( 〇18問 / 全19問 )
*/ 


