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
                <#assign fNamePl><@spring.message code="signup.firstname.placeholder"/></#assign>
                <@spring.formInput "user.firstName" "placeholder=${fNamePl}"/>
                <@spring.showErrors "<br>" "error"/>
                <br>
                <br>
            </label>
            <label><@spring.message code="signup.lastname"/>:
                <#assign lNamePl><@spring.message code="signup.lastname.placeholder"/></#assign>
                <@spring.formInput "user.lastName" "placeholder=${lNamePl}"/>
                <@spring.showErrors "<br>" "error"/>
                <br>
                <br>
            </label>
            <label><@spring.message code="signup.email"/>:
                <#assign emailPl><@spring.message code="signup.email.placeholder"/></#assign>
                <@spring.formInput "user.email" "placeholder=${emailPl}"/>
                <@spring.showErrors "<br>" "error"/>
                <br>
                <br>
            </label>
            <label><@spring.message code="signup.phone"/>:
                <#assign phonePl><@spring.message code="signup.phone.placeholder"/></#assign>
                <@spring.formInput "user.phone" "placeholder=${phonePl}"/>
                <@spring.showErrors "<br>" "error"/>
                <br>
                <br>
            </label>
            <label><@spring.message code="signup.login"/>:
                <#assign loginPl><@spring.message code="signup.login.placeholder"/></#assign>
                <@spring.formInput "user.login" "placeholder=${loginPl}"/>
                <@spring.showErrors "<br>" "error"/>
                <br>
                <br>
            </label>
            <label><@spring.message code="signup.password"/>:
                <#assign passPl><@spring.message code="signup.password.placeholder"/></#assign>
                <@spring.formPasswordInput "user.password" "placeholder=${passPl}"/>
                <@spring.showErrors "<br>" "error"/>
                <br>
                <br>
            </label>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit"  value="<@spring.message code="signup.button"/>"/>
        </form>
    </div>
</div>
</body>

</html>