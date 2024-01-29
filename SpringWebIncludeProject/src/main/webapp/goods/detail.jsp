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
			<table class="table">
				<tr>
					<td width="40%" class="text-center" rowspan="4">
						<img src="${vo.goods_poster }" style="width: 100%;">
					</td>
					<td colspan="2">
						<h3>${vo.goods_name }&nbsp;<span style="color: orange;">${vo.goods_sub }</span></h3>
					</td>
				</tr>
				<tr>
					<th width="10%" class="text-right">구매가</th>
					<td width="50%">${vo.goods_price }</td>
				</tr>
				<tr>
					<th width="10%" class="text-right">첫 구매가</th>
					<td width="50%">${vo.goods_first_price }</td>
				</tr>
				<tr>
					<th width="10%" class="text-right">배송</th>
					<td width="50%">${vo.goods_delivery }</td>
				</tr>
				<tr>
					<td colspan="3" class="text-right">
						<a href="javascript:history.back()" class="btn btn-xs btn-primary">목록</a>
					</td>
				</tr>
			</table>
		</div>
		<div style="height: 20px;"></div>
		<div class="col-md-8">
			<table class="table">
				<tr>
					<td>
						<c:forEach var="rvo" items="${rList }">
							<table class="table">
								<tr>
									<td class="text-left">◑${rvo.name }(${rvo.dbday })</td>
									<td class="text-right">
										<c:if test="${rvo.id eq sessionScope.id }">
											<span class="btn btn-xs btn-info updates" data-no="${rvo.no }">수정</span>
											<a href="../reply/reply_delete.do?no=${rvo.no }&fno=${vo.no }&typeno=1" class="btn btn-xs btn-success">삭제</a>
										</c:if>
									</td>
								</tr>
								<tr>
									<td colspan="2" class="text-left" valign="top">
										<pre style="white-space: pre-wrap;border: none;background-color: white;">${rvo.msg }</pre>
									</td>
								</tr>
								<tr style="display: none;" id="u${rvo.no }" class="ups">
									<td colspan="2">
										<form method="post" action="../reply/reply_update.do">
											<input type="hidden" name="no" value="${rvo.no }">
											<input type="hidden" name="typeno" value="1">
											<input type="hidden" name="fno" value="${vo.no }">
											<textarea rows="4" cols="65" name="msg" style="float: left;">${rvo.msg }</textarea>
											<button class="btn-primary" style="width: 100px;height: 104px;float: left;">댓글수정</button>
										</form>
									</td>
								</tr>
							</table>
						</c:forEach>
					</td>
				</tr>
			</table>
			<c:if test="${sessionScope.id ne null }">
				<table class="table">
					<tr>
						<td>
							<form method="post" action="../reply/reply_insert.do">
								<input type="hidden" name="fno" value="${vo.no }">
								<input type="hidden" name="typeno" value="1">
								<textarea rows="4" cols="65" name="msg" style="float: left;"></textarea>
								<button class="btn-primary" style="width: 100px;height: 104px;float: left;">댓글쓰기</button>
							</form>
						</td>
					</tr>
				</table>
			</c:if>
		</div>
	</div>
</body>
</html>