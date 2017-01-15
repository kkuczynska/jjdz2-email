<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>JBD Email Search Engine</title>
    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <link href="resources/css.css" rel="stylesheet" type="text/css">
    <script src="resources/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
    <jsp:directive.include file="header.jsp"/>
    <div id="container">
        <div class="jumbotron">
            <h2 id="jumbotron">Keywords finder</h2>
            <h3>If you need help with finding keywords, answer questions in below form and we will provide you with some ideas!</h3>
        </div>

        <div class="searchKeywords">
            <div class="col-md-4">
                <form method="POST" action="keywords">
                    <p>
                        Are you looking for an urgent email?
                        <label><input type="radio" name="q0" value="yes" ${checkedq0yes}>Yes</label>
                        <label><input type="radio" name="q0" value="no" ${checkedq0no}>No</label><br/>
                    </p>
                    <p>
                    Are you looking for a business email
                        <label><input type="radio" name="q1" value="yes" ${checkedq1yes}>Yes</label>
                        <label><input type="radio" name="q1" value="no" ${checkedq1no}>No</label><br/>
                    </p>
                    <p>
                        Are you looking for a family related email?
                        <label><input type="radio" name="q2" value="yes" ${checkedq2yes}>Yes</label>
                        <label><input type="radio" name="q2" value="no" ${checkedq2no}>No</label><br/>
                    </p>
                    <p>
                        Are you looking for an email about a meeting?
                        <label><input type="radio" name="q3" value="yes" ${checkedq3yes}>Yes</label>
                        <label><input type="radio" name="q3" value="no" ${checkedq3no}>No</label><br/>
                    </p>
                    <input class="btn btn-warning" type="submit" value="Search Keywords">
                </form>

                <div class="backToEmails">
                    If you already know what you are looking for you can just start searching...<br>
                    <form action="emails.jsp">
                        <input class="btn btn-warning" type="submit" value="Go to Email Search" name="searchEmails">
                    </form>
                </div>
            </div>

            <div class="col-md-8">
                <span class="keywordsMsg">${keywordsMsg}</span> <br/>
                <div class="keywordsList">
                    <ol>
                        <c:forEach items="${keywordsList}" var="keyword">
                            <li> ${keyword} </li>
                        </c:forEach>
                    </ol>
                </div>
            </div>
        </div><br>



    </div>
    <jsp:directive.include file="footer.jsp"/>
</body>
</html>