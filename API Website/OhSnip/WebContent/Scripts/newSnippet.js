$(document).ready(function () {

    $('#TEXT').click(function() {
        window.location.href = "http://localhost:8081/OhSnip"
    })
    
    $("#addSnippet").click(function (e) {
        e.preventDefault();
        if (localStorage.getItem('hashSnip') != undefined && localStorage.getItem('hashSnip') != null) {
            var nome = $("#nomeSnippet").val();
            var utilizador = localStorage.getItem('user_id');
            var descricao = $("#descricaoSnippet").val();
            var linguagem = $("#linguagemSnippet").val();
            var conteudo = $("#conteudoSnippet").val();
            var tags = $("#tagsSnippet").val();
            $.ajax({
                type: "POST",
                url: "http://localhost:8081/OhSnip/api/snippets/new",
                data: {
                    nome: nome,
                    utilizador: utilizador,
                    descricao: descricao,
                    linguagem: linguagem,
                    conteudo: conteudo,
                    tags: tags,
                },
            })
                .done(function () {
                    alert("Snippet created with success");
                    window.location.href = "http://localhost:8081/OhSnip/user?u=" + localStorage.getItem('user_id');
                  
                })
        }
    });
});