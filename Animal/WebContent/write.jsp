<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="animal.bean.CateDBBean, animal.bean.CateDataBean, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<!-- 부트스트랩 부분 -->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">
  <!-- Bootstrap core CSS-->
   <link href="./Resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="./Resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <link href="./Resources/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
  <link href="./Resources/css/sb-admin.css" rel="stylesheet">
</head>
<body class="bg-dark">
  <div class="container">
  	<div class="card mb-3">
  		<div class="card-header">
  			<i class="fa fa-table"></i>글쓰기</div>
  		<div class="card-body">
  			<div class="table-responsive">
  			 <form name=writetable method="post" action="/Animal/Controller?action=writeAction" enctype="multipart/form-data">
  				<table class="table table-bordered" id="dataTable" width="70%" cellpadding="0">
  					<tr>
  						<td>글 제목</td>
  					</tr>
  					<tr>
  						<td><input class="form-control" type="text" aria-describedby="board_title" name="board_title" placeholder="제목"></td>
  					</tr>
  					<tr>
  						<td>카테고리</td>
  					</tr>
  					<tr>
  						<td> <select class="form-control" id="cate" name="cate_num">
						<option value="">카테고리를 선택해 주세요</option>
						<% 
							CateDBBean cate = CateDBBean.getinstance();
							ArrayList<CateDataBean> list = cate.getList(); //카테고리
							for(int i = 0; i < list.size(); i++){
						%>
							<option value="<%=list.get(i).getCate_num()%>" name="cate_num"><%=list.get(i).getCate_name() %></option>
						<%
							}
						%>
						</select></td>
					</tr>
					<tr>
						<td>내용</td>
					</tr>
					<tr>
						<td><textarea rows="20" cols="20" class="form-control"></textarea></td>
					</tr>
					<tr>
						<td>사진</td>
					</tr>
					<tr>
						<td><input class="form-control" type="file" aria-describedby="board_image" placeholder="ID" ></td>
					</tr>
					<tr>
						<td><input type="submit" class="btn btn-primary btn-block" value="글쓰기"></td>
						<td><a class="btn btn-primary btn-block" href="board.jsp">취소</a></td>
					</tr>
  				</table>
  			</form>
  			</div>
  		</div>
  	</div>
  	<jsp:include page="alert.jsp" />
  	</div>
  <!-- Bootstrap core JavaScript-->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
  
</body>

</html>