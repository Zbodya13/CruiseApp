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
<title><fmt:message key="cruise_firm" /></title>
</head>
<body>

<div class="container">
<div class="row">
<div class="col-sm-10"><ul>
 <li><a href="?sessionLocale=en"><fmt:message key="en" /></a></li>
 <li><a href="?sessionLocale=ua"><fmt:message key="ua" /></a></li>
 </ul></div>
<div class="col-sm-2">
<form method="get" action="<c:url value='/auth'/>" >
<input type="text" hidden name="action" value="first"> 
<button type="submit" class="btn btn-default"><fmt:message key="registration" /></button>  
</form>
</div>
</div>
<h1 class=text-center><fmt:message key="cruise_firm" /></h1>
<h3 class=text-center><fmt:message key="cruises" /></h3>
<table class="table table-condensed">
	<thead>
		<tr>			
			<th><fmt:message key="capacity" /></th>
			<th><fmt:message key="route" /></th>
			<th><fmt:message key="countPorts" /></th>
			<th><fmt:message key="duration" /></th>
			<th><fmt:message key="staff" /></th>
			<th><fmt:message key="type" /></th>
			<th><fmt:message key="price" /></th>
			<th><fmt:message key="services" /></th>
			<th><fmt:message key="departure" /></th>
						
		</tr>
	</thead>
	<tbody>
		<c:forEach var="ship" items="${ships}"> 					
   			<tr>    				
        				<th><c:out value="${ship.capacity}"/></th>             
       				<th><c:out value="${ship.route}"/></th>         
       				<th><c:out value="${ship.countPort}"/></th>          
        				<th><c:out value="${ship.duration}"/></th>           
        				<th><c:out value="${ship.staff}"/></th>         
        				<th><c:out value="${ship.type}"/></th> 
        				<th><c:out value="${ship.price}"/></th> 
        				<th><c:out value="${ship.services}"/></th>  
        				<th><c:out value="${ship.departure}"/></th>            				       				        				  				     
       			</tr>			 
		</c:forEach>		
	</tbody>
</table>

</div>
</body>
</html>