<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script type="text/javascript" src="LogoutFB.js">
</script>
<h1>Welcome</h1>
<p>Add new admins or edit existing one</p>
<p>Welcome! ${sessionData.username} </p>
<button onclick="Logout()">Logout From FB</button>

</body>
<div>
    <form method="post" action="search">
        <button onclick="location.href=search">SearchButton</button>
    </form>
    <p>Add new admin or edit exisitng one </p>
    <form method="post" action="update">
        <ul>
            <c:forEach items="${userList}" var="user">
                <li> ${user.id} . ${user.username} - ${user.privilege}<input type="checkbox" name="isPrivileged"
                                                                             value=${user.id}></li>
            </c:forEach>
            <input type="submit" value="Update">
        </ul
    </form>
</div>
</html>
