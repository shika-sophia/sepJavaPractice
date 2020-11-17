package webPractice.prime.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrimeLogic {


    //====== 計算方法の分岐 -> data.fieldに格納 ======
    public void calcWay(Integer x, Integer y, String calcWay, PrimeData data) {

        switch(calcWay) {
        case "prime"://素数
            List<Integer> xPrime = calcPrime(x, calcWay);
            List<Integer> yPrime = calcPrime(y, calcWay);
            data.setxPrime(xPrime);
            data.setyPrime(yPrime);
            break;

        case "divisor"://約数
            List<Integer> xDivisor = calcPrime(x, calcWay);
            List<Integer> yDivisor = calcPrime(y, calcWay);
            data.setxDivisor(xDivisor);
            data.setyDivisor(yDivisor);
            break;

        case "multiple"://倍数
            List<Integer> xMultiple = calcMultiple(x, data);
            List<Integer> yMultiple = calcMultiple(y, data);
            data.setxMultiple(xMultiple);
            data.setyMultiple(yMultiple);
            break;

        case "gcd"://ＧＣＤ: 最大公約数 Greatest Common Divisor
            List<Integer> commonDivisor = calcGCD(x, y);
            data.setCommonDivisor(commonDivisor);
            break;

        case "lcm"://ＬＣＭ: 最小公倍数 Least Common Multiple
            calcLCM(x, y, data);
            break;

        default:  //none
            throw new IllegalArgumentException("in logic.calcWay().swich(calcWay)");

        }//switch
    }//calcWay


    //====== 素数と約数の抽出は同じメソッドで行う ======
    private List<Integer> calcPrime(Integer num, String calcWay) {
        //ローカル用 Listのインスタンス
        List<Integer> primeList = new ArrayList<>(num);
        List<Integer> divisorList = new ArrayList<>(num);

        // 0 から num までの素数を Listに格納
        for (int i = 0; i < num; i++) {
            divisorList.clear();
            divisorList.add( 1 );

            //割り切れる数は約数
            for (int j = 2; j <= num; j++) {
                if (num % j == 0) {
                    divisorList.add(j);
                }
            }//for j

            //約数が２個(1とその数自身)なら素数
            if(divisorList.size() == 2) {
                primeList.add(i);
            }
        }

        if(calcWay.equals("prime")) {
            return primeList;
        }

        return divisorList;
    }//calcPrime()


    //====== 倍数の抽出 (BOUNDまで)======
    private List<Integer> calcMultiple(Integer num ,PrimeData data) {
        int BOUND = data.getBOUND();

        //ローカル用 List
        List<Integer> multipleList = new ArrayList<>(BOUND);

        // 1から BOUNDまでのを numに掛け算
        for(int i = 1; i < BOUND; i++) {
            multipleList.add(num * i);
        }//for i

        return multipleList;
    }//calcMultiple()


    //====== 公約数 ======
    //最大公約数は表示の際に抽出
    private List<Integer> calcGCD(Integer x, Integer y) {
        //各約数を取得
        String calcWay = "divisor";
        List<Integer> xDivisor = calcPrime(x, calcWay);
        List<Integer> yDivisor = calcPrime(y, calcWay);

        List<Integer> commonDivisor = retainList(xDivisor, yDivisor);

        return commonDivisor;
    }//calcGCD()


    //====== 公倍数 ======
    //最小公倍数は表示の際に抽出
    private List<Integer> calcLCM(Integer x, Integer y, PrimeData data) {
        List<Integer> xMultiple = calcMultiple(x, data);
        List<Integer> yMultiple = calcMultiple(y ,data);

        List<Integer> commonMultiple = retainList(xMultiple, yMultiple);

        return commonMultiple;
    }//calcLCM()


    //====== ListからSetにして -> 積集合 commonList ======
    private List<Integer> retainList(List<Integer> list1, List<Integer> list2){
        //Setに入れて積集合
        Set<Integer> set1 = new HashSet<>(list1);
        Set<Integer> set2 = new HashSet<>(list2);
        set1.retainAll(set2); //retainAll() Setの積集合

        //Listに再置換、sort()して昇順に
        List<Integer> common = new ArrayList<>(set1);
        Collections.sort(common);

        return common;
    }//retainList()

}//class
