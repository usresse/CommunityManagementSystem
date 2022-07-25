function sum() {
    var number = $("#number")[0].value;
    var password = $("#password")[0].value;
    if (number.length == 0 || password.length == 0) {
        alert("有参数为空！")
    } else {
        if (/[\d]{6,8}/.test(number) && /^.{6,8}/.test(password)) {
            return true;
        } else {
            alert("请输入正确格式的账号和密码！")
        }
    }
    return false;
}