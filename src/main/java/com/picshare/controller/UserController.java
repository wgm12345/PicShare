package com.picshare.controller;

import com.picshare.dao.UserDao;
import com.picshare.entity.Genre;
import com.picshare.entity.Image;
import com.picshare.entity.User;
import com.picshare.service.ImageService;
import com.picshare.service.SecurityService;
import com.picshare.service.SecurityServiceImpl;
import com.picshare.service.UserService;
import com.sun.media.sound.ModelDestination;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wgm on 17/3/21.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/signIn",method = RequestMethod.GET)
    public  String getSignIn(){
        return "/user/signIn";
    }

    @RequestMapping(value = "/signIn",method = RequestMethod.POST)
    //登陆逻辑：判断用户账户密码是否存在，存在将用户存入会话，并且跳转如主页面，并在主页面topNav进行展现
    public ModelAndView handleSignIn(@RequestParam("email") String email,@RequestParam("password") String password,HttpServletRequest httpServletRequest,HttpServletResponse response) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        ModelAndView mav = new ModelAndView();
        User user = userService.getUserByEmail(email);
//        List<Image> images = user.getImages();
//        System.out.println(images.get(0).getName());
        if(user != null){
            if(securityService.checkPassword(password,user.getPassword())){
                //mav.addObject("user",user);
                //mav.setViewName("/index");

                //用户存入会话
                httpServletRequest.getSession().setAttribute("user",user);
               // httpServletRequest.getSession().setAttribute("dynamics",user.getDynamicses());
                //邮箱账号写入cookie
                Cookie cookie = new Cookie("userEmail",user.getEmail());
                cookie.setMaxAge(3600);//设置cookie生命周期
                //设置路径，这个路径即该工程下都可以访问该cookie 如果不设置路径，那么只有设置该cookie路径及其子路径可以访问
                cookie.setPath("/");
                response.addCookie(cookie);
                mav.setViewName( "redirect:/index");
                return mav;
            }else{
                System.out.println("wrong password");
                mav.setViewName("/user/signIn");
                mav.addObject("error","密码错误");
                return mav;
                //return "/user/signIn";//前端要显示错误信息
            }
        }else{
            System.out.println("user is not exist");
            mav.setViewName("/user/signIn");
            mav.addObject("error","账号不存在");
            return mav;
            //return "/user/signIn";
        }
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String getRegister(){
        return "/user/register";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ModelAndView registerHandle(@ModelAttribute("user")User user ,HttpServletRequest httpServletRequest,HttpServletResponse response) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        ModelAndView mav = new ModelAndView();

        try{
            user.setPassword(securityService.encoderByMd5(user.getPassword()));
            this.userService.addUser(user);

            //用户存入会话
            httpServletRequest.getSession().setAttribute("user",user);
            //邮箱账号写入cookie
            Cookie cookie = new Cookie("userEmail",user.getEmail());
            cookie.setMaxAge(3600);//设置cookie生命周期
            //设置路径，这个路径即该工程下都可以访问该cookie 如果不设置路径，那么只有设置该cookie路径及其子路径可以访问
            cookie.setPath("/");
            response.addCookie(cookie);

            //注册完向邮箱发消息，并等待进行确认
            mav.setViewName("/user/registerSuccess");
            mav.addObject("success","注册成功");
            return mav;
//            return "/user/registerSuccess";
        }catch (Exception e){
            e.printStackTrace();
            mav.addObject("error","账号名称或用户名已存在");
            mav.setViewName("/user/register");
            return mav;
        }
    }

    @RequestMapping(value = "/signOut",method = RequestMethod.POST)
    public void signOut(HttpServletRequest request,HttpServletResponse response){
        //去掉会话里user的内容，移除cookie内emailName
        //没删除掉，还不知为何
        request.getSession().removeAttribute("user");
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("userEmail")){
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
        //return "redirect:/";
    }

