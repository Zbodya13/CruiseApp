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
<div class="col-sm-4"><ul>
 <li><a href="?sessionLocale=en&action=listExcursions"><fmt:message key="en" /></a></li>
 <li><a href="?sessionLocale=ua&action=listExcursions"><fmt:message key="ua" /></a></li>
 <li><fmt:message key="currentCash" />${sessionScope.cash}</li> </ul></div>
<div class="col-sm-4"><ul>
 <li><a href="?action=userListShips&sessionLocale=${sessionScope.sessionLocale}"><fmt:message key="myShips" /></a></li>
 <li><a href="?action=userListExcursions&sessionLocale=${sessionScope.sessionLocale}"><fmt:message key="myExcursions" /></a></li>
 <li><a href="?action=listShips&sessionLocale=${sessionScope.sessionLocale}"><fmt:message key="ships" /></a></li>
 <li><a href="?action=listExcursions&sessionLocale=${sessionScope.sessionLocale}"><fmt:message key="excursions" /></a></li>
 <li><a href="?action=availableListExcursions&sessionLocale=${sessionScope.sessionLocale}"><fmt:message key="available_excursions" /></a></li>
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
			<th><fmt:message key="excursion_id" /></th>
			<th><fmt:message key="city" /></th>
			<th><fmt:message key="description" /></th>
			<th><fmt:message key="price" /></th>			
		</tr>
	</thead>
	<tbody>
		<c:forEach var="exc" items="${excursions}"> 					
   			<tr>
    				<th><c:out value="${exc.excursionID}"/></th>   
        				<th><c:out value="${exc.city}"/></th>             
       				<th><c:out value="${exc.description}"/></th>         
       				<th><c:out value="${exc.price}"/></th>         				     
        				<th>
        				<form method="get" action="<c:url value='/customUser'/>" accept-charset="UTF-8">
        				<input type="text" hidden name="action" value="buyExcursion">
        				<input type="text" hidden name="excursionID" value="${exc.excursionID}">    
        				<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}">        							
        				<button type="submit" class="btn btn-default"><fmt:message key="buy" /></button>  
        				</form>
        				</th>   				        				  				     
       			</tr>			 
		</c:forEach>		
	</tbody>
</table>
<table>
<c:forEach var="pagen" items="${pages}">
	<td>
	<form method="get" action="<c:url value='/customUser'/>" accept-charset="UTF-8">
        	<input type="text" hidden name="action" value="listExcursions">
        	<input type="text" hidden name="page" value="${pagen}">    
        	<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}">        							
        	<button type="submit" class="btn btn-default">${pagen}</button>  
        	</form></td>
</c:forEach>
</table>

<form method="post" action="<c:url value='/customUser'/>" accept-charset="UTF-8">	
	<input type="number" name="cash" placeholder="Add money to your cash account">
	<input type="text" hidden name="action" value="addCash">   
	<input type="text" hidden name="page" value="exc"> 
	<input type="text" hidden name="sessionLocale" value="${sessionScope.sessionLocale}">      	 
	<button type="submit" class="btn btn-default"><fmt:message key="addCash" /></button> 		
</form> 



<div class="col-xs-4">
<div class="alert alert-danger" role="alert">
   <fmt:message key="${sessionScope.error}" />  
</div></div>





</div>
</body>
</html>