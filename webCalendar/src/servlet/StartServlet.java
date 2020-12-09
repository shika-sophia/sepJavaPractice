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

import model.InputLogic;
import model.Message;
import model.MyCalendar;


@WebServlet("/StartServlet")
public class StartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Message mess;
    private InputLogic inLogic;
    private MyCalendar calen;
    private HttpSession session;

    public void init(ServletConfig config) throws ServletException {
        mess = new Message();
        inLogic = new InputLogic();
        calen = new MyCalendar();

    }//init()


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        mess.msgForInput();
        session = request.getSession();

        doForward(request, response);
    }//doGet()


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String yearStr = request.getParameter("year");
        String monthStr = request.getParameter("month");

        inLogic.transInt(mess, yearStr, monthStr);
        boolean isMatch = inLogic.inputMatch(mess);

        int year = inLogic.getYear();
        int month = inLogic.getMonth();

        if(isMatch) {
            calen.buildCalendar(year, month);
        }

        doForward(request, response);
    }//doPost()


    private void doForward(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //---- necessary ----
        request.setAttribute("msgList", mess.getMsgList());
        session.setAttribute("dayList", calen.getDayList());

        //---- forward ----
        String path = "/WEB-INF/webCalendar/input.jsp";
        RequestDispatcher dis = request.getRequestDispatcher(path);
        dis.forward(request, response);
    }//doForward()

}//class
