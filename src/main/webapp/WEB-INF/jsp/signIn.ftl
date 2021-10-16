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
        <form name="auth" action="/signIn" method="POST">
            <label>Login: </label>
            <input type="text" placeholder="Enter your email" name="login" required>
            <label>Password: </label>
            <input type="password" name="password" placeholder="Enter your password" required>
            <button type="submit">signIn</button>
        </form>
    </div>
</div>
</body>

</html>