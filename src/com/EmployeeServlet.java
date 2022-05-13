package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.Employee;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Employee empObj=new Employee();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Output= empObj.readEmployee();
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().write(Output);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String output = empObj.insertEmployee(request.getParameter("empName"),
			request.getParameter("empSalary"),
			request.getParameter("empGender"),
			request.getParameter("empContact"),
			request.getParameter("empDep"),
			request.getParameter("empJoinDate"));
			response.getWriter().write(output);
}

/**
 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
 */
protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 Map paras = getParasMap(request);
	 String output = empObj.updateEmployee(paras.get("hidIDSave").toString(),
	 paras.get("empName").toString(),
	 paras.get("empSalary").toString(),
	 paras.get("empGender").toString(),
	 paras.get("empContact").toString(),
	 paras.get("empDep").toString(),
	 paras.get("empJoinDate").toString());
	 response.getWriter().write(output);
}

/**
 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
 */
protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 Map paras = getParasMap(request);
	 String output = empObj.deleteEmployee(paras.get("empID").toString());
	 response.getWriter().write(output);
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

}
