<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert Image and View in JSP, Servlet</title>
</head>
<body>
<h1 align="center">Please fillup all the information---</h1>
<h1 align="center"><a href="viewImage.jsp">View Data</a></h1>
<form action="imageServ" method="post" enctype="multipart/form-data">
		<table align="center" border="1">
			<tr>
				<td>PictureId: </td>
				<td><input type="text" name="pictureId" placeholder="Enter a picture ID--"></td>
			</tr>
		<tr>
				<td>Your Name: </td>
				<td><input type="text" name="name" placeholder="Enter your Name---"></td>
			</tr>
			<tr>
				<td>Your Email: </td>
				<td><input type="text" name="email" placeholder="Enter your Email---"></td>
			</tr>
			<tr>
				<td>Mobile No.: </td>
				<td><input type="text" name="mobile" placeholder="Enter your Mobile---"></td>
			</tr>
			<tr>
				<td>Select Picture: </td>
				<td><input type="file" name="picture"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit"></td>
			</tr>
		</table>
	</form>
</body>
</html>