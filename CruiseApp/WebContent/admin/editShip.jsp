<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.sessionLocale}"/>
<fmt:setBundle basename="resources.loc"/>
<html lang="${sessionScope.sessionLocale}">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Список круїзів </title>
</head>
<body>

<div class="container">
<div class="row">
<div class="col-sm-10"><ul>
 <li><a href="adminShip?sessionLocale=en&action=edit&shipID=${ship.shipID}"><fmt:message key="en" /></a></li>
 <li><a href="adminShip?sessionLocale=ua&action=edit&shipID=${ship.shipID}"><fmt:message key="ua" /></a></li>
</ul></div>
<div class="col-sm-2">
<form method="post" action="<c:url value='/logout'/>" >
<button type="submit" class="btn btn-default"><fmt:message key="logout" /></button> 
</form>
</div>
</div>	
<form method="post" action="<c:url value='/admin/adminShip'/>" accept-charset="UTF-8">
	<div class="form-group row">
		<label for="shipID"  class="col-sm-2 col-form-label"><fmt:message key="ship_id" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="shipID"  value="${ship.shipID}">
    		</div>
    		<label for="capacity"  class="col-sm-2 col-form-label"><fmt:message key="capacity" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="capacity" id="capacity" value="${ship.capacity}">
    		</div>	
    		<label for="route"  class="col-sm-2 col-form-label"><fmt:message key="route" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="route" id="route" value="${ship.route}">
    		</div>	
    		<label for="countPort"  class="col-sm-2 col-form-label"><fmt:message key="countPorts" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="countPort" id="countPort" value="${ship.countPort}">
    		</div>	
    		<label for="duration"  class="col-sm-2 col-form-label"><fmt:message key="duration" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="duration" id="duration" value="${ship.duration}">
    		</div>	
    		<label for="staff"  class="col-sm-2 col-form-label"><fmt:message key="staff" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="staff" id="staff" value="${ship.staff}">
    		</div>	
    		<label for="type"  class="col-sm-2 col-form-label"><fmt:message key="type" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="type" id="type" value="${ship.type}">
    		</div>	
    		<label for="price"  class="col-sm-2 col-form-label"><fmt:message key="price" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="price" id="price" value="${ship.price}">
    		</div>	
    		<label for="services"  class="col-sm-2 col-form-label"><fmt:message key="services" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="services" id="services" value="${ship.services}">
    		</div>	
    		<label for="departure"  class="col-sm-2 col-form-label"><fmt:message key="departure" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="departure" id="departure" value="${ship.departure}">
    		</div>    		    			
	</div>
	<input type="text" hidden name="action" value="edit">    
	<button type="submit" class="btn btn-default"><fmt:message key="edit" /></button> 	
</form>
<div class="col-xs-4">
<div class="alert alert-danger" role="alert">
   <fmt:message key="${sessionScope.error}" />  
</div></div>
</div>
</body>
</html>