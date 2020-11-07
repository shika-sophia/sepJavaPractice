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

public class CalculatorMain {

    public static void main(String[] args) {
        MachineLogic mcnLogic = new MachineLogic();
        CalcProcess calc = new CalcProcess();

        //List<Integer> inputList = mcnLogic.inputLoop();

      //---- 計算方法の分岐 ----
      //selectCalcWay();
    }//main()

//  //====== 計算方法の分岐 ======
//    private static boolean selectCalcWay(CalcProcess calc) {
//        boolean isEnd = false;
//        int result = 0;
//        String resultInfo = "";
//
//        int x = 0;
//        int y = 0;
//        int count = 0;
//
//        //オペランドと計算方法の分離
//        for (int i = 0; i < inputList.size(); i++) {
//            // iが偶数のときオペランド
//            if (i % 2 == 0) {
//                //x, y にオペランドを代入
//                if(count % 2 == 0) {
//                    x = inputList.get(i);
//                } else if (count % 2 == 1) {
//                    y = inputList.get(i);
//                }
//                count++;
//
//            // iが奇数のとき計算方法
//            } else if (i % 2 == 1) {
//                inputWay = inputList.get(i);
//            }
//
//        }//for inputList
//
//        resultInfo = formatInfo(x, y);
//
//        switch (inputWay) {
//        case 0://「＝」
//            resultInfo = " ＝ ";
//            textArea.append(" ＝ ");
//            isEnd = true;
//            break;
//
//        case 1://「＋」
//            isEnd = calc.calcAdd(x, y);
//            break;
//
//        case 2://「ー」
//            isEnd = calc.calcSubstract(x, y);
//            break;
//
//        case 3://「×」
//            isEnd = calc.calcMultiply(x, y);
//            break;
//
//        case 4://「÷」
//            isEnd = calc.calcDevide(x, y);
//            break;
//
//        case 5://「剰余」
//            isEnd = calc.calcRest(x, y);
//            resultInfo = String.format("%d を %d で割った余りは ",x , y);
//            break;
//        }//switch
//
//        result = calc.getResult();
//        System.out.println(resultInfo + result);
//
//        return isEnd;
//    }//selectCalcWay()
//
//    private static String formatInfo(int x, int y) {
//        String resultInfo = "";
//        String[] calcWayStr = new String[] {
//            "＝","＋","ー","×","÷", "余り"
//        };
//
//        resultInfo = String.format("%d %s %d", x, calcWayStr[inputWay], y);
//        textArea.append(resultInfo);
//
//        return resultInfo;
//    }//selectCalcWay()

}//class CalculatorMain
