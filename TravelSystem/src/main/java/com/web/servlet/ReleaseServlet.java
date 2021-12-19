package com.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.dao.NoteDao;
import com.web.dao.impl.NoteDaoImpl;
import com.web.model.Note;
import com.web.model.User;
import com.web.util.Constants;

/**
 * Servlet implementation class ReleaseServlet
 */
@WebServlet("/release")
public class ReleaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReleaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
		String uuid = user.getUserUUid();
		String noteTitle = new String(request.getParameter("noteTitle").getBytes("ISO-8859-1"),"UTF-8");
		String noteContent = new String(request.getParameter("noteContent").getBytes("ISO-8859-1"),"UTF-8");
		
		NoteDao noteDao = new NoteDaoImpl();
		Note note = new Note();
		note.setUserUUid(uuid);
		note.setNoteTitle(noteTitle);
		note.setNoteContent(noteContent);
		
		int rows = noteDao.insertNote(note);
		if(rows > 0) {
			request.setAttribute("result", "提交成功");
			request.getRequestDispatcher("/jsp/front/release.jsp").forward(request,response);
		}else {
			request.setAttribute("result", "提交失败");
			request.getRequestDispatcher("/jsp/front/release.jsp").forward(request,response);
		}
	}

}
