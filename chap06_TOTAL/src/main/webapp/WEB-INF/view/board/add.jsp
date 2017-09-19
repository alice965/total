<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import= "java.util.*" %>
<%@ page import="java.io.*"%>




<div align="center" style="line-height: 25px;">
	<h2> 게시글 작성 </h2>
	<form action="/board/add" method="post">
	<b>글 제목</b> <br>
	<input type="text" name="title"  placeholder="글 제목 입력" /><br />
	<b>작성자</b> <br>
	<input type="text" name="writer"  placeholder="작성자 입력" /><br />
	<b>내용 입력</b> <br>
	<textarea rows="10" name="content"  placeholder="내용을 입력하세요" ></textarea>
	
	<br />
	
	<button type="submit" style="width: 50px;">등록</button>
	<button type="reset" style="width: 50px;">취소</button>
	
	</form>
	
</div>
