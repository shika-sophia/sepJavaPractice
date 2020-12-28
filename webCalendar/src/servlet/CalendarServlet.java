/**
 * @title webCalendar / servlet / CalendarServlet
 * @content
 * @class
 * @author shika
 * @date 2020-12-08 ～
 */

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
import model.InputLogic;
import model.Message;


@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Message mess;
    private InputLogic inLogic;
    private CalendarLogic calen;
    private MemoServlet memoServlet;
    private HttpSession session;

    //====== ◆Start Point ======
    //------ initialize ------
    public void init(ServletConfig config) throws ServletException {
        mess = new Message();
        inLogic = new InputLogic();
        calen = new CalendarLogic();
        memoServlet = new MemoServlet();
    }//init()

    //====== initial calendar ======
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session = request.getSession();
        mess.msgForInput();
        calen.dateNow();

        withMemo(request, response);
    }//doGet()


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String yearStr = request.getParameter("inputYear");
        String monthStr = request.getParameter("inputMonth");

        //---- 不正値チェック(非整数)----
        boolean isMatch = inLogic.transInt(mess, yearStr, monthStr);

        int year = 0;
        int month = 0;

        if(isMatch) {
            //---- 不正値チェック(範囲外) ----
            isMatch = inLogic.inputMatch(mess);

            year = inLogic.getYear();
            month = inLogic.getMonth();
        }

        if(isMatch) {
            calen.dateInput(year, month);
        } else {
            calen.dateNow();
        }

        withMemo(request, response);
    }//doPost()


    private void withMemo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //---- necessary setting ----
        session.setAttribute("mess", mess);
        session.setAttribute("calen", calen);

        //---- MemoServlet ----
        memoServlet.doGet(request, response);
    }//withMemo()

}//class
