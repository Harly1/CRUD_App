<%--
  Created by IntelliJ IDEA.
  User: Kaz
  Date: 13.05.2018
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <a href="/list">List All Books</a>
    <title>Add new user</title>

</head>
<body>
<form action="/add" method="POST">
    <table border="1">
        <caption>ADD_NEW_USER</caption>
        <thead>
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>PASSWORD</th>

        </tr>
        </thead>

        <tr>

            <td><input type="text" name="id"/></td>

            <td><input type="text" name="login"/></td>

            <td><input type="text" name="password"/></td>

        </tr>

    </table>

    <input type="submit" value="SUBMIT"/>

</form>

</body>
</html>
