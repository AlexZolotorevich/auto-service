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
<fmt:message bundle="${loc}" key="common.sales_page.detail.text"
	var="detail" />
<fmt:message bundle="${loc}" key="common.profile.delete.text"
	var="delete" />
<fmt:message bundle="${loc}" key="common.profile.infoText.text"
	var="infoText" />
<fmt:message bundle="${loc}" key="common.error.checkLogin.text"
	var="incorrectLogin" />
<fmt:message bundle="${loc}" key="common.error.checkPasswordSignIn.text"
	var="incorrectPassword" />

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

.errors {
	width: 300px; /* Ширина слоя в пикселах */
	margin: 0 auto; /* Отступ слева и справа */
	background: #393939; /* Цвет фона */
	padding: 10px; /* Поля вокруг текста */
	padding-left: 30px;
	text-align: left; /* Выравнивание содержимого слоя по левому краю */
}

.profile-table {
	width: 300px; /* Ширина слоя в пикселах */
	margin: 0 auto; /* Отступ слева и справа */
	background: #393939; /* Цвет фона */
	padding: 10px; /* Поля вокруг текста */
	padding-left: 30px;
	text-align: left; /* Выравнивание содержимого слоя по левому краю */
}
</style>

	<div class="container-fluid p-0"">
		<div id="page-align" class="radius3">
			<jsp:include page="header.jsp" />
		</div>
	</div>
	<div class="profile-table">
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="edit_user" /> <input
				class="btn btn-outline-secondary" type="text" name="login"
				placeholder="login" value="${sessionScope.user.login}"></br> <input
				class="btn btn-outline-secondary" type="password" name="password"
				placeholder="password"></br> <input
				class="btn btn-outline-secondary" type="text" name="name"
				placeholder="name" value="${sessionScope.user.name}"></br> <input
				class="btn btn-outline-secondary" type="text" name="phone"
				placeholder="+375 29 550 50 50" value="${sessionScope.user.phone}"></br>
			<input class="btn btn-outline-secondary" type="text" name="email"
				placeholder="email" value="${sessionScope.user.email}"></br> <input
				class="btn btn-outline-secondary" type="submit" value="${accept}">
		</form>


	</div>

	<div class="errors">
		<i> <c:if test="${not empty requestScope.errorMessage}">
				<div class="text-color">
					<c:forEach items="${requestScope.errorMessage}" var="error">

						<c:if test="${error.contains('incorrectLogin')}">
							<c:out value="${incorrectLogin}" />
						</c:if>

						<c:if test="${error.contains('incorrectPassword')}">
							<c:out value="${incorrectPassword}" />
						</c:if>

						<c:if test="${error.contains('incorrectEmail')}">
							<c:out value="${incorrectEmail}" />
						</c:if>

						<c:if test="${error.contains('incorrectPhone')}">
							<c:out value="${incorrectPhone}" />
						</c:if>

						<c:if test="${error.contains('incorrectName')}">
							<c:out value="${incorrectName}" />
						</c:if>
					</c:forEach>
				</div>
			</c:if>
		</i><br />
	</div>

	<div class="text">
		<h4>${infoText}</h4>
	</div>
	</br>
	<div class="yourVehicle">
		<c:if test="${not empty sessionScope.cars}">
			<c:forEach items="${cars}" var="car">

				<div class="content-cars">
					<form action="Controller" method="get">
						<p>${car}</p>
						<input type="hidden" name="car" value="${car}"> <input
							type="hidden" name="command" value="detail_vehicle" /> <input
							type="hidden" name="vehicle_ID" value="${car.ID}"><input
							class="btn btn-outline-secondary" type="submit" value="${detail}" />
					</form>

					<form action="Controller" method="post">
						<input type="hidden" name="command" value="delete_vehicle" /> <input
							type="hidden" name="vehicle_ID" value="${car.ID}"><input
							class="btn btn-outline-secondary" type="submit" value="${delete}" />
					</form>

				</div>
			</c:forEach>
		</c:if>
	</div>

	<div class="footer">
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>