<html>
<head>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <style>
        <#include "css/sessionSearching.css">
    </style>
    <title>Sessions searching</title>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/sessionsLiveSearch.js"></script>
</head>
<body>
<input class="search" name="filmName" type="text" placeholder="Search..." id="searchField">
<br>
<div class="list-of-film" id="result">
</div>
</body>
</html>