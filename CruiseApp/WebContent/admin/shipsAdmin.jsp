<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<div class="col-sm-6"><ul>
 <li><a href="?sessionLocale=en&action=list"><fmt:message key="en" /></a></li>
 <li><a href="?sessionLocale=ua&action=list"><fmt:message key="ua" /></a></li>
</ul></div>
<div class="col-sm-4"><ul>
 <li><a href="adminShip?action=list&sessionLocale=${sessionScope.sessionLocale}"><fmt:message key="ships" /></a></li>
 <li><a href="adminExcursion?action=list&sessionLocale=${sessionScope.sessionLocale}"><fmt:message key="excursions" /></a></li>
  <li><a href="adminExcursion?action=createShipExc&sessionLocale=${sessionScope.sessionLocale}"><fmt:message key="createSE" /></a></li>
</ul></div>
<div class="col-sm-2">
<form method="post" action="<c:url value='/logout'/>" >
<button type="submit" class="btn btn-default"><fmt:message key="logout" /></button> 
</form>
</div>
</div>
<table class="table table-condensed">
	<thead>
		<tr>
			<th><fmt:message key="ship_id" /></th>
			<th>
			<form method="get" action="<c:url value='/admin/adminShip'/>" >
        				<input type="text" hidden name="action" value="listBy"/>
        				<input type="text" hidden name="orderBy" value="capacity"/>
        				<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}"/>
        				<button type="submit" class="btn btn-default"><fmt:message key="capacity" /></button>  
        			</form>
			</th>
			<th>
			<form method="get" action="<c:url value='/admin/adminShip'/>" >
        				<input type="text" hidden name="action" value="listBy">
        				<input type="text" hidden name="orderBy" value="route">
        				<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}"/>
        				<button type="submit" class="btn btn-default"><fmt:message key="route" /></button> 
        			</form> 
			</th>
			<th>
			<form method="get" action="<c:url value='/admin/adminShip'/>" >
        				<input type="text" hidden name="action" value="listBy">
        				<input type="text" hidden name="orderBy" value="countPort">
        				<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}"/>
        				<button type="submit" class="btn btn-default"><fmt:message key="countPorts" /></button>
        			</form>  
			</th>
			<th>
			<form method="get" action="<c:url value='/admin/adminShip'/>" >
        				<input type="text" hidden name="action" value="listBy">
        				<input type="text" hidden name="orderBy" value="duration">
        				<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}"/>
        				<button type="submit" class="btn btn-default"><fmt:message key="duration" /></button> 
        			</form> 
			</th>
			<th>
			<form method="get" action="<c:url value='/admin/adminShip'/>" >
        				<input type="text" hidden name="action" value="listBy">
        				<input type="text" hidden name="orderBy" value="staff">
        				<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}"/>
        				<button type="submit" class="btn btn-default"><fmt:message key="staff" /></button>  
        			</form>
			</th>
			<th>
			<form method="get" action="<c:url value='/admin/adminShip'/>" >
        				<input type="text" hidden name="action" value="listBy">
        				<input type="text" hidden name="orderBy" value="type">
        				<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}"/>
        				<button type="submit" class="btn btn-default"><fmt:message key="type" /></button> 
        			</form> 
			</th>
			<th>
			<form method="get" action="<c:url value='/admin/adminShip'/>" >
        				<input type="text" hidden name="action" value="listBy">
        				<input type="text" hidden name="orderBy" value="price">
        				<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}"/>
        				<button type="submit" class="btn btn-default"><fmt:message key="price" /></button>  
        			</form>
			</th>
			<th>
			<form method="get" action="<c:url value='/admin/adminShip'/>" >
        				<input type="text" hidden name="action" value="listBy">
        				<input type="text" hidden name="orderBy" value="services">
        				<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}"/>
        				<button type="submit" class="btn btn-default"><fmt:message key="services" /></button>  
        			</form>
			</th>		
			<th>
			<form method="get" action="<c:url value='/admin/adminShip'/>" >
        				<input type="text" hidden name="action" value="listBy">
        				<input type="text" hidden name="orderBy" value="departure">
        				<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}"/>
        				<button type="submit" class="btn btn-default"><fmt:message key="departure" /></button> 
        			</form> 
			</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="ship" items="${ships}"> 					
   			<tr>
    				<th><c:out value="${ship.shipID}"/></th>   
        				<th><c:out value="${ship.capacity}"/></th>             
       				<th><c:out value="${ship.route}"/></th>         
       				<th><c:out value="${ship.countPort}"/></th>          
        				<th><c:out value="${ship.duration}"/></th>           
        				<th><c:out value="${ship.staff}"/></th>         
        				<th><c:out value="${ship.type}"/></th> 
        				<th><c:out value="${ship.price}"/></th> 
        				<th><c:out value="${ship.services}"/></th>  
        				<th><c:out value="${ship.departure}"/></th>       
        				<th>
        				<form method="get" action="<c:url value='/admin/adminShip'/>" accept-charset="UTF-8">
        				<input type="text" hidden name="action" value="edit">
        				<input type="text" hidden name="shipID" value="${ship.shipID}">
        				<button type="submit" class="btn btn-default"><fmt:message key="edit" /></button>  
        				</form>
        				</th>   
        				<th>
        				<form method="get" action="<c:url value='/admin/adminShip'/>" accept-charset="UTF-8">
        				<input type="text" hidden name="action" value="delete">
        				<input type="text" hidden name="shipID" value="${ship.shipID}">
        				<button type="submit" class="btn btn-default"><fmt:message key="delete" /></button>  
        				</form>
        				</th>         				  				     
       			</tr>			 
		</c:forEach>		
	</tbody>
</table>
<form method="get" action="<c:url value='/admin/adminShip'/>" accept-charset="UTF-8"> 
	<input type="text" hidden name="action" value="create">    
	<button type="submit" class="btn btn-default"><fmt:message key="create" /></button>      
</form> 

</div>
</body>
</html>