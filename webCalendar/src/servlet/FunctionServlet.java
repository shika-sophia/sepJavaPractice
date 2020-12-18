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
import model.Message;

@WebServlet("/FunctionServlet")
public class FunctionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Message mess;
    private CalendarLogic calen;
    private HttpSession session;


    public void init(ServletConfig config) throws ServletException {
        mess = new Message();

    }//init()

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        calen = (CalendarLogic) session.getAttribute("calen");

        String move = request.getParameter("move");

        calen.moveSwitch(move);

        String path = "";
        if(move.equals("memo")) {
            path = "";
            response.sendRedirect(path);
            return;

        } else if (move.equals("prev") || move.equals("next")) {
            path = "/WEB-INF/webCalendar/calendarView.jsp";

            request.setAttribute("msgList", mess.getMsgList());
            session.setAttribute("calen", calen);

            RequestDispatcher dis = request.getRequestDispatcher(path);
            dis.forward(request, response);
        }
    }//doGet()


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }//doPost()

}
