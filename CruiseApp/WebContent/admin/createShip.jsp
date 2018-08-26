<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.sessionLocale}"/>
<fmt:setBundle basename="resources.loc"/>
<html lang="${sessionScope.sessionLocale}">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
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
 <li><a href="adminShip?sessionLocale=en&action=create"><fmt:message key="en" /></a></li>
 <li><a href="adminShip?sessionLocale=ua&action=create"><fmt:message key="ua" /></a></li>
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
     		<input type="text" class="form-control" name="shipID"  placeholder="Ship ID">
    		</div>
    		<label for="capacity"  class="col-sm-2 col-form-label"><fmt:message key="capacity" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="capacity" id="capacity" placeholder="Capacity">
    		</div>	
    		<label for="route"  class="col-sm-2 col-form-label"><fmt:message key="route" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="route" id="route" placeholder="Route">
    		</div>	
    		<label for="countPort"  class="col-sm-2 col-form-label"><fmt:message key="countPorts" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="countPort" id="countPort" placeholder="Count of ports">
    		</div>	
    		<label for="duration"  class="col-sm-2 col-form-label"><fmt:message key="duration" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="duration" id="duration" placeholder="Duration">
    		</div>	
    		<label for="staff"  class="col-sm-2 col-form-label"><fmt:message key="staff" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="staff" id="staff" placeholder="Staff">
    		</div>	
    		<label for="type"  class="col-sm-2 col-form-label"><fmt:message key="type" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="type" id="type" placeholder="Type of ticket">
    		</div>	
    		<label for="price"  class="col-sm-2 col-form-label"><fmt:message key="price" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="price" id="price" placeholder="Price">
    		</div>	
    		<label for="services"  class="col-sm-2 col-form-label"><fmt:message key="services" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="services" id="services" placeholder="Services">
    		</div>	
    		<label for="departure"  class="col-sm-2 col-form-label"><fmt:message key="departure" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="departure" id="departure" placeholder="Departure date">
    		</div>    		    			
	</div>
	<input type="text" hidden name="action" value="create">    
	<button type="submit" class="btn btn-default"><fmt:message key="create" /></button> 	
</form>
<div class="col-xs-4">
<div class="alert alert-danger" role="alert">
   <fmt:message key="${sessionScope.error}" />  
</div></div>
</div>
</body>
</html>