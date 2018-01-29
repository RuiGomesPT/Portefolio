$(document).ready(function () {
var click = false;
    // Função que ativa a bolinha do "E" quando clicada, põe o texto de cima legível, e tira as letras da ESMAD do ecrâ:
    $("#inter").click(function changerect(evt) {
 
        click = true;
        var efeito = evt.target;
        efeito.setAttribute('cx', 109);
        efeito.setAttribute('cy', 50);


        //Exconde os textos todos, excepto a mensagem pretendida:
        $("#texto").css({ fill: "black" });
        $("#esmad").css({ fill: "white" });
        $("#text1").css({ fill: "white" });
        $("#text2").css({ fill: "white" });
        $("#text3").css({ fill: "white" });
        $("#text4").css({ fill: "white" });
        $("#exter").css({ fill: "transparent" });
        //######################################################

        //Circulo grande:  
        $("#B1").attr('cx', 80);
        $("#B1").attr('cy', 50);
        $("#B2").attr('cx', 80);
        $("#B2").attr('cy', 50);
        //##############


        //Circulos médios:
        $("#A1").attr('cx', 48);
        $("#A1").attr('cy', 35);
        $("#A2").attr('cx', 48);
        $("#A2").attr('cy', 35);

        $("#F1").attr('cx', 48);
        $("#F1").attr('cy', 68);
        $("#F2").attr('cx', 48);
        $("#F2").attr('cy', 68);
        //###############


        //Circulos pequenos:
        $("#C1").attr('cx', 22);
        $("#C1").attr('cy', 51);
        $("#C2").attr('cx', 22);
        $("#C2").attr('cy', 51);

        $("#D1").attr('cx', 22);
        $("#D1").attr('cy', 20);
        $("#D2").attr('cx', 22);
        $("#D2").attr('cy', 20);

        $("#E1").attr('cx', 22);
        $("#E1").attr('cy', 83);
        $("#E2").attr('cx', 22);
        $("#E2").attr('cy', 83);
        //#################

        //Esconde botão traseiro:
        $("#exter").hide();
        //#######################
    });
    //#################################################################################################################


    // Função que faz a bolinha do "E" regressar à posição original quando duplamente clicada,põe o texto de cima ilegível e retorna as letras do título ESMAD à cor original:
    $("#inter").dblclick(function regresso(etc) {
        click = false;
        var efeito = etc.target;
        efeito.setAttribute('cx', 109);
        efeito.setAttribute('cy', 195);


        //Esconde a mensagem anterior, e expõe de novo os textos todos:
        $("#texto").css({ fill: "white" });
        $("#esmad").css({ fill: "black" });
        $("#text1").css({ fill: "black" });
        $("#text2").css({ fill: "black" });
        $("#text3").css({ fill: "black" });
        $("#text4").css({ fill: "black" });
        //#############################################################

        //Circulo grande:  
        $("#B1").attr('cx', 180);
        $("#B1").attr('cy', 222);
        $("#B2").attr('cx', 180);
        $("#B2").attr('cy', 222);
        //##############


        //Circulos médios:
        $("#A1").attr('cx', 183);
        $("#A1").attr('cy', 169);
        $("#A2").attr('cx', 183);
        $("#A2").attr('cy', 169);

        $("#F1").attr('cx', 514);
        $("#F1").attr('cy', 196);
        $("#F2").attr('cx', 514);
        $("#F2").attr('cy', 196);
        //###############


        //Circulos pequenos:
        $("#C1").attr('cx', 290);
        $("#C1").attr('cy', 243);
        $("#C2").attr('cx', 290);
        $("#C2").attr('cy', 243);

        $("#D1").attr('cx', 398);
        $("#D1").attr('cy', 247);
        $("#D2").attr('cx', 398);
        $("#D2").attr('cy', 247);

        $("#E1").attr('cx', 398);
        $("#E1").attr('cy', 188);
        $("#E2").attr('cx', 398);
        $("#E2").attr('cy', 188);
        //#################

        //Mostra de novo o botão traseiro:
        $("#exter").show();
        //################################

    });
    //########################################################################################################################################################################


    //Muda as cores do texto ESMAD ao clicar nas bolas A1-A2,ou D1-D2:
    $("#A1,#A2").mousedown(function () {
        if (click == false) {
             $("#esmad").css('fill', 'orange');
        } else if (click == true) {
             $("#esmad").css('fill', 'white');
        }
    });
    $("#A1,#A2").mouseup(function () {
        if (click == false) {
             $("#esmad").css('fill', 'black');
        } else if (click == true) {
             $("#esmad").css('fill', 'white');
        }
    });

    $("#D1,#D2").mousedown(function () {
       if (click == false) {
             $("#esmad").css('fill', 'blue');
        } else if (click == true) {
             $("#esmad").css('fill', 'white');
        }
    });
    $("#D1,#D2").mouseup(function () {
        if (click == false) {
             $("#esmad").css('fill', 'black');
        } else if (click == true) {
             $("#esmad").css('fill', 'white');
        }
    });
    //################################################################

});







