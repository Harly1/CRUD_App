package controller;

import model.User;
import service.UserServiceImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="UserRegServlet", displayName="UserRegServlet", urlPatterns = {"/registration"})
public class UserRegServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        String name = httpServletRequest.getParameter("name");
        String password = httpServletRequest.getParameter("password");
        String role = "user";


        User newUser = new User(name, password,role);
        try {
            new UserServiceImp().insertUser(newUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute("username", newUser);
        httpServletResponse.sendRedirect("user/welcome");
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("UserRegistration.jsp");
        dispatcher.forward(httpServletRequest, httpServletResponse);
    }
}
