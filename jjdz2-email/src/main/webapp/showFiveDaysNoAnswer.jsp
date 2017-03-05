<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages" var="msg"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>JBD Email Search Engine</title>
    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <link href="resources/css.css" rel="stylesheet" type="text/css">
    <script src="resources/bootstrap/js/bootstrap.min.js"></script>
    <jsp:directive.include file="header.jsp"/>
</head>
<body>
<div class="col-md-12">
    <form id="emailForm3" method="get" action="showFiveDaysNoAnswer.jsp" enctype="multipart/form-data">
        <c:forEach items="${displayMails}" var="mails">
            <li> ${mails.from} || ${mails.data} || ${mails.subject}</li>
        </c:forEach>
        <input class="btn btn-warning" type="submit"
               value="<fmt:message bundle="${msg}" key="view1" />" name="weirdcutemails">
    </form>
        <br>
        <br>
</div>

<jsp:directive.include file="footer.jsp"/>
</body>
</html>





<%--<form>--%>
    <%--<input class="btn btn-warning" type="submit"--%>
           <%--value="<fmt:message  key="view2" />" name="weirdcutemails">--%>

<%--</form>--%>