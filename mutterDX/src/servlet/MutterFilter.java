package servlet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;


@WebFilter("/*")
public class MutterFilter extends HttpServlet implements Filter {
    private static final long serialVersionUID = 1L;

    public void init(FilterConfig arg0) throws javax.servlet.ServletException {

    }//init()

    public void doFilter(ServletRequest request, ServletResponse respnse, FilterChain chain)
            throws java.io.IOException, javax.servlet.ServletException {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, respnse);
    }//doFilter()

    public void destroy() {

    }//destroy()
}//class
