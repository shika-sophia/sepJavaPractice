package servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MutterData;


@WebFilter("/*")
public class MutterFilter extends HttpServlet implements Filter {
    private static final long serialVersionUID = 1L;
    MutterData data;
    HttpSession session;

    public void init(FilterConfig arg0) throws javax.servlet.ServletException {

    }//init()

    //====== set UTF-8 ======
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws java.io.IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        //---- call Overload method ----
        //doFilter((HttpServletRequest)request, (HttpServletResponse)response, chain);

        //---- doFilter ----
        chain.doFilter(request, response);
    }//doFilter()

    //====== check Session Time Out ======
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        try {
            session = request.getSession();
            data = (MutterData) session.getAttribute("data");

        } catch (IllegalStateException e) {
            System.out.println("< ! > Session Time Out");

        } catch (NullPointerException e) {
            System.out.println("< ! > NullPointerException at session scorp in MutterFilter.java");
        }

        if(session == null || data == null) {
            String path = "/mutterDX/MutterLoginServlet";
            RequestDispatcher dis = request.getRequestDispatcher(path);
            dis.forward(request, response);
            return;
        }//if null

    }//doFilter

    public void destroy() {

    }//destroy()
}//class
