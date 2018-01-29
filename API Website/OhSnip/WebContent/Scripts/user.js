$(document).ready(function () {
    
    $('#TEXT').click(function() {
        window.location.href = "http://localhost:8081/OhSnip"
    })
    
    var idUser;
    var idUserLocal;
    var text = $(location).attr('href');
    var userN = text.indexOf("u=");
    var user = text.substring(userN + 2, text.length);

    if (localStorage.getItem('hashSnip') != undefined && localStorage.getItem('hashSnip') != null) {
        idUser = localStorage.getItem('user_id');
    }

    function getProfile() {
        var send = "http://localhost:8081/OhSnip/api/users/user/" + user;
        $.getJSON(send, function (data, status) {      
            $("#snipList").empty();
            $("#snipList").append('<div class="col-md-12"> <h1 id="nomeProfile"> Nome: ' + data.nome_utilizador + '#' + data.id + '</h1> <br> <h3 id="emailProfile">Email: ' + data.email + '</h3></div>');
            idUserLocal = data.id;
        });
    }

    function getSnippets() {
        var send2 = "http://localhost:8081/OhSnip/api/snippets/users/" + user;
        $.getJSON(send2, function (data, status) {
            $("#snipList").empty();
            if (idUser == idUserLocal) {
                $("#snipList").append('<div class="container-fluid newSnippet"><div class="row"><div class="cold-md-2 col-md-offset-10"><h4 id="gato">New Snippet</h4></div></div></div>');
            }
            $.each(data, function (index, value) {
                var langColor;
                var nomeUtil;
                if (value.linguagem == "Python") {
                    langColor = "lightgray";
                } else if (value.linguagem == "Java") {
                    langColor = "red";
                } else if (value.linguagem == "CSharp") {
                    langColor = "blue"
                } else if (value.linguagem == "Javascript") {
                    langColor = "yellow"
                } else if (value.linguagem == "C++") {
                    langColor = "lightblue"
                } else {
                    langColor = "gray";
                } 
                $.getJSON("http://localhost:8081/OhSnip/api/users/user/" + value.id_utilizador, function (data, status) {
                    nomeUtil = data.nome_utilizador;
                })

                .done(function() {
                    $("#snipList").append('<div class="container-fluid snippetBox" id=' + value.id_snippet + '> <div class="row titleViews ' + langColor + '"> <div class="col-md-10"> <h1>' + value.nome_snippet + '</h1> </div> <div class="col-md-2"> <h3> by ' + nomeUtil + ' </h3></div> </div> <div class="row contentInfo"> <div class="col-md-11"> <h5> Tags:' + value.tags + '</h5> </div> <div class="col-md-1"> <h5> Rating: ' + value.classificacao/value.reviewNum  + '</h5> <h5> Views: ' + value.visualizacoes + '</h5> </div> </div>');
                    if (value.id_utilizador == localStorage.getItem('user_id')) {
                        $("#snipList").append('<div class="container-fluid deleteSnippet" id=' + value.id_snippet + '><div class="row"><div class="cold-md-2 col-md-offset-10"><h4 id="gato">Delete Snippet</h4></div></div></div>');
                    };
                });
            });
        });
    }

    $('body').on('click', 'div.snippetBox', function () {
        window.location.href = "http://localhost:8081/OhSnip/snippet?snip=" + this.id;
    });

    $('body').on('click', 'div.deleteSnippet', function () {
        console.log(this.id);
        $.ajax({
            method: "DELETE",
            url: "http://localhost:8081/OhSnip/api/snippets/snippet/" + this.id,
            data: {
                token: localStorage.getItem('hashSnip'),
            }
          })
            .done(function(msg) {
              alert(msg);
              window.location.href = "http://localhost:8081/OhSnip/user?u=" + localStorage.getItem('user_id');
            });
    });
    
    $('body').on('click', 'div.newSnippet', function () {
        console.log("Gato");
        window.location.href = "http://localhost:8081/OhSnip/new"
    });

    $("#dataProfile").click(function () {
        getProfile();
    }); 

    $("#snippetProfile").click(function () {
        getSnippets();
    });


        
    getProfile();
});