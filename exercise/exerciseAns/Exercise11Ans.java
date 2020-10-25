/**
 * @title javaPractice / exercise / Exercise11Ans
 * @content 配列, Random
 * @author shika
 * @date 2020-10-25
 */
/*
 * ◆Exercise11
 * Ramdomクラスをimportし、インスタンスする。
 * 乱数の結果によって"グー","チョキ","パー"のいずれかを表示する。
 *
 */
package javaPractice.exercise;

import java.util.Random;

public class Exercise11Ans {

    public static void main(String[] args) {
        String[] finger = new String[] {"グー","チョキ","パー"};

        Random rdm = new Random();

        //↓解答欄ここから
        int dice = rdm.nextInt(3);

        System.out.println(finger[dice]);
    }//main()

}//class

/*
チョキ

チョキ

グー

パー
*/