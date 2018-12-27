

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.net.*;
import misClases.*;

public class addArticulo extends HttpServlet{
	
		
	protected void seleccion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset='UTF-8'");
		PrintWriter out = response.getWriter();
		ServletContext miContexto=getServletContext();
		
		RequestDispatcher cabeceraHTML = miContexto.getRequestDispatcher("/cabecera.html");
		cabeceraHTML.include(request,response);

		//----------------------------------------------------------------------------
			
			HashMap<String,Articulo> listaArticulos = ((Tienda) miContexto.getAttribute("contextoTienda")).getListaArticulos();
			
			String articulo = request.getParameter("articulo");
			String existencias = request.getParameter("existencias");
			String precio = request.getParameter("precio");
			String cantidad = request.getParameter("cantidad");
			
			if(cantidad!=null && precio!=null && existencias!=null && cantidad!="" && articulo!=null){
				
				HttpSession session = request.getSession();
				Cesta miCarro =(Cesta)session.getAttribute("miCarro");
				
				if(Integer.parseInt(existencias)>=Integer.parseInt(cantidad)){
					//sumar al carrito
					Integer ctdInicial = miCarro.getContenido().get(articulo);
					Integer ctdInicialContexto = listaArticulos.get(articulo).getExistencias();
					
					if(ctdInicial==null){
						ctdInicial=0;
					}
					if(ctdInicialContexto==null){
						ctdInicialContexto=0;
					}
					if(Integer.parseInt(cantidad)>0){
						miCarro.incluyePedido(articulo,(Integer.parseInt(cantidad)+ctdInicial));
						listaArticulos.get(articulo).setExistencias(ctdInicialContexto-Integer.parseInt(cantidad));
					}
						
						session.setAttribute("miCarro",miCarro);
						out.println("<h1>articulos incluidos correctamente</h1>");
					
					
				}else{
					out.println("<h1>No hay suficiente stock</h1>");
				}
				
			}
		}
		
		//----------------------------------------------------------------------------


	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		seleccion(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		seleccion(request, response);
	}
}

