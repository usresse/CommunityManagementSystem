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

