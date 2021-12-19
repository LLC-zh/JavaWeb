package com.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.dao.UserDao;
import com.web.dao.impl.UserDaoImpl;
import com.web.model.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register.do")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null && action.equals("username")) {
			selectUserName(request,response);
		}else if(action != null && action.equals("addUser")) {
			insertUser(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void selectUserName(HttpServletRequest req,HttpServletResponse resp) {
		String username = req.getParameter("username");
		UserDao dao = new UserDaoImpl();
		boolean result = dao.selectUserByName(username);
		try {
			PrintWriter out = resp.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void insertUser(HttpServletRequest request, HttpServletResponse response) {
		
		String userName = request.getParameter("username");
		String userPassword = request.getParameter("confirm");
		
		UserDao dao = new UserDaoImpl();
		User user = new User();
		user.setUserName(userName);
		user.setUserPassword(userPassword);
		int rows = dao.insertUser(user);
		try {
			if(rows > 0) {
				response.sendRedirect(request.getContextPath()+"/login.jsp");
			}else {
				response.sendRedirect(request.getContextPath()+"/error.jsp");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
