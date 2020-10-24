/**
 * @title javaPractice / exercise / Exercise10Ans
 * @content 配列,(別解 二次元配列), ArrayIndexOutOfBoundsException, Scanner,
 *           while(true), ラベル, break, continue, クラスファイルの実行,
 *           《解説》フィールド,《解説》StringBuilder
 * @author shika
 * @date 2020-10-22 ～ 10-24
 */
/*
 * ◆Exercise10 [応用]
 * 同じフォルダ内にある [Exercise10.class]を
 * コマンドプロンプトのワークフォルダ(自分のjavaファイルを置いてるところ)にコピーし
 *
 * コマンドプロンプトを開いて
 * >java Exercise10 で実行する。
 * (classファイルはコンパイルした結果できたファイルなので、すでにコンパイル済)
 *
 * 実行動作を確認し、こういう動作をするプログラムを作成せよ。
 *
 *----------------------------
 * >java Exercise10
 *　　 [↑8]
 *[←4] 　　[6→]
 *　　 [↓2] 　　[0]終了
 *
 *　　□□□□
 *　　□□◆□
 *　　□□□□
 *　　
 *数字を入力すると移動します。
 *----------------------------
 *
 * 問１ 横４×縦３で同様の動作を実現せよ。
 *
 * 問２ 横WIDTH×縦HEIGHTとし、
 *      横と縦の変更をフィールドの定数を変更すればできるようにしたい。
 *      問１で定数の計算式にしていた部分を WIDTHとHEIGHTを用いた式に修正せよ。
 *
 * 問３ 同様の動きを二次元配列を使って実現せよ。
 *
 *-----------------------------
 * 〔ヒント〕
 * ◆ArrayIndexOutOfBoundsException:
 *    配列を使う際、初心者のうちは、よく目にして悩まされる例外(エラー)。
 *    配列は array[5]などと、あらかじめ要素の個数を宣言してから使うが、
 *    この例で言えば[]の中のindex(添え字 = 配列の要素番号)は 0～4までしかない。
 *    それなのに0～4以外のindexを入れて実行すると出される例外。
 *
 *    この Exercise10 は、この例外に対処するための練習問題です。
 *    後に学習する例外処理のための try-catch構文というものがあるが、
 *    IndexOutOfBoundsExceptionは、そもそもコンパイルで検出されず、例外として認識されない。
 *    実行時に例外が出されるのは、正しいプログラムを書くことで防ぐことができるからです。
 *
 *    なのでこの例外の処理に try-catchは使わず、[]の中に不正な値が入らないよう細心の注意をして
 *    プログラムを作ります。以下いくつかコツがあるので ご紹介します。
 *
 *    ◇lengthフィールドを使う
 *    for(int i = 0; i < array.length; i++){
 *        array[i] = n;
 *    }
 *    というfor文で配列を扱うと上記の例外は起こりえない。
 *    このfor文は 0～「indexの最後」の[i]しか入らないので、
 *    [i]に 「+,-,*,/」などの計算を加えない限り、例外が出ることはない。
 *    なるべく iの終了条件式(継続条件式)には定数ではなく「配列名.length」を使うようにすると良い。
 *    不等号には「=」を入れないよう注意。
 *
 *    ◇不正な値をあらかじめチェック
 *    [i]の中はできれば計算式を入れたくはないが、どうしても必要なときは
 *    事前に ifや switchで不正な値が入る可能性をなくしてから計算式を入れると良い。
 *
 * ◆永久ループ 「while(true){}」
 * このwhile文は永久ループする。whileの代わりに「for( ; ; ){}」と何も()中に条件を書き込まない for文でも可能。
 * 永久ループを作る場合は、if, switchで 必ず break;を入れること。
 *
 * switchで用いるbreak;は switch{}ブロックを抜けるだけなので、
 * ループさせるときは while(true)の直前に「loop:」などラベルを付ける。
 * ラベル名は自由に付けていいが「:」コロンで終わること。「;」文末のセミコロンではない。
 * そして、while(true){}の永久ループを抜ける breakにはラベルを付けて「break loop;」文末の「;」とする。
 *
 * 不正な値をチェックして、もう一度 Scanner入力がほしいときなどに利用する。
 * 必要に応じて Scannerクラスのインスタンスをwhileループの中に入れるか、外に出しておくかを考える。
 * 外に置いたほうがＰＣの処理負担が軽減される。永久ループはＰＣに負担の掛かる処理であることを考慮して使う。
 * (ひと昔前のＰＣなら永久ループさせただけでフリーズすることもあったのだ。)
 *
 */
