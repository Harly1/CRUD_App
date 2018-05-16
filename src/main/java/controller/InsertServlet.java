package controller;

import service.DBService;
import service.DBServiceImp;
import util.DbHelper;
import dao.UserDAOImp;
import dao.UserDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="InsertServlet", displayName="InsertServlet", urlPatterns = {"/insert"})
public class InsertServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DBService dbService;

    public void init() {
        dbService = new DBServiceImp();
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        try {
            insertUser(httpServletRequest,httpServletResponse);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        doGet(httpServletRequest, httpServletResponse);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String password = request.getParameter("password");


        User newUser = new User(id, name, password);
        dbService.getUserDAO().insertUser(newUser);
        response.sendRedirect("list");
    }
}
