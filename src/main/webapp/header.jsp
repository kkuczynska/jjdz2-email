<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<script type="text/javascript" src="LogoutFB.js">
</script>
<div class="header">
    <div class="col-md-6" name="headerText">
        JBD Email Search Engine
    </div>
    <div class="col-md-3" id="language">
        <a href="?locale=pl">english</a> . <a href="?locale=en">polish</a>
        <button class="btn btn-primary btn-xs" onclick="Logout()">Logout From FB</button>
    </div>
    <div class="col-md-3" id="headerDateTime">
            Today is <%java.text.DateFormat df = new java.text.SimpleDateFormat("dd-MM-yyyy"); %>
            <%= df.format(new java.util.Date())%>
    </div>
</div>

