package servlets;

import dbService.DataBaseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;



@WebServlet(name = "HelloWorldServlet", urlPatterns = {"/servlet"})

public class HelloWorldServlet extends HttpServlet {
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)  throws IOException {

        httpServletResponse.getWriter().print("Hello from servlet");
        DataBaseServiceImpl dataBaseService = new DataBaseServiceImpl();
        dataBaseService.printConnectInfo();

    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

    }
}
