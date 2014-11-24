<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>File Upload</title>
</head>
<body>
    <center> 
    	<form method="get" action="Servlet1" enctype="multipart/form-data">
    		<input type="submit" value="Download 1" />
    	</form>
    	<form method="post" action="Servlet1" enctype="multipart/form-data">
    		<input type="file" name="uploadFile" />
    		<input type="submit" value="Upload" />
    	</form>
    </center>
</body>
</html>