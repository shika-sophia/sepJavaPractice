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
 *     method: inputLoop(), exceptZero(), checkWay(),
 *             textFormat(), printCalcWay(), printResult()
 *     ユーザーの入力、不正値ループ、ゼロ除算チェック、入力途中の計算式表示、計算結果の表示
 *
 * @see 関連src: javaPractice / exercise / Exercise04Ans ～ add, subStract, multiply, devide, except 0
 * @see 関連src: javaPracrice / chapter05 / Pracitce5_4 ～ BigDecimal
 * @author shika
 * @date 2020-11-07 ,11-08
 */
package javaPractice.calculator;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

//###### inputLoop() as for instance ######
public class MachineLogic {
    private List<Integer> inputList; //user inputの履歴を保存
    private StringBuilder textArea;   //入力途中の計算式を保存
    private int inputNum; //計算のオペランド(リテラル)を入力
    private int inputWay; //計算方法を数字で入力
    private String CALC_WAY;   //計算方法の文字列(固定値)
    private Scanner scn;

    public MachineLogic() {
        inputList = new ArrayList<Integer>(32);
        textArea = new StringBuilder();
        inputNum = 0;
        inputWay = 0;
    }

    public List<Integer> inputLoop(){

        //====== 適正値まで入力ループ ======
        input:
        while(true) {
            scn = new Scanner(System.in);
            boolean isContinue = false;

            try {
                //input 偶数回のみ値の入力から、奇数回は計算方法から
                if(inputList.size() % 2 == 0) {

                    //---- inputNum ----
                    System.out.print("値を入力してください。");

                    //初回でなければ、入力経過の表示
                    if(!(inputList.isEmpty())){
                        System.out.printf("\n〔 %s 〕", textArea.toString());
                    }

                    inputNum = scn.nextInt();
                    System.out.println();

                    //---- add inputNum to inputList and textArea ----
                    inputList.add(inputNum);
                    textArea.append(inputNum);
                }//if

                //---- 不正値チェック (０除算)----
                isContinue = exceptZero();

                if (isContinue) {
                    continue input;
                }

                //---- print CALC_WAY and textArea ----
                printCalcWay();
                System.out.printf("〔 %s 〕", textArea.toString());

                //---- inputWay ----
                inputWay = scn.nextInt();
                System.out.println();

            //---- 不正値チェック(非整数) ----
            } catch (InputMismatchException e) {
                System.out.println("<！> 整数で入力してください。\n");
                continue input;
            }

            //---- 不正値チェック([0]～[9]) ----
            //---- [6]～[9] 未対応 ----
            //---- 不正値チェック (値１つなのに「＝」) ----
            isContinue = checkWay();

            if (isContinue) {
                continue input;
            }

            //---- add inputWay to inputList and textArea----
            inputList.add(inputWay);

            String wayStr = textFormat(inputWay);

            //「×」「÷」「％」のとき ()を挿入
            if ((inputWay == 3 || inputWay == 4 || inputWay == 5) && inputList.size() >= 3) {
                textArea.insert(0, "( ");
                textArea.append(" )").append(wayStr);
            } else {
                textArea.append(wayStr);
            }




            //「＝」のとき while inputループを抜ける
            //値が１つだと計算不可なので入力継続
            if (inputList.size() < 4) {
                continue input;

            } else if (inputWay == 0 && inputList.size() >= 4) {
                //System.out.printf("〔 %s 〕\n", textArea.toString());
                break input;
            }
        }//while input

        scn.close();
        return this.inputList;
    }//inputLoop()


    //====== 不正値チェック (０除算)======
    private boolean exceptZero() {
        boolean isContinue = false;

        //「÷」「剰余」のとき
        if (inputWay == 4 || inputWay == 5) {
            //かつ直近の値が 0で inputListの入力が３つ以上のとき
            if(inputNum == 0 && inputList.size() >= 3) {
                System.out.println("< ！ > ０で割ることはできません。\n");

                //直近の値をリストから消去
                inputList.remove(inputList.size() - 1);
                textArea.deleteCharAt(textArea.length() - 1);

                isContinue = true;
            }//if
        }//if

        return isContinue;
    }//exceptZero()


