<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="model.Employee"%>

<%
//Save---------------------------------
if (request.getParameter("empName") != null)
{
	Employee empObj = new Employee();
	String stsMsg = "";
	
//Insert--------------------------
if (request.getParameter("hidIDSave") == "")
{
	stsMsg = empObj.insertEmployee(request.getParameter("empName"),
	request.getParameter("empSalary"),
	request.getParameter("empGender"),
	request.getParameter("empContact"),
	request.getParameter("empDep"),
	request.getParameter("empJoinDate"));
}

else//Update----------------------
{
	stsMsg = empObj.updateEmployee(request.getParameter("hidIDSave"),
	request.getParameter("empName"),
	request.getParameter("empSalary"),
	request.getParameter("empGender"),
	request.getParameter("empContact"),
	request.getParameter("empDep"),
	request.getParameter("empJoinDate"));
}
	session.setAttribute("statusMsg", stsMsg);
}

//Delete-----------------------------
if (request.getParameter("hidIDDelete") != null)
{
	Employee empObj = new Employee();
	String stsMsg = empObj.deleteEmployee(request.getParameter("hidIDDelete"));
	session.setAttribute("statusMsg", stsMsg);
}
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<link rel="stylesheet" href="Views/emp.css">

	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/employee.js"></script>
	<title>Employee Management</title>
</head>
<body>
	<h1>Employee Management</h1>
	<div>
		<form id="formEmployee" name="formEmployee" method="post" action="Employee.jsp">
			Employee Name :
			<input id="empName" name="empName" type="text" placeholder="Enter your name" class="form-control form-control-sm">
			<br> 
			
			Employee Salary :
			<input id="empSalary" name="empSalary" type="text" placeholder="Enter your salary" class="form-control form-control-sm">
			<br> 
			
			Employee Gender :
			<select id="empGender" name="empGender" class="form-control form-control-sm">
    			<option value="male">Male</option>
        		<option value="female">Female</option>
			</select>
			<br> 
		
			Employee Contact :
			<input id="empContact" name="empContact" type="text" placeholder="Enter your mobile number" class="form-control form-control-sm">
			<br>
			
			Employee Department :
			<input id="empDep" name="empDep" type="text" placeholder="Enter your department" class="form-control form-control-sm">
			<br>
		
			Employee Joined Date :
			<input id="empJoinDate" name="empJoinDate" type="date" class="form-control form-control-sm">
			<br><br>
		
			<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
			<input type="hidden" id="hidIDSave" name="hidIDSave" value="">
		</form>
	</div>
	
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

<%
 out.print(session.getAttribute("statusMsg"));
%>

<div id="divEmployeeGrid">
<%
	Employee empObj = new Employee();
	out.print(empObj.readEmployee());
%>
</div>
</body>
</html>