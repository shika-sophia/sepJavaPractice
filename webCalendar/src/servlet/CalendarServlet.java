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
import model.InputLogic;
import model.Message;


@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Message mess;
    private InputLogic inLogic;
    private CalendarLogic calen;
    private HttpSession session;

    public void init(ServletConfig config) throws ServletException {
        mess = new Message();
        inLogic = new InputLogic();
        calen = new CalendarLogic();
    }//init()


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session = request.getSession();

        mess.msgForInput();
        calen.dateNow();

        doForward(request, response);
    }//doGet()


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String yearStr = request.getParameter("year");
        String monthStr = request.getParameter("month");
        String move = request.getParameter("move");

        boolean isMatch = inLogic.transInt(mess, yearStr, monthStr);

        if(isMatch == false) {
            doForward(request, response);
            return;
        }
        isMatch = inLogic.inputMatch(mess);

        int year = inLogic.getYear();
        int month = inLogic.getMonth();

        if(isMatch) {
            calen.dateInput(year, month);
        }

        doForward(request, response);
    }//doPost()


    private void doForward(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //---- necessary setting ----
        request.setAttribute("msgList", mess.getMsgList());
        session.setAttribute("calen", calen);

        //---- forward ----
        String path = "/WEB-INF/webCalendar/calendarView.jsp";
        RequestDispatcher dis = request.getRequestDispatcher(path);
        dis.forward(request, response);
    }//doForward()

}//class
