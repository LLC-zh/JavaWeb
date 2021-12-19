<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/back/common/head.jsp"%>
<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h3>美食管理</h3>
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
					<h2>修改美食</h2>
					<ul class="nav navbar-right panel_toolbox">
						<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
						</li>
						<li><a class="close-link"><i class="fa fa-close"></i></a></li>
					</ul>
					<div class="clearfix"></div>
				</div>
				<div class="x_content">
					<form action="${pageContext.request.contextPath }/jsp/back/delicacy.do?method=modifysave" method="post"
							class="col-md-6 offset-md-4">
					
						<div class="form-group">
							<label for="delicacyId">美食id:</label>
							<input id="delicacyId" type="text" value="${delicacy.id }"
							name="delicacyId" readonly>
						</div>
						<div class="form-group">
							<label for="delicacyName">美食名称:</label>
							<input id="delicacyName" type="text" value="${delicacy.delicacyName }"
							name="delicacyName">
						</div>
						<div class="form-group">
							<label for="delicacyPrice">美食价格:</label>
							<input id="delicacyPrice" type="text"
							value="${delicacy.delicacyPrice }" name="delicacyPrice">
						</div>
						<div class="form-group">
							<label for="delicacyIntroduction">美食介绍:</label>
							<input id="delicacyIntroduction" type="text" value="${delicacy.delicacyIntroduction }" name="delicacyIntroduction">
						</div>
						<div class="form-group">
							<label for="delicacyImage">美食图片地址:</label>
							<input id="delicacyImage" type="text" value="${delicacy.delicacyImage }"
							name="delicacyImage">
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-primary">提交</button>
							<button id="back_btn" type = "button" class="btn btn-info" onClick="location.href='${pageContext.request.contextPath }/jsp/back/delicacy.do?method=query'">返回</button>
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