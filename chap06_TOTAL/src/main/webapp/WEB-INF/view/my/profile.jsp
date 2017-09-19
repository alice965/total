<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.bt {
	padding: 3pt;
	width: 50px;
}
</style>
<div align="center">
	<h3>프로필관리</h3>
	<div>
		<div style="height: 200px; width: 200px;">
			<c:choose>
				<c:when test="${empty pic.URI }">
					<img id="pre" src="/profiles/default.png" alt="기본이미지"
						style="width: 100%; height: 100%; border-radius: 10%;" />
				</c:when>
				<c:otherwise>
					<img id="pre" src="${pic.URI }" alt="사용자프로필"
						style="width: 100%; height: 100%; border-radius: 10%;"/>
						
				</c:otherwise>
			</c:choose>
		</div>
		<div style="margin-top: 20px;">
			<form action="/my/profile" method="post" id="pform"
				enctype="multipart/form-data" style="display: block;">
				<input id="profile" type="file" name="profile" style="display: none" />
				<button type="button" class="bt" id="sbt">적용</button>
				<button type="button" class="bt"
					onclick="javascript:location.reload()">취소</button>
			</form>
		</div>
	</div>
	<div>
		<h4>--- 프로필 히스토리 ---</h4>
		<c:forEach var="obj" items="${list }">
			${obj.NUM}<br>
			<img src="${obj.URI}" style="width: 40%; height: 40%; border-radius: 10%;" /><br>
			<button type="button" class="del" id="del">삭제</button><br>
		</c:forEach>
	</div>
</div>
<script>
	document.getElementById("sbt").onclick = function(){
		if(window.confirm("프로필 변경을 저장하시겠습니까?")) {
			document.getElementById("pform").submit();
		}
	}

	document.getElementById("pre").onclick = function() {
		document.getElementById("profile").click();
	}
	document.getElementById("profile").onchange = function() {
		// console.log(this.files[0]);
		// console.log("변경발생");
		var reader = new FileReader();
		reader.onload = function(e) {
			// console.log(e.target.result);
			document.getElementById("pre").src = e.target.result;
		}
		reader.readAsDataURL(this.files[0]);
	}
</script>


