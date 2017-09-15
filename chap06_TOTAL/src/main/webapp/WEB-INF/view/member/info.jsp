<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div align="center" style="line-height: 25px;">
	<h2> MY INFO 	</h2>
	<div align="center">
		<h3>프로필관리</h3>
		<div style="height: 200px; width: 200px;">
			<img id="pre" src="/" alt="기본이미지" style="width: 100%; height: 100%" />
		</div>
		<div style="margin-top: 20px;">
			<form action="/member/info" method="post"
				enctype="multipart/form-data" style="display: block;">
				<input id="profile" type="file" name="profile" style="display: none" />
				<input type="text" name="nick" />
				<button type="submit" class="bt">적용</button>
				<button type="button" class="bt">취소</button>
			</form>
		</div>
	</div>


<script>
	document.getElementById("pre").onclick=function() {
		document.getElementById("profile").click();
	}
</script>








