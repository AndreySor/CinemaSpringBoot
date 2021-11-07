<#import "/spring.ftl" as spring/>

<html>
<head>
    <meta charset="UTF-8">
    <style>
        <#setting classic_compatible=true>
        <#include "css/style.css">
    </style>
    <title>Аутентификация</title>
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
        <p><a href="/signIn?lang=en" onclick=enCookie()>English</a></p>
        <p><a href="/signIn?lang=ru" onclick=ruCookie()>Русский</a></p>
    </div>
    <div class="form-style-6">
        <h1><@spring.message code="label.login_page_title"/></h1>
        <form name="auth" action="/j_spring_security_check" method="POST">
            <label><@spring.message code="label.login"/>:
                <input type="text" placeholder="<@spring.message code="label.login_field"/>" name="username">
            </label>
            <label><@spring.message code="label.password"/>:
                <input type="password" name="password" placeholder="<@spring.message code="label.password_field"/>">
            </label>
            <label><@spring.message code="label.remember"/>?:
                <input type="checkbox" name="remember-me">
            </label>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit"><@spring.message code="label.submit"/></button>
        </form>
    </div>
</div>
</body>

</html>