var personalBean = parent.window.personalBean;

var schoolMajor;

/**性别和日期的解析    所有专业单选框的创建插入*/
$(function () {
    for (let key in personalBean) {
        let _name = "div[class='" + key + "']";
        let value = personalBean[key];

        if (key == "sex" && value == null) {
            $(".sex > option").eq(0).show();
            $(".sex").eq(1).change(selected);
        }

        if (value != null) {
            switch (key) {
                //学号
                case "studentNumber":
                //姓名
                case "name":
                //账号
                case "number":
                //密码
                case "password":
                //练习电话
                case "telephone":
                //邮箱
                case "mailbox":
                //学校专业
                case "schoolMajor":
                //账号昵称
                case "nickName":
                    $(_name).text(value)
                    break;
                //性别
                case "sex":
                    if (value == '0') {
                        $(_name).text("女");
                    } else {
                        $(_name).text("男");
                    }
                    break;
                //出生日期
                case "dateOfBirth":
                    let dateOfBirth = personalBean["dateOfBirth"];
                    if (dateOfBirth == null || dateOfBirth == "" || dateOfBirth == "null") {

                    } else {
                        $(".dateOfBirth").eq(0).text(dateFormat(dateOfBirth, 'yyyy-MM-dd'))
                    }
                    break;
                //社团职位
                case "major":
                //社团ID
                case "associationNumber":
                    $.ajax({
                        url:key,
                        data:personalBean,
                        type:"get",
                        success: function (data){
                            $("#"+key+"").text(data);
                        }
                    })
                    break;
            }
        }
    }

    $.ajax({
        url: "schoolMajor",
        type: "get",
        async: false,
        success: function (data) {
            schoolMajor = data;
        }
    });

    for (let i = 0; i < schoolMajor.length; i++) {
        let arry = schoolMajor[i];

        /**插入value值为专业编号*/
        let option = $("<option></option>").val(arry["schoolmajorID"]);
        /**添加选项的内容*/
        option.text(arry["schoolmajorName"]);
        /**将选项添加到页面*/
        $(".selectSchoolMajor").append(option);

        /**对第一次查询显示的专业进行编译*/
        if ($(".schoolMajor").text() == arry["schoolmajorID"]) {
            $(".schoolMajor").text(arry["schoolmajorName"]);
        }
    }

});


/**小眼睛显示密码*/
function imgclick(img) {
    $("img[class='img']").show();
    let type = $("input[class='password']").attr("type");
    if (type == "text") {
        $("input[class='password']").attr("type", "password");
    } else {
        $("input[class='password']").attr("type", "text");
    }
    $(img).hide();
}

/**处理当性别为空时更改空选项*/
function selected() {
    $('.sex').eq(1).find("option").eq(0).attr('style', 'display:none')
}

/**编辑按钮的标签转换操作*/
function tiaozhaun() {
    $("button[disabled]").attr("disabled", false);

    let div = $("div[class]");
    let input = $("input[class],select[class]");

    /**判断是否重复点击*/
    if (div.eq(1).is(':hidden')) {
        return;
    }

    for (let i = 0; i < div.length; i++) {
        let value = div.eq(i).text();

        if (i == 3) {
            if (value == "男") {
                $(".sex").eq(1).find("option").eq(1).attr("selected", true);
            } else if (value == "女") {
                $(".sex").eq(1).find("option").eq(2).attr("selected", true);
            }
        } else {
            input.eq(i).val(value);
        }
        /**对学生专业的单选框初始选项的而处理*/
        if (i == 7) {
            let major = $(".selectSchoolMajor>option");
            for (let i = 0; i < major.length; i++) {
                if (major.eq(i).text() == value) {
                    major.eq(i).attr("selected", true);
                }
            }
        }

    }

    $("text[class='password']").hide();
    div.hide();
    input.show();
    /**按钮显示*/
    imgclick($("img[class='img']")[0]);
}

/**确定按钮的提交数据操作*/
function sub() {
    let input = $("input[class],select[class]");
    let data = {};

    for (let i = 0; i < input.length; i++) {
        let NameClass = input.eq(i).attr("class");
        let value = input.eq(i).val();

        /**当数据为空时设置为null值*/
        if (value == "") {
            value = null;
        }

        /**判断性别的选框设置*/
        if (i == 3) {
            value = input.eq(i).find("option:selected").val();
        }

        /**对时间的格式化*/
        if (i == 5) {
            value = dateFormat(new Date(value), 'yyyy-MM-dd');
        }

        /**对学校专业的选框设置*/
        if (i == 7) {
            NameClass = "schoolMajor";
            value = input.eq(i).find("option:selected").val();
        }

        /**对电话号码的值限制*/
        if (i == 8) {
            let reg_phone = /^(13[0-9]|14[01456879]|15[0-3,5-9]|16[2567]|17[0-8]|18[0-9]|19[0-3,5-9])\d{8}$/;
            if (!reg_phone.test(value)) {
                alert("电话号码格式不正确！");
                return;
            }
        }

        /**创建Objeat对象的属性*/
        data[NameClass] = value;
    }

    /*构建ajax向settingInsert传送数据*/
    $.ajax({
        url: "settingInsert",
        type: "post",
        data: data,
        success: function (dex) {
            alert(dex);
            if (dex == "修改成功!") {
                /**刷新本页面*/
                window.location.reload();
            }
        }
    });
}