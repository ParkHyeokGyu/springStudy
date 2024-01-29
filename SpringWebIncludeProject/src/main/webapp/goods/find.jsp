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
			<form method="post" action="../goods/find.do">
			<select name="colname" class="input-sm">
				<option value="goods_name" ${colname eq "goods_name" ? "selected" : "" }>상품명</option>
			</select>
			<input type="text" name="ss" size="20" class="input-sm" value="${ss }">
			<input type="submit" value="검색" class="btn btn-sm btn-danger">
			</form>
		</div>
		<div style="height: 20px;"></div>
		<div class="row">
			<c:forEach var="vo" items="${list }">
				<div class="col-md-3">
					<a href="../goods/detail.do?no=${vo.no }">
						<div class="panel panel-danger">
							<div class="panel-heading">${vo.goods_price }</div>
							<div class="panel-body text-center">
								<img src="${vo.goods_poster }" title="${vo.goods_name }" style="width: 100%;">
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
						<li><a href="../goods/find.do?page=${startPage-1 }&colname=${colname }&ss=${ss }">&lt;</a></li>
					</c:if>
					<c:forEach var="i" begin="${startPage }" end="${endPage }">
				  		<li ${curpage eq i ? "class=active" : "" }><a href="../goods/find.do?page=${i }&colname=${colname }&ss=${ss }">${i }</a></li>
				  	</c:forEach>
				  	<c:if test="${endPage lt totalpage }">
				  		<li><a href="../goods/find.do?page=${endPage+1 }&colname=${colname }&ss=${ss }">&gt;</a></li>
				  	</c:if>
				</ul> 
			</div>
		</div>
	</div>
</body>
</html>