<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<div>
	<div>	
		<h3>��������</h3>
		<form action="/my/info" method="post">
			<p>
				<b>NAME</b><br /> <input type="text"
					value="<c:if test="${map.NAME != null}">  ${map.NAME} </c:if> " name="name" required />
			</p>
			<p>
				<b>GENDER</b><br /> 
					<input type="radio" name="gender" value="��"
					<c:if test="${map.GENDER eq '��'}">  "checked" </c:if>
					required />�� 
					
					<input
					type="radio" name="gender" value="��"
					<c:if test="${map.GENDER eq '��'}">  "checked" </c:if>
					required />�� 
			</p>
			<p>
				<b>BIRTH</b><br /> <select name="birth">
					
					<option value="1" >2017</option>
					
				</select>
			</p>
			<p>
				<b>ADDRESS</b><br /> <input type="text" name="address" size="50"
					value="<c:if test="${map.ADDRESS != null}">  ${map.ADDRESS} </c:if> " name="address" required />
			</p>
			
			<button type="submit">��������</button>
		</form>
	</div>
</div>