/**
 * @title javaPractice / calculator / Calculator.java
 * @content ３数以上の整数計算をするアプリ
 * @content ３ class ,following
 *
 * @class CalculatorMain / main() /
 *     field: opeList, wayList, x, y
 *     method: main(),separateOperand(), putXY(), selectCalcWay()
 *     全体の司令塔、各クラスのインスタンス, 計算方法の分岐、 データの授受
 *
 * @class CalcProcess
 *     field: result
 *     method: calcAdd(), calcSubstract(), calcMultiply(), calcDevide(), calcRest()
 *    〔overload: calcAdd(double d) as BigDecimal.add() ...etc〕(appendix function)
 *
 *     計算処理と計算結果の保持、除算と剰余
 *     (追加機能: 小数の計算に正確を期すために BigDecimalクラスによる各メソッドのオーバーロード)
 *
 * @class MachineLogic
 *     field: inputList, textArea, inputNum, inputWay, CALC_WAY, scn
 *     method: inputLoop(), excreptZero(), checkWay(),
 *             textFormat(), printCalcWay(), printResult()
 *     ユーザーの入力、不正値ループ、ゼロ除算チェック、入力途中の計算式表示、計算結果の表示
 *
 * @see 関連src: javaPractice / exercise / Exercise04Ans ～ add, subStract, multiply, devide, except 0
 * @see 関連src: javaPracrice / chapter05 / Pracitce5_4 ～ BigDecimal
 * @author shika
 * @date 2020-11-07 ,11-08
 */

package javaPractice.caluculator;

import java.util.ArrayList;
import java.util.List;

public class CalculatorMain {
    private static List<Integer> opeList; //オペランドのリスト
    private static List<Integer> wayList; //計算方法のリスト
    private static int x; //オペランド１
    private static int y; //オペランド２

    public static void main(String[] args) {
        MachineLogic mcnLogic = new MachineLogic();
        CalcProcess calc = new CalcProcess();

        opeList = new ArrayList<Integer>();
        wayList = new ArrayList<Integer>();

        List<Integer> inputList = mcnLogic.inputLoop();


        //---- オペランドと計算方法の分岐 ----
        separateOperand(inputList);

        int result = 0;//最終結果は calc.resultから取得
        int count = 1; //putXYへの周回カウント

        for(int calcWay : wayList) {
            //「＝」のときは x,yに代入なし
            if(calcWay == 0) {
                ;
            } else {
                //---- x,yに代入 ----
                putXY(count, calc);
                count++;
            }

            //---- 計算方法を分岐----
            result = selectCalcWay(calcWay, calc);

        }//for calcWay

        mcnLogic.printResult(result);

    }//main()


    //====== オペランドと計算方法の分離 ======
    private static void separateOperand(List<Integer> inputList) {

        for (int i = 0; i < inputList.size(); i++) {
            // iが偶数のときオペランド
            if (i % 2 == 0) {
                opeList.add(inputList.get(i));
            } else {
                wayList.add(inputList.get(i));
            }
        }//for inputList
    }//separateOperand()


    //====== x,y に代入 ======
    private static void putXY(int count, CalcProcess calc) {
        //２数の計算
        if(count == 1) {
            x = opeList.get(0);
            y = opeList.get(1);
            return;
        }

        //２数以上の計算
        x = calc.getResult();
        y = opeList.get(count);

    }//putXY()


    //====== 計算方法の分岐 ======
    private static int selectCalcWay(int calcWay ,CalcProcess calc) {
        int result = 0;

        switch (calcWay) {
        case 0://「＝」
            result = calc.getResult();
            break;

        case 1://「＋」
            calc.calcAdd(x, y);
            break;

        case 2://「ー」
            calc.calcSubstract(x, y);
            break;

        case 3://「×」
            calc.calcMultiply(x, y);
            break;

        case 4://「÷」
            calc.calcDevide(x, y);
            break;

        case 5://「剰余」
            calc.calcRest(x, y);
            break;
        }//switch

        return result;
    }//selectCalcWay()

}//class CalculatorMain

