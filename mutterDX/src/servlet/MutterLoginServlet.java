/**
 * @title  mutterDX / servlet / MutterLoginServlet
 * @content つぶやき投稿のWebアプリ
 *
 *        ---- servlet ----
 * @class ◆MutterLoginServlet    //◆Start Point / Login機能のControl
 * @class MutterRegisterServlet //本登録のControl
 * @class MutterServlet         //Mutter機能のControl
 * @class MutterFunctionServlet //Load,Save,Edit,Logout機能
 * @class MutterLogourServlet   //Logoutの最終処理
 * @class MutterFilter          //各Servlet, jspに共通する内容を記述
 *
 *        ---- model ----
 * @class LoginLogic  //Loginの適正判定、LoginDAOの呼び出し
 * @class MutterLogic //MutterのList追加、MutterDAOの呼び出し
 * @class MutterData  //データの保持 User,Mutterを統合
 * @class Message     //各ページに表示するメッセージ
 * @class LoadLogic   //Load時の処理
 * @class SaveLogic   //Save時の処理
 *
 *        ---- dao ----
 * @class DataAccess  //dao全体のindex、JDBC_URLなどのフィールド
 * @class LoginDAO    //MUTTER_USER_tbに登録があるか SELECT
 * @class RegisterDAO //MUTTER_USER_tbに本登録 INSERT
 * @class LoadDAO     //MUTTER_tbからデータ取得 SELECT
 * @class DistinctDAO //MUTTER_tbから日付に対応するmutterを取得 SELECT
 * @class SaveDAO     //MUTTER_tbにデータ記録 INSERT
 *
 *        ---- WebContent ----
 * @page /WEB-INF/mutter/mutterLogin.jsp //Login View
 * @page /WEB-INF/mutter/mutterConfirm.jsp //確認 View
 * @page /WEB-INF/mutter/mutterRegister.jsp//本登録用の入力 View
 * @page /WEB-INF/mutter/mutter.jsp        //つぶやき投稿・表示 View
 * @page /WEB-INF/mutter/functionConfirm.jsp //各機能のYES-NO確認 View
 * @page /WEB-INF/mutter/logout.jsp      //ログアウト時の最終表示 View
 * @page /WEB-INF/mutter/mutterHeaderer.jsp    //各ページのヘッダー
 * @page /WEB-INF/mutter/mutterFooter.jsp      //各ページのフッター
 * @page /css/mutterStyle.css            //mutterDX用のStyleSheet
 *
 * @author shika
 * @date 2020-11-22 ～ 2020-12-06
 */
package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DataAccess;
import model.LoginLogic;
import model.MutterData;


@WebServlet("/MutterLoginServlet")
public class MutterLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginLogic inLogic;
    private DataAccess dataAcs;
    private MutterData data;

    //======◆Start Point ======
    //---- initalize ----
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        inLogic = new LoginLogic();
        dataAcs = new DataAccess();
        data = new MutterData();
    }//init()

    //====== get Query and swich ======
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //---- get Query "?action" ----
        String action = request.getParameter("action");

        //---- judge action to separate way ----
        if (action == null || action.equals("")) {
            ;
        } else {
            switch(action) {
            case "init":
                data = new MutterData();
                break;
            }//switch
        }//if-else

        //---- get msgList and set it to request scorp ----
        List<String> msgList = inLogic.getMsgList();
        request.setAttribute("msgList", msgList);

        //---- forward to Login ----
        String path = "/WEB-INF/mutter/mutterLogin.jsp";
        doForward(request,response, path);
    }//doGet()


    //====== <form action> from [mutterLogin.jsp] ======
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //---- get name, pass as required -> not null ----
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        //---- judge Login ----
        boolean isLogin = inLogic.checkInput(name, pass);

        List<String> msgList;
        if (isLogin) {
            ;
        //---- isLogin == false ----
        } else {
            //---- get msgList and set it to request scorp ----
            msgList = inLogic.getMsgList();
            request.setAttribute("msgList", msgList);

            //---- forward to Login <again> ----
            String path = "/WEB-INF/mutter/mutterLogin.jsp";
            doForward(request,response, path);
            return;
        }

        //---- isLogin == true -> set to data.field ----
        String passCode = inLogic.passCode(pass);

        data = new MutterData(name, pass, passCode);

        //---- set data to session scorp ----
        HttpSession session = request.getSession();
        session.setAttribute("data", data);

        //---- DBと照合して登録が存在すればLogin ----
        //登録が存在しなければ 登録のため RegisterServletへ
        boolean isRegister = dataAcs.existRegiter(data);

        if(isRegister) {
            //---- forward to confirm ----
            String path = "/WEB-INF/mutter/mutterConfirm.jsp";
            doForward(request, response, path);
            return;

        } else {
            //---- Redirect to register ----
            String path = "/mutterDX/MutterRegisterServlet";
            response.sendRedirect(path);
        }
    }//doPost()


    //====== forward ======
    public void doForward(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException{
        RequestDispatcher dis = request.getRequestDispatcher(path);
        dis.forward(request, response);
    }//doForward()

}//class
