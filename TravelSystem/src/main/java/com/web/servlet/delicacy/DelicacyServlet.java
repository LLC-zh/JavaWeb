package com.web.servlet.delicacy;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.web.dao.DelicacyDao;
import com.web.dao.impl.DelicacyDaoImpl;
import com.web.model.Delicacy;
import com.web.util.PageSupport;

/**
 * Servlet implementation class DelicacyServlet
 */
@WebServlet("/jsp/back/delicacy.do")
@MultipartConfig()
public class DelicacyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelicacyServlet() {
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
		String queryDelicacyName = request.getParameter("queryDelicacyName");
		
		DelicacyDao delicacyDao = new DelicacyDaoImpl();
		
		int pageSize = 5;
        int currentPageNo = 1;
        
        if(queryDelicacyName == null) {
        	queryDelicacyName = "";
        }
        
        if(pageIndex != null) {
        	if(Integer.parseInt(pageIndex) > 0) {
        		currentPageNo = Integer.parseInt(pageIndex);
        	}
        }
        
        int totalCount = delicacyDao.selectDelicacyByName(queryDelicacyName);
        
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
        
		
		List<Delicacy> list = delicacyDao.selectDelicacyList(queryDelicacyName,currentPageNo,pageSize);
		if(!list.isEmpty()) {
			request.setAttribute("delicacyList", list);
			request.setAttribute("queryDelicacyName", queryDelicacyName);
			request.setAttribute("totalCount", totalCount);
			request.setAttribute("currentPageNo", currentPageNo);
			request.setAttribute("totalPageCount", totalPageCount);
			request.getRequestDispatcher("/jsp/back/delicacylist.jsp").forward(request,response);
		}else {
			String message = "列表无任何内容";
			request.setAttribute("delicacyList", "");
			request.setAttribute("queryDelicacyName", queryDelicacyName);
			request.setAttribute("message",message);
			request.getRequestDispatcher("/jsp/back/delicacylist.jsp").forward(request, response);
		}
		
	}
	
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String path = this.getServletContext().getRealPath("/");
		
		DelicacyDao delicacyDao = new DelicacyDaoImpl();
		
		//int id = Integer.parseInt(request.getParameter("scenicId"));
		String delicacyName = new String(request.getParameter("delicacyName").getBytes("ISO-8859-1"),"UTF-8");
		String delicacyPrice = new String(request.getParameter("delicacyPrice").getBytes("ISO-8859-1"),"UTF-8");
		String delicacyIntroduction = new String(request.getParameter("delicacyIntroduction").getBytes("ISO-8859-1"),"UTF-8");
		String delicacyImage = "";
		
		Part part = request.getPart("delicacyImage");
		//System.out.println(part.getInputStream());
		String message = "";
		if(part.getSize() > 1024*1024) {
			part.delete();
			message = "文件太大，不能上传";
		}else {
			path = path + "\\image\\delicacy\\";
			File f = new File(path);
			if(!f.exists()) {
				f.mkdirs();
			}
			String fname = getFilename(part);
			System.out.println(fname);
			part.write(path+"\\"+fname);
			//part.write("E:\\java-workspace\\TravelSystem\\src\\main\\webapp\\image\\scenic"+fname);
			delicacyImage = "/image/delicacy/"+fname;
			message = "文件上传成功";
			System.out.println(message);
		}
		
		Delicacy delicacy = new Delicacy();
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		
		
		//scenic.setId(id);
		delicacy.setDelicacyName(delicacyName);
		delicacy.setDelicacyPrice(decimalFormat.format(Double.parseDouble(delicacyPrice)));
		delicacy.setDelicacyIntroduction(delicacyIntroduction);
		delicacy.setDelicacyImage(delicacyImage);
		
		int rows = delicacyDao.insertDelicacy(delicacy);
		
		if(rows > 0) {
			request.setAttribute("message", message);
			request.getRequestDispatcher("/jsp/back/delicacy.do?method=query").forward(request, response);
		}else {
			request.getRequestDispatcher("/jsp/back/error.jsp").forward(request, response);
		}
		
	}
	
	private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		DelicacyDao delicacyDao = new DelicacyDaoImpl();
		Delicacy delicacy = delicacyDao.selectDelicacyById(id);
		if(delicacy != null) {
			request.setAttribute("delicacy", delicacy);
			request.getRequestDispatcher("/jsp/back/delicacymodify.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/jsp/back/error.jsp").forward(request, response);
		}
		
	}
	
	private void modifySave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DelicacyDao delicacyDao = new DelicacyDaoImpl();
		
		String delicacyId = new String(request.getParameter("delicacyId").getBytes("ISO-8859-1"),"UTF-8");
		String delicacyName = new String(request.getParameter("delicacyName").getBytes("ISO-8859-1"),"UTF-8");
		String delicacyPrice = new String(request.getParameter("delicacyPrice").getBytes("ISO-8859-1"),"UTF-8");
		String delicacyIntroduction = new String(request.getParameter("delicacyIntroduction").getBytes("ISO-8859-1"),"UTF-8");
		String delicacyImage = new String(request.getParameter("delicacyImage").getBytes("ISO-8859-1"),"UTF-8");
		
		Delicacy delicacy = new Delicacy();
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		
		
		delicacy.setId(Integer.parseInt(delicacyId));
		delicacy.setDelicacyName(delicacyName);
		delicacy.setDelicacyPrice(decimalFormat.format(Double.parseDouble(delicacyPrice)));
		delicacy.setDelicacyIntroduction(delicacyIntroduction);
		delicacy.setDelicacyImage(delicacyImage);
		
		int rows = delicacyDao.updateDelicacy(delicacy);
		if(rows > 0) {
			request.getRequestDispatcher("/jsp/back/delicacy.do?method=query").forward(request, response);
		}else {
			request.getRequestDispatcher("/jsp/bakc/error.jsp").forward(request, response);
		}
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		DelicacyDao delicacyDao = new DelicacyDaoImpl();
		int rows = delicacyDao.deleteDelicacy(id);
		if(rows > 0) {
			request.setAttribute("message", "删除成功");
			request.getRequestDispatcher("/jsp/back/delicacy.do?method=query").forward(request, response);
		}else {
			request.getRequestDispatcher("/jsp/back/error.jsp").forward(request, response);
		}
		
	}

}
