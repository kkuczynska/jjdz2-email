<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="messages" var="msg"/>
<html>
<head>
    <title>JBD Email Search Engine</title>
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

        <h1><fmt:message bundle="${msg}" key="title"/></h1>
        <br/><br/>
        <p><fmt:message bundle="${msg}" key="searchEngine"/>
            <br>
            <fmt:message bundle="${msg}" key="prepare"/><i>.eml</i> or <i>.mbox</i> <fmt:message bundle="${msg}"
                                                                                                 key="searchEngineContinue"/>
        </p>
    </div>
    <div class="underJumbotron">
        <div class="row">
            <div class="col-md-6" id="searchEmailsSection">
                <fmt:message bundle="${msg}" key="lookFor"/><br>
                <p>
                <form action="emails.jsp">
                    <input class="btn btn-warning" type="submit"
                           value="<fmt:message bundle="${msg}" key="searchEmails" />" name="searchEmails">
                </form>
                </p>
            </div>
            <div class="col-md-6">
                <fmt:message bundle="${msg}" key="orLookFor"/><br>
                <p>
                <form action="keywords">
                    <input class="btn btn-warning" type="submit"
                           value="<fmt:message bundle="${msg}" key="searchKeywords" />" name="searchKeywords">
                </form>
                </p>
            </div>
            <div class="col-md-6">
                <fmt:message bundle="${msg}" key="manage"/><br>
                <p>
                <form action="App/AdminConsole.jsp">
                    <input class="btn btn-warning" type="submit" value="Admin" key="searchKeywords"
                           name="searchKeywords">
                </form>
                </p>
            </div>
        </div>
    </div>
    <jsp:directive.include file="footer.jsp"/>

</div>
</body>
</html>
