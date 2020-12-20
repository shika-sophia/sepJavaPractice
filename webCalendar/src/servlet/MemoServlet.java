package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CalendarLogic;
import model.MemoLogic;


@WebServlet("/MemoServlet")
public class MemoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CalendarLogic calen;
    private MemoLogic memoLogic;
    private HttpSession session;

    public MemoServlet() {
        memoLogic = new MemoLogic();
    }//constractor

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        calen = (CalendarLogic) session.getAttribute("calen");//session"calen": year,monthのみ

        String yearStr = request.getParameter("year");
        String monthStr = request.getParameter("month");
        String dayStr = request.getParameter("day");

        memoLogic.treatDate(yearStr, monthStr, dayStr, calen);
        session.setAttribute("calen", calen);//session"calen": year,month,day
        return;
    }//doGet()


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }//doPost()

}//class
