/**
 * @title javaPractice / DiceRV
 * @content Scanner, Random, while(true), StringBuilder
 * @author Nishio-san, SepJava2020, [Saikoro.java]
 * @author forked from Nishio-san / Revision by shika
 * @date 2020-10-28
 */

package javaPractice;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class DiceRV {
    //フィールド: 複数メソッド共通で使うものをここに出す
    //diceList: サイコロの履歴を記録
    private static List<Integer> diceList = new ArrayList<>();

    public static void main(String[] args) {
        //==== 初期設定 ====
        Random rdm = new Random();
        int dice = (rdm.nextInt(6) + 1); //0～5までを「+1」して 1～6

        //---- サイコロの履歴を記録 ----
        diceList.add(dice);

        //==== サイコロ繰り返しのループ ====
        //int count = 1; //サイコロ予想の回数 -> diceList.size()で代用
        int input = -1; //-1は初期値のダミー

        //while(true)は、繰り返し条件が いつも[true]のため永久にループする。
        //while{}ブロック内に必ず [break loop;]を作り、ループを抜ける方法を用意する。
        //[loop:]はこのwhile{}ブロックの名前で「ラベル」という。
        //break文に上記のようにラベルを付け、どこのbreakをするか明示する。
        loop:
        while(true) {
            Scanner scn = new Scanner(System.in);

            //---- 最初の１回だけルール説明 ----
            //(ここに置く理由)
            //・Scannerクラスのインスタンスを２つ作りたくない。(１つ閉じると他のも開かない)
            //・Scannerインスタンスは while loop内に置かないとtry-catchからの繰り返しができない

            if (diceList.size() == 1) { //diceList.size()でカウンター変数の代用
                printRule(scn);
            }

            //input時の説明表示
            printInput(dice);

            //---- try-catchで不正値チェック ----
            try {
                input = scn.nextInt();
                System.out.println();

            } catch (InputMismatchException e) {
                System.out.println("整数で入力してください。\n");
                continue loop;
            }

            //---- 不正値チェック, 終了処理 ----
            switch(input) {
            case 0: //終了
                scn.close();
                break loop;
                //このbreakは 永久ループwhile(true)を抜けるbreak

            case 1: //大きい
            case 2: //小さい
            case 3: //等しい
                ;
                break;
                //こっちのbreakは switch{}ブロックのみを抜ける。

            default: //不正値チェック
                System.out.println("[1][2][3][0]で入力してください。\n");
                continue loop; //while loopやり直し
            }//switch

            //---- サイコロ振り直し ----
            int preDice = dice; //前の値を保存
            dice = (rdm.nextInt(6) + 1);

            //サイコロリストに記録
            diceList.add(dice);

            //結果判定と表示
            boolean gameOver = judgeResult(dice, preDice, input);

            if (gameOver) {
                scn.close();
                break loop;
            }
        }//while

        int success;
        if (input == 0) {
            success = (diceList.size() - 1);
        } else {
            success = (diceList.size() - 2);
        }

        System.out.println("おつかれさまでした。");
        System.out.printf("成功回数 : %d \nサイコロ記録: %s",
            success, diceList);
    }//main()


    private static boolean judgeResult(int dice, int preDice, int input) {
        String[] highLow = new String[] {"大きい","小さい","等しい"};

        StringBuilder bld = new StringBuilder();
        bld.append(String.format(
            "【%d回目の結果】\n前回のサイコロ: %d / 今回のサイコロ: %d \n",
            (diceList.size() - 1), preDice, dice));
        bld.append("あなたの予想:   ").append(highLow[input - 1]).append("\n");

        //---- 結果判定 ----
        String result = "";
        if (dice > preDice) {
            result = highLow[0];
        } else if (dice < preDice) {
            result = highLow[1];
        } else if (dice == preDice) {
            result = highLow[2];
        }
        bld.append("サイコロの結果: ").append(result).append("\n\n");

        //---- 予想の結果判定 ---
        boolean gameOver = false;
        if (result.equals(highLow[input - 1])) {
            bld.append("おめでとう！予想どうりです。\n");
            bld.append("*** ゲームを続けます *** \n");
            gameOver = false;

        } else {
            bld.append("残念！予想は外れました。\n");
            bld.append("*** ゲームオーバー ***\n");
            gameOver = true;
        }

        //結果表示
        System.out.println(bld.toString());

        return gameOver;
    }//judgeResult()

    private static void printInput(int dice) {
        System.out.printf("【%d回目】サイコロの目: %d\n", diceList.size(), dice);
        System.out.println("次のサイコロは、これより[1]大きい,[2]小さい,[3]等しい,[0]終了");
        System.out.print("どれか予想してください。");
    }//printInput()

    private static void printRule(Scanner scn) {
        //ルール説明をメソッドにして外に出す。
        //(理由)制御の間に説明の文字列が混在していて、プログラムの動きを追いづらいので
        //      固定値を表示するルール説明は外に出してmain()をスッキリさせておく。

        //int known = scn.nextInt()で入力待ち
        //switch(known) でダウンフォールのルール説明

    }//printRule()

}//class

