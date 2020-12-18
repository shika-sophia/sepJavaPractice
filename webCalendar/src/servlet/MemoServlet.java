package servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CalendarLogic;
import model.Message;


@WebServlet("/MemoServlet")
public class MemoServlet extends HttpServlet {
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

        int year = Integer.parseInt(request.getParameter("year"));
        int month = Integer.parseInt(request.getParameter("month"));

        try {
            int day = Integer.parseInt(request.getParameter("day"));
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException of \'day\' in Memo");

        }

    }//doGet()


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }//doPost()

}//class
