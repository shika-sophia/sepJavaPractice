/**
 * @title javaSilver / AnswerMaker.java
 * @reference 山本道子『JavaSilver SE11 [1ZO-815]』翔泳社,2019 (紫本)
 * @content 問題の回答と答え合わせの入力、正答率の自動表示
 * @author shika
 * @date 2021-01-07
 */

package javaSilver;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AnswerMaker {
    private Scanner scn;
    private Scanner scnSub;
    private List<String> resList;
    private List<String> correctList;
    private int questNum;
    private int correctNum;

    public AnswerMaker() {
        //---- initialize field ----
        resList = new ArrayList<>();
        correctList = new ArrayList<>();
        questNum = 0;
        correctNum = 0;

        //---- execute AnswerMaker ----
        run();
    }//constructor

    //====== call method() ======
    public void run() {
        resLoop();
        correctLoop();
        String result = calcRate();
        printResult(result);

        scn.close();
        scnSub.close();
    }//run()

    //====== input 'resInput' ======
    private void resLoop() {
        scn = new Scanner(System.in);

        System.out.println("*** 回答入力 ***");
        System.out.println("[0: 終了][9: 戻る]");
        System.out.println();

        resLoop:
        for(int i = 1; ; i++) {
            System.out.printf("%d : ", i);
            String resInput = scn.nextLine();

            //---- 終了フラグの判定 ----
            if(resInput.equals("")
                || resInput.equals("0")
                || resInput.equals("０")
                || resInput.equals("o") ) {

                //---- input [ Y / N ] 終了の確認 ----
                boolean isEnd = confirmEnd(i);

                if(isEnd) {
                    break resLoop;
                } else {
                    i--; //for( ; ;i++)で +1されるので実質 ±0
                    continue resLoop;
                }

            }//if resInput

            //---- [9: 戻る]の判定 ----
            if(resInput.equals("9") || resInput.equals("９")) {
                boolean isReverse = reverseNine(i);
                if(isReverse) {
                    resList.remove(i - 2); //iは1からなので indexは i-1
                    i -= 2; //for( ; ;i++)で +1されるので実質 -1
                    continue resLoop;
                }

            }

            //---- リストに登録 ----
            resList.add(i + ": " + resInput);
        }//for resLoop

        questNum = resList.size();
    }//resLoop()

    //====== [9: 戻る]の処理 ====
    //分岐: from resLoop() / correctLoop()
    private boolean reverseNine(int i) {
        boolean isReverse = false;

        String preRes = "";
        String preCorrect = "";

        //---- 戻り先のデータ取得 ----
        if(correctList.isEmpty()) {
            //---- resListが 空か size==1 なら最初から ----
            if(resList.isEmpty() || resList.size() == 1) {
                new AnswerMaker();
                System.exit(0);

            //---- 前のresを取得 ----
            } else {
                preRes = resList.get(i - 2); //iは1からなのでindexは i-1
            }//if-else resList

        } else if(correctList.size() == 1){
            preCorrect = correctList.get(0);
        } else {
            preCorrect = correctList.get(i - 2);//iは1からなのでindexは i-1
        }

        //---- 前のデータを表示 ----
        if(preCorrect.equals("")) {
            System.out.println("< ! >戻り先⇒ " + preRes);
        } else {
            System.out.println("< ! >戻り先⇒ｎ " + preCorrect);
        }

        //---- confirm [ Y / N ] of reverse ----
        scnSub = new Scanner(System.in);
        System.out.print("< ? > １つ戻り、前のデータは消えます。よろしいですか？ [ Y / N ] ");
        String confirmReverse = scnSub.next();

        switch(confirmReverse) {
        case "Y":
        case "y":
        case "Ｙ":
        case "ｙ":
        case "0":
        case "０":
            isReverse = true;
            break;

        default:
            isReverse = false;
        }//switch

        return isReverse;
    }//reverseNine()

    //====== confirm [ Y / N ] of resLoop end ======
    private boolean confirmEnd(int i) {
        boolean isEnd = false;

        scnSub = new Scanner(System.in);
        System.out.printf(
            "< ? > 回答入力を終了してもいいですか？(%d問完了) [ Y / N ] ", (i - 1));
        String confirmEnd = scnSub.next();

        switch(confirmEnd) {
        case "Y":
        case "y":
        case "Ｙ":
        case "ｙ":
        case "0":
        case "０":
            isEnd = true;
            System.out.println("// 回答終了 //");
            break;

        default:
            isEnd = false;
            System.out.println("回答を続けます⇒\n");
        }//switch

        return isEnd;
    }//confirmEnd()

    //====== input 〇× ======
    private void correctLoop() {
        System.out.println("\n*** 答え合わせ ***");
        System.out.println("[ 0 : 〇 ]/[ 1 : × ]");
        System.out.println();

        int isCorrectInt = 0;

        for (int i = 0; i < questNum; i++) {
            correctInput: //不正値のときのみ whileで繰り返し
            while (true) {
                scn = new Scanner(System.in);

                try {
                    System.out.print(resList.get(i) + " => ");
                    isCorrectInt = scn.nextInt();

                //---- 不正値チェック(非整数) ----
                } catch (InputMismatchException e) {
                    System.out.println("\n< ! > 整数で入力してください。\n");
                    continue correctInput;
                }

                //---- 不正値チェック(0,1以外) ----
                if (isCorrectInt < 0 || 1 < isCorrectInt) {
                    System.out.println("\n< ! > 0, 1で入力してください。[ 0 : 〇 ] / [ 1 : × ]\n");
                    continue correctInput;
                }

                //---- int -> String ----
                String isCorrectStr = "";

                if(isCorrectInt == 0) {
                    isCorrectStr = "〇";
                    correctNum++;

                } else if (isCorrectInt == 1){
                    isCorrectStr = "×";

                    //---- 正解を追加入力 ----
                    addInput(i);
                }

                correctList.add(isCorrectStr);
                break correctInput;
            }//while
        }//for
    }//correctLoop()

    //====== × -> 追加入力 ======
    private void addInput(int i) {
        StringBuilder bld = new StringBuilder();

        bld.append(resList.get(i)).append(" => 〇: ");
        System.out.print("< ? >正解入力 ×" + bld);

        scnSub = new Scanner(System.in);
        String addInput = scnSub.nextLine();
        System.out.println();

        bld.append(addInput);

        resList.set(i, bld.toString());
     }//addInput()

    //====== calc correctRate ======
    private String calcRate() {
        double correctRate = (double)correctNum / questNum;

        String result = String.format("正答率 %.2f ％ ( 〇%d問 / 全%d問 )",
            (correctRate * 100), correctNum, questNum);

        return result;
    }//calcRate()

    //====== print List and result ======
    private void printResult(String result) {
        System.out.println("\n*** 結果発表 ***");
        System.out.println("/*");

        for(int i = 0; i < questNum; i++) {
            System.out.printf("%s %s \n", correctList.get(i), resList.get(i));
        }//for
        System.out.println();
        System.out.println(result);
        System.out.println("*/");
    }//printResult()

    //====== Test main() ======
    public static void main(String[] args) {
        new AnswerMaker();
    }//main()

}//class

