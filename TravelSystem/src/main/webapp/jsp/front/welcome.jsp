<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>校园旅游攻略首页</title>
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath }/css/first.css"
	rel="stylesheet">
<script src="${pageContext.request.contextPath }/js/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
</head>
<body>
<body>
	<header class="bs-docs-nav navbar navbar-static-top" id="top">
		<c:if test="${sessionScope.userSession != null }">
			<span>欢迎你,${userSession.userName}</span> 
			<a href="${pageContext.request.contextPath }/user.do?action=modify&uuid=${userSession.userUUid}" style="text-decoration: none;">修改密码</a>
			<a href="${pageContext.request.contextPath }/user.do?action=logout" style="text-decoration: none;">登出</a>
		</c:if>
	</header>

	<div class="jumbotron text-center" style="margin-bottom: 0">
		<h1>浙江工商大学杭州商学院校园旅行攻略</h1>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-sm-4">
				<ul class="list-group" style="padding-top: 25px;">
					<li class="list-group-item list-group-item-primary"><a
						href="#">浮光掠影</a></li>
					<li class="list-group-item list-group-item-light"><a
						class="list-item" href="${pageContext.request.contextPath }/home?action=delicacy">美食饕餮</a></li>
					<li class="list-group-item list-group-item-light"><a
						href="${pageContext.request.contextPath }/home?action=note">浮生游记</a></li>
					<li class="list-group-item list-group-item-light"><a
						href="${pageContext.request.contextPath }/home?action=release">发布游记</a></li>
				</ul>
			</div>
			<div class="col-sm-8">
				<c:forEach var="map" items="${scenicAllList }" varStatus="loop">
					<div class="card" style="width: 800px">
						<div class="card-body">
							<h4 class="card-title">${map.scenicName}</h4>
							<span>简介:</span>
							<p class="card-text">${map.scenicDescribe }</p>
							<span>位置:</span>
							<p class="card-text">${map.scenicLocation }</p>
							<span>发布时间:</span>
							<p class="card-text">${map.gmtCreate }</p>
							<button type="button" class="btn btn-primary"
								data-toggle="collapse" data-target="#demo">查看游玩攻略</button>
							<div id="demo" class="collapse">${map.playIntroduction }</div>
						</div>
						<img class="card-img-bottom"
							src="${pageContext.request.contextPath }${map.scenicImage}"
							alt="Card image" style="width: 60%">
					</div></br>
				</c:forEach>
			</div>
		</div>
	</div>

	<div class="totop">
		<a href="#top" class="back-to-top">回到顶端</a>
	</div>

	<div class="jumbotron text-center" style="margin-bottom: 0">
		<p>Copyright © 2018-2021 llc-zh.github.io All Rights Reserved.</p>
	</div>
</body>
</html>