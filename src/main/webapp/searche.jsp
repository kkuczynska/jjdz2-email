<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>JBD Email Search Engine</title>
</head>
<body>
    <h1>Here you can specify the search criteria and upload your email file</h1>
    <div>
        <form method="post" action="searche">
            Email file path: <span id="required">*</span><input type="text" name="emailPath"><br />
            <br />
            Please provide email address in correct format (xxxxx@xxxxxxx.xxx). <br />
            <input type="text" name="email"> <br />
            <span id="multipleEmailsInformation">
                * If you want to search for more than one email address, separate them with commas. <br />
            </span>
            Start date: <input type="text" name="startDate"> (YYYY-MM-DD) <br />
            End date: <input type="text" name="endDate"> (YYYY-MM-DD) <br />
            Keywords separated with comma: <input type="text" name="keywords"> <br />
            <br /><br />
            Should we also display phone numbers contained in the emails?<br />
            <label><input type="checkbox" name="yes">Yes</label><br />
            <label><input type="checkbox" name="no">No</label><br />
            <br><br>
            <input type="submit" value="Search emails">
        </form>
        <form name="goBackToKeywordsFinder" action="searchk.jsp">
            <input type="submit" value="Go to Keywords Finder">
        </form>
    </div>

    <div>
        <span>Emails matching your criteria: </span> <br/>
        <ol>
            <c:forEach items="${finalEmailSet}" var="email">
                <li> ${email.from} || ${email.subject} || ${email.data}
            </c:forEach>
        </ol>

    </div>
</body>
</html>
