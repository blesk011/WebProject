<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="animal.bean.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <meta charset="utf-8">
  <link href="/Animal/Resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="/Animal/Resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <link href="/Animal/Resources/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
  <link href="/Animal/Resources/css/sb-admin.css" rel="stylesheet">
  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <script src="/Animal/js/bootstrap.js"></script>
  <link href="/Animal/css/customfont.css" rel="stylesheet">
<!-- 부트스트랩 부분 -->
<head>
    <title>Animal</title>
</head>
<!-- 메뉴부분 -->
<body class="fixed-nav sticky-footer bg-dark">
  <!-- Navigation-->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <a class="navbar-brand" href="/Animal/index.jsp"><h1>Animal<img src="images/foot.png" width="50" height="30"></h1></a>
    <div class="collapse navbar-collapse" id="navbarResponsive">
  	  <ul class="navbar-nav navbar-sidenav"><br>
    	<%
			ArrayList<CateDataBean> cateList = CateDBBean.getinstance().getList();
    		for(CateDataBean cate : cateList) {
		%>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Dashboard">
          <a class="nav-link" href="/Animal/index.html">
            <span class="nav-link-text"><h3><%=cate.getCate_name()%></h3></span>
          </a>
        </li>
        <%} %>
      </ul>
      <ul class="navbar-nav sidenav-toggler">
        <li class="nav-item">
          <a class="nav-link text-center" id="sidenavToggler">
            <i class="fa fa-fw fa-angle-left"></i>
          </a>
        </li>
      </ul>
      <ul class="navbar-nav ml-auto">
        <li class="nav-item">
          <form class="form-inline my-2 my-lg-0 mr-lg-2" method="post" action="/Animal/Controller?action=searchAction">
            <div class="input-group">
              <input class="form-control" type="text" placeholder=Search id="searchKeyword" name="searchKeyword">
              <span class="input-group-btn">
                <button class="btn btn-primary" type="button">
                  <i class="fa fa-search"></i>
                </button>
              </span>
            </div>
          </form>
        </li>
        <c:if test="${user_id ne null}">
          <c:if test="${user_available >= '2'}">
            <a class="nav-link" href="/Animal/mngr/mngrForm.jsp">
            	<h5><span class="nav-link-text">Manage</span></h5></a>
          </c:if>
          <a class="nav-link" href="/Animal/Controller?action=mypage&click_id=${user_id}">
            <h5><span class="nav-link-text">My Page</span></h5></a>
         </c:if>
        <li class="nav-item">
          <c:if test="${user_id eq null}">
          	<a class="nav-link" href="/Animal/Controller?action=login">
            <h5><i class="fa fa-fw fa-sign-in"></i>Login</h5></a>
          </c:if>
          <c:if test="${user_id ne null }">
          	<a class="nav-link" href="/Animal/Controller?action=logout&user_id=${user_id}">
          	<h5><i class="fa fa-fw fa-sign-out"></i>Logout</h5></a>
          </c:if>
        </li>
      </ul>
    </div>
  </nav>
</body>

</html>
