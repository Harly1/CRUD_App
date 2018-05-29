package controller;

import model.User;
import service.UserService;
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
import java.util.List;

@WebServlet(name="UserServlet", displayName="UserServlet", urlPatterns = {"/user/welcome"})
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        HttpSession session = httpServletRequest.getSession();
        User userInSession = (User) session.getAttribute("username");
        String username = userInSession.getName();

        UserService dbService = new UserServiceImp();
        String userName = null;
        try {
            userName = dbService.getUserByName(username).getName();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        httpServletRequest.setAttribute("userName", userName);

        RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("UserHello.jsp");

        dispatcher.forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

    }




}
