<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<h1>SPRING WEB MVC</h1>
	<p id="alt"></p>
</div>
<script>
	var aws = new WebSocket("ws://192.168.10.78/ws/alert");
	
	aws.onmessage = function(a){
		window.alert(a.data);
		document.getElementById("alt").innerHTML = a.data;
		
		var audio = new Audio("/join.wav");
		audio.play();
	}
	document.getElementById("alt").onclick= function(){
		this.innerHTML="";
	}
</script>