package javaPractice.exercise;
/*
//###### 問１解答 / fix 4×3 version ######
//マス目の横４×縦３に固定したversion
import java.util.InputMismatchException;
import java.util.Scanner;

public class Exercise10Ans {
    private static int point; //駒の現在位置
    private static int prePoint;//駒の前位置をコピー
    private static int move;
    private static String[] square; //横４×縦３のマス目

    public static void main(String[] args) {
        //==== 初期設定 ====
        square = new String[12];

        //squareに全て「□」を入れて初期化
        for(int i = 0; i < square.length; i++) {
            square[i] = "□";
        }//for i

        //初期値 6番に◆を入れて駒とする
        point = 6;
        prePoint = point;
        move = 0;
        movePoint();

        //マス目の表示
        printSquare();

        //==== 移動の入力ループ ====
        loop:
        while(true) {
            Scanner scn = new Scanner(System.in);
            int input = -1;

            try {
                System.out.println("数字を入力すると移動します。");
                input = scn.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("整数で入力してください");
                continue loop;
            }

            //---- switch 入力分岐 ----
            //break loop, continue loopをするため、ここのメソッド分け不可

            switch (input) {
            case 0: //[0] 終了
                scn.close();
                break loop; //while loopを break

            case 2: //[2] ↓下へ移動
                checkDownable();
                break;
            case 4: //[4] ←左へ移動
                checkLeftable();
                break;
            case 6: //[6] →右へ移動
                checkRightable();
                break;
            case 8: //[8] ↑上へ移動
                checkUpable();
                break;

            default: //上記以外の不正入力
                System.out.println("[2][4][6][8][0] の数字で入力してください。");
                continue loop;
            }//switch

            //移動処理メソッド
            movePoint();

            //マス目を表示
            printSquare();
        }//while loop

        System.out.println("お疲れさまでした。");
    }//main()


    private static void checkDownable() {
        for (int i = 0; i < 4; i++) {
            //point が最下段にあるとき
            if (point == (8 + i)){
                move = -8;
                break;
            } else {
                move = 4;
            }
        }//for

    }//checkDownable()

    private static void checkLeftable() {
        for (int i = 0; i < 3; i++) {
            //pointが最左列にいる場合
            if(point == 4 * i) {
                move = 3;
                break;
            } else {
                move = -1;
            }
        }//for

    }//checkLeftable()

    private static void checkRightable() {
        for (int i = 0; i < 3; i++) {
            //pointが最右列にいる場合
            if(point == 3 + 4 * i) {
                move =  -3;
                break;
            } else {
                move = +1;
            }
        }//for

    }//checkeRightable()

    private static void checkUpable() {
        for (int i = 0; i < 4; i++) {
            //point が最上段にあるとき
            if (point == i ){
                move = 8;
                break;
            } else {
                move = -4;
            }
        }//for

    }//checkUpable()


    private static void movePoint() {
        prePoint = point;
        point += move;
        square[prePoint] = "□";
        square[point] = "◆";
    }//movePoint()

    //マス目の表示
    private static void printSquare() {
        StringBuilder bld = new StringBuilder();
        bld.append("　　 [↑8]\n");
        bld.append("[←4] 　　[6→]\n");
        bld.append("　　 [↓2] 　　[0]終了\n\n");

        bld.append("　　");
        for(int i = 0; i < square.length; i++) {

            bld.append(square[i]);

            if((i + 1) % 4 == 0) {
                bld.append("\n");
                bld.append("　　");
            }
        }//for i

        System.out.println(bld.toString());
    }//printSquare()

}//class
*/
/*
//###### 問２解答 / variable WIDTH & HEIGHT version ######
//マス目の横×縦を変数にしたversion
import java.util.InputMismatchException;
import java.util.Scanner;


public class Exercise10Ans {
    private static int point; //駒の現在位置
    private static int prePoint;//駒の前位置をコピー
    private static int move;
    private static final int WIDTH = 4; //マス目の横幅
    private static final int HEIGHT = 3;//マス目の縦高
    private static String[] square; //横WIDTH×縦HEIGHTのマス目

    public static void main(String[] args) {
        //==== 初期設定 ====
        square = new String[WIDTH * HEIGHT];

        //squareに全て「□」を入れて初期化
        for(int i = 0; i < square.length; i++) {
            square[i] = "□";
        }//for i

        //初期値 6番に◆を入れて駒とする
        point = 6;
        prePoint = point;
        move = 0;
        movePoint();

        //マス目の表示
        printSquare();

        //==== 移動の入力ループ ====
        loop:
        while(true) {
            Scanner scn = new Scanner(System.in);
            int input = -1;

            try {
                System.out.println("数字を入力すると移動します。");
                input = scn.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("整数で入力してください");
                continue loop;
            }

            //---- switch 入力分岐 ----
            //break loop, continue loopをするため、ここのメソッド分け不可

            switch (input) {
            case 0: //[0] 終了
                scn.close();
                break loop; //while loopを break

            case 2: //[2] ↓下へ移動
                checkDownable();
                break;
            case 4: //[4] ←左へ移動
                checkLeftable();
                break;
            case 6: //[6] →右へ移動
                checkRightable();
                break;
            case 8: //[8] ↑上へ移動
                checkUpable();
                break;

            default: //上記以外の不正入力
                System.out.println("[2][4][6][8][0] の数字で入力してください。");
                continue loop;
            }//switch

            //移動処理メソッド
            movePoint();

            //マス目を表示
            printSquare();
        }//while loop

        System.out.println("お疲れさまでした。");
    }//main()


    private static void checkDownable() {
        for (int i = 0; i < WIDTH; i++) {
            //point が最下段にあるとき
            if (point == (WIDTH * (HEIGHT - 1) + i)){
                move = -(WIDTH * (HEIGHT - 1));
                break;
            } else {
                move = WIDTH;
            }
        }//for

    }//checkDownable()

    private static void checkLeftable() {
        for (int i = 0; i < HEIGHT; i++) {
            //pointが最左列にいる場合
            if(point == WIDTH * i) {
                move = (WIDTH - 1);
                break;
            } else {
                move = -1;
            }
        }//for

    }//checkLeftable()

    private static void checkRightable() {
        for (int i = 0; i < HEIGHT; i++) {
            //pointが最右列にいる場合
            if(point == (WIDTH - 1) + WIDTH * i) {
                move =  -(WIDTH - 1);
                break;
            } else {
                move = +1;
            }
        }//for

    }//checkeRightable()

    private static void checkUpable() {
        for (int i = 0; i < WIDTH; i++) {
            //point が最上段にあるとき
            if (point == i ){
                move = WIDTH * (HEIGHT - 1);
                break;
            } else {
                move = -(WIDTH);
            }
        }//for

    }//checkUpable()


    private static void movePoint() {
        prePoint = point;
        point += move;
        square[prePoint] = "□";
        square[point] = "◆";
    }//movePoint()

    //マス目の表示
    private static void printSquare() {
        StringBuilder bld = new StringBuilder();
        bld.append("　　 [↑8]\n");
        bld.append("[←4] 　　[6→]\n");
        bld.append("　　 [↓2] 　　[0]終了\n\n");

        bld.append("　　");
        for(int i = 0; i < square.length; i++) {

            bld.append(square[i]);

            if((i + 1) % WIDTH == 0) {
                bld.append("\n");
                bld.append("　　");
            }
        }//for i

        System.out.println(bld.toString());
    }//printSquare()

}//class
*/
//###### 問３解答 / Matrix Array Version / 二次元配列 ######
//Vector: 一次元、線形
//Matrix: 二次元、平方

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Exercise10Ans {
    private static int[] point; //駒の現在位置 (x座標(横),y座標(縦))
    private static int[] prePoint;//駒の前位置をコピー
    private static int[] move;
    private static final int WIDTH = 5; //マス目の横幅
    private static final int HEIGHT = 6;//マス目の縦高
    private static String[][] square; //横WIDTH×縦HEIGHTのマス目

    public static void main(String[] args) {
        //==== 初期設定 ====
        square = new String[HEIGHT][WIDTH];//二次元配列は[行][列]の順

        //squareに全て「□」を入れて初期化
        for(int i = 0; i < square.length; i++) { //(HEIGHT - 1)まで
            for (int j = 0; j < square[i].length; j++) { // (WIDTH - 1)まで
                square[i][j] = "□";
            }//for j
        }//for i

        //初期値{2,2}に◆を入れて駒とする
        point = new int[] {2, 2};
        prePoint = new int[point.length];
        prePoint = Arrays.copyOf(point, point.length);
        move = new int[]{0, 0};

        //駒◆の配置
        movePoint();

        //squareの表示
        printSquare();

        //==== 移動の入力ループ ====
        loop:
        while(true) {
            Scanner scn = new Scanner(System.in);
            int input = -1;

            try {
                System.out.println("数字を入力すると移動します。");
                input = scn.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("整数で入力してください");
                continue loop;
            }

            //---- switch 入力分岐 ----
            //break loop, continue loopをするため、ここのメソッド分け不可

            switch (input) {
            case 0: //[0] 終了
                scn.close();
                break loop; //while loopを break

            case 2: //[2] ↓下へ移動
                checkDownable();
                break;
            case 4: //[4] ←左へ移動
                checkLeftable();
                break;
            case 6: //[6] →右へ移動
                checkRightable();
                break;
            case 8: //[8] ↑上へ移動
                checkUpable();
                break;

            default: //上記以外の不正入力
                System.out.println("[2][4][6][8][0] の数字で入力してください。");
                continue loop;
            }//switch

            //移動処理メソッド
            movePoint();

            //マス目を表示
            printSquare();
        }//while loop

        System.out.println("お疲れさまでした。");
    }//main()


    private static void checkDownable() {
        for (int i = 0; i < WIDTH; i++) {
            //point が最下段にあるとき
            if (point[0] == i && point[1] == (HEIGHT - 1)){
                move[0] = 0;
                move[1] = -(HEIGHT - 1);
                break;
            } else {
                move[0] = 0;
                move[1] = 1;
            }
        }//for

    }//checkDownable()

    private static void checkLeftable() {
        for (int i = 0; i < HEIGHT; i++) {
            //pointが最左列にいる場合
            if(point[0] == 0 && point[1] == i) {
                move[0] = (WIDTH - 1);
                move[1]	= 0;
                break;
            } else {
                move[0] = -1;
                move[1] = 0;
            }
        }//for

    }//checkLeftable()

    private static void checkRightable() {
        for (int i = 0; i < HEIGHT; i++) {
            //pointが最右列にいる場合
            if(point[0] == (WIDTH - 1) && point[1] == i) {
                move[0] =  -(WIDTH - 1);
                move[1] = 0;
                break;
            } else {
                move[0] = 1;
                move[1] = 0;
            }
        }//for

    }//checkeRightable()

    private static void checkUpable() {
        for (int i = 0; i < WIDTH; i++) {
            //point が最上段にあるとき
            if (point[0] == i && point[1] == 0){
                move[0] = 0;
                move[1] = (HEIGHT - 1);
                break;
            } else {
                move[0] = 0;
                move[1] = -1;
            }
        }//for

    }//checkUpable()


    private static void movePoint() {
        prePoint = Arrays.copyOf(point, point.length);

        for(int i = 0; i < point.length; i++) {
            point[i] += move[i];
        }
        square[prePoint[1]][prePoint[0]] = "□";
        square[point[1]][point[0]]  = "◆";
    }//movePoint()

    //マス目の表示
    private static void printSquare() {
        StringBuilder bld = new StringBuilder();
        bld.append("　　 [↑8]\n");
        bld.append("[←4] 　　[6→]\n");
        bld.append("　　 [↓2] 　　[0]終了\n\n");
        bld.append("　");

        for(int i = 0; i < square.length; i++) {
            for (int j = 0; j < square[i].length; j++) {
                bld.append(square[i][j]);
            }//for j
            bld.append("\n");
            bld.append("　");
        }//for i
        System.out.println(bld.toString());
    }//printSquare()

}//class

