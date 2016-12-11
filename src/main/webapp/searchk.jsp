<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Keywords finder</h1>
<div>
    <form method="post" action="searchk">
        urgent <input type="text" name="answer1"> <br />
        business <input type="text" name="answer2"> <br />
        family <input type="text" name="answer3"> <br />
        meeting <input type="text" name="answer4"> <br />

        <input type="submit" value="Search Keywords">
    </form>
</div>

<div>
    <span>Keywords found: </span> <br/>
    <ol>
        <c:forEach items="${keywordsList}" var="keyword">
            <li> ${keyword}
        </c:forEach>
    </ol>
</div>

</body>
</html>
