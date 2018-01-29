$(document).ready(function () {
    var audio = new Audio('materials/audio/A Corner of Memories.mp3');
    var play = true;
    audio.play();


    $("#btnNewGame").click(function () {
        var levelSelect = document.getElementById("level");
        var level = levelSelect.options[levelSelect.selectedIndex].value;

        var colorSelect = document.getElementById("color");
        var color = colorSelect.options[colorSelect.selectedIndex].value;

        var musicSelect = document.getElementById("music");
        var music = musicSelect.options[musicSelect.selectedIndex].value;


        console.log(level, color, music);
        localStorage.setItem("level", level);
        localStorage.setItem("color", color);
        localStorage.setItem("music", music);

        window.open("./Projeto.html", "_self");
    });

    $("#btnMute").click(function () {
        if (play == true) {
            audio.pause();
            play = false;
        } else if (play == false) {
            audio.currentTime = 0
            audio.play();
            play = true;
        }
    });
});