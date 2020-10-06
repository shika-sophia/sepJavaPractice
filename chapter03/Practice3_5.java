/**
 * @title javaPractice / chapter03 / 練習問題3-5 (p136) => Practice3_5
 * @content  配列, Scanner, try-catch
 * @author shika
 * @date 2020-10-06
 */

package javaPractice.chapter03;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Practice3_5 {

    public static void main(String[] args) {
        //---- menu作成 ----
        String[] menu = {"検索","登録","削除","変更"};

        StringBuilder bld = new StringBuilder();
        bld.append("[メニュー] ");

        for (int i = 0; i < menu.length; i++) {
            bld.append(i).append(":").append(menu[i]).append(" ");
        }//for

        //---- 入力準備 ----
        Scanner scn = new Scanner(System.in);
        int order = -1;

        try {
            System.out.print(bld.toString());
            order = scn.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("[エラー] 整数で入力してください。\n");
            System.exit(0);
        } finally {
            scn.close();
        }

        //---- order 分岐 ----
        String message = "";

        if (0 <= order && order <= 3) {
            message = menu[order] + "します。";
        } else {
            message = "終了します。";
        }

        System.out.println(message);

    }//main()

}//class

/*
[メニュー] 0:検索 1:登録 2:削除 3:変更 1
登録します。

[メニュー] 0:検索 1:登録 2:削除 3:変更 4
終了します。

[メニュー] 0:検索 1:登録 2:削除 3:変更 検索
[エラー] 整数で入力してください。
*/