package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Employee;

@Path("/Emp")
public class EmployeeService {
	
	Employee empObj = new Employee();
		
	//GET
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readEmployee()
	{
		return empObj.readEmployee();
	}
	
	//POST
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertEmployee(@FormParam("empName") String empName,
	 @FormParam("empSalary") String empSalary,
	 @FormParam("empGender") String empGender,
	 @FormParam("empContact") String empContact,
	 @FormParam("empDep") String empDep,
	 @FormParam("empJoinDate") String empJoinDate)
	{
		String output = empObj.insertEmployee(empName, empSalary, empGender, empContact, empDep, empJoinDate);
	    return output;
	}
	
	//PUT
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateEmployee(String empData)
	{
		//Convert the input string to a JSON object
	    JsonObject employeeObject = new JsonParser().parse(empData).getAsJsonObject();
	    
	    //Read the values from the JSON object
	    String empID = employeeObject.get("empID").getAsString();
	    String empName = employeeObject.get("empName").getAsString();
	    String empSalary = employeeObject.get("empSalary").getAsString();
	    String empGender = employeeObject.get("empGender").getAsString();
	    String empContact = employeeObject.get("empContact").getAsString();
	    String empDep = employeeObject.get("empDep").getAsString();
	    String empJoinDate = employeeObject.get("empJoinDate").getAsString();
	    String output = empObj.updateEmployee(empID, empName, empSalary, empGender, empContact, empDep, empJoinDate);
	
	    return output;
	}
	
	//DELETE
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteEmployee(String empData)
	{
		//Convert the input string to an XML document
	    Document doc = Jsoup.parse(empData, "", Parser.xmlParser());

	    //Read the value from the element <empID>
	    String empID = doc.select("empID").text();
	    String output = empObj.deleteEmployee(empID);
	
	    return output;
	}
}
