/**
 * @title javaSilver / SE11Violet / VioletChapter04.java
 * @reference 山本道子『JavaSilver SE11 [1ZO-815]』翔泳社,2019 (紫本)
 * @content 第４章 for, while, break, continue
 * @author shika
 * @date 2021-01-08
 * @correctRate ①正答率 80.00 ％ ( 〇12問 / 全15問 )
 */
package javaSilver.SE11Violet;

//import javaSilver.AnswerMaker;
//
//public class VioletChapter04 {
//
//    public static void main(String[] args) {
//        new AnswerMaker();
//    }//main()
//
//}//class

/*
〇 1: C / one three
× 2: B, H => F,H
       forEach()は未定義(List, Stream処理のみ) / hasNext(),next()は配列で未定義
〇 3: A, B, C
〇 4: A compile error
〇 5: D / ary[x][y] = ary[x][y] * ary[x][y]
× 6: C / [1]x [2]y [3] => 〇: D [1]x y [2]y [3]〔コードで確認↓〕
〇 7: D / 20
〇 8: C / 9回
〇 9: B / no print
× 10: A / Orange Apple other => B 問６と同じ間違い
〇 11: C / 10 30
〇 12: B / continue break
〇 13: C / xx yy xx yy
〇 14: B / null null A B
〇 15: C / a b c d

正答率 80.00 ％ ( 〇12問 / 全15問 )
*/

//====== 問題4-6 ======
public class VioletChapter04 {
    public static void main(String[] args) {
        String[] ary = {"x","y","z"};

        int i = 1;
        for(String str : ary) {
            System.out.print("[" + i + "]");
            i++;

            switch(str) {
            case "x":
                System.out.print("x ");
            case "y":
                System.out.print("y ");
                break;
            case "a":
                System.out.print("a ");

            }//switch
        }//for
    }//main()
}//class

/*
//==== Result ====
[1]x y [2]y [3]
*/