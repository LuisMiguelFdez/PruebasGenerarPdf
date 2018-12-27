<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>

<%@include file="cabecera.html" %>
	
	<jsp:useBean id="contextoTienda" scope="application" class="misClases.Tienda">
		<jsp:setProperty name="contextoTienda" property="nombreTienda" value='${initParam["nombreTienda"]}'/>
	</jsp:useBean>

	<jsp:useBean id="miCarro" scope="session" class="misClases.Cesta">
		<jsp:setProperty name="miCarro" property="*"/>
	</jsp:useBean>
	
	<jsp:include page="/addArticulo"/>
	
	<h1>Bienvenido ${sessionScope.miCarro.nombreUsu} a la ${applicationScope.contextoTienda.nombreTienda}</h1>
	<table>
		<tr>
			<th>Articulos</th>
			<th>Existencias</th>
			<th>Precio kg</th>
			<th>Unid</th>
			<th></th>
		</tr>
	<c:forEach var='articulo' items='${contextoTienda.listaArticulos}' varStatus='guia'>
		<c:if test="${articulo.value.existencias!=0}">
		<form action='Tienda.jsp'>
			<tr>
				<td class='articulo'><input type='text' name='articulo' value='${articulo.value.nombre}' readonly></td>
				<td class='existencias'><input type='text' name='existencias' value='${articulo.value.existencias}' readonly></td>
				<td class='precio'><input type='text' name='precio' value='${articulo.value.precio}' readonly></td>
				<td class='ctd'><input type='number' min='0' name='cantidad' value='0' ></td>
				<td class='submit'><input type='submit' value=''></td>
			<tr>
		</form>
		</c:if>
	</c:forEach>
		</table>
		
		<form action='VerCesta.jsp'>
		<input type='submit' value='Ver cesta'>
		</form>
	
	
<%@include file="pie.html" %>