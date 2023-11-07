package com.alejandro.ejercicio_mvc_3.models;

import java.util.ArrayList;

public class Cientifico {
	
	private String dni;
	private String nomapels;
	private ArrayList<Proyecto> proyectos;
	
	public Cientifico() {
		
	}
	
	public Cientifico(String dni, String nomapels) {
		this.dni = dni;
		this.nomapels = nomapels;
		this.proyectos = new ArrayList<Proyecto>();
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNomapels() {
		return nomapels;
	}

	public void setNomapels(String nomapels) {
		this.nomapels = nomapels;
	}

	public ArrayList<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(ArrayList<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}
	
	public void clearInfo() {
		dni = "";
		nomapels = "";
		proyectos = new ArrayList<Proyecto>();
	}
	
	public String toString() {
		String toString = dni + " " + nomapels + " (";
		
		if(proyectos != null && proyectos.size() > 0) {
			for (Proyecto proyecto : proyectos) {
				toString += proyecto.getNombre() + ", ";
			}
		}
		
		toString += ")";
		
		return toString; 
	}
}
