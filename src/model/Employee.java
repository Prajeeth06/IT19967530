package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Employee {
	
	    //A common method to connect to the DB
		private Connection connect()
		{
			Connection con = null;
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employeedb", "root", "");
			}
			catch (Exception e)
				{
					e.printStackTrace();
				}
				return con;
			}
			
			public String insertEmployee(String name, String salary, String gender, String contact, String department, String joinDate)
			{
			   String output = "";
				try
				{
					Connection con = connect();
					if (con == null)
				{				
					return "Error while connecting to the database for inserting"; 
				}
			
				// create a prepared statement
				String query = " insert into employee(`empID`,`empName`,`empSalary`,`empGender`,`empContact`,`empDep`,`empJoinDate`)"+ " values (?, ?, ?, ?, ?, ?, ?)";
			    PreparedStatement preparedStmt = con.prepareStatement(query);
			       
			    // binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, name);				
				preparedStmt.setDouble(3, Double.parseDouble(salary));
				preparedStmt.setString(4, gender);
				preparedStmt.setString(5, contact);
				preparedStmt.setString(6, department);
				preparedStmt.setString(7, joinDate);
			
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Inserted successfully";
				}
				catch (Exception e)
				{
					output = "Error while inserting employee";
					System.err.println(e.getMessage());
				}
				return output;
			}
			
			public String readEmployee()
			{
				String output = "";
				  try
				  {
					  Connection con = connect();
					  if (con == null)
				  {	
					  return "Error while connecting to the database for reading"; 
			      }
					  
				  // Prepare the html table to be displayed
				  output = "<table id='emp'><tr><th>Employee Name</th><th>Salary</th>" +
				           "<th>Gender</th>" +
						   "<th>Contact</th>" +
						   "<th>Department</th>" +
						   "<th>Join Date</th>" +
						   "<th>Update</th><th>Remove</th></tr>";
				  String query = "select * from employee";
				  Statement stmt = con.createStatement();
				  ResultSet rs = stmt.executeQuery(query);
				
				  // iterate through the rows in the result set
				  
				  while (rs.next())
				  {
						String empID = Integer.toString(rs.getInt("empID"));
						String empName = rs.getString("empName");					
						String empSalary = Double.toString(rs.getDouble("empSalary"));
						String empGender = rs.getString("empGender");
						String empContact = rs.getString("empContact");
						String empDep = rs.getString("empDep");
						String empJoinDate = rs.getString("empJoinDate");
						
						// Add into the html table
						
						output += "<tr><td>" + empName + "</td>";
						output += "<td>" + empSalary + "</td>";
						output += "<td>" + empGender + "</td>";
						output += "<td>" + empContact + "</td>";
						output += "<td>" + empDep + "</td>";
						output += "<td>" + empJoinDate + "</td>";
						// buttons
						 output += "<td align='center'><input name='btnUpdate' type='button' value='Update' "
								 + "class='btnUpdate btn btn-secondary' data-id='" + empID + "'></td>"
								 + "<td align='center'><input name='btnRemove' type='button' value='Remove' "
								 + "class='btnRemove btn btn-danger' data-id='" + empID + "'></td></tr>";
				  }
						con.close();
						// Complete the html table
						output += "</table><br>";
				  }
				  catch (Exception e)
				  {
						output = "Error while reading employee";
						System.err.println(e.getMessage());
				  }
				return output;
			}
			
			public String updateEmployee(String ID, String name, String salary, String gender, String contact, String department, String joinDate)
			{
				String output = "";
				  try
				  {
					  Connection con = connect();
					  if (con == null)
				  {
					  return "Error while connecting to the database for updating"; 
				  }
					  
					  // create a prepared statement
					  String query = "UPDATE employee SET empName=?,empSalary=?,empGender=?,empContact=?,empDep=?,empJoinDate=? WHERE empID=?";
					  PreparedStatement preparedStmt = con.prepareStatement(query);
					  
					  // binding values
					  preparedStmt.setString(1, name);					
					  preparedStmt.setDouble(2, Double.parseDouble(salary));
					  preparedStmt.setString(3, gender);
					  preparedStmt.setString(4, contact);
					  preparedStmt.setString(5, department);
					  preparedStmt.setString(6, joinDate);
					  preparedStmt.setInt(7, Integer.parseInt(ID));
					 
					  // execute the statement
					  preparedStmt.execute();
					  con.close();
					  output = "Updated successfully";
				  }
				  catch (Exception e)
				  {
					  output = "Error while updating employee";
					  System.err.println(e.getMessage());
				  }
				  return output;
			}
					
			public String deleteEmployee(String empID)
			{
				String output = "";
				  try
				  {
					  	Connection con = connect();
					  	if (con == null)
				  {
						return "Error while connecting to the database for deleting"; 
				  }
					  	
					  	// create a prepared statement
					  	String query = "delete from employee where empID=?";
					  	PreparedStatement preparedStmt = con.prepareStatement(query);
					
					  	// binding values
					  	preparedStmt.setInt(1, Integer.parseInt(empID));
					
					  	// execute the statement
					  	preparedStmt.execute();
					  	con.close();
					  	output = "Deleted successfully";
				  }
				  catch (Exception e)
				  {
					  	output = "Error while deleting employee";
					  	System.err.println(e.getMessage());
				  }
				  return output;
			}

}
