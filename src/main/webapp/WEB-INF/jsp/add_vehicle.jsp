<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />
<fmt:message bundle="${loc}" key="common.header.home.text" var="home" />
<fmt:message bundle="${loc}" key="common.sales_page.accept.text"
	var="accept" />

<fmt:message bundle="${loc}" key="common.error.checkModel.text"
	var="incorrectModel" />
<fmt:message bundle="${loc}" key="common.error.checkYear.text"
	var="incorrectYear" />
<fmt:message bundle="${loc}" key="common.error.checkCarcase.text"
	var="incorrectCarcase" />
<fmt:message bundle="${loc}" key="common.error.checkPrice.text"
	var="incorrectPrice" />
<fmt:message bundle="${loc}" key="common.error.checkTransmission.text"
	var="incorrectTransmission" />
<fmt:message bundle="${loc}" key="common.error.checkTypeFuel.text"
	var="incorrectTypeFuel" />
<fmt:message bundle="${loc}" key="common.error.checkEngineCapacity.text"
	var="incorrectEngineCapacity" />
<fmt:message bundle="${loc}" key="common.error.checkDriveUnit.text"
	var="incorrectDriveUnit" />
<fmt:message bundle="${loc}" key="common.error.checkMileage.text"
	var="incorrectMileage" />


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
.addvehicle {
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

.input {
	clear: both;
	float: left;
}

.button {
	clear: both;
	float: left;
}

.footer {
	clear: both;
}
.errors{
	margin-left: 500px;
	width: 400px;
 	border-color: #484848; /* Цвет границы */
    border-style: solid; /* Стиль границы */
    padding: 5px; /* Поля вокруг текста */
    color: #484848;
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
				<textarea rows="5" cols="60" name="description"
					placeholder="Your description"></textarea>
			</div>

			<div class="input">
				<input type="file" name="image" multiple="multiple"
					accept="image/jpeg">
			</div>

			<div class="user_ID">
				<input type="hidden" name="user_ID" value="${sessionScope.user.id}">
			</div>

			<input class="button btn btn-outline-secondary" type="submit"
				value="${accept}" />
		</div>

		<i> <c:if test="${not empty requestScope.errorMessage}">
				<div class="errors">
					<c:forEach items="${requestScope.errorMessage}" var="error">
						<c:if test="${error.contains('model')}">
							<c:out value="${incorrectModel}" />
						</c:if>

						<c:if test="${error.contains('year')}">
							<c:out value="${incorrectYear}" />
						</c:if>

						<c:if test="${error.contains('carcase')}">
							<c:out value="${incorrectCarcase}" />
						</c:if>

						<c:if test="${error.contains('price')}">
							<c:out value="${incorrectPrice}" />
						</c:if>

						<c:if test="${error.contains('transmission')}">
							<c:out value="${incorrectTransmission}" />
						</c:if>

						<c:if test="${error.contains('typeFuel')}">
							<c:out value="${incorrectTypeFuel}" />
						</c:if>

						<c:if test="${error.contains('engineCapacity')}">
							<c:out value="${incorrectEngineCapacity}" />
						</c:if>

						<c:if test="${error.contains('driveUnit')}">
							<c:out value="${incorrectDriveUnit}" />
						</c:if>

						<c:if test="${error.contains('mileage')}">
							<c:out value="${incorrectMileage}" />
						</c:if>
					</c:forEach>
				</div>
			</c:if>
		</i><br />
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