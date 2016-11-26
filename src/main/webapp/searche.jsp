<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>JBD Email Search Engine</title>
</head>
<body>
    <h1>Here you can specify the search criteria and upload your email file</h1>
    <div>
        <form method="post" action="sendData">
            Provide email address in correct format: <br /><br />
            Email address: <input type="text" name="email"> (xxxxx@xxxxxxx.xxx) <br />
            Start date: <input type="text" name="startDate"> (YYYY-MM-DD) <br />
            End date: <input type="text" name="endDate"> (YYYY-MM-DD) <br />
            Keywords separated with comma: <input type="text" name="keywords"> <br />
            <input type="submit" value="Search emails">
        </form>
        <form name="goBackToKeywordsFinder" action="searchk.jsp">
            <input type="submit" value="Go to Keywords Finder">
        </form>

    </div>
</body>
</html>
