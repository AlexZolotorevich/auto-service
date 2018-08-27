<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />
<fmt:message bundle="${loc}" key="common.header.profile.text"
var="toProfile" />
<fmt:message bundle="${loc}" key="common.salespage.model.text"
var="model" />
<fmt:message bundle="${loc}" key="common.salespage.year.text"
var="year" />
<fmt:message bundle="${loc}" key="common.salespage.carcase.text"
var="carcase" />
<fmt:message bundle="${loc}" key="common.salespage.price.text"
var="price" />
<fmt:message bundle="${loc}" key="common.salespage.transmission.text"
var="transmission" />
<fmt:message bundle="${loc}" key="common.salespage.fuel.text"
var="fuel" />
<fmt:message bundle="${loc}" key="common.salespage.engineCapacity.text"
var="engineCapacity" />
<fmt:message bundle="${loc}" key="common.salespage.driveUnit.text"
var="driveUnit" />
<fmt:message bundle="${loc}" key="common.salespage.mileage.text"
var="mileage" />
<fmt:message bundle="${loc}" key="common.profile.detailPage.text"
var="detailPage" />
<fmt:message bundle="${loc}" key="common.profile.discriptionChoosedVehicle.text"
var="discriptionChoosedVehicle" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Vehicle Detail</title>
</head>

<body>
	<style>
.tg {
	border-collapse: collapse;
	border-spacing: 0;
	border-color: #ccc;
}

.tg td {
	font-family: Arial, sans-serif;
	font-size: 14px;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #fff;
}

.tg th {
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #f0f0f0;
}

.tg .tg-4eph {
	background-color: #f9f9f9
}
</style>
	<div id="page-align" class="radius3">
		<jsp:include page="header.jsp" />
	</div>
	<div class="text-info">
		<h3>
			${detailPage}: </br>
		</h3>
	</div>
	
	<div class="content">
	<table class="tg">
		<tr>
			<th width="120">${model}</th>
			<th width="120">${year}</th>
			<th width="120">${carcase}</th>
			<th width="120">${price}</th>
			<th width="120">${transmission}</th>
			<th width="120">${fuel}</th>
			<th width="120">${engineCapacity}</th>
			<th width="120">${driveUnit}</th>
			<th width="120">${mileage}</th>
		</tr>

		 <tr>
                <td>${sessionScope.vehicle.model}</td>
  		        <td>${sessionScope.vehicle.year}</td>
                <td>${sessionScope.vehicle.typeCarcase}</td>
                <td>${sessionScope.vehicle.price}</td>
                <td>${sessionScope.vehicle.transmission}</td>
                <td>${sessionScope.vehicle.typeFuel}</td>
                <td>${sessionScope.vehicle.engineCapacity}</td>
                <td>${sessionScope.vehicle.driveUnit}</td>
               <td>${sessionScope.vehicle.mileage}</td> 
            </tr>
	</table>
	</br>
	<h5>${discriptionChoosedVehicle}:</h5></br>
	<h5>${sessionScope.vehicle.description}</h5>
</div>

<c:if test="${not empty sessionScope.user}">
<div class="profile">
			
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="to_profile_page" /> <input
					class="btn btn-outline-secondary" type="submit" name="to_profile_page"
					value="${toProfile}" />
					</form>
			
		</div>		
</c:if>		

<div class="footer">
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>