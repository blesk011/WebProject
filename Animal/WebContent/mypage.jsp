<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="animal.bean.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<!-- 부트스트랩 부분 -->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Animal</title>
  <link href="./Resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="./Resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <link href="./Resources/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
  <link href="./Resources/css/sb-admin.css" rel="stylesheet">
  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<!-- 메뉴부분 -->
<body>
 <jsp:include page="form.jsp"/>
      <!-- Breadcrumbs-->
  <div class="content-wrapper">
    <div class="container-fluid">
    <br>
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="#">${user_id}님의 뉴스피드</a>
        </li>
      </ol>
      <% String user_id = (String)request.getSession().getAttribute("user_id");
      	if(user_id.equals(request.getAttribute("click_id"))){%>
      	<a href="./Controller?action=news_write"><button type="button" class="btn btn-default">글쓰기</button></a>
      	<a href="./Controller?action=confirm"><button type="button" class="btn btn-default">정보 수정</button></a>
      	<a href="#"><button type="button" class="btn btn-default">스크랩함</button></a>
      <%} %>
          <!-- Card Columns Example Social Feed-->
          <div class="mb-0 mt-4">
            <i class="fa fa-newspaper-o"></i>News Feed</div>
          <hr class="mt-2">
          <div class="card-columns">
            <!-- Example Social Card-->
            <% 
               BoardDBBean board = BoardDBBean.getinstance();
               ArrayList<BoardDataBean> preboarddt =board.news_getlist(request.getParameter("click_id"));
               ArrayList<BoardDataBean> boarddt = new ArrayList<BoardDataBean>();
         
               if(user_id.equals(request.getAttribute("click_id"))){
            	  boarddt = preboarddt;
            	  }
               else{
            	  for(int j = 0; j < preboarddt.size(); j++){
            		if(preboarddt.get(j).getNews_visible() == 0){
            		   boarddt.add(preboarddt.get(j));
            			  }
            		  }
            	 }
               for(int i = 0; i < boarddt.size(); i++){
            	   String image = boarddt.get(i).getBoard_image();
				   String[] images = image.split("/");
            %>
            <div class="card mb-3">
              <a href="#">
                <img class="card-img-top" width="300" height="500" src="<%= boarddt.get(i).getBoard_path()%>\<%=images[0]%>" alt="">
              </a>
              <div class="card-body">
                <h6 class="card-title mb-1"><a href="./Controller?action=mypage&click_id=<%=boarddt.get(i).getUser_id()%>"><%=boarddt.get(i).getUser_id() %></a></h6>
                <h7 class="card-title mb-2"><%=boarddt.get(i).getBoard_title() %></h7>
                <p class="card-text small"><%=boarddt.get(i).getBoard_content() %></p>
              </div>
              <hr class="my-0">
              <div class="card-body py-2 small">
                <a class="mr-3 d-inline-block" href="./Controller?action=news_update&board_num=<%=boarddt.get(i).getBoard_num()%>">
                  <i class="fa fa-fw fa-thumbs-up"></i>Like</a>
                <% if(user_id.equals(request.getAttribute("click_id"))){ %>
                <a class="mr-3 d-inline-block" href="./Controller?action=news_update&board_num=<%=boarddt.get(i).getBoard_num()%>">
                	<i class="fa fa-fw fa-wrench"></i>update</a>
                <a class="mr-3 d-inline-block" href="./Controller?action=news_delete&board_num=<%=boarddt.get(i).getBoard_num()%>">
                	<i class="fa fa-fw fa-wrench"></i>delete</a>
                <%} %>
              </div>
            </div>
            <%} %>
         </div>
        </div>
       </div>
    <!-- /.content-wrapper-->
    <footer class="sticky-footer">
      <div class="container">
        <div class="text-center">
          <small>Copyright © Animal 2017</small>
        </div>
      </div>
    </footer>
</body>
</html>
