var applicationPath = window.applicationPath === "" ? "" : window.applicationPath || "../../";
$(function () {
    var $ = jQuery,
    $list = $('#thelist'),
    // Web Uploader实例
    uploader;
    uploader = WebUploader.create({
        // 选完文件后，是否自动上传。
        auto: true,

        // swf文件路径
        swf: applicationPath + '/webupdater-0.1.5/Uploader.swf',

        // 文件接收服务端。
        server: applicationPath + '/Home/UpLoadProcess',

        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#picker',

        //只允许选择图片
        accept: {
            title: 'videos',
            extensions: 'mp4',
            mimeTypes: ''
        }
    });

    // 当有文件被添加进队列的时候
    uploader.on('fileQueued', function (file) {
        $list.append('<div id="' + file.id + '" class="item">' +
            '<h4 class="info">' + file.name + '</h4>' +
            '<p class="state">等待上传...</p>' +
        '</div>');
    });


    // 文件上传过程中创建进度条实时显示。
    uploader.on('uploadProgress', function (file, percentage) {
        var $li = $('#' + file.id),
            $percent = $li.find('.progress .progress-bar');

        // 避免重复创建
        if (!$percent.length) {
            $percent = $('<div class="progress progress-striped active">' +
              '<div class="progress-bar" role="progressbar" style="width: 0%">' +
              '</div>' +
            '</div>').appendTo($li).find('.progress-bar');
        }

        $li.find('p.state').text('上传中');

        $percent.css('width', percentage * 100 + '%');
    });


    uploader.on('uploadSuccess', function (file) {
        $('#' + file.id).find('p.state').text('已上传');
    });

    uploader.on('uploadError', function (file) {
        $('#' + file.id).find('p.state').text('上传出错');
    });

    uploader.on('uploadComplete', function (file) {
        $('#' + file.id).find('.progress').fadeOut();
    });


    //所有文件上传完毕
    uploader.on("uploadFinished", function () {
        //提交表单

    });

    //开始上传
    $("#ctlBtn").click(function () {
        uploader.upload();

    });

    //显示删除按钮
    $(".cp_img").live("mouseover", function () {
        $(this).children(".cp_img_jian").css('display', 'block');

    });
    //隐藏删除按钮
    $(".cp_img").live("mouseout", function () {
        $(this).children(".cp_img_jian").css('display', 'none');

    });
    //执行删除方法
    $list.on("click", ".cp_img_jian", function () {
        var Id = $(this).parent().attr("id");
        uploader.removeFile(uploader.getFile(Id, true));
        $(this).parent().remove();
    });

});
