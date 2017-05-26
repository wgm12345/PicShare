<%--
  Created by IntelliJ IDEA.
  User: wgm
  Date: 17/3/21
  Time: 上午11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<html>
<head>
	<title>用户注册</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/bootstrap-3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/site.css">
</head>

<c:if test="${sessionScope.user == null}">
    <c:url var="register" value="/user/register"></c:url>
<body class="sign-bg">
<div id = "main">
    <div class = "sign-box-wrap">
        <%----%>
        <%--<form action="${register}" method="post">--%>
            <%--<label>用户名</label>--%>
            <%--<input type="text" name="name">--%>
            <%--<label>邮箱</label>--%>
            <%--<input type="text" name="email">--%>
            <%--<label>密码</label>--%>
            <%--<input type="password" name="password">--%>
                <%--&lt;%&ndash;<input type="hidden" name="signUpDateTime" value="2017/2/21">&ndash;%&gt;--%>
            <%--<input type="hidden" name="headImgPath" value="default.jpg">--%>
            <%--<label>男</label>--%>
            <%--<input type="radio" name="sex" value="男">--%>
            <%--<label>女</label>--%>
            <%--<input type="radio" name="sex" value="女">--%>
            <%--<label>保密</label>--%>
            <%--<input type="radio" name="sex" value="保密">--%>
            <%--<input type="submit" value="提交" >--%>
        <%--</form>--%>
        <form class="sign-box" action="${register}" method="post">
            <input type="hidden" name="headImgPath" value="default.jpg">
            <div class="logo">Picshare</div>
            <c:if test="${error != null}">
                <label class="label-danger sign-error-box">${error}</label>
            </c:if>
            <div class="login-list form-horizontal">
                <div class="form-group">
                    <label class="col-md-3" >用户名</label>
                    <div class="col-md-9">
                        <input class="form-control" type="text" name="name">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3" >邮箱</label>
                    <div class="col-md-9">
                        <input class="form-control" type="text" name="email">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3">密码</label>
                    <div class="col-md-9">
                        <input class="form-control" type="password" name="password">
                    </div>
                </div>
                <div class="form-group">
				    <label class="col-md-3">性别</label>
                    <div class="col-md-9">
                        <label>男</label>
                        <input type="radio" name="sex" value="男">
                        <label>女</label>
                        <input type="radio" name="sex" value="女">
                        <label>保密</label>
                        <input type="radio" name="sex" value="保密">
                    </div>
                </div>
                <div class="form-group">
                    <input class="col-md-offset-8 col-md-2 btn-submit" type="submit" value="提交">
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</c:if>
<c:if test="${sessionScope.user != null}">
    <script>
        window.location.href = "/index";
    </script>
</c:if>
</html>
