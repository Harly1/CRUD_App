package controller;

import dao.UserDAO;
import factory.UserDaoFactory;
import service.UserService;
import service.UserServiceImp;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
     * ControllerServlet.java
     * This servlet acts as a page controller for the application, handling all
     * requests from the user.
     * @author www.codejava.net
     */
    @WebServlet (name="ControlServlet", displayName="ControlServlet", urlPatterns = {"/admin"})
    public class ControlServlet extends HttpServlet {

        public void init() {
            UserDAO userDAO = UserDaoFactory.getDao();
            try {
                userDAO.printConnectInfo();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            try {
                listUser(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        private void listUser(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, IOException, ServletException {
            UserService dbService = new UserServiceImp();
            List<User> listUser = dbService.listUser();
            request.setAttribute("listUser", listUser);
            RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");
            dispatcher.forward(request, response);
        }

    }

