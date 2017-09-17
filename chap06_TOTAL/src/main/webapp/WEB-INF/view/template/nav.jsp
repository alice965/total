<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="right">
	<c:choose>
		<c:when test="${empty auth }">
			<a href="/login">로그인</a> | <a href="/join">회원가입</a>
		</c:when>
		<c:otherwise>
			<p>
				<b>${auth.ID } <i>(${auth.NAME })</i>, LOG ON</b>
			</p>
			<p>
				<a href="/my/info">내정보</a> | <a href="/my/profile">프로필</a>
				| <a href="/board/list">게시판</a>
			</p>
		</c:otherwise>
	</c:choose>
</div>