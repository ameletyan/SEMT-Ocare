<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>File Upload</title>
    <link rel="stylesheet" href="normalize.css">
	<link rel="stylesheet" href="style.css">
	<link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
</head>
<body>
    <font color="#FFFFFF" size="7"><center><h1>Welcome, Doctor</h1></center></font>
	<font color="#FFFFFF" size="5"><center><h3>Use the buttons below to download documents and upload updated versions</h3></center></font>
    <center> 
        <font color="#FFFFFF" size="3"><h3>Patient 1:</h3></font>
    	<form method="get" action="Servlet1" enctype="multipart/form-data">
    		<input type="submit" value="Download" />
    	</form>
    	<form method="post" action="Servlet1" enctype="multipart/form-data">
    		<input type="file" name="uploadFile" />
    		<input type="submit" value="Upload" />
    	</form>
    </center>
    <br>
    <center>
        <font color="#FFFFFF" size="3"><h3>Patient 2:</h3></font>
    	<form method="get" action="Servlet2" enctype="multipart/form-data">
    		<input type="submit" value="Download" />
    	</form>
    	<form method="post" action="Servlet2" enctype="multipart/form-data">
    		<input type="file" name="uploadFile" />
    		<input type="submit" value="Upload" />
    	</form>
    </center>
    <br>
     <center>
        <font color="#FFFFFF" size="3"><h3>Patient 3:</h3></font>
    	<form method="get" action="Servlet3" enctype="multipart/form-data">
    		<input type="submit" value="Download" />
    	</form>
    	<form method="post" action="Servlet3" enctype="multipart/form-data">
    		<input type="file" name="uploadFile" />
    		<input type="submit" value="Upload" />
    	</form>
    </center>
</body>
</html>