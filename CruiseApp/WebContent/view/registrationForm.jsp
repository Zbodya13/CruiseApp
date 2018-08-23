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

<p>
<a href="?sessionLocale=en" class="btn btn-info" role="button"><fmt:message key="en" /></a>
<a href="?sessionLocale=ua" class="btn btn-info" role="button"><fmt:message key="ua" /></a>
</p>

	
<form method="post" action="<c:url value='/register'/>" accept-charset="UTF-8">
	<div class="form-group row">
		<label for="login"  class="col-sm-5 col-form-label"><fmt:message key="login_name" /></label>
		<div class="col-sm-7">
     		<input type="text" class="form-control" name="login"  placeholder="Login">
    		</div>
    		<label for="password"  class="col-sm-5 col-form-label"><fmt:message key="password" /></label>
		<div class="col-sm-7">
     		<input type="text" class="form-control" name="password" id="password" placeholder="Password">
    		</div>	
    		<label for="name_en"  class="col-sm-5 col-form-label"><fmt:message key="name_en" /></label>
		<div class="col-sm-7">
     		<input type="text" class="form-control" name="name_en" id="name_en" placeholder="User name in English">
    		</div>	
    		<label for="surname_en"  class="col-sm-5 col-form-label"><fmt:message key="surname_en" /></label>
		<div class="col-sm-7">
     		<input type="text" class="form-control" name="surname_en" id="surname_en" placeholder="Surname in English">
    		</div> 
    		<label for="name_ua"  class="col-sm-5 col-form-label"><fmt:message key="name_ua" /></label>
		<div class="col-sm-7">
     		<input type="text" class="form-control" name="name_ua" id="name_ua" placeholder="User name in Ukrainian">
    		</div>	
    		<label for="surname_ua"  class="col-sm-5 col-form-label"><fmt:message key="surname_ua" /></label>
		<div class="col-sm-7">
     		<input type="text" class="form-control" name="surname_ua" id="surname_ua" placeholder="Surname in Ukrainian">
    		</div> 	 
    		<label for="telephon"  class="col-sm-5 col-form-label"><fmt:message key="telephon" /></label>
		<div class="col-sm-7">
     		<input type="text" class="form-control" name="telephon" id="telephon" placeholder="Your telephon">
    		</div> 
    		<label for="cash"  class="col-sm-5 col-form-label"><fmt:message key="cash" /></label>
		<div class="col-sm-7">
     		<input type="number" class="form-control" name="cash" id="cash" placeholder="Count of putting money">
    		</div>	   			
	</div>	   
	<button type="submit" class="btn btn-default"><fmt:message key="create" /></button> 	
</form>
<div class="col-xs-4">
<div class="alert alert-danger" role="alert">
   ${sessionScope.error}
</div></div>
</div>
</body>
</html>