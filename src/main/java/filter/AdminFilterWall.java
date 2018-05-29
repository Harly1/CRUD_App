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

@WebFilter(urlPatterns = {"/admin/*"}, filterName="AdminFilterWall")
public class AdminFilterWall implements Filter {
    private static FilterConfig config = null;
    private static boolean active = true;

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        String act = config.getInitParameter("active");
        if (act != null)
            active = (act.toUpperCase().equals("TRUE"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        if (active){
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        HttpSession session = request.getSession();

        User userInSession = (User) session.getAttribute("username");
        String role  = userInSession.getRole();


            // Здесь можно вставить код для обработки
            switch (role){
                case "admin":
                    filterChain.doFilter(request, response);
//                    response.sendRedirect("/admin/list");
                    break;
                default:
                    response.setStatus(403);
                    break;
            }
        }

    }

    @Override
    public void destroy() {

    }
}