    //====== 不正値チェック([0]～[9])======
    private boolean checkWay() {
        boolean isContinue = false;

        if (0 <= inputWay && inputWay <= 9) {
            ;
        } else {
            System.out.println("<！> [0]～[9]で入力してください \n");
            isContinue =true;
        }

        //---- [6]～[9] 未対応 ----
        if (0 <= inputWay && inputWay <= 5) {
            ;
        } else {
            System.out.println("<！> [6]～[9]は現在 未対応です。\n");
            isContinue =true;
        }

        //---- 不正値チェック (値１つなのに「＝」) ----
        if(inputWay == 0 && inputList.size() <= 2) {
            System.out.println("< ！ > 値が１つだと計算できません。\n");
            isContinue =true;
        }//if

        return isContinue;
    }//checkWay()


    //====== inputWayの数字から記号に変換 ======
    private String textFormat(int inputWay) {
        String[] calcArray = new String[] {
            " ＝ "," ＋ "," ― "," × "," ÷ ", " ％(剰余) "
        };
        String wayStr = calcArray[inputWay];

        return wayStr;
    }//textFormat()


    //====== 計算方法の表示 ======
    private void printCalcWay() {
        //初回のみ計算方法の作成
        if (CALC_WAY == null) {
            StringBuilder bld = new StringBuilder();
            bld.append("[1] ＋ , [2] ― , [3] × , [4] ÷ ,[5]剰余 \n");
            bld.append("[6] Ｃ , [7] ← , [8]小数, [9] Ｍ ,[0] ＝  \n");
            bld.append("計算方法を選んでください。[0]～[9] \n");

            CALC_WAY = bld.toString();
        }

        System.out.print(CALC_WAY);
    }//printCalcWay()

    //====== 最終結果を表示 ====
    public void printResult(int result) {
        System.out.println("◆計算結果");
        System.out.print(textArea.toString());
        System.out.println(result);
    }//printResult()

}//class

