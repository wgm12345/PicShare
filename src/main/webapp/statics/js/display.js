/**
 * Created by wgm on 17/4/21.
 */
$(function(){
    //从url获取图片id
    var url = window.location.href;
    //alert(url);
    var startIndex = url.indexOf("id=");
    if(startIndex > 0){
        startIndex += 3;
    }
    var imageId = url.substring(startIndex,url.length);
   // alert(imageId);

    //加载评论楼层
    $.ajax({
        type:"GET",
        url:"/comment/getCommentData",
        data:{
            imageId:imageId
        },
        //async:false,
        success: function (data) {
            //alert(data["success"]);
            //$("#com-floors").append(data["data"][0].content);
            $("#com-floors ul").html("");//清空
            data["data"].forEach(function(value,index){
                //value.content
                index = index + 1;
                $("#com-floors ul").append(
                    "<li class='row table-bordered cmt-box'>" +
                    "<div class='col-md-2' style='padding:20px 20px 20px 20px'>" +
                    "<img src='/statics/images/sculpture/" + value.commenterHeadImagePath + "' class='img-thumbnail' />" +
                    "</div>" +
                    "<div class='col-lg-10' style='padding:30px 20px 0px 0px'>" +
                    index + " 楼:" + value.commenterName + "<br />" +
                    value.content + "<br />" +
                    "时间：" + value.publicTime + "<a class='text-left'>回复</a>" +
                    "</div>" +
                    "</li>"
                );
            });
        }
    });
    //test
//              $.ajax({
//                  type:"GET",
//                  url:'/comment/test',
//                  success:function(data){
//                      var str = "";
//                      for(item in data){
//                          str += data[item];
//                      }
//                      $("#com-floors").append(str);
//
//                  }
//              });

    //要实现ajax那么提交按钮不能写入表单
    $("#btn-submit").click(function(){
        //console.log("提交评论");
        //alert($("#comment-data").serialize());
        //ajax运用***************************************
        $.ajax({
            type:"POST",
            url:"/comment/submit",
            data:$("#comment-data").serialize(),
            //async:false,
            success: function (data) {
                alert(data["success"]);
                //$("#com-floors").append(data["data"][0].content);
                $("#com-floors ul").html("");//清空
                data["data"].forEach(function(value,index){
                    //value.content
                    $("#com-floors ul").append(
                        "<li class='row table-bordered cmt-box'>" +
                        "<div class='col-md-2' style='padding:20px 20px 20px 20px'>" +
                        "<img src='/statics/images/sculpture/" + value.commenterHeadImagePath + "' class='img-thumbnail' />" +
                        "</div>" +
                        "<div class='col-lg-10' style='padding:30px 20px 0px 0px'>" +
                        index + " 楼:" + value.commenterName + "<br />" +
                        value.content + "<br />" +
                        "时间：" + value.publicTime + "<a class='text-left'>回复</a>" +
                        "</div>" +
                        "</li>"
                    );
                });
            }
        });

    });

    //      $(document).ready(function() {
//        var numInOnePage = 5;
//        var currentPage = 1;
//        $("#com-floors ul").page({
//          num:numInOnePage,
//          cur:currentPage
//        });
//      });
    //调用
    //调用者覆盖 插件暴露的共公方法
    //            $.fn.highLight.format = function (txt) {
    //                return "<em>" + txt + "</em>"
    //            }
    //            $(function () {
    //                $("p").highLight({ foreground: 'orange', background: '#ccc' }); //调用自定义 高亮插件
    //            });
    $(".follow").click(function(){
        if(fanId == null){
            alert("关注请先登录");
        }else{
            if(fanId == followUserId){
                alert("不能关注自己哦，亲");
            }
            else{
                $.ajax({
                    type:"POST",
                    url:"/user/follow",
                    data:{
                        fanId:fanId,
                        followUserId:followUserId
                    },
                    success:function(data){
                        if(data["error"] != null){
                            alert(data["error"]);
                        }
                        if(data["success"] != null){
                            alert(data["success"]);
                        }
                    }
                });
            }
        }

    });

    $(".btn-collect").click(function () {
        if(userId == null){
            alert("收藏请登录");
        }else{
            $.ajax({
                type:"POST",
                url:"/user/collect",
                data:{
                    userId:userId,
                    imageId:imageId
                },
                success:function(data){
                    if(data["collect_error"] != null){
                        alert(data["collect_error"]);
                    }
                    if(data["collect_success"] != null){
                        alert(data["collect_success"]);
                    }
                    if(data["error_user_not_found"] != null){
                        alert(data["error_user_not_found"]);
                    }
                }

            });
        }

    });

});