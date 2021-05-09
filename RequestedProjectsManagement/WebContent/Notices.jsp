<%@page import="model.NoticeItem"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>RequestedProject Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/notice.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>ADD New Notices </h1>
<form id="formNotice" name="formNotice">
 Company Name:
 <input id="name" name="name" type="text"
 class="form-control form-control-sm">
 <br> Related Field:
 <input id="field" name="feild" type="text"
 class="form-control form-control-sm">
 <br> Notice Description:
 <input id="description" name="description" type="text"
 class="form-control form-control-sm">
 <br> Submission Link:
 <input id="submission_link" name="submission_link" type="text"
 class="form-control form-control-sm">
 <br>Registration Number:
 <input id="fundingbody_id" name="fundingbody_id" type="text"
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidItemIDSave"
 name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 NoticeItem itemObj = new NoticeItem();
 out.print(itemObj.readItems());
 %>
</div>
</div> </div> </div>
</body>
</html>