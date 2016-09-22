<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>登录</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	String isFirst = request.getParameter("flag");
	if(isFirst!=null){
		String code = request.getParameter("code");
		String password = request.getParameter("password");
		ResultSet resultSet = null;
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiring?characterEncoding=UTF8","root","root");
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT name FROM talent WHERE code=? AND password=?");
		preparedStatement.setString(1,code);
		preparedStatement.setString(2, password);
		resultSet = preparedStatement.executeQuery();
		if(resultSet.next()){
			request.setAttribute("name",resultSet.getString("name"));
			request.getRequestDispatcher("index.jsp").forward(request,response);
		}else{
			request.getRequestDispatcher("login.jsp").forward(request,response);
		}
		
	}
%>
		<form action="login.jsp?flag=true" method="post" >
			<input type="text" name="code">
			<input type="password" name="password">
			<input type="submit" value="登录">
		</form>
</body>
</html>