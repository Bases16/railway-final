
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <title>Login Customer</title>
</head>
<body>
<div>
    <form method="post" action="/auth/login">
        <h2>Login</h2>
        <p>
            <label for="username">Username</label>
            <input type="text" id="username" name="username" class="form-control" placeholder="Username" required>
        </p>
        <p>
            <label for="password">Password</label>
            <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
        </p>
        <button type="submit">Sign in</button>
    </form>
</div>
</body>
</html>
