<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div>
		<ul class="pagination">
			<li>共${totalCount }条记录&nbsp;&nbsp; ${currentPageNo }/${totalPageCount }页</li>
			<c:if test="${currentPageNo > 1}">
				<a href="javascript:page_nav(document.forms[0],1);">首页</a>
				<a
					href="javascript:page_nav(document.forms[0],${param.currentPageNo-1});">上一页</a>
			</c:if>
			<c:if test="${currentPageNo < totalPageCount }">
				<a
					href="javascript:page_nav(document.forms[0],${param.currentPageNo+1 });">下一页</a>
				<a
					href="javascript:page_nav(document.forms[0],${param.totalPageCount });">最后一页</a>
			</c:if>
		  &nbsp;&nbsp;
		</ul>
		<span><label>跳转至</label> <input
			type="number" name="inputPage" id="inputPage" />页
			<button type="button" onClick='jump_to(document.forms[0],document.getElementById("inputPage").value)'>GO</button>
		</span>
	</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/rollpage.js"></script>
</html>