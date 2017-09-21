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
		<a href="/board/list">게시판</a>
	</h2>
	<hr />
	<c:choose>
		<c:when test="${empty one }">
			이미 삭제된 글입니다.
		</c:when>
		<c:otherwise>
			<div
				style="width: 80%; border-radius: 10px; background-color: #e6dfdf; padding-left: 20px;"
				align="left">
				<input type="hidden" id="num" value="${one.NUM }" />
				<h3>${one.TITLE }</h3>
				<p style="padding-left: 10px;">
					<small>작성자 : ${one.WRITER } | 작성일 : <fmt:formatDate
							pattern="MM.dd.yyyy HH:mm:ss" value="${one.WRITEDATE }" /> | 조회수
						: <fmt:formatNumber value="${one.COUNT }" pattern="#,###" />
					</small>
				</p>
				<pre style="font-family: 맑은 고딕; font-size: 12pt; min-height: 250px;">${one.CONTENT }</pre>
			</div>
		</c:otherwise>
	</c:choose>
	<hr />
	<div style="width: 60%">
		<div align="left">
			<input type="text" id="writer" placeholder="작성자" /> <input
				type="password" id="pass" placeholder="비밀번호" />
		</div>
		<div align="left" style="padding-top: 3px;">
			<textarea style="width: 100%" rows="3" id="content"
				placeholder="남길내용"></textarea>
			<br />
			<button type="button" id="send" style="width: 100%;">댓글남기기</button>
		</div>
		<script>
			document.getElementById("send").onclick = function() {
				var xhr = new XMLHttpRequest();
				xhr.open("post", "/reply/add", true);
				var data = {
					"parent" : ${one.NUM },
					"writer" : document.getElementById("writer").value,
					"pass" : document.getElementById("pass").value,
					"content" : document.getElementById("content").value
				};
				xhr.send(JSON.stringify(data));
				xhr.onreadystatechange = function() {
					if (this.readyState == 4) {
						var obj = JSON.parse(this.responseText);
						if (obj.result == 1) {
							window.alert("등록되었습니다.");
							document.getElementById("writer").value = "";
							document.getElementById("pass").value = "";
							document.getElementById("content").value = "";
							getList();
						} else if(obj.result==0){
							window.alert("등록 과정에서 문제가 발생하였습니다.");
						} else {
							window.alert("댓글 등록후 30초간 새 댓글을 등록할수 없습니다.");
						}
					}
				}
			};
		</script>
	</div>
	<div id="reps" align="left" style="width: 70%;"></div>
	<script>
	var getList  = function() {
		var xhr = new XMLHttpRequest();
		xhr.open("post", "/reply/list/"+ document.getElementById("num").value, true);
		xhr.onreadystatechange = function() {
			if (this.readyState == 4) {
			//	window.alert(this.responseText);
			//	window.alert(typeof this.responseText);
				var obj = JSON.parse(this.responseText);
				var html="";
				for(idx in obj) {
					html += "→ " +obj[idx].WRITER+":"+obj[idx].CONTENT +"<br/>";
				}
				document.getElementById("reps").innerHTML = html;
			}
		}
		xhr.send();
	}
	getList();
	
	</script>
</div>


