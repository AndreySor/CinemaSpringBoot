<#import "/spring.ftl" as spring/>

<html>
<head>
    <style>
        <#setting classic_compatible=true>
        <#include "css/chat.css">
    </style>
    <title>Chat</title>
    <meta charset="UTF-8">
</head>
<body>
<div id="chat-page">
    <div class="chat-container">
        <div class="chat-header">
            <h2>${film.title}</h2>
            <input type="hidden" id="htmlFilmId" value="${film.filmId}">
        </div>
        <div class="connecting">
            Connecting...
        </div>
        <ul id="messageArea">
            <#list history as historyMessage>
                <li class="chat-message">
                    <span>${historyMessage.user.login}</span>
                    <p>${historyMessage.message}</p>
                </li>
            </#list>
        </ul>
        <form id="messageForm" name="messageForm">
            <div class="form-group">
                <div class="input-group clearfix">
                    <input type="text" id="message" placeholder="Type a message..." autocomplete="off" class="form-control"/>
                    <button type="submit" class="primary">Send</button>
                </div>
            </div>
        </form>
    </div>
    <div class="user-info">
        <div id="authInfo" class="auth">
            <h3>Users authentications</h3>
        </div>
        <div class="avatar">
            <div id="userAvatars">
                <h3>User's avatars</h3>
            </div>
            <form id="avatarForm" enctype="multipart/form-data" method="POST" action="/images">
                <p>
                    <input type="file" name="avatar" id="avatar" multiple accept="image/*">
                    <input type="hidden" name="filmId" id="formFilmId">
                    <input type="hidden" name="userId" id="formUserId">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <button id="uploadButton">Upload</button>
                </p>
            </form>
        </div>
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"></script>
<script type="text/javascript" src="/static/js/chat.js"></script>
</body>
</html>