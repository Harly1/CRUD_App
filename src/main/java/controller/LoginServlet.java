package controller;

import model.User;
import service.UserService;
import service.UserServiceImp;

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


	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {


		String name = request.getParameter("name");

		User user = null;
		try {
			user = new UserServiceImp().getUserByName(name);
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().println("Login or password is wrong");
		}

		HttpSession session = request.getSession(true);
		session.setAttribute("username",user);

		if(user.getRole().equals("admin")){
			response.sendRedirect("/admin");
		} else {
			response.sendRedirect("/user");
		}


	/*	if(userID.equals(user) && password.equals(pwd)){
			HttpSession session = request.getSession();
			session.setAttribute("user", "Pankaj");
			//setting session to expiry in 30 mins
			session.setMaxInactiveInterval(30*60);
			Cookie userName = new Cookie("user", user);
			userName.setMaxAge(30*60);
			response.addCookie(userName);
			response.sendRedirect("LoginSuccess.jsp");
		}else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>Either user name or password is wrong.</font>");
			rd.include(request, response);
		}*/

	}

}
