<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Books Store Application</title>
</head>
<body>
<center>
    <h1>Users Management</h1>
    <h2>
        <a href="/new">Add New User</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/list">List All Users</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>PASSWORD</th>
            <th>ACTIONS</th>
        </tr>
        <c:forEach var="users" items="${listUser}">
            <tr>
                <td><c:out value="${users.id}" /></td>
                <td><c:out value="${users.name}" /></td>
                <td><c:out value="${users.password}" /></td>
                <td>
                    <a href="/edit?id=<c:out value='${users.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/delete?id=<c:out value='${users.id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>