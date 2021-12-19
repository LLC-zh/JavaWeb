<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/back/common/head.jsp"%>
<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h3>景点管理</h3>
			</div>

			<div class="title_right">
				<div class="col-md-5 col-sm-5   form-group pull-right top_search">
					<div class="input-group">
						<input type="text" class="form-control"
							placeholder="Search for..."> <span
							class="input-group-btn">
							<button class="btn btn-default" type="button">Go!</button>
						</span>
					</div>
				</div>
			</div>
		</div>

		<div class="clearfix"></div>

		<div class="col-md-12 col-sm-12  ">
			<div class="x_panel">
				<div class="x_title">
					<h2>修改景点</h2>
					<ul class="nav navbar-right panel_toolbox">
						<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
						</li>
						<li><a class="close-link"><i class="fa fa-close"></i></a></li>
					</ul>
					<div class="clearfix"></div>
				</div>
				<div class="x_content">
					<form action="${pageContext.request.contextPath }/jsp/back/scenic.do?method=modifysave" method="post"
							class="col-md-6 offset-md-4">
					
						<div class="form-group">
							<label for="scenicNumber">景点id:</label>
							<input id="scenicId" type="text" value="${scenic.id }"
							name="scenicId" readonly>
						</div>
						<div class="form-group">
							<label for="scenicNumber">景点编号:</label>
							<input id="scenicNumber" type="text" value="${scenic.scenicNumber }"
							name="scenicNumber">
						</div>
						<div class="form-group">
							<label for="scenicName">景点名称:</label>
							<input id="scenicName" type="text"
							value="${scenic.scenicName }" name="scenicName">
						</div>
						<div class="form-group">
							<label for="scenicImage">景点图片地址:</label>
							<input id="scenicImage" type="text" value="${scenic.scenicImage }" name="scenicImage">
						</div>
						<div class="form-group">
							<label for="scenicLocation">景点位置:</label>
							<input id="scenicLocation" type="text" value="${scenic.scenicLocation }"
							name="scenicLocation">
						</div>
						<div class="form-group">
							<label for="scenicDescribe">景点简述:</label>
							<textarea id="scenicDescribe" type="text" name="scenicDescribe">${scenic.scenicDescribe }</textarea>
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-primary">提交</button>
							<button id="back_btn" type = "button" class="btn btn-info" onClick="location.href='${pageContext.request.contextPath }/jsp/back/scenic.do?method=query'">返回</button>
						</div>
					</form>
				</div>
			</div>

		</div>
	</div>


</div>
</div>
</div>
</div>
<!-- /page content -->

<%@include file="/jsp/back/common/foot.jsp"%>