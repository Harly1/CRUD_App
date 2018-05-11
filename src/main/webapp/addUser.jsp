<%--
  Created by IntelliJ IDEA.
  User: krvl8001
  Date: 11.05.2018
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>

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

<form action="/add" method="POST">
   <input type="submit" value="SUBMIT"/>
</form>

</body>
</html>
