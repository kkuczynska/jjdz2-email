<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<div class="header">
    <div class="col-md-6" name="headerText">
        JBD Email Search Engine
    </div>
    <div class="col-md-6" id="headerDateTime">
            Today is <%java.text.DateFormat df = new java.text.SimpleDateFormat("dd-MM-yyyy"); %>
            <%= df.format(new java.util.Date())%>
    </div>
</div>