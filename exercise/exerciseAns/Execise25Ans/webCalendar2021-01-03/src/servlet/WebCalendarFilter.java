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

        chain.doFilter(request, response);
    }//doFilter()


    public void destroy() {

    }//destroy()

}//class Filter
