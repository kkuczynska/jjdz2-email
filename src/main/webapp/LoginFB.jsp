<%@page import="com.jbd.authorization.FBConnection" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages" var="msg"/>
<%
    FBConnection fbConnection = new FBConnection();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Java Facebook Login</title>
    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <link href="resources/css.css" rel="stylesheet" type="text/css">
    <script src="js/bootstrap.min.js"></script>
</head>
<body style="text-align: center;">
<jsp:directive.include file="header.jsp"/>

<div id="container">
    <div class="jumbotron">
        <h1><fmt:message bundle="${msg}" key="title"/></h1>
        <br/><br/>
        <p><fmt:message bundle="${msg}" key="searchEngine"/>
            <br>
            <fmt:message bundle="${msg}" key="prepare"/><i>.eml</i> or <i>.mbox</i> <fmt:message bundle="${msg}"
                                                                                                 key="searchEngineContinue"/>
        </p>
    </div>
    <div class="underJumbotron">
        <h1>You are not Logged in ! Please use Your FB account! :)</h1>
        <a href="<%=fbConnection.getFBAuthUrl()%>"> <img
                style="margin-top: 38px; left: 10px; height: 72px; width: 240px"
                src="http://www.adweek.com/socialtimes/wp-content/uploads/sites/2/2013/10/FBloginbutton.png"/>
        </a>
    </div>


    <jsp:directive.include file="footer.jsp"/>
</div>
</body>
</html>