
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Success</title>
</head>
<body>
    <div class="container">
        <h1>Congrats!!! You did it!</h1>
        <form action="/auth/logout" method="POST">
            <button type="submit">Logout</button>
        </form>
    </div>
</body>
</html>
