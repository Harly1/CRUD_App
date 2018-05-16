package controller;

import service.DBService;
import service.DBServiceImp;
import util.DbHelper;
import dao.UserDAOImp;
import dao.UserDAO;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

    /**
     * ControllerServlet.java
     * This servlet acts as a page controller for the application, handling all
     * requests from the user.
     * @author www.codejava.net
     */
    @WebServlet (name="ControlServlet", displayName="ControlServlet", urlPatterns = {"/"})
    public class ControlServlet extends HttpServlet {

        private static final long serialVersionUID = 1L;
        private DBService dbService;

        public void init() {
            dbService = new DBServiceImp();
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doGet(request, response);
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
            List<User> listUser = dbService.getUserDAO().listAllUsers();
            request.setAttribute("listUser", listUser);
            RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");
            dispatcher.forward(request, response);
        }

    }

