/**获取账号数据*/
var personalBean = parent.window.personalBean;
/**用户账号*/
var number = personalBean["number"];

/**获取显示iframe*/
var ifram = $("#nei",parent.document);


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
        $(".tem3").append($("<div><div onclick=CommunityInformationMaintenance('CommunityInformationMaintenance')>社团信息维护</div></div>"))
    }

    if (data == "" || data == null || data.length == 0) {
        $(".ti3").hide();
        /**隐藏参加的社团活动申请和历史*/
        $(".tem2").find("div").eq(2).hide()
        $(".tem2").find("div").eq(3).hide();
    } else {
        $(".tem3").append($("<div><div onclick=notice('notice')>社团通知</div></div>"))
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
function iframe(cla) {
    /* 获取到父页面的元素 */
    ifram.attr("src", cla);
}

function CommunityPersonnelManagement(url) {
    ifram.attr("src", url + "/" + 1 + "/null");
}

function activity(url) {
    ifram.attr("src", url);
}

function activityAll(url) {
    ifram.attr("src", url + "/" + number);
}

function MyClubStatus(url) {
    ifram.attr("src", url + "/" + number);
}

function Reviewed(url) {
    ifram.attr("src", url);
}

function ActivityRequest(url) {
    ifram.attr("src", url);
}

function ActivityHistory(url) {
    ifram.attr("src", url);
}

function ApplyAssociation(url) {
    ifram.attr("src", url);
}

function CommunityInformationMaintenance(url) {
    ifram.attr("src", url);
}

function announcement(url) {
    ifram.attr("src", url);
}

function notice(url) {
    ifram.attr("src", url);
}

/*****************************************************************************/
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