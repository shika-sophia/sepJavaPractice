package webPractice.mutter.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webPractice.mutter.model.MutterData;
import webPractice.mutter.model.MutterLoginLogic;


@WebServlet("/MutterServlet")
public class MutterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MutterLoginLogic inLogic;
    private MutterData data;

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
                break;
            }//switch
        }//if-else

        //---- set message List to request scorp ----
        List<String> msgList = inLogic.getMsgList();
        request.setAttribute("msgList", msgList);

        //---- set  data  to session scorp ----
        session.setAttribute("data", data);

        //---- forward to mutter ----
        String path ="/WEB-INF/mutter/mutter.jsp";
        doForward(request, response, path);
    }//doGet()

    //====== <form action> from [mutter.jsp] ======
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String mutter = request.getParameter("mutter");


    }//doPost()


    //====== forward to path ======
    public void doForward(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException{
        RequestDispatcher dis = request.getRequestDispatcher(path);
        dis.forward(request, response);
    }//doForward()

}//class
