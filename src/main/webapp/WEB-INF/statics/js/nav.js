/**
 * Created by 何鹏帅 on 2016/11/26.
 */
$(function () {
    $('#nav').ready(function () {
        var username = sessionStorage.getItem("username");
        if(username != null) {
            $('#login_user').html(username);
        } else if(!window.location.href.endsWith("index.html")) {
            window.location.href = "index.html";
        }
    });

    $("#user_quit").click(function () {
        sessionStorage.removeItem('username');
        $("#login_user").html("未登录");
        window.location.href = "index.html";
    });
});

function allPrpos ( obj ) {
// 用来保存所有的属性名称和值
    var props = "" ;
// 开始遍历
    for ( var p in obj ){
// 方法
        if ( typeof ( obj [ p ]) == " function " ){
            obj [ p ]() ;
        } else {
// p 为属性名称，obj[p]为对应属性的值
            props += p + " = " + obj [ p ] + " \t " ;
        }
    }
// 最后显示所有的属性
    alert ( props ) ;
}

function getUrlParam(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r!=null) return unescape(r[2]); return null; //返回参数值
}