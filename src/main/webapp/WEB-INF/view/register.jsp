
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <title>Login Customer</title>
</head>
<body>
<div class="container">
    <form method="post" action="/auth/register">
        <h2>Login</h2>
        <p>
            <label for="username">Username</label>
            <input type="text" id="username" name="username" placeholder="Username" required>
        </p>
        <p>
            <label for="password">Password</label>
            <input type="password" id="password" name="password" placeholder="Password" required>
        </p>
        <button type="submit">Sign in</button>
    </form>
</div>
</body>
</html>
