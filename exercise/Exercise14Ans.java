/**
 * @title javaPractice / exercise / Exercise14Ans
 * @content
 * @author shika
 * @date 2020-10-30
 */

package javaPractice.exercise;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Exercise14Ans {

    public static void main(String[] args) {
        final int BOUND = 99; //0～BOUND answerの上限値

        //正当可能回数の計算
        int inputTimes = calcTimes(BOUND);
        int[] input = new int[inputTimes];

        //---- answer ランダム生成 ----
        Random rdm = new Random();
        int answer = rdm.nextInt(BOUND + 1);

        for (int i = 0; i < input.length; i++) {

            userInput:
            while(true) {
                Scanner scn = new Scanner(System.in);

                try {
                    System.out.printf("◆%d2回目の回答 [0～%d] ", i, BOUND);
                    input[i] = scn.nextInt();

                } catch (InputMismatchException e) {
                    System.out.println("整数で入力してください。");
                    continue userInput;
                }

                //---- judge adaptable input / inputの適合判定 ----
                if (0 <= input[i] && input[i] <= BOUND) {
                    ;
                    break userInput;
                } else {
                    System.out.printf(" 0 ～ %dで入力してください。\n", BOUND);
                    continue userInput;
                }
            }//while userInput

        }//for i
    }//main()


    private static int calcTimes(int BOUND) {
        int count = 0;
        loop:
        while(true) {
        //2で割り続けて余り1 or 0になるまで続ける。その回数をカウント
        //その回数分をinputできれば、必ずanswerを当てられる。
            int devide = BOUND / 2;
            count++;

            if (devide == 1 || devide == 0) {
                break loop;
            }
        }//while loop

        return count;
    }//calcTimes()

}//class
