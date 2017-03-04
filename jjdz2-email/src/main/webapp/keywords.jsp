<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <h2 id="jumbotron"><fmt:message bundle="${msg}" key="keywordsFinder"/></h2>
        <h3><fmt:message bundle="${msg}" key="help"/></h3>
    </div>

    <div class="searchKeywords">
        <div class="col-md-4">
            <form method="POST" action="keywords">
                <p>
                    <fmt:message bundle="${msg}" key="q1"/>
                    <label><input type="radio" name="q0" value="yes" ${checkedq0yes}>Yes</label>
                    <label><input type="radio" name="q0" value="no" ${checkedq0no}>No</label><br/>
                </p>
                <p>
                    <fmt:message bundle="${msg}" key="q2"/>
                    <label><input type="radio" name="q1" value="yes" ${checkedq1yes}>Yes</label>
                    <label><input type="radio" name="q1" value="no" ${checkedq1no}>No</label><br/>
                </p>
                <p>
                    <fmt:message bundle="${msg}" key="q3"/>
                    <label><input type="radio" name="q2" value="yes" ${checkedq2yes}>Yes</label>
                    <label><input type="radio" name="q2" value="no" ${checkedq2no}>No</label><br/>
                </p>
                <p>
                    <fmt:message bundle="${msg}" key="q4"/>
                    <label><input type="radio" name="q3" value="yes" ${checkedq3yes}>Yes</label>
                    <label><input type="radio" name="q3" value="no" ${checkedq3no}>No</label><br/>
                </p>
                <input class="btn btn-warning" type="submit"
                       value="<fmt:message bundle="${msg}" key="searchKeywords"/>">
            </form>

            <div class="backToEmails">
                <fmt:message bundle="${msg}" key="justSearching"/><br>
                <form action="emails.jsp">
                    <input class="btn btn-warning" type="submit" value="<fmt:message bundle="${msg}" key="goTo"/>"
                           name="searchEmails">
                </form>
            </div>
        </div>

        <div class="col-md-8">
            <span class="keywordsMsg">${keywordsMsg}</span> <br/>
            <div class="keywordsList">
                <ol>
                    <c:forEach items="${keywordsList}" var="keyword">
                        <li> ${fn:escapeXml(keyword)} </li>
                    </c:forEach>
                </ol>
            </div>
        </div>
    </div>
    <br>


</div>
<jsp:directive.include file="footer.jsp"/>
</body>
</html>