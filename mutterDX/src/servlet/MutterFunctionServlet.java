package servlet;

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

import model.LoadLogic;
import model.MutterData;
import model.MutterLoginLogic;
import model.SaveLogic;

@WebServlet("/MutterFunctionServlet")
public class MutterFunctionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MutterLoginLogic inLogic;
    private MutterData data;
    private HttpSession session;
    private ServletContext application;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //---- initiaize ----
        request.setCharacterEncoding("UTF-8");

        inLogic = new MutterLoginLogic();

        session = request.getSession();
        data = (MutterData) session.getAttribute("data");

        application = this.getServletContext();
        @SuppressWarnings("unchecked")
        List<String> mutterListAll = (List<String>) application.getAttribute("mutterList");

        //---- get Query "?action" ----
        String msgFlag = request.getParameter("action");

        if (msgFlag == null) {
            //---- forward to Login <again> ----
            String path = "/MutterLoginServlet";
            doForward(request, response, path);
            return;
        }

        //---- msgListの分岐 ----
        List<String> msgList = inLogic.getMsgList();

        switch (msgFlag) {
        case "load":
            msgList.add("現在のつぶやきは消えます。");
            msgList.add("ロードしますか？");
            break;

        case "save":
        case "logout":
            msgList.add("ご自分の「つぶやき」のみ保存できます。");
            msgList.add("セーブしますか？");
            break;

        case "edit":
            //MutterEditServletへ
            break;
        }//switch

        inLogic.setMsgList(msgList);

        //---- neccesary setting to doFoward [functionConfirm.jsp] ----
        request.setAttribute("msgFlag", msgFlag);
        request.setAttribute("msgList", msgList);
        session.setAttribute("data", data);
        application.setAttribute("mutterList", mutterListAll);

        String path = "functionConfirm.jsp";
        doForward(request, response, path);
    }//doGet()


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String msgFlag = request.getParameter("msgFlag");
        String confirm = request.getParameter("confirm");

        String path = "";
        switch (msgFlag) {
        case "load":
            if (confirm.equals("yes")) {
                LoadLogic load = new LoadLogic();
                load.loadDB(data);
                List<String> mutterListAll = new ArrayList<>(data.getMutterList());
            } else {
                path = "/mutterDX/MutterServlet";
            }
            break;

        case "save":
            if (confirm.equals("yes")) {
                SaveLogic save = new SaveLogic();
            } else {
                path = "/mutterDX/MutterServlet";
            }
            break;

        case "logout":
            if (confirm.equals("yes")) {
                SaveLogic save = new SaveLogic();
            } else {
                path = "/mutterDX/MutterLogoutServlet";
            }
            break;
        }//switch


    }//doPost()

    //====== forward to path ======
    public void doForward(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException{
        RequestDispatcher dis = request.getRequestDispatcher(path);
        dis.forward(request, response);
    }//doForward()

}//class
