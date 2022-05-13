/**
 * 
 */

$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateEmployeeForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "EmployeeServlet",
 type : type,
 data : $("#formEmployee").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
  location.reload(true);
 onEmployeeSaveComplete(response.responseText, status);

 }
 }); 
});

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidIDSave").val($(this).data("empID"));
 $("#empName").val($(this).closest("tr").find('td:eq(0)').text());
 $("#empSalary").val($(this).closest("tr").find('td:eq(1)').text());
 $("#empGender").val($(this).closest("tr").find('td:eq(2)').text());
 $("#empContact").val($(this).closest("tr").find('td:eq(3)').text());
 $("#empDep").val($(this).closest("tr").find('td:eq(4)').text());
 $("#empJoinDate").val($(this).closest("tr").find('td:eq(5)').text());
});

$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "EmployeeServlet",
 type : "DELETE",
 data : "empID=" + $(this).data("id"),
 dataType : "text",
 complete : function(response, status)
 {

  location.reload(true);
 onEmployeeDeleteComplete(response.responseText, status);

 }
 });
});

// CLIENT-MODEL================================================================
function validateEmployeeForm()
{
// Employee name
if ($("#empName").val().trim() == "")
 {
 return "Insert Employee Name.";
 }
// Salary
if ($("#empSalary").val().trim() == "")
 {
 return "Insert Employee Salary.";
 } 
// is numerical value
var tmpSal = $("#empSalary").val().trim();
if (!$.isNumeric(tmpSal))
 {
 return "Insert a numerical value for Salary.";
 }
// Gender-------------------------------
if ($("#empGender").val().trim() == "")
 {
 return "Insert Employee Gender.";
 }
// Contact
if ($("#empContact").val().trim() == "")
 {
 return "Insert Employee Contact.";
 } 
// is numerical value
var tmpCon = $("#empContact").val().trim();
if (!$.isNumeric(tmpCon))
 {
 return "Insert a numerical value for Contact.";
 }
 // Department------------------------
if ($("#empDep").val().trim() == "")
 {
 return "Insert Employee Department.";
 }
 // Join Date------------------------
if ($("#empJoinDate").val().trim() == "")
 {
 return "Insert Employee Joined Date.";
 }
return true;
}

function onEmployeeSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divEmployeeGrid").html(resultSet.data);

 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 

 $("#hidIDSave").val("");
 $("#formEmployee")[0].reset();
}

function onEmployeeDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divEmployeeGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}   
 