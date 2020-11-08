/**
 * @title javaPractice / calculator / CalculatorDouble
 * @content 小数計算のできる計算機アプリ
 * @edition calculator 2nd Edition
 *
 * @class CalculatorDouble / main()
 *     今度はここに inputLoopを置いて input中に計算結果を表示
 *
 * @class CalcDouble
 *     BigDecimalクラスを用いて小数計算に対応
 *
 * @class CalcLogic
 *     ロジック, 分岐, 表示用textArea
 *
 * @see 関連src: javaPractice / exercise / Exercise04Ans ～ add, subStract, multiply, devide, except 0
 * @see 関連src: javaPracrice / chapter05 / Pracitce5_4 ～ BigDecimal
 * @see 関連src: CalculatorMain, CalcProcess, MachineLogic
 * @author shika
 * @date 2020-11-08
 */

package javaPractice.caluculator;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CalculatorMain2nd {
    static List<Double> opeList; //オペランドを保存
    static List<Integer> wayList;//計算方法を保存
    static Scanner scn = null;

    public static void main(String[] args) {
        opeList = new ArrayList<Double>();
        wayList = new ArrayList<Integer>();

        CalcDouble calc = new CalcDouble();
        CalcLogic logic = new CalcLogic();

        //入力ループ
        inputLoop(calc, logic);

        scn.close();
    }//main()


    private static void inputLoop(CalcDouble calc, CalcLogic logic) {
        double inputNum = 0d;//オペランドの入力
        int inputWay = 0;

        //適正値が入るまでループ、「＝」で終了
        input:
        while(true) {
            scn = new Scanner(System.in);

            //==== 値の入力 inputNum ====
            try {
                //値が計算方法より多いときは、値入力なし
                if (opeList.size() > wayList.size()) {
                    ;
                } else {
                    System.out.print("値を入力してください。(整数,小数)");
                    inputNum = scn.nextDouble();
                    System.out.println();
                }

                //---- add inputNum to opeList ----
                opeList.add(inputNum);
                String text = logic.buildOpe(inputNum);

                //---- print textArea ----
                System.out.printf("〔 %s 〕\n", text);

                //==== 計算方法の入力 inputWay ====
                logic.printCalcWay();
                inputWay = scn.nextInt();
                System.out.println();

            //---- 不正値チェック(非数値)----
            } catch (InputMismatchException e) {
                System.out.println("< ！ > 数値で入力してください。\n");
                continue input;
            }

            //---- 不正値チェック (inputWay)----
            String flag = logic.judgeWay(inputWay, opeList);

            if(flag.equals("continue input")) {
                continue input;

            } else if (flag.equals("break input")) {
                break input;
            }

        }//while input

    }//inputLoop()


}//class

/*
値を入力してください。5
System.out.printf("inputNum: %f" ,inputNum);
inputNum: 5.000000

値を入力してください。8
System.out.printf("inputNum: %.0f" ,inputNum);
inputNum: 8

if(inputNum == (int)inputNum) {
    System.out.printf("inputNum: %.0f" ,inputNum);
} else {
    System.out.printf("inputNum: %.2f" ,inputNum);
}
値を入力してください。7.235
inputNum: 7.24
*/