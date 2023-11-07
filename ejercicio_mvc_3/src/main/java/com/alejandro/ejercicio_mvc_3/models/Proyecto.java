package com.alejandro.ejercicio_mvc_3.models;

import java.util.ArrayList;

public class Proyecto {

	private String id;
	private String nombre;
	private int horas;
	private ArrayList<Cientifico> cientificos;

	public Proyecto() {

	}

	public Proyecto(String id) {
		this.id = id;
	}

	public Proyecto(String id, String nombre, int horas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.horas = horas;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public ArrayList<Cientifico> getCientificos() {
		return cientificos;
	}

	public void setCientificos(ArrayList<Cientifico> cientificos) {
		this.cientificos = cientificos;
	}
	
	public void clearInfo() {
		id = "";
		nombre = "";
		horas = 0;
	}
	
	public String toString() {
		return id + " " + nombre + " ("+horas+" horas)";
	}

}
