package com;
import model.NoticeItem;
import com.NoticeItemService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class NoticeAPI
 */
@WebServlet("/NoticeAPI")
public class NoticeAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	NoticeItem itemObj = new NoticeItem();
    /**
     * Default constructor. 
     */
    public NoticeAPI() {
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
		// TODO Auto-generated method stub
		String output = itemObj.insertItem(request.getParameter("name"),
				 request.getParameter("feild"),
				request.getParameter("description"),
				request.getParameter("submission_link"),
		        request.getParameter("fundingbody_id"));
				response.getWriter().write(output);
				
				System.out.println("POST METHOD");
	}
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
	{
	 Map<String, String> map = new HashMap<String, String>();
	try
	 {
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	 String queryString = scanner.hasNext() ?
	 scanner.useDelimiter("\\A").next() : "";
	 scanner.close();
	 String[] params = queryString.split("&");
	 for (String param : params)
	 { 
	String[] p = param.split("=");
	 map.put(p[0], p[1]);
	 }
	 }
	catch (Exception e)
	 {
	 }
	return map;
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		
		// TODO Auto-generated method stub
		
		{
			 Map paras = getParasMap(request);

			 String output = itemObj.updateItem(paras.get("hidItemIDSave").toString(),
			 paras.get("name").toString(),
			 paras.get("feild").toString(),
			paras.get("description").toString(),
			paras.get("submission_link").toString());
			response.getWriter().write(output);

		} 
		
		
	

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request);
		System.out.println("%%%%%%%%%%%%%%%%%%%%% "+paras.get("pRequest_id").toString());
		 String output = itemObj.deleteItem(paras.get("pRequest_id").toString());
		response.getWriter().write(output); 
	}

}
