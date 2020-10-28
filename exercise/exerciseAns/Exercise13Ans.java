/**
 * @title javaPractice / exercise / Exercise13Ans
 * @content 配列, sum, average, max, min, printf, Arrays.copyOf(), Arrays.sort()
 *          《解説》複数行コメントアウト
 * @author shika
 * @date 2020-10-28
 */
/*
◆Exercise13
10個の値を入力を入力してもらい、配列に格納する。
その後、下記の実行結果のように数値の記録,合計,平均,最大,最小を表示せよ。

◇実行結果
◆10個の整数を入力してください。

 1個目の数値: 24
 2個目の数値: 36
 3個目の数値: 48
 4個目の数値: -64
 5個目の数値: 72
 6個目の数値: 81
 7個目の数値: 99
 8個目の数値: 7
 9個目の数値: -48
10個目の数値: 0

数値: [24, 36, 48, -64, 72, 81, 99, 7, -48, 0, ]
合計: 255
平均: 25.50
最大: 99
最小: -64
*/
package javaPractice.exercise;

//import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Exercise13Ans {

    public static void main(String[] args) {
        int[] input = new int[10];

        //==== 数値の入力 ====
        Scanner scn = new Scanner(System.in);
        System.out.printf("◆%d個の整数を入力してください。", input.length);
        System.out.println();

        for (int i = 0; i < input.length; i++) {

            //不正値チェック
            try {
                System.out.printf("%2d個目の数値: ", (i + 1));
                input[i] = scn.nextInt();

            } catch (InputMismatchException e ) {
                System.out.println("整数で入力してください。終了します。");
                System.exit(0);
            }
        }//for

        //==== 数値の計算 ====
        int sum = 0;         //合計
        double average = 0d;//平均
        int max = input[0]; //最大
        int min = input[0]; //最小

        //inputの全要素を取り出して各処理
        for (int i = 0; i < input.length; i++) {
            sum += input[i];

            if(input[i] >= max) {
                max = input[i];
            }

            if (input[i] <= min) {
                min = input[i];
            }
        }//for i

        average = (double)sum / input.length;

//        ====〔別解〕数値の計算 ====
//        Arraysクラスの sort()メソッドで簡単に最大最小が出ます。
//
//        配列の要素順が変わるので、元の入力順を残したければ あらかじめコピーして、また戻す。
//        配列は参照型なので、代入しても参照先をコピーするだけ(= シャローコピー[浅いコピー])
//
//           ×inputCopy = input;
//
//        これだと変更すると両方変わってしまう。
//        なので、各要素ごとコピーする。(= ディープコピー[深いコピー])
//
//        int[] inputCopy = Arrays.copyOf(input, input.length);
//
//        //小さい順(昇順)に並び替え
//        Arrays.sort(input);
//        max = input[input.length - 1];
//        min = input[0];
//
//        input = Arrays.copyOf(inputCopy, input.length);

        //==== 数値・計算結果の表示 ====
        System.out.println();

        //---- 数値 ----
        System.out.print("数値: [ ");
        for(int bit : input) {
            System.out.print(bit + ", ");
        }
        System.out.println("]");

        //---- 計算結果 ----
        System.out.println("合計: " + sum);
        System.out.printf("平均: %.2f \n", average);
        System.out.println("最大: " + max);
        System.out.println("最小: " + min);

        scn.close();
    }//main()

}//class

/*
//《解説》
配列を扱う場合、0から始まる for文とarray.lengthを利用すると
例外 ArrayIndexOutOfBoundsExceptionを起こさないというのは既述。

合計, 平均, 最大, 最小の求め方は、いつもこうするので定型として覚えてしまう。

最大, 最小は input[i]を１個ずつ取り出し、それまでの最大最小と比較して
max, minを更新するか決める。

if (input[i] >= input[i + 1])と次の要素と比べないように。
input[i + 1]は iの最後の周回で ArrayIndexOutOfBoundsExceptionを起こす。
input[i - 1]も同様に、i = 0 のとき例外が出る。
なるべく配列の[]の中には計算式を入れないようにするのが良い。
*/
//【おまけ】複数行のコメントアウト
//「/*  */」はclassごと無効にしたいときなどに使いたい。別バージョンを作るときとか。
//
//「/*  */」は入れ子にできないので、コード中にこれを使うと
//classごとコメントアウトしたい場合、使った箇所の手前で一旦閉じて
//「*/」「/* 最初に使っていた部分 */」「/*」んで、また開始としなければならない。
//
//なのでコード中の複数行コメントアウトは「//」で行う。
//たくさんあっても eclipseなら 複数行を選択して[Ctrl + /]で一気にコメントアウトできる。
//解除したいときも同様。
/*
◆10個の整数を入力してください。

1個目の数値: 24
2個目の数値: 36
3個目の数値: 48
4個目の数値: -64
5個目の数値: 72
6個目の数値: 81
7個目の数値: 99
8個目の数値: 7
9個目の数値: -48
10個目の数値: 0

数値: [24, 36, 48, -64, 72, 81, 99, 7, -48, 0, ]
合計: 255
平均: 25.50
最大: 99
最小: -64

◆10個の整数を入力してください。

1個目の数値: 4
2個目の数値: 数値
整数で入力してください。終了します。

//====〔別解〕実行結果 ====
数値: [24, 36, 12, -48, 11, 3, 0, 99, 100, -8, ]
合計: 229
平均: 22.90
最大: 100
最小: -48
*/
