
<%@ page language="java" import="javax.servlet.jsp.PageContext"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" type="text/css"
	href="static/css/magnific-popup.css">
<link rel="stylesheet" type="text/css" href="static/css/test.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>Insert title here</title>

</head>

<body>


	<div id="zatemnenie">
		<div id="okno">
			Всплывающее окошко!<br> <a href="#" class="close">Закрыть окно</a>
		</div>
	</div>

	<a href="#zatemnenie">Вызвать всплывающее окно</a>

<div><h1>ghbdtn</h1></div>
	<div class="container-fluid p-0" >
		<div id="carouselExampleIndicators" class="carousel slide"
		data-ride="carousel">
			<ol class="carousel-indicators">
				<li class="active" data-target="#carouselExampleIndicators" 
				data-slide-to="0">
				</li>
				<li data-target="#carouselExampleIndicators" data-slide-to="1">
				</li>
				<li data-target="#carouselExampleIndicators" data-slide-to="2">
				</li>
				<li data-target="#carouselExampleIndicators" data-slide-to="3">
				</li>
			</ol>
			<div class="carousel-inner">
			
				<div class="carousel-item active">
					<img src="static/images/img_01.png" alt="" class="d-block w-100">
				</div>
				<div class="carousel-item">
					<img src="static/images/img_02.png" alt="" class="d-block w-100">
				</div>
				<div class="carousel-item">
					<img src="static/images/img_03.png" alt="" class="d-block w-100">
				</div>
				<div class="carousel-item">
					<img src="static/images/img_04.png" alt="" class="d-block w-100">
				</div>
			</div>
			
			<a href="#carouselExampleIndicators" class="carousel-control-prev" 
			role="button" data-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a>
			<a href="#carouselExampleIndicators" class="carousel-control-next" 
				role="button" data-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>				
			
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
	<script src="static/js/script.js"></script>

</body>

</html>