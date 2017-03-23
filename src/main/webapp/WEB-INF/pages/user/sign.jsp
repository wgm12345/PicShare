<%--
  Created by IntelliJ IDEA.
  User: wgm
  Date: 17/3/22
  Time: 下午1:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>用户登陆/注册</title>
  <link rel="stylesheet" href="/statics/css/site.css">
</head>
<body class="sign-bg">
<div id = "main">
  <div class = "sign-box-wrap">
    <form class="sign-box">
      <div class="logo">Picshare</div>
      <div class="btns-wrap">
        <a href="/user/signIn" class="sign-btn">登录</a>
        <a href="/user/register" class="register-btn">注册</a>
        <div class="other-btns-wrap">
          <button class="icon-sina">新浪</button>
          <button class="icon-google">Google</button>
          <button class="icon-QQ">QQ</button>
        </div>
      </div>
    </form>
  </div>
</div>
<footer class="sign-footer">
  <button class="about-us">关于我们</button>
</footer>
</html>
