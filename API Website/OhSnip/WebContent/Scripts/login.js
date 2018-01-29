$(document).ready(function () {
    if (localStorage.getItem('hashSnip') != undefined && localStorage.getItem('hashSnip') != null) {
        $("#dpBt").html('Profile');
        $("#myDropdown").empty();
        $("#myDropdown").append('<br><input id="btnPerfil" type="button" class="btn btn-block profile" value="Profile"><br><input id="btnLogout" type="button" class="btn btn-block logout" value="Log out"><br>')
    } else {
        $("#dpBt").html('Log In');
    }

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
                    alert("Log in successful")
                    window.location.reload();
                } else {
                    alert("User not found");
                }
            })
    });

    $('body').on('click', '#btnRegisto', function () {
        window.location.href = "http://localhost:8081/OhSnip/register";
    });

    $('body').on('click', 'input.profile', function () {
        window.location.href = "http://localhost:8081/OhSnip/user?u=" + localStorage.getItem('user_id');
    });

    $('body').on('click', 'input.logout', function () {
        localStorage.removeItem('hashSnip');
        localStorage.removeItem('user_id');
        localStorage.removeItem('user_level');
        window.location.reload()
    });
});