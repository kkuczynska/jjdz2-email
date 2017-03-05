<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages" var="msg"/>
<html>
<head>
    <title>JBD Email Search Engine</title>
    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <link href="resources/css.css" rel="stylesheet" type="text/css">
    <script src="resources/bootstrap/js/bootstrap.min.js"></script>
</head>
<body class="body">
<jsp:directive.include file="header.jsp"/>
<div id="container">
    <script type="text/javascript" src="App/LogoutFB.js">
    </script>
    <div class="jumbotron">
        <h2 id="jumbotron"><fmt:message bundle="${msg}" key="Weirdcutemailstitle"/></h2>
        <h3><fmt:message bundle="${msg}" key="passyouremail"/></h3>
    </div>
    <br>
    <div class="col-md-12">
        <h4>Wybierz opcję</h4>
        <c:forEach items="${displayMails}" var="mails">
            <li> ${mails.from} || ${mails.data} || ${mails.subject}</li>
        </c:forEach>
    </div>
    <div class="col-md-12">
        <form method="get" action="weirdcutemails" enctype="multipart/form-data">
            <input type="hidden" name="type" value="fivedays" />
            <input class="btn btn-warning" type="submit"
                   value="<fmt:message bundle="${msg}" key="view1" />" name="weirdcutemails">
            <br>
            <br>
        </form>
    </div>
    <div class="col-md-12">
        <form method="get" action="weirdcutemails" enctype="multipart/form-data">
            <input type="hidden" name="type" value="rudewords" />
            <input class="btn btn-warning" type="submit"
                   value="<fmt:message bundle="${msg}" key="view2" />" name="weirdcutemails">
        </form>
    </div>
    <br>
    <br>
</div>


<%--List of users:--%>
<%--<br />--%>
<%--<table>--%>
<%--<c:forEach items="${userList}" var="user">--%>
<%--<tr>--%>
<%--<td>${user.userName}</user>--%>
<%--</tr>--%>
<%--</c:forEach>--%>
<%--</table>--%>


<jsp:directive.include file="footer.jsp"/>
</body>
</html>