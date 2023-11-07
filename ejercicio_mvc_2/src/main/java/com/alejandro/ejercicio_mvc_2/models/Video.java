package com.alejandro.ejercicio_mvc_2.models;

public class Video {
	
	private int id;
	private String titulo;
	private String director;
	private int cli_id;
	
	public Video() {
		
	}

	public Video(int id, String titulo, String director, int cli_id) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.director = director;
		this.cli_id = cli_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getCli_id() {
		return cli_id;
	}

	public void setCli_id(int cli_id) {
		this.cli_id = cli_id;
	}
	
	public void clearInfo() {
		id = 0;
		titulo = "";
		director = "";
		cli_id = 0;
	}
	
	public String toString() {
		return id + ". " + titulo + " | " + director;
	}
}
