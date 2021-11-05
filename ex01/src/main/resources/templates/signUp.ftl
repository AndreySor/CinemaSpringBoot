<#import "/spring.ftl" as spring/>

<html>
<head>
    <style>
        <#setting classic_compatible=true>
        <#include "css/style.css">
    </style>
    <title>Регистрация</title>
    <script type="text/javascript" src="/static/js/util.js"></script>
</head>
<body>
<#if errorMessage??>
    <div style="color:red;font-style:italic;">
        ${errorMessage}
    </div>
</#if>

<div>
    <div class="change-lang">
        <p><a href="/signUp?lang=en" onclick=enCookie()>English</a></p>
        <p><a href="/signUp?lang=ru" onclick=ruCookie()>Русский</a></p>
    </div>
    <div class="form-style-6">
        <h1><@spring.message code="signup.formname"/></h1>
        <form name="user" action="/signUp" method="POST">
            <label><@spring.message code="signup.firstname"/>:
                <input type="text" placeholder="<@spring.message code="signup.firstname.placeholder"/>" name="firstName" required>
            </label>
            <label><@spring.message code="signup.lastname"/>:
                <input type="text" placeholder="<@spring.message code="signup.lastname.placeholder"/>" name="lastName" required>
            </label>
            <label><@spring.message code="signup.email"/>:
                <input type="email" placeholder="<@spring.message code="signup.email.placeholder"/>" name="email" required>
            </label>
            <label><@spring.message code="signup.phone"/>:
                <input type="text" placeholder="<@spring.message code="signup.phone.placeholder"/>" name="phone" required>
            </label>
            <label><@spring.message code="signup.login"/>:
                <input type="text" placeholder="<@spring.message code="signup.login.placeholder"/>" name="login" required>
            </label>
            <label><@spring.message code="signup.password"/>:
                <input type="password" name="password" placeholder="<@spring.message code="signup.password.placeholder"/>" required>
            </label>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit"  value="<@spring.message code="signup.button"/>"/>
        </form>
    </div>
</div>
</body>

</html>