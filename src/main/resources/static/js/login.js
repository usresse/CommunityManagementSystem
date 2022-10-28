$(function (){
    /**获取cookie数据加入登录页面*/
    if($.cookie("UserJudge") == "false"){
        $(".jinggao").show();
    }else {
        $(".jinggao").hide();
    }
})

function sum() {
    var number = $("#number").val();
    var password = $("#password").val();
    if (number.length == 0 || password.length == 0) {
        alert("有参数为空！")
    } else {
        if (/[\d]{6,8}/.test(number) && /^.{6,8}/.test(password)) {
            /**设置cookie登录值*/
            $.cookie("login","true");
            return true;
        } else {
            alert("请输入正确格式的账号和密码！")
        }
    }
    return false;
}