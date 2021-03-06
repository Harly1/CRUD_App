package controller;

import filter.AdminFilterWall;
import model.User;
import service.UserService;
import service.UserServiceImp;

import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name="LoginServlet", displayName="LoginServlet",urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {


	@Override
	protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

		RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("UserLogin.jsp");
		dispatcher.forward(httpServletRequest, httpServletResponse);
	}

	protected void doPost(HttpServletRequest request,
						  HttpServletResponse response) throws ServletException, IOException {


		String name = request.getParameter("name");
		String passwordFromForm = request.getParameter("password");


		User user = null;
		try {
			user = new UserServiceImp().getUserByName(name);
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().println("Login or password is wrong");
		}

		HttpSession session = request.getSession(true);
		session.setAttribute("username", user);

		if (user == null) {
			response.getWriter().println("Login or password is invalid");
			response.sendRedirect("/login");
		} else {

			String passwordFromDB = user.getPassword();
			String role = user.getRole();

			if(passwordFromDB.equals(passwordFromForm)) {
				switch (role) {
					case "admin":
						response.sendRedirect("/admin/list");

						break;
					case "user":
						response.sendRedirect("/user/welcome");

						break;
					default:
						response.getWriter().println("Login or password is invalid");
						break;
				}
			} else {
				response.getWriter().println("Login or password is invalid");
			}
		}
	}
}
