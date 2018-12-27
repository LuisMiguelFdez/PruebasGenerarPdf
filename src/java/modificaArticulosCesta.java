

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.net.*;
import misClases.*;

public class modificaArticulosCesta extends HttpServlet{
	
		
	protected void seleccion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//----------------------------------------------------------------------------
			String opcion = request.getParameter("opcion");
			switch(opcion){
				case "anade":efectuaCambio(request,response,1);
					break;
				case "quita":efectuaCambio(request,response,-1);
					break;
				case "borraPedido":efectuaCambio(request,response,0);
					break;
			}
		
		//----------------------------------------------------------------------------
	
	}
	
	public void efectuaCambio(HttpServletRequest request, HttpServletResponse response,int modificador) throws ServletException, IOException {
			HttpSession miSession = request.getSession();
			String nombreArticulo = request.getParameter("articulo");
			ServletContext miContexto=getServletContext();
			Tienda miTienda = (Tienda) miContexto.getAttribute("contextoTienda");
			
			HashMap<String,Articulo> listaArticulos = miTienda.getListaArticulos();
			HashMap<String,Integer> miCarro = ((Cesta)miSession.getAttribute("miCarro")).getContenido();
			
			/* Articulos iniciales en el contexto/sesion */
			int iniContext = listaArticulos.get(nombreArticulo).getExistencias();
			int iniCesta = miCarro.get(nombreArticulo);
			
			/* Modificacion de articulos*/
			if(modificador!=0){
				
				if((iniCesta+modificador)<=0){
					miCarro.remove(nombreArticulo);}
					else{
						miCarro.put(nombreArticulo,(iniCesta+modificador));}
						
				listaArticulos.get(nombreArticulo).setExistencias((iniContext-modificador));
			}
			
			/*Eliminacion de articulos*/
			else{
				miCarro.remove(nombreArticulo);
				listaArticulos.get(nombreArticulo).setExistencias((iniContext+iniCesta));
			}
			miContexto.setAttribute("contextoTienda",miTienda);
			
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		seleccion(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		seleccion(request, response);
	}
}

