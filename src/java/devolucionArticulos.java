
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.net.*;
import misClases.*;

public class devolucionArticulos extends HttpServlet{
	
		
	protected void seleccion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext miContexto=getServletContext();
		
		//----------------------------------------------------------------------------
			HttpSession miSession = request.getSession();
			
			Tienda miTienda = (Tienda) miContexto.getAttribute("contextoTienda");
			
			HashMap<String,Articulo> listaArticulos = miTienda.getListaArticulos();
			HashMap<String,Integer> miCarro = ((Cesta)miSession.getAttribute("miCarro")).getContenido(); ;
			
			for(Map.Entry<String,Integer> cursor : miCarro.entrySet()) {
			
					String articulo = cursor.getKey();
					Integer ctd = cursor.getValue();
					Integer cantidadAnterior = listaArticulos.get(articulo).getExistencias();
					listaArticulos.get(articulo).setExistencias((ctd+cantidadAnterior));
			}
			
			miContexto.setAttribute("contextoTienda",miTienda);
		
		//---------------------------------------------------------------------------

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

