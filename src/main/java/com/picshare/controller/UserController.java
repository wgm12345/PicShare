package com.picshare.controller;

import com.picshare.entity.User;
import com.picshare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wgm on 17/3/21.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/sign",method = RequestMethod.GET)
    public  String getSign(){
        return "/user/sign";
    }

    @RequestMapping(value = "/signIn",method = RequestMethod.GET)
    public  String getSignIn(){
        return "/user/signIn";
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String getRegister(){
        return "/user/register";
    }

    @RequestMapping(value = "register",method = RequestMethod.POST)
    public String registerHandle(@ModelAttribute("user")User user){

        this.userService.addUser(user);
        return "/user/registerSuccess";
    }


}
