
<%@ page language="java" import="javax.servlet.jsp.PageContext"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />
<fmt:message bundle="${loc}" key="common.profile_admin.ban.text"
	var="ban" />
<fmt:message bundle="${loc}" key="common.profile_admin.unban.text"
	var="unban" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Users</title>
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
	<div class="container-fluid p-0">
		<div id="page-align" class="radius3">
			<jsp:include page="header.jsp" />
		</div>
	</div>

	<div class="users">
		<c:if test="${not empty sessionScope.allUsers}">
			<c:forEach items="${allUsers}" var="user">

				<div class="content-users">
					<table class="tg">
						<tr>
							<th width="30">${id}</th>
							<th width="120">${login}</th>
							<th width="120">${status}</th>
							<th width="120">${role}</th>
							<th width="120">${name}</th>
							<th width="120">${phone}</th>
							<th width="120">${email}</th>
							<th width="100"></th>
							<th width="100"></th>
						</tr>

						<tr>
							<td>${user.id}</td>
							<td>${user.login}</td>
							<td>${user.status}</td>
							<td>${user.role}</td>
							<td>${user.name}</td>
							<td>${user.phone}</td>
							<td>${user.email}</td>
							<td><form action="Controller" method="post">
									<input type="hidden" name="command" value="ban_user" /> <input
										type="hidden" name="user_ID" value="${user.id}"> <input
										class="btn btn-outline-secondary" type="submit" value="${ban}" />
								</form></td>
							<td><form action="Controller" method="post">
									<input type="hidden" name="command" value="unban_user" /> <input
										type="hidden" name="user_ID" value="${user.id}"> <input
										class="btn btn-outline-secondary" type="submit" value="${unban}" />
								</form></td>
						</tr>
					</table>

				</div>
			</c:forEach>
		</c:if>

	</div>

</body>
</html>