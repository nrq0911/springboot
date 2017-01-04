<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="error" type="java.util.Optional<String>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Log in</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
    </ul>
</nav>

<h1>登录</h1>

<p>You can use: admin/123456 (无管理员权限)|| aaaa/123456(管理员)</p>

<form role="form" action="/login" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <div>
        <label for="text">用户名:</label>
        <input type="text" name="email" id="email" required autofocus/>
    </div>
    <div>
        <label for="password">密码:</label>
        <input type="password" name="password" id="password" required/>
    </div>
    <div>
        <label for="remember-me">记住我</label>
        <input type="checkbox" name="remember-me" id="remember-me"/>
    </div>
    <button type="submit">登录</button>
    <button type="reset">重置</button>
</form>

<#if error.isPresent()>
<p>The username or password you have entered is invalid, try again.</p>
</#if>
</body>
</html>