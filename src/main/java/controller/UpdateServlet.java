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

@WebServlet(name="UpdateServlet", displayName="UpdateServlet", urlPatterns = {"/update"})
public class UpdateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DBService dbService;

    public void init() {
        dbService = new DBServiceImp();
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {
            updateBook(httpServletRequest,httpServletResponse);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        super.doPost(httpServletRequest, httpServletResponse);
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String password = request.getParameter("password");


        User user = new User(id,name, password);
        dbService.getUserDAO().updateUser(user);
        response.sendRedirect("list");
    }
}
