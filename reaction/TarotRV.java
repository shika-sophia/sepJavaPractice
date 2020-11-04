/**
 * @title javaPractice / reaction / TarotRV
 * @content BufferedReader, FileReader
 * @author Ueda-san, SepJava2020, [TarotAndBook.java][memo.pptx]
 * @author forked from Ueda-san / Revision by shika
 * @date 2020-11-04
 */

package javaPractice.reaction;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TarotRV {
    private static final String[] TAROT = new String[] {
        "死神","太陽","星","正義","皇帝","魔術師","愚者",
    };
    private static int cardNum = TAROT.length;
    private static Scanner scn = null;


    public static void main(String[] args) {
        System.out.println("†† タロット図書館 ††");
        //==== parameter definition ====
        int birth = 0;
        boolean repeat = true;

        //==== while repeat if user want ====
        while(repeat) {

            //---- while input until acceptable input value ----
            birth = inputLoop(birth);

            //---- Random select calculate way ----
            //fortune: 運命数 = カード番号 0～(TAROT.length - 1)
            int fortune = selectCalc(birth);

            //---- printTarot ,printExplain, (printBook) ----
            printTarot(fortune);

            //---- ask user if repeat ----
            repeat = askRepeat();

        }//while(repeat)

        System.out.println("† おつかれさまでした †");
        scn.close();
    }//main()


    private static int inputLoop(int birth) {
        //==== while input until acceptable input value ====
        input:
        while(true) {
            scn = new Scanner(System.in);

            //不正値チェック(非整数)
            try {

                //初回なら生年月日を入力
                if(birth == 0) {
                    System.out.print("†あなたの生年月日を入力してください†[例: 20201104 ] ");
                    birth = scn.nextInt();
                    System.out.println();

                //すでにbirthの入力があれば その値でいいかを確認
                } else {
                    System.out.printf("†生年月日は[ %d ]でよろしいですか？† [0]YES, [1]NO ", birth);
                    int yesNo = scn.nextInt();
                    System.out.println();

                    //---- yesNo分岐 ----
                    if (0 <= yesNo && yesNo <= 1) {
                        ;
                    // 0, 1以外なら もう一度 yesNo
                    } else {
                        System.out.println("†[0][1]で入力してください†\n");
                        continue input;
                    }//if yesNo except 0,1

                    //YESなら inputループを抜け birthは以前の値
                    if (yesNo == 0) {
                        break input;

                    //NOなら birthを初期化して 生年月日の入力へ
                    } else if (yesNo == 1) {
                        birth = 0;
                        continue input;
                    }//if 0 or 1

                }//if birth

            } catch (InputMismatchException e) {
                System.out.println("†整数で入力してください†\n");
                continue input;
            }

            //不正値チェック(6-8桁かどうか)
            String regex = "\\d{6,8}";
            if(Pattern.matches(regex, String.valueOf(birth))) {
                break input;

            } else {
                System.out.println("†[例: 20201104 ] のように入力してください†\n");
                birth = 0;
                continue input;
            }
        }//while input

        return birth;
    }//inputLoop()


    //==== Random select calculate method / 計算方法をランダム選択 ====
    private static int selectCalc(int birth) {
        int fortune = 0;//運命数 = カード番号 0～(TAROT.length - 1)

        //運命数の計算方法をランダム選択
        final int CALC_NUM = 5; //計算方法の数
        Random rdm = new Random();
        int calcWay = rdm.nextInt(CALC_NUM);

        //現在の日時から年,日,秒を取得 -> birthに加算して使う
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int day = now.getDayOfYear();
        int sec = now.getSecond();

        //calcWayの分岐
        switch (calcWay) {
        case 0: //生年月日に関係なくランダムでカード選択
            fortune = rdm.nextInt(cardNum);
            break;

        case 1://カード枚数で割った余りは 0～(tarot.length - 1) になる
            fortune = birth % cardNum;
            break;

        case 2://birthに 秒を加算
            fortune = (birth + sec) % cardNum;
            break;

        case 3://birthに 年と秒を加算
            fortune = (birth + year + sec) % cardNum;
            break;

        case 4://birthに 日と秒を加算
            fortune = (birth + day + sec) % cardNum;
            break;
        }//switch

        return fortune;
    }//selectCalc()


    //==== print tarot and its explain ====
    private static void printTarot(int fortune) {
        System.out.print("†† あなたのカードは †† ");

        //---- 時間差「・・・・・」表示 ----
        final int WAIT = 5;
        for (int i = 1; i <= WAIT; i++) {
            System.out.print("・");

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("†中断されました†終了します†");
                System.exit(0);
            }
        }//for WAIT

        //---- カード表示 ----
        System.out.println(" †† " + TAROT[fortune] + " ††");
        System.out.println();

        //---- カードの説明表示 ----
        printExplain(fortune);

        //---- 本の紹介 ----
        //printBook(fortune);
    }//printTarot()


    //==== print card explain which read from tarotData.txt ====
    private static void printExplain(int fortune) {
        //説明文を連結していく箱を準備
        StringBuilder explain = new StringBuilder();

        //出たタロットカードを取得
        String card = TAROT[fortune];

        //==== 説明文を上記ファイルから読み込み ====
        //読み込むデータファイル名
        //ここ相対パスでは読み込めず、絶対パスにしました。みんな違うので書き換えてください。
        String fileName = "C:\\Program Files\\pleiades\\workspace-web\\sepJavaRecurrent\\src\\javaPractice\\reaction\\TarotData.txt";

        BufferedReader reader = null;
        try {
            //ファイルを読み込むIOストリームを準備
            reader = new BufferedReader(new FileReader(fileName));

            String line = "";
            //読み込める内容がなくなると nullを返すので、それまで１行ずつ読み込み
            readLine:
            while ((line = reader.readLine()) != null) {
                //【皇帝】などの行が来たら記録を開始
                if (line.contains(String.format("【%s】", card))) {

                    while(true) {
                        explain.append(line).append("\n");
                        line = reader.readLine();

                        //[END]が来たら読み込み終了
                        if (line.contains("[END]")) {
                            break readLine;
                        }
                    }//while explain

                }//if 【card】

            }//while readLine

        //以下は FileReaderを使うときの定型文なので、今は気にしなくて大丈夫です
        } catch (FileNotFoundException e) {
            System.out.println(fileName + " が見つかりません");
        } catch (IOException e) {
            System.out.println(fileName + "を読み込めません");
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }//try-catch-finally

        //==== 説明文の表示 ====
        System.out.println(explain.toString());

    }//printExplain()


  //==== ask user if repeat ====
    private static boolean askRepeat() {
        boolean repeat = true;

        input:
        while(true) {
            scn = new Scanner(System.in);
            int yesNo = -1;

            try {
                System.out.print("†もう一度、占いますか？† [0]YES, [1]NO ");
                yesNo = scn.nextInt();
                System.out.println();

            } catch (InputMismatchException e) {
                System.out.println("†整数で入力してください†\n");
                continue input;
            }

            //---- yesNo分岐 ----
            if (0 <= yesNo && yesNo <= 1) {
                ;
            // 0, 1以外なら もう一度 yesNo
            } else {
                System.out.println("†[0][1]で入力してください†\n");
                continue input;
            }//if yesNo except 0,1

            //YESなら while inputを抜け、while repeatを繰り返し
            if (yesNo == 0) {
                repeat = true;
                break input;

            //NOなら while inputを抜け、そのまま終了
            } else if (yesNo == 1) {
                repeat = false;
                break input;
            }//if 0 or 1

        }//while input

        return repeat;
    }//askRepeat()

}//class