//    @RequestMapping(value = "/test",method = RequestMethod.POST)
//    public void test(){
//        System.out.println("test");
//    }

    @RequestMapping(value = "/personalPage",method = RequestMethod.GET)
    public ModelAndView getPersonalPage(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        User user = (User)request.getSession().getAttribute("user");
        if(user != null){
            user  = userService.getUserByEmail(user.getEmail());
        }
        //List<Image> images = user.getImages();
        //System.out.println(images.get(0).getName()+"youmeiyou");
        mav.addObject("personalPageOwner",user);
        mav.setViewName("/user/personalPage");
        return mav;
    }

    @RequestMapping(value = "/personalPage/uid={userId}",method = RequestMethod.GET)
    public ModelAndView getPersonalPageByUserId(@PathVariable("userId") long userId){
        ModelAndView mav = new ModelAndView();
        User user = userService.getUserById(userId);
        mav.addObject("personalPageOwner",user);
        mav.setViewName("/user/personalPage");
        return mav;
    }
    //关注用户功能
    @RequestMapping(value = "/follow",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> follow(long fanId,long followUserId){

        Map<String,Object> map = new HashMap<>();

        User fan = userService.getUserById(fanId);
        User followUser = userService.getUserById(followUserId);

        boolean isExist = false;
        for(User user : fan.getFollowUsers()){
            if(user.equals(followUser)){
                isExist = true;
            }
        }
        if(!isExist){
            userService.addFollowUserForFan(fan,followUser);
            map.put("success","关注成功");
        }else{
            map.put("error","已经关注过");
        }
        return map;
    }

    //取消关注用户
    @RequestMapping(value = "cancelFollow",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> cancelFollow(long fanId,long followUserId){
        Map<String,Object> map = new HashMap<>();

        User fan = userService.getUserById(fanId);
        User followUser = userService.getUserById(followUserId);
        userService.cancelFollow(fan, followUser);
        map.put("success","取消关注成功");

        return map;
    }

    @RequestMapping(value = "/collect",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> collect(long userId,long imageId){
        Map<String,Object> map = new HashMap<>();
        User user = userService.getUserById(userId);
        Image image = imageService.getImageById(imageId);
        boolean isExist = false;
        for(Image collect : user.getCollections()){
            if(collect.equals(image)){
                isExist = true;
            }
        }
        if(!isExist){
            userService.addCollectImage(user, image);
            map.put("collect_success","收藏成功");
        }else{
            map.put("collect_error","已经收藏过了");
        }
        return map;
    }

    @RequestMapping(value = "/cancelCollect",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> cancelCollect(long userId,long imageId){
        Map<String,Object> map = new HashMap<>();
        try{
            User user = userService.getUserById(userId);
            Image image = imageService.getImageById(imageId);
            userService.cancelCollect(user,image);
            map.put("success","成功取消收藏");
        }catch (Exception e){
            e.printStackTrace();
            map.put("error","出错了");
        }
        return map;
    }

    @RequestMapping(value = "/dynamicList",method = RequestMethod.GET)
    public ModelAndView getDynamicListPage(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();

        mav.setViewName("/user/dynamicList");

        User user = (User)request.getSession().getAttribute("user");
        if(user != null){
            mav.addObject("dynamics", user.getDynamicses());
        }else{
            mav.addObject("error", "请登录");
        }
        return mav;
    }

//    @RequestMapping(value = "/collect",method = RequestMethod.POST)
//    public Map<String,Object> collectImage(image){
//        Map<String,Object> map = new HashMap<>();
//        userService.addCollectionForUser();
//    }

    //////////////////////////personnal page ref////////////////////
    @RequestMapping(value = "/accountManage/userId={userId}" ,method = RequestMethod.GET)
    public ModelAndView getAccountManagePage(@PathVariable("userId") long userId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/user/accountManage");
        User user = userService.getUserById(userId);

        mav.addObject("user",user);

        return mav;
    }

    @RequestMapping(value = "/changePassword" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> changePassword(String newPassword,long userId) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Map<String,Object> map = new HashMap<>();

        String password = securityService.encoderByMd5(newPassword);
        User user = userService.getUserById(userId);
        user.setPassword(password);
        userService.update(user);
        map.put("success","修改成功");
        return map;
    }

    @RequestMapping(value = "/imageManage/userId={userId}" ,method = RequestMethod.GET)
    public ModelAndView getImageManagePage(@PathVariable("userId") long userId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user/imageManage");

        List<Image> images = userService.getUserById(userId).getImages();
        List<Image> myImages = new ArrayList<>();
        for(Image image : images){
            if(image.isDeleted() == false){
                myImages.add(image);
            }
        }
        mav.addObject("myImages", myImages);
        return mav;

    }

    //change info ref
    @RequestMapping(value = "/infoChange/userId={userId}" ,method = RequestMethod.GET)
    public ModelAndView getInfoChangePage(@PathVariable("userId") long userId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/user/infoChange");
        User user = userService.getUserById(userId);
        if(user != null){
            mav.addObject("error",null);
            mav.addObject("userInfo",user);
        }else{
            mav.addObject("error","用户不存在");
        }

        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/headImgUploadProcess",method = RequestMethod.POST)
    public void uploadProcess(MultipartFile file,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IllegalStateException, IOException {
        if(file != null){
            String path = null;
            String type = null;
            String fileName = file.getOriginalFilename(); //获取文件名称
            System.out.println("上传的文件原名称:" + fileName);

            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1,fileName.length()) : null;//获取type
            if(type != null){
                if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
                    String realPath = request.getSession().getServletContext().getRealPath("/");//获取实际根路径
                    String trueFileName=String.valueOf(System.currentTimeMillis())+fileName;//自定义文件名称
                    path = realPath + "statics/images/sculpture/" + trueFileName;
                    System.out.println("存放图片文件的路径:" + path);
                    file.transferTo(new File(path));//文件转存
                    System.out.println("文件成功上传到指定目录下");

                    //持久化
                    MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
//                    Image image = new Image();
//
//                    String name = multipartHttpServletRequest.getParameter("imageName");
//                    String briefIntro = multipartHttpServletRequest.getParameter("briefIntro");
//                    Genre genre = genreService.getGenreById(1);
//                    image.setName(name);
//                    image.setBriefIntro(briefIntro);
//                    image.setGenre(genre);
                    User user = (User)session.getAttribute("user");
                    user = userService.getUserById(user.getId());
                    user.setHeadImgPath(trueFileName);
                    userService.update(user);
//                    image.setUploader(user);
//                    image.setImgPath(trueFileName);
//                    img.setImgPath("statics/images/master/" + trueFileName);
//                    Date date = new Date();
//                    img.setCreateDateTime(date);
//                    imageService.addImage(image);
//                    System.out.println("持久化成功");

                    //测试状态，在未审核情况下，直接发布动态
//                    dynamicService.createDynamicsWhenFollowUserUpload(image.getUploader(),image);

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
    ////////////////////

    @RequestMapping(value = "/fanList/userId={userId}" ,method = RequestMethod.GET)
    public ModelAndView getFanListPage(@PathVariable("userId")long userId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/user/fanList");
        User user = userService.getUserById(userId);
        List<User> fans = user.getFans();
        mav.addObject("userName",user.getName());
        mav.addObject("fans",fans);
        return mav;
    }

    @RequestMapping(value = "/followUserList/userId={userId}" ,method = RequestMethod.GET)
    public ModelAndView getFollowUserListPage(@PathVariable("userId")long userId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/user/followUserList");
        User user = userService.getUserById(userId);
        List<User> followUsers = user.getFollowUsers();
        mav.addObject("followUsers",followUsers);
        mav.addObject("followUsersOwner",user);
        return mav;
    }

    @RequestMapping(value = "/collectionList/userId={userId}",method = RequestMethod.GET)
    public ModelAndView getCollectionListPage(@PathVariable("userId")long userId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user/collectionList");
        List<Image> collections = userService.getUserById(userId).getCollections();
        mav.addObject("collections",collections);
        return mav;
    }

    //////////////////////////personnal page ref////////////////////
}
