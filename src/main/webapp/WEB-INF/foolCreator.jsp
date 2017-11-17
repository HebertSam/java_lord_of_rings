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
        <table>
            <tr>
                <th>Name</th>
                <th>Guilds</th>
                <th>Age</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${allUsers}" var="user">
            <tr>
                <td>${user.getusername()}</td>
                <td>${user.getGuilds()}</td>
                <td>${user.getCreatedAt()}</td>
                <td><a href="/admin/destroy/${user.getId()}">Destroy</a> <a href="/admin/makeAdmin/${user.getId()}">Make user admin</a></td>
            </tr>
        </c:forEach>
        </table>
    </div>

    <div>
        <p>${guildError}</p>
        <form action="/admin/joinGuild" method="post">
        Name: <select name="user" id="">
            <c:forEach items="${allUsers}" var="user">
                <option value="${user.getId()}">${user.getusername()}</option>
            </c:forEach>
        </select>
        Guild <select name="guild" id="">
            <c:forEach items="${guilds}" var="guild">
                <option value="${guild.getId()}">${guild.getName()}</option>
            </c:forEach>
        </select>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Join!">
        </form>
    <div>
    <div>
        <p>${error.defaultMessage}</p>
        <form:form method="post" action="/admin/createGuild"  modelAttribute="guild">
            <form:label path="name">Name
                <form:errors path="name"/>
                <form:input path="name"/>
            </form:label>
            <form:label path="size">Size
                <form:errors path="size"/>
                <form:input path="size"/>
            </form:label>

            <input type="submit" value="Create">
        </form:form>
    </div>
</body>
</html>