<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>校园旅游攻略注册</title>
<script src="${pageContext.request.contextPath }/js/jquery-3.6.0.min.js"></script>
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath }/css/first.css"
	rel="stylesheet">
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<style type="text/css">
html, body {
	height: 100%;
}
</style>
<script type="text/javascript">

</script>
</head>
<body>
	<div class="login">
		<div class="login-form">
			<form action="register.do?action=addUser" class="form-horizontal" rol="form"
				method="post">
				<div class="form-group">
					<label for="username" class="col-md-2 control-label">用户名</label>
					<div class="col-md-8">
						<input type="text" class="form-control" id="username"
							name="username" placeholder="请输入用户名,4-8个英文字母或数字或中文"
							style="display: inline; width: 400px;" autocomplete="off">
					</div>
					<div class="col-md-4">
						<label id="errorname" class="control-label errstyle"></label>
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-md-2 control-label">密码</label>
					<div class="col-md-8">
						<input type="password" class="form-control" id="password"
							name="password" placeholder="请输入密码,6-16位英文字母或数字"
							style="display: inline; width: 400px;" autocomplete="off">
					</div>
					<div class="col-md-4">
						<label id="errorpassword" class="control-label errstyle"></label>
					</div>
				</div>
				<div class="form-group">
					<label for="confirm" class="col-md-2 control-label">确认密码</label>
					<div class="col-md-8">
						<input type="password" class="form-control" id="confirm"
							name="confirm" placeholder="确认密码"
							style="display: inline; width: 400px;" autocomplete="off">
					</div>
					<div class="col-md-4">
						<label id="errorconfirm" class="control-label errstyle"></label>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-offset-2 col-md-10">
						<button id="submit_btn" type="submit" class="btn btn-primary">提交</button>
						<button id="reset_btn" type="reset" class="btn btn-default">重置</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/register.js"></script>
</html>