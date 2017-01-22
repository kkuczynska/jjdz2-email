<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
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
            <h1>Welcome to the world of <i>JBD Email Search Engine!</i></h1>
            <br /><br />
            <p>This search engine is here to help you search email messages in your email files.
                <br>
                Prepare your <i>.eml</i> or <i>.mbox</i> file and select one of below options in order to find what you are looking for.
            </p>
        </div>
            <div class="underJumbotron">
                <div class="row">
                    <div class="col-md-6" id="searchEmailsSection">
                        If you already know what you are looking for you can start searching...<br>
                        <p><form action="emails.jsp">
                            <input class="btn btn-warning" type="submit" value="Search emails" name="searchEmails">
                        </form></p>
                    </div>
                    <div class="col-md-6">
                        ... or you can get help with defining keywords for your search query.<br>
                        <p><form action="keywords">
                            <input class="btn btn-warning" type="submit" value="Search keywords" name="searchKeywords">
                        </form></p>
                    </div>
                </div>
            </div>
        <jsp:directive.include file="footer.jsp"/>
    </div>
</body>
</html>
