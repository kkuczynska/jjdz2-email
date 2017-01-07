<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>yolo</title>
</head>
<body>
<script type="text/javascript" src="LogoutFB.js">
</script>
<div>

    <p>Welcome! ${sessionData.username} </p>
    <button onclick="Logout()">Logout From FB</button>
</div>


<c:if test="${sessionData.privilege=='Admin'}">
<h2>Content Only for Administrators!<\h2></c:if>

        <p>form:</p>
        <div>
            <form method="post" action="sendData">
                email: <input type="text" name="email"> <br/>
                date: <input type="text" name="date"> <br/>
                keywords: <input type="text" name="keywords"> <br/>
                <input type="submit" value="Send">
            </form>
        </div>
        <div>
            <ul>
                <c:forEach items="${results}" var="email">
                <li> ${email.subject}
                    </c:forEach>
            </ul>
        </div>
</body>
</html>
