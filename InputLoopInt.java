package javaPractice;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputLoopInt {
/*
    public static void main(String[] args) {
//    	//---- Copy Format ----
//    	String fixed = "を入力してください。";
//    	String[] quest = new String[] {
//    	    "","",
//    	};
//    	int questNum = quest.length;
//    	int[] rangeLow = new int[]{ , , };
//    	int[] rangeHigh = new int[]{ , , };
//
//    	int[] questAns = inputLoop(fixed, quest, questNum, rangeLow, rangeHigh);

        //---- main() Test ----
        String fixed = "を入力してください。";      //定型文
        String[] quest = new String[] {             //質問内容
            "年","月","日"
        };
        int questNum = quest.length;               //質問数
        int[] rangeLow = new int[]{ 0, 1, 1};      //範囲下限
        int[] rangeHigh = new int[]{ 3000, 12, 31};//範囲上限

        int[] questAns = inputLoop(fixed, quest, questNum, rangeLow, rangeHigh);

        for (int answer : questAns) {
            System.out.println(answer);
        }//for questAns
    }//main()
*/

    public int[] inputLoop(
            String fixed, String[] quest, int questNum, int[] rangeLow, int[] rangeHigh) {
        int[] questAns = new int[questNum];
        Scanner scn = null;

        for(int i = 0; i < questNum; i++) {

            inputLoop:
            while(true) {
                scn = new Scanner(System.in);

                try {
                    System.out.print(quest[i] + fixed);
                    questAns[i] = scn.nextInt();
                    System.out.println();

                //---- 不正値チェック (非整数) ----
                } catch (InputMismatchException e) {
                    System.out.println("\n< ! > 整数で入力してください。\n");
                    continue inputLoop;

                } catch (NumberFormatException e) {
                    System.out.println("\n< ! > 整数で入力してください。終了します。\n");
                    System.exit(0);
                }

                //---- 不正値チェック (範囲外) ----
                if (rangeLow[i] <= questAns[i] && questAns[i] <= rangeHigh[i]) {
                    ;
                } else {
                    System.out.printf("< ! > [ %d ～ %d ]の範囲で入力してください。\n\n",
                        rangeLow[i], rangeHigh[i]);
                    continue inputLoop;
                }

                break;
            }//while inputLoop

        }//for questNum

        scn.close();
        return questAns;
    }//inputLoop
}//class

/*
◇実行結果
年を入力してください。2020

月を入力してください。11

日を入力してください。24

2020
11
24
---------------------------
年を入力してください。年

< ! > 整数で入力してください。

年を入力してください。-1

< ! > [ 0 ～ 3000 ]の範囲で入力してください。

年を入力してください。
---------------------------
*/