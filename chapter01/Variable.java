/**
 * @title javaPractice / chapter01 / code 1-2 (p046) => Variable
 * @content field, Scanner, call method, printf()
 * @author shika
 * @date 2020-10-05
 */

package javaPractice.chapter01;

import java.util.Scanner;

public class Variable {
    private static final double PI = 3.14d;

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        System.out.println("半径を入力してください");

        int r = scn.nextInt();
        System.out.printf("半径 %dの円の面積は%.2fです。", r, value(r));
        scn.close();
    }//main()

    private static Object value(int r) {
        return r * r * PI;
    }//value()

}//class

/*
半径を入力してください
5

半径 5の円の面積は78.50です。
 */