/*
====== 実行結果 ======
†† タロット図書館 ††
†あなたの生年月日を入力してください†[例: 20201104 ] 19721124

†† あなたのカードは †† ・・・・・ †† 皇帝 ††

【皇帝】
あなたに選ばれた「皇帝」のカードは、支配、権力、安定、成功、責任を示します。
これまでの努力が実り、成功を治めると解釈します。

†もう一度、占いますか？† [0]YES, [1]NO0

†生年月日は[ 19721124 ]でよろしいですか？† [0]YES, [1]NO 0

†† あなたのカードは †† ・・・・・ †† 太陽 ††

【太陽】
あなたに選ばれた「太陽」のカードは、天真爛漫、無邪気、喜び、栄光、成功を示します。\n
成功や栄光、など手放しで喜べる状況を表します。

†もう一度、占いますか？† [0]YES, [1]NO 1

† おつかれさまでした †


====== エラーテスト ====
あなたの生年月日を入力してください。[例: 20201104 ] 19941123

あなたのカードは: 皇帝
生年月日は[ 19941123 ]でよろしいですか？ [0]YES, [1]NO 2

[0][1]で入力してください。
生年月日は[ 19941123 ]でよろしいですか？ [0]YES, [1]NO 1

あなたの生年月日を入力してください。[例: 20201104 ]
------------------------------------------------------------
†† タロット図書館 ††
†あなたの生年月日を入力してください†[例: 20201104 ] 昭和47年
†整数で入力してください†

†あなたの生年月日を入力してください†[例: 20201104 ] -1

†[例: 20201104 ] のように入力してください†

†あなたの生年月日を入力してください†[例: 20201104 ]
-------------------------------------------------------------
†もう一度、占いますか？† [0]YES, [1]NO 占う
†整数で入力してください†

†もう一度、占いますか？† [0]YES, [1]NO -1

†[0][1]で入力してください†

†もう一度、占いますか？† [0]YES, [1]NO
 */
