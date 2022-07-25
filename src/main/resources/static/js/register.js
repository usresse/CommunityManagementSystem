function sub() {
    /*保存数据*/
    var data = new Object();
    data.number = $("#number")[0].value;
    if ($("#password1")[0].value == $("#password2")[0].value) {
        data.password = $("#password1")[0].value;
    } else {
        alert("两次密码不一样！");
        return;
    }
    // data.mailbox = $("#mailbox")[0].value;
    data.telephone = $("#telephone")[0].value;

    /*保证数据不为空  || data.mailbox == ""  */
    if (data.number == "" || data.password == ""  || data.telephone == "") {
        alert("有参数为空！")
    } else {
        if (/[\d]{6,8}/.test(data.number)){
            if (/^.{6,8}/.test(data.password)) {
                if (/^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$/.test(data.telephone)) {
                    // if (/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(data.mailbox)) {
                        $.ajax({
                                url: "insert",
                                data: data,
                                success: function (dex) {
                                    alert(dex);
                                }
                            }
                        );
                    // }else {
                    //     alert("请输入正确邮箱的格式！");
                    // }
                }else {
                    alert("请输入正确电话号码的格式！");
                }
            }else {
                alert("请输入正确账号格式！");
            }
        }else{
            alert("请输入正确账号格式！");
        }
    }
}