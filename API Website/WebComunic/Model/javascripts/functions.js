$(document).ready(function () {

    //verificador de cookies
    function checkCookie() {
        if (document.cookie.indexOf('IDUtil=')) {
            $("#refreshPNum").val("Entrar");
            $("#changeUp").append("<form id='login-nav' role='form' method='post' action='/login' accept-charset='UTF-8' class='form'><div class='form-group'><label for='username' class='sr-onl'>Utilizador</label><input id='username' type='username' required='' placeholder='Nome de utilizador' class='form-control' name='username'></div><div class='form-group'><label for='password' class='sr-only'>Palavra-Passe</label><input id='password' type='password' placeholder='Palavra-passe' required='' class='form-control' name='password'></div><div class='form-group'><button id='botaoLogin' class='btn btn-danger btn-block'>Fazer Login</button></div></form><form id='regist-nav' role='form' method='post' action='/registo' accept-charset='UTF-8' class='form'><div class='form-group'><button id='botaoRegisto' class='btn btn-danger btn-block'>Fazer Registo</button></div></form>");
        } else {
            $("#refreshPNum").val("Conta");
            $("#changeUp").append("<form id='perfil-nav' role='form' method='post' action='/perfil' accept-charset='UTF-8' class='form'><div class='form-group'><label for='perfilNum' class='sr-onl'>Nº de Utilizador</label><input id='perfilNum' readonly type='perfilNum' class='form-control' name='perfilNum'><br><button id='botaoPerfil' class='btn btn-danger btn-block'>Perfil</button><br></div></form><form id='perfil-nav' role='form' method='post' action='/logOut' accept-charset='UTF-8' class='form'><div class='form-group'><button id='logOut' class='btn btn-danger btn-block'>Log out</button></div></form>");
        }
    }

    checkCookie();

    //importe de produtos
    $(function () {
        $.ajax({
            type: 'POST',
            url: 'https://wcomunic.herokuapp.com/getPacotes',
            success: function (data) {
                for (var i = 0; i < data.length; i++) {

                    var idPacote = data[i].idPacotes;
                    var nomePacote = data[i].Nome_Pacote;
                    var precoPacote = data[i].Preco_Pacote;
                    var fornecedorPacote = data[i].Fornecedor_Pacote;
                    var internet = data[i].Internet;
                    var netMovel = data[i].Internet_Telemovel;
                    var telemovel = data[i].Telefone;
                    var televisao = data[i].Televisao;
                    var extras = data[i].extras;
                    var destaques = data[i].destaques;
                    var foto = "https://webitcloud.net/PW/1617/RMR/Views/images/Pacotes/" + data[i].Foto;
                    var pacoteSet1 = "<div class='row'><div class='col-md-6 col-lg-6 col-xs-6 col-sm-6 pacotes'>";
                    var dynaButton = "<input class='produto' id=" + idPacote + " type='button' name='comprar' value='Comprar Pacote'>";
                    var pacoteSet2 = "<img src=" + foto + " alt='Mountain View' style='width:150px;height:150px;'><br>";
                    var pacoteSet3 = "</div></div>";
                    console.log("destaques");
                    if (destaques == 1) {
                        $("#destaquesAtt").append("<div class='col-md-4 col-lg-4 col-xs-4 col-sm-4 text-center" + pacoteSet2 + "class='img-set'><h3>" + idPacote + "<br><mark>" + nomePacote + "</mark></h3>" + fornecedorPacote + "<hr><p>" + televisao + "</p><p>" + internet + "</p><p>" + telemovel + "</p><p>" + extras + "</p><p>" + netMovel +"</p></div>'");
                    }

                    $("#pacotes").append(pacoteSet1 + pacoteSet2 + "</div><div class='col-md-6 col-lg-6 col-xs-6 col-sm-6 pacotes'><h1> ID do Pacote: #" + idPacote + "</h1>" + "<h1> Nome: " + nomePacote + "</h1>" + "<h3> Fornecedor: " + fornecedorPacote + "</h3>" + "<h3> Preco do Pacote: " + precoPacote + "euros/mes </h3>" + "<h5> Internet: " + internet + "</h5>" + "<h5> Telemovel: " + telemovel + "</h5>" + pacoteSet3);
                    //$("#pacotes").append(pacoteSet1 + "<h1>" + idPacote + "</h1>" + "<h1>" + nomePacote + "</h1>" + "<h3>" + fornecedorPacote + "</h3>" + "<h3>" + precoPacote + "euros/mes </h3>" + "<h5>" + internet + "</h5>" + "<h5>" + telemovel + "</h5>" + pacoteSet2 + pacoteSet3);
                    // $(document).find("#" + i).attr({
                    //     "id": idPacote
                    // });
                }
            }
        });
    });

    //Funcoes
    $("#submitRegisto").click(function() {
        alert("Registo efectuado com sucesso");
    });

    $("#botaoComprarProd").click(function() {
        alert("Compra efectuada com sucesso");
    });


    //leitor de cookies
    function readCookie(name) {
        var nameEQ = name + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') c = c.substring(1, c.length);
            if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
        }
        return null;
    }

    //confirmar compra
    $("#loadFile").click(function () {
        console.log(readCookie("IDUtil"));
        $("#userBuy").val(readCookie("IDUtil"));
    });

    
    $("#refreshPNum").click(function () {
        console.log(readCookie("IDUtil"));
        document.getElementById("perfilNum").value = readCookie("IDUtil");
    });

    //tentativas falhadas
    //as funcoes estavam corretas mas nao renderizar o template de Handlebars

    // $("#botaoLogin").click(function () {
    //     var username = $("#username").val();
    //     var password = $("#password").val();
    //     $.ajax({
    //         type: 'POST',
    //         url: "https://wcomunic.herokuapp.com/logIn",
    //         data: {
    //             username: username,
    //             password: password
    //         },
    //         success: function (data) {
    //             jQuery(window).load(function () {
    //                 sessionStorage.setItem('status', 'loggedIn')
    //             });
    //         }
    //     });
    // });

    // $("#pacotes").on('click', ".produto", function () {
    //     var prod = this.id;
    //     console.log(prod);
    //     $.get("http://localhost:3000/pacote?idPacote=" + prod);
    // });
    // for (i = 0; i < 1000; i++) {
    //     console.log("gato");
    //     $("#pacotes").on("click", "#btnPac" + i, function () {
    //         console.log("cao");
    //         $.post("http://localhost:3000/pacote" + this.idPacote);
    //     });
    // }

});