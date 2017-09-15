<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
input, button {
	width: 100%;
	padding: 5px;
	font-family: 맑은 고딕;
}
b {
	font-size: 12pt;
}
</style>
<div align="center">
	<div style="width: 340px;" align="left">
		<h3>LOGIN TO HUB</h3>
		<c:if test="${!empty temp }">
				<b style="color: red">login failed..</b>
			</c:if>
		<form action="/login" method="post" autocomplete="off">
			<p>
				<b>ID or Email Address</b><br /> <input type="text" name="idmail"
					required id="idmail" /><br /> <span id="chk_rst"></span>
			</p>
			<p>
				<b>PASS</b><br /> <input type="password" name="pass" required />
			</p>
			<button id="sbt" type="submit" style="">L O G I N</button>
		</form>
	</div>
	<div style="width: 340px; margin-top: 20px;" align="center">
		New to HUB? <a href="/join">Create an account.</a>
	</div>
</div>