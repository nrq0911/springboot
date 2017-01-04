<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="currentUser" type="com.example.domain.OperatorDetails" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Home page</title>
    <style type="text/css">
        li{text-decoration: none;}
    </style>
</head>
<body>
<nav role="navigation">
    <ul>
    <#if !currentUser??>
        <li><a href="/login">登录</a></li>
    </#if>
    <#if currentUser??>
        <li>
            <form action="/logout" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit">退出</button>
            </form>
        </li>
        <li><a href="/user/${currentUser.username}">查看自己信息</a></li>
    </#if>
    <#if currentUser?? && currentUser.isInRole("ROLE_ADMIN") >
        <li><a href="/user/create">创建一个用户</a></li>
        <li><a href="/users">查看所有用户</a></li>
    </#if>
    </ul>
</nav>
</body>
</html>