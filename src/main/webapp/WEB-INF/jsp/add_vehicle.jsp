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

<fmt:message bundle="${loc}" key="common.sales_page.choosePicture.text"
	var="choosePicture" />	

<fmt:message bundle="${loc}" key="common.salespage.chooseModel.text"
	var="chooseModel" />
<fmt:message bundle="${loc}" key="common.salespage.chooseYear.text"
	var="chooseYear" />	
<fmt:message bundle="${loc}" key="common.salespage.price.text"
	var="choosePrice" />	
	<fmt:message bundle="${loc}" key="common.salespage.typeCarcase.text"
	var="chooseCarcase" />
<fmt:message bundle="${loc}" key="common.salespage.typeTransmission.text"
	var="chooseTransmission" />
	<fmt:message bundle="${loc}" key="common.salespage.typeFuel.text"
	var="chooseTypeFuel" />
	<fmt:message bundle="${loc}" key="common.salespage.chooseDriveUnit.text"
	var="chooseDriveUnit" />
	<fmt:message bundle="${loc}" key="common.salespage.engineCapacity.text"
	var="chooseEngineCapacity" />
	<fmt:message bundle="${loc}" key="common.salespage.mileage.text"
	var="mileage" />
	
	
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
.common {
	margin: 30px;
	padding-top: 15px;
	background: 3px solid #e8e8e8;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.5); /* Параметры тени */
	width: 800px;
	margin-left: auto;
    margin-right: auto;
}

.addvehicle {
	width: 500; /* Ширина слоя в пикселах */
	padding: 10px; /* Поля вокруг текста */
	text-align: center; /* Выравнивание содержимого слоя по левому краю */
	margin-left: auto;
	margin-right: auto;
	display: block;
}

.footer {
	clear: both;
}

.errors {
margin-top: 30px;
	margin-left: 350px;
	width: 400px;
	border-color: #484848; /* Цвет границы */
	border-style: solid; /* Стиль границы */
	padding: 5px; /* Поля вокруг текста */
	color: #484848;
	margin-left: auto;
	margin-right: auto;
}

.description-text {
	margin-top: 10px;
	width: 180px;
	margin-left: 150px;
	float: left;
}

.choosing {
	margin-left: 330px;
	width: 300px;
}

.text-inner {
	height: 33px;
	text-align: left;
}

#inlineFormCustomSelect {
	width: 220px;
}
</style>
	<jsp:include page="header.jsp" />

	<form action="Controller" method="post">
		<div class="common">
			<input type="hidden" name="command" value="add_vehicle" />
			<div class="description-text">
				<div class="text-inner">
					<h6>${chooseModel}:</h6>
				</div>

				<div class="text-inner">
					<h6>${chooseYear}:</h6>
				</div>

				<div class="text-inner">
					<h6>${choosePrice}:</h6>
				</div>

				<div class="text-inner">
					<h6>${chooseCarcase}:</h6>
				</div>

				<div class="text-inner">
					<h6>${chooseTransmission}:</h6>
				</div>

				<div class="text-inner">
					<h6>${chooseTypeFuel}:</h6>
				</div>

				<div class="text-inner">
					<h6>${chooseEngineCapacity}:</h6>
				</div>

				<div class="text-inner">
					<h6>${chooseDriveUnit}:</h6>
				</div>

				<div class="text-inner">
					<h6>${mileage}:</h6>
				</div>

			</div>

			<div class="choosing">

				<select class="custom-select mr-sm-2" id="inlineFormCustomSelect" name="model">
					<option value="">choose a model</option>
					<option value="a">A</option>
					<option value="b">B</option>
					<option value="c">C</option>
					<option value="e">E</option>
					<option value="g">G</option>
					<option value="gl">GL</option>
					<option value="amg">AMG</option>
				</select>


				<div class="year">
					<input class="btn btn-outline-secondary" type="text" name="year"
						placeholder="Year"></br>
				</div>

				<div class="price">
					<input class="btn btn-outline-secondary" type="text" name="price"
						placeholder="Price"></br>

				</div>


				<select class="custom-select " id="inlineFormCustomSelect" name="carcase">
					<option value="">choose a body</option>
					<option value="sedan">sedan</option>
					<option value="coupe">coupe</option>
					<option value="vagon">vagon</option>
				</select> 
				<select class="custom-select " id="inlineFormCustomSelect" name="transmission">
					<option value="">choose a transmission</option>
					<option value="automatic">Automatic</option>
					<option value="manual">Manual</option>
				</select> 
				<select class="custom-select mr-sm-2" id="inlineFormCustomSelect" name="fuel">
					<option value="">choose a fuel</option>
					<option value="petrol">petrol</option>
					<option value="diesel">diesel</option>
				</select>


				<div class="engine-capacity">
					<input class="btn btn-outline-secondary" type="text" name="engine"
						placeholder="Engine capacity"></br>
				</div>


				<select class="custom-select mr-sm-2" id="inlineFormCustomSelect" name="driveUnit">
					<option value="">choose a drive unit</option>
					<option value="4WD">4WD</option>
					<option value="fwd">front-wheel drive</option>
					<option value="rwd">rear drive</option>
				</select>


				<div class="mileage">
					<input class="btn btn-outline-secondary" type="text" name="mileAge"
						placeholder="mileage"></br>
				</div>

				
					<textarea class="form-control" rows="5" cols="40" name="description"
						placeholder="Your description"></textarea>
				

				<div class="custom-file">
					<input type="file" class="custom-file-input" id="inputGroupFile01" name="image" 
						accept="image/jpeg">
						<label class="custom-file-label" for="inputGroupFile01">${choosePicture}</label>
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