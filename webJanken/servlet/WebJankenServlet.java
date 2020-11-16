/**
 * @title webPractice / webJanken / WebJankenServlet
 *          ---- servlet ----
 * @content WebJankenServlet
 *
 *          ---- model ----
 * @content WebJankenLogic.java
 * @content WebJankenData.java
 *
 *          ---- WebContent ----
 * @content webJanken_input.jsp
 * @content webJanken_result.jsp
 * @content webJanken_style.css
 *
 * @see 関連src: javaPractice / reaction / JankenRV.java
 * @author shika
 * @date 2020-11-14, 11-15
 */

package webPractice.webJanken.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webPractice.webJanken.model.WebJankenData;
import webPractice.webJanken.model.WebJankenLogic;


@WebServlet("/WebJankenServlet")
public class WebJankenServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Integer count;//訪問回数を表すIntegerインスタンス
    private WebJankenData data;  //データを保持するクラス
    private WebJankenLogic logic;//勝敗判定などの論理クラス

    //====== Start Point ======
    //---- initialize ----
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        //---- countの初期化 ----
        this.count = 0;

        //---- instsnce of model 2 class ----
        data = new WebJankenData();
        logic = new WebJankenLogic();
    }//init()

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //---- ?actionの取得 ----
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if (action == null) {
            ;
        //---- ?action=init なら初期化 ----
        } else if(action.equals("init")) {
            data = new WebJankenData();
            count = 0;
        }

        //---- count 適正化 / 初回は１----
        if (count == 0) {
            count = 1;

        //count++にするとwebページのリロードで回数が増えるのでList.size()を取得
        } else {
            count = data.getWinList().size() + 1;
        }

        //---- set count, message to request scorp ----
        request.setAttribute("count", count);

        String message = "どれかを選んでください。";
        request.setAttribute("message", message);

        //---- forward to input ----
        String path = "/webJanken_input.jsp";
        RequestDispatcher dis = request.getRequestDispatcher(path);
        dis.forward(request, response);
    }//doGet()


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //====== <form action> from [webJanken_input.jsp] ======
        request.setCharacterEncoding("UTF-8");
        String userHand = request.getParameter("userHand");

        //---- radioの null判定 ----
        String message = "";
        if(userHand == null) {
            message = "どれかを必ず選んでください。";
            request.setAttribute("message", message);

            request.setAttribute("count", count);

            //---- forward to input <<again>> ----
            String path = "/webJanken_input.jsp";
            RequestDispatcher dis = request.getRequestDispatcher(path);
            dis.forward(request, response);
        }

        //---- comHandの決定と勝敗判定 -> data.fieldに収納 ----
        logic.prepareHand(userHand, data);

        //---- 対戦結果が増えたので count 取得 ----
        count = data.getWinList().size();

        //---- set count, message to request scorp ----
        request.setAttribute("count", count);

        message = "もう一度やりますか？";
        request.setAttribute("message", message);

        //---- set data instance to session scorp ----
        HttpSession session = request.getSession();
        session.setAttribute("data", data);

        //---- forward to result ----
        String path = "/webJanken_result.jsp";
        RequestDispatcher dis = request.getRequestDispatcher(path);
        dis.forward(request, response);
    }//doPost()

}//class
