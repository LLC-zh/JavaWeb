<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/back/common/head.jsp"%>
<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h3>景点攻略管理</h3>
				<Button class="btn btn-success btn-sm" data-toggle="modal"
					data-target="#addScenic" onClick="getScenicNameAdd()">添加</Button>
				<span style="color: red;">${message }</span>
			</div>

			<div class="title_right">
				<div class="col-md-5 col-sm-5 form-group pull-right">
					<form method="get" id="getLocationForm"
						action="${pageContext.request.contextPath }/jsp/back/play.do">
						<span>景点位置</span> 
						<select name="queryScenicLocation" id="playListLocation" onchange="getScenicName()">
							<c:if test="${locationList != null}">
								<option value="">--请选择--</option>
								<c:forEach var="location" items="${locationList}">
									<option value="${location.scenicLocation}" <c:if test="${requestScope.queryScenicLocation eq location.scenicLocation }">selected="selected"</c:if>>${location.scenicLocation}</option>
								</c:forEach>
							</c:if>
						</select> 
						<span>景点名称</span>
						<select name="queryScenicNumber" id="playListScenicName"></select>
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
						景点攻略 <small>景点攻略列表</small>
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
								<th class="column-title">攻略主键id</th>
								<th class="column-title">景点编号</th>
								<th class="column-title">攻略内容</th>
								<th class="column-title">创建时间</th>
								<th class="column-title">更新时间</th>
								<th class="column-title">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="play" items="${playList }" varStatus="loop">
								<tr>
									<td
										style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis">${play.id }</td>
									<td
										style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis">${play.scenicNumber }</td>
									<td
										style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis">${play.playIntroduction }</td>
									<td
										style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis">${play.gmtCreate}</td>
									<td
										style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis">${play.gmtModified}</td>
									<td
										style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis">
										<span style="text-decoration: none;"><a
											href="${pageContext.request.contextPath }/jsp/back/play.do?method=modify&id=${play.id }">修改</a></span>
										<span style="text-decoration: none;"><a
											href="${pageContext.request.contextPath }/jsp/back/play.do?method=delete&id=${play.id }">删除</a></span>
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
								<h4 class="modal-title">增加攻略</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>

							<!-- 模态框主体 -->
							<div class="modal-body" style="width: 500px;">
								<form
									action="${pageContext.request.contextPath }/jsp/back/play.do?method=add"
									id="demo-form2" class="form-horizontal form-label-left"
									method="post">
									<div class="item form-group">
										<label class="col-form-label col-md-3 col-sm-3 label-align"
											for="scenicNumber">景点编号 <span class="required">*</span>
										</label>
										<select id="scenicNumber" name="scenicNumber"></select>
									</div>
									<div class="item form-group">
										<label class="col-form-label col-md-3 col-sm-3 label-align">攻略内容
											<span class="required">*</span>
										</label>
										<div class="col-md-8 col-sm-8 ">
											<textarea id="playIntroduction" required="required"
												name="playIntroduction"></textarea>
										</div>
									</div>
									<div class="ln_solid"></div>
									<div class="item form-group">
										<div
											style="float: none; display: block; margin-left: auto; margin-right: auto;">
											<button class="btn btn-primary" type="reset">重置</button>
											<button type="submit" class="btn btn-success">提交</button>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/playlist.js"></script>
<!-- iCheck -->
<script
	src="${pageContext.request.contextPath }/vendors/iCheck/icheck.min.js"></script>