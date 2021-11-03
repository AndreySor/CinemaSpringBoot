const token = $("meta[name='_csrf']").attr("content");
const header = $("meta[name='_csrf_header']").attr("content");

$(document).ajaxSend(function(e, xhr, options) {
    xhr.setRequestHeader(header, token);
});

$(document).ready(function () {
    $("#searchField").keyup(function (e) {

        $("#result").html('');
        e.preventDefault();

        let url = "/sessions/search"
        let rq = $("#searchField").val();

        $.ajax({
            type: "GET",
            url: url,
            contentType: "application/json",
            dataType: "json",
            data: {filmName: rq},
            success: function (data) {
                $.each(data.sessions, function (key, value) {
                    $("#result").append('<div>' +
                        '<p><img src="data:image/png;base64,'+value.film.posterUrl+'" width="150", height="200"></p>' +
                        '<p>'+value.dateTime+'</p>' +
                        '<p><a href="/sessions/'+value.id+'">'+value.film.name+'</a></p>' +
                        '</div>')
                });
            },
            error: function (jqXhr, textStatus, errorMessage) {
                console.log("Error", errorMessage);
                $("#result").append('<p class="error-msg">Not found any sessions</p>');
            }
        });
    })
})