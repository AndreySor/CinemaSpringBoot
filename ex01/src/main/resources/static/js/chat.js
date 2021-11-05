'use strict';
let usernamePage = document.querySelector('#username-page');
let chatPage = document.querySelector('#chat-page');
let usernameForm = document.querySelector('#usernameForm');
let messageForm = document.querySelector('#messageForm');
let messageInput = document.querySelector('#message');
let messageArea = document.querySelector('#messageArea');
let connectingElement = document.querySelector('.connecting');
let chatFilmId;
let stompClient = null;
let username = null;
let colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];
$(document).ready(function () {
    chatFilmId = $("#htmlFilmId").val();
    console.log(chatFilmId);
    document.getElementById("formFilmId").value=(chatFilmId);
    username = getCookie("login");
    let socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
})

function onConnected() {
    stompClient.subscribe('/topic/public', onMessageReceived);
    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({type: 'JOIN', user: {
                login: username
            }})
    )
    connectingElement.classList.add('hidden');
}

function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}

function sendMessage(event) {
    let messageContent = messageInput.value.trim();
    if(messageContent && stompClient) {
        let chatMessage = {
            message: messageInput.value,
            type: 'CHAT',
            film: {
                filmId: chatFilmId
            },
            user: {
                id: getCookie("userId"),
                login: username
            }
        };
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}
function onMessageReceived(payload) {
    let message = JSON.parse(payload.body);
    let messageElement = document.createElement('li');
    if (message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.message = message.user.login + ' joined!';
        if (!getCookie("userId")) {
            document.cookie = "userId=" + message.user.id;
        }
        if (getCookie("userId") == message.user.id) {
            document.getElementById("formUserId").value=(getCookie("userId"));
            getAuthList();
            getListOfAvatar();
        }
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.message = message.user.login + ' left!';
    } else {
        messageElement.classList.add('chat-message');
        let avatarElement = document.createElement('i');
        let avatarText = document.createTextNode(message.user.login[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.user.login);
        messageElement.appendChild(avatarElement);
        let usernameElement = document.createElement('span');
        let usernameText = document.createTextNode(message.user.login);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }
    let textElement = document.createElement('p');
    let messageText = document.createTextNode(message.message);
    textElement.appendChild(messageText);
    messageElement.appendChild(textElement);
    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}
function getAvatarColor(messageSender) {
    let hash = 0;
    for (let i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }
    let index = Math.abs(hash % colors.length);
    return colors[index];
}
function getCookie(name) {
    let matches = document.cookie.match(new RegExp(
        "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
    ));
    return matches ? decodeURIComponent(matches[1]) : undefined;
}
function setCookie(name, value) {
    document.cookie = encodeURIComponent(name) + "=" + encodeURIComponent(value);
}
function deleteCookie(name) {
    setCookie(name, "", {
        'max-age': -1
    })
}
function getAuthList() {
    let url = "/userInfo/auth";
    $.ajax({
        type: "GET",
        url: url,
        contentType: "application/json",
        dataType: "json",
        data: {id: getCookie("userId")},
        success: function (rs) {
            $.each(rs, function (key, value) {
                $("#authInfo").append('<div><p>' + value.ipAddress + ' ' + value.date + '</p></d>')
            })
        },
        error: function (jqXhr, textStatus, errorMessage) {
            console.log("Error", errorMessage);
        }
    })
}
function getListOfAvatar() {
    let url = "/chat/usersAvatars/list";
    $.ajax({
        type: "GET",
        url: url,
        contentType: "application/json",
        dataType: "json",
        data: {id: getCookie("userId")},
        success: function (rs) {
            $.each(rs, function (key, value) {
                $("#userAvatars").append('<p><a href="/chat/avatar/' + getCookie("userId") + '/' + value + '" target="_blank">' + value + '</a></p>')
            })
        },
        error: function (jqXhr, textStatus, errorMessage) {
            console.log("Error", errorMessage);
        }
    })
}
messageForm.addEventListener('submit', sendMessage, true)