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
<title><fmt:message key="cruise_firm" /></title>
</head>
<body>

<div class="container">
<div class="row">
<div class="col-sm-10"><ul>
 <li><a href="adminExcursion?sessionLocale=en&action=edit&excursionID=${exc.excursionID}"><fmt:message key="en" /></a></li>
 <li><a href="adminExcursion?sessionLocale=ua&action=edit&excursionID=${exc.excursionID}"><fmt:message key="ua" /></a></li>
</ul></div>
<div class="col-sm-2">
<form method="post" action="<c:url value='/logout'/>" >
<button type="submit" class="btn btn-default"><fmt:message key="logout" /></button> 
</form>
</div>
</div>	
<form method="post" action="<c:url value='/admin/adminExcursion'/>" accept-charset="UTF-8">
	<div class="form-group row">
		<label for="excursionID"  class="col-sm-2 col-form-label"><fmt:message key="excursion_id" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="excursionID" id="escursionID"  value="${exc.excursionID}">
    		</div>
    		<label for="city"  class="col-sm-2 col-form-label"><fmt:message key="city" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="city" id="city" value="${exc.city}">
    		</div>	
    		<label for="description"  class="col-sm-2 col-form-label"><fmt:message key="description" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="description" id="description" value="${exc.description}">
    		</div>	
    		<label for="price"  class="col-sm-2 col-form-label"><fmt:message key="price" /></label>
		<div class="col-sm-10">
     		<input type="text" class="form-control" name="price" id="price" value="${exc.price}">
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