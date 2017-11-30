<!-- 
메니저 메인페이지
작성자: 정은진
수정자: 정은진(11.19)
수정자 : 정은진
수정내용 : 회원 관리 부분(js포함)
최종수정일: 17.11.23
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src= "http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="/Animal/mngr/managermain.js"></script>
	<div id="mList">
		<ul>
			<li>회원 관리
			<li><button id="member">전제회원정보</button><!-- 전체 회원을 출력하는 리스트 출력 및 특정회원을 활동정지나 강제탈퇴 할수있음 -->
			<li><button id="mBanned">활동 정지 맴버</button> <!-- 강제 탈퇴된 맴버 리스트를 출력 -->
		</ul>
		
		<ul>
			<li>스탭 관리
			<li><button id="staff">스탭</button> <!-- 각 페이지마다 스탭을 임명하는 페이지 -->
			
		</ul>
		
		<ul>
			<li>메뉴 관리
			<li><button id="menu">메뉴 관리</button>
		</ul>
		
		<ul>
			<li>신고 관리
			<li><button id="spam">신고처리 게시글</button> <!--  신고처리된 글을 볼수있고, 삭제할것인지  판단할수 있는 테이블 생성  -->
			<li><button id="deleted">삭제된 글/댓글 </button> <!-- 삭제된글의 리스트 출력 -->
		</ul>

	</div>
