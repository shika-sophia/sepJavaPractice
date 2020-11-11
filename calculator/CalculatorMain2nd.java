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
    static StringBuilder memoryFin;

    //static初期化子 (staticはインスタンスしないのでコンストラクタの代わり)
    static {
        opeList = new ArrayList<Double>(32);
        wayList = new ArrayList<Integer>();
        resultList = new ArrayList<Double>();
        memoryList = new ArrayList<Double>();

        calc = new CalcDouble();
        logic = new CalcLogic();
        scn = null;
        memoryFin = new StringBuilder();
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
                    System.out.println("値を入力してください。(整数,小数) ");

                    //---- textAreaを表示 ----
                    System.out.printf("〔 %s 〕", logic.textArea.toString());

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

                    //---- Infinityの防止 ----
                    //doubleの範囲 -4.94..×10 ^ -324 ～ 1.79.. × 10 ^ 308
                    if (text.length() >= 100) {
                        System.out.println("< ！ > 計算結果が100桁以上になったので、一度クリアします。\n");
                        logic.textArea.delete(0, logic.textArea.length());
                        memoryList.clear();
                        clearLogic();
                        continue input;
                    }

                }

                //==== 計算方法の入力 inputWay ====
                //---- 計算方法を表示 ----
                logic.printCalcWay();

                //---- textAreaを表示 ----
                System.out.printf("〔 %s 〕", logic.textArea.toString());

                //---- inputWay ----
                inputWay = scn.nextInt();
                System.out.println();

            //---- 不正値チェック(非数値)----
            } catch (InputMismatchException e) {
                System.out.println("\n< ！ > 数値で入力してください。\n");
                continue input;

            } catch (NumberFormatException e) {
                System.out.println("\n< ！ > 数値に変換できません。一度クリアします。\n");
                logic.textArea.delete(0, logic.textArea.length());
                clearLogic();
                memoryList.clear();
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


    //====== 値入力時のtextArea表示 ======
    private static String tempAns() throws NumberFormatException {
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

            if(memoryList.size() >= 1) {
                logic.textArea.append(" Ｍ | ");
            }

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
        resultList.clear();
        //continueは logic.judgeWay()から flagで指示
    }//clearLogic()


    //====== 「←」prevの処理 from logic.judgeWay() ======
    public static void prevLogic() {
        //直近のオペランドと計算方法を１つずつ消去
        if (opeList.size() >= 1) {
            opeList.remove(opeList.size() - 1);
        }

        if (wayList.size() >= 1) {
            wayList.remove(wayList.size() - 1);
        }

        //continueは logic.judgeWay()から flagで指示
    }//prevLogic()


    //====== memoryの処理 from logic.judgeWay() ======
    public static void memoryLogic(int inputWay) {
        if(inputWay == 8) { //[8] Ｍ＋
            //値１つなら値を、値２つ以上なら最新の仮結果をＭメモリに登録
            if (resultList.isEmpty() && opeList.size() == 1) {
                memoryList.add(opeList.get(0));
            } else {
                memoryList.add(resultList.get(resultList.size() - 1));
            }
        }

        if(inputWay == 9) { //[9] Ｍ＝
            double sum = integralMemory();
            resultList.add(sum);
            opeList.clear();
            opeList.add(sum);
            wayList.clear();
            memoryList.clear();
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
            memoryFin.append(memoryList.get(0));
            return sum;
        }

        for(int i = 0; i < memoryList.size(); i++) {
            if(i == 0) {
                x = memoryList.get(0);
                memoryFin.append(memoryList.get(0)).append(" ＋ ");
            } else if (i == 1) {
                y = memoryList.get(1);
                memoryFin.append(memoryList.get(1));
                if(memoryList.size() > 2) {
                    memoryFin.append(" ＋ ");
                }
            } else {
                x = sum;
                y = memoryList.get(i);
                memoryFin.append(memoryList.get(i));

                if(i == memoryList.size() - 1) {
                    ;
                } else {
                    memoryFin.append(" ＋ ");
                }
            }
            String result = calc.calcAdd(x, y);
            sum = Double.parseDouble(result);

        }//for memoryList

        return sum;
    }//integralMemory()

}//class

