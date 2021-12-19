<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/back/common/head.jsp"%>
<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h3>景点管理</h3>
				<Button class="btn btn-success btn-sm" data-toggle="modal"
					data-target="#addScenic">添加</Button>
				<span style="color: red;">${message }</span>
			</div>

			<div class="title_right">
				<div class="col-md-5 col-sm-5 form-group pull-right">
					<form method="get" id="getLocationForm"
						action="${pageContext.request.contextPath }/jsp/back/scenic.do">
						<span>景点位置</span> 
						<select name="queryScenicLocation">
							<c:if test="${locationList != null}">
								<option value="">--请选择--</option>
								<c:forEach var="location" items="${locationList}">
									<option value="${location.scenicLocation}" <c:if test="${requestScope.queryScenicLocation eq location.scenicLocation }">selected="selected"</c:if>>${location.scenicLocation}</option>
								</c:forEach>
							</c:if>
						</select> 
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
						景点 <small>景点列表</small>
					</h2>
					<ul class="nav navbar-right panel_toolbox">
						<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
						</li>
						<li><a class="close-link"><i class="fa fa-close"></i></a></li>
					</ul>
					<div class="clearfix"></div>
				</div>
				<div class="x_content">

					<table class="table table-bordered" style="table-layout: fixed;">
						<thead>
							<tr>
								<th class="column-title">景点主键id</th>
								<th class="column-title">景点编号</th>
								<th class="column-title">景点名称</th>
								<th class="column-title">景点图片地址</th>
								<th class="column-title">景点位置</th>
								<th class="column-title">景点简述</th>
								<th class="column-title">创建时间</th>
								<th class="column-title">更新时间</th>
								<th class="column-title">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="scenic" items="${scenicList }" varStatus="loop">
								<tr>
									<td
										style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis">${scenic.id }</td>
									<td
										style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis">${scenic.scenicNumber }</td>
									<td
										style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis">${scenic.scenicName }</td>
									<td
										style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis"><img src="${pageContext.request.contextPath }${scenic.scenicImage }" width="100" height="100"></td>
									<td
										style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis">${scenic.scenicLocation }</td>
									<td
										style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis">${scenic.scenicDescribe }</td>
									<td
										style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis">${scenic.gmtCreate}</td>
									<td
										style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis">${scenic.gmtModified}</td>
									<td
										style="white-space: nowrap">
										<span style="text-decoration: none;"><a
											href="${pageContext.request.contextPath }/jsp/back/scenic.do?method=modify&id=${scenic.id }">修改</a></span>
										<span style="text-decoration: none;"><a
											href="${pageContext.request.contextPath }/jsp/back/scenic.do?method=delete&id=${scenic.id }">删除</a></span>
										<Button class="btn btn-success btn-sm" data-toggle="modal" data-target="#repushImage" onclick="getRepushId(${scenic.id})">重传</Button>
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
								<h4 class="modal-title">增加景点</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>

							<!-- 模态框主体 -->
							<div class="modal-body" style="width: 500px;">
								<form
									action="${pageContext.request.contextPath }/jsp/back/scenic.do?method=add"
									id="demo-form2" class="form-horizontal form-label-left"
									enctype="multipart/form-data" method="post">
									<div class="item form-group">
										<label class="col-form-label col-md-3 col-sm-3 label-align"
											for="scenic-number">景点编号 <span class="required">*</span>
										</label>
										<div class="col-md-6 col-sm-6 ">
											<input type="text" id="scenic-number" name="scenic-number"
												required="required" class="form-control "><span id="no_tip"></span>
										</div>
									</div>
									<div class="item form-group">
										<label class="col-form-label col-md-3 col-sm-3 label-align"
											for="scenic-name">景点名称 <span class="required">*</span>
										</label>
										<div class="col-md-6 col-sm-6 ">
											<input type="text" id="scenic-name" name="scenic-name"
												required="required" class="form-control">
										</div>
									</div>
									<div class="item form-group">
										<label class="col-form-label col-md-3 col-sm-3 label-align"
											for="scenic-image">景点图片 <span class="required">*</span>
										</label>
										<div class="col-md-6 col-sm-6 ">
											<input type="file" id="scenic-image" name="scenic-image"
												required="required" multiple="multiple" size="30">
										</div>
									</div>
									<div class="item form-group">
										<label class="col-form-label col-md-3 col-sm-3 label-align"
											for="scenic-location">景点位置 <span class="required">*</span>
										</label>
										<div class="col-md-6 col-sm-6 ">
											<input type="text" id="scenic-location"
												name="scenic-location" required="required"
												class="form-control">
										</div>
									</div>
									<div class="item form-group">
										<label class="col-form-label col-md-3 col-sm-3 label-align">景点简述
											<span class="required">*</span>
										</label>
										<div class="col-md-8 col-sm-8 ">
											<textarea id="scenic-describe" required="required"
												name="scenic-describe"></textarea>
										</div>
									</div>
									<div class="ln_solid"></div>
									<div class="item form-group">
										<div
											style="float: none; display: block; margin-left: auto; margin-right: auto;">
											<button id = "scenic_reset" class="btn btn-primary" type="reset">重置</button>
											<button id = "scenic_add" type="submit" class="btn btn-success">提交</button>
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
 
				<!-- 模态框 -->
				<div class="modal fade" id="repushImage">
				  <div class="modal-dialog">
				    <div class="modal-content">
				 
				      <!-- 模态框头部 -->
				      <div class="modal-header">
				        <h4 class="modal-title">重传文件</h4>
				        <button type="button" class="close" data-dismiss="modal">&times;</button>
				      </div>
				 
				      <!-- 模态框主体 -->
				      <div class="modal-body">
				        <form action="${pageContext.request.contextPath }/jsp/back/scenic.do?method=repushsave" enctype="multipart/form-data"
				        	method="post">
				        	<input id="repushId" type="hidden" name="scenicId">
			        		<div class="item form-group">
								<label class="col-form-label col-md-3 col-sm-3 label-align"
									for="repushImage">景点图片 <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 ">
									<input type="file" id="repushImage" name="repushImage"
										required="required" multiple="multiple" size="30">
								</div>
							</div>
							<div class="item form-group">
								<div
									style="float: none; display: block; margin-left: auto; margin-right: auto;">
									<button id = "scenic_reset" class="btn btn-primary" type="reset">重置</button>
									<button id = "scenic_add" type="submit" class="btn btn-success">提交</button>
								</div>
							</div>
			        	</form>
				      </div>
				 
				      <!-- 模态框底部 -->
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/sceniclist.js"></script>
<!-- iCheck -->
<script
	src="${pageContext.request.contextPath }/vendors/iCheck/icheck.min.js"></script>