package webPractice.mutter.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webPractice.mutter.model.MutterData;
import webPractice.mutter.model.MutterLogic;
import webPractice.mutter.model.MutterLoginLogic;


@WebServlet("/MutterServlet")
public class MutterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MutterLoginLogic inLogic;
    private MutterLogic logic;
    private MutterData data;
    private ServletContext application;

    //----<a href="?action=admit"> from [mutterConfirm.jsp] ----
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        //---- inLogic, data -> this.field ----
        inLogic = new MutterLoginLogic();

        HttpSession session = request.getSession();
        data = (MutterData)session.getAttribute("data");

        //---- get Query "?action" ----
        String action = request.getParameter("action");

        if (action == null) {
            //---- forward to Login <again> ----
            String path = "/MutterLoginServlet";
            doForward(request, response, path);

        } else {
            switch(action) {
            case "admit":
                inLogic.setMsgList("ログインしました。");
                inLogic.setMsgList("つぶやきを投稿してください。");
                break;
            }//switch
        }//if-else

        //---- set to necessary scorp ----
        preNecessarySetting(request, session);

        //---- forward to mutter ----
        String path ="/WEB-INF/mutter/mutter.jsp";
        doForward(request, response, path);
    }//doGet()


    //====== <form action> from [mutter.jsp] ======
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        //---- get mutter, mutterListAll ----
        String mutter = request.getParameter("mutter");

        @SuppressWarnings("unchecked")
        List<String> mutterListAll = (List<String>) application.getAttribute("mutterList");

        //---- logic -> this.field ----
        logic = new MutterLogic();

        //---- mutter -> List ----
        mutterListAll = logic.addMutter(mutter, mutterListAll, data);

        //---- set to necessary scorp ----
        necessarySetting(request, mutterListAll);

        //---- forward to mutter ----
        String path = "/WEB-INF/mutter/mutter.jsp";
        doForward(request, response, path);
    }//doPost()


    //====== set to necessary scorp for doGet() -> [mutter.jsp] ======
    private void preNecessarySetting(HttpServletRequest request, HttpSession session) {
        //---- set message List to request scorp ----
        List<String> msgList = inLogic.getMsgList();
        request.setAttribute("msgList", msgList);

        //---- set  data  to session scorp ----
        session.setAttribute("data", data);

        //---- set mutterList as empty to application scorp ----
        List<String> mutterListAll = new ArrayList<>();

        application = this.getServletContext();
        application.setAttribute("mutterList", mutterListAll);

    }//preNecessarySetting() for doGet()


    //====== set to necessary scorp for doPost() -> [mutter.jsp] ======
    private void necessarySetting(
            HttpServletRequest request, List<String> mutterListAll) {

        //---- set message List to request scorp ----
        List<String> msgList = inLogic.getMsgList();
        msgList.clear();
        msgList.add(String.format("%sさんが投稿しました。", data.getName()));
        inLogic.setMsgList(msgList);
        request.setAttribute("msgList", msgList);

        //---- set data to session scorp ----
        HttpSession session = request.getSession();
        session.setAttribute("data", data);

        //---- set mutterListAll to application scorp ----
        application.setAttribute("mutterList", mutterListAll);

    }//necessarySetting() for doPost()


    //====== forward to path ======
    public void doForward(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException{
        RequestDispatcher dis = request.getRequestDispatcher(path);
        dis.forward(request, response);
    }//doForward()

}//class