/*
//【西尾さんへの特別追加問題】
問１ サイコロゲームを 1～100のランダムに改良せよ。
     〔註: 元のコードはそのまま残しておき、新たにクラスを作って書こう〕

問２ 更にランダムの上限を変更する場合、あちこちコードをいじるのは
     いかに「いじられ好き」言えどもイヤなので、フィールドの定数を一か所変更すれば
     上限の変更が可能になるコードに修正せよ。

     〔ヒント〕今まで定数(固定値)で書いていた部分を変数に置き換え
     数式によって表現すると、変更に強いプログラムになる。

     このように、変更に強いプログラムにしていくと、他でも使える一般的なプログラムになっていく。
     この「他でも使える一般的なプログラム」のことを「汎用的[はんようてき]」と言う。
     より汎用性の高いコードにしていくことがプログラム作りの大事な考え方です。

問３ [応用問題]
     大小比較に「等しい」を加える。「等しい」は当然 的中する確率が低く
     誰も選ばないので、コインゲーム機能を追加し、
     「等しい」で当てると何倍も多くコインがもらえるよにしたい。

     このような「ミニカジノゲーム」作ってほしい。
     (てか、大鹿がプレイしてみたい)←それかよ

（時間に余裕があったらで、気が向いたらでいいです）
*/
/*
//==== 実行結果 ====
【1回目】サイコロの目: 4
次のサイコロは、これより[1]大きい,[2]小さい,[3]等しい,[0]終了
どれか予想してください。2

【1回目の結果】
前回のサイコロ: 4 / 今回のサイコロ: 2
あなたの予想:   小さい
サイコロの結果: 小さい

おめでとう！予想どうりです。
*** ゲームを続けます ***

【2回目】サイコロの目: 2
次のサイコロは、これより[1]大きい,[2]小さい,[3]等しい,[0]終了
どれか予想してください。1

【2回目の結果】
前回のサイコロ: 2 / 今回のサイコロ: 3
あなたの予想:   大きい
サイコロの結果: 大きい

おめでとう！予想どうりです。
*** ゲームを続けます ***

【3回目】サイコロの目: 3
次のサイコロは、これより[1]大きい,[2]小さい,[3]等しい,[0]終了
どれか予想してください。1

【3回目の結果】
前回のサイコロ: 3 / 今回のサイコロ: 1
あなたの予想:   大きい
サイコロの結果: 小さい

残念！予想は外れました。
*** ゲームオーバー ***

おつかれさまでした。
成功回数 : 2
サイコロ記録: [4, 2, 3, 1]

--------------------------
どれか予想してください。0

おつかれさまでした。
成功回数 : 0
サイコロ記録: [4]
--------------------------
【1回目】サイコロの目: 5
次のサイコロは、これより[1]大きい,[2]小さい,[3]等しい,[0]終了
どれか予想してください。予想
整数で入力してください。

【1回目】サイコロの目: 5
次のサイコロは、これより[1]大きい,[2]小さい,[3]等しい,[0]終了
どれか予想してください。8

[1][2][3][0]で入力してください。

【1回目】サイコロの目: 5
次のサイコロは、これより[1]大きい,[2]小さい,[3]等しい,[0]終了
どれか予想してください。
 */
