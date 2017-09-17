<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<style>
th {
	border-bottom: 1px solid;
	text-align: left;
}
th, td {
	padding: 10px;
}
</style>
<div align="center" style="line-height: 35px">
	<h2>경매 물품 목록</h2>
	<p align="right" style="margin-right: 30px;">
		총 <b>${cnt }</b> 개의 물품이 등록되어있습니다.
	</p>
	<table style="width: 95%">
		<thead>
			<tr>
				<th style="width: 10%">번호</th>
				<th style="width: 35%">물품명</th>
				<th style="width: 10%">시작가</th>
				<th style="width: 10%">즉구가</th>
				<th style="width: 15%">종료일</th>
				<th style="width: 10%">남은기간</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="obj" items="${list }">
				<tr>
					<td>${obj.NO }</td>
					<td><a href="/market/view/${obj.NO}">${fn:substring(obj.PNAME, 0, 12) }
					</a></td>
					<td><fmt:formatNumber value="${obj.SPRICE }" pattern="#,###" /></td>
					<td><fmt:formatNumber value="${obj.BPRICE }" pattern="#,###" /></td>
					<td><c:if test="${!empty obj.EDATE }">~<fmt:formatDate
								value="${obj.EDATE }" pattern="yyyy.MM.dd" />
						</c:if></td>
					<td><c:if test="${!empty obj.GAB }">(<fmt:formatNumber
								value="${obj.GAB }" pattern="#,##0.00" />일 남음)
					</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p align="right" style="margin-right: 30px;">
		<a href="/market/add"><button type="button" style="padding: 5px;">물품등록</button></a>
	</p>
	
	<form action="/market/search">
		가격조건: <input type="number" name="sprice" required style="width: 100px;" 
			value="${obj.SPRICE }">
		이하 (<input type="checkbox" name="mode" />경매물품만)
		<button type="submit">검색</button>
	</form>
	
</div>