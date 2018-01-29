window.onload = function () {
    var canvas = document.getElementById('myCanvas');
    if (canvas.getContext) {    //context
        var ctx = canvas.getContext("2d");
        var gameover = false;
        var nxtLv = false;
        var start = false;

        var disparoX = -1;
        var cont = 0;

        var ang = 0;

        var level = 1;




        var YKey = false;
        var NKey = false;
        var rightKey = false; //mover para a esquerda
        var leftKey = false; //mover para a direita
        var spaceBar = false; //disparar projétil
        var enterKey = false;

        var sizeX = 60; //tamanho do player em X
        var sizeY = 60; //tamanho do player em Y


        var posY = canvas.height - sizeY; //posição inicial do player em X
        var posX = canvas.width / 2; //posição inicial do player em Y

        var yGrow = 0; //crescimento do projétil
        var tempX = -1; //posição do player quando o projétil é disparado
        var position = true; //cooldown do projétil

        function ArrowPressed(evt) { //controlos do player
            if (evt.keyCode == 39) rightKey = true;

            if (evt.keyCode == 37) leftKey = true;

            if (evt.keyCode == 32) spaceBar = true;

            if (evt.keyCode == 89) YKey = true;

            if (evt.keyCode == 78) NKey = true;

            if (evt.keyCode == 13) enterKey = true;
        }

        function ArrowReleased(evt) {
            if (evt.keyCode == 39) rightKey = false;

            if (evt.keyCode == 37) leftKey = false;

            if (evt.keyCode == 89) YKey = false;

            if (evt.keyCode == 78) NKey = false;

            if (evt.keyCode == 13) enterKey = false;
        }

        window.addEventListener('keydown', ArrowPressed);
        window.addEventListener('keyup', ArrowReleased);



        function Bola(x, y, cor, raio, direction) { //construtor dos adversários

            this.direction = false;
            this.x = x; //posição X
            this.y = y; //posição Y
            this.raio = raio; //raio do objeto

            this.el = 8; //valor do salto inicial
            this.dY = this.el * Math.sin(275 * Math.PI / 180); //movimento em Y
            this.dX = this.el * Math.cos(275 * Math.PI / 180); //movimento em X
            this.g = 0.10; //gravidade/atrito

            this.width = 5; //tamanho da linha
            this.cor = cor; //cor do objeto


            this.bolaPX = this.x;
            this.bolaPY = this.y;

            this.bolaPX2 = this.x;
            this.bolaPY2 = this.y;

            this.bolaPX3 = this.x;
            this.bolaPY3 = this.y;

            this.bolaPX4 = this.x;
            this.bolaPY4 = this.y;

            this.text = " ";

            this.ballDraw = function () { //desenho dos adversários

                ctx.beginPath();
                ctx.lineWidth = this.width;
                ctx.fillStyle = this.cor;
                ctx.arc(this.x, this.y, this.raio, 0, 2 * Math.PI, true);
                ctx.closePath();
                ctx.fill();
                ctx.stroke();

                if (this.raio == 60) {

                    ctx.beginPath()
                    ctx.arc(this.bolaPX, this.bolaPY, 5, 0, 2 * Math.PI, true);
                    ctx.fill();
                    ctx.stroke();

                    ctx.beginPath()
                    ctx.arc(this.bolaPX2, this.bolaPY2, 5, 0, 2 * Math.PI, true);
                    ctx.fill();
                    ctx.stroke();

                    ctx.beginPath()
                    ctx.arc(this.bolaPX3, this.bolaPY3, 5, 0, 2 * Math.PI, true);
                    ctx.fill();
                    ctx.stroke();

                    ctx.beginPath()
                    ctx.arc(this.bolaPX4, this.bolaPY4, 5, 0, 2 * Math.PI, true);
                    ctx.fill();
                    ctx.stroke();

                    ctx.font = 'bold 40px Arial Black'
                    text = "C";
                    ctx.textAlign = 'center';
                    ctx.textBaseline = 'middle';
                    ctx.fillStyle = "white";
                    ctx.fillText(text, this.x, this.y);

                } else if (this.raio == 30) {

                    ctx.beginPath()
                    ctx.arc(this.bolaPX5, this.bolaPY, 5, 0, 2 * Math.PI, true);
                    ctx.fill();
                    ctx.stroke();

                    ctx.beginPath()
                    ctx.arc(this.bolaPX6, this.bolaPY2, 5, 0, 2 * Math.PI, true);
                    ctx.fill();
                    ctx.stroke();

                    ctx.font = 'bold 20px Arial Black'
                    text = "Be";
                    ctx.textAlign = 'center';
                    ctx.textBaseline = 'middle';
                    ctx.fillStyle = "white";
                    ctx.fillText(text, this.x, this.y);

                } else if (this.raio == 15) {
                    ctx.font = 'bold 15px Arial Black'
                    text = "He";
                    ctx.textAlign = 'center';
                    ctx.textBaseline = 'middle';
                    ctx.fillStyle = "red";
                    ctx.fillText(text, this.x, this.y);
                }

            }

            this.ballRefresh = function () { //movimento dos adversários



                if (this.y > canvas.height - this.raio) {
                    this.y = canvas.height - this.raio;
                    this.dY = this.el * Math.sin(275 * Math.PI / 180);
                } else {
                    this.dY = this.dY + this.g;
                    this.y = this.y + this.dY;
                    if (direction == true) {
                        this.x = this.x + this.dX;
                    }
                    else {
                        this.x = this.x - this.dX;
                    }
                }

                if (this.x + this.raio >= canvas.width) {
                    this.dX = -this.dX;
                } else if (this.x - this.raio <= 0) {
                    this.dX = -this.dX;
                }

                this.bolaPX = this.x + (this.raio + 15) * Math.cos(Math.PI / 180 * ang) * 1.5;
                this.bolaPY = this.y - (this.raio + 15) * Math.sin(Math.PI / 180 * ang) * 1.5;

                this.bolaPX2 = this.x - (this.raio + 15) * Math.cos(Math.PI / 180 * ang) * 1.5;
                this.bolaPY2 = this.y + (this.raio + 15) * Math.sin(Math.PI / 180 * ang) * 1.5;

                this.bolaPX3 = this.x + (this.raio + 15) * Math.cos(Math.PI / 180 * ang);
                this.bolaPY3 = this.y + (this.raio + 15) * Math.sin(Math.PI / 180 * ang);

                this.bolaPX4 = this.x - (this.raio + 15) * Math.cos(Math.PI / 180 * ang);
                this.bolaPY4 = this.y - (this.raio + 15) * Math.sin(Math.PI / 180 * ang);

                this.bolaPX5 = this.x + (this.raio + 15) * Math.cos(Math.PI / 180 * ang);
                this.bolaPY5 = this.y + (this.raio + 15) * Math.sin(Math.PI / 180 * ang);

                this.bolaPX6 = this.x - (this.raio + 15) * Math.cos(Math.PI / 180 * ang);
                this.bolaPY6 = this.y - (this.raio + 15) * Math.sin(Math.PI / 180 * ang);
            }
        }

        var bolas = new Array(); //array de adversários
        var raioInicial = 60;

        if (level == 1) {
            bolas.push(new Bola(raioInicial, 400, "brown", raioInicial, true));
        } else if (level == 2) {
            bolas.push(new Bola(raioInicial, 400, "brown", raioInicial, true));
            bolas.push(new Bola(canvas.width - 2 * raioInicial, 400, "brown", raioInicial, false));
        } else if (level == 3) {
            bolas.push(new Bola(raioInicial, 400, "brown", raioInicial, true));
            bolas.push(new Bola(canvas.width / 4, 400, "brown", raioInicial, true));
            bolas.push(new Bola(canvas.width - 2 * raioInicial, 400, "brown", raioInicial, false)); //bola inicial
        }


        var timer; //timer

        function drawIt() {
            if (gameover == false && start == true) {

                ctx.clearRect(0, 0, canvas.width, canvas.height);
                ctx.font = 'bold 15px Arial Black';
                ctx.fillStyle = "red";
                ctx.fillText("score: " + cont, canvas.width - 50, 30);


                var rect = { x: tempX - 5, y: canvas.height, width: 10, height: yGrow };
                var player = { x: posX, y: posY, width: sizeX, height: sizeY };


                if (rightKey == true && posX + sizeX < canvas.width)
                    //posX++; //movimento do player em X
                    posX += 3;

                if (leftKey == true && posX > 0)
                    //posX--; //movimento do player em Y
                    posX -= 3;

                if (spaceBar == true) { //disparo

                    disparoX = player.x + player.width / 2;

                    if (yGrow > -canvas.height) {
                        yGrow -= 5;

                        if (position == true) {
                            tempX = posX + 30;

                            position = false;
                        }

                    } else {
                        ctx.clearRect(rect.x, rect.y, rect.width, rect.height);
                        yGrow = 0;
                        spaceBar = false;
                        position = true;
                        tempX = -1;
                    }


                }



                if (tempX != -1) {
                    for (var i = 0; i < bolas.length; i++) {
                        var distX = Math.abs(rect.x - bolas[i].x);
                        var distY = rect.y + rect.height - bolas[i].y;
                        if (distX <= bolas[i].raio && (distY >= 0 && distY <= bolas[i].raio)) {
                            if (bolas[i].raio != 15) {

                                var cor;

                                if (bolas[i].raio == 60) {
                                    cor = "purple";
                                } else {
                                    cor = "gold";
                                }
                                bolas.push(new Bola(bolas[i].x - 35, 400, cor, bolas[i].raio / 2, false));
                                bolas.push(new Bola(bolas[i].x + 35, 400, cor, bolas[i].raio / 2, true));
                                cont++;
                                yGrow = 0;
                                position = true;
                                spaceBar = false;
                                tempX = -1;
                                bolas.splice(i, 1);
                            }
                            else {
                                yGrow = 0;
                                cont++;
                                position = true;
                                spaceBar = false;
                                tempX = -1;
                                bolas.splice(i, 1);
                            }
                        }
                    }
                }

                ctx.fillStyle = "grey";

                ctx.fillRect(rect.x, rect.y, rect.width, rect.height);

                for (var i = 0; i < bolas.length; i++) {
                    var valX = Math.abs((player.x + player.width) - bolas[i].x);
                    var valY = Math.abs(player.y - bolas[i].y);

                    if (valX <= 2 * bolas[i].raio && valY <= bolas[i].raio) {
                        gameover = true;
                    }
                }

                ctx.fillStyle = "orange";
                ctx.fillRect(player.x, player.y, player.width, player.height);
                ctx.strokeRect(player.x, player.y, player.width, player.height);

                for (var i = 0; i < bolas.length; i++) {
                    bolas[i].ballDraw();
                }

                for (var i = 0; i < bolas.length; i++) {
                    bolas[i].ballRefresh(); //movimento do Array de adversários
                }

                if (cont == 7 && level == 1 || cont == 21 && level == 2 || cont == 42 && level == 3) {
                    gameover = true;
                    nxtLv = true;
                }

                console.log(nxtLv);

                ang++;

            } else if (gameover == true && nxtLv == true) {
                if (level == 1) {
                    ctx.clearRect(0, 0, canvas.width, canvas.height);
                    ctx.font = 'bold 40px Arial Black'
                    text = "Acabou o nivel 1!"
                    ctx.textAlign = 'center';
                    ctx.textBaseline = 'middle';
                    ctx.fillStyle = "black";
                    ctx.fillText(text, canvas.width / 2, canvas.height / 2);

                    ctx.font = 'bold 20px Arial Black'
                    text = "Pressione Enter para continuar";
                    ctx.textAlign = 'center';
                    ctx.textBaseline = 'middle';
                    ctx.fillStyle = "black";
                    ctx.fillText(text, canvas.width / 2, 3 * canvas.height / 4);
                    if (enterKey == true) {
                        bolas.splice(0, bolas.length);
                        yGrow = 0;
                        position = false;
                        level = 2;
                        bolas.push(new Bola(raioInicial, 400, "brown", raioInicial, true));
                        bolas.push(new Bola(canvas.width - 2 * raioInicial, 400, "brown", raioInicial, false));
                        posX = canvas.width / 2 - sizeX;
                        gameover = false;
                        nxtLv = false;
                    }
                } else if (level == 2) {
                    ctx.clearRect(0, 0, canvas.width, canvas.height);
                    ctx.font = 'bold 40px Arial Black'
                    text = "Acabou o nivel 2!"
                    ctx.textAlign = 'center';
                    ctx.textBaseline = 'middle';
                    ctx.fillStyle = "black";
                    ctx.fillText(text, canvas.width / 2, canvas.height / 2);

                    ctx.font = 'bold 20px Arial Black'
                    text = "Pressione Enter para continuar";
                    ctx.textAlign = 'center';
                    ctx.textBaseline = 'middle';
                    ctx.fillStyle = "black";
                    ctx.fillText(text, canvas.width / 2, 3 * canvas.height / 4);
                    if (enterKey == true) {
                        bolas.splice(0, bolas.length);
                        yGrow = 0;
                        position = false;
                        level = 3;
                        bolas.push(new Bola(raioInicial, 400, "brown", raioInicial, true));
                        bolas.push(new Bola(canvas.width / 4, 400, "brown", raioInicial, true));
                        bolas.push(new Bola(canvas.width - 2 * raioInicial, 400, "brown", raioInicial, false));
                        posX = canvas.width / 2 - sizeX;
                        tempX = posX;
                        gameover = false;
                        nxtLv = false;
                    }

                } else if (level == 3) {
                    ctx.clearRect(0, 0, canvas.width, canvas.height);
                    ctx.font = 'bold 40px Arial Black'
                    text = "Parabens!";
                    ctx.textAlign = 'center';
                    ctx.textBaseline = 'middle';
                    ctx.fillStyle = "black";
                    ctx.fillText(text, canvas.width / 2, canvas.height / 2);

                    ctx.font = 'bold 20px Arial Black'
                    text = "Pontuação " + cont;
                    ctx.textAlign = 'center';
                    ctx.textBaseline = 'middle';
                    ctx.fillStyle = "black";
                    ctx.fillText(text, canvas.width / 2, 3 * canvas.height / 4);

                    ctx.font = 'bold 20px Arial Black'
                    text = "Jogar outra vez Y/N";
                    ctx.textAlign = 'center';
                    ctx.textBaseline = 'middle';
                    ctx.fillStyle = "black";
                    ctx.fillText(text, canvas.width / 2, canvas.height / 5);
                    if (YKey == true) {
                        cont = 0;
                        bolas.splice(0, bolas.length);
                        bolas.push(new Bola(raioInicial, 400, "brown", raioInicial, true));
                        gameover = false;
                    } else if (NKey == true) {
                        posX = canvas.width / 2 - sizeX / 2;
                        tempX = posX;
                        bolas.splice(0, bolas.length);
                        start = false;
                        yGrow = 0;
                        position = false;
                        level = 1;
                        bolas.push(new Bola(raioInicial, 400, "brown", raioInicial, true));
                        NKey = false;

                    }

                }
            } else if (start == false) {
                ctx.clearRect(0, 0, canvas.width, canvas.height);
                ctx.font = 'bold 40px Arial Black'
                text = "Atomic Trouble";
                ctx.textAlign = 'center';
                ctx.textBaseline = 'middle';
                ctx.fillStyle = "black";
                ctx.fillText(text, canvas.width / 2, canvas.height / 2);

                ctx.font = 'bold 20px Arial Black'
                text = "Carregue no Enter para jogar";
                ctx.textAlign = 'center';
                ctx.textBaseline = 'middle';
                ctx.fillStyle = "black";
                ctx.fillText(text, canvas.width / 2, 3 * canvas.height / 4);
                if (enterKey == true) {
                    level = 1;
                    gameover = false;
                    start = true;
                }

            } else {
                ctx.clearRect(0, 0, canvas.width, canvas.height);
                ctx.font = 'bold 40px Arial Black'
                text = "Score = " + cont;
                ctx.textAlign = 'center';
                ctx.textBaseline = 'middle';
                ctx.fillStyle = "black";
                ctx.fillText(text, canvas.width / 2, canvas.height / 2);

                ctx.font = 'bold 20px Arial Black'
                text = "Jogar outra vez Y/N";
                ctx.textAlign = 'center';
                ctx.textBaseline = 'middle';
                ctx.fillStyle = "black";
                ctx.fillText(text, canvas.width / 2, 3 * canvas.height / 4);
                if (YKey == true) {
                    level = 1;
                    cont = 0;
                    bolas.splice(0, bolas.length);
                    bolas.push(new Bola(raioInicial, 400, "brown", raioInicial, true));
                    posX = canvas.width / 2 - sizeX / 2;
                    gameover = false;
                    YKey = false;
                } else if (NKey == true) {
                    posX = canvas.width / 2 - sizeX / 2;
                    tempX = posX;
                    bolas.splice(0, bolas.length);
                    start = false;
                    yGrow = 0;
                    position = false;
                    level = 1;
                    bolas.push(new Bola(raioInicial, 400, "brown", raioInicial, true));
                    NKey = false;
                }



            }
        }
        window.setInterval(drawIt, 10);


    }
}
