<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/back/common/head.jsp"%>

	<!-- page content -->
	<div class="right_col" role="main">
		<div class="">
			<div class="page-title">
				<div class="title_left">
					<h3>
						概览 <small>景点概览</small>
					</h3>
				</div>
			</div>

			<div class="clearfix"></div>

			<div class="col-md-6 col-sm-6  ">
				<div class="x_panel">
					<div class="x_title">
						<h2>景点数量占比饼状图</h2>
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="collapse-link"><i
									class="fa fa-chevron-up"></i></a></li>
							<li><a class="close-link"><i class="fa fa-close"></i></a>
							</li>
						</ul>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">

						<div id="scenic_pie" style="height: 350px;"></div>

					</div>
				</div>
			</div>
			
			<div class="col-md-6 col-sm-6  ">
				<div class="x_panel">
					<div class="x_title">
						<h2>景点数量柱状图</h2>
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="collapse-link"><i
									class="fa fa-chevron-up"></i></a></li>
							<li><a class="close-link"><i class="fa fa-close"></i></a>
							</li>
						</ul>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">

						<div id="scenic_bar" style="height: 350px;"></div>

					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- /page content -->
<%@include file="/jsp/back/common/foot.jsp" %>
<!-- ECharts -->
<script src="${pageContext.request.contextPath }/vendors/echarts/dist/echarts.min.js"></script>
<!-- 自定义图表-->
<script src="${pageContext.request.contextPath }/js/myEcharts.js"></script>