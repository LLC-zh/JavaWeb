<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/back/common/head.jsp"%>
<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h3>攻略管理</h3>
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
					<h2>修改攻略</h2>
					<ul class="nav navbar-right panel_toolbox">
						<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
						</li>
						<li><a class="close-link"><i class="fa fa-close"></i></a></li>
					</ul>
					<div class="clearfix"></div>
				</div>
				<div class="x_content">
					<form action="${pageContext.request.contextPath }/jsp/back/play.do?method=modifysave" method="post"
							class="col-md-6 offset-md-4">
					
						<div class="form-group">
							<label for="scenicNumber">景点攻略id:</label>
							<input id="playId" type="text" value="${play.id }"
							name="playId" readonly>
						</div>
						<div class="form-group">
							<label for="scenicNumber">景点编号:</label>
							<select id="scenicNumber" name="scenicNumber">
								<c:if test="${scenicNumberList != null }">
									<c:forEach var="scenic" items="${scenicNumberList }" varStatus="status">
										<option value="${scenic.scenicNumber }" <c:if test="${play.scenicNumber eq scenic.scenicNumber }">selected="selected"</c:if>>${scenic.scenicName }</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
						<div class="form-group">
							<label for="scenicDescribe">攻略内容:</label>
							<textarea id="playIntroduction" type="text" name="playIntroduction">${play.playIntroduction }</textarea>
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-primary">提交</button>
							<button id="back_btn" type = "button" class="btn btn-info" onClick="location.href='${pageContext.request.contextPath }/jsp/back/play.do?method=query'">返回</button>
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