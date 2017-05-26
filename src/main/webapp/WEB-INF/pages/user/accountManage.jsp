<%--
  Created by IntelliJ IDEA.
  User: wgm
  Date: 17/5/9
  Time: 上午12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>账户管理</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/bootstrap-3.3.7/css/bootstrap.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/site.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery/jquery-1.10.2.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/statics/bootstrap-3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #ffffff">
<jsp:include page="/layout/topNav" flush="true">
  <jsp:param name="user" value= "${sessionScope.user}" ></jsp:param>
</jsp:include>
<jsp:include page="/layout/user_header" flush="true"></jsp:include>
<div class="user-info-wrap">
  <h3>账户管理</h3>
  <hr>
  <!-- Button trigger modal -->
  <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
    修改密码
  </button>

  <!-- Modal -->
  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" id="myModalLabel">修改密码</h4>
        </div>
        <div class="modal-body">
          <div class="label-warning" id="warning-change-password"></div>
          <form method="POST" action="">
            <div class="row">
              <label class="">新密码：</label><input id="newPassword" name="newPassword" type="password">
            </div>
            <div class="row">
              <label>新密码确认：</label><input id="newPasswordAgain"  name="newPasswordAgain" type="password">
            </div>
          </form>
          <div class="label-success" id="success-change-password"></div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          <button id="change-password" type="button" class="btn btn-primary">修改密码</button>
        </div>
      </div>
    </div>
  </div>
</div>

</body>
<script>
  $("#change-password").click(function(){
    var newPassword = $("#newPassword").val();
    var newPasswordAgain = $("#newPasswordAgain").val();
    if(newPassword == newPasswordAgain){
      $.ajax({
        type:"POST",
        url:"/user/changePassword",
        data:{
          newPassword:newPassword,
          userId:${user.id}
        },
        success:function(data){
          $("#success-change-password").append(data["success"]);
        }
      })
    }else{
      $("#warning-change-password").append("密码不一致");
    }
  });
</script>
</html>
