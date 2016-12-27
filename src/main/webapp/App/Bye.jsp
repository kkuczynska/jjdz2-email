<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.jbd.Authorization.FBConnection"%>
<%
    FBConnection fbConnection = new FBConnection();
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>
    Żegnaj!
</h1>
<div>
    <h2>Chcesz się zalogować ?</h2>
    <a href="<%=fbConnection.getFBAuthUrl()%>">Facebook</a>
</div>


</body>
</html>
