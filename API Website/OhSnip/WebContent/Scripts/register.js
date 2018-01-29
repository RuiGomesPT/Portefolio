$(document).ready(function () {

    $('#TEXT').click(function() {
        window.location.href = "http://localhost:8081/OhSnip"
    })
    
    $("#btnLogin").click(function (e) {
        e.preventDefault();
        var username = $("#username").val();
        var password = $("#password").val();
        $.ajax({
            type: "POST",
            url: "http://localhost:8081/OhSnip/api/users/",
            data: {
                username: username,
                password: password,
            },
        })
            .done(function (msg) {
                if (msg != undefined) {
                    localStorage.setItem('hashSnip', msg.hash);
                    localStorage.setItem('user_id', msg.id_user);
                    localStorage.setItem('user_level', msg.user_type);
                    window.location.href = "http://localhost:8081/OhSnip/user?u=" + msg.id_user;
                    alert("Log in successful");
                } else {
                    alert("User not found")
                }
            })
    });

    $("#btnRegist").click(function (e) {
        e.preventDefault();
        var username = $("#registUsername").val();
        var password = $("#registPassword").val();
        var email = $("#registEmail").val();
        console.log(username);
        console.log(password);
        console.log(email);
        $.ajax({
            type: "POST",
            url: "http://localhost:8081/OhSnip/api/users/register",
            data: {
                username: username,
                email: email,
                password: password
            },
        })
            .done(function (msg) {
                if (msg != undefined) {
                    alert("Sign up completed with success");
                    localStorage.setItem('hashSnip', msg.hash);
                    localStorage.setItem('user_id', msg.id_user);
                    localStorage.setItem('user_level', msg.user_type);
                    window.location.href = "http://localhost:8081/OhSnip/user?u=" + msg.id_user;
                } else {
                    alert("Error signing up");
                }
            })
    })
});