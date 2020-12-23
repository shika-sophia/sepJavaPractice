package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CalendarLogic;
import model.MemoLogic;
import model.Message;

@WebServlet("/MemoServlet")
public class MemoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Message mess;
    private CalendarLogic calen;
    private MemoLogic memoLogic;
    private HttpSession session;

    public MemoServlet() {
        memoLogic = new MemoLogic();
    }//constractor

    //====== from CalendarServlet ======
    //====== from FunctionServlet ======
    //====== from calendarMemo.jsp <form> action ======
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session = request.getSession();
        mess = (Message) session.getAttribute("mess");
        calen = (CalendarLogic) session.getAttribute("calen");

        //---- from calendarMemo <form>action ----
        String yearStr = request.getParameter("year");
        String monthStr = request.getParameter("month");
        String dayStr = request.getParameter("day");

        memoLogic.treatDate(yearStr, monthStr, dayStr, calen);
        session.setAttribute("calen", calen);//session"calen": year,month,day

        doForward(request, response);
    }//doGet()


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String memoStr = request.getParameter("memoStr");

        memoLogic.buildMemoList(memoStr);

    }//doPost()

    protected void doForward(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //---- neccesary setting ----
        if(mess.getMsgList().isEmpty()) {
            mess.msgForInput();
        }

        if (calen.getBaseList().isEmpty()) {
            calen.dateInput(calen.getYear(), calen.getMonth());
        }

        request.setAttribute("msgList", mess.getMsgList());
        request.setAttribute("memoList", memoLogic.getMemoList());
        session.setAttribute("mess", mess);
        session.setAttribute("calen", calen);

        mess.getMsgList().clear();
        memoLogic.getMemoList().clear();

        //---- forward ----
        String path = "/WEB-INF/webCalendar/calendarView.jsp";
        RequestDispatcher dis = request.getRequestDispatcher(path);
        dis.forward(request, response);
    }//doForward()
}//class
