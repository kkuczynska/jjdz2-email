<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>JBD Email Search Engine</title>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css"
                integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<h2>Keywords finder</h2>
<h3>If you need help with finding keywords, answer questions in below form and we will provide you with some ideas!</h3>
<div>
    <form method="GET" action="keywords">
        <ul>
            <c:forEach items="${questions}" var="question">
                ${question.value}
                <label><input type="radio" name="${question.key}" value="yes"/>Yes</label>
                <label><input type="radio" name="${question.key}" value="no"/>No</label> <br/>
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