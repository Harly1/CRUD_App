package controller;

import service.UserService;
import service.UserServiceImp;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="DeleteServlet", displayName="DeleteServlet", urlPatterns = {"/admin/delete"})
public class DeleteServlet extends HttpServlet {

    private UserService dbService;

    public void init() {
        dbService = new UserServiceImp();
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

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = new User(id);
        dbService.deleteUser(user);
        response.sendRedirect("admin/list");
    }
}
