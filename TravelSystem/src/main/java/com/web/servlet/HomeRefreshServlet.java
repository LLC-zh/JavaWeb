package com.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.dao.DelicacyDao;
import com.web.dao.NoteDao;
import com.web.dao.ScenicDao;
import com.web.dao.impl.DelicacyDaoImpl;
import com.web.dao.impl.NoteDaoImpl;
import com.web.dao.impl.ScenicDaoImpl;
import com.web.model.Delicacy;

/**
 * Servlet implementation class HomeRefreshServlet
 */
@WebServlet("/home")
public class HomeRefreshServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeRefreshServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if(action != null && action.equals("scenic")) {
			loadScenic(request,response);
		}else if(action != null && action.equals("note")) {
			loadNote(request,response);
		}else if(action != null && action.equals("release")) {
			loadRelease(request,response);
		}else if(action != null && action.equals("delicacy")) {
			loadDelicacy(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void loadScenic(HttpServletRequest request, HttpServletResponse response) {
		ScenicDao scenicDao = new ScenicDaoImpl();
		try {
			List<Map<String,Object>> scenicAllList = scenicDao.selectScenicAll();
			if(!scenicAllList.isEmpty()) {
				request.setAttribute("scenicAllList",scenicAllList);
				request.getRequestDispatcher("/jsp/front/welcome.jsp").forward(request,response);
			}else {
				request.getRequestDispatcher("/error.jsp").forward(request,response);
			}
			
		}catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void loadDelicacy(HttpServletRequest request, HttpServletResponse response) {
		DelicacyDao delicacyDao = new DelicacyDaoImpl();
		
		try {
			List<Delicacy> delicacyList = delicacyDao.selectDelicacyList(null,1,100);
			if(!delicacyList.isEmpty()) {
				request.setAttribute("delicacyList", delicacyList);
				request.getRequestDispatcher("/jsp/front/food.jsp").forward(request,response);
			}else {
				request.getRequestDispatcher("/error.jsp").forward(request,response);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadNote(HttpServletRequest request, HttpServletResponse response) {
		try {
			NoteDao noteDao = new NoteDaoImpl();
			List<Map<String, Object>> list = noteDao.selectNoteAllList();
			if(!list.isEmpty()) {
				request.setAttribute("noteAllList", list);
				request.getRequestDispatcher("/jsp/front/note.jsp").forward(request,response);
			}else {
				request.getRequestDispatcher("/error.jsp").forward(request,response);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void loadRelease(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("result", "");
			request.getRequestDispatcher("/jsp/front/release.jsp").forward(request,response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
