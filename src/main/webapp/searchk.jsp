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

</body>
</html>
