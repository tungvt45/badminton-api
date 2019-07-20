<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Username</th>
        <th>Password</th>
    </tr>
    <c:forEach var="dto" items="${requestScope.ACCOUNTS}">
        <tr>
            <td>${dto.username}</td>
            <td>${dto.password}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>