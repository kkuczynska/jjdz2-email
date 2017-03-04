<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="messages" var="msg"/>
<html>
<head>
    <title>JBD Email Search Engine - report page</title>
    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <link href="resources/css.css" rel="stylesheet" type="text/css">
    <script src="js/bootstrap.min.js"></script>
</head>
<body class="body">
<jsp:directive.include file="header.jsp"/>

<div id="container">
    <script type="text/javascript" src="App/LogoutFB.js">
    </script>
    <div class="jumbotron">
        <div class="row">
            <div class="col-md-6" id="searchEmailsSection">
                <p>
                <form action="emails.jsp">
                    <input class="btn btn-warning" type="submit"
                           value="<fmt:message bundle="${msg}" key="searchEmails" />" name="searchEmails">
                </form>
                <p>
            </div>
        </div>
    </div>
</div>

<div class="col-md-12">
<form method="get" action="simpleReport">
    <div>
        <input class="btn btn-warning" type="submit"
            value="<fmt:message bundle="${msg}" key="view" />" name="displayReport">
        <br>
        <br>
        <div class="col-md-12">
        <ol>
            <c:forEach items="${displayReport}" var="report">
                <li> ${fn:escapeXml(report.key)} || ${fn:escapeXml(report.value)}
            </c:forEach>
        </ol>
        </div>
    </div>
</form>
</div>

<jsp:directive.include file="footer.jsp"/>
</body>
</html>
