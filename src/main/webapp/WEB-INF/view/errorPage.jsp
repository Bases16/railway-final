<%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 16.12.2020
  Time: 1:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <%
        String data = (String) request.getAttribute("errorMessage");
        out.write(data);
//                out.print(data);
    %>

</body>
</html>
