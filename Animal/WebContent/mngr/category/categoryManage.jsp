<!-- 
카테고리관리 페이지
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
<div id="categoryManage">
		<ul>
			<li>카테고리 수 : ${count}</li>
		</ul>
		<table class='table table-striped' style='border: 1px solid #dddddd' height='100'>
			<tr>
				<th>카테고리 이름</th>
				<th>카테고리 생성일</th>
				<th>카테고리 생성</th>
				<th>카테고리 삭제</th>
			</tr>
			<c:forEach var="category" items="${categoryList}">
				<tr>
					<td>${category.cate_num}</td>
					<td>${category.cate_date}</td>
					<td><a href="/Animal/MngrCategoryAction?action=newCategory">카테고리 생성</a></td>
					<td><a href="/Animal/MngrCategoryAction?action=deleteCategory&cate_id=${category.cate_num}">카테고리 삭제</a></td>
				</tr>
			</c:forEach>
		</table>
</div>
<center><button onclick="location.href='mngr/managerMain.jsp'" class = "btn btn-default" align="center">돌아가기</button></center>