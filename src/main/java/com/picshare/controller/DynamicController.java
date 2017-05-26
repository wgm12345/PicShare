package com.picshare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wgm on 17/4/24.
 */
@Controller
@RequestMapping(value = "/dynamic")


public class DynamicController {
    //创建评论回复型动态消息的handler
    @RequestMapping(value = "/createForCommentReply")
    public void createDynamicForCommentReply(){

    }

    //创建关注用户投稿提示型动态消息handler
    @RequestMapping(value = "/createForFollowUserUpload")
    public void createDynamicForFollowUserUpload(){

    }
}
