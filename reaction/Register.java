/**
 * @title javaPractice / reaction / Register
 * @author Sumita-san, SepJava2020
 * @see AccountRegistrationMain.java
 * @see NameRegistration.java
 * @author forked from Sumita-san at 2020-11-02 / Revision by shika
 * @date 2020-11-02, 11-03
 */

package javaPractice.reaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Register {

    public static void main(String[] args) {
        //---- parameter definitions ----
        Scanner scn = null;
        String name = "";
        int year = 0;
        int month = 0;
        int day = 0;
        String address = "";

        //==== nameLoop until nameAcceptable ====
        nameLoop:
        while(true) {
            scn = new Scanner(System.in);
            System.out.print("お名前を入力してください。\n姓と名の間にスペースを入れてください。");
            name = scn.nextLine();
            System.out.println();

            //名前の適合判定
            boolean nameAccept = judgeName(name);

            //適合すれば while nameLoopを抜け。不適合は再入力へ
            if (nameAccept) {
                break nameLoop;
            }
        }//while nameLoop

        //==== birthLoop until birthAcceptable ====
        birthLoop:
        while(true) {
            scn = new Scanner(System.in);

            try {
                System.out.print("生まれた年は？");
                year = scn.nextInt();
                System.out.println();

                System.out.print("生まれた月は？");
                month = scn.nextInt();
                System.out.println();

                System.out.print("生まれた日は？");
                day = scn.nextInt();
                System.out.println();

            } catch (InputMismatchException e) {
                System.out.println("整数で入力してください。\n");
                continue birthLoop;
            }

            //生年月日の適合判定
            boolean birthAccept = judgeBirth(year, month, day);

            //適合すれば while birthLoopを抜け。不適合は再入力へ
            if (birthAccept) {
                break birthLoop;
            }
        }//while birthLoop

        //==== addressLoop until addressAcceptable ====
        addressLoop:
        while(true) {
            scn = new Scanner(System.in);
            System.out.print("メールアドレスを入力してください。");
            address = scn.nextLine();
            System.out.println();

            //メールの適合判定
            boolean addressAccept = judgeAddress(address);

            //適合すれば while addressLoopを抜け。不適合は再入力へ
            if (addressAccept) {
                break addressLoop;
            }
        }//while addressLoop

        //表示用の加工
        String birth = String.format("%d年 %d月 %d日", year, month, day);

        //データベース用の加工
        //LocalDate birthData = LocalDate.of(year, month, day);

        //==== 登録情報の表示 ====
        printRegister(name, birth, address);

        scn.close();
    }//main()


    private static boolean judgeName(String name) {
        boolean nameAccept = false;

        String[] nameBox = new String[2];//[0]姓, [1]名前

        name = (name.replace("　", " "));
        nameBox = name.split(" ");

        //単一判定の else ifを 複数判定の独立した if文に変更。
        //複数メッセージを記録するため String message = "";ではなく Listを定義
        List<String> message = new ArrayList<>();

        //---- 名前の判定 ----
        if (nameBox.length <= 1) {
            message.add("姓と名前の両方を入力してください。\n");
        }

        if (!(name.matches(".+\\s+.+"))) {
            message.add("空白(スペース)が無いため姓と名前を判別できません。\n");
        }

        //【註】条件式はなるべく否定語「!」がないほうが解りやすい。
        //それが難しいケースもあるが、肯定形で同じ意味になるならそちらを使う
        if (name.length() <= 2) {
            message.add("入力文字が少なすぎます。姓と名前の両方を入力してください。\n");
        }

        if (nameBox.length > 2) {
            message.add("空白が2回以上入っています。姓と名前の間に1回だけ入れてください。\n");
        }

        //---- 上記すべての条件に当てはまらなければ適正。どれかがあれば再入力----
        if(message.size() == 0) {
            nameAccept = true;
        } else {
            nameAccept = false;
        }

        //---- エラーメッセージの表示 ----
        printError(message);

        return nameAccept;
    }//judgeName()


    private static boolean judgeBirth(int year, int month, int day) {
        boolean birthAccept = false;

        //現在の年を取得
        LocalDate current = LocalDate.now();
        int	currentYear = current.getYear();

       //年月日の適正判定、３つとも適合する必要があるので独立した if文３つ
        List<String> message = new ArrayList<>();

        if (1800 < year && year <= currentYear) {
            ;
        } else {
            message.add("年の値が不正です。");
        }

        if (1 <= month && month <= 12) {
            ;
        } else {
            message.add("月の値が不正です。");
        }

        //year, monthが適正値ならその年月の最終日を取得
        int lastDay = 0;
        if (message.size() == 0) {
            LocalDate user = LocalDate.of(year, month, 1);
            lastDay = user.lengthOfMonth();
        }

        if (1 <= day && day <= lastDay) {
            ;
        }else {
            message.add("日の値が不正です。");
        }

        //---- ３つの条件に適合したか ----
        if (message.size() == 0) {
            birthAccept =true;
        } else {
            birthAccept =false;
        }

        //---- エラーメッセージの表示 ----
        printError(message);

        return birthAccept;
    }//judgeBirth()


    private static boolean judgeAddress(String address) {
        boolean addressAccept = false;

        //メールアドレスパターン[正規表現]
        String regex = "([a-zA-Z0-9.!#$&'*+/=?^_{|}~-]+)@([a-zA-Z0-9-]+)(\\.[a-zA-Z0-9-]+)*";

        //入力アドレスが上記パターンにマッチ(一致)するか boolenで返す
        addressAccept = Pattern.matches(regex, address);

        if(!addressAccept) {
            System.out.println("メールアドレスが不正です。\n");
        }

        return addressAccept;
    }//judgeAddress()


    //==== エラーメッセージの表示 ====
    private static void printError(List<String> message) {

        for (String str : message) {
            System.out.println("エラー！ " + str);
        }//for message
        System.out.println();

    }//printError()


    //登録内容の表示
    private static void printRegister(String name, String birth, String address) {
        StringBuilder bld = new StringBuilder();
        bld.append("お名前　: ").append(name).append("さん\n");
        bld.append("生年月日: ").append(birth).append("\n");
        bld.append("メール　: ").append(address).append("\n\n");

        //bld.append("この内容で登録してよろしいですか？");
        bld.append("登録しました。");

        System.out.println(bld.toString());
    }//printRegister()
}//class

