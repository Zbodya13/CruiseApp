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
<title></title>
</head>
<body>

<div class="container">

<p>
<a href="?sessionLocale=en" class="btn btn-info" role="button"><fmt:message key="en" /></a>
<a href="?sessionLocale=ua" class="btn btn-info" role="button"><fmt:message key="ua" /></a>
</p>


<form method="post" action="<c:url value='/auth'/>">
	<div class="form-group row">
		<label for="login"  class="col-sm-12 col-form-label"><fmt:message key="login_name" />:</label>
		<div class="col-sm-3">
     		<input type="text" class="form-control" name="login">
    		</div>    		
    		<label for="password"  class="col-sm-12 col-form-label"><fmt:message key="password" />:</label>
		<div class="col-sm-3">
     		<input type="password" class="form-control" name="password">
    		</div>	    			    			
	</div>	 
	<button type="submit" class="btn btn-default"><fmt:message key="login"/></button> 	
</form>

<form method="get" action="<c:url value='/register'/>"> 	    
	<button type="submit" class="btn btn-default"><fmt:message key="registration"/></button>      
</form>

<form method="get" action="<c:url value='/admin/adminShip'/>"> 
	<input type="text" hidden name="action" value="create">    
	<button type="submit" class="btn btn-default"><fmt:message key="create"/></button>      
</form>


<div class="col-xs-4">
<div class="alert alert-danger" role="alert">
   ${sessionScope.errorMessage}
</div></div>

<div class="col-xs-4">
<div class="alert alert-success" role="alert">
   ${sessionScope.message}
</div></div>

</div>

</body>
</html>