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
    Id:${customer.id}
    First name:${customer.firstName}
    Second name:${customer.secondName}
    Age:c:out value="${customer.age}
    <br>
    Cars:
    { <c:forEach var="car" items="${customer.cars}"><br>
    Id:${car.id}
    Brand:${car.carBrand}
    Number:${car.carNumber}
    Date of manufacture:${car.dateOfManufactureCar}
    Exist on warehouse:${car.isExistOnWarehouse}
    <br>
    Regions:
    <c:forEach var="region" items="${car.regions}"> <br>
        {
        Id:${region.id}
        Region:${region.region}
        }
    </c:forEach>
</c:forEach>
    }
    }
</c:forEach>


</body>
</html>