/*
//###### inputLoop() as for main() Test ######
public class MachineLogic {
private static List<Integer> inputList; //user inputの履歴を保存
private static StringBuilder textArea;   //入力途中の計算式を保存
private static int inputNum; //計算のオペランド(リテラル)を入力
private static int inputWay; //計算方法を数字で入力
private static String CALC_WAY;   //計算方法の文字列(固定値)
private static Scanner scn;

//public MachineLogic() {
//    inputList = new ArrayList<Integer>(32);
//    textArea = new StringBuilder();
//    inputNum = 0;
//    inputWay = 0;
//}
//
//public List<Integer> inputLoop(){
  public static void main(String[] args) {
      inputList = new ArrayList<Integer>(32);
      textArea = new StringBuilder();
      inputNum = 0;
      inputWay = 0;

    //====== 適正値まで入力ループ ======
    input:
    while(true) {
        scn = new Scanner(System.in);
        boolean isContinue = false;

        try {
            //input 偶数回のみ値の入力から、奇数回は計算方法から
            if(inputList.size() % 2 == 0) {

                //---- inputNum ----
                System.out.print("値を入力してください。");

                //初回でなければ、入力経過の表示
                if(!(inputList.isEmpty())){
                    System.out.printf("\n〔 %s 〕", textArea.toString());
                }

                inputNum = scn.nextInt();
                System.out.println();

                //---- add inputNum to inputList and textArea ----
                inputList.add(inputNum);
                textArea.append(inputNum);
            }//if

            //---- 不正値チェック (０除算)----
            isContinue = exceptZero();

            if (isContinue) {
                continue input;
            }

            //---- print CALC_WAY and textArea ----
            printCalcWay();
            System.out.printf("〔 %s 〕", textArea.toString());

            //---- inputWay ----
            inputWay = scn.nextInt();
            System.out.println();

        //---- 不正値チェック(非整数) ----
        } catch (InputMismatchException e) {
            System.out.println("<！> 整数で入力してください。\n");
            continue input;
        }

        //---- 不正値チェック([0]～[9]) ----
        //---- [6]～[9] 未対応 ----
        //---- 不正値チェック (値１つなのに「＝」) ----
        isContinue = checkWay();

        if (isContinue) {
            continue input;
        }

        //---- add inputWay to inputList and textArea----
        inputList.add(inputWay);

        String wayStr = textFormat(inputWay);
        textArea.append(wayStr);

        //「＝」のとき while inputループを抜ける
        //値が１つだと計算不可なので入力継続
        if (inputList.size() < 4) {
            continue input;

        } else if (inputWay == 0 && inputList.size() >= 4) {
            System.out.printf("〔 %s 〕\n", textArea.toString());
            break input;
        }
    }//while input

    //==== main() Test Print ====
    System.out.println("inputLoop() 終了");
    System.out.println("inputList: " + inputList);

    scn.close();
    //return this.inputList;
}//inputLoop()


//====== 不正値チェック (０除算)======
private static boolean exceptZero() {
    boolean isContinue = false;

    //「÷」「剰余」のとき
    if (inputWay == 4 || inputWay == 5) {
        //かつ直近の値が 0で inputListの入力が３つ以上のとき
        if(inputNum == 0 && inputList.size() >= 3) {
            System.out.println("< ！ > ０で割ることはできません。\n");

            //直近の値をリストから消去
            inputList.remove(inputList.size() - 1);
            textArea.deleteCharAt(textArea.length() - 1);

            isContinue = true;
        }//if
    }//if

    return isContinue;
}//exceptZero()


//====== 不正値チェック([0]～[9])======
private static boolean checkWay() {
    boolean isContinue = false;

    if (0 <= inputWay && inputWay <= 9) {
        ;
    } else {
        System.out.println("<！> [0]～[9]で入力してください \n");
        isContinue =true;
    }

    //---- [6]～[9] 未対応 ----
    if (0 <= inputWay && inputWay <= 5) {
        ;
    } else {
        System.out.println("<！> [6]～[9]は現在 未対応です。\n");
        isContinue =true;
    }

    //---- 不正値チェック (値１つなのに「＝」) ----
    if(inputWay == 0 && inputList.size() <= 2) {
        System.out.println("< ！ > 値が１つだと計算できません。\n");
        isContinue =true;
    }//if

    return isContinue;
}//checkWay()


//====== inputWayの数字から記号に変換 ======
private static String textFormat(int inputWay) {
    String[] calcArray = new String[] {
        " ＝ "," ＋ "," ー "," × "," ÷ ", " ％(剰余) "
    };
    String wayStr = calcArray[inputWay];

    return wayStr;
}//textFormat()


//====== 計算方法の表示 ======
private static void printCalcWay() {
    //初回のみ計算方法の作成
    if (CALC_WAY == null) {
        StringBuilder bld = new StringBuilder();
        bld.append("[1] ＋ , [2] ー , [3] × , [4] ÷ ,[5]剰余 \n");
        bld.append("[6] Ｃ , [7] ← , [8]小数, [9] Ｍ ,[0] ＝  \n");
        bld.append("計算方法を選んでください。[0]～[9] \n");

        CALC_WAY = bld.toString();
    }

    System.out.print(CALC_WAY);
}//printCalcWay()

}//class
*/
/*
//====== inputLoop() as for main() 実行結果 ======
値を入力してください。3

[1] ＋ , [2] ー , [3] × , [4] ÷ ,[5]剰余
[6] Ｃ , [7] ← , [8] (  , [9] )  ,[0] ＝
計算方法を選んでください。[0]～[9]
〔 3 〕3

値を入力してください。
〔 3 ×  〕4

[1] ＋ , [2] ー , [3] × , [4] ÷ ,[5]剰余
[6] Ｃ , [7] ← , [8] (  , [9] )  ,[0] ＝
計算方法を選んでください。[0]～[9]
〔 3 × 4 〕7

<！> [6]～[9]は現在 未対応です。

[1] ＋ , [2] ー , [3] × , [4] ÷ ,[5]剰余
[6] Ｃ , [7] ← , [8] (  , [9] )  ,[0] ＝
計算方法を選んでください。[0]～[9]
〔 3 × 4 〕-1

<！> [0]～[9]で入力してください

[1] ＋ , [2] ー , [3] × , [4] ÷ ,[5]剰余
[6] Ｃ , [7] ← , [8] (  , [9] )  ,[0] ＝
計算方法を選んでください。[0]～[9]
〔 3 × 4 〕4

値を入力してください。
〔 3 × 4 ÷  〕0

< ！ > ０で割ることはできません。

値を入力してください。
〔 3 × 4 ÷  〕2

[1] ＋ , [2] ー , [3] × , [4] ÷ ,[5]剰余
[6] Ｃ , [7] ← , [8] (  , [9] )  ,[0] ＝
計算方法を選んでください。[0]～[9]
〔 3 × 4 ÷ 2 〕0

〔 3 × 4 ÷ 2 ＝  〕
inputLoop() 終了
inputList: [3, 3, 4, 4, 2, 0]


//====== エラーテスト ======
値を入力してください。
〔 3 ÷  〕0

< ！ > ０で割ることはできません。

値を入力してください。
〔 3 ÷  〕4

計算方法を選んでください。[0]～[9]
〔 3 ÷ 4 〕0

〔 3 ÷ 4 ＝  〕
inputLoop() 終了
inputList: [3, 4, 4, 0]
------------------------------------------
値を入力してください。
〔 12 ％(剰余)  〕0

< ！ > ０で割ることはできません。

値を入力してください。
〔 12 ％(剰余)  〕4

〔 12 ％(剰余) 4 ＝  〕
inputLoop() 終了
inputList: [12, 5, 4, 0]

------------------------------------------
値を入力してください。値
<！> 整数で入力してください。

値を入力してください。－１
<！> 整数で入力してください。

値を入力してください。-1

計算方法を選んでください。[0]～[9]
〔 -1 ー 3 〕＝
<！> 整数で入力してください。

[1] ＋ , [2] ー , [3] × , [4] ÷ ,[5]剰余
[6] Ｃ , [7] ← , [8] (  , [9] )  ,[0] ＝
計算方法を選んでください。[0]～[9]
〔 -1 ー 3 〕-1

<！> [0]～[9]で入力してください

[1] ＋ , [2] ー , [3] × , [4] ÷ ,[5]剰余
[6] Ｃ , [7] ← , [8] (  , [9] )  ,[0] ＝
計算方法を選んでください。[0]～[9]
〔 -1 ー 3 〕6

<！> [6]～[9]は現在 未対応です。

〔 -1 ー 3 ＝  〕
inputLoop() 終了
inputList: [-1, 2, 3, 0]

------------------------------------------
値を入力してください。4

計算方法を選んでください。[0]～[9]
〔 4 〕0

< ！ > 値が１つだと計算できません。

〔 4 ＋ 2 ＝  〕
inputLoop() 終了
inputList: [4, 1, 2, 0]
------------------------------------------
*/
/* ProtoType(旧版)
//###### inputLoop() as for main() Test ######
public class MachineLogic {
  private static List<Integer> inputList; //user inputの履歴を保存
  private static StringBuilder textArea;   //入力途中の計算式を保存
  private static int inputNum; //計算のオペランド(リテラル)を入力
  private static int inputWay; //計算方法を数字で入力
  private static String CALC_WAY;   //計算方法の文字列(固定値)
  private static Scanner scn;


  public static void main(String[] args) {
      inputList = new ArrayList<Integer>(32);
      textArea = new StringBuilder();
      inputNum = 0;
      inputWay = 0;

      //==== user input until acceptable input / 適正値まで入力ループ ====
      input:
      while(true) {
          scn = new Scanner(System.in);

          try {
              //input 偶数回のみ値の入力から、奇数回は計算方法から
              if(inputList.size() % 2 == 0) {

                  //---- inputNum ----
                  System.out.print("値を入力してください。");

                  //初回でなければ、入力経過の表示
                  if(!(inputList.isEmpty())){
                      System.out.printf("\n〔 %s 〕", textArea.toString());
                  }

                  inputNum = scn.nextInt();
                  System.out.println();

                  //---- add inputNum to inputList and textArea ----
                  inputList.add(inputNum);
                  textArea.append(inputNum);
              }

              //---- 不正値チェック (０除算)----
              //「÷」「剰余」のとき
              if (inputWay == 4 || inputWay == 5) {
                  //かつ直近の値が 0で inputListの入力が４つ以上のとき
                  if(inputNum == 0 && inputList.size() >= 3) {
                      System.out.println("< ！ > ０で割ることはできません。\n");

                      //直近の値をリストから消去
                      inputList.remove(inputList.size() - 1);
                      textArea.deleteCharAt(textArea.length() - 1);

                      continue input;
                  }//if
              }//if

              //---- print CALC_WAY and textArea ----
              printCalcWay();
              System.out.printf("〔 %s 〕", textArea.toString());

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

          //inputWay [0]～[5]のみ対応、編集中
          if (0 <= inputWay && inputWay <= 5) {
              ;
          } else {
              System.out.println("<！> [6]～[9]は現在 未対応です。\n");
              continue input;
          }

          //---- add inputWay to inputList and textArea----
          inputList.add(inputWay);

          String wayStr = textFormat(inputWay);
          textArea.append(wayStr);

          //値が１つしかないと計算不可なので入力を続ける
          if(inputList.size() < 4) {
              //---- 不正値チェック (値１つなのに「＝」) ----
              if(inputWay == 0) {
                  System.out.println("< ！ > 値が１つだと計算できません。");

                  //直近の計算方法をリストから消去
                  inputList.remove(inputList.size() - 1);
                  textArea.delete(textArea.length() - 3, textArea.length());
              }

              continue input;
          }

          //「＝」のとき while inputループを抜ける
          if (inputWay == 0 && inputList.size() >= 4) {
              System.out.printf("〔 %s 〕\n", textArea.toString());
              break input;
          }
      }//while input

      //==== main() Test Print ====
      System.out.println("inputLoop() 終了");
      System.out.println("inputList: " + inputList);

      scn.close();
      //return this.inputList;
  }//main() for Test


  private static String textFormat(int inputWay) {
      String[] calcArray = new String[] {
          " ＝ "," ＋ "," ー "," × "," ÷ ", " ％(剰余) "
      };
      String wayStr = calcArray[inputWay];

      return wayStr;
  }//textFormat()

  //====== 計算方法の表示 ======
  private static void printCalcWay() {
      //初回のみ計算方法の作成
      if (CALC_WAY == null) {
          StringBuilder bld = new StringBuilder();
          bld.append("[1] ＋ , [2] ー , [3] × , [4] ÷ ,[5]剰余 \n");
          bld.append("[6] Ｃ , [7] ← , [8] (  , [9] )  ,[0] ＝  \n");
          bld.append("計算方法を選んでください。[0]～[9] \n");

          CALC_WAY = bld.toString();
      }

      System.out.print(CALC_WAY);
  }//printCalcWay()

}//class
*/