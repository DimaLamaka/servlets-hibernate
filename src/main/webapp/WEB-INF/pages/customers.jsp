<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Customers</title>
</head>
<body>
<c:forEach var="customer" items="${requestScope.customers}">
    <br>
    <br>
    {
    Id:<c:out value="${customer.id}"/>
    First name:<c:out value="${customer.firstName}"/>
    Second name:<c:out value="${customer.secondName}"/>
    Age:<c:out value="${customer.age}"/>
    <br>
    Cars:
    { <c:forEach var="car" items="${customer.cars}"><br>
    Id:<c:out value="${car.id}"/>
    Brand:<c:out value="${car.carBrand}"/>
    Number:<c:out value="${car.carNumber}"/>
    Date of manufacture: <c:out value="${car.dateOfManufactureCar}"/>
    Exist on warehouse: <c:out value="${car.isExistOnWarehouse}"/>
    <br>
    Regions:
    <c:forEach var="region" items="${car.regions}"> <br>
        {
        Id:<c:out value="${region.id}"/>
        Region:<c:out value="${region.region}"/>
        }
    </c:forEach>
</c:forEach>
    }
    }
</c:forEach>


</body>
</html>
