<#import "/spring.ftl" as spring/>

<html>
<head>
    <title>Add Hall</title>
</head>
<body>
<#if errorMessage??>
    <div style="color:red;font-style:italic;">
        ${errorMessage}
    </div>
</#if>

<div>
    <fieldset>
        <legend>Add Poster</legend>
        <form name="saveFilm" action="/admin/panel/savePoster" method="POST" enctype="multipart/form-data">
            <td><input type="hidden" value="${saveFilm.title}" name="title"></td>
            <td><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></td>
            Poster: <input type="file" name="file" /><br/>
            <input type="submit" value="Create"/>
            <input type="button" value="Exit"
                   onclick="window.location.href = '/admin/panel/films'"/>
        </form>
    </fieldset>
</div>
</body>

</html>