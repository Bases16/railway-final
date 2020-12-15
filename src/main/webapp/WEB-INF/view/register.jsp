
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <title>Login Customer</title>
</head>
<body>
<div>
    <form method="post" action="/auth/register">
        <h2>Register new user</h2>
        <p>
            <label for="email">Email</label>
            <input type="text" id="email" name="username" placeholder="Username" required>
        </p>
        <p>
            <label for="password">Password</label>
            <input type="password" id="password" name="password" placeholder="Password" required>
        </p>
        <button type="submit">submit</button>
    </form>
</div>
</body>
</html>
