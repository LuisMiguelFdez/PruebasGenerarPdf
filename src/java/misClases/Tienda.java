package misClases;

import java.io.*;
import java.util.*;

public class Tienda {
	
    private String nombreTienda;
    private double iva;
    private HashMap<String,Articulo> listaArticulos;
	
	
	public Tienda() {
		this.nombreTienda= ""; /*Evitamos que de un null*/
		this.iva =0.21;
		this.listaArticulos = new HashMap<String,Articulo>();
		listaArticulos.put("Manzana",new Articulo("Manzana",2.50,500));
		listaArticulos.put("Peras",new Articulo("Peras",3.35,5055));
		listaArticulos.put("Limones",new Articulo("Limones",1.10,5034));
		listaArticulos.put("Naranjas",new Articulo("Naranjas",2.10,502));
		listaArticulos.put("Platanos",new Articulo("Platanos",1.99,900));

    }
  
    
  //-----------Getter & Setter-------------------------------------- 
    public String getNombreTienda() {
        return nombreTienda;
    }

    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }
	
	 public HashMap<String,Articulo> getListaArticulos() {
        return listaArticulos;
    }

    public void setListaArticulos(HashMap<String,Articulo> listaArticulos) {
        this.listaArticulos = listaArticulos;
    }

	public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }
	
	public void setDevolucionArticulo(HashMap<String,Integer> carro){
		for(Map.Entry<String,Integer> cursor : carro.entrySet()) {
			
					String articulo = cursor.getKey();
					Integer ctd = cursor.getValue();
					Integer cantidadAnterior = this.listaArticulos.get(articulo).getExistencias();
					this.listaArticulos.get(articulo).setExistencias((ctd+cantidadAnterior));
			}
		
		
	}
	public String getDevolucionArticulo(){
		return "";
	}
	
	
	
}
