$(function () {
    /**对成员隐藏审核选项*/
    let major = window.parent.document.getElementsByClassName("major")[0];
    let data = major.innerHTML;

    /**成员菜单*/
    if (data == "成员") {
        $(".ti3").hide();
        $(".tem2>div").eq(0).text("其他成员");
    }

    if(data == "" || data == null || data.length == 0){
        $(".ti3").hide();
        /**隐藏参加的社团活动申请和历史*/
       $(".tem2").find("div").eq(2).hide()
       $(".tem2").find("div").eq(3).hide();

    }

    /**社长菜单*/
    if (data == "社长") {
        $(".ti2").hide();
        $(".tem3").prepend($(".tem2 > div").eq(0).text("社团成员管理"))
        /**添加社团信息维护*/
        $(".tem3").append($("<div onclick=CommunityInformationMaintenance('CommunityInformationMaintenance')></div>").text("社团信息维护"))
    }

    /**对社团申请的菜单隐藏*/
    if (data != "") {
        $(".nul").hide();
    }
});

/**用户账号*/
var number = window.parent.document.getElementsByClassName("quan")[0].innerHTML;
var ifram = window.parent.document.getElementById("nei");

/**菜单框效果实现*/
function toggleshow(cla) {
    $(document.getElementsByClassName(cla)).toggle(1);
};

/**设置点击事件页面跳转*/
function iframe(cla) {
    /* 获取到父页面的元素 */
    ifram.setAttribute("src", cla + "/" + number);
}

function CommunityPersonnelManagement(url) {
    ifram.setAttribute("src", url + "/" + 1 + "/null");
}

function activity(url) {
    ifram.setAttribute("src", url + "/" + 1 + "/null");
}

function activityAll(url) {
    ifram.setAttribute("src", url + "/" + number);
}

function MyClubStatus(url) {
    ifram.setAttribute("src", url + "/" + number);
}

function Reviewed(url) {
    ifram.setAttribute("src", url + "/0/" + number);
}

function ActivityRequest(url) {
    ifram.setAttribute("src", url);
}

function ActivityHistory(url) {
    ifram.setAttribute("src", url + "/1/" + number + "/null");
}

function ApplyAssociation(url) {
    ifram.setAttribute("src", url + "/" + number);
}

function CommunityInformationMaintenance(url){
    ifram.setAttribute("src", url + "/" + number);
}