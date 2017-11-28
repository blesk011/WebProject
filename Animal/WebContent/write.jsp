<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- 부트스트랩 부분 -->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>login</title>
  <!-- Bootstrap core CSS-->
  <link href="./Resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="./Resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <link href="./Resources/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
  <link href="./Resources/css/sb-admin.css" rel="stylesheet">
</head>
<body class="bg-dark">
  <div class="container">
    <div class="card card-register mx-auto mt-5">
      <div class="card-header">글쓰기</div>
      <form>
      <div class="card-body">
          <div class="form-group">
            <label for="exampleInputEmail1">글제목</label>
            <input class="form-control" type="text" aria-describedby="emailHelp" placeholder="ID">
          </div>
          <div class="form-group">
            <label for="exampleInputEmail1">카테고리</label>
            <input class="form-control" type="text" aria-describedby="emailHelp" placeholder="ID">
          </div>
          <div class="form-group">
            <label for="exampleInputEmail1">내용</label>
           	<textarea rows="20" cols="20" class="form-control"></textarea>
          </div>
          <div class="form-group">
            <label for="exampleInputEmail1">사진/동영상</label>
           	<input class="form-control" type="file" aria-describedby="emailHelp" placeholder="ID">
          </div>
          </div>
          <a class="btn btn-primary btn-block" href="login.html">글쓰기</a>
        </form>
      </div>
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