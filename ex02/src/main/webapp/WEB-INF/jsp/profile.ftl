<#import "/spring.ftl" as spring/>

<html>
<head>
    <style>
        <#setting classic_compatible=true>
        <#include "css/style.css">
    </style>
    <title>Profile</title>
</head>
<body>
<h1>HALLS</h1>
<#if errorMessage??>
    <div style="color:red;font-style:italic;">
        ${errorMessage}
    </div>
</#if>
</body>

</html>