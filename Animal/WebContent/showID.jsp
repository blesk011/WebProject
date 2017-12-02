<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="animal.bean.UserDBBean" %>
    
<!DOCTYPE html>
<html>
<!-- 부트스트랩 부분 -->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>Animal</title>
  <link href="./Resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="./Resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <link href="./Resources/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
  <link href="./Resources/css/sb-admin.css" rel="stylesheet">
</head>
<%
	  request.setCharacterEncoding("utf-8");
	  String user_name = request.getParameter("user_name");
	  String user_phone = request.getParameter("user_phone");
	  UserDBBean userdbbean = UserDBBean.getinstance();
	  String user_id = userdbbean.searchId(user_name, user_phone);
%>
<body class="bg-dark">
  <div class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header">아이디 확인</div>
      	<%if(user_id!= null){ %>
			<center>
			<br/><br/>			
	      		<div class="card-body">
	          		<div class="form-group">
						<%=user_name %>님의 아이디는<br/>
					</div>
					<div class="form-group">
						<h1><%=user_id %></h1>	<br/>			
					</div>
					<div class="form-group">						
						입니다. </div>
				</div></center>
	       <div class="text-center">
	          <a class="d-block small mt-3" href="./Controller?action=login">로그인</a>
	          <a class="d-block small" href="./Controller?action=forgetPW">비밀번호 찾기</a>
	        </div>	
      				
      	<%} else{%>
			<div class="form-group">
				<br/><br/><br/>
				<center><h3>가입정보가 없습니다.</h3></center>				
			</div><br/><br/><br/><br/>
			<center>				
       			<input type="button" value="회원가입 하기" class="btn btn-primary" onclick="location.href='./Controller?action=join'">&emsp;
        		<input type="button" value="처음으로" class="btn btn-primary" onclick="location.href='./Controller?action=forgetID'">     							
			</center>
		<%}%>
        </div>
      </div>
  <!-- Bootstrap core JavaScript-->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
  <jsp:include page="alert.jsp"/>
  
</body>

</html>
