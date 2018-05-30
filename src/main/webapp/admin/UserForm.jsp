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
    <a href="list">List All Books</a>
    <title></title>

</head>
<body>
<div align="center">

    <c:if test="${users != null}">
    <form action="update" method="post">
        Edit User
        </c:if>
        <c:if test="${users == null}">
    <form action="insert" method="post">
        Add New User
            </c:if>


<form action="/admin/insert" method="POST">

    <table border="1">
        <caption></caption>
        <thead>
        <tr>
            <%--<th>ID</th>--%>
            <th>NAME</th>
            <th>PASSWORD</th>

        </tr>

        <c:if test="${users != null}">
            <input type="hidden" name="id" value="<c:out value='${users.id}' />" />
        </c:if>

        </thead>

        <tr>

            <%--<td>--%>
                <%--<input type="text" name="id" size="45"--%>
                       <%--value="<c:out value='${users.id}' />"--%>
                <%--/>--%>
            <%--</td>--%>

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

    <input type="submit" value="SUBMIT"/>

</form>
    </div>
</body>
</html>
