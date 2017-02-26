<#-- @ftlvariable name="user" type="com.example.domain.AdminUser" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>User details</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/home">Home</a></li>
    </ul>
</nav>

<h1>User details</h1>

<p>username: ${user.loginName}</p>

<p>information: ${user.toString()}</p>
</body>
</html>