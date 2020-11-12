/**
 * @title javaPractice / calculator / CalcDouble
 * @see CalculatorMain2nd
 * @author shika
 * @date 2020-11-08, 11-09
 */

package javaPractice.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalcDouble {
    String result;
    private BigDecimal xBD;
    private BigDecimal yBD;
    private BigDecimal zeroBD;

    CalcDouble(){
        result = "";
        zeroBD = new BigDecimal("0");
    }

    //double -> String -> BigDecimal -> フィールド
    private void toBigDecimal(double x, double y) {
        String xStr = String.valueOf(x);
        String yStr = String.valueOf(y);

        this.xBD = new BigDecimal(xStr);
        this.yBD = new BigDecimal(yStr);
    }//toBigDecimal()


    public String calcAdd(double x, double y){
        //BigDecimalに変換
        toBigDecimal(x, y);

        //演算処理 x + y
        result = xBD.add(yBD).toString();

        return result;
    }//calcAdd()


    public String calcSubtract(double x, double y){
        //BigDecimalに変換
        toBigDecimal(x, y);

        //演算処理 x - y
        result = xBD.subtract(yBD).toString();

        return result;
    }//calcSubtract()


    public String calcMultiply(double x, double y){
        //BigDecimalに変換
        toBigDecimal(x, y);

        //演算処理 x * y
        result = xBD.multiply(yBD).toString();

        return result;
    }//calcMultiply()


    public String calcDivide(double x, double y){
        //BigDecimalに変換
        toBigDecimal(x, y);

        //BigDecimalの比較 一致 -> 0
        if (yBD.compareTo(zeroBD) == 0) {
            result = "< ！ > ０で割ることはできません。\n";
            return result;
        }

        //演算処理 x / y
        //商が無限小数になると ArithmeticExceptionが発生するの小数点４位で丸め処理
        result = xBD.divide(yBD, 5,RoundingMode.HALF_UP).toString();

        return result;
    }//calcDivide()


    public String calcRest(double x, double y){
        //BigDecimalに変換
        toBigDecimal(x, y);

        //BigDecimalの比較 一致 -> 0
        if (yBD.compareTo(zeroBD) == 0) {
            result = "< ！ > ０で割ることはできません。\n";
            return result;
        }

        //演算処理 x % y
        result = xBD.remainder(yBD).toString();

        return result;
    }//calcRest()

}//class

/*
【資料】
◆山田祥寛『独習Ｊａｖａ 新版』翔泳社, 2019 / p87, p93

◆正確に計算を行う！Javaでbigdecimalを使う方法【初心者向け】
https://techacademy.jp/magazine/18597

◆Javaプログラマーのためのjava.math.BigDecimalまとめ
http://kidotaka.hatenablog.com/entry/2012/11/05/112139

◆BigIntegerクラス
http://www1.megaegg.ne.jp/~yasu/ProgrammerPage/math/BigInteger.html


◆【Java入門】BigDecimalで小数点以下を誤差なく計算する方法
https://www.sejuku.net/blog/21804

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        // double型で算術演算子を使う場合
        double d1 = 10.0;
        double d2 = 3.0;
        System.out.println(d1 + " ÷ " + d2 + " = " + (d1 / d2));

        // BigDecimalコンストラクタの引数にdouble型を使用する場合
        BigDecimal bd1 = new BigDecimal(10.0);
        BigDecimal bd2 = new BigDecimal(3.0);
        BigDecimal result1 = bd1.divide(bd2, 1, BigDecimal.ROUND_HALF_UP);
        System.out.println(bd1 + " ÷ " + bd2 + " = " + result1);

        // BigDecimalコンストラクタの引数にString型を使用する場合
        BigDecimal bd3 = new BigDecimal("10.0");
        BigDecimal bd4 = new BigDecimal("3.0");
        BigDecimal result2 = bd3.divide(bd4, 1, BigDecimal.ROUND_HALF_UP);
        System.out.println(bd3 + " ÷ " + bd4 + " = " + result2);
    }
}

◆[ Java ] String.Format は小数点の末端の桁は四捨五入される。切り捨てるには？
http://hensa40.cutegirl.jp/archives/5620

float halfUp = 99.455f; // 小数点第3位が5
float halfDn = 99.454f; // 小数点第3位が4

// 四捨五入されて指定の桁数にまるめられる模様
String strUp = String.format("%.2f", halfUp); // 99.46
String strDn = String.format("%.2f", halfDn); // 99.45

// BigDecimal 型に変換
BigDecimal bd = new BigDecimal(halfUp);

// 小数点第3位で切り捨てて総数点2桁にまるめる
bd = bd.setScale(2, BigDecimal.ROUND_FLOOR);
String.format("%.2f", bd); // 99.45

// BigDecimal型の場合、文字列への出力は以下のようにも記述できる、
// str = bd.setScale(2, BigDecimal.ROUND_FLOOR).toString();
// str = bd.setScale(2, BigDecimal.ROUND_FLOOR).toPlainString();

*/