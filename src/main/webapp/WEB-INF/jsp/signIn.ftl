<#import "/spring.ftl" as spring/>

<html>
<head>
    <style>
        <#setting classic_compatible=true>
        <#include "css/style.css">
    </style>
    <title>Аутентификация</title>
</head>
<body>
<#if errorMessage??>
    <div style="color:red;font-style:italic;">
        ${errorMessage}
    </div>
</#if>

<div>
    <div class="form-style-6">
        <h1>Аутентификация</h1>
        <form name="auth" action="/j_spring_security_check" method="POST">
            <label>Login:
                <input type="text" placeholder="Enter your login" name="username">
            </label>
            <label>Password:
                <input type="password" name="password" placeholder="Enter your password">
            </label>
            <label>Remember Me?:
                <input type="checkbox" name="remember-me">
            </label>
            <button type="submit">signIn</button>
        </form>
    </div>
</div>
</body>

</html>