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
  <title>${followUsersOwner.name}的关注的用户</title>
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
    <h4 class="i-box">${followUsersOwner.name}的关注的用户</h4>
    <div class="row">
      <c:forEach var="followUser" items="${followUsers}">   <%--<div class="row">--%>
        <div class="col-md-offset-1 col-md-5 i-box">
          <div class="col-md-4">
            <img class="img-thumbnail" src="${pageContext.request.contextPath}/statics/images/sculpture/${followUser.headImgPath}"/>
          </div>

          <div class="col-md-8"  style="font-size:14px; color:#2aabd2">
            <p >昵称: ${followUser.name}  <br>  性别: ${followUser.sex}</p>
            <ul class="v-ul" style="float:right">

              <li><a href="/user/personalPage/uid=${followUser.id}" class="btn">进入TA的空间</a></li>
              <c:if test="${followUsersOwner.id == sessionScope.user.id}">
                <li><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal"> 取消关注</button></li>
                <!-- Modal -->
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                  <div class="modal-dialog" role="document">
                    <div class="modal-content">
                      <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">取消关注</h4>
                      </div>
                      <div class="modal-body">
                        您确定取消对${followUser.name}的关注？
                      </div>
                      <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button id="btn-cancel-follow" type="button" class="btn btn-primary" data-fan-id="${user.id}" data-follow-user-id="${followUser.id}">确定</button>
                      </div>
                    </div>
                  </div>
                </div>
              </c:if>
            </ul>
          </div>
        </div>
      </c:forEach>
    </div>
  </div>
</div>
</body>
<script>
  $("#btn-cancel-follow").click(function () {
    $.ajax({
      type:"POST",
      url:"/user/cancelFollow",
      data:{
        fanId : $(this).data("fanId"),
        followUserId : $(this).data("followUserId")
      },
      success:function(data){
        window.location.reload();
      }
    });
  });
</script>
</html>
