/**
 * Created by 何鹏帅 on 2016/11/26.
 */
$(function () {
    $('#btn-login').click(function () {
        var username = $('#username').val().trim();
        var password = $('#password').val().trim();
        if(username == "") {
            bootbox.alert("请输入用户名!");
        } else if(password == "") {
            bootbox.alert("请输入密码!");
        } else {
            var params = "username=" + username + "&password=" + password;
            $.ajax({
                type: "POST",
                url:  "login",
                dataType: "JSON",
                data: params,
                success:function(data){
                    if(data['code'] == 1) {
                        sessionStorage.setItem("username", username);
                        window.location.href = "home.html";
                    } else {
                        bootbox.alert(data['message']);
                    }
                },
                error:function (data) {
                    bootbox.alert("登录失败");
                }
            });
        }
    })
});
