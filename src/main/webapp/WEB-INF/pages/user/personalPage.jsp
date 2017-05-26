<%--
  Created by IntelliJ IDEA.
  User: wgm
  Date: 17/3/22
  Time: 下午1:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
        uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <c:if test="${personalPageOwner != null}">
      <title>${personalPageOwner.name}的个人空间</title>
  </c:if>
  <c:if test="${personalPageOwner == null}">
      <title>此个人空间访问失效</title>
  </c:if>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/bootstrap-3.3.7/css/bootstrap.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/site.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/statics/bootstrap-3.3.7/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery/jquery-1.10.2.min.js"></script>

</head>
<body class="u_body">
  <c:if test="${personalPageOwner == null}">
      此个人空间访问失效，<a href="${pageContext.request.contextPath}/">返回主页</a>
  </c:if>
  <c:if test="${personalPageOwner != null}">
    <jsp:include page="/layout/topNav" flush="true">
      <jsp:param name="user" value= "${sessionScope.user}" ></jsp:param>
    </jsp:include>
    <jsp:include page="/layout/user_header" flush="true"></jsp:include>

    <div class="container body-content">
      <div class="container-fluid" style="background-image:url(${pageContext.request.contextPath}/statics/images/user/bg.jpg)">

        <c:if test="${personalPageOwner.id == sessionScope.user.id}">
        <%--只有当访问界面的用户是本地用户才能启动管理--%>
          <div class="row">
            <div class="col-md-2">
              <div class="navbar navbar-left">
                <h3 class="text-center ">用户中心<br /></h3>
                <div class="btn-group-vertical btn-group-lg ">
                  <a href="/user/imageManage/userId=${sessionScope.user.id}" class="btn btn-primary">作品管理</a>
                  <a href="/user/accountManage/userId=${sessionScope.user.id}" class="btn btn-primary">账户管理</a>
                  <a href="/user/infoChange/userId=${sessionScope.user.id}" class="btn btn-primary">信息修改</a>
                </div>
              </div>
            </div>
          </c:if>

          <!---------------------------------------------------main-------------------->
          <div class="col-md-10">
            <div class="info">
              <c:if test="${personalPageOwner.id == sessionScope.user.id}">
                <h4 class="i-box">我的信息</h4>
              </c:if>
              <c:if test="${personalPageOwner.id != sessionScope.user.id}">
                <h4 class="i-box">TA的信息</h4>
              </c:if>
              <div class="row">
                <div class="col-md-2">
                  <img class="img-thumbnail i-box" src="${pageContext.request.contextPath}/statics/images/sculpture/${personalPageOwner.headImgPath}"/>
                </div>

                <div class="col-md-9 i-box" style="font-size: 14px;color:#2aabd2;">
                  <p >昵称: ${personalPageOwner.name}  <br>  性别: ${personalPageOwner.sex}</p>
                  <ul class="v-ul" style="float:right">
                    <c:if test="${personalPageOwner.id == sessionScope.user.id}">
                      <li><a href="/user/collectionList/userId=${personalPageOwner.id}" class="btn">我的收藏</a></li>
                      <li><a href="/user/followUserList/userId=${personalPageOwner.id}" class="btn">我的关注</a></li>
                      <li><a href="/user/fanList/userId=${personalPageOwner.id}" class="btn">我的粉丝</a></li>
                    </c:if>
                    <c:if test="${personalPageOwner.id != sessionScope.user.id}">
                      <li><a href="/user/collectionList/userId=${personalPageOwner.id}" class="btn">TA的收藏</a></li>
                      <li><a href="/user/followUserList/userId=${personalPageOwner.id}" class="btn">TA的关注</a></li>
                      <li><a href="/user/fanList/userId=${personalPageOwner.id}" class="btn">TA的粉丝</a></li>
                    </c:if>

                  </ul>
                </div>
              </div>
              <div class="i-box">
                <label>简介：</label>
                <p>作者简介</p>
              </div>

            </div>
            <h4><em class="text-info">${personalPageOwner.name}的上传</em></h4>
            <div class="row ">
              <c:forEach items="${personalPageOwner.images}" var="img">
                <div class="col-md-3">
                  <a href="/image/id=${img.id}"><img src="${pageContext.request.contextPath}/statics/images/master/${img.imgPath}" class="img-thumbnail"/></a>
                  <a href="/image/id=${img.id}">
                    <br />${img.name}<br />${img.createDateTime}
                  </a>
                </div>
              </c:forEach>
            </div>

            <%--引入上传画册集合的功能--%>
          </div>

        </div>
      </div>
      <hr />
    </div>

  </c:if>


</body>
</html>
