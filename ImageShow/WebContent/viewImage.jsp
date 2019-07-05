<%@page import="com.util.DBConnection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Information with Image</title>
</head>
<body>
<%
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
%>
	<h1 align="center">All Information with Image</h1>
	<h1 align="center"><a href="index.jsp">Go to Insert Page</a></h1>
	<table align="center" border="1">
		<tr>
			<td align="center">Picture ID</td>
			<td align="center">Name</td>
			<td align="center">Email</td>
			<td align="center">Picture</td>
		</tr>
		<%
			conn = DBConnection.createConnection();
			String sql = "select * from tbl_student";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()){
				//pid = rs.getString("pictureId");
		%>
			<tr>
			<td align="center"><%=rs.getString("pictureId") %></td>
			<td align="center"><%=rs.getString("name") %></td>
			<td align="center"><%=rs.getString("email") %></td>
			<td align="center"><img src="<%=rs.getString("picture")%>" height="200px" width="180px"></td>
		</tr>
		<%} %>
	</table>
</body>
</html>