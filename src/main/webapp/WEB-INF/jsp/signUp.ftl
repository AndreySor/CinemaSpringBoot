<#import "/spring.ftl" as spring/>

<html>
<head>
    <style>
        <#setting classic_compatible=true>
        <#include "css/style.css">
    </style>
    <title>Регистрация</title>
</head>
<body>
<#if errorMessage??>
    <div style="color:red;font-style:italic;">
        ${errorMessage}
    </div>
</#if>

<div>
    <div class="form-style-6">
        <h1>Регистрация</h1>
        <form name="user" action="/signUp" method="POST">
            <label>Login:
                <input type="text" placeholder="Enter your login" name="login" required>
            </label>
            <label>Password:
                <input type="password" name="password" placeholder="Enter your password" required>
            </label>
            <input type="submit"  value="SignUp"/>
        </form>
    </div>
</div>
</body>

</html>