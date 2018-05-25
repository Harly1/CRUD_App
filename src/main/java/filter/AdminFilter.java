package filter;


import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin"}, filterName="AdminFilter")
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        HttpSession session = request.getSession();
        User userInSession = (User) session.getAttribute("username");
        String role  = userInSession.getRole();
        RequestDispatcher dispatcher;

        switch (role){
            case "admin":
                    dispatcher = request.getRequestDispatcher("/list");
                    dispatcher.forward(request, response);
                    break;
            case "user":
                    dispatcher = request.getRequestDispatcher("/user");
                    dispatcher.forward(request, response);
                    break;
            default: response.getWriter().println("Role is not defined");
        }
    }

    @Override
    public void destroy() {

    }
}
