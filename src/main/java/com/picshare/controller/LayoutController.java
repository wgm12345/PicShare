package com.picshare.controller;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wgm on 17/3/29.
 */
@Controller
@RequestMapping(value = "/layout")
public class LayoutController {
    @RequestMapping(value = "/footer")
    public String getFooter(){
        return "/layout/footer";
    }
    @RequestMapping(value = "/header")
    public String getHeader(){
        return "/layout/header";
    }
    @RequestMapping(value = "/topNav")
    public String getTopNav(){
        return "/layout/topNav";
    }
    @RequestMapping(value = "/user_header")
    public String getHeaderOfUser(){
        return "/layout/user_header";
    }
}
