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
	<h2>회원 목록</h2>
	<p align="right" style="margin-right: 30px;">
		총 <b>${cnt }</b> 명의 회원이 등록되어있습니다.
	</p>
	<table style="width: 95%">
		<thead>
			<tr>
				<th style="width: 30%">프로필</th>
				<th style="width: 30%">아이디</th>
				<th style="width: 40%">이메일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="obj" items="${list }">
				<tr>
					<td>
						<c:choose>
							<c:when test="${empty obj.URI }">
									<img id="pre" src="/profiles/default.png" alt="기본이미지"
										style="width: 50px; height: 50px; border-radius: 10%;" />
							</c:when>
							<c:otherwise>
									<img id="pre" src="${obj.URI }" alt="사용자프로필"
										style="width: 50px; height:50px; border-radius: 10%;" />
							</c:otherwise>
						</c:choose>
					</td>
					<td>${obj.ID }</td>
					<td>${obj.EMAIL }</td>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<c:if test="${param.page ne 1 }">

			<a href="/info/mlist?page=${param.page -1 }" style="text-decoration: none">
				<b style="color: #9c9892;">◀</b>
			</a>	
		</c:if>
		<c:forEach var="i" begin="1" end="${size }" varStatus="vs">
			<c:choose>
				<c:when test="${i eq param.page }">
					<b style="color: #ff9800;">${i }</b>
				</c:when>
				<c:otherwise>
					<a href="/my/list?page=${i }" style="text-decoration: none">
					<b style="color: #9c9892;">${i }</b></a>	
				</c:otherwise>
			</c:choose>
			<c:if test="${!vs.last }"> | </c:if>
		</c:forEach>
		<c:if test="${param.page ne last }">▷</c:if>
	</div>
	
</div>