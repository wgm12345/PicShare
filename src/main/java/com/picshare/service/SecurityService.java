package com.picshare.service;

import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by wgm on 17/4/9.
 */

public interface SecurityService {
    public String encoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException;
    public boolean checkPassword(String password ,String oldPassword) throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