/*
値を入力してください。(整数,小数)
〔  〕0.1

[0]  ＝ , [1]  ＋ , [2]  ― , [3]  × , [4]  ÷ , [5]  ％(剰余) ,
[6]  Ｃ , [7]  ← , [8] Ｍ＋, [9] Ｍ＝, [10] ＭＣ,
計算方法を選んでください。[0]～[10]
〔 0.10.. 〕1

値を入力してください。(整数,小数)
〔 0.10.. ＋  〕0.7

[0]  ＝ , [1]  ＋ , [2]  ― , [3]  × , [4]  ÷ , [5]  ％(剰余) ,
[6]  Ｃ , [7]  ← , [8] Ｍ＋, [9] Ｍ＝, [10] ＭＣ,
計算方法を選んでください。[0]～[10]
〔 0.80.. 〕3

値を入力してください。(整数,小数)
〔 0.80.. ×  〕3

[0]  ＝ , [1]  ＋ , [2]  ― , [3]  × , [4]  ÷ , [5]  ％(剰余) ,
[6]  Ｃ , [7]  ← , [8] Ｍ＋, [9] Ｍ＝, [10] ＭＣ,
計算方法を選んでください。[0]～[10]
〔 2.40.. 〕0

(0.1 ＋ 0.7) × 3.0 ＝ 2.4

--------------------------------------------
値を入力してください。(整数,小数)
〔  Ｍ |999.90.. ＋  〕0.1

[0]  ＝ , [1]  ＋ , [2]  ― , [3]  × , [4]  ÷ , [5]  ％(剰余) ,
[6]  Ｃ , [7]  ← , [8] Ｍ＋, [9] Ｍ＝, [10] ＭＣ,
計算方法を選んでください。[0]～[10]
〔  Ｍ |1000 〕

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
値を入力してください。(整数,小数)
〔  〕5

[0]  ＝ , [1]  ＋ , [2]  ― , [3]  × , [4]  ÷ , [5]  ％(剰余) ,
[6]  Ｃ , [7]  ← , [8] Ｍ＋, [9] Ｍ＝, [10] ＭＣ,
計算方法を選んでください。[0]～[10]
〔 5 〕4

値を入力してください。(整数,小数)
〔 5 ÷  〕0

< ！ > ０で割ることはできません。

値を入力してください。(整数,小数)
〔 5 ÷  〕3

[0]  ＝ , [1]  ＋ , [2]  ― , [3]  × , [4]  ÷ , [5]  ％(剰余) ,
[6]  Ｃ , [7]  ← , [8] Ｍ＋, [9] Ｍ＝, [10] ＭＣ,
計算方法を選んでください。[0]～[10]
〔 1.67.. 〕5

値を入力してください。(整数,小数)
〔 1.67.. ％(剰余)  〕0

< ！ > ０で割ることはできません。

値を入力してください。(整数,小数)
----------------------------------------------

【考察】
BigDecimalクラスで小数計算は正確に求められるようになりました。(たぶん)

四則計算のみで「＝」で終了した場合の最終結果は ちゃんと表示されます。
Ｍメモリ機能や「Ｃ」「←」を使用時に最終結果の表示に不具合がまだあります。
今後、気が向いたら直すかも・・←気が向いたらかいっ

計算機のテストを何回もしすぎて、くるくるパーになってます。しばらく置いておこう。

【考察】追記 2020-11-11-2000
2020-11-11-1730頃、西尾さんによる挙動テストで大量の桁の掛け算により
Double.Infinityとなり、その後の演算で NumberFormatExceptionとなったので、それに対応。

〔修正後〕
値を入力してください。(整数,小数)
〔 1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.00.. ×  〕
1000000000000000000000000000000000000000000000000000000000000000000000000000

< ！ > 計算結果が100桁以上になったので、一度クリアします。

値を入力してください。(整数,小数)
〔  〕
*/