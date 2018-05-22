package controller;

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
    @WebServlet (name="ControlServlet", displayName="ControlServlet", urlPatterns = {"/"})
    public class ControlServlet extends HttpServlet {
        private UserService dbService;

        @Override
        protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
                throws ServletException, IOException {

            try {
                checkUser(httpServletRequest, httpServletResponse);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                listUser(httpServletRequest, httpServletResponse);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void init() {
            dbService = new UserServiceImp();
            try {
                dbService.printConnectInfo();
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
            List<User> listUser = dbService.listUser();
            request.setAttribute("listUser", listUser);
            RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");
            dispatcher.forward(request, response);
        }

        private void checkUser(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, IOException, ServletException {

            HttpSession session = request.getSession();
            Enumeration eNames = session.getAttributeNames();
            List<String> list = new ArrayList<>();
            while (eNames.hasMoreElements()) {
                String attributeName = (String) eNames.nextElement();
                list.add(attributeName);
                Object attribute = session.getAttribute(attributeName);
                System.out.println(attributeName + " as " + attribute.getClass().getName() + ": " + attribute);
            }

            List<User> listUser = dbService.listUser();
            request.setAttribute("listUser", listUser);
            RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");
            dispatcher.forward(request, response);
        }

    }

