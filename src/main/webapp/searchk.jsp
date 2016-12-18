<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>JBD Email Search Engine</title>
</head>
<body>
<h2>Keywords finder</h2>
<h3>If you need help with finding keywords, answer questions in below form and we will provide you with some ideas!</h3>

<div>
    <form method="get" action="form">
        <ul>
            <c:forEach items="${questions}" var="question">
                <li> ${question}
            </c:forEach>
        </ul>
    </form>
</div>


<div>
    <form method="post" action="searchk">
        urgent <label><input type="checkbox" name="keywordsForm1" value="yes">Yes</label>
               <label><input type="checkbox" name="keywordsForm1" value="no">No</label><br />
        business <label><input type="checkbox" name="keywordsForm2" value="yes">Yes</label>
                 <label><input type="checkbox" name="keywordsForm2" value="no">No</label><br />
        family <label><input type="checkbox" name="keywordsForm3" value="yes">Yes</label>
               <label><input type="checkbox" name="keywordsForm3" value="no">No</label><br />
        meeting <label><input type="checkbox" name="keywordsForm4" value="yes">Yes</label>
                <label><input type="checkbox" name="keywordsForm4" value="no">No</label><br />
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
<div>
     If you already know what you are looking for you can just start searching...<br>
     <form action="searche.jsp">
        <input type="submit" value="Go to Email Search" name="searchEmails">
     </form>
</body>
</html>
