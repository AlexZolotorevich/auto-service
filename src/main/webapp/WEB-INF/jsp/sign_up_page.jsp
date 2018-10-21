<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />
<fmt:message bundle="${loc}" key="common.error.sign_up.text"
	var="signup" />
<fmt:message bundle="${loc}" key="common.error.checkPassword.text"
	var="checkPassword" />
<fmt:message bundle="${loc}" key="common.error.checkEmail.text"
	var="checkEmail" />
<fmt:message bundle="${loc}" key="common.sales_page.accept.text"
	var="accept" />
<fmt:message bundle="${loc}" key="common.error.checkLogin.text"
	var="incorrectLogin" />
<fmt:message bundle="${loc}" key="common.error.checkPassword.text"
	var="incorrectPassword" />
<fmt:message bundle="${loc}" key="common.error.checkEmail.text"
	var="incorrectEmail" />
<fmt:message bundle="${loc}" key="common.error.checkPhone.text"
	var="incorrectPhone" />
<fmt:message bundle="${loc}" key="common.error.checkName.text"
	var="incorrectName" />

<fmt:message bundle="${loc}" key="common.signInPage.login.text"
	var="login" />
<fmt:message bundle="${loc}" key="common.signInPage.password.text"
	var="password" />
<fmt:message bundle="${loc}" key="common.signInPage.name.text"
	var="name" />
<fmt:message bundle="${loc}" key="common.signInPage.phone.text"
	var="phone" />
<fmt:message bundle="${loc}" key="common.signInPage.email.text"
	var="email" />

<fmt:message bundle="${loc}" key="common.signUpPage.login.web"
	var="incorrectLoginWeb" />
<fmt:message bundle="${loc}" key="common.signUpPage.password.web"
	var="incorrectPasswordWeb" />
<fmt:message bundle="${loc}" key="common.signUpPage.email.web"
	var="incorrectEmailWeb" />


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">


<title>sign up page</title>
</head>
<body>
	<style>
.content{
	background-color: #383838;

}

.block {
	width: 30%; /* Ширина слоя в пикселах */
	margin: 0 auto; /* Отступ слева и справа */
	border: 1px solid #060606;
	background: url(static/images/carbon.jpeg); 
	padding: 10px; /* Поля вокруг текста */
	text-align: left; /* Выравнивание содержимого слоя по левому краю */
	
}

.line-inner-text {
	
	margin-top: 20px;
	height: 70px;
	
}

.field-name{
	float: left;
	width: 10%;
	margin-top: 5px;
	margin-left: 7%;
	margin-right: 20px;
	color: #cccfd1;
	
}
.input-dates{
	margin-left: 20px;
	float: left;
	width: 30%;
}
.error-dates{
	
	width: 50%;
	color: #c91313;
}
#button{
	margin-left: 15%:
}

</style>

	<div class="container-fluid p-0">
		<div id="page-align" class="radius3">
			<jsp:include page="header.jsp" />
		</div>
	</div>
	<div class="content">
	<div class="block">
		<form action="Controller" class="needs-validation" method="post">
			<input type="hidden" name="command" value="sign_up" />
			
			<div class="line-inner-text">
				<div class="field-name">${login}:</div>
				<div class="input-dates">
					<input class="btn btn-outline-secondary" id="form_userlogin"
						type="text" name="login" placeholder="login" required>
				</div>
				<div class="error-dates">
					<span class="error-form" id="login_error_message"></span>
				</div>
			</div>
			
			<div class="line-inner-text">
				<div class="field-name">${password}:</div>
				<div class="input-dates">
					<input class="btn btn-outline-secondary" id="form_userPassword"
						type="password" name="password" placeholder="password" required>
				</div>
				<div class="error-dates">
					<span class="error-form" id="password_error_message"></span>
				</div>
			</div>
			
			<div class="line-inner-text">
				<div class="field-name">${name}:</div>
				<div class="input-dates">
					<input class="btn btn-outline-secondary" id="form_userName"
						type="text" name="name" placeholder="name" required>
				</div>
				<div class="error-dates">
					<span class="error-form" id="name_error_message"></span>
				</div>
			</div>
			
			<div class="line-inner-text">
				<div class="field-name">${phone}:</div>
				<div class="input-dates">
					<input class="btn btn-outline-secondary" id="form_userPhone"
						type="text" name="phone" placeholder="phone" required>
				</div>
				<div class="error-dates">
					<span class="error-form" id="phone_error_message"></span>
				</div>
			</div>
			
			<div class="line-inner-text">
				<div class="field-name">${email}:</div>
				<div class="input-dates">
					<input class="btn btn-outline-secondary" id="form_userEmail"
						type="text" name="email" placeholder="email" required>
				</div>
				<div class="error-dates">
					<span class="error-form" id="email_error_message"></span>
				</div>
			</div>
			
			<input class="btn btn-outline-secondary" id="button" type="submit"
				value="${accept}">



		</form>

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
		</i> <br />

	</div>

</div>
	<div>
		<jsp:include page="footer.jsp" />
	</div>
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

<script>
	(function() {
		  'use strict';
		  window.addEventListener('load', function() {
		    // Fetch all the forms we want to apply custom Bootstrap validation styles to
		    var forms = document.getElementsByClassName('needs-validation');
		    // Loop over them and prevent submission
		    var validation = Array.prototype.filter.call(forms, function(form) {
		      form.addEventListener('submit', function(event) {
		        if (form.checkValidity() === false) {
		          event.preventDefault();
		          event.stopPropagation();
		        }
		        form.classList.add('was-validated');
		      }, false);
		    });
		  }, false);
		})();
	</script>

	<script src="static/js/validation.js"></script>
	


</body>
</html>