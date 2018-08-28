	<%@ page language="java" import="javax.servlet.jsp.PageContext" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@ page isELIgnored="false"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />
<fmt:message bundle="${loc}" key="common.profile_admin.ban.text" var="ban" />


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Users</title>
</head>

<body>
<div class="container-fluid p-0">
	<div id="page-align" class="radius3">
		<jsp:include page="header.jsp"/> 
	</div>		
</div>	

<div class="users">
	<c:if test="${not empty sessionScope.allUsers}">
			<c:forEach items="${allUsers}" var="user">

				<div class="content-cars">
					<p>${user}</p>
					<form action="Controller" method="post">
					<input type="hidden" name="car" value="${user}"> 
						<input type="hidden" name="command" value="ban_user" /> 
						<input type="hidden" name="user_ID" value="${user.id}">
						<input class="btn btn-outline-secondary" type="submit" value="${ban}" />
					</form>
				</div>
			</c:forEach>
		</c:if>

</div>

</body>
</html>