/*
《解説》
・入力処理が両クラスにまたがり点在しているので、可能な限り１か所にまとめる。

・条件式を else ifで繋げる際、先に適合したものがあると以下の条件は判定されずにスルーされるので注意。
  複数に適合する可能性のある場合は、可能性の少ない条件を先に書くか、
  if文をそれぞれ独立させて複数適合可にする。

・同じ記述を繰り返している場合は、部品化して for文で回せないかを考える。

・この入力適合判定は HTML, JavaScriptで 不適合な入力を受け付けない機能を作ることができ、
通常はその機能を使ってアプリ制作をする。
しかし、不適合な入力を判定する内部的ロジック(論理)を自分で再現してみることは、
ＡＩ作りのいい練習になるだろう。


【考察】
・本来は登録前に「この内容でよろしいですか？」と聞いて
  YESなら登録、NOなら入力のやり直しとすべきところだが、whileループをもう１つ追加すればできるだろう。

・while(true) のループは３つあるので、部品化して１つにできるかもしれない。

・inputした nameを spilt()で分割していたので、本来は姓と名を別々に登録可能だが、
  データーベースなどへに登録のための情報加工は別クラスに譲り、
  このクラスは入力ループと適正判定に特化したほうが、のちのち他アプリにも転用しやすかろう。

・いろいろ追加機能や発展の可能性はあるが、今回は登録情報の適正判定という基本機能を実現できたので良しとしよう。


//==== 実行結果 ====
お名前を入力してください。
姓と名の間にスペースを入れてください。大鹿 聡

生まれた年は？1972

生まれた月は？11

生まれた日は？24

メールアドレスを入力してください。shika@dammy.com

お名前　: 大鹿 聡さん
生年月日: 1972年 11月 24日
メール　: shika@dammy.com

登録しました。

//==== エラーテスト ====
お名前を入力してください。
姓と名の間にスペースを入れてください。お

エラー！ 姓と名前の両方を入力してください。

エラー！ 空白(スペース)が無いため姓と名前を判別できません。

エラー！ 入力文字が少なすぎます。姓と名前の両方を入力してください。

お名前を入力してください。
姓と名の間にスペースを入れてください。
---------------------------
お名前を入力してください。
姓と名の間にスペースを入れてください。さ ん ま

エラー！ 空白が2回以上入っています。姓と名前の間に1回だけ入れてください。

お名前を入力してください。
姓と名の間にスペースを入れてください。
---------------------------
生まれた年は？昭和47年
整数で入力してください。

生まれた年は？year
整数で入力してください。

生まれた年は？
---------------------------
生まれた年は？1192

生まれた月は？-1

生まれた日は？33

エラー！ 年の値が不正です。

エラー！ 月の値が不正です。

エラー！ 日の値が不正です。

生まれた年は？
----------------------------
生まれた年は？1980

生まれた月は？2

生まれた日は？30

エラー！ 日の値が不正です。

生まれた年は？

-----------------------------
メールアドレスを入力してください。shika-mail

メールアドレスが不正です。

メールアドレスを入力してください。
-----------------------------
*/