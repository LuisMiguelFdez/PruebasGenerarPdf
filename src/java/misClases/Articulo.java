package misClases;

import java.io.*;
import java.util.*;

public class Articulo {
	
    private String nombre;
    private double precio;
    private Integer existencias = 0;
	
	public Articulo(){}
	
	public Articulo(String nombre,double precio,Integer existencias) {
        this.nombre = nombre;
		this.precio = precio;
		this.existencias=existencias;

    }
  
    
  //-----------Getter & Setter-------------------------------------- 
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
	
	public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
	
	public Integer getExistencias() {
        return existencias;
    }

    public void setExistencias(Integer existencias) {
        this.existencias = existencias;
    }
	
	
}
