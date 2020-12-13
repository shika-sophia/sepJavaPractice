package servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


@WebFilter("/*")
public class WebCalendarFilter implements Filter {

    public WebCalendarFilter() {

    }//constractor

    public void init(FilterConfig fConfig) throws ServletException {

    }//init()

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        //doFilter((HttpServletRequest) request, (HttpServletResponse) response);

        chain.doFilter(request, response);
    }//doFilter()

//    public void doFilter(HttpServletRequest request, HttpServletResponse response)
//            throws IOException, ServletException {
//        CalendarServlet cs = new CalendarServlet();//毎回newするとdata消えるしNullPの原因
//
//        HttpSession session = request.getSession();
//
//        try {
//            CalendarLogic calen = (CalendarLogic) session.getAttribute("calen");
//
//        } catch (IllegalStateException e) {
//            System.out.println("< ! > Session Time Out");
//            cs.doGet(request, response);
//
//        } catch (NullPointerException e) {
//            System.out.println("< ! > NullPointerException of session 'calen' in doFilter()");
//            cs.doGet(request, response);
//        }//try-catch
//    }//doFilter()

    public void destroy() {

    }//destroy()

}//class Filter
