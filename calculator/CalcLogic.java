/**
 * @title javaPractice / calculator / CalcLogic
 * @see CalculatorMain2nd
 * @author shika
 * @date 2020-11-08, 11-09
 */


package javaPractice.calculator;

import java.util.List;

public class CalcLogic {
    StringBuilder textArea;
    private String CALC_WAY;
    private String[] calcArray;

    CalcLogic(){
        textArea = new StringBuilder();
        calcArray = new String[] {
            " ＝ "," ＋ "," ― "," × "," ÷ ", " ％(剰余) ",
            " Ｃ "," ← ","Ｍ＋","ＭＲ","ＭＣ"
        };
    }

    //====== 表示枠にオペランドを追加 / 小数点以下のフォーマット======
    public String opeFormat(double inputNum) {
        if(inputNum == (int)inputNum) {
            textArea.append(String.format("%.0f" ,inputNum));
        } else {
            textArea.append(String.format("%.2f.." ,inputNum));
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

            bld.append("計算方法を選んでください。[0]～[10] \n");

            CALC_WAY = bld.toString();
        }

        System.out.print(CALC_WAY);
    }//printCalcWay()


    //====== 計算方法の適正判定 ======
    public String judgeWay(int inputWay, List<Double> opeList) {
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
        case 3://「×」
        case 4://「÷」
        case 5://「％」
            textArea.append(calcArray[inputWay]);
            break;

        case 6://「Ｃ」
            flag = "clear";
            textArea.delete(0, textArea.length());
            break;

        case 7://「←」
            flag = "prev";
            textArea.delete((textArea.length() - 1), textArea.length());
            break;

        case 8://「Ｍ＋」
        case 9://「ＭＲ」
        case 10://「ＭＣ」
            flag = "continue input";
            System.out.println("< ！ > 未対応です。(編集中)");
            break;

        default:
            flag = "continue input";
            System.out.printf("< ！ > [0]～[%d]で入力してください。\n", (calcArray.length - 1));
            break;
        }//switch

        return flag;
    }//judgeWay()

}//class
