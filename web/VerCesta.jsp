<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>

<%@include file="cabecera.html" %>


	<jsp:useBean id="miCarro" scope="session" class="misClases.Cesta"/>
	
	<c:if test="${!empty param.opcion }">
		<jsp:include page="/modificaArticulosCesta"/>
	</c:if>
	<h3>------------------------------------------------------------------------</h3>
			<table class='ticket'>
			<c:choose>
			<c:when test="${!empty sessionScope.miCarro.contenido }">
				<c:set var="precioTotal" value="0"/>
				<c:forEach var='linea' items='${sessionScope.miCarro.contenido}'>
					
						<tr>
							<td class='ticket'>${linea.key} </td>
							<td class='ticket'>${linea.value} Kg</td>
							<c:set var="precioLinea" value="${linea.value * applicationScope.contextoTienda.listaArticulos[linea.key].precio}"/>
							<td class='ticket'>${precioLinea} €</td>
							
							<form action="VerCesta.jsp">
								
								<c:if test="${applicationScope.contextoTienda.listaArticulos[linea.key].existencias gt 0}">
									<td class='ticket'>
									<input type="image" src="img/mas.png">
									<input type="hidden" name="opcion" value="anade" >
									<input type="hidden" name="articulo" value="${linea.key}" >
									</td>
								</c:if>
							</form>
							
							<form action="VerCesta.jsp">
									<td class='ticket'>
									<input type="image" src="img/menos.png">
									<input type="hidden" name="opcion" value="quita" >
									<input type="hidden" name="articulo" value="${linea.key}" >
									</td>
							</form>
							
							<form action="VerCesta.jsp">
									<td class='ticket'>
									<input type="image" src="img/delete.png">
									<input type="hidden" name="opcion" value="borraPedido">
									<input type="hidden" name="articulo" value="${linea.key}">
									</td>
							</form>
							
							<c:set var="precioTotal" value="${(precioTotal+precioLinea)}"/>
						</tr>
					
				</c:forEach>
			</table>

			</c:when>
			<c:otherwise>
				<h3>No hay articulos en su carrito</h3>
			</c:otherwise>
		</c:choose>
	<h3>------------------------------------------------------------------------</h3>
	
	<form action="index.jsp">
		<input type="submit" name="salidaSinCompra" value="Salir sin compra">
	</form>
	
	<br>
	<form action="ConfirmaPedido.jsp">
		<input type="submit" value="Finalizar compra">
	</form>
	
	<br>
	<form action="Tienda.jsp">
		<input type="submit" value="Seguir comprando">
	</form>
		
		
		
		
<%@include file="pie.html" %>