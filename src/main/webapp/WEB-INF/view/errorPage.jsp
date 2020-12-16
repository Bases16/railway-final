
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <%
        String data = (String) request.getAttribute("errorMessage");
        if (data != null) {
            out.write(data);
        } else out.write("data is null");
    %>

    <br>
    <strong>
        <a href="/">on homepage</a>
    </strong>

</body>
</html>
