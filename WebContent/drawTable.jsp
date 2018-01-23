<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*" contentType="text/html; charset=utf-8" %>
<jsp:useBean id="dbUtil" class="com.johanneslee.util.DbUtil" scope="page" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="col-md-12">
		<table class="table table-striped">
			<thead>
				<tr>
					<td class="col-md-6">번호</td>
					<td class="col-md-6">횟수</td>
				</tr>
			</thead>
			<tbody>
			<%
				List<Map<String, String>> list = dbUtil.drawNums();
				for(Map<String, String> map : list) {
			%>
				<tr>
					<td class="col-md-6"><%=map.get("number")%></td>
					<td class="col-md-6"><%=map.get("count")%></td>
				</tr>
			<%
				}
			%>
			</tbody>
		</table>
	</div>
</body>
</html>