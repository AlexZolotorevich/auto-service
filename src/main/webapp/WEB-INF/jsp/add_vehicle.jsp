<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />
<fmt:message bundle="${loc}" key="common.header.home.text" var="home" />
<fmt:message bundle="${loc}" key="common.sales_page.accept.text" var="accept" />


<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<title>add_vehicle</title>
</head>

<body>
<style>
.addvehicle{
	width: 500; /* Ширина слоя в пикселах */
	 
	padding: 10px; /* Поля вокруг текста */
	text-align: center; /* Выравнивание содержимого слоя по левому краю */
	margin-left: auto;
    margin-right: auto;
    display: block;
}

.model {
	clear: both;
	float: left;
	
	margin-left: auto;
    margin-right: auto;
}

.year {
	clear: both;
	float: left;
	
}

.price {
	clear: both;
	float: left;
	
}

.carcase {
	clear: both;
	float: left;
}

.fuel {
	clear: both;
	float: left;
}

.transmission {
	clear: both;
	float: left;
}

.engine-capacity {
	clear: both;
	float: left;
}

.drive-unit {
	clear: both;
	float: left;
}

.mileage {
	clear: both;
	float: left;
}
.input{
	clear: both;
	float: left;
}
.button {
	clear: both;
	float: left;
}
.footer{
	clear: both;
	
}

</style>
	<jsp:include page="header.jsp" />

		<form action="Controller" method="post">
			<div class="addvehicle">
				<input type="hidden" name="command" value="add_vehicle" />

			<div class="model">
				<a class="text-menu">Choose a model:</a> <select name="model">
					<option value="">choose a model</option>
					<option value="a">A</option>
					<option value="b">B</option>
					<option value="c">C</option>
					<option value="e">E</option>
					<option value="g">G</option>
					<option value="gl">GL</option>
					<option value="amg">AMG</option>
				</select>
			</div>

			<div class="year">
				<a class="text-menu">Choose a year:</a> <input
					class="btn btn-outline-secondary" type="text" name="year"
					placeholder="Year"></br>
			</div>

			<div class="price">
				<a class="text-menu">Price:</a> <input
					class="btn btn-outline-secondary" type="text" name="price"
					placeholder="Price"></br>

			</div>

			<div class="carcase">
				<a class="text-menu ">Choose a body:</a> <select name="carcase">
					<option value="">choose a body</option>
					<option value="sedan">sedan</option>
					<option value="coupe">coupe</option>
					<option value="vagon">vagon</option>
				</select>
			</div>

			<div class="transmission">
				<a class="text-menu">Choose a transmission:</a> <select
					name="transmission">
					<option value="">choose a transmission</option>
					<option value="automatic">Automatic</option>
					<option value="manual">Manual</option>
				</select>
			</div>

			<div class="fuel">
				<a class="text-menu">Fuel:</a> <select name="fuel">
					<option value="">choose a fuel</option>
					<option value="petrol">petrol</option>
					<option value="diesel">diesel</option>
				</select>
			</div>

			<div class="engine-capacity">
				<a class="text-menu">Engine capacity:</a> <input
					class="btn btn-outline-secondary" type="text" name="engine"
					placeholder="Engine capacity"></br>
			</div>

			<div class="drive-unit">
				<a class="text-menu">Drive unit:</a> <select name="driveUnit">
					<option value="">choose a drive unit</option>
					<option value="4WD">4WD</option>
					<option value="fwd">front-wheel drive</option>
					<option value="rwd">rear drive</option>
				</select>
			</div>

			<div class="mileage">
				<a class="text-menu">Mileage:</a> <input
					class="btn btn-outline-secondary" type="text" name="mileAge"
					placeholder="mileage"></br>
			</div>
			
			<div class="description">
				<textarea rows="20" cols="120" name="description" placeholder="Your description"></textarea>
			</div>
			
			<div class="input">
			<input type="file" name="image" multiple="multiple" accept="image/jpeg">
			</div>
			
			<div class="user_ID">
				<input type="hidden" name="user_ID" value="${sessionScope.user.id}">
			</div>
			
			<input class="button btn btn-outline-secondary" type="submit"
				value="${accept}" />
			</div>
		</form>
		
		
	

	<div class="footer">
		<jsp:include page="footer.jsp" />
	</div>

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