package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DataAccess;
import model.LoginLogic;
import model.MutterData;


@WebServlet("/MutterRegisterServlet")
public class MutterRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginLogic inLogic;
    private DataAccess dataAcs;
    private MutterData data;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //---- initialize ----
        inLogic = new LoginLogic();
        dataAcs = new DataAccess();

        HttpSession session = request.getSession();
        data = (MutterData) session.getAttribute("data");

        //---- case session time out ----
        if (data == null) {
            inLogic.setMsgList("タイムアウトしたので再ログインしてください。");
            List<String> msgList = inLogic.getMsgList();
            request.setAttribute("msgList", msgList);

            String path = "/WEB-INF/mutter/mutterLogin.jsp";
            doForward(request,response, path);
            return;
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


    //====== <form action> from [mutterRegister.jsp] ======
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        String mail = request.getParameter("mail");

        //---- 不正値チェック(name, pass) ----
        //mailは <input type="email">で@の存在のみチェック済
        inLogic.checkInput(name, pass);
        inLogic.checkMail(mail);

        //---- 不正値チェック (pass)----
        //Login時のpassと Register時のpassが一致するか
        boolean isLogin = inLogic.identifyPass(pass, data);

        //---- set it to request scorp ----
        List<String> msgList = inLogic.getMsgList();
        request.setAttribute("msgList", msgList);

        if (msgList.isEmpty()) {
            ;
        //---- isLogin == false ----
        } else if ( !isLogin) {
            //---- forward to Login <again> ----
            String path = "/WEB-INF/mutter/mutterLogin.jsp";
            doForward(request,response, path);
            return;

        //---- msgListがあるとき ----
        } else {
            //---- forward to Register <again> ----
            String path = "/WEB-INF/mutter/mutterRegister.jsp";
            doForward(request,response, path);
            return;
        }

        //---- update data.field ----
        String mailCode = inLogic.mailCode(mail);

        data.setMail(mail);
        data.setMailCode(mailCode);

        HttpSession session = request.getSession();
        session.setAttribute("data", data);

        //---- DBへ登録----
        boolean doneRegister = dataAcs.register(data);

        //----登録できたら MutterServletへ ----
        if (doneRegister) {
            String path = "/mutterDX/MutterServlet?action=doneRegister";
            response.sendRedirect(path);
        } else {
            //登録できないのは、なんらかのプログラムエラー
            inLogic.setMsgList("登録できませんでした。終了します。");
            System.exit(0);
        }

    }//doPost()


    //====== forward ======
    public void doForward(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException{
        RequestDispatcher dis = request.getRequestDispatcher(path);
        dis.forward(request, response);
    }//doForward()
}//class
