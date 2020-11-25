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


@WebServlet("/MutterRegisterServlet")
public class MutterRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MutterLoginLogic inLogic;
    private MutterData data;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        inLogic = new MutterLoginLogic();

        HttpSession session = request.getSession();
        data = (MutterData) session.getAttribute("data");

        if (data == null) {
            inLogic.setMsgList("タイムアウトしたので再ログインしてください。");
            List<String> msgList = inLogic.getMsgList();
            request.setAttribute("msgList", msgList);

            String path = "/WEB-INF/mutter/mutterLogin.jsp";
            doForward(request,response, path);
        }

        //---- message List ----
        inLogic.setMsgList("未登録なので登録してください。");
        List<String> msgList = inLogic.getMsgList();
        request.setAttribute("msgList", msgList);

        //---- set  data  to session scorp ----
        session.setAttribute("data", data);

        //---- forward to register ----
        String path = "/WEB-INF/mutter/mutterRegister.jsp";
        doForward(request,response, path);
    }//doGet()


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }//doPost()


    //====== forward ======
    public void doForward(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException{
        RequestDispatcher dis = request.getRequestDispatcher(path);
        dis.forward(request, response);
    }//doForward()
}//class
