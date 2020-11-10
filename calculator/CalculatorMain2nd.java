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
    static List<Double> memoryList;  //計算途中のメモリを保持

    static CalcDouble calc;
    static CalcLogic logic;
    static Scanner scn;

    //static初期化子 (staticはインスタンスしないのでコンストラクタの代わり)
    static {
        opeList = new ArrayList<Double>(32);
        wayList = new ArrayList<Integer>();
        resultList = new ArrayList<Double>();
        memoryList = new ArrayList<Double>();

        calc = new CalcDouble();
        logic = new CalcLogic();
        scn = null;
    }

    public static void main(String[] args) {

        //入力ループ
        inputLoop();

        //全計算式と最終計算結果の表示
        logic.printResult(opeList, wayList, resultList, memoryList);

        scn.close();
    }//main()


    private static void inputLoop() {
        double inputNum = 0d;//オペランドの入力
        int inputWay = 0;    //計算方法の入力
        String text = "";

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

                    //初回は表示なし
                    if(opeList.isEmpty()) {
                        ;
                    } else {
                        System.out.printf("〔 %s 〕", logic.textArea.toString());
                    }

                    inputNum = scn.nextDouble();
                    System.out.println();

                    //---- inputNumをリストに記録 ----
                    opeList.add(inputNum);

                    //---- 値のtextArea か 仮の計算結果temp を表示 ----
                    text = tempAns();

                    //---- ０除算の処理 ----
                    if(text.contains("< ！ > ０")){
                        System.out.println(text);
                        opeList.remove(opeList.size() - 1);
                        continue input;
                    }
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
                System.out.println("\n< ！ > 数値で入力してください。\n");
                continue input;
            }

            //---- 不正値チェック (inputWay)----
            String flag = logic.judgeWay(inputWay,
                opeList, wayList, resultList, memoryList);

            if(flag.contains("continue input")) {
                continue input;
            }

            //---- inputWayをリストに記録 ----
            wayList.add(inputWay);

            //---- オペランド２数以上かつ「＝」でwhile inputを抜ける ----
            if (flag.equals("break input")) {
                break input;
            }

        }//while input

    }//inputLoop()


    //====== 値入力時のtextArea表示
    private static String tempAns() {
        String text = "";

        //計算可能なら計算結果を取得
        if(opeList.size() >= 2) {
            //----計算メソッドを選択し計算結果を取得 ----
            String result = selectCalcMethod();

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
    }//tempAns()


    //====== 計算方法を分岐、計算結果を取得 ======
    private static String selectCalcMethod() {
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


    //====== 「Ｃ」clearの処理 from logic.judgeWay() ======
    public static void clearLogic() {
        //両リストを全消去
        opeList.clear();
        wayList.clear();
        //continueは logic.judgeWay()から flagで指示
    }//clearLogic()


    //====== 「←」prevの処理 from logic.judgeWay() ======
    public static void prevLogic() {
        //直近のオペランドと計算方法を１つずつ消去
        opeList.remove(opeList.size() - 1);

        if (wayList.size() >= 1) {
            wayList.remove(wayList.size() - 1);
        }

        //continueは logic.judgeWay()から flagで指示
    }//prevLogic()


    //====== memoryの処理 from logic.judgeWay() ======
    public static void memoryLogic(int inputWay) {
        if(inputWay == 8) { //[8] Ｍ＋
            //値１つなら値を、値２つ以上なら最終の仮結果をＭメモリに登録
            if (opeList.size() == 1) {
                memoryList.add(opeList.get(0));
            } else {
                memoryList.add(resultList.get(resultList.size() - 1));
            }
        }

        if(inputWay == 9) { //[9] Ｍ＝
             double sum = integralMemory();
             resultList.add(sum);
        }

        if(inputWay == 10) { //[10] ＭＣ
            memoryList.clear();
        }

        //continueは logic.judgeWay()から flagで指示
    }// memoryLogic()

    //====== memoryList の各要素を合計する ======
    //普通のリストの和ではなくBigDecimalでやらないといけない
    private static double integralMemory() {
        double sum = 0d;
        double x = 0d;
        double y = 0d;

        if(memoryList.size() == 1) {
            sum = memoryList.get(0);
            return sum;
        }

        for(int i = 0; i < memoryList.size(); i++) {
            if(i == 0) {
                x = memoryList.get(0);
            } else if (i == 1) {
                y = memoryList.get(1);
            } else {
                x = sum;
                y = memoryList.get(i);
            }
            String result = calc.calcAdd(x, y);
            sum = Double.parseDouble(result);

        }//for memoryList

        return sum;
    }//integralMemory()

}//class

/*
値を入力してください。(整数,小数)
4.66

[0]  ＝ , [1]  ＋ , [2]  ― , [3]  × , [4]  ÷ , [5]  ％(剰余) ,
[6]  Ｃ , [7]  ← , [8] Ｍ＋, [9] Ｍ＝, [10] ＭＣ,
計算方法を選んでください。[0]～[10]
〔 4.66.. 〕1

値を入力してください。(整数,小数)
〔 4.66.. ＋  〕5.55

[0]  ＝ , [1]  ＋ , [2]  ― , [3]  × , [4]  ÷ , [5]  ％(剰余) ,
[6]  Ｃ , [7]  ← , [8] Ｍ＋, [9] Ｍ＝, [10] ＭＣ,
計算方法を選んでください。[0]～[10]
〔 10.21.. 〕3

値を入力してください。(整数,小数)
〔 10.21.. ×  〕45

[0]  ＝ , [1]  ＋ , [2]  ― , [3]  × , [4]  ÷ , [5]  ％(剰余) ,
[6]  Ｃ , [7]  ← , [8] Ｍ＋, [9] Ｍ＝, [10] ＭＣ,
計算方法を選んでください。[0]～[10]
〔 459.45.. 〕4

値を入力してください。(整数,小数)
〔 459.45.. ÷  〕56

[0]  ＝ , [1]  ＋ , [2]  ― , [3]  × , [4]  ÷ , [5]  ％(剰余) ,
[6]  Ｃ , [7]  ← , [8] Ｍ＋, [9] Ｍ＝, [10] ＭＣ,
計算方法を選んでください。[0]～[10]
〔 8.20.. 〕0

((4.66 ＋ 5.55) × 45.0) ÷ 56.0 ＝ 8.20446

--------------------------------------------
値を入力してください。(整数,小数)45.22

[0]  ＝ , [1]  ＋ , [2]  ― , [3]  × , [4]  ÷ , [5]  ％(剰余) ,
[6]  Ｃ , [7]  ← , [8] Ｍ＋, [9] Ｍ＝, [10] ＭＣ,
計算方法を選んでください。[0]～[10]
〔 45.22.. 〕0

< ！ > 値が１つしかないと計算できません。

[0]  ＝ , [1]  ＋ , [2]  ― , [3]  × , [4]  ÷ , [5]  ％(剰余) ,
[6]  Ｃ , [7]  ← , [8] Ｍ＋, [9] Ｍ＝, [10] ＭＣ,
計算方法を選んでください。[0]～[10]
〔 45.22.. 〕
----------------------------------------------

*/