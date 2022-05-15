<%@ page import="com.Connect"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<!-- <link rel="stylesheet" href="Views/bootstrap.min.css"> -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/Connect.js"></script>
<meta charset="ISO-8859-1">
<title>Connection Management</title>
</head>

<body onload="JavaScript:AutoRefresh(5000);">

	<div class="container">

		<div class="row">
			<div class="col-6">

				<h1>Connection Management</h1>
				<br>

				<form id="formItem" name="formItem">

					<!-- CONNECTION NAME -->
					<div class="form-floating mb-3">
						<input id="connectionName" name="connectionName" type="text"
							class="form-control"><br> <label for="floatingInput">Connection
							Name</label>
					</div>

					<!-- CONNECTION TYPE -->
					<div class="form-floating mb-3">
						<select id="connectionType" name="connectionType"
							class="form-select">
							<option value="Ordinary Supply Connection">Ordinary
								Supply Connection</option>
							<option value="Bulk Supply Connection">Bulk Supply
								Connection</option>
						</select><br> <label for="floatingInput">Connection Type</label>
					</div>
					<!-- DESCRIPTION -->
					<div class="form-floating mb-3">
						<input id="description" name="description" type="text"
							class="form-control"><br> <label for="floatingInput">Description</label>
					</div>

					<div class="form-floating mb-3">
						<!-- PRICE -->
						<input id="price" name="price" type="text" class="form-control"><br>
						<label for="floatingInput">Price</label>
					</div>

					<div class="form-floating mb-3">
						<!-- DATE -->
						<input id="date" name="date" type="date" class="form-control"><br>
						<label for="floatingInput">Date</label>
					</div>

					<div class="col-12">
						<input id="btnSave" name="btnSave" type="button" value="Save"
							class="btn btn-primary"> <input type="hidden"
							id="hidItemIDSave" name="hidItemIDSave" value="">
					</div>
				</form>

				<div class="container">
					<div class="row">
						<div s>
							<h1 style="text-align: center">Connection Management</h1>
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
			</div>
		</div>
	</div>

</body>
</html>