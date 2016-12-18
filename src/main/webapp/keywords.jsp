<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>JBD Email Search Engine</title>
</head>
<body>
<h2>Keywords finder</h2>
<h3>If you need help with finding keywords, answer questions in below form and we will provide you with some ideas!</h3>
<div>
    <form method="GET" action="keywords">
        <p>Keywordsy:</p>
        <ul>
            <c:forEach items="${questions}" var="question">
                <li> ${question} <label><input type="radio" name="keywordsForm" value="${question}">Yes</label>
                    <label><input type="radio" name="keywordsForm" value="${question}">No</label>
                </li>
            </c:forEach>
        </ul>
    </form>
</div>
<div>
    <form method="post" action="keywords">
        <input type="submit" value="Search Keywords">
    </form>
</div>
<div>
    <span>Keywords found: </span> <br/>
    <ol>
        <c:forEach items="${keywordsList}" var="keyword">
        <li> ${keyword} </li>
            </c:forEach>
    </ol>
</div>
<div>
    If you already know what you are looking for you can just start searching...<br>
    <form action="emails.jsp">
        <input type="submit" value="Go to Email Search" name="searchEmails">
    </form>
</div>
</body>
</html>