<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Cars</title>
    <%--<link rel="stylesheet" href="<c:url value="/css/styles.css"/>" type="text/css">--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<%--<style>
    <%@include file="css/styles.css"%>
</style>--%>
<body>
<div class="container">
    <div class="row align-items-center">
        <div class="col">
        </div>
        <div class="col-8 border border-2 rounded">
            <h1 class="display-2 text-center fst-italic ">>Car list<</h1>
            <table class="table table-dark table-hover">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Brand</th>
                    <th>Model</th>
                    <th>Production year</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${cars}" var="car">
                    <tr>
                        <td><c:out value="${car.id}"/></td>
                        <td><c:out value="${car.brand}"/></td>
                        <td><c:out value="${car.model}"/></td>
                        <td><c:out value="${car.productionYear}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col">
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>
