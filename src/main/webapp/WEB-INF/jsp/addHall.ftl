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
        <legend>Add Hall</legend>
        <form name="hall" action="/admin/panel/saveNewHall" method="POST">
            Serial Number: <input type="number" name="serialNumber" /><br/>
            Seats Number:  <input type="number" name="seatsNumber" /><br/>
            <input type="submit" value="Create"/>
            <input type="button" value="Exit"
                   onclick="window.location.href = '/admin/panel/halls'"/>
        </form>
    </fieldset>
</div>
</body>

</html>