package filter;



import controller.ControlServlet;
import model.User;
import service.UserService;
import service.UserServiceImp;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter(urlPatterns = {"/"}, filterName="ControlServletFilter")
public class ControlServletFilter implements Filter {

    public ControlServletFilter(){

    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {


         HttpServletRequest req = (HttpServletRequest)servletRequest;
         HttpServletResponse res = (HttpServletResponse)servletResponse;

         HttpSession session = req.getSession();
         User userInSession = (User) session.getAttribute("username");

         if(userInSession == null ){
             // Перенаправление на страницу login.jsp

             res.sendRedirect("/login");

         } else {

             try {
                 String username = userInSession.getName();
                 User user = new UserServiceImp().getUserByName(username);
                 String role = user.getRole();

                 if("admin".equals(role)){
                     res.sendRedirect("/admin/list");

                 } else if("user".equals(role)) {
                     res.sendRedirect("/user/welcome");
                 }

             } catch (Exception e) {
                 res.getWriter().println("Incorrect login or password");
                 e.printStackTrace();
             }
         }
    }
}
