package com.picshare.controller;

import com.picshare.entity.Image;
import com.picshare.entity.User;
import com.picshare.service.ImageService;
import com.picshare.service.UserService;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by wgm on 17/3/21.
 */
@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    // 改成sign作为默认页
    public  String getSign(){
        return "/user/sign";
    }

    //要cookie中判定用户是否已经登录
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();

        //判断cookie中是否有值
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie:cookies){
                // System.out.println(cookie.getName());
                if(cookie.getName().equals("userEmail")){
                    User user = userService.getUserByEmail(cookie.getValue());
                    if(user != null)
                        request.getSession().setAttribute("user",user);
                }
            }
        }

        //添加首页所需模型
        modelAndView.addObject("imgWeekHitTop20", imageService.getImagesBy(20,"weekHits",true));
        modelAndView.addObject("imgLatestSubmitTop20",imageService.getImagesBy(20,"createDateTime",true));
        modelAndView.addObject("imgOrderByAnimateHits",imageService.getImagesBy(4,"totalHits","动画",true));
        modelAndView.addObject("imgOrderByGameHits",imageService.getImagesBy(4,"totalHits","游戏",true));
        //modelAndView.addObject("user",userService.getUserById(1));//测试index 界面顶部菜单用户栏逻辑功能
        modelAndView.setViewName("/index");
        return modelAndView;
    }

    @RequestMapping(value = "/error",method = RequestMethod.GET)
    public String error(){
        return "/error";
    }

}
