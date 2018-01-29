//variaveis globais da cena
var scene, cameraFollow, cameraFollowUp, planoSizeX = 100, planoSizeY = 1, planoSizeZ;
//variaveis globais do gato
var gato, cabeca, corpo, cauda, perna1, perna2, perna3, perna4, caudaGato1, caudaGato2;
//movimentos
var left, right;
//elementos
var enemy, powerUp, diamond;
//contador do movimentos do gato
var cont = 0;
//pontos
var pont = 0;
//declarao das vidas
var vidas = 3;
//flag de pausa
var pause = false;
//flag de gameover
var gameover = false;
//arrays de elementos
var arrayTarget = [];
var arrayEnemy = [];
var arrayPowerUp = [];
//contadores de controlo de enimigos
var contEnemy = 0, maxCont = 5;
//contador de controlo de powerups
var contPowerUp = 0; maxPowerUp = 300;
//velocidade
var vel = 1;
//cor do gato
var cor;
//musica
var musica;
//level
var level;
//loader de texturas
var loaderText = new THREE.TextureLoader();
//controlo de cameras
var activeCam = true;
var activeCamUP = false;

//funcao inicial
window.onload = function init() {
    //scene

    scene = new THREE.Scene();
    menu = new THREE.Scene();

    //cameras
    var camera = new THREE.PerspectiveCamera(60, window.innerWidth / window.innerHeight, 10, 200);
    var cameraUP = new THREE.PerspectiveCamera(60, window.innerWidth / window.innerHeight, 10, 200);
    // posicao de camera
    camera.position.set(0, 10, -40);
    cameraUP.position.set(0, 80, 0);
    camera.lookAt(scene.position);
    cameraUP.lookAt(scene.position);

    //renderer
    var renderer = new THREE.WebGLRenderer({ antialias: true,alpha:true });
    renderer.setSize(window.innerWidth, window.innerHeight);
    renderer.autoClear = false;
    renderer.setClearColor(0x000000, 0);

    //fundo
    //renderer.setClearColor("#add8e6");
    //canvas
    document.getElementById('canvas-container').appendChild(renderer.domElement);

    //audio
    var audio1 = new Audio('materials/audio/Reach out to the truth.mp3');
    var audio2 = new Audio('materials/audio/Sonic X - Theme Song.mp3');
    var audio3 = new Audio('materials/audio/Rivers in the Desert.mp3');
    var audio4 = new Audio('materials/audio/Initial D - Deja Vu.mp3');
    var audio5 = new Audio('materials/audio/Light the Fire up in the Night.mp3');
    var beyblade = new Audio('materials/audio/Beyblade.mp3');
    var takeDamage = new Audio('materials/audio/Taking Damage.wav');
    takeDamage.volume = 1;
    var collecting = new Audio('materials/audio/Collecting.mp3');
    collecting.volume = 0.5;
    var PUP = new Audio('materials/audio/Power Up.mp3');
    var destroyed = new Audio('materials/audio/Destroyed.wav');
    destroyed.volume = 0.3;
    var vitoria = new Audio('materials/audio/Victory.mp3');
    var derrota = new Audio('materials/audio/The Reverie.mp3');

    //flags de recomeco ou retorno
    var menu = false;
    var restart = false;

    //controlos
    document.onkeydown = function handleKeyDown(event) {
        var key = String.fromCharCode(event.keyCode);
        if (key == "A") {
            left = true;
            right = false;
        }

        if (key == "D") {
            left = false;
            right = true;
        }

        if (key == "C") {
            if (activeCam == true) {
                activeCam = false;
                activeCamUP = true;
            } else if (activeCamUP == true) {
                activeCam = true;
                activeCamUP = false;
            }
        }

        if (key == "M" && (pause == true || gameover == true)) {
            menu = true;
        }

        if (key == "R" && (pause == true || gameover == true)) {
            restart = true;
        }
        //pausa
        if (key == "P" && pause == false) {
            pause = true;
            if (localStorage.getItem("music") != null) {
                musica = localStorage.getItem("music");
            }
            if (musica == "reachout") {
                audio1.pause();
            } else if (musica == "sonic") {
                audio2.pause();
            } else if (musica == "rivers") {
                audio3.pause();
            } else if (musica == "dejavu") {
                audio4.pause();
            } else if (musica == "lightthefire") {
                audio5.pause();
            }
        } else if (key == "P" && pause == true) {
            pause = false;
            if (localStorage.getItem("music") != null) {
                musica = localStorage.getItem("music");
            }
            if (musica == "reachout") {
                audio1.play();
            } else if (musica == "sonic") {
                audio2.play();
            } else if (musica == "rivers") {
                audio3.play();
            } else if (musica == "dejavu") {
                audio4.play();
            } else if (musica == "lightthefire") {
                audio5.play();
            }
        }
    }

    //escolhe a musica atraves do localStorage
    function getSong() {
        if (localStorage.getItem("music") != null) {
            musica = localStorage.getItem("music");
        } else {
            musica = "reachout";
        }
        if (musica == "reachout") {
            audio1.play();
        } else if (musica == "sonic") {
            audio2.play();
        } else if (musica == "rivers") {
            audio3.play();
        } else if (musica == "dejavu") {
            audio4.play();
        } else if (musica == "lightthefire") {
            audio5.play();
        }

    }

    //escolhe o nivel atraves do localStorage
    function getLevel() {
        if (localStorage.getItem("level") != null) {
            level = localStorage.getItem("level");
        } else {
            level = "normal";
        }

        if (level == "easy") {
            planoSizeZ = 10000;
            vel = 2;
            maxCont = 5;
            vidas = 3;
        }

        if (level == "medium") {
            planoSizeZ = 20000;
            vel = 3;
            maxCont = 4;
            vidas = 3;
        }

        if (level == "hard") {
            planoSizeZ = 30000;
            vel = 4;
            maxCont = 3;
            vidas = 3;
        }
        if (level == "extreme") {
            planoSizeZ = 40000;
            vel = 5;
            maxCont = 2;
            vidas = 3;
        }

    }

    //adiciona luzes
    function addLight() {
        var ambLight = new THREE.AmbientLight(0xffffff);
        scene.add(ambLight);
        ambLight.name = ambLight;
        ambLight.castShadow = true;
    }

    //adiciona o plano do solo
    function addPlano() {

        //solo
        var texture = loaderText.load('materials/textures/grassTexture.jpg', function (texture) {

            texture.wrapS = THREE.RepeatWrapping;
            texture.wrapT = THREE.RepeatWrapping;
            texture.repeat.set(1, 2000);

        });
        var planoGeometry = new THREE.BoxGeometry(planoSizeX, planoSizeY, planoSizeZ);

        var planoMaterial = new THREE.MeshPhongMaterial({
            color: 0xffffff,
            specular: 0x111111,
            shininess: 10,
            map: texture,
        });



        plano = new THREE.Mesh(planoGeometry, planoMaterial);
        plano.position.z = planoSizeZ / 2;
        scene.add(plano);

        //linha de fim
        var FLtexture = loaderText.load('materials/textures/finishLineTexture.jpg', function (texture) {

            texture.wrapS = THREE.RepeatWrapping;
            texture.wrapT = THREE.RepeatWrapping;
            texture.repeat.set(1, 1);

        });

        var FLGeometry = new THREE.BoxGeometry(planoSizeX, planoSizeY, 40);

        var FLMaterial = new THREE.MeshPhongMaterial({
            color: 0xffffff,
            specular: 0x111111,
            shininess: 10,
            map: FLtexture,
        });

        finishLine = new THREE.Mesh(FLGeometry, FLMaterial);
        finishLine.position.z = planoSizeZ - 500;
        finishLine.position.y = 1.1;
        scene.add(finishLine);

    }

    //cria os diversos elementos e junta-as a um Array
    function addTarget() {


        //importacao de texturas
        var textureEnemy = loaderText.load('materials/textures/angryFace.jpg', function (texture) {
        });

        var enemyG = new THREE.SphereGeometry(1, 8, 8);

        var enemyM = new THREE.MeshPhongMaterial({
            map: textureEnemy
        });

        var texturePowerUp = loaderText.load('materials/textures/spinIt.jpg', function (texture) {
            texture.wrapS = texture.wrapT = THREE.RepeatWrapping;
            texture.repeat.set(4, 1);
        });

        var powerUpG = new THREE.SphereGeometry(1, 8, 8);

        var powerUpM = new THREE.MeshPhongMaterial({
            map: texturePowerUp
        });

        var texturePoint = loaderText.load('materials/textures/gemTexture.jpg', function (texture) {
        });

        var pointSize = 3;
        var pointG = new THREE.ConeGeometry(pointSize, pointSize, 4);
        var pointM = new THREE.MeshBasicMaterial({
            map: texturePoint
        });

        //adiciona aos arrays

        for (z = 275; z <= planoSizeZ - 775; z += 50) {
            var posX = Math.floor((Math.random() * 3) + 1);
            var x;
            if (posX == 1) {
                x = -30;
            }

            if (posX == 2) {
                x = 0;
            }

            if (posX == 3) {
                x = 30;
            }

            if (contEnemy == maxCont) {
                var posXenemy = Math.floor((Math.random() * 2) + 1);
                var xEnemy;
                if (posX == 1) {
                    if (posXenemy == 1) {
                        xEnemy = 0;
                    } else if (posXenemy == 2) {
                        xEnemy = 30;
                    }
                }

                if (posX == 2) {
                    if (posXenemy == 1) {
                        xEnemy = -30;
                    } else if (posXenemy == 2) {
                        xEnemy = 30;
                    }
                }

                if (posX == 3) {
                    if (posXenemy == 1) {
                        xEnemy = -30;
                    } else if (posXenemy == 2) {
                        xEnemy = 0;
                    }
                }

                //adiciona os enemies

                enemy = new THREE.Mesh(enemyG, enemyM);
                enemy.position.z = z;
                enemy.position.y = 8;
                enemy.position.x = xEnemy;
                enemy.rotation.y = THREE.Math.degToRad(90);
                enemy.scale.set(5, 5, 5);

                arrayEnemy.push(enemy);


                contEnemy = 0;
            }


            //adiciona o powerUp

            if (contPowerUp == maxPowerUp) {

                powerUp = new THREE.Mesh(powerUpG, powerUpM);
                powerUp.position.z = z;
                powerUp.position.y = 8;
                powerUp.position.x = x;
                powerUp.rotation.y = THREE.Math.degToRad(45);
                powerUp.scale.set(8, 8, 8);
                arrayPowerUp.push(powerUp);



                contPowerUp = 0;
            } else {

                var cone1 = new THREE.Mesh(pointG, pointM);
                var cone2 = new THREE.Mesh(pointG, pointM);


                //adiciona os diamantes

                diamond = new THREE.Object3D();
                diamond.rotation.z = THREE.Math.degToRad(180);
                diamond.add(cone1);
                diamond.add(cone2);
                cone2.rotation.z = THREE.Math.degToRad(180);
                cone1.position.y = pointSize;
                diamond.position.z = z;
                diamond.position.y = 10;
                diamond.position.x = x;
                arrayTarget.push(diamond);

            }
            //contador para os powerUps/diamantes
            contEnemy++;
            contPowerUp++;
        }


    }
    //adiciona os elementos dos arrays a cena
    function populate() {
        for (i = 0; i < arrayTarget.length; i++) {
            scene.add(arrayTarget[i]);
        }

        for (i = 0; i < arrayEnemy.length; i++) {
            scene.add(arrayEnemy[i]);
        }

        for (i = 0; i < arrayPowerUp.length; i++) {
            scene.add(arrayPowerUp[i]);
        }
    }
    //adiciona o player
    function addPlayer() {

        //declaracao dos elementos do gato
        gato = new THREE.Object3D();
        perna1 = new THREE.Object3D();
        perna2 = new THREE.Object3D();
        perna3 = new THREE.Object3D();
        perna4 = new THREE.Object3D();
        cabeca = new THREE.Object3D();
        corpo = new THREE.Object3D();
        cauda = new THREE.Object3D();


        //importe dos materiais
        var material1;
        var material2;
        var material3;

        var metalTexture = loaderText.load('materials/textures/Mecha/metalTexture.jpg', function (texture) {
        });
        var jungleTexture = loaderText.load('materials/textures/Jungle/jungleTexture.jpg', function (texture) {
        });
        var magmaTexture = loaderText.load('materials/textures/Magma/lavaTexture.png', function (texture) {
        });
        var crystalTexture = loaderText.load('materials/textures/Crystal/crystalTexture.jpg', function (texture) {
        });
        var yellowTexture = loaderText.load('materials/textures/Dio/yellowRockTexture.jpg', function (texture) {
        });


        var rubberTexture = loaderText.load('materials/textures/Mecha/rubberTexture.jpg', function (texture) {
        });
        var treeTexture = loaderText.load('materials/textures/Jungle/treeTexture.png', function (texture) {
        });
        var lavaTexture = loaderText.load('materials/textures/Magma/magmaTexture.jpg', function (texture) {
        });
        var spiralTexture = loaderText.load('materials/textures/Crystal/spiralTexture.jpg', function (texture) {
        });
        var gemstoneTexture = loaderText.load('materials/textures/Dio/gemstoneTexture.jpg', function (texture) {
        });

        var redLazerTexture = loaderText.load('materials/textures/Mecha/LazerTexture.jpg', function (texture) {
        });
        var blueLazerTexture = loaderText.load('materials/textures/Jungle/blueLazerTexture.jpg', function (texture) {
        });
        var greenLazerTexture = loaderText.load('materials/textures/Magma/greenLazerTexture.jpg', function (texture) {
        });
        var yellowLazerTexture = loaderText.load('materials/textures/Crystal/yellowLazerTexture.jpg', function (texture) {
        });
        var purpleLazerTexture = loaderText.load('materials/textures/Dio/purpleLazerTexture.jpg', function (texture) {
        });
        //escolha da cor atraves de local storage
        if (localStorage.getItem("color") != null) {

            cor = localStorage.getItem("color");


            if (cor == "mecha") {
                material1 = new THREE.MeshPhongMaterial({ map: metalTexture });
                material2 = new THREE.MeshPhongMaterial({ map: rubberTexture });
                material3 = new THREE.MeshPhongMaterial({ map: redLazerTexture });
            } else if (cor == "jungle") {
                material1 = new THREE.MeshPhongMaterial({ map: treeTexture });
                material2 = new THREE.MeshPhongMaterial({ map: jungleTexture });
                material3 = new THREE.MeshPhongMaterial({ map: blueLazerTexture });
            } else if (cor == "magma") {
                material1 = new THREE.MeshPhongMaterial({ map: magmaTexture });
                material2 = new THREE.MeshPhongMaterial({ map: lavaTexture });
                material3 = new THREE.MeshPhongMaterial({ map: greenLazerTexture });
            } else if (cor == "crystal") {
                material1 = new THREE.MeshPhongMaterial({ map: crystalTexture });
                material2 = new THREE.MeshPhongMaterial({ map: spiralTexture });
                material3 = new THREE.MeshPhongMaterial({ map: yellowLazerTexture });
            } else if (cor == "dio") {
                material1 = new THREE.MeshPhongMaterial({ map: yellowTexture });
                material2 = new THREE.MeshPhongMaterial({ map: gemstoneTexture });
                material3 = new THREE.MeshPhongMaterial({ map: purpleLazerTexture });
            }

        } else {
            material1 = new THREE.MeshPhongMaterial({ map: metalTexture });
            material2 = new THREE.MeshPhongMaterial({ map: rubberTexture });
            material3 = new THREE.MeshPhongMaterial({ map: redLazerTexture });
        }

        // desenha o gato
        var geomCorpo = new THREE.CylinderGeometry(2.4, 2.4, 10, 32);
        var corpoGato = new THREE.Mesh(geomCorpo, material1);

        corpoGato.rotation.z = THREE.Math.degToRad(90);

        corpo.add(corpoGato);

        var geomCauda = new THREE.CylinderGeometry(0.5, 0.5, 5, 32);
        var caudaGato1 = new THREE.Mesh(geomCauda, material2);

        caudaGato1.rotation.z = THREE.Math.degToRad(45);
        caudaGato1.position.y = 2;
        caudaGato1.position.x = -5;

        var geomCauda2 = new THREE.CylinderGeometry(0.5, 0.5, 3, 32);
        caudaGato2 = new THREE.Mesh(geomCauda2, material2);

        caudaGato2.rotation.z = THREE.Math.degToRad(-45);
        caudaGato2.position.y = 5;
        caudaGato2.position.x = -5.5;

        cauda.add(caudaGato1);
        cauda.add(caudaGato2);


        var geomPescoco = new THREE.CylinderGeometry(1, 1, 5, 32);
        var pescocoGato = new THREE.Mesh(geomPescoco, material2);

        pescocoGato.rotation.z = THREE.Math.degToRad(-45);
        pescocoGato.position.y = 1;
        pescocoGato.position.x = 5;

        var geomCabeca = new THREE.SphereGeometry(2, 32, 32);
        var cabecaGato = new THREE.Mesh(geomCabeca, material1);

        cabecaGato.rotation.z = THREE.Math.degToRad(45);
        cabecaGato.position.y = 2;
        cabecaGato.position.x = 7;

        var geomOrelha = new THREE.ConeGeometry(0.5, 2, 32);
        var orelhaGato1 = new THREE.Mesh(geomOrelha, material2);

        orelhaGato1.position.x = 7;
        orelhaGato1.position.y = 4.5;
        orelhaGato1.position.z = 0.8;


        var orelhaGato2 = new THREE.Mesh(geomOrelha, material2);

        orelhaGato2.position.x = 7;
        orelhaGato2.position.y = 4.5;
        orelhaGato2.position.z = -0.8;


        var geomFocinho = new THREE.CylinderGeometry(0.5, 1, 1, 32);
        var focinhoGato = new THREE.Mesh(geomFocinho, material2);

        focinhoGato.rotation.z = THREE.Math.degToRad(270);
        focinhoGato.position.y = 2;
        focinhoGato.position.x = 9;

        var geomOlho = new THREE.SphereGeometry(0.5, 32, 32);
        var olhoGato1 = new THREE.Mesh(geomOlho, material3);

        olhoGato1.position.y = 3;
        olhoGato1.position.x = 8;
        olhoGato1.position.z = -0.8;

        var olhoGato2 = new THREE.Mesh(geomOlho, material3);

        olhoGato2.position.y = 3;
        olhoGato2.position.x = 8;
        olhoGato2.position.z = 0.8;

        cabeca.add(cabecaGato);
        cabeca.add(pescocoGato);
        cabeca.add(orelhaGato1);
        cabeca.add(orelhaGato2);
        cabeca.add(focinhoGato);
        cabeca.add(olhoGato1);
        cabeca.add(olhoGato2);

        var geomPerna = new THREE.CylinderGeometry(0.5, 0.5, 2, 32);
        var pernaGato1 = new THREE.Mesh(geomPerna, material2);
        var pernaGato2 = new THREE.Mesh(geomPerna, material2);
        var pernaGato3 = new THREE.Mesh(geomPerna, material2);
        var pernaGato4 = new THREE.Mesh(geomPerna, material2);
        var pernaGato5 = new THREE.Mesh(geomPerna, material1);
        var pernaGato6 = new THREE.Mesh(geomPerna, material1);
        var pernaGato7 = new THREE.Mesh(geomPerna, material1);
        var pernaGato8 = new THREE.Mesh(geomPerna, material1);

        pernaGato1.rotation.z = THREE.Math.degToRad(0);
        pernaGato1.position.y = -2;
        pernaGato1.position.x = 3;
        pernaGato1.position.z = 1.5;

        pernaGato2.rotation.z = THREE.Math.degToRad(0);
        pernaGato2.position.y = -2;
        pernaGato2.position.x = 3;
        pernaGato2.position.z = -1.5;

        pernaGato3.rotation.z = THREE.Math.degToRad(30);
        pernaGato3.position.y = -2;
        pernaGato3.position.x = -3;
        pernaGato3.position.z = 1.5;

        pernaGato4.rotation.z = THREE.Math.degToRad(30);
        pernaGato4.position.y = -2;
        pernaGato4.position.x = -3;
        pernaGato4.position.z = -1.5;

        pernaGato5.rotation.z = THREE.Math.degToRad(-30);
        pernaGato5.position.y = -4;
        pernaGato5.position.x = 2.5;
        pernaGato5.position.z = 1.5;

        pernaGato6.rotation.z = THREE.Math.degToRad(-30);
        pernaGato6.position.y = -4;
        pernaGato6.position.x = 2.5;
        pernaGato6.position.z = -1.5;

        pernaGato7.rotation.z = THREE.Math.degToRad(-30);
        pernaGato7.position.y = -4;
        pernaGato7.position.x = -2.5;
        pernaGato7.position.z = 1.5;

        pernaGato8.rotation.z = THREE.Math.degToRad(-30);
        pernaGato8.position.y = -4;
        pernaGato8.position.x = -2.5;
        pernaGato8.position.z = -1.5;

        perna1.add(pernaGato1);
        perna1.add(pernaGato5);
        perna2.add(pernaGato2);
        perna2.add(pernaGato6);
        perna3.add(pernaGato3);
        perna3.add(pernaGato7);
        perna4.add(pernaGato4);
        perna4.add(pernaGato8);



        gato.add(corpo);
        gato.add(cauda);
        gato.add(perna1);
        gato.add(perna2);
        gato.add(perna3);
        gato.add(perna4);
        gato.add(cabeca);
        scene.add(gato);
        scene.add(camera);


        var cameraFollowG = new THREE.BoxGeometry(1, 1, 1);

        var cameraFollowM = new THREE.MeshPhongMaterial({
            opacity: 0.0,
            transparent: true,
            visible: false
        });

        cameraFollow = new THREE.Mesh(cameraFollowG, cameraFollowM);
        cameraFollow.position.z = 60;
        cameraFollow.position.y = 10;
        cameraFollow.add(camera);
        scene.add(cameraFollow);

        cameraFollowUP = new THREE.Mesh(cameraFollowG, cameraFollowM);
        cameraFollowUP.position.z = 110;
        cameraFollowUP.position.y = 60;
        cameraFollowUP.add(cameraUP);
        cameraFollowUP.rotation.y = THREE.Math.degToRad(180);

        scene.add(cameraFollowUP);

        gato.position.z = 50;
        gato.position.y = 5;
        gato.rotation.y = THREE.Math.degToRad(270)

    }

    function background() {
        
    }

    //funcoes
    getSong();
    getLevel();
    addPlano();
    addPlayer();
    addLight();
    addTarget();
    populate();

    var animeEnemy = false;
    var contaSalto = 0;
    //declarao do tempo de powerup
    var contTimer = 0;
    //flag de movimento
    var move = true;
    //flag de powerUp
    var spin = false;
    //render
    var render = function () {

        //verifica o gameover
        if (gameover == false) {
            //verifica a pausa
            if (pause == false) {
                //deteta colisoes com os diamantes
                var playerCol = new THREE.Box3().setFromObject(gato);
                for (i = 0; i < arrayTarget.length; i++) {
                    var boxCol = new THREE.Box3().setFromObject(arrayTarget[i]);
                    if (playerCol.intersectsBox(boxCol)) {
                        scene.remove(arrayTarget[i]);
                        arrayTarget.splice(i, 1);
                        pont++;
                        collecting.currentTime = 0;
                        collecting.play();
                    }

                }
                //deteta colisoes com os enimigos
                for (i = 0; i < arrayEnemy.length; i++) {
                    var enemyCol = new THREE.Box3().setFromObject(arrayEnemy[i]);
                    if (playerCol.intersectsBox(enemyCol)) {
                        if (spin == true) {
                            scene.remove(arrayEnemy[i]);
                            arrayEnemy.splice(i, 1);
                            pont += 3;
                            destroyed.currentTime = 0;
                            destroyed.play();
                        } else {
                            scene.remove(arrayEnemy[i]);
                            arrayEnemy.splice(i, 1);
                            vidas--;
                            takeDamage.currentTime = 0;
                            takeDamage.play();
                        }

                    }
                }
                //deteta colisoes com os powerUps
                for (i = 0; i < arrayPowerUp.length; i++) {
                    var powerUpCol = new THREE.Box3().setFromObject(arrayPowerUp[i]);
                    if (playerCol.intersectsBox(powerUpCol)) {
                        scene.remove(arrayPowerUp[i]);
                        arrayPowerUp.splice(i, 1);
                        pont++;
                        spin = true;
                        PUP.play();
                    }

                }


                //detecao e inicio de powerup
                if (spin == true) {
                    gato.rotation.y += 360;
                    contTimer++;
                    beyblade.play();
                    if (musica == "reachout") {
                        audio1.pause();
                    } else if (musica == "sonic") {
                        audio2.pause();
                    } else if (musica == "rivers") {
                        audio3.pause();
                    } else if (musica == "dejavu") {
                        audio4.pause();
                    } else if (musica == "lightthefire") {
                        audio5.pause();
                    }
                    //fim de powerup
                    if (contTimer == 1500) {
                        gato.rotation.y = THREE.Math.degToRad(270)
                        spin = false;
                        beyblade.pause();
                        if (musica == "reachout") {
                            audio1.play();
                        } else if (musica == "sonic") {
                            audio2.play();
                        } else if (musica == "rivers") {
                            audio3.play();
                        } else if (musica == "dejavu") {
                            audio4.play();
                        } else if (musica == "lightthefire") {
                            audio5.play();
                        }
                        contTimer = 0;

                    }
                }
                //rotacao dos diamantes
                for (i = 0; i < arrayTarget.length; i++) {
                    arrayTarget[i].rotation.y += THREE.Math.degToRad(15);
                }

                for (i = 0; i < arrayPowerUp.length; i++) {
                    arrayPowerUp[i].rotation.y += THREE.Math.degToRad(15);
                }

                if (animeEnemy == true) {
                    if (contaSalto > 20) {
                        animeEnemy = false;
                    }

                    for (i = 0; i < arrayEnemy.length; i++) {
                        arrayEnemy[i].position.y += 0.2;
                    }

                    contaSalto++;

                } else if (animeEnemy == false) {
                    if (contaSalto < 0) {
                        animeEnemy = true;
                    }

                    for (i = 0; i < arrayEnemy.length; i++) {
                        arrayEnemy[i].position.y -= 0.2;
                    }

                    contaSalto--;

                }

                //animacao do gato
                if (move == true) {
                    perna1.rotation.z += 0.08;
                    perna2.rotation.z += 0.08;
                    perna3.rotation.z -= 0.08;
                    perna4.rotation.z -= 0.08;
                    cabeca.rotation.z += 0.02;
                    corpo.rotation.z += 0.01;
                    cauda.rotation.z -= 0.01;
                    caudaGato2.rotation.z -= 0.05;
                    if (cont >= 6) {
                        move = false;
                    }
                    cont++;
                } else if (move == false) {
                    perna1.rotation.z -= 0.08;
                    perna2.rotation.z -= 0.08;
                    perna3.rotation.z += 0.08;
                    perna4.rotation.z += 0.08;
                    cabeca.rotation.z -= 0.02;
                    corpo.rotation.z -= 0.01;
                    cauda.rotation.z += 0.01;
                    caudaGato2.rotation.z += 0.05;
                    if (cont <= -6) {
                        move = true;
                    }
                    cont--;
                }
                //movimento do gato no plano
                if (gato != undefined) {
                    gato.position.z += vel;
                    if (gato.position.z >= planoSizeZ - 600) {
                        if (cameraFollow.position.y <= 40) {
                            cameraFollow.position.y += vel;
                            cameraFollowUP.position.y += vel;
                        }
                    } else {
                        cameraFollow.position.z += vel;
                        cameraFollowUP.position.z += vel;
                    }
                }
                //movimento do gato na horizontal
                if (left == true && gato.position.x < 30) {
                    gato.position.x += 30;
                    left = false;
                }

                if (right == true && gato.position.x > -30) {
                    gato.position.x -= 30;
                    right = false
                }
                //detecao de vidas
                if (vidas <= 0) {
                    gameover = true;
                    if (musica == "reachout") {
                        audio1.pause();
                    } else if (musica == "sonic") {
                        audio2.pause();
                    } else if (musica == "rivers") {
                        audio3.pause();
                    } else if (musica == "dejavu") {
                        audio4.pause();
                    } else if (musica == "lightthefire") {
                        audio5.pause();
                    }

                    derrota.play();
                }
                //musica de vitoria
                if (gato.position.z >= planoSizeZ - 500) {
                    if (spin == false) {
                        if (musica == "reachout") {
                            audio1.pause();
                        } else if (musica == "sonic") {
                            audio2.pause();
                        } else if (musica == "rivers") {
                            audio3.pause();
                        } else if (musica == "dejavu") {
                            audio4.pause();
                        } else if (musica == "lightthefire") {
                            audio5.pause();
                        }
                    }
                    else {
                        beyblade.pause();
                    }

                    vitoria.play();


                }
                //detecao de vitoria
                if (gato.position.z >= planoSizeZ - 250) {
                    gameover = true;

                }
                //elementos do HUD
                document.getElementById("main").innerHTML = "";
                document.getElementById("sub1").innerHTML = "";
                document.getElementById("sub2").innerHTML = "";
                document.getElementById("score").innerHTML = "Score: " + pont;
                document.getElementById("vidas").innerHTML = "Vidas: " + vidas;
            } else if (pause == true) {
                //menu Pausa
                document.getElementById("main").innerHTML = "PAUSA";
                document.getElementById("sub1").innerHTML = "R - Recomeçar";
                document.getElementById("sub2").innerHTML = "M - Voltar ao Menu";
                if (menu == true) {
                    window.open("./Menu.html", "_self");
                }

                if (restart == true) {
                    window.open("./Projeto.html", "_self");
                }

            }
            //menu Gameover
        } else if (gameover == true) {
            var rank = "F";
            //definicao de ranks
            if (level == "easy") {
                if (pont >= 180) {
                    rank = "S+";
                } else if (pont >= 170) {
                    rank = "S";
                } else if (pont >= 160) {
                    rank = "A";
                } else if (pont >= 130) {
                    rank = "B";
                } else if (pont >= 90) {
                    rank = "C";
                } else if (pont >= 50) {
                    rank = "D";
                } else {
                    rank = "F";
                }
            } else if (level == "medium") {
                if (pont >= 180) {
                    rank = "S+";
                } else if (pont >= 420) {
                    rank = "S";
                } else if (pont >= 400) {
                    rank = "A";
                } else if (pont >= 300) {
                    rank = "B";
                } else if (pont >= 210) {
                    rank = "C";
                } else if (pont >= 100) {
                    rank = "D";
                } else {
                    rank = "F";
                }
            } else if (level == "hard") {
                if (pont >= 520) {
                    rank = "S+";
                } else if (pont >= 500) {
                    rank = "S";
                } else if (pont >= 480) {
                    rank = "A";
                } else if (pont >= 360) {
                    rank = "B";
                } else if (pont >= 240) {
                    rank = "C";
                } else if (pont >= 120) {
                    rank = "D";
                } else {
                    rank = "F";
                }
            } else {
                if (pont >= 820) {
                    rank = "S+";
                } else if (pont >= 800) {
                    rank = "S";
                } else if (pont >= 700) {
                    rank = "A";
                } else if (pont >= 600) {
                    rank = "B";
                } else if (pont >= 400) {
                    rank = "C";
                } else if (pont >= 200) {
                    rank = "D";
                } else {
                    rank = "F";
                }
            }
            //menu GameOver
            document.getElementById("main").innerHTML = "GAME";
            document.getElementById("sub").innerHTML = "OVER";
            document.getElementById("rank").innerHTML = "RANK: " + rank;
            document.getElementById("sub1").innerHTML = "R - Recomeçar";
            document.getElementById("sub2").innerHTML = "M - Voltar ao Menu";
            if (menu == true) {
                window.open("./Menu.html", "_self");
            }

            if (restart == true) {
                window.open("./Projeto.html", "_self");
            }
        }
        //renderizacao da camera
        renderer.clear();
        if (activeCam == true) {
            renderer.render(scene, camera);
        } else if (activeCamUP == true) {
            renderer.render(scene, cameraUP);
        }

        requestAnimationFrame(render);
    }
    //chamamento de funcao de renderizar

    render();

};
