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
import model.MemoLogic;
import model.Message;

@WebServlet("/FunctionServlet")
public class FunctionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Message mess;
    private CalendarLogic calen;
    private MemoServlet memoServlet;
    private HttpSession session;


    public void init(ServletConfig config) throws ServletException {
        memoServlet = new MemoServlet();

    }//init()

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session = request.getSession();
        mess = (Message) session.getAttribute("mess");
        calen = (CalendarLogic) session.getAttribute("calen");

        String move = request.getParameter("move");

        calen.moveSwitch(move);

        if (move.equals("prev") || move.equals("next")) {
            withMemo(request, response);
        }
    }//doGet()

    //====== <form>action from [calendarMemo.jsp] with 'deleteMemo' ======
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] deleteIdStr = request.getParameterValues("deleteMemo");
        int[] deleteId = new int[deleteIdStr.length];

        if (deleteIdStr == null) {
            ;
        } else {
            for (int i = 0; i < deleteIdStr.length; i++) {
                deleteId[i] = Integer.parseInt(deleteIdStr[i]);
            }
        }//if-else

        MemoLogic memoLogic = new MemoLogic();
        memoLogic.deleteMemo(deleteId, calen);
    }//doPost()


    private void withMemo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //---- neccesary setting ----
        session.setAttribute("mess", mess);
        session.setAttribute("calen", calen);

        //---- memoServlet ----
        memoServlet.doGet(request, response);
    }//doForward()

}//class
