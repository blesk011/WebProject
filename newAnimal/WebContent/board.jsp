<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="animal.bean.*, java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title></title>
<link href="./Resources/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="./Resources/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="./Resources/vendor/datatables/dataTables.bootstrap4.css"
	rel="stylesheet">
<link href="./Resources/css/sb-admin.css" rel="stylesheet">
<link href="css/paging.css" rel="stylesheet">
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top">
	<!-- Navigation-->
  <jsp:include page="form.jsp" />
  <br>
  <div class="content-wrapper">
  <style type="text/css">
a, a:hover {
	color: #000000;
	text-decoration: none;
}
;
</style>
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
        	<a href="#"></a>
          <%-- <a href="/Animal/Controller?action=boardAction&cate_num=${cate_num}"><c:out value="${cate_name }"></c:out></a> --%>
        </li>
        <li class="breadcrumb-item active">게시판</li>
      </ol>
      <!-- Example DataTables Card-->
      <div class="card mb-3">
 <!--        <div class="card-header">
          <i class="fa fa-table"></i> Data Table Example</div> -->
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" height="30" cellspacing="0">
              <thead>
                <tr>
                  <th style="text-align:center; width:15%;">번호</th>
                  <th style="text-align:center; width:40%;">제목</th>
                  <th style="text-align:center; width:15%;">작성자</th>
                  <th style="text-align:center; width:20%;">작성일</th>
                  <th style="text-align:center; width:10%;">조회</th>
                </tr>
              </thead>
              <tbody>
              <tr>
						<%
							int pageNum = 1;
							if (request.getParameter("pageNumber") != null)
								pageNum = Integer.parseInt(request.getParameter("pageNumber"));
							int cate = 0;
							if (request.getParameter("cate_num") != null)
								cate = Integer.parseInt(request.getParameter("cate_num"));
							int length = 0;
							BoardDBBean board = BoardDBBean.getinstance();
							ArrayList<BoardDataBean> list = null;
							if (cate == 0) {
								list = board.getList(0, pageNum);
								length = board.allCount(cate);
							} else {
								list = board.getList(cate, pageNum);
								length = board.allCount(cate); 
							}
							if (list.size() == 0) {
						%>
						<td colspan="5"align="center">등록된 게시글이 없습니다.</td>
						<%
							} else {
								if (length % 8 == 0)
									length = length / 8;
								else if ((length % 8) != 0)
									length = length / 8 + 1;
								for (int i = 0; i < list.size(); i++) {
						%>
						<td style="text-align:center; width:15%;"><%=list.get(i).getBoard_num() %></td>
						<td style="text-align:center; width:40%;"><a href="/Animal/Controller?action=view&board_num=<%=list.get(i).getBoard_num()%>"><%=list.get(i).getBoard_title().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>")%></a>
						<td style="text-align:center; width:15%;"><a href="/Animal/Controller?action=mypage&click_id=<%=list.get(i).getUser_id()%>"><%=list.get(i).getUser_id()%></a></td>
						<td style="text-align:center; width:20%;"><%=list.get(i).getBoard_date().substring(0, 11) + list.get(i).getBoard_date().substring(11, 13)+ "시" + list.get(i).getBoard_date().substring(14, 16) + "분"%></td>
						<td style="text-align:center; width:10%;"><%=list.get(i).getBoard_hit() %>
					</tr>
					<tr>
						<%
								}
							}
						%>
					</tr>
              </tbody>
            </table>
            <nav>
				<ul class="pagination">
					<li><a href="/Animal/Controller?action=paging&pageNumber=<%=pageNum - 1%>&cate_num=${cate_num}"
						aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
					<%
						for (int j = 0; j < length; j++) {
					%>
					<li><a href="/Animal/Controller?action=paging&pageNumber=<%=(j + 1)%>&cate_num=${cate_num}"><%=(j + 1)%></a></li>
					<%
						}
					%>
					<li><a href="/Animal/Controller?action=paging&pageNumber<%=pageNum + 1%>&cate_num=${cate_num}"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span></a></li>
				</ul>
			</nav>
			<c:if test="${user_id ne null}">
				<a href="write.jsp" class='btn btn-primary pull-right'>글쓰기</a>
			</c:if>
          </div>
        </div>
        <div class="card-footer small text-muted"></div>
      </div>
    </div>
    <!-- /.container-fluid-->
    <!-- /.content-wrapper-->
    <footer class="sticky-footer">
      <div class="container">
        <div class="text-center">
          <small>Copyright © Animal 2017</small>
        </div>
      </div>
    </footer>
    
    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Page level plugin JavaScript-->
    <script src="vendor/datatables/jquery.dataTables.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin.min.js"></script>
    <!-- Custom scripts for this page-->
    <script src="js/sb-admin-datatables.min.js"></script>
  </div>
</body>

</html>