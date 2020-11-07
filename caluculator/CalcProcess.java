/**
 * @title javaPractice / calculator / CalcProcess
 * @see {@link CalculatorMain}, {@link MachineLogic}
 * @author shika
 * @date 2020-11-07
 */

package javaPractice.caluculator;

public class CalcProcess {
    private int result;     //計算結果を保持
    private int prevResult; //前の計算結果を保持

    public CalcProcess() {
        result = 0;
        prevResult = 0;
    }

    //====== calc methods of (int, int) ======
    public boolean calcAdd(int x, int y) {
        prevResult = result;
        result = x + y;
        return true;
    }//calcAdd()

    public boolean calcSubstract(int x, int y) {
        prevResult = result;
        result = x - y;
        return true;
    }//calcSubstract()

    public boolean calcMultiply(int x, int y) {
        prevResult = result;
        result = x * y;
        return true;
    }//calcMultiply()

    public boolean calcDevide(int x, int y) {
        //---- execpt 0 devide ----
        if (y == 0) {
            System.out.println("<！> 0 で割ることはできません。\n");
            return false;
        }
        //---- calc and set result ----
        prevResult = result;
        result = x / y;

        return true;
    }//calcDevide()

    public boolean calcRest(int x, int y) {
        //---- execpt 0 devide ----
        if (y == 0) {
            System.out.println("<！> 0 で割ることはできません。\n");
            return false;
        }
        //---- calc and set result ----
        prevResult = result;
        result = x % y;

        return true;
    }//calcRest()


    //====== getter ======
    public int getResult() {
        return result;
    }

    public int getPrevResult() {
        return prevResult;
    }

}//class CalcProcess
