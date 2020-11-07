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

    }//main()

}//class CalculatorMain
