/**
 * @title javaPractice / JustFounde
 * @content
 * @author Okamoto-san, SepJava2020, [Skin.java][Skin2.java]
 * @author forked from Okamoto-san / Revision by shika
 * @date 2020-10-29
 */
package javaPractice;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class JustFounde {
    private static final int QUEST = 4;//質問の数。


    public static void main(String[] args) {
        System.out.println("・*＊ ファンデ診断 ＊*・");

        //==== 質問と入力 ====
        int[] input = new int[QUEST];

        for (int i = 0; i < QUEST; i++) {
            Scanner scn = new Scanner(System.in);

            //---- 質問表示 ----
            int choice = printQuest(i);

            //不正値チェック
            try {
                input[i] = scn.nextInt();
                System.out.println();

            } catch (InputMismatchException e) {
                System.out.println("整数で入力してください。");
                scn.close();
                System.exit(0);
            }

            //不正値チェック
            if (1 <= input[i] && input[i] <= choice) {
                ;
            } else {
                System.out.println("[1]～[" + choice + "]で入力してください。");
                scn.close();
                System.exit(0);
            }

        }//for i

        //==== 結果判定と表示 ====
        judgeFounde(input);

    }//main()


    //==== 結果判定 ====
    //条件が複雑なため、複数に該当する可能性あり。
    //そのため、else ifで繋がず、それぞれ別個に判定。複数解答を可能にした。
    private static void judgeFounde(int[] input) {
        List<String> answer = new ArrayList<>();

        if (input[0] ==1 && input[1] == 1 && input[2] == 2 ) {
            answer.add("クッションファンデーション");
        }

        //??同じ条件が２つあるけどタイプミスかな？
        if (input[0] == 2 && input[0] == 2) {
            answer.add("パウダーファンデーション");
        }

        if (input[0] == 3 && input[1] == 2) {
            answer.add("パウダーファンデーション");
        }

        if (input[0] ==3 && input[3] == 1){
            answer.add("リキッドファンデーション");
        }

        if (input[0] ==1 && input[1] ==2 && input[2] == 1){
            answer.add("パウダーファンデーション");
        }

        if (input[2] == 2 && input[3] == 2){
            answer.add("クッションファンデーション");
        }

        if (answer.size() == 0){
            answer.add("不明");
        }

        //==== 結果表示 ====
        System.out.println("・*＊ 診断結果 ＊*・");
        System.out.print("あなたに最適のファンデは");

        //時間差「・・・」表示
        for(int i = 1; i <= 3; i++) {
            System.out.print("・");

            try {
                Thread.sleep(500);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }//for i
        System.out.println();

        for(String str : answer) {
            System.out.println("    " + str);
        }

        System.out.println("\n--------------------------------\n");
        System.out.println("・*＊ おまけ ＊*・");
        printMenu();
    }//judgeFounde()


    private static int printQuest(int i) {
        String message = "";//質問内容
        int choice = 0;     //選択肢の数

        switch (i) {
        case 0: //肌質
            message = "あなたの肌質は？\n" +
                "[1] 乾燥肌, [2] 混合肌, [3] オイリー肌  ";
            choice = 3;
            break;

        case 1: //感触
            message = "好きな仕上がりの感触は？\n" +
                "[1] しっとり, [2] さらさら  ";
            choice = 2;
            break;

        case 2: //見た目
            message = "好きな仕上がりの見た目は？\n" +
                "[1] ふんわり肌, [2] つや肌  ";
            choice = 2;
            break;

        case 3: //カバー力
            message = "好きなカバー力は？\n" +
                "[1] きちんとカバー, [2] ナチュラルカバー  ";
            choice = 2;
            break;
        }//switch

        System.out.print(message);

        return choice;
    }//printQuest()


    private static void printMenu() {
        String[] dinner ={"カレー","皿うどん","焼きそば","お好み焼き","野菜炒め","豚肉と玉ねぎの甘辛炒め"
                ,"ピーマンの肉詰","オムライス","おすし","炊き込みご飯","餃子","肉じゃが","筑前煮","マーボーナス"
                ,"マーボー豆腐","ロールキャベツ","シチュー","春巻き","豚の角煮","生姜焼き","どんぶり","唐揚げ"
                ,"たこ焼き","かす汁","茶碗蒸し","とんかつ","天ぷら","おでん"};

        Random rdm = new Random();
        int menu = rdm.nextInt(dinner.length);

        System.out.println("今日の夕飯は[ " + dinner[menu] + " ]にしましょ♪\n");
    }//printMenu()

}//class

/*
《解説》
◆クラス分けの考え方
同じ種類の機能をするものを１つのクラスにまとめる。
別々の機能は、別のクラスに分ける。
[Skin.java][Skin2.java]は同一機能なので、同じクラスにしてメソッドで分けると良い。

制御(プログラムの動き)と、文字列が分離していたので、
プログラムの動きを追いやすい、見やすいコードでした。
パッケージでまとめて、別クラスに飛ばすのも上手にできていました。

◆プログラムのコツ
同じことを繰り返し書いているときは、部品化して、for文などで回せないかを考える。

◆ファンデ結果判定は複雑な条件を判定するＡＩなので、
else ifで つなぐと最初に適合したものに決定し、後の条件はスルーされて判定してくれなくなる。
なので、それぞれのif文に分割して、複数解答ができるように Listを使いました。

Listは、配列の進化版で、あらかじめ要素数を決めなくていいし、
いくつでも要素を追加していけます。
いずれ学習するので、今は配列が使えれば大丈夫です。

◆フィールド
private static final int QUEST = 4;//質問の数。
今のところ、他のメソッドで使っていないので main()の中に記述してもいいのだけど、
質問数を今後増やすときは「QUEST = 」の値を変えれば済むようにしてある。
ただし、switch文に質問内容を追加する必要はある。

◆変数の名前
変数名は a, bではなく意味の分かる言葉で、できればローマ字を避け、英語で付けましょう。
例えば 肌質 -> quality, 感触 -> touch など
まとめて扱う場合は、ここのように配列 input[]に入れてしまう。
input[0], input[1]などは意味が解りにくいけど、コメントで意味を補うしかない。
配列の場合は for文で一気に扱えるので、そのメリットはある。


//==== 実行結果 ====
・*＊ ファンデ診断 ＊*・
あなたの肌質は？
[1] 乾燥肌, [2] 混合肌, [3] オイリー肌  3

好きな仕上がりの感触は？
[1] しっとり, [2] さらさら  2

好きな仕上がりの見た目は？
[1] ふんわり肌, [2] つや肌  1

好きなカバー力は？
[1] きちんとカバー, [2] ナチュラルカバー  1

・*＊ 診断結果 ＊*・
あなたに最適のファンデは・・・
    パウダーファンデーション
    リキッドファンデーション

--------------------------------
・*＊ ファンデ診断 ＊*・
あなたの肌質は？
[1] 乾燥肌, [2] 混合肌, [3] オイリー肌  肌質
整数で入力してください。
--------------------------------
・*＊ ファンデ診断 ＊*・
あなたの肌質は？
[1] 乾燥肌, [2] 混合肌, [3] オイリー肌  -1

[1]～[3]で入力してください。
--------------------------------
・*＊ ファンデ診断 ＊*・
あなたの肌質は？
[1] 乾燥肌, [2] 混合肌, [3] オイリー肌  3

好きな仕上がりの感触は？
[1] しっとり, [2] さらさら  -1

[1]～[2]で入力してください。
--------------------------------

・*＊ おまけ ＊*・
今日の夕飯は[ ロールキャベツ ]にしましょ♪

*/