package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CalendarLogic;
import model.MemoLogic;
import model.Message;

@WebServlet("/FunctionServlet")
public class FunctionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Message mess;
    private CalendarLogic calen;
    private MemoLogic memoLogic;
    private HttpSession session;


    public void init(ServletConfig config) throws ServletException {
        mess = new Message();
        memoLogic = new MemoLogic();

    }//init()

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session = request.getSession();
        calen = (CalendarLogic) session.getAttribute("calen");

        String move = request.getParameter("move");

        calen.moveSwitch(move);

        if (move.equals("prev") || move.equals("next")) {
            doForward(request, response);
        }
    }//doGet()


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }//doPost()


    private void doForward(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //---- neccesary setting ----
        mess.msgForInput();
        request.setAttribute("msgList", mess.getMsgList());
        session.setAttribute("calen", calen);

        //---- memoServlet ----
        memoLogic.memoFirstDay(calen);
        request.setAttribute("memoList", memoLogic.getMemoList());

        String path = "/WEB-INF/webCalendar/calendarView.jsp";
        RequestDispatcher dis = request.getRequestDispatcher(path);
        dis.forward(request, response);

    }//doForward()

}//class
