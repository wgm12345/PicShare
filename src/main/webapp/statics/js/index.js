/**
 * Created by wgm on 17/4/21.
 */
$(document).ready(function () {
    var $hide = $("#LastestSubmit").children(":gt(20)");
    $hide.hide();
    $("#toggle1").click(function () {
        if ($hide.is(":hidden")) {
            $hide.show();
            $(this).children("span").text("收回").removeClass("glyphicon-menu-down").addClass("glyphicon-menu-up");
        }
        else {
            $hide.hide();
            $(this).children("span").text("展开").removeClass("glyphicon-menu-up").addClass("glyphicon-menu-down");
        }
    });

    $(document).ready(function () {
        var hide7 = $("li[id*='lsls7']");
        var hide3 = $("li[id*='lsls3']");
        hide7.hide();
        $("button[id='lsls3_btn']").click(function () {
            hide3.show();
            hide7.hide();
        });
        $("button[id='lsls7_btn']").click(function () {
            hide7.show();
            hide3.hide();
        });
    })
})