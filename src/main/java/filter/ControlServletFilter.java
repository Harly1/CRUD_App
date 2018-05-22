package filter;



import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter(urlPatterns = {"/"}, filterName="ControlServletFilter")
public class ControlServletFilter implements Filter {

    private FilterConfig filterConfig;
    private static ArrayList<String> pages;

    public ControlServletFilter(){
        // Создание хранилища страниц
        if (pages == null)
            pages = new ArrayList<String>();
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        filterConfig = fConfig;
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        {
            // Если фильтр активной, то выполнить проверку
//            if (filterConfig.getInitParameter("active").equalsIgnoreCase("true")) {
                HttpServletRequest req = (HttpServletRequest)servletRequest;
                // Раскладываем адрес на составляющие
                String[] list = req.getRequestURI().split("/");
                // Извлекаем наименование страницы
                String page = "UserList.jsp";
            /*    if (list[list.length - 1].indexOf(".jsp") > 0) {
                    page = list[list.length - 1];
                }*/
                // Если открывается главная страница, то выполняем проверку
                if ((page != null) && page.equalsIgnoreCase("UserList.jsp")) {
                    // Если была предварительно открыта одна из страниц
                    // login.jsp или registration.jsp, то передаем управление
                    // следующему элементу цепочки фильтра
                    if (pages.contains("UserLogin.jsp") || pages.contains("UserRegistration.jsp")) {
                        filterChain.doFilter(servletRequest, servletResponse);
                        return;
                    } else {
                        // Перенаправление на страницу login.jsp
                        ServletContext ctx = filterConfig.getServletContext();
                        RequestDispatcher dispatcher = ctx.getRequestDispatcher("/UserLogin.jsp");
                        dispatcher.forward(servletRequest, servletResponse);
                        return;
                    }
                } else if (page != null) {
                    // Добавляем страницу в список
                    if (!pages.contains(page))
                        pages.add(page);
                }

            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
