<#import "/spring.ftl" as spring/>

<html>
<head>
    <style>
        <#setting classic_compatible=true>
        <#include "css/profile.css">
    </style>
    <title>Profile</title>
</head>
<body>
<h1>Profile</h1>
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
            <button id="uploadButton">Upload avatar</button>
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