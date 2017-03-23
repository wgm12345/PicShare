package com.picshare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * Created by wgm on 17/3/23.
 */
@Controller
@RequestMapping(value = "/image")
public class ImageController {

    @RequestMapping(value = "/upload" ,method = RequestMethod.GET)
    public String getUploadPage(){
        return "/image/upload";
    }

    //处理作品上传的保存
    @ResponseBody
    @RequestMapping(value = "/uploadProcess",method = RequestMethod.POST)
    public void uploadProcess(MultipartFile file,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IllegalStateException, IOException {
        if(file != null){
            String path = null;
            String type = null;
            String fileName = file.getOriginalFilename(); //获取文件名称
            System.out.println("上传的文件原名称:"+fileName);

            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1,fileName.length()) : null;//获取type
            if(type != null){
                if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
                    String realPath = request.getSession().getServletContext().getRealPath("/");//获取实际根路径
                    String trueFileName=String.valueOf(System.currentTimeMillis())+fileName;//自定义文件名称
                    path = realPath + "statics/images/master/" + trueFileName;
                    System.out.println("存放图片文件的路径:"+path);
                    file.transferTo(new File(path));//文件转存
                    System.out.println("文件成功上传到指定目录下");
                }else {
                    System.out.println("不是我们想要的文件类型,请按要求重新上传");
                }
            }else{
                System.out.println("文件类型为空");
            }
        }else{
            System.out.println("没有找到相对应的文件");
        }
    }
}

