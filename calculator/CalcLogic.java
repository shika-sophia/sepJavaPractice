/**
 * @title javaPractice / calculator / CalcLogic
 * @see CalculatorMain2nd
 * @author shika
 * @date 2020-11-08, 11-09
 */


package javaPractice.calculator;

import java.math.BigDecimal;
import java.util.List;

public class CalcLogic {
    StringBuilder textArea;     //途中式を表示するための箱
    private String CALC_WAY;   //計算方法の表示(calcArrayから自動生成)
    private String[] calcArray;//計算方法の文字列変換用 配列

    CalcLogic(){
        textArea = new StringBuilder();
        calcArray = new String[] {
            " ＝ "," ＋ "," ― "," × "," ÷ ", " ％(剰余) ",
            " Ｃ "," ← ","Ｍ＋","Ｍ＝","ＭＣ"
        };
    }

    //====== 表示枠にオペランドを追加 / 小数点以下のフォーマット======
    @SuppressWarnings("deprecation")
    public String opeFormat(double inputNum) {
        BigDecimal inputNumBD = new BigDecimal(String.valueOf(inputNum));
        inputNumBD.setScale(2, BigDecimal.ROUND_FLOOR);
        if(inputNum == (int)inputNum) {
            textArea.append(String.format("%.0f" ,inputNumBD));
        } else {
            textArea.append(String.format("%.2f.." ,inputNumBD));
        }

        return textArea.toString();
    }//buildText()


    //====== 計算方法の表示 ======
    public void printCalcWay() {
        //初回のみ計算方法の作成
        if (CALC_WAY == null) {
            StringBuilder bld = new StringBuilder();

            for(int i = 0; i < calcArray.length; i++) {
                bld.append(String.format("[%d] %s, ", i, calcArray[i]));

                if(i % 5 == 0 && i != 0) {
                    bld.append("\n");
                }
            }//for

            bld.append(String.format("計算方法を選んでください。[0]～[%d] \n", calcArray.length - 1));

            CALC_WAY = bld.toString();
        }

        System.out.print(CALC_WAY);
    }//printCalcWay()


    //====== 計算方法の適正判定 ======
    public String judgeWay(int inputWay, List<Double> opeList, List<Integer> wayList,
            List<Double> resultList, List<Double> memoryList) {

        String flag = "";

        switch(inputWay) {
        case 0://「＝」

            if(opeList.size() < 2){
                System.out.println("< ！ > 値が１つしかないと計算できません。\n");
                flag = "continue input";

            } else if (opeList.size() >= 2) {
                textArea.append(calcArray[inputWay]);
                flag = "break input";
            }
            break;

        case 1://「＋」
        case 2://「―」
            textArea.append(calcArray[inputWay]);
            break;

        case 3://「×」
        case 4://「÷」
        case 5://「％」
            //最終計算方法を取得し「＝」の後の表示のみ()を挿入
            if(wayList.isEmpty()) {
                ;
            } else if(wayList.get(wayList.size() - 1) == 0) {
                textArea.insert(0, "(");
                textArea.append(")");
            }
            textArea.append(calcArray[inputWay]);
            break;

        case 6://「Ｃ」
            //textAreaを全消去
            textArea.delete(0, textArea.length());

            if(memoryList.size() >= 1) {
                textArea.append(" Ｍ | ");
            }

            //Main.両リストの処理
            CalculatorMain2nd.clearLogic();

            //inputLoop()に戻って continue
            flag = "continue input";
            break;

        case 7://「←」
            textArea.delete(0, textArea.length());

            if(memoryList.size() >= 1) {
                textArea.append(" Ｍ | ");
            }

            CalculatorMain2nd.prevLogic();
            if(opeList.isEmpty()) {
                ;
            }else if(resultList.isEmpty() && opeList.size() >= 1) {
                textArea.append(opeList.get(opeList.size() - 1));
            } else if (resultList.size() >= 1){
                textArea.append(resultList.get(resultList.size() -1));
            }

            flag = "continue input";
            break;

        case 8://「Ｍ＋」
            //Ｍメモリ初回のみ追加表示
            if (memoryList.isEmpty()) {
                textArea.insert(0, "Ｍ | ");
            }
            CalculatorMain2nd.memoryLogic(inputWay);
            flag = "continue input";
            break;

        case 9://「Ｍ＝」
            if (memoryList.isEmpty()) {
                System.out.println("< ！ > Ｍメモリはありません。\n");
                flag = "continue input";
                return flag;
            }

            CalculatorMain2nd.memoryLogic(inputWay);

            //表面表示用
            textArea.delete(0, textArea.length());

            if(resultList.isEmpty() && opeList.size() >= 1) {
                textArea.append(opeList.get(opeList.size() - 1));
            } else if (resultList.size() >= 1){
                textArea.append(resultList.get(resultList.size() -1));
            }

            flag = "continue input";
            break;

        case 10://「ＭＣ」
            if (memoryList.isEmpty()) {
                System.out.println("< ！ > Ｍメモリはありません。\n");
                flag = "continue input";
                return flag;
            }

            CalculatorMain2nd.memoryLogic(inputWay);
            textArea.delete(0, 4);
            flag = "continue input";
            break;

        default:
            flag = "continue input";
            System.out.printf("< ！ > [0]～[%d]で入力してください。\n\n", (calcArray.length - 1));
            break;
        }//switch

        return flag;
    }//judgeWay()


    //====== 最終結果の表示 ======
    public void printResult(List<Double> opeList, List<Integer> wayList,
                            List<Double> resultList, List<Double> memoryList) {

        textArea.delete(0, textArea.length());

        if(CalculatorMain2nd.memoryFin.length() >= 1) {
            textArea.append(CalculatorMain2nd.memoryFin.toString());
        }

        for(int i = 0; i < wayList.size(); i++) {
            textArea.append(opeList.get(i));
            judgeWay(wayList.get(i), opeList, wayList, resultList, memoryList);
        }//for wayList

        textArea.append(resultList.get(resultList.size() - 1));

        System.out.println(textArea.toString());
    }//printResult()

}//class
