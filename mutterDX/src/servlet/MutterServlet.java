package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Message;
import model.MutterData;
import model.MutterLogic;


@WebServlet("/MutterServlet")
public class MutterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MutterLogic logic;
    private Message mess;
    private MutterData data;
    private HttpSession session;
    private ServletContext application;
    private int count;

    //---- initalize ----
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        logic = new MutterLogic();
        mess = new Message();
        application = this.getServletContext();

        count = 0;
    }//init()

    //----<a href="?action=admit"> from [mutterConfirm.jsp] ----
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //---- continued data -> this.field ----
        session = request.getSession();
        data = (MutterData)session.getAttribute("data");

        //---- get Query "?action" ----
        String msgFlag = request.getParameter("action");

        if (msgFlag == null) {
            //---- forward to Login <again> ----
            String path = "/MutterLoginServlet";
            doForward(request, response, path);
            return;
        }

        //---- msgListの分岐 ----
        mess.msgMutterDoGet(msgFlag);

        //---- set to necessary scorp ----
        preNecessarySetting(request, msgFlag);

        //---- forward to mutter ----
        String path ="/WEB-INF/mutter/mutter.jsp";
        doForward(request, response, path);
    }//doGet()


    //====== set to necessary scorp for doGet() -> [mutter.jsp] ======
    @SuppressWarnings("unchecked")
    private void preNecessarySetting(HttpServletRequest request, String msgFlag) {
        //---- set message List to request scorp ----
        request.setAttribute("msgFlag", msgFlag);

        List<String> msgList = mess.getMsgList();
        request.setAttribute("msgList", msgList);

        //---- set  data  to session scorp ----
        session.setAttribute("data", data);

        //---- set List as empty to application scorp ----
        //初回
        List<String> mutterListAll = logic.getMutterListAll();
        List<String> dateTimeListAll = logic.getDateTimeListAll();

        //２回目以降
        if (count > 0) {
            mutterListAll = (List<String>) application.getAttribute("mutterList");
            dateTimeListAll = (List<String>) application.getAttribute("dateTimeList");
        }
        count++;

        application.setAttribute("mutterList", mutterListAll);
        application.setAttribute("dateTimeList", dateTimeListAll);
    }//preNecessarySetting() for doGet()


    //====== <form action> from [mutter.jsp] ======
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //---- get mutter, mutterListAll ----
        String mutter = request.getParameter("mutter");

        //---- application scorpの内容を取得 ----
        List<String> mutterListAll = (List<String>) application.getAttribute("mutterList");
        List<String> dateTimeListAll = (List<String>) application.getAttribute("dateTimeList");

//        //---- リロード対策に最新のmutterを準備 ----
//        String lastMutter = "";
//        int lastIndex = (mutterListAll.size() - 1);
//
//        if (mutterListAll.isEmpty()) {
//            ;
//        } else {
//            lastMutter = mutterListAll.get(lastIndex);
//        }
//
//        //---- [mutter.jsp]に表示するメッセージを分岐----
          String msgFlag = "postMutter";
//
//        if(mutter.equals(lastMutter)) {
//            msgFlag = "reload";
//        }

        //---- mutterの文字数チェック----
        msgFlag = logic.checkMutter(mutter);

        //---- mutter -> List -> 更新したListを取得 ----
        if (msgFlag.equals("postMutter")) {
            logic.addMutter(mutter, data, mutterListAll, dateTimeListAll);
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
        mess.msgMutterDoPost(msgFlag, data);
        List<String> msgList = mess.getMsgList();

        request.setAttribute("msgFlag", msgFlag);
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
