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
	<div style="width: 380px;" align="left">
		<h3>Join HUB</h3>
		<h4>The BestWay to Study Software</h4>
		<h3 style="margin-top: 50px;">CREATE YOUR ACCOUNT</h3>
		<c:if test="${!empty temp }">
			<b style="color:red">join failed..</b>	
		</c:if>
		<form action="/join" method="post" autocomplete="off">
			<p>
				<b>ID</b><br /> <input type="text" name="id" required id="id" /><br />
				<span id="chk_rst"></span>
			</p>
			<p>
				<b>PASS</b><br /> <input type="password" name="pass" required />
			</p>
			<p>
				<b>EMAIL</b><br /> <input type="email" name="email"  id="email" style="width: 40%;" required />
				<button type="button"  id="sendVal" style="width: 30%;">인증번호발송</button><br>
			</p>
			<p id="validation_view" style="display:none">
				<b>Validation</b><small id="left"	 style="color: red; font-weight: bold"></small><br /> 
				
				<br><input type="text" id="vnum" name="vnum"  style="width: 40%;" required />
				<button type="button"  id="vldbtn" style="width: 30%;">확인</button><br>
				<span id="chk_validate"></span>
				
			</p>
			<button id="sbt" type="submit">C R E A T E</button>
		</form>
	</div>
</div>
<script>
	var tot;
	var time;
	document.getElementById("sendVal").onclick = function(){
		var xhr = new XMLHttpRequest();
		xhr.open("post", "/validate/sendMail", true);
		var edata = {
				"email" : document.getElementById("email").value
		}
		xhr.send(JSON.stringify(edata));
		xhr.onreadystatechange = function() {
			if (this.readyState == 4) {
				//console.log("들어온 값 : "+this.responseText);
				var obj = JSON.parse(this.responseText);
				if(obj.result == 1){
					window.alert("입력하신 이메일로 인증번호가 발송되었습니다.");
					document.getElementById("validation_view").style.display = "";
					}else{
						window.alert("이메일 전송 오류가 발생.");	
					}
				}
			}
	}
		//인증확인버튼 클릭시
	document.getElementById("vldbtn").onclick = function(){
		var xhr = new XMLHttpRequest();
		xhr.open("post", "/validate/check", false); // 이메일 요청을 계속하는 것을 방지하기 위해 ㄹ
		var data = {
			"vnum" : document.getElementById("vnum").value
		};
		xhr.send(JSON.stringify(data));
		xhr.onreadystatechange = function() {
			if (this.readyState == 4) {
				var obj = JSON.parse(this.responseText);
				if (obj.result == 1) {
					document.getElementById("chk_validate").innerHTML = "인증번호가 일치합니다.";
				} else {
					document.getElementById("chk_validate").innerHTML = "인증번호가 일치하지 않습니다. ";
				}
			}
		}
		
	};
	var limit = function() {
		var m = Math.floor(tot / 60);
		var s = tot % 60;
		console.log(m + "/" + s);
		document.getElementById("left").innerHTML = m + ":"
				+ (s < 10 ? "0" + s : s);
		tot--;
		console.log(tot);
		if(tot<0) {
			window.alert("인증시간이 초과되었습니다.");
			clearInterval(time);
			//document.getElementById("auth").style.display = "";
			document.getElementById("validation_view").style.display = "none";
		}
	}
</script>
