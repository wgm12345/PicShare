package com.picshare.controller;

import com.picshare.entity.Image;
import com.picshare.service.ImageService;
import com.picshare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wgm on 17/5/2.
 */
@Controller
public class SearchController {
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/search")
    public ModelAndView search(@RequestParam("key")String key){
        ModelAndView mav = new ModelAndView();
        mav.addObject("relatedImages",this.imageService.getImagesByKey(key));
//        mav.addObject("relatedUsers",this.userService.getUserByKey(key));
        mav.setViewName("/other/search");
        return mav;
    }
}
