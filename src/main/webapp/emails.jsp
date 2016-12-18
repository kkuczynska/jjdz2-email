<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>JBD Email Search Engine</title>
    <script type="text/javascript" src="resources/js.js"></script>
    <link href="resources/css.css" rel="stylesheet" type="text/css">
</head>
<body>
<h2>Here you can specify the search criteria and select email file</h2>
<div>
    <form method="post" action="emails">
        Email file path: <span id="requiredEmailPath">*</span><input type="text" name="emailPath" id="email">
        <span id="emailPathMsg"></span>
        <br/><br/>
        Email address: <input type="text" name="email"> (xxxxx@xxxxxxx.xxx) <br/>
            <span id="multipleEmailsInformation">
                If you want to search for more than one email address, separate them with commas. <br/>
            </span>
        Start date: <input type="text" name="startDate"> (YYYY-MM-DD) <br/>
        End date: <input type="text" name="endDate"> (YYYY-MM-DD) <br/>
        Keywords separated with comma: <input type="text" name="keywords"> <br/>
        <br/><br/>
        Should we also display phone numbers contained in the emails?<br/>
        <label><input type="checkbox" name="phoneNumbers" value="yes">Yes</label><br/>
        <label><input type="checkbox" name="phoneNumbers" value="no">No</label><br/>
        <br><br>
        <input type="submit" value="Search emails" onclick="validateFilePath()">
    </form>
    <form name="goBackToKeywordsFinder" action="keywords.jsp">
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
<div>
    <span>Phone numbers found in your email file: </span> <br/>
    <ol>
        <c:forEach items="${displayNumbers}" var="phone">
        <li> ${phone.key} || ${phone.value}
            </c:forEach>
    </ol>
</div>
</body>
</html>
