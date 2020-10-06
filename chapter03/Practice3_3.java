/**
 * @title javaPractice / chapter03 / 練習問題3-3 (p135) => Practice3_3
 * @content if, Scanner, try-catch, while loop
 * @author shika
 * @date 2020-10-06
 */

package javaPractice.chapter03;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Practice3_3 {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int isHungry = -1;
        String food = "ラーメン";
        String message = "";

        //適切な入力があるまで無限ループ
        while(true) {
            try {
                System.out.println("◆お腹が空いていますか？[ 0: はい / 1: いいえ ]");
                isHungry = scn.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("[エラー] 数字で入力してください。\n");
                System.exit(0);
            }

            if(0 <= isHungry && isHungry <= 1) {
                if (isHungry == 0) {
                    message = String.format(
                        "はらぺこですね。\n%1$sを召し上がれ。\n%1$sをごちそうさまでした",
                        food);
                    break;

                } else if (isHungry == 1) {
                    message = String.format(
                        "お腹いっぱいですね。\n%1$sは要らないですね。\n%1$sは私が食べます。",
                        food);
                    break;

                } else {
                    continue;
                }
            } else {
                System.out.println("[エラー] 0か1で入力してください。\n");
                continue;
            }
        }//while loop

        //標準出力表示
        System.out.println(message);

        scn.close();
    }//main()

}//class

/*
// ====== Result ======
◆お腹が空いていますか？[ 0: はい / 1: いいえ ]
9
[エラー] 0か1で入力してください。

◆お腹が空いていますか？[ 0: はい / 1: いいえ ]
0
はらぺこですね。
ラーメンを召し上がれ。
ラーメンをごちそうさまでした

◆お腹が空いていますか？[ 0: はい / 1: いいえ ]
1
お腹いっぱいですね。
ラーメンは要らないですね。
ラーメンは私が食べます。


◆お腹が空いていますか？[ 0: はい / 1: いいえ ]
yes
[エラー] 数字で入力してください。<終了>

◆お腹が空いていますか？[ 0: はい / 1: いいえ ]
0.5
[エラー] 数字で入力してください。<終了>
*/