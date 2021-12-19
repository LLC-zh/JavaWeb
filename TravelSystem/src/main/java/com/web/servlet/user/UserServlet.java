package com.web.servlet.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.dao.UserDao;
import com.web.dao.impl.UserDaoImpl;
import com.web.model.User;
import com.web.util.Constants;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user.do")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action != null && action.equals("modify")) {
			this.modifyUser(request,response);
		}else if(action != null && action.equals("logout")) {
			this.logoutWeb(request,response);
		}else if(action != null && action.equals("modifySave")) {
			this.modifySave(request,response);
		}else if(action != null && action.equals("ajaxquery")) {
			this.ajaxQuery(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void modifyUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/jsp/back/usermodify.jsp").forward(request, response);
	}
	
	private void modifySave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String modifiedPassword = request.getParameter("confirm");
		String uuid = request.getParameter("userUUid");
		
		User user = new User();
		user.setUserUUid(uuid);
		user.setUserPassword(modifiedPassword);
		
		UserDao userDao = new UserDaoImpl();
		int rows = userDao.updateUser(user);
		
		if(rows > 0) {
			request.setAttribute("message", "修改成功");
			request.getRequestDispatcher("/jsp/back/usermodify.jsp").forward(request, response);
		}else {
			request.setAttribute("message", "修改失败");
			request.getRequestDispatcher("/jsp/back/usermodify.jsp").forward(request, response);
		}
		
	}
	
	private void ajaxQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uuid = request.getParameter("uuid");
		String password = request.getParameter("password");
		
		UserDao userDao = new UserDaoImpl();
		User user = userDao.selectUserByUUid(uuid);
		
		if(password.equals(user.getUserPassword())) {
			response.getWriter().print(true);
		}else {
			response.getWriter().print(false);
		}
		
	}
	
	private void logoutWeb(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute(Constants.USER_SESSION);
		request.getRequestDispatcher("/login.jsp").forward(request, response);
		
	}

}
