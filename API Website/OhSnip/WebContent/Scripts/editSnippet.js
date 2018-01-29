$(document).ready(function () {

    $('#TEXT').click(function () {
        window.location.href = "http://localhost:8081/OhSnip"
    })

    var text = $(location).attr('href');
    var snipN = text.indexOf("snip=");
    var snip = text.substring(snipN + 5, text.length);
    var send = "http://localhost:8081/OhSnip/api/snippets/snippet/" + snip;

    $.getJSON(send, function (data, status) {
        $("#nomeSnippet").val(data.nome_snippet);
        $("#descricaoSnippet").html(data.descricao);
        $("#linguagemSnippet").val(data.linguagem);
        $("#conteudoSnippet").html(data.conteudo);
        $("#tagsSnippet").val(data.tags);
    });



    $("#editSnippet").click(function (e) {
        e.preventDefault();
        if (localStorage.getItem('hashSnip') != undefined && localStorage.getItem('hashSnip') != null) {
            var hash = localStorage.getItem("hashSnip");
            console.log(hash);
            var nome = $("#nomeSnippet").val();
            var descricao = $("#descricaoSnippet").val();
            var linguagem = $("#linguagemSnippet").val();
            var conteudo = $("#conteudoSnippet").val();
            var tags = $("#tagsSnippet").val();
            $.ajax({
                type: "PUT",
                url: "http://localhost:8081/OhSnip/api/snippets/edit",
                data: {
                    id_snippet: snip,
                    nome_snippet: nome,
                    descricao: descricao,
                    linguagem: linguagem,
                    conteudo: conteudo,
                    tags: tags,
                    token: hash,
                },
            })
                .done(function () {
                    alert("Snipped edited with success");
                    window.location.href = "http://localhost:8081/OhSnip/user?u=" + localStorage.getItem('user_id');

                })
        }
    });


});