/*
◇実行結果
値を入力してください。3

[1] ＋ , [2] ー , [3] × , [4] ÷ ,[5]剰余
[6] Ｃ , [7] ← , [8]小数, [9] Ｍ ,[0] ＝
計算方法を選んでください。[0]～[9]
〔 3 〕2

値を入力してください。
〔 3 ー  〕4

[1] ＋ , [2] ー , [3] × , [4] ÷ ,[5]剰余
[6] Ｃ , [7] ← , [8]小数, [9] Ｍ ,[0] ＝
計算方法を選んでください。[0]～[9]
〔 3 ー 4 〕3

値を入力してください。
〔 ( 3 ー 4 ) ×  〕5

[1] ＋ , [2] ー , [3] × , [4] ÷ ,[5]剰余
[6] Ｃ , [7] ← , [8]小数, [9] Ｍ ,[0] ＝
計算方法を選んでください。[0]～[9]
〔 ( 3 ー 4 ) × 5 〕4

値を入力してください。
〔 ( ( 3 ー 4 ) × 5 ) ÷  〕-5

[1] ＋ , [2] ー , [3] × , [4] ÷ ,[5]剰余
[6] Ｃ , [7] ← , [8]小数, [9] Ｍ ,[0] ＝
計算方法を選んでください。[0]～[9]
〔 ( ( 3 ー 4 ) × 5 ) ÷ -5 〕1

値を入力してください。
〔 ( ( 3 ー 4 ) × 5 ) ÷ -5 ＋  〕5

[1] ＋ , [2] ー , [3] × , [4] ÷ ,[5]剰余
[6] Ｃ , [7] ← , [8]小数, [9] Ｍ ,[0] ＝
計算方法を選んでください。[0]～[9]
〔 ( ( 3 ー 4 ) × 5 ) ÷ -5 ＋ 5 〕3

値を入力してください。
〔 ( ( ( 3 ー 4 ) × 5 ) ÷ -5 ＋ 5 ) ×  〕2

[1] ＋ , [2] ー , [3] × , [4] ÷ ,[5]剰余
[6] Ｃ , [7] ← , [8]小数, [9] Ｍ ,[0] ＝
計算方法を選んでください。[0]～[9]
〔 ( ( ( 3 ー 4 ) × 5 ) ÷ -5 ＋ 5 ) × 2 〕0

◆計算結果
( ( ( 3 ー 4 ) × 5 ) ÷ -5 ＋ 5 ) × 2 ＝ 12


【考察】
◆計算結果
5 ＋ 3 × 4 ＝ 32

前から計算していくので上記の式は こうすべき。
(5 ＋ 3) × 4 ＝ 32

「()」を自動で挿入するロジックも実装
◆計算結果
( 2 × 4 ー 1 ) × 4 ＝ 28

これは ()要らんけど、まあいいか
◆計算結果
( 3 × 4 ) ÷ 5 ＝ 2


＊本物の計算機は、２数を入れた時点で計算結果が出る。
そのように入力途中で計算結果に置き換えていけば、計算優先順位や()の挿入の
問題はなくなる。

計算優先順位を考慮せず、前から順に計算していく状態だが、
３数以上の計算が可能になった。

＊あと問題なのは、割り算の答えが整数で出るのは、計算機ぽくない。
ここも改良すべし。

◆計算結果
25 ÷ 4 ＝ 6

◆計算結果
12 ％(剰余) 5 ＝ 2

◆計算結果 ×間違っとる
12 ÷ 3 ー 3 × 5 ＝ -5

    ◆計算結果
    12 ÷ 3 ＝ 4

    ◆計算結果
    4 ー 3 ＝ 1

    ◆計算結果
    1 × 5 ＝ 5

２数計算ずつやると、ちゃんとできてる。(前から計算として)
探すの大変、もう一度、最初から作るか・・・

あっ解った。putXY()のここ
//２数以上の計算
for (int i = 2; i < opeList.size(); i++) {
    x = calc.getResult();
    y = opeList.get(i);
}
２数以上のとき、いつもfor文が全周して終端の値が入るようになってる。
つまり
(12 / 3 - 5) * 5 = (4 - 5) * 5 = (-1)* 5 = -5

putXY()に入るたびに１ずつ増やさなといけないので countを使って
y = opeList.get(count); とすべき。修正済

◆計算結果 〇正解出ました
12 ÷ 3 ー 3 × 5 ＝ 5


＊ゼロ除算のチェックを入力時にしたけれど、
計算の優先順位を実装するなら、計算結果によってゼロ除算が生まれる可能性あり。
前から計算してく今の仕様なら問題ないが、 実際に計算するCalcProcessクラスに
ゼロチェックを置かないと

*/