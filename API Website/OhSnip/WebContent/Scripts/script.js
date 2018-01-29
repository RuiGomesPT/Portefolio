$(document).ready(function () {

    $('#TEXT').click(function () {
        window.location.href = "http://localhost:8081/OhSnip"
    })

    var list;
    $.getJSON("http://localhost:8081/OhSnip/api/snippets", function (data, status) {
        list = data;
        $("#snipList").empty();
        $.each(list, function (index, value) {
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
                .done(function () {
                    $("#snipList").append('<div class="container-fluid snippetBox" id=' + value.id_snippet + '> <div class="row titleViews ' + langColor + '"> <div class="col-md-10"> <h1>' + value.nome_snippet + '</h1> </div> <div class="col-md-2"> <h3> by ' + nomeUtil + ' </h3></div> </div> <div class="row contentInfo"> <div class="col-md-11"> <h5> Tags: ' + value.tags + '</h5> </div> <div class="col-md-1"> <h5> Rating: ' + value.classificacao/value.reviewNum + '</h5> <h5> Views: ' + value.visualizacoes + '</h5> </div> </div>');
                    $("#snipList").append('<div class="container-fluid authorLink" id=' + value.id_utilizador + '><div class="row"><div class="cold-md-2 col-md-offset-10"><h4 id="gato">Visit Author Profile</h4></div></div></div>');
                });
        });
    });

    $('body').on('click', 'input.profile', function () {
        window.location.href = "http://localhost:8081/OhSnip/user?u=" + localStorage.getItem('ohsnipid');
    });

    $('body').on('click', 'input.logout', function () {
        localStorage.removeItem('ohsnipid');
        window.location.reload()
    });

    $('body').on('click', 'div.snippetBox', function () {
        window.location.href = "http://localhost:8081/OhSnip/snippet?snip=" + this.id;
    });

    $('body').on('click', 'div.authorLink', function () {
        window.location.href = "http://localhost:8081/OhSnip/user?u=" + this.id;
    });

    $('body').on('click', 'h3.userR', function () {
        window.location.href = "http://localhost:8081/OhSnip/user?u=" + this.id;
    });

    $('#IDsearchBtn').click(function () {
        window.location.href = "http://localhost:8081/OhSnip/snippet?snip=" + $('#idSearch').val();
    });

    $("#searchBtn").click(function () {
        $("#snipList").empty();
        var search = $("#tagSearch").val();
        var send = "http://localhost:8081/OhSnip/api/snippets/" + search;
        $.getJSON(send, function (data, status) {
            list = data;
            $.each(list, function (index, value) {
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
                    .done(function () {

                        if ($("#selectLang").val() == "All") {
                            $("#snipList").append('<div class="container-fluid snippetBox" id=' + value.id_snippet + '> <div class="row titleViews ' + langColor + '"> <div class="col-md-10"> <h1>' + value.nome_snippet + '</h1> </div> <div class="col-md-2"> <h3> by ' + nomeUtil + ' </h3></div> </div> <div class="row contentInfo"> <div class="col-md-11"> <h5> Tags: ' + value.tags + '</h5> </div> <div class="col-md-1"> <h5> Rating: ' + value.classificacao/value.reviewNum  + '</h5> <h5> Views: ' + value.visualizacoes + '</h5> </div> </div>');
                            $("#snipList").append('<div class="container-fluid authorLink" id=' + value.id_utilizador + '><div class="row"><div class="cold-md-2 col-md-offset-10"><h4 id="gato">Visit Author Profile</h4></div></div></div>');
                        } else if ($("#selectLang").val() == value.linguagem) {
                            $("#snipList").append('<div class="container-fluid snippetBox" id=' + value.id_snippet + '> <div class="row titleViews ' + langColor + '"> <div class="col-md-10"> <h1>' + value.nome_snippet + '</h1> </div> <div class="col-md-2"> <h3> by ' + nomeUtil + ' </h3></div> </div> <div class="row contentInfo"> <div class="col-md-11"> <h5> Tags: ' + value.tags + '</h5> </div> <div class="col-md-1"> <h5> Rating: ' + value.classificacao/value.reviewNum  + '</h5> <h5> Views: ' + value.visualizacoes + '</h5> </div> </div>');
                            $("#snipList").append('<div class="container-fluid authorLink" id=' + value.id_utilizador + '><div class="row"><div class="cold-md-2 col-md-offset-10"><h4 id="gato">Visit Author Profile</h4></div></div></div>');
                        } else if ($("selectLang").val() == "Others") {
                            if (value.linguagem != "Python" && value.linguagem != "Java" && value.linguagem != "CSharp" && value.linguagem != "Javascript" && value.linguagem != "C++") {
                                $("#snipList").append('<div class="container-fluid snippetBox" id=' + value.id_snippet + '> <div class="row titleViews ' + langColor + '"> <div class="col-md-10"> <h1>' + value.nome_snippet + '</h1> </div> <div class="col-md-2"> <h3> by ' + nomeUtil + ' </h3></div> </div> <div class="row contentInfo"> <div class="col-md-11"> <h5> Tags: ' + value.tags + '</h5> </div> <div class="col-md-1"> <h5> Rating: ' + value.classificacao/value.reviewNum  + '</h5> <h5> Views: ' + value.visualizacoes + '</h5> </div> </div>');
                                $("#snipList").append('<div class="container-fluid authorLink" id=' + value.id_utilizador + '><div class="row"><div class="cold-md-2 col-md-offset-10"><h4 id="gato">Visit Author Profile</h4></div></div></div>');
                            }
                        }
                    })

            });
        });
    });
});