package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.Doctor;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class DoctorsAPI
 */

/**
 * Setting up the DC-Bus
 */
@WebServlet("/DoctorsAPI")
public class DoctorsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Doctor doctorObj = new Doctor();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorsAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//not used
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			
		String output = doctorObj.insertDoctor(
				request.getParameter("docName"),
				request.getParameter("docSpec"),
				request.getParameter("docHosp"),
				request.getParameter("docContact"),
				request.getParameter("docEmail"),
				request.getParameter("docStat"));
		
		response.getWriter().write(output);
		

	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Map paras = getParasMap(request);
		String output = doctorObj.updateDoctor(paras.get("hidDocIDSave").toString(),
											   paras.get("docName").toString().replace('+', ' '),
											   paras.get("docSpec").toString().replace('+', ' '),
											   paras.get("docHosp").toString().replace('+', ' '),
											   paras.get("docContact").toString(),
											   paras.get("docEmail").toString().replace("%40", "@"),
											   paras.get("docStat").toString());
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		String output = doctorObj.deleteDoctor(paras.get("docID").toString());
		
		response.getWriter().write(output);
	}
	
	
	
	// Convert request parameters to a Map
	
	private static Map getParasMap(HttpServletRequest request){
		
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

}
