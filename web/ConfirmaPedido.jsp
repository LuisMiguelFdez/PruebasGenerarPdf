<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>

<%@include file="cabecera.html" %>
	
	<table class='ticket'>
			<c:choose>
			<c:when test="${!empty sessionScope.miCarro.contenido }">
				<c:set var="precioTotal" value="0"/>
				<c:forEach var='linea' items='${sessionScope.miCarro.contenido}'>
					<tr>
                                            <td class='ticket'>${linea.key}</td>
                                            <td class='ticket'>${linea.value} Kg</td>
                                            <c:set var="precioLinea" value="${linea.value * applicationScope.contextoTienda.listaArticulos[linea.key].precio}"/>
                                            <td class='ticket'>${precioLinea} €</td>
                                            <c:set var="precioTotal" value="${(precioTotal+precioLinea)}"/>
					</tr>
					
				</c:forEach>
			</table>
				
			<table class='ticket2'>
				<tr>
					<td class='ticket2'>Total Ticket:</td>
					<td class='ticket2'>${precioTotal} €</td>
				</tr>
				<tr>
					<td class='ticket2'>Iva(${applicationScope.contextoTienda.iva*100}%)</td>
					<td class='ticket2'>${precioTotal*applicationScope.contextoTienda.iva} €</td>
				</tr>
				<tr>
					<td class='ticket2'>Total:</td>
					<td class='ticket2'>${precioTotal+(precioTotal*applicationScope.contextoTienda.iva)} €</td>
				</tr>
			</table>
                        
                        <jsp:include page="/generaPdf"/>
                        <jsp:include page="/enviameUnCorreo"/>
                        <h1>Se ha generado la facturita en c:/documento.pdf</h1>
                      
			</c:when>
			<c:otherwise>
				<h3>No habia articulos en su carrito</h3>
			</c:otherwise>
		</c:choose>
		
		<form action="index.jsp">
			<input type="submit" name="desconectar" value="Finalizar">
		</form>
	
<%@include file="pie.html" %>