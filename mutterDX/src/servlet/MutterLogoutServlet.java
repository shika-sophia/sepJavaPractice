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

import model.Message;


@WebServlet("/MutterLogoutServlet")
public class MutterLogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Message mess;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String msgFlag = request.getParameter("action");

        if (msgFlag == null) {
            //---- forward to Login <again> ----
            String path = "/MutterLoginServlet";
            RequestDispatcher dis = request.getRequestDispatcher(path);
            dis.forward(request, response);
            return;
        }

        mess = new Message();
        mess.msgLogout(msgFlag);

        request.setAttribute("msgFlag", msgFlag);

        List<String> msgList = mess.getMsgList();
        request.setAttribute("msgList", msgList);

        if (msgFlag.equals("finish")) {
            doPost(request, response);
            return;
        }

        //---- forward to logout ----
        String path = "/WEB-INF/mutter/logout.jsp";
        RequestDispatcher dis = request.getRequestDispatcher(path);
        dis.forward(request, response);
    }//doGet()


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //---- Logout処理 ----
        HttpSession session = request.getSession();
        session.invalidate();

        ServletContext application = this.getServletContext();
        application.removeAttribute("mutterList");
        application.removeAttribute("dateTimeList");

        //---- forward to logout ----
        String path = "/WEB-INF/mutter/logout.jsp";
        RequestDispatcher dis = request.getRequestDispatcher(path);
        dis.forward(request, response);
    }//doPost()

}//class
