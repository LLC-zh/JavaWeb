package com.web.servlet.scenic;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.alibaba.fastjson.JSON;
import com.web.dao.ScenicDao;
import com.web.dao.impl.ScenicDaoImpl;
import com.web.model.Scenic;
import com.web.util.PageSupport;

/**
 * Servlet implementation class ScenicServlet
 */
@WebServlet(name = "ScenicServlet",urlPatterns = "/jsp/back/scenic.do")
@MultipartConfig()
public class ScenicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScenicServlet() {
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
		}else if(method != null && method.equals("repush")) {
			this.repush(request,response);
		}else if(method != null && method.equals("repushsave")) {
			this.repushSave(request,response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private String getFilename(Part part) {
		String fname = null;
		String header = part.getHeader("content-disposition");
		System.out.println(header);
		fname = header.substring(header.lastIndexOf("=")+2,header.length()-1);
		return fname;
	}
	
	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageIndex = request.getParameter("pageIndex");
		String queryScenicLocation = request.getParameter("queryScenicLocation");
		
		ScenicDao scenicDao = new ScenicDaoImpl();
		
		int pageSize = 5;
        int currentPageNo = 1;
        
        if(queryScenicLocation == null) {
        	queryScenicLocation = "";
        }
        
        if(pageIndex != null) {
        	if(Integer.parseInt(pageIndex) > 0) {
        		currentPageNo = Integer.parseInt(pageIndex);
        	}
        }
        
        int totalCount = scenicDao.selectScenicCountByLocation(queryScenicLocation);
        
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
        
		
		List<Scenic> list = scenicDao.scenicList(queryScenicLocation,currentPageNo,pageSize);
		List<Map<String, Object>> locationList = scenicDao.selectScenicCount();
		if(!list.isEmpty()) {
			request.setAttribute("scenicList", list);
			request.setAttribute("queryScenicLocation", queryScenicLocation);
			request.setAttribute("locationList", locationList);
			request.setAttribute("totalCount", totalCount);
			request.setAttribute("currentPageNo", currentPageNo);
			request.setAttribute("totalPageCount", totalPageCount);
			request.getRequestDispatcher("/jsp/back/sceniclist.jsp").forward(request,response);
		}else {
			String message = "列表没有任何内容";
			request.setAttribute("message",message);
			request.setAttribute("scenicList", list);
			request.setAttribute("queryScenicLocation", queryScenicLocation);
			request.setAttribute("locationList", locationList);
			request.getRequestDispatcher("/jsp/back/sceniclist.jsp").forward(request, response);
		}
		
	}
	
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String path = this.getServletContext().getRealPath("/");
		
		ScenicDao scenicDao = new ScenicDaoImpl();
		
		//int id = Integer.parseInt(request.getParameter("scenicId"));
		String scenicNumber = new String(request.getParameter("scenic-number").getBytes("ISO-8859-1"),"UTF-8");
		String scenicName = new String(request.getParameter("scenic-name").getBytes("ISO-8859-1"),"UTF-8");
		String scenicLoaction = new String(request.getParameter("scenic-location").getBytes("ISO-8859-1"),"UTF-8");
		String scenicDescribe = new String(request.getParameter("scenic-describe").getBytes("ISO-8859-1"),"UTF-8");
		String scenicImage = "";
		
		Part part = request.getPart("scenic-image");
		//System.out.println(part.getInputStream());
		String message = "";
		if(part.getSize() > 1024*1024) {
			part.delete();
			message = "文件太大，不能上传";
		}else {
			path = path + "\\image\\scenic\\";
			File f = new File(path);
			if(!f.exists()) {
				f.mkdirs();
			}
			String fname = getFilename(part);
			System.out.println(fname);
			part.write(path+"\\"+fname);
			//part.write("E:\\java-workspace\\TravelSystem\\src\\main\\webapp\\image\\scenic"+fname);
			scenicImage = "/image/scenic/"+fname;
			message = "文件上传成功";
			System.out.println(message);
		}
		
		Scenic scenic = new Scenic();
		
		//scenic.setId(id);
		scenic.setScenicNumber(scenicNumber);
		scenic.setScenicName(scenicName);
		scenic.setScenicImage(scenicImage);
		scenic.setScenicLocation(scenicLoaction);
		scenic.setScenicDescribe(scenicDescribe);
		
		int rows = scenicDao.insertScenic(scenic);
		
		if(rows > 0) {
			request.setAttribute("message", message);
			request.getRequestDispatcher("/jsp/back/scenic.do?method=query").forward(request, response);
		}else {
			request.getRequestDispatcher("/jsp/back/error.jsp").forward(request, response);
		}
		
	}
	
	private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		ScenicDao scenicDao = new ScenicDaoImpl();
		Scenic scenic = scenicDao.selectScenicById(id);
		if(scenic != null) {
			request.setAttribute("scenic", scenic);
			request.getRequestDispatcher("/jsp/back/scenicmodify.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/jsp/back/error.jsp").forward(request, response);
		}
		
	}
	
	private void modifySave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ScenicDao scenicDao = new ScenicDaoImpl();
		
		int id = Integer.parseInt(request.getParameter("scenicId"));
		String scenicNumber = new String(request.getParameter("scenicNumber").getBytes("ISO-8859-1"),"UTF-8");
		String scenicName = new String(request.getParameter("scenicName").getBytes("ISO-8859-1"),"UTF-8");
		String scenicImage = new String(request.getParameter("scenicImage").getBytes("ISO-8859-1"),"UTF-8");
		String scenicLocation = new String(request.getParameter("scenicLocation").getBytes("ISO-8859-1"),"UTF-8");
		String scenicDescribe = new String(request.getParameter("scenicDescribe").getBytes("ISO-8859-1"),"UTF-8");
		
		Scenic scenic = new Scenic();
		scenic.setId(id);
		scenic.setScenicNumber(scenicNumber);
		scenic.setScenicName(scenicName);
		scenic.setScenicImage(scenicImage);
		scenic.setScenicLocation(scenicLocation);
		scenic.setScenicDescribe(scenicDescribe);
		
		int rows = scenicDao.updateScenic(scenic);
		if(rows > 0) {
			request.getRequestDispatcher("/jsp/back/scenic.do?method=query").forward(request, response);
		}else {
			request.getRequestDispatcher("/jsp/bakc/error.jsp").forward(request, response);
		}
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		ScenicDao scenicDao = new ScenicDaoImpl();
		int rows = scenicDao.deleteScenic(id);
		if(rows > 0) {
			request.setAttribute("message", "删除成功");
			request.getRequestDispatcher("/jsp/back/scenic.do?method=query").forward(request, response);
		}else {
			request.getRequestDispatcher("/jsp/back/error.jsp").forward(request, response);
		}
		
	}
	
	private void ajaxQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String no = request.getParameter("no");
		boolean result = true;
		
		ScenicDao scenicDao = new ScenicDaoImpl();
		List<Map<String, Object>> noList = scenicDao.selectScenicNumber(null);
		for(Map<String, Object> map: noList) {
			if(map.get("scenicNumber").equals(no)) {
				result = false;
			}
		}
		
		response.getWriter().print(result);
		
	}
	
	private void repush(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		ScenicDao scenicDao = new ScenicDaoImpl();
		Scenic scenic = scenicDao.selectScenicById(id);
		
		String resp = JSON.toJSONString(scenic);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(resp);
		
	}
	
	private void repushSave(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int id = Integer.parseInt(request.getParameter("scenicId"));
		String repushImage = "";
		String message = "";
		String path = this.getServletContext().getRealPath("/");
		
		ScenicDao scenicDao = new ScenicDaoImpl();
		
		Part part = request.getPart("repushImage");
		
		if(part.getSize() > 1024 * 1024) {
			part.delete();
			message = "文件太大，不能上传";
		}else {
			path = path + "\\mage\\scenic";
			File f = new File(path);
			if(!f.exists()) {
				f.mkdirs();
			}
			String fname = getFilename(part);
			repushImage = "/image/scenic/"+fname;
			message = "文件上传成功";
		}
		
		Scenic scenic = new Scenic();
		
		scenic.setId(id);
		scenic.setScenicImage(repushImage);
		
		int rows = scenicDao.updateScenic(scenic);
		
		if(rows > 0) {
			request.setAttribute("message", message);
			request.getRequestDispatcher("/jsp/back/scenic.do?method=query").forward(request, response);
		}else {
			request.setAttribute("message", "修改图片失败");
			request.getRequestDispatcher("/jsp/back/scenic.do?method=query").forward(request, response);
		}
	}
}
