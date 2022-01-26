<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Cars</title>
    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>" type="text/css">
</head>
<style>
    <%@include file="css/styles.css"%>
</style>
<body>
<table>
    <tr>
        <th>Id</th>
        <th>Brand</th>
        <th>Model</th>
        <th>Production year</th>
    </tr>
    <c:forEach items="${cars}" var="car">
    <tr>
        <td><c:out value="${car.id}"/></td>
        <td><c:out value="${car.brand}"/></td>
        <td><c:out value="${car.model}"/></td>
        <td><c:out value="${car.productionYear}"/></td>
    </tr>
    </c:forEach>
</body>
</html>