/*
◆《解説》フィールド
クラス宣言とmain()の間で変数宣言しているものをフィールドという。
各メソッド間を越えてクラス全体で代入・参照する変数はフィールドに置くと使いやすい。
フィールドを使わないとすると各メソッド間を引数とreturnで値のやりとりをしなくてはならなくなる。

解答のコードにメソッド呼び出しに引数がなく、void のメソッドばかりでreturn文がないのは
フィールドを利用しているからである。

private: アクセス修飾子、いずれ学習するので解説は割愛。
static: インスタンスせずにmain()から参照するためには staticにしておく必要がある。


 ◆《解説》StringBuilder (java.langパッケージ)
 String結合「+」演算子は「"あ"+"い" -> "あい"」の結合で３つのStringオブジェクトを生成する。
 連続で文字列結合する際、「+」演算子を使うとメモリへの負担は相当掛かることになるので、
 そんなときに利用するのが StringBuilderクラス。

 StringBuilder bld = new StringBuilder();
 bld.append(""): 文字列追加、""をなくせば そのまま数値,変数も入れられ、Stringに変換してくれる。
 bld.toString(): 最後に１回これをすると、巨大なStringオブジェクトにしてくれる。
 bld.append(String.format("", n )): StringBuilder内に printf()の書式指定を利用したいときに使う。

 */
