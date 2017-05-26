package com.picshare.controller;

import com.picshare.entity.Genre;
import com.picshare.entity.Image;
import com.picshare.entity.User;
import com.picshare.service.DynamicService;
import com.picshare.service.GenreService;
import com.picshare.service.ImageService;
import com.picshare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wgm on 17/3/23.
 */
@Controller
@RequestMapping(value = "/image")
public class ImageController {

    @Autowired
    private ImageService imageService;
    @Autowired
    private UserService userService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private DynamicService dynamicService;
    //获取上传界面
    @RequestMapping(value = "/upload" ,method = RequestMethod.GET)
    public String getUploadPage(){
        return "/image/imageUpload";
    }

//    @RequestMapping(value = "/imageUploadTest")
//    public String testImageUploadOnly(){
//        return "image/imageUpload";
//    }

//    @RequestMapping(value = "upload" ,method = RequestMethod.POST)
//    public String handleUpload(@ModelAttribute("image")Image img){
//
//        imageService.addImage(img);
//
//        return "/image/uploadSuccess";
//    }
//    //没写完
    //获取图片展示界面
    @RequestMapping(value = "/id={imageId}")
    public ModelAndView getImageDisplayPageById(@PathVariable("imageId") long imgId){
        ModelAndView mav = new ModelAndView();

        mav.setViewName("/image/display");
        Image img = imageService.getImageById(imgId);
        if(img != null){
            //更新图片点击量
            img.setTotalHits(img.getTotalHits() + 1);
            this.imageService.update(img);
            //
            mav.addObject("image",img);
            mav.addObject("uploader",img.getUploader());
        }
        else{
            mav.addObject("error","未找到图片");
        }
        return mav;
    }
    @RequestMapping(value = "/deleteImage",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteImage(long imageId){
        Map<String,Object> map = new HashMap<>();
        Image image = imageService.getImageById(imageId);
        imageService.deleteImage(image);
        map.put("success","删除成功");
        return map;
    }

    //图片收藏
    //public void collect()

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
                    System.out.println("存放图片文件的路径:" + path);
                    file.transferTo(new File(path));//文件转存
                    System.out.println("文件成功上传到指定目录下");

                    //持久化
                    MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
                    Image image = new Image();

                    String name = multipartHttpServletRequest.getParameter("imageName");
                    String briefIntro = multipartHttpServletRequest.getParameter("briefIntro");
                    Genre genre = genreService.getGenreById(1);
                    image.setName(name);
                    image.setBriefIntro(briefIntro);
                    image.setGenre(genre);
                    User user = (User)session.getAttribute("user");
                    user = userService.getUserById(user.getId());
                    image.setUploader(user);
                    image.setImgPath(trueFileName);
//                    img.setImgPath("statics/images/master/" + trueFileName);
//                    Date date = new Date();
//                    img.setCreateDateTime(date);
                    imageService.addImage(image);
//                    System.out.println("持久化成功");

                    //测试状态，在未审核情况下，直接发布动态
                    dynamicService.createDynamicsWhenFollowUserUpload(image.getUploader(),image);

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

