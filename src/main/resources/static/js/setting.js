/*编辑按钮的标签转换操作*/
function tiaozhaun(boot) {
    let div = $("div[class]");
    let input = $("input[class],select[class]");
    /**判断是否重复点击*/
    if (div.eq(1).is(':hidden')) {
        return;
    }

    for (let i = 0; i < div.length; i++) {
        let value = div[i].innerText;
        if (i == 3) {
            if (value == "男") {
                $("select>option[value=1]").attr("selected", true);
            } else {
                $("select>option[value=0]").attr("selected", true);
            }
        } else {
            input.eq(i).val(value);
        }

        /**对学生专业的单选框初始选项的而处理*/
        if (i == 7) {
            let major = $("select[class='schoolMajor']>option");
            for (let i = 0; i < major.length; i++) {
                if (major[i].innerText == value) {
                    major[i].setAttribute("selected", true);
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

/*确定按钮的提交数据操作*/
function sub() {
    let input = $("input[class],select[class]");
    let data = {};
    let number = window.parent.document.getElementsByClassName("quan")[0].innerHTML;

    for (let i = 0; i < input.length; i++) {
        let NameClass = input.eq(i).attr("class");
        let value = input.eq(i).val();
        if (i == 8) {
            let reg_phone = /^(13[0-9]|14[01456879]|15[0-3,5-9]|16[2567]|17[0-8]|18[0-9]|19[0-3,5-9])\d{8}$/;
            if (!reg_phone.test(value)) {
                alert("电话号码格式不正确！");
                return;
            }
        }
        data[NameClass] = value;
    }
    data["number"] = number;
    /*构建ajax向settingInsert传送数据*/
    $.ajax({
        url: "settingInsert",
        type: "post",
        data: data,
        success: function (dex) {
            alert(dex);
            if (dex == "保存数据成功！") {
                /**刷新本页面*/
                location.reload();
            }
        }
    });
}


/**对于上传图片的效果操作*********************************************************************************************************/
/*绑定监听器事件*/
$(function () {
    /*选中照片后上传照片到展示区*/
    function getFile(file) {
        if (!file) {
            return;
        }
        /*const声明的常量必须初始化
        const 定义常量的值不能通过再赋值修改，也不能再次声明==》只能赋值一次
        const {files}指向的是内部的files属性提取出来成为files常量名
        */
        const {files} = file.target;

        if (files.length <= 0) return;

        const fileReader = new FileReader();
        /*因为可以上传多个图片所以要加【0】取出值*/
        fileReader.readAsDataURL(files[0]);

        /*function (event) ==>也可以使用*/
        fileReader.onload = (event) => {
            const {result} = event.target;
            $("#myImage").attr("src", result);
        };
    }

    $(".file").on("change", getFile);
});
/***********************************************************************************************************/
/**性别和日期的解析    所有专业单选框的创建插入*/
$(function () {
    /**性别的数据解析*/
    let sex = $(".sex")[0].innerText;
    if (sex == '0') {
        $(".sex")[0].innerText = "女";
    } else {
        $(".sex")[0].innerText = "男";
    }

    /**生日日期格式化*/
    let dateOfBirth = $(".dateOfBirth")[0].innerText;
    if (dateOfBirth == null || dateOfBirth == "" || dateOfBirth == "null") {

    } else {
        $(".dateOfBirth")[0].innerText = dateFormat(dateOfBirth, 'yyyy-MM-dd');
    }

    /**所有专业单选框的创建插入*/
    let major = $("div[class='schoolMajor']")[0].innerText;
    console.log(major);
    let all = $("#schoolMajorAll")[0].innerText;
    let select = $("select[class='schoolMajor']")
    all = all.toString().substring(2, all.toString().length - 2);
    all = all.split("}, {");
    for (let i = 0; i < all.length; i++) {
        let arry = all[i].split(",");
        /**插入value值为专业编号*/
        let option = $("<option></option>").val(arry[0].split("=")[1]);
        option[0].innerText = arry[1].split("=")[1];
        select.append(option);
        /**对第一次查询显示的专业进行编译*/
        if (major == arry[0].split("=")[1]) {
            $("div[class='schoolMajor']")[0].innerText = arry[1].split("=")[1];
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