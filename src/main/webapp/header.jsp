<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<fmt:setBundle basename="messages" var="msg"/>
<html>
    <head>
        <script src="resources/jquery/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="LogoutFB.js"></script>
        <script type="text/javascript" src="count.js"></script>
        <script src="https://apis.google.com/js/client.js?onload=checkAuth"></script>
    </head>
<body class="body">
    <div class="header">
        <div class="col-md-6" name="headerText">
            <fmt:message bundle="${msg}" key="jbd"/>
        </div>
        <div class="col-md-3" id="language">
            <a href="?locale=pl" class="language"><fmt:message bundle="${msg}" key="english"/></a>
                <span class="glyphicon glyphicon-globe"></span>
                <a href="?locale=en" class="language"><fmt:message bundle="${msg}" key="polish"/></a>
            <button class="btn btn-primary btn-xs" onclick="Logout()">
                <fmt:message bundle="${msg}" key="logout"/></button>
        </div>
        <div class="col-md-3" id="gmailUnreadMessages">
            <fmt:message bundle="${msg}" key="youGot"/> <span id="count"></span>
                <fmt:message bundle="${msg}" key="unreadMessages"/>
        </div>
    </div>
</body>
</html>