/*
*** 回答入力 ***
[0: 終了]

1 : B
2 : C
3 : D,E
4 : G
5 : ０
6 :

*** 答え合わせ ***
[ 0 : 〇 ]/[ 1 : × ]

1: B => 0
2: C => 5

< ! > 0, 1で入力してください。[ 0 : 〇 ] / [ 1 : × ]

2: C => まる

< ! > 整数で入力してください。

2: C => １
3: D,E => 0
4: G => 0
5: ０ => 0

*** 結果発表 ***
/＊
〇 1: B
× 2: C
〇 3: D,E
〇 4: G
〇 5: ０

正答率 80.00 ％ ( 〇4問 / 全5問 )
＊/

---- Test confirmEnd() ----
*** 回答入力 ***
[0: 終了]

1 : confirm
2 : y
3 : n
4 : 0
< ? > 回答入力を終了してもいいですか？(3問完了) [ Y / N ] n
回答を続けます⇒

4 : 続けます
5 : ０
< ? > 回答入力を終了してもいいですか？(4問完了) [ Y / N ] ｙ
// 回答終了 //

*** 答え合わせ ***
[ 0 : 〇 ]/[ 1 : × ]

1: confirm => 0
2: y => 0
3: n => 0
4: 続けます => 0

---- Test addInput() ----
*** 回答入力 ***
[0: 終了]

1 : A
2 : B
3 : C
4 : 0
< ? > 回答入力を終了してもいいですか？(3問完了) [ Y / N ] y
// 回答終了 //

*** 答え合わせ ***
[ 0 : 〇 ]/[ 1 : × ]

1: A => 0
2: B => 1
< ? >正解入力 ×2: B => 〇: A

3: C => 1
< ? >正解入力 ×3: C => 〇: D


*** 結果発表 ***
/＊
〇 1: A
× 2: B => 〇: A
× 3: C => 〇: D

正答率 33.33 ％ ( 〇1問 / 全3問 )
＊/
*/
