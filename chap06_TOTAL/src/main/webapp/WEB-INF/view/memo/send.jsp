<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>

<div align="center">

	<h2>쪽지 보내기</h2>

	<form action="/memo/send" method="post">
	
		<b>받을 사람</b><br />
		<input type="text" name="receiver"   placeholder="받을 아이디입력" autocomplete="off"/><br /> 
		
		<b>보낼 내용</b><br /> 
		<textarea rows="6" name="content" placeholder="보낼 내용을 입력하세요"></textarea>
		<br />
		
		<button type="submit" style="width: 169px;">쪽지 전송</button>
	</form>
</div>