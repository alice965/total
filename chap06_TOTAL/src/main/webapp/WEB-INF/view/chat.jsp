<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div align="center" style="margin:10px;">
<h3>OPEN CHAT<span id="cnt"></span></h3>
	<div style="padding:3px; overflow-y:scroll; word-break: break-all;width: 80%; height: 70%; background-color: #D5D5D5; font-size:10pt;" align="left"  id="log">
		
	</div>
	<input type="text" id="f" style="width:80%;  margin-top: 5px; padding:4px; " />
</div>

<script>
	document.getElementById("f").onchange=function(){
		if(this.value.length != 0){
			ws.send(this.value);
			this.value="";
		}
	}
	var ws = new WebSocket("ws://192.168.10.78/ws/chat");
	
	ws.onopen = function(e){
		document.getElementById("log").innerHTML += "<p><b>-----오픈채팅 서버 접속 성공-----</b></p>";
		var obj = JSON.parse(e.data);
		document.getElementById("cnt").innerHTML = "<small>[ " + obj.cnt+ " ] 명</small>";
	}
	ws.onmessage = function(a){
		console.log("a : "+a.data);
		var obj = JSON.parse(a.data);
			document.getElementById("cnt").innerHTML = "<small>[ " + obj.cnt+ " ] 명</small>";
		if(obj.mode == "join"){
			var html = obj.user + "채팅방 입장 <br>";
		}else if(obj.mode =="exit"){
			var html = "<b>[ "+obj.user+ "]</b>님이 퇴장하셨습니다.<br>" ;
		}
		else{
			var html = "<b>[ "+obj.sender+ "]</b>"+obj.msg +"<br>" ;
		}
		
		document.getElementById("log").innerHTML += html;
		document.getElementById("log").scrollTop = document.getElementById("log").scrollHeight;
		
	}

</script>