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
		<a href="/market/list">경매물품 상세보기</a>
	</h2>
	<hr />
	<c:choose>
		<c:when test="${empty pmap }">
			이미 삭제된 물품입니다.
		</c:when>
		<c:otherwise>
			<div
				style="width: 80%; border-radius: 10px; background-color: #e6dfdf; padding: 50px;"
				align="left">
				<input type="hidden" id="no" value="${pmap.NO }" />
				<h3>경매 물품 : ${pmap.PNAME }</h3>
				<p style="padding-left: 10px;">
					경매번호 : ${pmap.NO }<br>
					등록인 : ${pmap.ID}<br>
					시작가 : <fmt:formatNumber value="${pmap.SPRICE }" pattern="#,###"  /> 원 <br>
					즉구가 : <fmt:formatNumber value="${pmap.BPRICE }" pattern="#,###" /> 원<br>
					 종료일 : <fmt:formatDate
							pattern="YYYY.MM.dd" value="${pmap.EDATE }" /> <br>
					<c:if test="${!empty pmap.GAB }"> 남은 기간 : <fmt:formatNumber
								value="${pmap.GAB }" pattern="#,##0.00" />일 남음
					</c:if>
				</p>
			</div>
		</c:otherwise>
	</c:choose>
	<hr />
	<h2>입찰하기</h2>
	입찰가격 : <input type="text" name="bprice" id="bprice"/>
	<button type="button" id="send" style="width: 10%;">입찰</button>
	<hr />
	<!--입찰하기 버튼 클릭시  -->
	<script>
			document.getElementById("send").onclick = function() {
				var bprice =document.getElementById("bprice").value;
				var xhr = new XMLHttpRequest();
				xhr.open("post", "/auction/add", true);
				var data = {
					"pno" : ${pmap.NO },
					"bprice" : document.getElementById("bprice").value
				}
				xhr.send(JSON.stringify(data));
				xhr.onreadystatechange = function() {
					if (this.readyState == 4) {
						var obj = JSON.parse(this.responseText);
						if (obj.result == 1) {
							window.alert("등록되었습니다.");
							document.getElementById("sprice").value = bprice;
							getList();
						} else if(obj.result==0){
							window.alert("입찰 등록 과정에서 문제가 발생하였습니다.");
						} else {
							window.alert("입찰 후 30초간 새 입찰을 등록할수 없습니다.");
						}
					}
				}
			};
		</script>
		
	<h2>입찰기록</h2>
	<div id="bidlist" align="left" style="width: 70%;"></div>
	<!--입찰기록  -->
	<script>
	var getList  = function() {
		var xhr = new XMLHttpRequest();
		xhr.open("post", "/auction/list/"+ document.getElementById("no").value, true);
		xhr.onreadystatechange = function() {
			if (this.readyState == 4) {
				var obj = JSON.parse(this.responseText);
				console.log(obj);
				var html="";
				for(idx in obj) {
					// console.log(obj[idx].BDATE +" / " +typeof obj[idx].BDATE);
					var d = new Date(obj[idx].BDATE);
					html += "→  입찰자 ID : " +obj[idx].BID+"  /  입찰가 : "+obj[idx].BPRICE +" / 입찰일 : "+d+"<br/>";
				}
				document.getElementById("bidlist").innerHTML = html;
			}
		}
		xhr.send();
	}
	getList();
	
	</script>

</div>


