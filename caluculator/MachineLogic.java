/**
 * @title javaPractice / calculator / Calculator.java
 * @content 計算機をコンソールで再現するアプリ
 * @content 3 class ,following
 *
 * @class CalculatorMain / main() /
 *     全体の司令塔、各クラスのインスタンス, メソッド呼び出し, データの授受
 *
 * @class CalcProcess
 *     field: result, prevResult, memoSum
 *     method: calcAdd(), calcSubstract(), calcMultiply(), calcDevide()[except 0], calcRest()[except 0]
 *     overload: calcAdd(double d) as BigDecimal.add() ...etc (appendix function)
 *
 *     計算処理と計算結果の保持、除算と剰余はゼロチェック機能
 *     (追加機能: 小数の計算に正確を期すために BigDecimalクラスによる各メソッドのオーバーロード)
 *
 * @class MachineLogic
 *     field: textArea, inputList, inputNum, inputWay, CALC_WAY, scn
 *     method: inputLoop(), printCalcWay(), printTextArea(), printResult()
 *     ユーザーの入力、不正値ループ、計算方法の分岐、入力途中の計算式表示、計算結果の表示
 *
 * @see 関連src: javaPractice / exercise / Exercise04Ans ～ add, subStract, multiply, devide, except 0
 * @see 関連src: javaPracrice / chapter05 / Pracitce5_4 ～ BigDecimal
 * @author shika
 * @date 2020-11-07 / 0930-
 */
package javaPractice.caluculator;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MachineLogic {
    private static List<String> textArea;   //入力途中の計算式を保存
    private static List<Integer> inputList; //user inputの履歴を保存
    private static int inputNum; //計算のオペランド(リテラル)を入力
    private static int inputWay; //計算方法を数字で入力
    private static String CALC_WAY;   //計算方法の文字列(固定値)
    private static Scanner scn;

    public MachineLogic() {
        textArea = new ArrayList<String>(32);
        inputList = new ArrayList<Integer>(32);

    }

    public static void main(String[] args) {
    //public void inputLoop(){

        //==== user input until acceptable input / 適正値まで入力ループ ====
        input:
        while(true) {
            scn = new Scanner(System.in);

            try {
                //input 偶数回のみ値の入力から、奇数回は計算方法から
                if(inputList.size() % 2 == 0) {
                    //---- inputNum ----
                    System.out.print("値を入力してください。");
                    inputNum = scn.nextInt();
                    System.out.println();
                }

                //---- add inputNum to both List ----
                textArea.add(String.valueOf(inputNum));
                inputList.add(inputNum);

                //---- print CALC_WAY ----
                printCalcWay();

                //---- inputWay ----
                inputWay = scn.nextInt();
                System.out.println();

            //---- 不正値チェック(非整数) ----
            } catch (InputMismatchException e) {
                System.out.println("<！> 整数で入力してください。\n");
                continue input;
            }

            //---- 不正値チェック([0]～[9]) ----
            if (0 <= inputWay && inputWay <= 9) {
                ;
            } else {
                System.out.println("<！> [0]～[9]で入力してください \n");
                continue input;
            }

            //---- 計算方法の分岐 ----
            boolean isEnd = selectCalcWay();

            //「 = 」のときのみ while inputループを抜ける
            if (isEnd) {
                break input;
            }
        }//while input

        scn.close();
    }//inputLoop() or main() for Test


    //====== 計算方法の表示 ======
    private static void printCalcWay() {
        //初回のみ計算方法の作成
        if (CALC_WAY == null) {
            StringBuilder bld = new StringBuilder();
            bld.append("[1] + , [2] - , [3] * , [4] / ,[5]剰余 \n");
            bld.append("[6] C , [7] ←, [8] ( , [9] ) ,[0] =   \n");
            bld.append("計算方法を選んでください。[0]～[9] ");

            CALC_WAY = bld.toString();
        }

        System.out.println(CALC_WAY);
    }//inputCalcWay()


    //====== 計算方法の分岐 ======
    private static boolean selectCalcWay() {
        boolean isEnd = false;

        return isEnd;
    }//selectCalcWay()
}//class
