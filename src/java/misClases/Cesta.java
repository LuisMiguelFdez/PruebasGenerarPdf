package misClases;

import java.io.*;
import java.util.*;

public class Cesta {
	
    private String nombreUsu;
    private HashMap<String,Integer> contenido;
	
 
	public Cesta() {
		this.nombreUsu="";
		this.contenido = new HashMap<String,Integer>();

    }
  
    
  //-----------Getter & Setter-------------------------------------- 
    public String getNombreUsu() {
        return nombreUsu;
    }

    public void setNombreUsu(String nombreUsu) {
        this.nombreUsu = nombreUsu;
    }
	
	 public HashMap<String,Integer> getContenido() {
        return contenido;
    }

    public void setContenido(HashMap<String,Integer> contenido) {
        this.contenido = contenido;
    }
	
	public void incluyePedido(String key ,Integer value){
		this.contenido.put(key,value);
	}
	
}
