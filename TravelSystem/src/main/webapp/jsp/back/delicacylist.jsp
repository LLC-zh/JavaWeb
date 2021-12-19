<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/back/common/head.jsp"%>
<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h3>美食管理</h3>
				<Button class="btn btn-success btn-sm" data-toggle="modal"
					data-target="#addScenic">添加</Button>
				<span style="color: red;">${message }</span>
			</div>

			<div class="title_right">
				<div class="col-md-5 col-sm-5 form-group pull-right">
					<form action="${pageContext.request.contextPath }/jsp/back/delicacy.do" method="get">
						<input type="text" placeholder="请输入美食名称" name="queryDelicacyName" value="${queryDelicacyName }"/>
						<input type="hidden" name="method" value="query" />
						<input type="hidden" name="pageIndex" value="1" />
						<input value="查 询" type="submit" id="searchbutton">
					</form>
				</div>
			</div>
		</div>

		<div class="clearfix"></div>

		<div class="col-md-12 col-sm-12  ">
			<div class="x_panel">
				<div class="x_title">
					<h2>
						美食 <small>美食列表</small>
					</h2>
					<ul class="nav navbar-right panel_toolbox">
						<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>request	<li><a class="close-link"><i class="fa fa-close"></i></a></li>
					</ul>
					<div class="clearfix"></div>
				</div>
				<div class="x_content">

					<table class="table table-bordered" style="table-layout: fixed;">
						<thead>
							<tr>
								<th class="column-title">美食主键id</th>
								<th class="column-title">美食名称</th>
								<th class="column-title">美食价格</th>
								<th class="column-title">美食介绍</th>
								<th class="column-title">美食图片</th>
								<th class="column-title">创建时间</th>
								<th class="column-title">更新时间</th>
								<th class="column-title">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="delicacy" items="${delicacyList }" varStatus="loop">
								<tr>
									<td
										style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis">${delicacy.id }</td>
									<td
										style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis">${delicacy.delicacyName }</td>
									<td
										style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis">${delicacy.delicacyPrice }</td>
									<td
										style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis">${delicacy.delicacyIntroduction }</td>
									<td
										style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis"><img src="${pageContext.request.contextPath }${delicacy.delicacyImage }" width="100" height="100"></td>
									<td
										style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis">${delicacy.gmtCreate}</td>
									<td
										style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis">${delicacy.gmtModified}</td>
									<td
										style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis">
										<span style="text-decoration: none;"><a
											href="${pageContext.request.contextPath }/jsp/back/delicacy.do?method=modify&id=${delicacy.id }">修改</a></span>
										<span style="text-decoration: none;"><a
											href="${pageContext.request.contextPath }/jsp/back/delicacy.do?method=delete&id=${delicacy.id }">删除</a></span>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<div clss="row">
						<div class="col-md-4 offset-md-8">
							<input type="hidden" id="totalPageCount" value="${totalPageCount}"/>
							<c:import url="rollpage.jsp">
								<c:param name="totalCount" value="${totalCount}"/>
	          					<c:param name="currentPageNo" value="${currentPageNo}"/>
	          					<c:param name="totalPageCount" value="${totalPageCount}"/>
							</c:import>
						</div>
					</div>

				</div>

				<div class="modal fade" id="addScenic">
					<div class="modal-dialog">
						<div class="modal-content">

							<!-- 模态框头部 -->
							<div class="modal-header">
								<h4 class="modal-title">增加美食</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>

							<!-- 模态框主体 -->
							<div class="modal-body" style="width: 500px;">
								<form
									action="${pageContext.request.contextPath }/jsp/back/delicacy.do?method=add"
									id="demo-form2" class="form-horizontal form-label-left"
									enctype="multipart/form-data" method="post">
									<div class="item form-group">
										<label class="col-form-label col-md-3 col-sm-3 label-align"
											for="delicacyName">美食名称 <span class="required">*</span>
										</label>
										<div class="col-md-6 col-sm-6 ">
											<input type="text" id="delicacyName" name="delicacyName"
												required="required" class="form-control "><span id="no_tip"></span>
										</div>
									</div>
									<div class="item form-group">
										<label class="col-form-label col-md-3 col-sm-3 label-align"
											for="delicacyPrice">美食价格 <span class="required">*</span>
										</label>
										<div class="col-md-6 col-sm-6 ">
											<input type="number" id="delicacyPrice" name="delicacyPrice"
												required="required" class="form-control">
										</div>
									</div>
									<div class="item form-group">
										<label class="col-form-label col-md-3 col-sm-3 label-align"
											for="delicacyIntroduction">美食介绍 <span class="required">*</span>
										</label>
										<div class="col-md-6 col-sm-6 ">
											<input type="text" id="delicacyIntroduction"
												name="delicacyIntroduction" required="required"
												class="form-control">
										</div>
									</div>
									<div class="item form-group">
										<label class="col-form-label col-md-3 col-sm-3 label-align"
											for="delicacyImage">美食图片 <span class="required">*</span>
										</label>
										<div class="col-md-6 col-sm-6 ">
											<input type="file" id="delicacyImage" name="delicacyImage"
												required="required" multiple="multiple" size="30">
										</div>
									</div>
									<div class="ln_solid"></div>
									<div class="item form-group">
										<div
											style="float: none; display: block; margin-left: auto; margin-right: auto;">
											<button id = "delicacy_reset" class="btn btn-primary" type="reset">重置</button>
											<button id = "delicacy_add" type="submit" class="btn btn-success">提交</button>
										</div>
									</div>

								</form>
							</div>

							<!-- 模态框底部 -->
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">关闭</button>
							</div>

						</div>
					</div>
				</div>

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
<!-- iCheck -->
<script
	src="${pageContext.request.contextPath }/vendors/iCheck/icheck.min.js"></script>