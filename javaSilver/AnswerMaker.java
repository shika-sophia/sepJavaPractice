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
    private List<String> resList;
    private List<String> correctList;
    private int questNum;
    private int correctNum;
    private double correctRate;

    public AnswerMaker() {
        //---- initialize field ----
        scn = new Scanner(System.in);
        resList = new ArrayList<>();
        correctList = new ArrayList<>();
        questNum = 0;
        correctNum = 0;
        correctRate = 0L;

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
    }//run()

    //====== input 'resInput' ======
    private void resLoop() {
        System.out.println("*** 回答入力 ***");
        System.out.println("[0: 終了]");
        System.out.println();

        resLoop:
        for(int i = 1; ; i++) {
            System.out.printf("%d : ", i);
            String resInput = scn.nextLine();

            if(resInput.equals("")
                || resInput.equals("0")
                || resInput.equals("０")
                || resInput.equals("o") ) {
                break resLoop;
            }

            resList.add(i + ": " + resInput);
        }//for resLoop

        questNum = resList.size();
    }//resLoop()

    //====== input 〇× ======
    private void correctLoop() {
        System.out.println("\n*** 答え合わせ ***");
        System.out.println("[ 0 : 〇 ]/[ 1 : × ]");
        System.out.println();

        int isCorrectInt = 0;

        for (int i = 0; i < questNum; i++) {
            correctInput:
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
                }

                correctList.add(isCorrectStr);
                break correctInput;
            }//while
        }//for
    }//correctLoop()

    //====== calc correctRate ======
    private String calcRate() {
        correctRate = (double)correctNum / questNum;

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

//    //====== Test main() ======
//    public static void main(String[] args) {
//        new AnswerMaker();
//    }//main()

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
*/
