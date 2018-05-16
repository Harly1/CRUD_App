package controller;

import service.DBService;
import service.DBServiceImp;
import util.DbHelper;
import dao.UserDAOImp;
import dao.UserDAO;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="EditServlet", displayName="EditServlet", urlPatterns = {"/edit"})
public class EditServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DBService dbService;

    public void init() {
        dbService = new DBServiceImp();
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {
            showEditForm(httpServletRequest,httpServletResponse);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        doGet(httpServletRequest, httpServletResponse);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = dbService.getUserDAO().getUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("UserForm.jsp");
        request.setAttribute("model", existingUser);
        dispatcher.forward(request, response);

    }
}
