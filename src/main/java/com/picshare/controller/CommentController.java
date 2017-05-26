package com.picshare.controller;

import com.picshare.entity.Comment;
import com.picshare.entity.DisplayDataOfComment;
import com.picshare.entity.Image;
import com.picshare.entity.User;
import com.picshare.service.CommentService;
import com.picshare.service.ImageService;
import com.picshare.service.UserService;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.HttpJspPage;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletionException;

/**
 * Created by wgm on 17/4/14.
 */
@Controller
@RequestMapping(value = "/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserService userService;



    //test 后台为前台发数据json
    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public java.util.Map<String, Object> test() {
        java.util.Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        map.put("data", "your data");
        return map;
    }


//    @RequestMapping(value = "/submit" ,method = RequestMethod.POST)
//    @ResponseBody
//    public List<Comment> handleSubmit(@RequestParam("content") String content,@RequestParam("imageId") long imageId,HttpServletRequest request){
//        User user = (User)request.getSession().getAttribute("user");
//        user = userService.getUserById(user.getId());//由于lazy加载，session里的user数据没包括collection里的数据images、logins
//        Image image = imageService.getImageById(imageId);
//        commentService.addComment(content,user,image);
//        //刷新楼层信息：后台负责传要显示的楼层数据，前台ajax回调进行渲染
//
//        List<Comment> comments = this.getCommentsWhichShowInPage(1,20,imageId);
//        //debug 发现没增加新的数据进去
//        return comments;
//    }


    @RequestMapping(value = "/getCommentData",method = RequestMethod.GET)
    @ResponseBody
    public java.util.Map<String,Object> getCommentDataWhenLoad(@RequestParam("imageId") long imageId){
        java.util.Map<String,Object> map = new HashMap<String,Object>();

        Image image = imageService.getImageById(imageId);

        List<Comment> comments = this.getCommentsWhichShowInPage(1, 20, imageId);

        //处理成显示数据

        List<DisplayDataOfComment> data = new ArrayList<>();

        for(Comment comment : comments){
            data.add(new DisplayDataOfComment(comment.getContent(),comment.getUser().getName(),comment.getPublicDateTime().toString(),comment.getUser().getHeadImgPath()));
        }

        //map.put("success","评论成功");
        //map.put("comments",comments);
        map.put("data",data);
        return map;
    }

    //先存入数据，然后调用评论显示处理函数刷新
    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    @ResponseBody
    public java.util.Map<String,Object> handleSubmit(@RequestParam("content") String content,@RequestParam("imageId") long imageId,HttpServletRequest request){

        java.util.Map<String,Object> map = new HashMap<String,Object>();

        //持久化评论
        User user = (User)request.getSession().getAttribute("user");
        user = userService.getUserById(user.getId());//由于lazy加载，session里的user数据没包括collection里的数据images、logins
        Image image = imageService.getImageById(imageId);
        commentService.addComment(content, user, image);

        //发送要显示评论数据
        List<Comment> comments = this.getCommentsWhichShowInPage(1, 20, imageId);

        //处理成显示数据

        List<DisplayDataOfComment> data = new ArrayList<>();

        for(Comment comment : comments){
           data.add(new DisplayDataOfComment(comment.getContent(),comment.getUser().getName(),comment.getPublicDateTime().toString(),comment.getUser().getHeadImgPath()));
        }

        map.put("success","评论成功");
        //map.put("comments",comments);
        map.put("data",data);
        return map;
    }

    private List<Comment> getCommentsWhichShowInPage(int numOfPage,int numInPage,long imageId){
        List<Comment> comments = imageService.getImageById(imageId).getComments();
        return comments;
    }

    //@RequestMapping(value = "/reply")
   // public java.util.Map<String,Object> handleRepl(@RequestParam("content") String content,@RequestParam("imageId") long imageId,HttpServletRequest request){
}


