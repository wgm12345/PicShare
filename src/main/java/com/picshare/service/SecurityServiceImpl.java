package com.picshare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by wgm on 17/4/9.
 */
@Service("securityService")
public class SecurityServiceImpl implements SecurityService{

    @Override
    public String encoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }

    @Override
    public boolean checkPassword(String password ,String oldPassword) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if(encoderByMd5(password).equals(oldPassword)) {
            return true;
        }
        else{
            return false;
        }
    }
}
