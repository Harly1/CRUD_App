package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AddUserServlet", urlPatterns = {"/add"})
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
      String id =  httpServletRequest.getParameter("id");
      String login =  httpServletRequest.getParameter("login");
      String password =  httpServletRequest.getParameter("password");
      httpServletResponse.getWriter().println(id +" "+ login +" "+password);
      Map<String,String[]> map = new HashMap<>();
      map = httpServletRequest.getParameterMap();
      System.out.println("");

    }
}
