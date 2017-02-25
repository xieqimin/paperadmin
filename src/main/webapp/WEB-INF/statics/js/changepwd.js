/**
 * Created by 何鹏帅 on 2016/12/30.
 */
$(function () {
    $('#btn-certain').click(function () {
        var oldpwd = $('#old-pwd').val().trim();
        var newpwd = $('#new-pwd').val().trim();
        var certainpwd = $('#certain-pwd').val().trim();

        if(newpwd.length < 6) {
            bootbox.alert("密码长度至少为6位");
            return;
        } else if(newpwd != certainpwd) {
            bootbox.alert("输入的密码不一致");
            return;
        }

        var param = "username=" + sessionStorage.getItem("username") + "&password=" + oldpwd + "&newpassword=" + newpwd;
        $.ajax({
            type: "POST",
            url:  "change_password",
            dataType: "JSON",
            data: param,
            success:function(data){
                if(data['code'] == 0) {
                    bootbox.alert(data['message']);
                } else {
                    bootbox.alert(data['message'], function () {
                        sessionStorage.removeItem("username");
                        window.location.href = "index.html";
                    });
                }
            },
            error:function () {
                bootbox.alert("密码修改失败");
            }
        });
    });

    $('#btn-cancel').click(function () {
        window.location.href = "home.html";
    });
});