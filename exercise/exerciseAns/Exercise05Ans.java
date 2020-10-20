/**
 * @title javaPractice / exercise / Exercise05Ans
 * @content コマンドライン引数, if, args.length, Integer.parseInt()
 * @author shika
 * @date 2020-10-19
 */
/*
◆Exercise05
Exercise03 または Exercise04と同じ動作をコマンドライン引数で行いたい。

引数を３つ入れずに実行すると以下のメッセージが出て終了する。

>java Exercise05
引数を３つ入れて実行してください。
実行例: >java Exercise05 3 4 1
第１引数: xの値 / 第２引数: yの値 / 第３引数: 計算方法
計算方法: [1]足し算, [2]引き算, [3]掛け算, [4]割り算

〔ヒント〕
・コマンドライン引数の個数は args.length で求められる。
・System.exit(0);を記述するとプログラムがここに来た時点で終了する。
・第１引数の値は args[0]で取得できるが、void main(String[] args)と書いてある通り、
  args[0]のデータ型は Stringなので、数値計算に利用するには、もうひと工夫必要。

・コンパイルするなら -encodingを忘れずに
 >javac Exercise05.java -encoding UTF-8

・switch文と計算メソッドは Exercise03 or 04 を そのまま使いましょう。
   Scanner部分をコマンドライン引数に置き換えて、
   args.lengthによっては註釈を表示する条件を考える問題
 */
package javaPractice.exercise;

public class Exercise05Ans {

    public static void main(String[] args) {
        //引数が３つでないときは使い方を説明して終了。
        if(args.length != 3) {
            System.out.println("引数を３つ入れて実行してください。");
            System.out.println("実行例: >java Exercise05 3 4 1");
            System.out.println("第１引数: xの値 / 第２引数: yの値 / 第３引数: 計算方法");
            System.out.println("計算方法: [1]足し算, [2]引き算, [3]掛け算, [4]割り算");
            System.exit(0);
        }//if

        //try{}ブロックの外でも、これらの変数を使いたいので、外で変数宣言。値は初期値のダミー。
        int x = 0;
        int y = 0;
        int calc = 0;

        try { //try-catchは、いずれ学習するので気にしないで。
              //例外(エラー)が出て途中で止まるのを防止しています。
            x = Integer.parseInt(args[0]);
            y = Integer.parseInt(args[1]);
            calc = Integer.parseInt(args[2]);

        } catch (NumberFormatException e) {
            System.out.println("引数は整数で入力してください。");
            System.exit(0);
        }

        //---- 以下、switch文と各メソッドはExercise03Ansと同様 ----
        int result = 0;
        switch(calc) {
        case 1:
            result = calcAdd(x, y);
            break;
        case 2:

            result = calcSubstract(x, y);
            break;

        case 3:

            result = calcMultiply(x, y);
            break;
        case 4:

            result = calcDevide(x, y);
            break;

        default:
            System.out.println("1～4の数字で入力してください。");
            System.exit(0);
        }//switch

    System.out.println("計算結果は " + result);
    }//main()


    private static int calcAdd(int x, int y) {
        return x + y;
    }//calcAdd()

    private static int calcSubstract(int x, int y) {
        return x - y;
    }//calcSubstract()

    private static int calcMultiply(int x, int y) {
        return x * y;
    }//calcMultiply()

    private static int calcDevide(int x, int y) {
        if (y == 0) {
            System.out.println("0で割ることはできません。");
            System.exit(0);
        }//if

        return x / y;
    }//calcDevide()

}//class

/*
◇コマンドプロンプトでコンパイル・実行
C:\Users\6A16\Desktop>javac Exercise05.java -encoding UTF-8

C:\Users\6A16\Desktop>java Exercise05
引数を３つ入れて実行してください。
実行例: >java Exercise05 3 4 1
第１引数: xの値, 第２引数: yの値, 第３引数: 計算方法
計算方法: [1]足し算, [2]引き算, [3]掛け算, [4]割り算

C:\Users\6A16\Desktop>java Exercise05 3 4 3
計算結果は 12

C:\Users\6A16\Desktop>java Exercise05 サン よん 1
引数は整数で入力してください。

C:\Users\6A16\Desktop>java Exercise05 2 5 15
1～4の数字で入力してください。

C:\Users\6A16\Desktop>java Exercise05 7 0 4
0で割ることはできません。
*/