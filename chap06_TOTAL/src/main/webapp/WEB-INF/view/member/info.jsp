<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div align="center" style="line-height: 25px;">
	<h2> MY INFO : 
	</h2>
	<div>
		<h3>프로필사진</h3>
		<form action="/member/pic_update.jsp" method="post" enctype="multipart/form-data">
			<p>
				<img src="" style="border-radius: 100%; width: 150px; height: 150px;"/>
			</p>
			<p>
				<input type="file" name="pic"/><button type="submit">변경</button>
			</p>
		</form>
	</div>
	<div>	
		<h3>세부정보</h3>
		<form action="/member/my_info_rst.jsp" method="get">
			<p>
				<b>NAME</b><br /> <input type="text"
					value="<c:if test="${map.NAME != null}">  ${map.NAME} </c:if> " name="name" required />
			</p>
			<p>
				<b>GENDER</b><br /> 
					<input type="radio" name="gender" value="남"
					<c:if test="${map.GENDER eq '남'}">  "checked" </c:if>
					required />남 
					
					<input
					type="radio" name="gender" value="여"
					<c:if test="${map.GENDER eq '여'}">  "checked" </c:if>
					required />여 
			</p>
			
			<button type="submit">정보변경</button>
			<a href="/member/drop.jsp"><button type="button">회원탈퇴</button></a>
		</form>
	</div>
</div>








`
