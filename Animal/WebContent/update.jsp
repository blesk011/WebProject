<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="animal.bean.*"%>
<%@ page import="java.util.*"%>
<!-- 
	수정 페이지
	view.jsp에서 수정 버튼을 누르면 넘어오는 페이지.
	
	최종 수정: 2017/11/05
-->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<!-- Bootstrap core CSS-->
<link href="./Resources/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="./Resources/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="./Resources/vendor/datatables/dataTables.bootstrap4.css"
	rel="stylesheet">
<link href="./Resources/css/sb-admin.css" rel="stylesheet">
</head>
<body class="bg-dark">
	<%
		int board_num = 0;

		if (request.getParameter("board_num") != null) {
			board_num = Integer.parseInt(request.getParameter("board_num"));
		}
		CateDBBean cate = CateDBBean.getinstance();
		BoardDataBean board = BoardDBBean.getinstance().getBoard(board_num);
		CateDataBean c = cate.getBoard(board.getCate_num());
	%>
	<div class="container">
		<div class="card mb-3">
			<div class="card-header">
				<i class="fa fa-table"></i>글 수정하기
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<form method="post" enctype="multipart/form-data"
						action="/Animal/Controller?action=BoardUpdate&cate_num=<%=board.getCate_num()%>&board_num=<%=board.getBoard_num()%>">
						<table class="table table-bordered" id="dataTable" width="100%"
							height="30" cellspacing="0">
							<thead>
								<tr>
									<th colspan="2"
										style="background-color: #eeeeee; text-align: center;">게시판
										수정</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="2"><input type="text" class="form-control"
										placeholder="글 제목" name="board_title" maxlength="50"
										value="<%=board.getBoard_title()%>"></td>
								</tr>
								<tr>
									<td><select class="form-control" id="cate_num" name="cate_num">
										<option value="<%=board.getCate_num()%>">카테고리를 선택해주세요</option>
										<%
											ArrayList<CateDataBean> list = cate.getList();
											for (int i = 0; i < list.size(); i++) {
										%>
										<option value="<%=list.get(i).getCate_num()%>" name="cate_num"><%=list.get(i).getCate_name()%></option>
										<%
											}
										%>
									</select>
									</td>
								</tr>
								<tr>
									<td colspan="2"><textarea class="form-control"
											placeholder="글  내용" name="board_content" maxlength="2048"
											style="height: 350px;"><%=board.getBoard_content()%></textarea></td>
								</tr>
								<tr>
									<td colspan="2"><label>수정할 파일 선택</label></td>
								</tr>
								<tr>
									<td colspan="2">
										<%
											String image = board.getBoard_image();
											String[] images = image.split("/");

											for (int i = 0; i < images.length; i++) {
										%> <img src="<%=board.getBoard_path()%>\<%=images[i]%>"
										height=100px width=100px><%=images[i]%><input
										type="checkbox" name="oldfile" value="<%=i%>"><%="<br>"%>
										<%
											}
										%>
									</td>
								</tr>
								<tr>
									<td colspan="2"><label>최대 업로드 파일 수 : 1개(총 개수가 1개
											초과시 가장 마지막에 업로드된 이미지 잘림)</label></td>
								</tr>
								<tr>
									<td colspan="2">파일: <input type="file"
										class="form-control" name="file1"><br>
									</td>
								</tr>
							</tbody>
						</table>
						<input type="submit" class="btn btn-primary pull-right"
							value="글수정">
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>