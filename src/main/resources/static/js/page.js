var personalBean = {};

$(function () {
    // /**获取数据*/
    _data = {};
    _data.number = $.cookie("number");
    $.ajax({
        url: "get/personal_number",
        data: _data,
        type: "get",
        async: false,
        success: function (data) {
            personalBean = data;
        }
    });

    /**获取数据并转化为对象*/
    $(".user").text(personalBean["nickName"]);
    $(".quan").text(personalBean["number"]);

    /**照片的获取效果，如果为空则没有*/
    let number = personalBean["number"];
    let bold = personalBean["bold"];
    let url;
    if (bold == "" || bold == null || bold == "null") {
        url = "img/img.png";
    } else {
        url = "img/" + number + "/" + bold;
    }
    $(".img>img").attr("src", url);

    /**往表单中添加number属性值*/
    $(".num").val(number);

    /**用户昵称的更改*/
    let name = $(".user").text();
    if (name == null || name == "") {
        $(".user")[0].innerText = number;
    }

    /*********图片更换代码*****************************************************************************/
    //通过点击img时，触发上传文本框；dblclick()双击事件
    $(".img>img").dblclick(function () {
        $(".file").click();
    });

    let regexImageFilter = /^(?:image\/bmp|image\/gif|image\/jpg|image\/jpeg|image\/png)$/i;
    let imgReader = new FileReader();
    //文件读取器读取到文件后的回调事件
    imgReader.onload = function (event) {
        //显示图片 base64编码的图片
        $(".img>img").attr("src", event.target.result);
    }

    $(".file").on('change', function () {
        //获取出文件选择器中的第一个文件
        var file = $(".file").get(0).files[0];
        if (regexImageFilter.test(file.type)) {
            //读取文件转换成URL把图片转为Base64编码
            imgReader.readAsDataURL(file);

            $("#form").submit();
        } else {
            layer.alert("请选择图片");
        }
    });
});

/******************菜单**************************/
/**更改函数数据*/
$(function () {
    /**对成员隐藏审核选项*/
    let data = personalBean["major"];


    /**成员菜单*/
    if (data == "3") {
        $(".ti3").hide();
        $(".tem2 > div > div").eq(0).text("其他成员");
        $(".tem2").append($("<div><div onclick=JumpHtml(this,'notice')>社团通知</div></div>"));
    }

    /**社长菜单*/
    if (data == "1") {
        $(".tem3").prepend($("<div></div>").append($(".tem2 > div > div").eq(0).text("社团成员管理")));
        /**添加社团信息维护*/
        $(".tem3").append($("<div><div onclick=JumpHtml(this,'CommunityInformationMaintenance')>社团信息维护</div></div>"))
    }

    if (data == "" || data == null || data.length == 0) {
        $(".ti3").hide();
        /**隐藏参加的社团活动申请和历史*/
        $(".tem2").find("div").eq(2).hide()
        $(".tem2").find("div").eq(3).hide();
    } else {
        $(".tem3").append($("<div><div onclick=JumpHtml(this,'notice')>社团通知</div></div>"));
    }

    /**对社团申请的菜单隐藏*/
    if (data != null && data != "") {
        $(".nul").hide();
    }
});


/**菜单框效果实现*/
function toggleshow(module, cla) {
    /**添加小导航数据*/
    $(".cai > div[style]").eq(0).show();

    $(".cai > div[style]").eq(3).hide();
    $(".caiNei").eq(1).text($(module).text());
    $(".caiNei").eq(2).text("");

    $(".stair2").hide();
    $("." + cla).show();
};

/**设置点击事件页面跳转*/
function JumpHtml(module, url) {
    /**添加小导航数据*/
    if (url == "announcement" || url == "setting") {
        $(".cai > div[style]").eq(0).show();
        $(".cai > div[style]").eq(1).hide();
        $(".caiNei").eq(1).text($(module).text());
        $(".caiNei").eq(2).text("");
    } else {
        $(".cai > div[style]").show();
        $(".caiNei").eq(1).text($(module).parents(".stair2").prev().text());
        $(".caiNei").eq(2).text($(module).text());
    }

    $("#nei").attr("src", url);
}

function JumpHtmlAndNumber(module, url) {
    /**添加小导航数据*/
    $(".cai > div[style]").show();
    $(".caiNei").eq(1).text($(module).parents(".stair2").prev().text());
    $(".caiNei").eq(2).text($(module).text());

    $("#nei").attr("src", url + "/" + personalBean["number"]);
}

/************************菜单样式*****************************************************/
$(function () {
    /* 一级标题样式 */
    $(".stair").on("click", function () {
        $(".stair2   > div > div").removeClass("menuInteraction_3");
        $(".stair").removeClass("menuInteraction_3");
        $(this).addClass("menuInteraction_3");
    });

    /* 二级标题样式 */
    $(".stair2 > div > div").on("click", function () {
        $(".stair").removeClass("menuInteraction_3");
        $(".stair2  > div > div").removeClass("menuInteraction_3");

        $(this).parents(".stair2").prev().addClass("menuInteraction_3");
        $(this).addClass("menuInteraction_3");
    });
});

