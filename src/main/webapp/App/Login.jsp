
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>
    Witaj! Proszę się zalogować
</h1>

<form action="/jbdee/LoginServlet" method="POST">
    <div>Username: <input type="text" name="username"></div>
    <div>Password: <input type="text" name="password"> </div>
    <input type="submit" value="Log in" >
</form>
<a href="/jbdee/LoginServlet">Facebook</a>

</body>
</html>
