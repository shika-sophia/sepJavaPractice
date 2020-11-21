/**
 * @title webPractice / prime / PrimeServlet
 * @mean  Prime Number: 素数
 * @content 素数、約数、倍数、最大公約数、最小公倍数を求めるアプリ
 *
 *        ---- servlet ----
 * @class PrimeServlet.java //Start Point, Controler
 *
 *        ---- model ----
 * @class PrimeLogic.java//各計算メソッド,分岐,表示作成
 * @class PrimeData.java //データの保持
 *
 *        ---- WebContent ----
 * @webContent primeInput.jsp
 * @webContent primeResult.jsp
 * @webContent primeStyle.css
 *
 * @see 関連src: javaPractice / exercise / Exercise15Ans.java
 * @author shika
 * @date 2020-11-17,11-21
 */

package webPractice.prime.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webPractice.prime.model.PrimeData;
import webPractice.prime.model.PrimeLogic;


@WebServlet("/PrimeServlet")
public class PrimeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PrimeLogic logic;//各計算メソッド,分岐,表示作成
    private PrimeData data;  //データの保持
    private Integer x; //値１
    private Integer y; //値２

    //====== Strat Point ======
    //---- initialize ----
    public void init(ServletConfig config) throws ServletException {
        logic = new PrimeLogic();
        data = new PrimeData();
        x = null;
        y = null;
    }//init()

    //====== get Query '?action=' and separate 'action' ======
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        //nullは初回なので処理なし。上記 init()で処理済
        if (action == null) {
            ;
        } else {
            switch (action) {
            // ?action=init は再初期化
            case "init":
                x = null;
                y = null;
                data = new PrimeData();
                break;

            case "again":
                ;
                break;
            }//switch
        }

       //---- forward to input ----
        String message = "自然数を入力してください。(１つでも可)";
        forwardInput(request, response, message);
    }//doGet()


    //====== <form action> from [primeInput.jsp] ======
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //---- get values ----
        request.setCharacterEncoding("UTF-8");
        String calcWay = request.getParameter("calcWay");

        //---- 不正値チェック ----
        String message = "";
        try {
            x = Integer.parseInt(request.getParameter("x"));

            String yStr = request.getParameter("y");

            if(yStr == null || yStr.equals("")) {
                y = 0;
            } else {
                y = Integer.parseInt(yStr);
            }

        } catch (NumberFormatException e) {
            message = "必ず整数で入力してください。";
            x = null;
            y = null;

            //---- inputに再forward ----
            forwardInput(request, response, message);
        }//try-catch

        if (x < 0 || y < 0 || x >10000 || y > 10000) {
            message = "必ず [0～10000] で入力してください。";
            forwardInput(request, response, message);
        }

        //---- instance of data ----
        if (data == null) {
             data = new PrimeData(x, y);
        } else if (x== data.getX() && y == data.getY()) {
            ;
        } else {
            data = new PrimeData(x, y);
        }

        //---- separate calcWay -> 計算結果を data.fieldに格納 ----
        logic.calcWay(x, y, calcWay, data);

        //---- set data to session scorp ----
        HttpSession session = request.getSession();
        session.setAttribute("data", data);

        //---- set (x, y) , calcWay to request scorp ----
        request.setAttribute("x", x);
        request.setAttribute("y", y);
        request.setAttribute("calcWay", calcWay);

        //---- forward to result ----
        String path = "primeResult.jsp";
        RequestDispatcher dis = request.getRequestDispatcher(path);
        dis.forward(request, response);
    }//doPost()


    //====== inputにforward ======
    private void forwardInput(
        HttpServletRequest request, HttpServletResponse response, String message)
        throws ServletException, IOException {

        //---- set (x, y), message to request scorp ----
        request.setAttribute("x", x);
        request.setAttribute("y", y);
        request.setAttribute("message", message);

        //---- forward to input ----
        String path = "primeInput.jsp";
        RequestDispatcher dis = request.getRequestDispatcher(path);
        dis.forward(request, response);
    }//inputAgain()


    public void destroy() {

    }//destroy()
}//class

/*
【考察】
◆2020-11-18-1600
「x = 100000を入力すると、ここに来るまでにフリーズするときあり
 どの計算処理もList容量を確保するので大量になりすぎないよう 10000を限界とする。」

◆2020-11-18-2030
x = 100000 を入力しても、１分ほど時間が掛かるが「[0 ～ 10000 ]で入力してください」のエラー表示が出る。
おそらく、Listのメモリ領域などに相当の時間が掛かるものと思われる。

試しに PrimeDataクラスのコンストラクタ中にある Listインスタンス部分を全てコメントアウトし
同様の x = 100000 を試みたが、やはり同様に かなり重かった。

この日の昼間、臨泉さんに「コンストラクタでメモリ領域を確保しないとデータが入らない」という話をする。
全くインスタンスをしない Listにデータを入れようとすると、NullPointerExceptionを返されるのは確かにその通りだが、
このアプリは各メソッドでインスタンスしたものを data.setterでフィールドに入れているため、
メモリ領域は各メソッドのローカルで作ったものが data.fieldに代入する際に持ち込まれ、
必ずしもコンストラクタでインスタンスする必要はない。
(他で全くインスタンスしてない場合はコンストラクタでする必要はあるが)

上記の点を休み明けに臨泉さんに訂正せねば。

このアプリは、何重にもインスタンスしているので、もっと無駄を省いて軽くできるはず、
そもそも、[primeResult.jsp]では計算結果のリストは使っておらず、
StringBuilderで結果表示をする際に役目は終わっていて、
それも logic.calcWay()で直接 logic.buildResult()に代入しているため、
data.fieldに ListをsetXxxx()で代入保持する意味はあまりない。

[primeResult.jsp]で Listを取り出さないのは、計算方法１つ分の計算しかしておらず、
全ての Listを取り出しても値はインスタンス時の初期状態のままのListがあるからで、
x,y,zResultだけあれば、事足りる。この辺りを省いて、次はもっとスリム化したものを作ろう。

あとxResultや setxPrime()などの識別子は混同しやすく可読性に欠くので、その点も要修正。
*/