<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />
<fmt:message bundle="${loc}" key="common.error.sign_up.text" var="signup" />
<fmt:message bundle="${loc}" key="common.error.checkPassword.text" var="checkPassword" />
<fmt:message bundle="${loc}" key="common.error.checkEmail.text" var="checkEmail" />
<fmt:message bundle="${loc}" key="common.sales_page.accept.text" var="accept" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>sign up page</title>
</head>
<body>

<style>
.body {
	background-color: #393939;
}

.block {
	width: 400px; /* Ширина слоя в пикселах */
	margin: 0 auto; /* Отступ слева и справа */
	background: #393939; /* Цвет фона */
	padding: 10px; /* Поля вокруг текста */
	text-align: center; /* Выравнивание содержимого слоя по левому краю */
}
</style>

	<div class="container-fluid p-0">
		<div id="page-align" class="radius3">
			<jsp:include page="header.jsp" />
		</div>
	</div>
	<p>
	<div class="block">

		<form action="Controller" method="post">
			<input type="hidden" name="command" value="sign_up" /> <input
				class="btn btn-outline-secondary" type="text" name="login"
				placeholder="login"></br> <input
				class="btn btn-outline-secondary" type="password" name="password"
				placeholder="password"></br> <input
				class="btn btn-outline-secondary" type="text" name="name"
				placeholder="name"></br> <input
				class="btn btn-outline-secondary" type="text" name="phone"
				placeholder="+375 29 550 50 50"></br> <input
				class="btn btn-outline-secondary" type="text" name="email"
				placeholder="email"></br> 
			<input class="btn btn-outline-secondary" type="submit" value="${accept}">
			
			<i> <c:if test="${not empty requestScope.errorMessage}">
			<div class="text-color">
				    <c:forEach  items="${requestScope.errorMessage}" var="error">
   						 <c:out value="${error}"/><br>
   					 </c:forEach>
   				</div>
			</c:if> </i><br />

		</form>
	</div>
	
	<div>	
		<jsp:include page="footer.jsp"/> 
	</div>	
</body>
</html>