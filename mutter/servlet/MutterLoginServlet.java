package webPractice.mutter.servlet;

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

import webPractice.mutter.model.MutterData;
import webPractice.mutter.model.MutterLoginLogic;


@WebServlet("/MutterLoginServlet")
public class MutterLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MutterLoginLogic inLogic;
    private MutterData data;

    //======◆Start Point ======
    //---- initalize ----
    public void init(ServletConfig config) throws ServletException {
        inLogic = new MutterLoginLogic();
        data = new MutterData();
    }//init()

    //====== get Query and swich ======
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //---- get Query "?action" ----
        request.setCharacterEncoding("UTF-8");
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
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        //---- judge Login ----
        boolean isLogin = inLogic.loginLogic(name, pass);

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
        }

        //---- isLogin == true -> set to data.field ----
        String passCode = inLogic.passCode(pass);

        data = new MutterData(name, pass, passCode);

        //---- set data to session scorp ----
        HttpSession session = request.getSession();
        session.setAttribute("data", data);

        //---- forward to confirm ----
        String path = "/WEB-INF/mutter/mutterConfirm.jsp";
        doForward(request, response, path);
    }//doPost()


    //====== forward ======
    public void doForward(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException{
        RequestDispatcher dis = request.getRequestDispatcher(path);
        dis.forward(request, response);
    }//doForward()

}//class
