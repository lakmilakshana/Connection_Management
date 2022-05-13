<%@ page import="com.Connect"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/Connect.js"></script>
<meta charset="ISO-8859-1">
<title>Connection Management</title>
</head>

<body>

	<div class="container">
	
		<div class="row">
			<div class="col-6">
		
			<h1>Connection Management</h1>

			<form id="formItem" name="formItem">
		
				<!-- CONNECTION NAME -->
				Connection Name:
					<input id="connectionName" name="connectionName" type="text" class="form-control form-control-sm"><br>
				
				<!-- CONNECTION TYPE --> 
				Connection Type
					<select id="connectionType" name="connectionType" class="form-control form-control-sm">
						<option value="Ordinary Supply Connection">Ordinary Supply Connection</option>
						<option value="Bulk Supply Connection">Bulk Supply Connection</option>
					</select><br>
					
				<!-- DESCRIPTION -->
				Description:
					<input id="description" name="description" type="text" class="form-control form-control-sm"><br>
				
				<!-- PRICE -->
				Price:
				<input id="price" name="price" type="text" class="form-control form-control-sm"><br>
				
				
				<!-- DATE -->
				Date:
				 	<input id="date" name="date" type="date" class="form-control form-control-sm"><br>
			
				<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
				<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
			
			</form>
	
<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	<br>
		<div id="divItemGrid">
			<%
			Connect ConnectionObj = new Connect(); 
			 out.print(ConnectionObj.readConnection()); 
			%>
		</div>
		</div> 
	</div> 
</div> 
	
</body>
</html>