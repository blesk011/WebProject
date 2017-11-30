<!-- 
신고관리 페이지
작성자 : 정은진
수정자:
최종수정일 : 17.11.16
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<jsp:include page="/mngr/managerMain.jsp" />
<div id="declarationManage">
		<ul>
			<li>신고글 수 : ${count}</li>
		</ul>
		<table class='table table-striped' style='border: 1px solid #dddddd' height='100'>
			<tr>
				<th>게시글 번호</th>
				<th>사용자 아이디</th>
				<th>신고 내용</th>
				<th>게시글 삭제</th>
			</tr>
			<c:forEach var="declar" items="${declarList}">
				<tr>
					<td>${declar.board_num}</td>
					<td>${declar.user_id}</td>
					<td>${declar.declaration_content}</td>
					<td><a href="/Animal/MngrDeclarAction?action=boardDelete&board_num=${declar.board_num}">게시글 삭제</a></td>
					<td><a href="/Animal/MngrDeclarAction?action=banUser&user_id=${declar.user_id}">사용자 활동 정지</a></td>
				</tr>
			</c:forEach>
		</table>
</div>
<center><button onclick="location.href='mngr/managerMain.jsp'" class = "btn btn-default" align="center">돌아가기</button></center>