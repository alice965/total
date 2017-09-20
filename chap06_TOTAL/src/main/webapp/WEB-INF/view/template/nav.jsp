<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="right">
	<c:choose>
		<c:when test="${empty auth_id }">
			<a href="/login">Log In</a> | <a href="/join">Join</a> | <a href="/chat">Chat</a>
		</c:when>
		<c:otherwise>
			<p>
				<b>${auth.ID } <i>(${auth.NAME })</i>, LOG ON</b>
			</p>
			<p>
				<a href="/logout">Log Out</a> | <a href="/my/info">My Info</a> | 
				<a href="/my/profile">Profile</a>	| <a href="/my/list">Member List</a> | 
				<a href="/board/list">Board</a> | <a href="/market/list">Market</a>
				
			</p>
		</c:otherwise>
	</c:choose>
</div>