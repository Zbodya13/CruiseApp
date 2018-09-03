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
<title><fmt:message key="cruise_firm" /></title>
</head>
<body>
<div class="container">

<div class="row">
<div class="col-sm-10"><ul>
 <li><a href="adminExcursion?sessionLocale=en&action=createShipExc"><fmt:message key="en" /></a></li>
 <li><a href="adminExcursion?sessionLocale=ua&action=createShipExc"><fmt:message key="ua" /></a></li>
</ul></div>
<div class="col-sm-2">
<form method="post" action="<c:url value='/logout'/>" >
<button type="submit" class="btn btn-default"><fmt:message key="logout" /></button> 
</form>
</div>
</div>
	
<form method="post" action="<c:url value='/admin/adminExcursion'/>" accept-charset="UTF-8">
	<div class="form-group row">
		<label for="shipID"  class="col-sm-2 col-form-label"><fmt:message key="ship_id" /></label>
		<div class="col-sm-10">
     		<select name="shipID" class="form-control">
     			<c:forEach var="ship" items="${ships}">
     			<option value=${ship.shipID}>${ship.shipID}</option>
     			</c:forEach>
     		</select>
    		</div>
    		<label for="excursionID"  class="col-sm-2 col-form-label"><fmt:message key="excursion_id" /></label>
		<div class="col-sm-10">
     		<select name="excursionID" class="form-control">
     			<c:forEach var="exc" items="${excursions}">
     			<option value=${exc.excursionID}>${exc.excursionID}</option>
     			</c:forEach>
     		</select>
    		</div>	    			    			
	</div>
	<input type="text" hidden name="action" value="createShipExc">    
	<input type="text" hidden name="shipID" value="${ship.shipID}">  
	<input type="text" hidden name="excursionID" value="${exc.excursionID}">  
	<button type="submit" class="btn btn-default"><fmt:message key="create" /></button> 	
</form>
<table  class="table table-condensed">
	<thead>
		<tr>
			<th><fmt:message key="ship_id" /></th>	
			<th><fmt:message key="excursion_id" /></th>					
		</tr>
	</thead>
	<tbody>
		<c:forEach var="excShip" items="${shipsExcursions}"> 
		<tr>
			<th><c:out value="${excShip.shipID}"/></th>     
			<th><c:out value="${excShip.excursionID}"/></th>       
			<th>
			<form method="post" action="<c:url value='/admin/adminExcursion'/>" accept-charset="UTF-8">        				
        				<input type="text" hidden name="excursionID" value="${excShip.excursionID}">
        				<input type="text" hidden name="shipID" value="${excShip.shipID}">
        				<input type="text" hidden name="action" value="deleteShipExc"> 
				<button type="submit" class="btn btn-default"><fmt:message key="delete" /></button> 
        			</form>
			</th>    			       
		</tr>
		</c:forEach>	
	</tbody>
</table>	

<table>
<c:forEach var="pagen" items="${pages}">
	<td>
	<form method="get" action="<c:url value='/admin/adminExcursion'/>" accept-charset="UTF-8">
        	<input type="text" hidden name="action" value="createShipExc">
        	<input type="text" hidden name="page" value="${pagen}">    
        	<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}">        							
        	<button type="submit" class="btn btn-default">${pagen}</button>  
        	</form></td>
</c:forEach>
</table>

<div class="col-xs-4">
<div class="alert alert-danger" role="alert">
   <fmt:message key="${sessionScope.error}" />  
</div></div>
</div>
</body>
</html>