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

<fmt:message bundle="${loc}" key="common.profile.refuse.text"
	var="refuse" />
<fmt:message bundle="${loc}" key="common.profile.infoText.text"
	var="infoText" />
<fmt:message bundle="${loc}" key="common.profile.users.text"
	var="users" />
<html>



<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Profile page</title>
</head>
<body>

	<style>
.yourVehicle {
	background: 3px solid #e8e8e8;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.5); /* Параметры тени */
}
</style>

	<div class="container-fluid p-0"">
		<div id="page-align" class="radius3">
			<jsp:include page="header.jsp" />
		</div>
	</div>

	<div class="text">
		<h4>${infoText}</h4>
	</div>

	<div class="yourVehicle">
		<c:if test="${not empty sessionScope.newCarsOfUsers}">
			<c:forEach items="${newCarsOfUsers}" var="car">

				<div class="content-cars">
					<p>${car}</p>
					<form action="Controller" method="post">
						<input type="hidden" name="command" value="accept_vehicle" /> 
						<input type="hidden" name="vehicle_ID" value="${car.ID}">
						<input class="btn btn-outline-secondary" type="submit" value="${accept}" />
					</form>

					<form action="Controller" method="post">
						<input type="hidden" name="command" value="delete_vehicle" /> 
						<input type="hidden" name="vehicle_ID" value="${car.ID}">
						<input class="btn btn-outline-secondary" type="submit" value="${refuse}" />
					</form>

				</div>
			</c:forEach>
		</c:if>
	</div>

	<form action="Controller" method="get">
		<input type="hidden" name="command" value="get_all_users" /> 
		<input class="btn btn-outline-secondary" type="submit" value="${users}" />
	</form>

	<div class="footer">
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>