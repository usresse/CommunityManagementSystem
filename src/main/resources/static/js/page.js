$(function () {
        /**获取数据并转化为对象*/

        $(".user").text(personalBean["nickName"]);
        $(".quan").text(personalBean["number"]);

        /**照片的获取效果，如果为空则没有*/
        let number = $(".quan")[0].innerHTML;
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
    }
)

/******************菜单**************************/
/**更改函数数据*/
$(function () {
    /**对成员隐藏审核选项*/
    let data = personalBean["major"];

    /**成员菜单*/
    if (data == "3") {
        $(".ti3").hide();
        $(".tem2 > div > div").eq(0).text("其他成员");
    }

    /**社长菜单*/
    if (data == "1") {
        $(".tem3 > div").prepend($(".tem2 > div > div").eq(0).text("社团成员管理"));
        /**添加社团信息维护*/
        $(".tem3").append($("<div><div onclick=JumpHtml('CommunityInformationMaintenance')>社团信息维护</div></div>"))
    }

    if (data == "" || data == null || data.length == 0) {
        $(".ti3").hide();
        /**隐藏参加的社团活动申请和历史*/
        $(".tem2").find("div").eq(2).hide()
        $(".tem2").find("div").eq(3).hide();
    } else {
        $(".tem3").append($("<div><div onclick=JumpHtml('notice')>社团通知</div></div>"))
    }

    /**对社团申请的菜单隐藏*/
    if (data != null && data != "") {
        $(".nul").hide();
    }
});


/**菜单框效果实现*/
function toggleshow(cla) {
    $("."+cla).toggle(1);
};

/**设置点击事件页面跳转*/
function JumpHtml(url){
    $("#nei").attr("src", url);
}

function JumpHtmlAndNumber(url) {
    $("#nei").attr("src", url + "/" + personalBean["number"]);
}

/************************菜单样式*****************************************************/
$(function (){
    /* 一级标题样式 */
    $(".stair").on("click",function(){
        $(".stair").css({"background-color": "aqua","color":"#000"});
        $(this).css({"background-color": "#000","color":"#fff"});
    });

    /* 二级标题样式 */
    $(".stair2 > div > div").on("click",function(){
        $(".stair2   > div > div").css({"background-color": "#27eaff","color":"#000"});
        $(this).css({"background-color": "#000","color":"#fff"});
    });
});

