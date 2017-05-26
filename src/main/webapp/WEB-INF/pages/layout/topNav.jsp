<%--
  Created by IntelliJ IDEA.
  User: wgm
  Date: 17/3/29
  Time: 上午9:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<script src="${pageContext.request.contextPath}/statics/js/jquery/jquery-1.10.2.min.js"></script>

<div class="top">
  <div class="top_bg_wrp">
    <div class="top_bg blur"></div>
    <div class="bg-blur-mask"></div>
  </div>
  <div class="top_container">
    <div class="top_nav">
      <ul class="v_ulist">
        <li class="t-ul-item">
          <a href="${pageContext.request.contextPath}/image/upload" class="submit">投稿</a>
        </li>
        <li class="t-ul-item">
          <a href="${pageContext.request.contextPath}/">主站</a>
        </li>
        <li class="t-ul-item"><a>关于</a></li>
        <li class="t-ul-item"><a>联系方式</a></li>
      </ul>
    </div>
    <div class="user_box">

      <c:if test="${user != null}">
        <ul>
          <li id="sign_out">退出</li>
          <li><a href="${pageContext.request.contextPath}/user/personalPage">个人空间</a></li>
          <li><a href="${pageContext.request.contextPath}/user/dynamicList">动态 ${user.dynamicses.size()}</a></li>
          <li>welcome! ${user.name}</li>
        </ul>
      </c:if>
      <c:if test="${user == null}">
        <ul>
          <li><a href="${pageContext.request.contextPath}/user/register">注册</a></li>
          <li><a href="${pageContext.request.contextPath}/user/signIn">登录</a></li>
        </ul>
      </c:if>
    </div>
  </div>
</div>

<script>
  $(document).ready(function(){
    $("li[id = 'sign_out']").bind("click",function(){
      $.ajax({
        url:"${pageContext.request.contextPath}/user/signOut",
        type:"POST"
      });
      window.location = "/";//重定向回首页
      alert("成功退出");
    });
  });

</script>




