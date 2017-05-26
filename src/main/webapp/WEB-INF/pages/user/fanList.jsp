<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wgm
  Date: 17/5/9
  Time: 上午12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>${userName}的粉丝</title>
  <script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery/jquery-1.10.2.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/statics/bootstrap-3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/bootstrap-3.3.7/css/bootstrap.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/site.css">
</head>
<body >
<jsp:include page="/layout/topNav" flush="true">
  <jsp:param name="user" value= "${sessionScope.user}" ></jsp:param>
</jsp:include>
<jsp:include page="/layout/header" flush="true"></jsp:include>

<div class="container body-content">
  <div class="container-fluid">
    <h4 class="i-box">${userName}的粉丝</h4>
    <div class="row">
      <c:forEach var="user" items="${fans}">   <%--<div class="row">--%>
        <div class="col-md-offset-1 col-md-5 i-box">
          <div class="col-md-4">
            <img class="img-thumbnail" src="${pageContext.request.contextPath}/statics/images/sculpture/${user.headImgPath}"/>
          </div>

          <div class="col-md-8"  style="font-size:14px; color:#2aabd2">
            <p >昵称: ${user.name}  <br>  性别: ${user.sex}</p>
            <ul class="v-ul" style="float:right">
              <%--<c:if test="${user.id == sessionScope.user.id}">--%>
              <li><a href="/user/personalPage/uid=${user.id}" class="btn">进入TA的空间</a></li>
              <%--li <li><a href="/user/followUserList/userId=${user.id}" class="btn">我的关注</a></li>--%>
              <%--<li><a href="/user/fanList/userId=${user.id}" class="btn">我的粉丝</a></li>--%>
              <%--</c:if>--%>
              <%--<c:if test="${user.id != sessionScope.user.id}">--%>
              <%--<li><a href="/user/collectionList/userId=${user.id}" class="btn">TA的收藏</a></li>--%>
              <%--<li><a href="/user/followUserList/userId=${user.id}" class="btn">TA的关注</a></li>--%>
              <%--<li><a href="/user/fanList/userId=${user.id}" class="btn">TA的粉丝</a></li>--%>
              <%--</c:if>--%>
            </ul>
          </div>
        </div>
    </c:forEach>
    </div>
  </div>
</div>
</body>
</html>
