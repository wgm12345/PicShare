<%@ page import="com.picshare.entity.Image" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: wgm
  Date: 17/3/21
  Time: 上午10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>欢迎来到 PicShare!</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/statics/bootstrap-3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/bootstrap-3.3.7/css/bootstrap.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/site.css">

</head>
<body>

  <jsp:include page="/layout/topNav" flush="true">
	  <jsp:param name="user" value= "${sessionScope.user}" ></jsp:param>
  </jsp:include>
  <jsp:include page="/layout/header" flush="true"></jsp:include>
  <div class="i_body">
    <div class="main">
      <!--main-->
      <div class="container-fluid i-box">

        <div class="row">
          <div class="col-md-12">
            <h4 class="text-info">最近投稿</h4>
            <hr/>
            <div class="row" id="latestSubmit">
              <c:forEach items="${imgLatestSubmitTop20}" var="img">
                <div class="col-md-4">
                  <a href="${pageContext.request.contextPath}/image/id=${img.id}" class="image-wrap"><img src="${pageContext.request.contextPath}/statics/images/master/${img.imgPath}" class="img-thumbnail image-thumb" /></a>
                  <a href="${pageContext.request.contextPath}/image/id=${img.id}">
                    <br />${img.uploader.name}<br />${img.createDateTime}
                  </a>
                </div>
              </c:forEach>
            </div>
            <div class="row ">
              <button class="btn center-block"><span class="glyphicon glyphicon-menu-down">更多</span></button>
            </div>
          </div>
        </div>

        <hr>

        <div class="row">
          <div class="col-md-12">
            <h4 class="text-info">本周最热</h4>
            <hr/>
            <div class="row" id="hottestSubmit">
              <c:forEach items="${imgWeekHitTop20}" var="img">
                <div class="col-md-4">
                  <a href="${pageContext.request.contextPath}/image/id=${img.id}" class="image-wrap"><img src="${pageContext.request.contextPath}/statics/images/master/${img.imgPath}" class="img-thumbnail image-thumb" /></a>
                  <a href="${pageContext.request.contextPath}/image/id=${img.id}">
                    <br />${img.uploader.name}<br />${img.createDateTime}
                  </a>
                </div>
              </c:forEach>
            </div>
            <div class="row ">
              <button class="btn center-block"><span class="glyphicon glyphicon-menu-down">更多</span></button>
            </div>
          </div>
        </div>

        <hr>
        <c:if test = "${sessionScope.user != null}">
        <div class="row">
          <div class="col-md-12">
            <h4 class="text-info">为你推荐</h4>
            这里做个性化推荐
          </div>
        </div>
        </c:if>
      </div>
    </div>

    <div class="sub">
      <div class="i-box">
        <p>公告栏</p>
        <ul>
          <li>
            <a>公告1 标题</a>
          </li>
          <li>
            <a>公告2 标题</a>
          </li>
        </ul>
      </div>
      <div class="i-box">
        <p>用户信息栏</p>
        <c:if test="${user != null}">
          <img class="img-thumbnail" src="/statics/images/sculpture/${user.headImgPath}">
          <p>欢迎您，</p>
          <p>${user.name}</p>
          <a href="/user/personalPage/uid=${user.id}">>>进入空间</a>
        </c:if>
        <c:if test="${user == null}">
          <a href="/user/signIn">>>请登录</a>
        </c:if>
      </div>
    </div>

    <div class="extra">
      <!--  旋转图片-->
      <%--显示内容暂时用一周最热的前五张--%>
      <div class="i-box" style="padding:5px;">
        <div id="carousel-example-generic" class="carousel slide " data-ride="carousel">
          <!-- Indicators -->
          <ol class="carousel-indicators">
            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
            <%--<li data-target="#carousel-example-generic" data-slide-to="3"></li>--%>
            <%--<li data-target="#carousel-example-generic" data-slide-to="4"></li>--%>
          </ol>

          <!-- Wrapper for slides -->
          <div class="carousel-inner" role="listbox">
            <!--  example
		<div class="item active">
			<a href="~/Views/Home/About">
				<img src="~/Images/Home/pic1.jpg" alt="..." class="img-thumbnail center-block">
			</a>
			<div class="carousel-caption">

			</div>
		</div>
		-->
            <div class="item active">
              <a href="/image/id=${imgWeekHitTop20[0].id}">
                <img src="/statics/images/master/${imgWeekHitTop20[0].imgPath}" alt="..." class=" center-block home-rollback-img-size">
              </a>
              <div class="carousel-caption"></div>
            </div>
            <div class="item">
              <a href="/image/id=${imgWeekHitTop20[1].id}">
                <img src="/statics/images/master/${imgWeekHitTop20[1].imgPath}" alt="..." class=" center-block home-rollback-img-size">
              </a>
            </div>
            <div class="item">
              <a href="/image/id=${imgWeekHitTop20[2].id}">
                <img src="/statics/images/master/${imgWeekHitTop20[2].imgPath}" alt="..." class=" center-block home-rollback-img-size">
              </a>
            </div>

          </div>
          <!-- Controls -->
          <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
          </a>
          <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
          </a>
        </div>
      </div>

      <div class="i-box">
        <hr />
        <ul class="list-group">
          <li class="list-group-item-heading">
            <div class="row">
              <div class="col-md-6">动画区总排行</div>
            </div>
          </li>
          <c:forEach items="${imgOrderByAnimateHits}" var="image">
            <li class="list-group-item" >
              <div class="row">
                <div class="col-md-10">
                  <img class="img-thumbnail" src="/statics/images/master/${image.imgPath}" />
                </div>
                <div class="col-md-12">
                  <a class="col-md-6" href="/image/id=${image.id}">
                    ${image.name}
                  </a>
                  <span class="col-md-6">
                    <span class="glyphicon glyphicon-facetime-video"></span>${image.totalHits}
                    <span class="glyphicon glyphicon-comment"></span>${image.commentsSum}
                  </span>
                </div>
              </div>
            </li>
          </c:forEach>
        </ul>
      </div>

      <div class="i-box">
        <hr />
        <ul class="list-group">
          <li class="list-group-item-heading">
            <div class="row">
              <div class="col-md-6">游戏区排行</div>
            </div>
          </li>
          <c:forEach items="${imgOrderByGameHits}" var="image">
            <li class="list-group-item" >
              <div class="row">
                <div class="col-md-10">
                  <img class="img-thumbnail" src="/statics/images/master/${image.imgPath}" />
                </div>
                <div class="col-md-12">
                  <a class="col-md-6" href="/image/id=${image.id}">
                      ${image.name}
                  </a>
                  <span class="col-md-6">
                    <span class="glyphicon glyphicon-facetime-video"></span>${image.totalHits}
                    <span class="glyphicon glyphicon-comment"></span>${image.commentsSum}
                  </span>
                </div>
              </div>
            </li>
          </c:forEach>
        </ul>
      </div>
    </div>
  </div>

  <hr />
  <jsp:include page="/layout/footer" flush="true"></jsp:include>
</body>
<script src="/statics/js/index.js"></script>
</html>
