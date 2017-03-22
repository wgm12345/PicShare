<%--
  Created by IntelliJ IDEA.
  User: wgm
  Date: 17/3/21
  Time: 上午11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>用户注册</title>
</head>
<body>
    <c:url var="register" value="/user/register"></c:url>
    <form action="${register}" method="post">
        <label>用户名</label>
        <input type="text" name="name">
        <label>邮箱</label>
        <input type="text" name="email">
        <label>密码</label>
        <input type="password" name="password">
        <input type="hidden" name="signUpDateTime" value="2017/2/21">
        <input type="hidden" name="headImgPath" value="default.jpg">
        <input type="submit" value="提交" >
    </form>
</body>
</html>
