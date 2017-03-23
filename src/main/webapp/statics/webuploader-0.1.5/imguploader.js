var applicationPath = window.applicationPath === "" ? "" : window.applicationPath || "../../";
$(function () {
    var $ = jQuery,
    $list = $('#fileList'),
    $btn = $('#uploadBtn'),
    state = 'pending',
    // 优化retina, 在retina下这个值是2
    ratio = window.devicePixelRatio || 1,
    // 缩略图大小
    thumbnailWidth = 240 * ratio,
    thumbnailHeight = 180 * ratio,
    // Web Uploader实例
    uploader;
    uploader = WebUploader.create({
        // 选完文件后，是否自动上传。
        //auto: false,

        // swf文件路径
        swf: applicationPath + 'statics/webupdater-0.1.5/Uploader.swf',

        // 文件接收服务端。
        server: applicationPath + 'image/uploadProcess"',

        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: {
            id: '#filePicker',
            multiple: false
        },

        //只允许选择图片
        accept: {
            title: 'Images,video',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        },
        compress: {
            width: 1200,
            height: 800,

            // 图片质量，只有type为`image/jpeg`的时候才有效。
            quality: 90,

            // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
            allowMagnify: false,

            // 是否允许裁剪。
            crop: true,

            // 是否保留头部meta信息。
            preserveHeaders: true,

            // 如果发现压缩后文件大小比原来还大，则使用原来图片
            // 此属性可能会影响图片自动纠正功能
            noCompressIfLarger: false,

            // 单位字节，如果图片大小小于此值，不会采用压缩。
            compressSize: 0
        }

    });


    // 当有文件添加进来的时候
    uploader.on('fileQueued', function (file) {
        var $li = $(
                '<div id="' + file.id + '" class="cp_img">' +
                    '<img>' +
                '<div class="cp_img_jian"></div>'+
                '<p class="state">等待上传...</p>' +
                '</div>'
                
                ),
            $img = $li.find('img');
        //移除选中文件

        // $list为容器jQuery实例
        $list.append($li);

        // 创建缩略图
        // 如果为非图片文件，可以不用调用此方法。
        // thumbnailWidth x thumbnailHeight 为 100 x 100
        uploader.makeThumb(file, function (error, src) {
            if (error) {
                $img.replaceWith('<span>不能预览</span>');
                return;
            }

            $img.attr('src', src);
        }, thumbnailWidth, thumbnailHeight);
    });

    // 文件上传过程中创建进度条实时显示。
    uploader.on('uploadProgress', function (file, percentage) {
        var $li = $('#' + file.id),
            $percent = $li.find('.progress span');

        // 避免重复创建
        if (!$percent.length) {
            $percent = $('<p class="progress"><span></span></p>')
                    .appendTo($li)
                    .find('span');
        }
        $percent.css('width', percentage * 100 + '%');
    });

    // 文件上传成功，给item添加成功class, 用样式标记上传成功。
    uploader.on('uploadSuccess', function (file, response) {

        //$('#' + file.id).addClass('upload-state-done');
        $('#' + file.id).find('p.state').text('已上传');
        //alert(response.coverFileName);
        $("#CoverFilePath").val(response.coverFileName);
    });

    // 文件上传失败，显示上传出错。
    uploader.on('uploadError', function (file) {
        //var $li = $('#' + file.id),
        // $error = $li.find('div.error');
        $('#' + file.id).find('p.state').text('上传出错');

        // 避免重复创建
       // if (!$error.length) {
         //   $error = $('<div class="error"></div>').appendTo($li);
      //  }

      //  $error.text('上传失败');
    });

    // 完成上传完了，成功或者失败，先删除进度条。
    uploader.on('uploadComplete', function (file) {
        // $('#' + file.id).find('.progress').remove();
        $('#' + file.id).find('.progress').fadeOut();
    });

    uploader.on('all', function (type) {
        if (type === 'startUpload') {
            state = 'uploading';
        } else if (type === 'stopUpload') {
            state = 'paused';
        } else if (type === 'uploadFinished') {
            state = 'done';
        }

        if (state === 'uploading') {
            $btn.text('暂停上传');
        } else {
            $btn.text('开始上传');
        }
    });

    $btn.on('click', function () {
        if (state === 'uploading') {
            uploader.stop();
        } else {
            uploader.upload();
        }
    });
 

});