<%--
  Created by IntelliJ IDEA.
  User: mrrobot
  Date: 1/23/25
  Time: 3:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Persons List</title>
</head>
<body>
<h1>Persons List</h1>
<ul>
    <c:forEach items="${persons}" var="person" >
        <li>${person.name} ${person.surname}</li>
    </c:forEach>
</ul>
</body>
</html>
