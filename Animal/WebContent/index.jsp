<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- 부트스트랩 부분 -->
<head>
  <title>Animal</title>
  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <script src="js/bootstrap.js"></script>
  <script type="text/javascript">
	function logout(){
		var user_id = $('#user_id').val();
		$.ajax({
			type: 'POST',
			url: './Controller',
		})
	}
	</script>
</head>
<body>
	<!-- 상단바 -->
	<jsp:include page="form.jsp"/>
	<div class="container" align="center">
	 <div id="myCarousel" class="carousel slide" data-ride="carousel">
	 	<ol class="carousel-indicators">
	 		<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
	 		<li data-target="#myCarousel" data-slide-to="1" ></li>
	 		<li data-target="#myCarousel" data-slide-to="2" ></li>
	 	</ol>
	 	<div class="carousel-inner">
	 		<div class="item active">
	 			<img src="images/cat.jpg" width="300" height="300">
	 		</div>
	 		<div class="item">
	 			<img src="images/cat2.jpg" width="300" height="300">
	 		</div>
	 		<div class="item">
	 			<img src="images/dog.jpg" width="300" height="300">
	 		</div>
	 	</div>
	 		<a class="left carousel-control" href="#myCarousel" data-slide="prev">
	 			<span class="glyphicon glyphicon-chevron-left"></span>
	 		</a>
	 		<a class="right carousel-control" href="#myCarousel" data-slide="next">
	 			<span class="glyphicon glyphicon-chevron-right"></span>
	 		</a>
	 </div>
	</div>
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