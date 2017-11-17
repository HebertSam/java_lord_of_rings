<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Index</title>
		<!-- <link rel="stylesheet" type="text/css" href="/css/style.css"> -->
	</head>

	<body>
		<div>
			<h2>Welcome, ${currentUser.firstName}</h2>
			<p>Welcome to your awesome magical ring finder, put the ring on, only good things will happen. Maybe it'll make you live forever, go invisible, turn your inherent hunger for riches or power into an insatiable curse that eventually dooms your entire species</p>
		</div>

		<div>
			<h3>Rings of Power</h3>
			<form action="/bind" method="post">
				<select name="ring">
					<c:forEach items="${rings}" var="ring">
						<c:if test="${ring.getUser() == null}">
							<option value="${ring.getId()}">${ring.getName()}</option> 
						</c:if>
					</c:forEach>
				</select>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<input type="submit" value="BIND YOURSELF IN DARKNESS">
			</form>
		</div>
		<c:forEach items="${currentUser.getRoles()}" var="role">
			<c:if test="${role.getName().equals('ROLE_ADMIN')}">
				<div>
					<a href="/admin/forge">Ring Creator</a>
					<a href="/admin/fool_creator">Person/Team Creator</a>
				</div>
			</c:if>
		</c:forEach>
		<div>
			<c:if test="${currentUser.getRing() != null}">
			<table>
				<tr>
					<th>Rings you have found</th>
					<th>Action</th>
				</tr>
				<tr>
					<c:forEach items="${currentUser.getRing()}" var="yourRing">
						<td>${yourRing.name}</td>
						<td><a href="">Loose the Ring (Delete)</a></td>
					</c:forEach>
				</tr>
			</table>
			</c:if>
		</div>
		<form action="/logout" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<input type="submit" value="Logout">
		</form>
	</body>
</html>