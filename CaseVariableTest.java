/**
 * @title javaPractice / CaseVariableTest
 * @content switchのcase句に、ランダムで生成した定数を使えるかどうかの実験
 * @Thanks Nakayama-san, SepJava2020
 * @author shika
 * @date 2020-10-10
 */

package javaPractice;

/*
//###### ローカル変数 version ######
public class CaseVariableTest {

    public static void main(String[] args) {
        int num = 1;
        final int ANS = 1;

        switch(num) {
        case ANS:
            System.out.println("case句に final変数の実験成功");
            break;

        default:
            System.out.println("実験失敗");

        }//switch

    }//main()

}//class
*/
/*
case句に final変数の実験成功
 */

//###### フィールド version ######
public class CaseVariableTest {
    private static final int ANS = 1;

    public static void main(String[] args) {
        int num = 1;

        switch(num) {
        case ANS:
            System.out.println("case句に final変数の実験成功");
            break;

        default:
            System.out.println("実験失敗");

        }//switch

    }//main()

}//class

/*
case句に final変数の実験成功

*/
/*
//###### ローカル変数にインスタンス version ######
import java.util.Random;

public class CaseVariableTest {

    public static void main(String[] args) {
        int num = 1;
        Random rdm = new Random();
        final int ANS = rdm.nextInt();

        switch(num) {
        case ANS://コンパイルエラー:「 case式は定数式でなければいけません」
            System.out.println("case句に final変数の実験成功");
            break;

        default:
            System.out.println("実験失敗");

        }//switch

    }//main()

}//class

//###### Result ######
//コンパイルエラー:「 case式は定数式でなければいけません」
*/
/*
//###### フィールド・インスタンス version ######
 *
import java.util.Random;

public class CaseVariableTest {
    private static Random rdm = new Random();
    private static final int ANS = rdm.nextInt();

    public static void main(String[] args) {
        int num = 1;


        switch(num) {
        case ANS://コンパイルエラー:「 case式は定数式でなければいけません」
            System.out.println("case句に final変数の実験成功");
            break;

        default:
            System.out.println("実験失敗");

        }//switch

    }//main()

}//class
*/
//###### Result ######
//コンパイルエラー:「 case式は定数式でなければいけません」
/*
public class CaseVariableTest {
    private static Random rdm = new Random();
    private static final int answer = rdm.nextInt();
    private static final int ANS = answer;

    public static void main(String[] args) {
        int num = 1;


        switch(num) {
        case ANS://コンパイルエラー:「 case式は定数式でなければいけません」
            System.out.println("case句に final変数の実験成功");
            break;

        default:
            System.out.println("実験失敗");

        }//switch

    }//main()

}//class
*/
//###### Result ######
//コンパイルエラー:「 case式は定数式でなければいけません」