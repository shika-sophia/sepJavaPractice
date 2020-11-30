package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MutterData;
import model.MutterLogic;
import model.MutterLoginLogic;


@WebServlet("/MutterServlet")
public class MutterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MutterLoginLogic inLogic;
    private MutterLogic logic;
    private MutterData data;
    private HttpSession session;
    private ServletContext application;

    //----<a href="?action=admit"> from [mutterConfirm.jsp] ----
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        //---- data -> this.field ----
        inLogic = new MutterLoginLogic();
        logic = new MutterLogic();

        session = request.getSession();
        data = (MutterData)session.getAttribute("data");

        application = this.getServletContext();

        //---- get Query "?action" ----
        String msgFlag = request.getParameter("action");

        if (msgFlag == null) {
            //---- forward to Login <again> ----
            String path = "/MutterLoginServlet";
            doForward(request, response, path);
            return;
        }

        switch(msgFlag) {
        case "admit":
            inLogic.setMsgList("ログインしました。");
            inLogic.setMsgList("つぶやきを投稿してください。");
            break;

        case "doneRegister":
            inLogic.setMsgList("登録完了。ログインしました。");
            inLogic.setMsgList("つぶやきを投稿してください。");
            break;
        }//switch

        //---- set to necessary scorp ----
        preNecessarySetting(request, msgFlag);

        //---- forward to mutter ----
        String path ="/WEB-INF/mutter/mutter.jsp";
        doForward(request, response, path);
    }//doGet()


    //====== set to necessary scorp for doGet() -> [mutter.jsp] ======
    private void preNecessarySetting(HttpServletRequest request, String msgFlag) {
        //---- set message List to request scorp ----
        request.setAttribute("msgFlag", msgFlag);

        List<String> msgList = inLogic.getMsgList();
        request.setAttribute("msgList", msgList);

        //---- set  data  to session scorp ----
        session.setAttribute("data", data);

        //---- set List as empty to application scorp ----
        List<String> mutterListAll = logic.getMutterListAll();
        List<String> dateTimeListAll = logic.getDateTimeListAll();

        application.setAttribute("mutterList", mutterListAll);
        application.setAttribute("dateTimeList", dateTimeListAll);
    }//preNecessarySetting() for doGet()


    //====== <form action> from [mutter.jsp] ======
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        //---- get mutter, mutterListAll ----
        String mutter = request.getParameter("mutter");

        //---- application scorpの内容を logic.fieldより取得 ----
        List<String> mutterListAll = logic.getMutterListAll();
        List<String> dateTimeListAll =  logic.getDateTimeListAll();

        //---- リロード対策に最新のmutterを準備 ----
        String lastMutter = "";
        int lastIndex = (mutterListAll.size() - 1);

        if (mutterListAll.isEmpty()) {
            ;
        } else {
            lastMutter = mutterListAll.get(lastIndex);
        }

        //---- [mutter.jsp]に表示するメッセージを分岐----
        String msgFlag = "postMutter";

        if(mutter.equals(lastMutter)) {
            msgFlag = "reload";
        }

        //---- mutterの文字数チェック----
        msgFlag = logic.checkMutter(mutter);

        //---- mutter -> List ----
        if (msgFlag.equals("postMutter")) {
            logic.addMutter(mutter, data);
            mutterListAll = logic.getMutterListAll();
            dateTimeListAll =  logic.getDateTimeListAll();
        }

        //---- set to necessary scorp ----
        necessarySetting(request, mutterListAll,dateTimeListAll, msgFlag);

        //---- forward to mutter ----
        String path = "/WEB-INF/mutter/mutter.jsp";
        doForward(request, response, path);

    }//doPost()


    //====== set to necessary scorp for doPost() -> [mutter.jsp] ======
    private void necessarySetting(
            HttpServletRequest request,
            List<String> mutterListAll,
            List<String> dateTimeListAll,
            String msgFlag) {

        //---- set message List to request scorp ----
        List<String> msgList = inLogic.getMsgList();
        msgList.clear();

        switch(msgFlag) {
        case "postMutter":
            msgList.add(String.format("%sさんが投稿しました。", data.getName()));
            break;

        case "overText":
            msgList.add("つぶやきは 150文字以内で入力してください。");
            break;

        case "reload":
            msgList.add("同じ内容は投稿できません。");
            msgList.add("Ｗｅｂページのリロードは使わないでください。");
            break;

        }//switch

        request.setAttribute("msgFlag", msgFlag);

        inLogic.setMsgList(msgList);
        request.setAttribute("msgList", msgList);

        //---- set data to session scorp ----
        session.setAttribute("data", data);

        //---- set mutterListAll to application scorp ----
        application.setAttribute("mutterList", mutterListAll);
        application.setAttribute("dateTimeList", dateTimeListAll);
    }//necessarySetting() for doPost()


  //====== forward to path ======
    public void doForward(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException{
        RequestDispatcher dis = request.getRequestDispatcher(path);
        dis.forward(request, response);
    }//doForward()

}//class
