<%@page import="com.jbd.Authorization.FBConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    FBConnection fbConnection = new FBConnection();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Java Facebook Login</title>
</head>
<body style="text-align: center; margin: 0 auto;">
<div style="height: 12px; width: 20px;">
    <a href="<%=fbConnection.getFBAuthUrl()%>"> <img
            style="margin-top: 38px; left: 10px; height: 72px; width: 240px" src="http://www.adweek.com/socialtimes/wp-content/uploads/sites/2/2013/10/FBloginbutton.png" />
    </a>


</div>
</body>
</html>