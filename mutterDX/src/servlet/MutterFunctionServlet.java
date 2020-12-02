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

import model.LoadLogic;
import model.Message;
import model.MutterData;
import model.MutterLogic;
import model.SaveLogic;

@WebServlet("/MutterFunctionServlet")
public class MutterFunctionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MutterLogic logic;
    private MutterData data;
    private Message mess;
    private HttpSession session;
    private ServletContext application;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //---- initiaize ----
        request.setCharacterEncoding("UTF-8");

        logic = new MutterLogic();
        mess = new Message();

        session = request.getSession();
        data = (MutterData) session.getAttribute("data");

        application = this.getServletContext();

        //---- get Query "?action" ----
        String msgFlag = request.getParameter("action");

        if (msgFlag == null) {
            //---- forward to Login <again> ----
            String path = "/MutterLoginServlet";
            RequestDispatcher dis = request.getRequestDispatcher(path);
            dis.forward(request, response);
            return;
        }

        //---- msgListの分岐 ----
        mess.msgFunction(msgFlag);
        List<String> msgList = mess.getMsgList();

        //---- neccesary setting to doFoward [functionConfirm.jsp] ----
        request.setAttribute("msgFlag", msgFlag);
        request.setAttribute("msgList", msgList);
        session.setAttribute("data", data);

        List<String> mutterListAll = logic.getMutterListAll();
        List<String> dateTimeListAll = logic.getDateTimeListAll();
        application.setAttribute("mutterList", mutterListAll);
        application.setAttribute("dataTimeList", dateTimeListAll);

        String path = "/WEB-INF/mutter/functionConfirm.jsp";
        RequestDispatcher dis = request.getRequestDispatcher(path);
        dis.forward(request, response);
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
                load.loadDB(data, logic);
                path = "/mutterDX/MutterServlet?action=load";

            } else {
                path = "/mutterDX/MutterServlet?action=noLoad";
            }
            break;

        case "save":
            if (confirm.equals("yes")) {
                SaveLogic save = new SaveLogic();
                save.saveDB(data);
                path = "/mutterDX/MutterServlet?action=save";
            } else {
                path = "/mutterDX/MutterServlet?action=noSave";
            }
            break;

        case "logout":
            if (confirm.equals("yes")) {
                SaveLogic save = new SaveLogic();
                save.saveDB(data);
                path = "/mutterDX/MutterLogoutServlet?action=saveOut";
            } else {
                path = "/mutterDX/MutterLogoutServlet?action=noSaveOut";
            }
            break;
        }//switch

        response.sendRedirect(path);
    }//doPost()

}//class
