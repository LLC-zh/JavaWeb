<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>高校校园旅行攻略系统登录</title>
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/css/first.css" rel="stylesheet">
<script src="${pageContext.request.contextPath }/js/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<style type="text/css">
html,body{
	height: 100%;
}
</style>
</head>
<body>
	<div class="login">
		<div class="login-form">
			<h3 class="title">高校校园旅行攻略系统</h3>
			<span style="color: red;">${message }</span>
			<form action="welcome.do" class="form-horizontal" rol="form" method="post">
				<div class="form-group">
					<label for="username" class="col-sm-2 control-label">用户名</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="username" name="userName"
							placeholder="请输入用户名" style="display: inline; width: 400px;"
							autocomplete="off">
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-2 control-label">密码</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="password" name="userPassword"
							placeholder="请输入密码" style="display: inline; width: 400px;"
							autocomplete="off">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button id = "login_btn"type="submit" class="btn btn-primary" >登录</button>
						<button id = "register_btn" type="button" class="btn btn-default" ><a href="${pageContext.request.contextPath }/register.jsp" style="text-decoration: none;">注册</a></button>
						<button id = "reset_btn" type="reset" class="btn btn-default">重置</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>