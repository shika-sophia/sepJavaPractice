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
 * @date 2020-11-08,11-09
 */

package javaPractice.calculator;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CalculatorMain2nd {
    static List<Double> opeList; //オペランドを保存
    static List<Integer> wayList;//計算方法を保存
    static List<Double> resultList;//計算結果を保存
    static Scanner scn = null;

    public static void main(String[] args) {
        opeList = new ArrayList<Double>(32);
        wayList = new ArrayList<Integer>();
        resultList = new ArrayList<Double>();

        CalcDouble calc = new CalcDouble();
        CalcLogic logic = new CalcLogic();

        //入力ループ
        inputLoop(calc, logic);

        scn.close();
    }//main()


    private static void inputLoop(CalcDouble calc, CalcLogic logic) {
        double inputNum = 0d;//オペランドの入力
        int inputWay = 0;    //計算方法の入力

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
                    System.out.println("値を入力してください。(整数,小数)");

                    //初回は表示なし
                    if(opeList.isEmpty()) {
                        ;
                    } else {
                        System.out.printf("〔 %s 〕", logic.textArea.toString());
                    }

                    inputNum = scn.nextDouble();
                    System.out.println();
                }

                //---- inputNumをリストに記録 ----
                opeList.add(inputNum);

                //---- 値のtextArea か 仮の計算結果temp を表示 ----
                String text = tempAns(calc, logic);

                //---- ０除算の処理 ----
                if(text.contains("< ！ > ０")){
                    System.out.println(text);
                    opeList.remove(opeList.size() - 1);
                    continue input;
                }

                //==== 計算方法の入力 inputWay ====
                //---- 計算方法を表示 ----
                logic.printCalcWay();

                //---- textAreaを表示 ----
                System.out.printf("〔 %s 〕", text);

                //---- inputWay ----
                inputWay = scn.nextInt();
                System.out.println();

            //---- 不正値チェック(非数値)----
            } catch (InputMismatchException e) {
                System.out.println("< ！ > 数値で入力してください。\n");
                continue input;
            }

            //---- 不正値チェック (inputWay)----
            String flag = logic.judgeWay(inputWay, opeList);

            //==== continueで while input 先頭へ====
            if(flag.equals("clear")) {
                opeList.clear();
                wayList.clear();
                continue input;
            }

            if(flag.equals("prev")) {
                opeList.remove(opeList.size() - 1);
                if (wayList.size() >= 1) {
                    wayList.remove(wayList.size() - 1);
                }
                continue input;
            }

            if(flag.contains("continue input")) {
                continue input;
            }

            //---- inputWayをリストに記録 ----
            wayList.add(inputWay);

            if (flag.equals("break input")) {
                break input;
            }

        }//while input

    }//inputLoop()


    //====== 値入力時のtextArea表示
    private static String tempAns(CalcDouble calc, CalcLogic logic) {
        String text = "";

        //計算可能なら計算結果を取得
        if(opeList.size() >= 2) {
            //----計算メソッドを選択し計算結果を取得 ----
            String result = selectCalcMethod(calc);

            //０除算のエラーメッセージが戻ってきたので表示
            //result をinputLoop()に戻してcontinue
            if (result.contains("< ！ > ０")) {
                return result;
            }

            //----resultListに計算結果を追加 ----
            resultList.add(Double.parseDouble(result));

            //----textAreaを消去、計算結果を加工し入れる----
            logic.textArea.delete(0, logic.textArea.length());
            text = logic.opeFormat(Double.parseDouble(result));

        //計算不可なら値を加工し取得
        } else {
            text = logic.opeFormat(opeList.get(opeList.size() - 1));
        }

        return text;
    }//printTemp()


    //====== 計算方法を分岐、計算結果を取得 ======
    private static String selectCalcMethod(CalcDouble calc) {
        String result = "";

        //---- x,yを生成 ----
        double x;
        if (resultList.isEmpty()) {
            x = opeList.get(opeList.size() - 2);
        } else {
            x = resultList.get(resultList.size() - 1);
        }

        double y = opeList.get(opeList.size() - 1);

        //---- inputWayを再生 ----
        int inputWay = wayList.get(wayList.size() - 1);

        switch(inputWay) {
        case 1:
            result = calc.calcAdd(x, y);
            break;

        case 2:
            result = calc.calcSubtract(x, y);
            break;

        case 3:
            result = calc.calcMultiply(x, y);
            break;

        case 4:
            result = calc.calcDivide(x, y);
            break;

        case 5:
            result = calc.calcRest(x, y);
            break;

        default:
            System.out.println("Main2nd.selectCalcMethod()でinputWayに不正値");
            break;
        }//switch

        return result;
    }//selectCalcMethod()

}//class

