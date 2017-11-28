<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="slidetag" %>
<!DOCTYPE html>
<html>
<!-- 부트스트랩 부분 -->
<head>
  <title>Animal</title>
</head>
<body>
	<!-- 상단바 -->
	<jsp:include page="form.jsp"/>
	<!-- 슬라이드 -->
	<%@include file="slide.jsp" %>
    <!-- copyright-->
    <footer class="sticky-footer">
      <div class="container">
        <div class="text-center">
          <small>Copyright © Animal 2017</small>
        </div>
      </div>
    </footer>
    <jsp:include page="alert.jsp"/>
</body>
</html>