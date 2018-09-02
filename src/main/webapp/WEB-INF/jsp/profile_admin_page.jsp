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
<fmt:message bundle="${loc}" key="common.profile.users.text" var="users" />
<fmt:message bundle="${loc}" key="common.sales_page.detail.text"
	var="detail" />
	

<fmt:message bundle="${loc}" key="common.salespage.model.text"
	var="model" />
<fmt:message bundle="${loc}" key="common.salespage.year.text" var="year" />
<fmt:message bundle="${loc}" key="common.salespage.transmission.text"
	var="transmission" />
<fmt:message bundle="${loc}" key="common.salespage.engineCapacity.text"
	var="engineCapacity" />
<fmt:message bundle="${loc}" key="common.salespage.fuel.text" var="fuel" />
<fmt:message bundle="${loc}" key="common.salespage.driveUnit.text"
	var="driveUnit" />
<fmt:message bundle="${loc}" key="common.salespage.mileage.text"
	var="mileage" />
<fmt:message bundle="${loc}" key="common.salespage.engineCapacity.text"
	var="engineCapacity" />
<fmt:message bundle="${loc}" key="common.salespage.price.text"
	var="price" />
<fmt:message bundle="${loc}" key="common.salespage.date.text" var="date" />
<fmt:message bundle="${loc}" key="common.salespage.carcase.text"
	var="carcase" />
	
	
<html>



<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Profile page</title>
</head>
<body>

<style>

.content-cars {
	background: 3px solid #e8e8e8;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.5); /* Параметры тени */
	padding: 5px;
	margin-left: 200px;
	width: 700px;
}
.yourVehicle {
	background: 3px solid #e8e8e8;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.5); /* Параметры тени */
}

.picture {
	float: left;
	width: 130px;
	height: 130px;
}

.block {
	background: #eee;
	padding: 10px;
	height: 130px;
	float: left;
	width: 25%;
	margin-right: 2%;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

.block:last-child {
	margin-right: 0;
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
					<div class="short-info">
						<div class="picture">
							<img src="static/images/commercial.jpg" height="130" width="130"
								border="0">
						</div>
						<div class="block">
							<div class="car">
								<h6>${model}:${car.model}</h6>
							</div>
							<div class="yearcar">
								<h6>${year}:${car.year}</h6>
							</div>
							<div class="carcasecar">
								<h6>${carcase}:${car.typeCarcase}</h6>
							</div>
							<div class="engineCapacity">
								<h6>${engineCapacity}:${car.engineCapacity}</h6>
							</div>
						</div>


						<div class="block">
							<div class="transmission">
								<h6>${transmission}:${car.transmission}</h6>
							</div>
							<div class="typeFuel">
								<h6>${fuel}:${car.typeFuel}</h6>
							</div>
							<div class="driveUnit">
								<h6>${driveUnit}:${car.driveUnit}</h6>
							</div>
							<div class="mileage">
								<h6>${mileage}:${car.mileage}</h6>
							</div>
						</div>


						<div class="block">
							<div class="price">
								<h6>${price}:${car.price}</h6>
							</div>
							<div class="date">
								<h6>${date}:${car.date}</h6>
							</div>
						</div>


						<form action="Controller" method="post">
							<input type="hidden" name="command" value="accept_vehicle" /> <input
								type="hidden" name="vehicle_ID" value="${car.ID}"> <input
								class="btn btn-outline-secondary" type="submit"
								value="${accept}" />
						</form>

						<form action="Controller" method="post">
							<input type="hidden" name="command" value="delete_vehicle_admin" />
							<input type="hidden" name="vehicle_ID" value="${car.ID}">
							<input class="btn btn-outline-secondary" type="submit"
								value="${refuse}" />
						</form>
						<form action="Controller" method="get">
								<input type="hidden" name="car" value="${car}"> <input
									type="hidden" name="command" value="detail_vehicle" /> <input
									type="hidden" name="vehicle_ID" value="${car.ID}"><input
									class="btn btn-outline-secondary" type="submit"
									value="${detail}" />
							</form>
					</div>
				</div>
			</c:forEach>
		</c:if>
	</div>
	
	<div class="pagination">
				<ul class="pagination">
					<c:if test="${currentPage > 1}">
						<li class="page-item"><a class="page-link"
							href="Controller?command=to_profile_page_admin&currentPage=${currentPage-1}">Previous</a>
						</li>
					</c:if>

					<c:forEach begin="1" end="${countPages}" var="i">
						<c:choose>
							<c:when test="${currentPage eq i}">
								<li class="page-item active"><a class="page-link"> ${i}
										<span class="sr-only">(current)</span>
								</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link"
									href="Controller?command=to_profile_page_admin&currentPage=${i}">${i}</a>
								</li>
							</c:otherwise>
						</c:choose>
					</c:forEach>

					<c:if test="${currentPage < countPages}">
						<li class="page-item"><a class="page-link"
							href="Controller?command=to_profile_page_admin&currentPage=${currentPage+1}">Next</a>
						</li>
					</c:if>
				</ul>
			</div>

	<form action="Controller" method="get">
		<input type="hidden" name="command" value="get_all_users" /> <input
			class="btn btn-outline-secondary" type="submit" value="${users}" />
	</form>

	<div class="footer">
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>