/*
//###### variable WIDTH & HEIGHT Reault ######
C:\Users\6A16\Desktop>javac Exercise10.java -encoding UTF-8

C:\Users\6A16\Desktop>java Exercise10
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　　□□□□
　　□□◆□
　　□□□□
　　
数字を入力すると移動します。
2
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　　□□□□
　　□□□□
　　□□◆□
　　
数字を入力すると移動します。
4
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　　□□□□
　　□□□□
　　□◆□□
　　
数字を入力すると移動します。
4
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　　□□□□
　　□□□□
　　◆□□□
　　
数字を入力すると移動します。
2
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　　◆□□□
　　□□□□
　　□□□□
　　
数字を入力すると移動します。
4
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　　□□□◆
　　□□□□
　　□□□□
　　
数字を入力すると移動します。
8
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　　□□□□
　　□□□□
　　□□□◆
　　
数字を入力すると移動します。
6
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　　□□□□
　　□□□□
　　◆□□□
　　
数字を入力すると移動します。
6
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　　□□□□
　　□□□□
　　□◆□□
　　
数字を入力すると移動します。
6
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　　□□□□
　　□□□□
　　□□◆□
　　
数字を入力すると移動します。
8
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　　□□□□
　　□□◆□
　　□□□□
　　
数字を入力すると移動します。
4
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　　□□□□
　　□◆□□
　　□□□□
　　
数字を入力すると移動します。
4
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　　□□□□
　　◆□□□
　　□□□□
　　
数字を入力すると移動します。
8
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　　◆□□□
　　□□□□
　　□□□□
　　
数字を入力すると移動します。
4
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　　□□□◆
　　□□□□
　　□□□□
　　
数字を入力すると移動します。
8
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　　□□□□
　　□□□□
　　□□□◆
　　
数字を入力すると移動します。
0
お疲れさまでした。

C:\Users\6A16\Desktop>java Exercise10
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　　□□□□
　　□□◆□
　　□□□□
　　
数字を入力すると移動します。
1
[2][4][6][8][0] の数字で入力してください。
数字を入力すると移動します。
サン
整数で入力してください
数字を入力すると移動します。
4
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　　□□□□
　　□◆□□
　　□□□□
　　
数字を入力すると移動します。
０
お疲れさまでした。

//###### Matrix Array Version ######
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　□□□□□
　□□□□□
　□□◆□□
　□□□□□
　□□□□□
　□□□□□
　
数字を入力すると移動します。
2
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　□□□□□
　□□□□□
　□□□□□
　□□◆□□
　□□□□□
　□□□□□
　
数字を入力すると移動します。
2
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　□□□□□
　□□□□□
　□□□□□
　□□□□□
　□□◆□□
　□□□□□
　
数字を入力すると移動します。
2
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　□□□□□
　□□□□□
　□□□□□
　□□□□□
　□□□□□
　□□◆□□
　
数字を入力すると移動します。
2
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　□□◆□□
　□□□□□
　□□□□□
　□□□□□
　□□□□□
　□□□□□
　
数字を入力すると移動します。
4
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　□◆□□□
　□□□□□
　□□□□□
　□□□□□
　□□□□□
　□□□□□
　
数字を入力すると移動します。
4
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　◆□□□□
　□□□□□
　□□□□□
　□□□□□
　□□□□□
　□□□□□
　
数字を入力すると移動します。
4
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　□□□□◆
　□□□□□
　□□□□□
　□□□□□
　□□□□□
　□□□□□
　
数字を入力すると移動します。
6
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　◆□□□□
　□□□□□
　□□□□□
　□□□□□
　□□□□□
　□□□□□
　
数字を入力すると移動します。
6
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　□◆□□□
　□□□□□
　□□□□□
　□□□□□
　□□□□□
　□□□□□
　
数字を入力すると移動します。
8
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　□□□□□
　□□□□□
　□□□□□
　□□□□□
　□□□□□
　□◆□□□
　
数字を入力すると移動します。
8
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　□□□□□
　□□□□□
　□□□□□
　□□□□□
　□◆□□□
　□□□□□
　
数字を入力すると移動します。
8
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　□□□□□
　□□□□□
　□□□□□
　□◆□□□
　□□□□□
　□□□□□
　
数字を入力すると移動します。
8
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　□□□□□
　□□□□□
　□◆□□□
　□□□□□
　□□□□□
　□□□□□
　
数字を入力すると移動します。
2
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　□□□□□
　□□□□□
　□□□□□
　□◆□□□
　□□□□□
　□□□□□
　
数字を入力すると移動します。
2
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　□□□□□
　□□□□□
　□□□□□
　□□□□□
　□◆□□□
　□□□□□
　
数字を入力すると移動します。
4
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　□□□□□
　□□□□□
　□□□□□
　□□□□□
　◆□□□□
　□□□□□
　
数字を入力すると移動します。
4
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　□□□□□
　□□□□□
　□□□□□
　□□□□□
　□□□□◆
　□□□□□
　
数字を入力すると移動します。
2
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　□□□□□
　□□□□□
　□□□□□
　□□□□□
　□□□□□
　□□□□◆
　
数字を入力すると移動します。
2
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　□□□□◆
　□□□□□
　□□□□□
　□□□□□
　□□□□□
　□□□□□
　
数字を入力すると移動します。
8
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　□□□□□
　□□□□□
　□□□□□
　□□□□□
　□□□□□
　□□□□◆
　
数字を入力すると移動します。
6
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　□□□□□
　□□□□□
　□□□□□
　□□□□□
　□□□□□
　◆□□□□
　
数字を入力すると移動します。
4
　　 [↑8]
[←4] 　　[6→]
　　 [↓2] 　　[0]終了

　□□□□□
　□□□□□
　□□□□□
　□□□□□
　□□□□□
　□□□□◆
　
数字を入力すると移動します。
数字
整数で入力してください
数字を入力すると移動します。
0
お疲れさまでした。


*/
