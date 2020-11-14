package webPractice.webJanken.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/WebJankenServlet")
public class WebJankenServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Integer count;

    public void init(ServletConfig config) throws ServletException {

        super.init(config);

        //訪問回数を表すIntegerインスタンスの新規作成
        this.count = 0;

    }//init()

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (count == 0) {
            count = 1;
        } else {
             count++;
            //count = model.WebJankenLogic.getWinList().size();
        }

        request.setCharacterEncoding("UTF-8");
        request.setAttribute("count", count);

        String message = "どれかを選んでください。";
        request.setAttribute("message", message);

        String path = "/webJanken_input.jsp";
        RequestDispatcher dis = request.getRequestDispatcher(path);
        dis.forward(request, response);
    }//doGet()


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String userHand = request.getParameter("radio");

        String message = "";
        if(userHand == null) {
            message = "どれかを必ず選んでください。";
            request.setAttribute("message", message);

            request.setAttribute("count", count);

            String path = "/webJanken_input.jsp";
            RequestDispatcher dis = request.getRequestDispatcher(path);
            dis.forward(request, response);
        }
        
    }//doPost

}//class
