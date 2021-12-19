package com.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.dao.ScenicDao;
import com.web.dao.UserDao;
import com.web.dao.impl.ScenicDaoImpl;
import com.web.dao.impl.UserDaoImpl;
import com.web.model.User;
import com.web.util.Constants;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/welcome.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		initView(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void initView(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		
		UserDao userDao = new UserDaoImpl();
		ScenicDao scenicDao = new ScenicDaoImpl();
		
		try {
			//PrintWriter out = resp.getWriter();
			User user = new User();
			String userName = req.getParameter("userName");
			String userPassword = req.getParameter("userPassword");
			user = userDao.selectUserLogin(userName, userPassword);
			
			if(user == null) {
				req.setAttribute("message", "登陆失败，请检查用户名或密码.");
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			}else if(user.getIsAdmin() == 0){
				List<Map<String,Object>> scenicAllList = scenicDao.selectScenicAll();
				req.setAttribute("scenicAllList",scenicAllList);
				req.getSession().setAttribute(Constants.USER_SESSION, user);
				req.getRequestDispatcher("/jsp/front/welcome.jsp").forward(req, resp);
			}else if(user.getIsAdmin() == 1) {
				req.getSession().setAttribute(Constants.USER_SESSION, user);
				req.getRequestDispatcher("/jsp/back/index.jsp").forward(req, resp);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
