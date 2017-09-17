<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- ---------------------------------------------------- --%>
<style>
input, textarea, button {
	padding: 4px;
	font-family: 맑은고딕;
	font-size: 9pt;
}
</style>
<div align="center" style="line-height: 35px">
	<h2>
		<a href="/market/list">경매물품 상세보기</a>
	</h2>
	<hr />
	<c:choose>
		<c:when test="${empty pmap }">
			이미 삭제된 물품입니다.
		</c:when>
		<c:otherwise>
			<div
				style="width: 80%; border-radius: 10px; background-color: #e6dfdf; padding: 50px;"
				align="left">
				<input type="hidden" id="no" value="${pmap.NO }" />
				<h3>경매 물품 : ${pmap.PNAME }</h3>
				<p style="padding-left: 10px;">
					시작가 : <fmt:formatNumber value="${pmap.SPRICE }" pattern="#,###" /> 원 <br>
					즉구가 : <fmt:formatNumber value="${pmap.BPRICE }" pattern="#,###" /> 원<br>
					 종료일 : <fmt:formatDate
							pattern="YYYY.MM.dd" value="${pmap.EDATE }" /> <br>
					<c:if test="${!empty pmap.GAB }"> 남은 기간 : <fmt:formatNumber
								value="${pmap.GAB }" pattern="#,##0.00" />일 남음
					</c:if>
				</p>
			</div>
		</c:otherwise>
	</c:choose>
	<hr />
	<h2>입찰기록</h2>

</div>


