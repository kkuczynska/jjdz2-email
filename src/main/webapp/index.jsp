<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>JBD Email Search Engine</title>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css"
            integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="resources/css.css" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
    <div class="jumbotron">
        <h1>Welcome to the world of JBD Email Search Engine!</h1>
        <p>This search engine is here to help you search email messages in your email files or directory.
        <br>
        Prepare your <i>.eml</i> or <i>.mbox</i> file and select one of below options in order to find what you are looking for.
        </p>
    </div>
<div class="row">
<div class="col-xs-6 col-xs-6">
    If you already know what you are looking for you can just start searching...<br>
    <p><form action="emails.jsp">
        <input class="btn btn-primary" type="submit" value="Search emails" name="searchEmails">
    </form></p>
    </div>
<div class="col-xs-6 col-xs-6">
    ... or you can ask for help with keywords you might need.<br>
    <p><form action="keywords">
        <input class="btn btn-primary" type="submit" value="Search keywords" name="searchKeywords">
    </form></p>
</div></div>
</body>
</html>
