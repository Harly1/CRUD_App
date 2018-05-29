<%--
  Created by IntelliJ IDEA.
  User: krvl8001
  Date: 22.05.2018
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Hello user</title>

</head>
<body>
<div align="center">

                <table border="1">
                <c:forEach var="userName" items="${userName}">
                    <caption></caption>
                    <thead>
                    <tr>
                        <th>HELLO</th>
                        <th>${userName}</th>

                    </tr>

                    </thead>

                </table>

</div>
</body>
</html>
