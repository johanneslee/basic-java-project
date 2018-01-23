<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script>
	//getXMLHttpRequest()함수로 생성한 객체를 저장하기 위해 선언한 전역 변수
	var httpRequest = null;
	
	$(document).ready(function() {
		if(window.XMLHttpRequest) { // Mozilla, Safari, ...
			httpRequest = new XMLHttpRequest();
		} else if (window.ActiveXObject) { // IE
		 	try {
		   		httpRequest = new ActiveXObject("Msxml2.XMLHTTP");
		  	} catch(e) {
		   		try {
		      		httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
		        } 
		        catch (e) {}
		  	}
		}
		
		if (!httpRequest) {
		   	alert('Giving up :( Cannot create an XMLHTTP instance');
		    return false;
		}
		
		httpRequest.onreadystatechange = function() {
			if (httpRequest.readyState === 4) {
				if (httpRequest.status === 200) {
					setTable(httpRequest.responseText);
				} else {
					alert('There was a problem with the request. ' + httpRequest.status);
				}
			}
		}
		httpRequest.open("GET", "/jsp-lottery/api/draw");
		httpRequest.send();
	});
		   	
	function setTable(response) {
		var html = "";
		var jsonObj = JSON.parse(response);
		for(var i = 0;i < jsonObj.length;i++) {
			html = html + "<tr><td>" + jsonObj[i].number + "</td>";
			html = html + "<td>" + jsonObj[i].count + "</td></tr>";
		}
		$("#drawTable > tbody").html(html);
	}
</script>
<style>
body{margin: 0; padding: 0; text-align: center;}
iframe{width: 80%; height: 100%; border: 0;}
.container{width: 80%; height: 100%; margin: 0 auto;}
.row{width: 100%; padding: 6px;}
.text-bar{padding: 6px 12px;}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<p class="text-bar pull-left">집계할 추첨 횟수</p>
			<select name="turns" class="form-control pull-left">
				<option value="0" selected>전체</option>
				<option value="10">최근 10회</option>
				<option value="25">최근 25회</option>
				<option value="50">최근 50회</option>
				<option value="100">최근 100회</option>
				<option value="250">최근 250회</option>
				<option value="500">최근 500회</option>
			</select>
		</div>
		<div class="row">
			<table id="drawTable" class="table table-striped">
				<thead>
					<tr>
						<td class="col-md-6">번호</td>
						<td class="col-md-6">횟수</td>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			
		</div>
	</div>
</body>
</html>