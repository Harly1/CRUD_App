package servlets;

import dao.UserDAO;
import users.User;

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
        private UserDAO userDAO;

        public void init() {
            String jdbcURL = getServletContext().getInitParameter("jdbcURL");
            String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
            String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

            userDAO = new UserDAO(jdbcURL, jdbcUsername, jdbcPassword);

        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doGet(request, response);
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            String action = request.getServletPath();

            try {
                switch (action) {
                    case "/new":
                        showNewForm(request, response);
                        break;
                    case "/insert":
                        insertUser(request, response);
                        break;
                    case "/delete":
                        deleteUser(request, response);
                        break;
                    case "/edit":
                        showEditForm(request, response);
                        break;
                    case "/update":
                        updateBook(request, response);
                        break;
                    default:
                        listUser(request, response);
                        break;
                }
            } catch (SQLException ex) {
                throw new ServletException(ex);
            }
        }

        private void listUser(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, IOException, ServletException {
            List<User> listUser = userDAO.listAllUsers();
            request.setAttribute("listUser", listUser);
            RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");
            dispatcher.forward(request, response);
        }

        private void showNewForm(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            RequestDispatcher dispatcher = request.getRequestDispatcher("UserForm.jsp");
            dispatcher.forward(request, response);
        }

        private void showEditForm(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, ServletException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            User existingUser = userDAO.getUser(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("UserForm.jsp");
            request.setAttribute("user", existingUser);
            dispatcher.forward(request, response);

        }

        private void insertUser(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, IOException {

            String name = request.getParameter("name");
            String password = request.getParameter("password");


            User newUser = new User(name, password);
            userDAO.insertUser(newUser);
            response.sendRedirect("list");
        }

        private void updateBook(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String password = request.getParameter("password");


            User user = new User(id, name, password);
            userDAO.updateUser(user);
            response.sendRedirect("list");
        }

        private void deleteUser(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));

            User user = new User(id);
            userDAO.deleteUser(user);
            response.sendRedirect("list");

        }
    }

