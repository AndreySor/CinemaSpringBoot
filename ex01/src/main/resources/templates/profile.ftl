<#import "/spring.ftl" as spring/>

<html>
<head>
    <style>
        <#setting classic_compatible=true>
        <#include "css/profile.css">
    </style>
    <title><@spring.message code="profile.title"/></title>
    <script type="text/javascript" src="/static/js/util.js"></script>
</head>
<body>
<h1><@spring.message code="profile.title"/></h1>
<div class="change-lang">
    <p><a href="/profile?lang=en" onclick=enCookie()>English</a></p>
    <p><a href="/profile?lang=ru" onclick=ruCookie()>Русский</a></p>
</div>
<div class="profile-card">
    <img src="data:image/jpg;base64,${userInfo.avatar}" alt="profile-page" style="width: 100%">
    <h1>${userInfo.login}</h1>
    <h2>${userInfo.firstName} ${userInfo.lastName}</h2>
    <p class="title">${userInfo.email}</p>
    <p>${userInfo.phone}</p>
    <form id="avatarForm" enctype="multipart/form-data" method="POST" action="/avatarUpload">
        <p>
            <input type="file" name="avatar" id="avatar" multiple accept="image/*">
            <input type="hidden" name="userId" value="${userInfo.id}">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button id="uploadButton"><@spring.message code="profile.upload"/></button>
        </p>
    </form>
</div>
<#if errorMessage??>
    <div style="color:red;font-style:italic;">
        ${errorMessage}
    </div>
</#if>
</body>
</html>