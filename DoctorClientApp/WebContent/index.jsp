<%@page import="com.Doctor" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Doctor Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Doctors.js"></script>
</head>
<body>
<div class="container">
<div class="row">
<div class="col-6">
	<h1>Doctor Management</h1>
	<br>
	<form id="formItem" name="formItem">
		Doctor Name:
		<input id="docName" name="docName"  type="text"  class="form-control form-control-sm">
		
		<br>Specialization:
		<input id="docSpec" name="docSpec"  type="text"  class="form-control form-control-sm">
	
		<br>Hospital:
		<input id="docHosp" name="docSpec"  type="text"  class="form-control form-control-sm">
	
		<br>Contact No:
		<input id="docCont" name="docCont"  type="text"  class="form-control form-control-sm">
	
		<br>Email:
		<input id="docEmail" name="docEmail" type="text" class="form-control form-control-sm">
	
		<br>Status:
		<input id="docStatus" name="docStatus" type="text" class="form-control form-control-sm">
		
		<br>
		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
	
	
	
	</form>
	
	<div id="alertSuccess" class="alert alert-success"></div>
    <div id="alertError" class="alert alert-danger"></div>
    
    <br>
    <div id="divItemsGrid">
    </div>
    
    
</div>
</div>
</div>
	



</body>
</html>