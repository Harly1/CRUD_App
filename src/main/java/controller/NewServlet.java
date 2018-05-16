package controller;

import dao.UserDAOImp;
import service.DBService;
import service.DBServiceImp;
import util.DbHelper;
import dao.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="NewServlet", displayName="NewServlet", urlPatterns = {"/new"})
public class NewServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DBService dbService;

    public void init() {
        dbService = new DBServiceImp();
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        showNewForm(httpServletRequest,httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        doGet(httpServletRequest, httpServletResponse);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("UserForm.jsp");
        dispatcher.forward(request, response);
    }
}
