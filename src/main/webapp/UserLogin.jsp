<%--
  Created by IntelliJ IDEA.
  User: Kaz
  Date: 13.05.2018
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

</head>
<body>
<div align="center">

        Please login or register

<form action="/list" method="POST">

    <table border="1">
        <caption></caption>
        <thead>
        <tr>
            <th>NAME</th>
            <th>PASSWORD</th>

        </tr>

        <c:if test="${users != null}">
            <input type="hidden" name="id" value="<c:out value='${users.id}' />" />
        </c:if>

        </thead>

        <tr>

             <td>
                    <input type="text" name="name" size="45"
                value="<c:out value='${users.name}' />"
                />
            </td>

            <td>
                <input type="text" name="password" size="45"
                       value="<c:out value='${users.password}' />"
                />
            </td>

        </tr>

    </table>

    <input type="submit" value="Login"/>

</form>

    <form action="/new" method="POST">
        <input type="submit" value="Registration"/>
    </form>

    </div>
</body>

</html>
