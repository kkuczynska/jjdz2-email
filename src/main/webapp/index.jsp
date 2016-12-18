<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>JBD Email Search Engine</title>
</head>
<body>
<h1>Welcome to the world of JBD Email Search Engine!</h1>
<h3>
    This search engine is here to help you search email messages in your email files or directory.
    <br>
    Prepare your <i>.eml</i> or <i>.mbox</i> file and select one of below options and find what you are looking for.
</h3>
<br><br><br>
<div>
    If you already know what you are looking for you can just start searching...<br>
    <form action="emails.jsp">
        <input type="submit" value="Search emails" name="searchEmails">
    </form>
    <br><br>
    ... or you can ask for help with keywords you might need.<br>
    <form action="keywords">
        <input type="submit" value="Keywords finder" name="searchKeywords">
    </form>
</div>
</body>
</html>
