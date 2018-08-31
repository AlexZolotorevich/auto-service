<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />
<fmt:message bundle="${loc}" key="common.header.home.text" var="home" />
<fmt:message bundle="${loc}" key="common.header.signin.text"
	var="signin" />
<fmt:message bundle="${loc}" key="common.header.signup.text"
	var="signup" />
<fmt:message bundle="${loc}" key="common.header.sales.text" var="sales" />
<fmt:message bundle="${loc}" key="common.header.service.text"
	var="service" />
<fmt:message bundle="${loc}" key="common.header.about_us.text"
	var="about" />
<fmt:message bundle="${loc}" key="common.header.search.text"
	var="search" />
<fmt:message bundle="${loc}" key="common.header.welcome.text"
	var="welcome" />
<fmt:message bundle="${loc}" key="common.header.signout.text"
	var="signout" />
	<fmt:message bundle="${loc}" key="common.header.profile.text"
	var="toProfile" />
	
	
	
<html>
<head>
<link rel="stylesheet" type="text/css" href="static/css/main.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>header</title>
</head>

<body>
<style>

.searching {
	
	height: 1000 px;
	padding: 8px;
	
}

.menu{
	width: 800px;
	height: 300 px;
	width: 500px;
}

.lang{
	padding: 5px; /* Поля вокруг текста */
    float: left; /* Обтекание по правому краю */
}

.profile{
	padding: 5px; /* Поля вокруг текста */
	text-align: right;
}

.signing{
	padding: 5px; /* Поля вокруг текста */
    text-align: right;
    
}

.signuping{
	padding: 5px; /* Поля вокруг текста */
	text-align: right;
}    
.text-color{
	color: white;
	padding: 5px; /* Поля вокруг текста */
    text-align: right;
    
}
</style>



	<nav class="navbar navbar-expand-lg navbar-dark bg-dark"> <a
		href="Controller?command=main_page" command="main_page"
		class="navbar-brand"> <img src="static/images/logo.png"
		height="70" width="70" border="0">
	</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="menu navbar-nav mr-auto">
			<li class="nav-item active"><a
				href="Controller?command=main_page" class="nav-link">${home}</a></li>
			<li class="nav-item"><a href="Controller?command=sales_page"
				class="nav-link">${sales}</a></li>
			<li class="nav-item"><a href="Controller?command=service_page" class="nav-link">${service}</a></li>
			<li class="nav-item"><a href="Controller?command=about_us" class="nav-link">${about}</a></li>
		</ul>
		
		<c:if test="${(empty sessionScope.user) and (empty sessionScope.admin)}">
		<div class="sign">
		<div class="signing">
			<form action="Controller" method="get">
				<input type="hidden" name="command" value="to_sign_in_page" /> <input
					class="btn btn-outline-secondary" type="submit" name="signin"
					value="${signin}" />
			</form>
			</div>
			<div class="signuping">
			<form action="Controller" method="get">
				<input type="hidden" name="command" value="to_sign_up_page" /> <input
					class="btn btn-outline-secondary" type="submit" name="signup"
					value="${signup}" />
			</form>
			</div>
		</div>
		</c:if>
		
		<div class="profile">
			<c:if test="${not empty sessionScope.user}">
			<form action="Controller" method="get">
				<input type="hidden" name="command" value="to_profile_page" /> <input
					class="btn btn-outline-secondary" type="submit" value="${toProfile}" />
					</form>
			</c:if>
		</div>
		
		<div class="profile">
			<c:if test="${not empty sessionScope.admin}">
			<form action="Controller" method="get">
				<input type="hidden" name="command" value="to_profile_page_admin" /> <input
					class="btn btn-outline-secondary" type="submit" value="${toProfile}" />
					</form>
			</c:if>
		</div>
		
		
		<c:if test="${not empty sessionScope.user}">
		<div class="sign">
		<div class="text-color">
			<c:out value="${welcome} ${sessionScope.user.login}!"/>
		</div>
		<div class="sign">
		<div class="signuping">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="sign_out" /> <input
					class="btn btn-outline-secondary" type="submit" name="signout"
					value="${signout}" />
			</form>
			</div>
			</div>
		</div>
		</c:if>
		
		<c:if test="${not empty sessionScope.admin}">
			<div class="sign">
			<div class="text-color">
				<c:out value="${welcome} ${ sessionScope.admin.login}!"/>
			</div>
			<div class="sign">
			<div class="signuping">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="sign_out" /> <input
					class="btn btn-outline-secondary" type="submit" name="signout"
					value="${signout}" />
			</form>
			</div>
			</div>
		</div>
		</c:if>
		

		<div class="searching ">
				<form class="form-inline my-2 my-lg-0" action="Controller"
					method="post">
					<input type="text" class="form-control mr-sm-2"
						placeholder=${search} 
						aria-label="Search">
					<button class="btn btn-outline-success my-2 my-sm-0">${search}</button>
				</form>
				
				
			
			<div class="lang ">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="localization" /> <input
					class="btn btn-outline-secondary" type="submit" name="local"
					value="en" /> <input class="btn btn-outline-secondary"
					type="submit" name="local" value="ru" />
			</form>
		</div>
			
		</div>
			
		
	</div>
	</nav>


	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
		integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
		crossorigin="anonymous"></script>

</body>
</html>