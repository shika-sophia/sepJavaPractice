package webPractice.prime.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrimeLogic {


    //====== 計算方法の分岐 -> data.fieldに格納 ======
    public void calcWay(Integer x, Integer y, String calcWay, PrimeData data) {
        String calcWayJP = "";
        List<Integer> listX = new ArrayList<>();
        List<Integer> listY = new ArrayList<>();
        List<Integer> listZ = new ArrayList<>();

        switch(calcWay) {
        case "prime":
            calcWayJP = "素数";
            listX = calcPrime(x, calcWay);
            listY = calcPrime(y, calcWay);
            data.setxPrime(listX);
            data.setyPrime(listY);
            break;

        case "divisor":
            calcWayJP = "約数";
            listX = calcPrime(x, calcWay);
            listY = calcPrime(y, calcWay);
            data.setxDivisor(listX);
            data.setyDivisor(listY);
            break;

        case "multiple":
            calcWayJP = "倍数";
            listX = calcMultiple(x, data);
            listY = calcMultiple(y, data);
            data.setxMultiple(listX);
            data.setyMultiple(listY);
            break;

        case "gcd"://ＧＣＤ: 最大公約数 Greatest Common Divisor
            calcWayJP = "最大公約数";
            listX = calcPrime(x, "divisor");
            listY = calcPrime(y, "divisor");
            listZ = retainList(listX, listY);
            data.setCommonDivisor(listZ);
            break;

        case "lcm"://ＬＣＭ: 最小公倍数 Least Common Multiple
            calcWayJP = "最小公倍数";
            listX = calcMultiple(x, data);
            listY = calcMultiple(y ,data);
            listZ = retainList(listX, listY);
            data.setCommonMulitiple(listZ);
            break;

        default:  //none
            throw new IllegalArgumentException("in logic.calcWay().swich(calcWay)");
        }//switch

        //---- 結果表示を作成 ----
        String xResult= buildResult(x, "X", calcWayJP, listX);
        String yResult= buildResult(y, "Y", calcWayJP, listY);
        data.setxResult(xResult);
        data.setyResult(yResult);

        if (listZ.isEmpty()) {
            ;
        } else {
             String zResult= buildResult(x, y, calcWayJP, listZ);
             data.setzResult(zResult);
        }
    }//calcWay


    //====== 素数と約数の抽出は同じメソッドで行う ======
    private List<Integer> calcPrime(Integer num, String calcWay) {
        //ローカル用 Listのインスタンス
        List<Integer> primeList = new ArrayList<>(num);
        List<Integer> divisorList = new ArrayList<>(num);

        // 0 から num までの素数を Listに格納
        for (int i = 0; i <= num; i++) {
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
        }//for i

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


    //====== 結果表示を作成 ======
    public String buildResult(int num, String numStr, String calcWayJP, List<Integer> list) {
        StringBuilder bld = new StringBuilder();

        if (calcWayJP.equals("最大公約数") || calcWayJP.equals("最小公倍数")) {
            bld.append(String.format("◆%s / %s = %d <br>", calcWayJP.substring(3), numStr, num));
        } else {
            bld.append(String.format("◆%s / %s = %d <br>", calcWayJP, numStr, num));
        }

        if (list.isEmpty()) {
            bld.append(calcWayJP).append(" なし \n");

        } else {
            for(int i = 0 ; i < list.size(); i++) {
                bld.append(list.get(i)).append(" ");

                if (i % 9 == 1) {
                    bld.append("\n");
                }
            }//for i
        }
        String result = bld.toString();

        return result;
    }//buildResult()


    //====== 結果表示を作成 ======
    public String buildResult(int x, int y, String calcWayJP, List<Integer> list) {
        StringBuilder bld = new StringBuilder();

        bld.append(String.format("◆%s / Ｘ＝%d　Ｙ＝%d  <br>", calcWayJP.substring(2), x, y));

        for(int i = 0 ; i < list.size(); i++) {
            bld.append(list.get(i)).append("　");

            if (i % 9 == 1) {
                bld.append("<br>");
            }
        }//for i

        String result = bld.toString();
        return result;
    }//buildResult()
}//class
