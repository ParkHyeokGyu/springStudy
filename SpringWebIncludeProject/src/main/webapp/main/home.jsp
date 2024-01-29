<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<c:forEach var="vo" items="${list }">
				<div class="col-md-3">
					<a href="../food/detail_before.do?fno=${vo.fno }">
						<div class="panel panel-primary">
			      			<div class="panel-heading" style="white-space: nowrap;text-overflow: ellipsis;overflow: hidden;">${vo.name }</div>
			      			<div class="panel-body">
								<img src="https://www.menupan.com${vo.poster }" style="width: 100%;">
							</div>
			    		</div>
		    		</a>
	    		</div>
			</c:forEach>
		</div>
		<div style="height: 20px;"></div>
		<div class="row">
			<div class="text-center">
				<ul class="pagination">
					<c:if test="${startPage gt 1 }">
						<li><a href="../main/main.do?page=${startPage-1 }">&lt;</a></li>
					</c:if>
					<c:forEach var="i" begin="${startPage }" end="${endPage }">
				  		<li ${curpage eq i ? "class=active" : "" }><a href="../main/main.do?page=${i }">${i }</a></li>
				  	</c:forEach>
				  	<c:if test="${endPage lt totalpage }">
				  		<li><a href="../main/main.do?page=${endPage+1 }">&gt;</a></li>
				  	</c:if>
				</ul> 
			</div>
		</div>
		<div style="height: 20px;"></div>
		<div class="row">
			<h3>최근 방문 맛집</h3>
			<hr>
			<c:if test="${cookiecount gt 0 }">
				<c:forEach var="cvo" items="${cList }" varStatus="s">
					<c:if test="${s.index lt 9 }">
						<img src="https://www.menupan.com${cvo.poster }" style="width: 100px;height: 100px;">
					</c:if>
				</c:forEach>
			</c:if>
		</div>
	</div>
</body>
</html>