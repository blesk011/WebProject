<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <meta charset="utf-8">
  <link href="./Resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="./Resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <link href="./Resources/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
  <link href="./Resources/css/sb-admin.css" rel="stylesheet">
  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <script src="js/bootstrap.js"></script>
<!-- 부트스트랩 부분 -->
<head>
    <title>Animal</title>
</head>
<!-- 메뉴부분 -->
<body class="fixed-nav sticky-footer bg-dark">
  <!-- Navigation-->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <a class="navbar-brand" href="index.jsp"><h2>Animal</h2></a>
    <button class="navbar-toggler navbar-toggler-right" type="button">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav navbar-sidenav">
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Dashboard">
          <a class="nav-link" href="index.html">
            <span class="nav-link-text">고양이 자랑</span>
          </a>
        </li>
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
          <form class="form-inline my-2 my-lg-0 mr-lg-2">
            <div class="input-group">
              <input class="form-control" type="text" placeholder="Search for...">
              <span class="input-group-btn">
                <button class="btn btn-primary" type="button">
                  <i class="fa fa-search"></i>
                </button>
              </span>
            </div>
          </form>
        </li>
        <c:if test="${user_id ne null }">
          <a class="nav-link" href="./Controller?action=mypage&click_id=${user_id}">
            <span class="nav-link-text">My Page</span></a>
         </c:if>
        <li class="nav-item">
          <c:if test="${user_id eq null}">
          	<a class="nav-link" data-toggle="modal" data-target="#exampleModal" href="./Controller?action=login">
            <i class="fa fa-fw fa-sign-in"></i>Login</a>
          </c:if>
          <c:if test="${user_id ne null }">
          	<a class="nav-link" data-toggle="modal" data-target="#exampleModal" href="./Controller?action=logout&user_id=${user_id}">
          	<i class="fa fa-fw fa-sign-out"></i>Logout</a>
          </c:if>
        </li>
      </ul>
    </div>
  </nav>
  <div class="content-wrapper">
    <div class="container-fluid"><br>
</body>

</html>
