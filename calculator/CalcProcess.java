/**
 * @title javaPractice / calculator / CalcProcess
 * @see {@link CalculatorMain}, {@link MachineLogic}
 * @author shika
 * @date 2020-11-07
 */

package javaPractice.calculator;

public class CalcProcess {
    private int result;     //計算結果を保持

    public CalcProcess() {
        result = 0;
    }

    //====== calc methods of (int, int) ======
    public void calcAdd(int x, int y) {
        result = x + y;
    }//calcAdd()

    public void calcSubstract(int x, int y) {
        result = x - y;
    }//calcSubstract()

    public void calcMultiply(int x, int y) {
        result = x * y;
    }//calcMultiply()

    public void calcDevide(int x, int y) {
        //---- execpt 0 devide ----(input時に検査済)
        //if (y == 0) {
        //    System.out.println("<！> 0 で割ることはできません。\n");
        //    System.exit(0);
        //}

        //---- calc and set result ----
        result = x / y;
    }//calcDevide()

    public void calcRest(int x, int y) {
        //---- execpt 0 devide ----(input時に検査済)
        //if (y == 0) {
        //    System.out.println("<！> 0 で割ることはできません。\n");
        //    System.exit(0);
        //}

        //---- calc and set result ----
        result = x % y;
    }//calcRest()


    //====== getter ======
    public int getResult() {
        return result;
    }

}//class CalcProcess
