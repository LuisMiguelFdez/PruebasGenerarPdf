<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Ejercicio 31 cuartaCestaJsp</title>
<style>
	div.principal{
		border:1px solid black;
		margin:auto;
		width:700px;
		border-radius:15px;
		padding:5px;
		padding-left:30px;
		font-size:18px;
	}
</style>
</head>


<body>
	
	<hr/>
<div class="principal">
	
	<h2>  Ejercicio 31 cuartaCestaJsp  </h2>
	<form action="Tienda.jsp" method="GET">
		<label>Nombre Usuario :  </label>
		<input type="text" name="nombreUsu" required="required">
		<input type="submit" value="Entrar"/>
	</form>
	
	
	<%if(request.getParameter("salidaSinCompra")!=null){%> <!-- Funcionaria con jsp Param empty --> 
		<jsp:include page="/devolucionArticulos"/>
		<h3>Se ha vaciado tu carrito</h3>
	<%}%>

	
	<%if(request.getParameter("desconectar")!=null || request.getParameter("salidaSinCompra")!=null){
			session.invalidate();
	}%>

</div>
<hr/>
</body>
</html>