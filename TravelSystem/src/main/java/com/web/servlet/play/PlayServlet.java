package com.web.servlet.play;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.web.dao.PlayDao;
import com.web.dao.ScenicDao;
import com.web.dao.impl.PlayDaoImpl;
import com.web.dao.impl.ScenicDaoImpl;
import com.web.model.Play;
import com.web.util.PageSupport;

/**
 * Servlet implementation class PlayServlet
 */
@WebServlet("/jsp/back/play.do")
public class PlayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method != null && method.equals("query")) {
			this.query(request,response);
		}else if(method != null && method.equals("add")){
			this.add(request,response);
		}else if(method != null && method.equals("modify")) {
			this.modify(request,response);
		}else if(method != null && method.equals("delete")) {
			this.delete(request,response);
		}else if(method != null && method.equals("modifysave")) {
			this.modifySave(request,response);
		}else if(method != null && method.equals("ajaxquery")) {
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
	
	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageIndex = request.getParameter("pageIndex");
		String queryScenicLocation = request.getParameter("queryScenicLocation");
		String queryScenicNumber = request.getParameter("queryScenicNumber");
		
		PlayDao playDao = new PlayDaoImpl();
		ScenicDao scenicDao = new ScenicDaoImpl();
		
		int pageSize = 5;
        int currentPageNo = 1;
        
        if(queryScenicLocation == null) {
        	queryScenicLocation = "";
        }
        
        if(queryScenicNumber == null) {
        	queryScenicNumber = "";
        }
        
        if(pageIndex != null) {
        	if(Integer.parseInt(pageIndex) > 0) {
        		currentPageNo = Integer.parseInt(pageIndex);
        	}
        }
        
        //System.out.println("queryScenicLocation:"+queryScenicLocation+",queryScenicNumber:"+queryScenicNumber);
        
        int totalCount = playDao.selectPlayCount(queryScenicLocation,queryScenicNumber);
        
        PageSupport page = new PageSupport();
        page.setPageSize(pageSize);
        page.setTotalCount(totalCount);
        page.setCurrentPageNo(currentPageNo);
        
        int totalPageCount = page.getTotalPageCount();
        
        if(totalPageCount < 1) {
        	currentPageNo = 1;
        }else if(currentPageNo > totalPageCount){
        	currentPageNo = totalPageCount;
        }
        
        //System.out.println("currentPageNo:"+currentPageNo+",pageSize:"+pageSize+",totalPageCount:"+totalPageCount);
        
		
		List<Play> list = playDao.selectPlayList(queryScenicLocation, queryScenicNumber, currentPageNo, pageSize);
		List<Map<String, Object>> locationList = scenicDao.selectScenicCount();
		//List<Map<String, Object>> scenicNameList = scenicDao.selectScenicNumber(queryScenicLocation);
		if(!list.isEmpty()) {
			request.setAttribute("playList", list);
			request.setAttribute("queryScenicLocation", queryScenicLocation);
			request.setAttribute("queryScenicNumber", queryScenicNumber);
			request.setAttribute("locationList", locationList);
			request.setAttribute("totalCount", totalCount);
			request.setAttribute("currentPageNo", currentPageNo);
			request.setAttribute("totalPageCount", totalPageCount);
			request.getRequestDispatcher("/jsp/back/playlist.jsp").forward(request,response);
		}else {
			String message = "列表没有任何内容";
			request.setAttribute("message",message);
			request.setAttribute("playList", list);
			request.setAttribute("queryScenicLocation", queryScenicLocation);
			request.setAttribute("queryScenicNumber", queryScenicNumber);
			request.setAttribute("locationList", locationList);
			request.getRequestDispatcher("/jsp/back/playlist.jsp").forward(request, response);
		}
		
	}
	
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String message = "";
		
		PlayDao playDao = new PlayDaoImpl();
		
		String scenicNumber = new String(request.getParameter("scenicNumber").getBytes("ISO-8859-1"),"UTF-8");
		String playIntroduction = new String(request.getParameter("playIntroduction").getBytes("ISO-8859-1"),"UTF-8");
		
		Play play = new Play();
		
		play.setScenicNumber(scenicNumber);
		play.setPlayIntroduction(playIntroduction);
		
		int rows = playDao.insertPlay(play);
		
		if(rows > 0) {
			message = "添加成功";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/jsp/back/play.do?method=query").forward(request, response);
		}else {
			request.getRequestDispatcher("/jsp/back/error.jsp").forward(request, response);
		}
		
	}
	
	private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		PlayDao playDao = new PlayDaoImpl();
		ScenicDao scenicDao = new ScenicDaoImpl();
		
		List<Map<String, Object>> list = scenicDao.selectScenicNumber(null);
		Play play = playDao.selectPlayById(id);
		if(play != null) {
			request.setAttribute("play", play);
			request.setAttribute("scenicNumberList", list);
			request.getRequestDispatcher("/jsp/back/playmodify.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/jsp/back/error.jsp").forward(request, response);
		}
		
	}
	
	private void modifySave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PlayDao playDao = new PlayDaoImpl();
		
		int id = Integer.parseInt(request.getParameter("playId"));
		String scenicNumber = new String(request.getParameter("scenicNumber").getBytes("ISO-8859-1"),"UTF-8");
		String playIntroduction = new String(request.getParameter("playIntroduction").getBytes("ISO-8859-1"),"UTF-8");
		
		Play play = new Play();
		play.setId(id);
		play.setScenicNumber(scenicNumber);
		play.setPlayIntroduction(playIntroduction);
		
		int rows = playDao.updatePlay(play);
		if(rows > 0) {
			request.getRequestDispatcher("/jsp/back/play.do?method=query").forward(request, response);
		}else {
			request.getRequestDispatcher("/jsp/back/error.jsp").forward(request, response);
		}
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		PlayDao playDao = new PlayDaoImpl();
		int rows = playDao.deletePlay(id);
		if(rows > 0) {
			request.setAttribute("message", "删除成功");
			request.getRequestDispatcher("/jsp/back/play.do?method=query").forward(request, response);
		}else {
			request.getRequestDispatcher("/jsp/back/error.jsp").forward(request, response);
		}
		
	}
	
	private void ajaxQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		
		String queryScenicLocation = request.getParameter("queryScenicLocation");
		
		ScenicDao scenicDao = new ScenicDaoImpl();
		List<Map<String, Object>> scenicNameList = scenicDao.selectScenicNumber(queryScenicLocation);
		
		response.getWriter().write(JSON.toJSONString(scenicNameList));
		
	}

}
