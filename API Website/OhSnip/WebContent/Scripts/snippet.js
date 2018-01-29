$(document).ready(function () {

    var content;
    $('#TEXT').click(function () {
        window.location.href = "http://localhost:8081/OhSnip"
    })

    var idLogged = localStorage.getItem('user_id');

    var text = $(location).attr('href');
    var snipN = text.indexOf("snip=");
    var snip = text.substring(snipN + 5, text.length);
    var send = "http://localhost:8081/OhSnip/api/snippets/snippet/" + snip;
    $.getJSON(send, function (data, status) {
        console.log(data);
        $("#nomePag").html(data.nome_snippet);
        $("#id_snippet").html("Snippet ID: " + data.id_snippet);
        $("#descricao").html("Description: " + data.descricao);
        $("#visualizacoes").html("Views: " + data.visualizacoes);
        $("#classificacao").html("Rating: " + data.classificacao / data.reviewNum);
        $("#content").html(data.conteudo);
        content = data.conteudo;
        findAuthor(data.id_utilizador);

        if (data.id_utilizador == idLogged) {
            $("rateDiv").empty();
        }

        if (localStorage.getItem('user_id') == data.id_utilizador) {
            $("#snipList").append('<div class="container-fluid editSnippet" id=' + data.id_snippet + '><div class="row"><div class="cold-md-2 col-md-offset-10"><h4 class="editSnippet" id="gato">Edit Snippet</h4></div></div></div>');
        }
    });
    function findAuthor(id) {
        $.getJSON("http://localhost:8081/OhSnip/api/users/user/" + id, function (data, status) {
            $("#author").html("User ID: " + data.nome_utilizador);
        });
    }

    $('body').on('click', 'div.snippetBox', function () {
        window.location.href = "http://localhost:8081/OhSnip/snippet?snip=" + this.id;
    });

    $('body').on('click', 'h4.editSnippet', function () {
        window.location.href = "http://localhost:8081/OhSnip/editSnippet?snip=" + snip;
    })

    ill = false;


    $("#rateBtn").click(function () {
        var score = $("#rateSel").val();

        if (idLogged != undefined && idLogged != null) {
            $.getJSON(send, function (data, status) {
                var res = data.idRev.split(";");
                for (i = 0; i < res.length; i++) {
                    if (idLogged == res[i]) {
                        ill = true;
                    }
                }
                if (ill == false) {
                    $.ajax({
                        type: "PUT",
                        url: "http://localhost:8081/OhSnip/api/snippets/rate",
                        data: {
                            id_snippet: snip,
                            id_user: idLogged,
                            rate: score,
                        },
                    })
                        .done(function () {
                            alert("Score saved");
                            ill = false;
                        })
                } else {
                    alert("You have already scored this snippet");
                }
            });
        } else {
            alert("You need to be logged in to rate a snippet");
        }

    });
});