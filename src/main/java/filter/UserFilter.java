package filter;


import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/user/*"}, filterName="UserFilter")
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        String role;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;


        HttpSession session = request.getSession();
        User userInSession = (User) session.getAttribute("username");
        role = (userInSession == null)? null : userInSession.getRole();

        if ("user".equals(role) || "admin".equals(role)){

//            response.sendRedirect("/user/welcome");
            filterChain.doFilter(request,response);

        } else {
//            response.sendRedirect("/user/registration");
            filterChain.doFilter(request,response);
        }
    }
    @Override
    public void destroy() {

    }
}
