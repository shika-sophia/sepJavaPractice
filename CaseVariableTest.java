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

/*
###### Reference / 参考文献 ######

■Oracle仕様書
Java SE > 11 > Java SE Specifications > Java Language Specification
https://docs.oracle.com/javase/specs/jls/se11/html/jls-14.html#jls-14.11

以下、「◎」部分のみ大鹿 翻訳

◆14.11. The switch Statement
The switch statement transfers control to one of several statements depending on the value of an expression.

SwitchStatement:
    switch ( Expression ) SwitchBlock

SwitchBlock:
    { {SwitchBlockStatementGroup} {SwitchLabel} }

SwitchBlockStatementGroup:
    SwitchLabels BlockStatements

SwitchLabels:
    SwitchLabel {SwitchLabel}

SwitchLabel:
    case ConstantExpression :
    case EnumConstantName :

default :
EnumConstantName:
Identifier

◎The type of the Expression must be
    char, byte, short, int, Character, Byte, Short, Integer, String, or an enum type (§8.9),
    or a compile-time error occurs.

〔(switch文の「(  )」に入れる)変数の型は上記のものでないといけない。
  さもないとコンパイル時にエラーが発生する。〕

The body of a switch statement is known as a switch block.
Any statement immediately contained by the switch block may be labeled with one or more switch labels,
which are case or default labels.


◎Every case label has a case constant,
which is either a constant expression or the name of an enum constant.
Switch labels and their case constants are said to be associated with the switch statement.
〔ケース句に用いるラベルは、すべて定数表現か enum(列挙型の)定数である。
    switchのラベルには switch文と関連した定数のcase句を持つ〕

Given a switch statement,
all of the following must be true or a compile-time error occurs:

◎Every case constant associated with the switch statement
must be assignment compatible with the type of the switch statement's Expression (§5.2).
〔switch文に関連するすべてのcase定数は、switch文に使える型と代入互換性(assignment compatible)〕を
持っていないとコンパイルエラーとなる。〕


If the type of the switch statement's Expression is an enum type,
then every case constant associated with the switch statement must be an enum constant of that type.

No two of the case constants associated with the switch statement have the same value.

No case constant associated with the switch statement is null.

At most one default label is associated with the switch statement.

The prohibition against using null as a case constant prevents code being written that can never be executed. If the switch statement's Expression is of a reference type, that is, String or a boxed primitive type or an enum type, then an exception will be thrown will occur if the Expression evaluates to null at run time. In the judgment of the designers of the Java programming language, this is a better outcome than silently skipping the entire switch statement or choosing to execute the statements (if any) after the default label (if any).

A Java compiler is encouraged (but not required) to provide a warning if a switch on an enum-valued expression lacks a default label and lacks case labels for one or more of the enum's constants. Such a switch will silently do nothing if the expression evaluates to one of the missing constants.


◆(おまけ) 詳細 constant expression(定数表現の定義)
https://docs.oracle.com/javase/specs/jls/se11/html/jls-15.html#jls-ConstantExpression


【考察】大鹿 2020-10-09
OracleのSE11の仕様書によると、switch文case句ラベルは定数でないといけないとある。

「final int ANS = new Random().nextInt();」
    は、int型でfinal宣言をして初期値以外は代入不可だけど、
    インスタンス生成とランダムで生成された値を入れる参照型の変数
    switch文に参照型の変数やオブジェクト(配列など)は使えないのと同じで、
    final宣言しても変数(variable)は定数(constant)の扱いにはならないと思う。

    値そのもの(リテラル)を入れないと使えないようです。

とはいえ、switch文にランダム変数を使えたら便利だし、
それを作ってみようとする試みは、とても大事だと思います。

そういう try and error を繰り返すことで、Java言語の習得だけでなく、
将来的には、独創性のある creativeなこと(創造的な = 新しい発明)に繋がる姿勢だとも思います。


【考察２】大鹿 2020-10-10
前半部分に Eclipseで検証してみました。
ネット記事を見たとのことで
「public static final int ANS = new java.util.Random.nextInt();」
と、フィールドぽい記述があったため、フィールドでも検証してみました。

結果は定数を代入した finalなら、switchで使用可能だが、
Randomなどのインスタンスを絡めると不可。
RandomクラスのnextInt()を呼び出している時点でその変数は参照値(メモリ上のアドレス)が
入っているものと思われ、switchでは使用不可。
素直に if文でやるしかなさそう。
*/