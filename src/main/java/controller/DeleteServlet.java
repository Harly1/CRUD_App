package controller;

import dao.UserDAOImp;
import service.DBService;
import service.DBServiceImp;
import util.DbHelper;
import dao.UserDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="DeleteServlet", displayName="DeleteServlet", urlPatterns = {"/delete"})
public class DeleteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DBService dbService;

    public void init() {
        dbService = new DBServiceImp();
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        try {
            deleteUser(httpServletRequest,httpServletResponse);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        doGet(httpServletRequest, httpServletResponse);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = new User(id);
        dbService.getUserDAO().deleteUser(user);
        response.sendRedirect("list");
    }
}
