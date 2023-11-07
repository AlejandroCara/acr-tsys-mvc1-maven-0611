package com.alejandro.ejercicio_mvc_3.dbcon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.alejandro.ejercicio_mvc_3.models.Cientifico;
import com.alejandro.ejercicio_mvc_3.models.Proyecto;

public class MyConnectionManager {
	
	private Connection connection;
	
	public MyConnectionManager() {
	}
	
	public Connection getConnection() {
		return this.connection;
	}
	
	//Inicia la conexión con el servidor mysql y la almacena en connection
	public void startConnection(String user, String pwd) {
		//Intenta cerrar una posible anterior conexión 
		this.closeConnection();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", user, pwd);
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}

	}
	
	//Inicia la conexión con la base de datos especifica y la almacena en connection
	public int startConnectionDB(String user, String pwd, String bbdd) {
		
		int status = 0;
		
		//Intenta cerrar una posible anterior conexión 
		this.closeConnection();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bbdd, user, pwd);
			status = 200;
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
			status = 400;
		}
		
		return status;
	}
	
	//Intenta cerrar la conexión en caso de que exista
	public void closeConnection() {
		
		if(this.connection != null) {
			try {
				this.connection.close();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		} 
	}
	
	public ArrayList<Cientifico> getAllCientificos(){
		ArrayList<Cientifico> cientificos = new ArrayList<Cientifico>();
		java.sql.ResultSet results = null;
		java.sql.ResultSet resultsProyecto = null;
		String query = "select * from cientifico";
		Cientifico cientifico;
		String dni;
		String nomapels;
		
		Proyecto proyecto;
		String id;
		String nombre;
		int horas;
		
		try {
			Statement st = this.connection.createStatement();
			results = st.executeQuery(query);
			
			while(results.next()){
				dni = results.getString("dni");
				nomapels = results.getString("nomapels");

				query = "select * from proyecto as p join asignado_a as a on p.id = a.id_proyecto where dni_cientifico = " + dni+ ";";
				st = this.connection.createStatement();
				resultsProyecto = st.executeQuery(query);

				cientifico = new Cientifico(dni, nomapels);
				
				while(resultsProyecto.next()){
					id = resultsProyecto.getString("id");
					nombre = resultsProyecto.getString("nombre");
					horas = Integer.parseInt(resultsProyecto.getString("horas"));
					
					proyecto = new Proyecto(id, nombre, horas);
					cientifico.getProyectos().add(proyecto);
				}
				
				cientificos.add(cientifico);
			}
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return cientificos;
	}
	
	public int insertCientifico(Cientifico cientifico) {
		String query = "insert into cientifico (dni, nomapels) values ('"+cientifico.getDni()+"', '"+cientifico.getNomapels()+"');";
		int status = 0;
		try {
			Statement st = this.connection.createStatement();
			st.executeUpdate(query);
			System.out.println("Cientifico insertado exitosamente.");
			
			if(cientifico.getProyectos().size() > 0) {
				for (Proyecto proyecto : cientifico.getProyectos()) {
					query = "insert into asignado_a(dni_cientifico, id_proyecto) values('"+cientifico.getDni()+"', '"+proyecto.getId()+"');";
					st = this.connection.createStatement();
					st.executeUpdate(query);
					System.out.println("Proyecto asignado correctamente");
				}
			}
			status = 200;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			status = 400;
		}
		
		return status;
	}
	
	public int updateCientifico(Cientifico cientifico) {
		String query = "update cientifico "
				     + "set nomapels = '"+cientifico.getNomapels()+"' "
				     + "where dni = '"+cientifico.getDni()+"';";
		int status = 0;
		System.out.println(query);
		try {
			Statement st = this.connection.createStatement();
			st.executeUpdate(query);
			System.out.println("Cientifico actualizado exitosamente.");
			
			//Eliminar primero todas las asignaciones para así quitar las anteriores asignaciones que 
			//no se encuentran ahora en cliente
			
			//Eliminar todas las asignaciones de cientificos a proyectos
			query = "delete from asignado_a where dni_cientifico = '"+cientifico.getDni()+"';";
			st = this.connection.createStatement();
			st.executeUpdate(query);
			System.out.println("Proyectos desasignados correctamente");
			
			//Asignar los nuevos proyectos al cientifico
			if(cientifico.getProyectos().size() > 0) {
				for (Proyecto proyecto : cientifico.getProyectos()) {
					query = "insert into asignado_a(dni_cientifico, id_proyecto) values('"+cientifico.getDni()+"', '"+proyecto.getId()+"');";
					st = this.connection.createStatement();
					st.executeUpdate(query);
					System.out.println("Proyecto asignado correctamente");
				}
			}
			status = 200;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			status = 400;
		}
		
		return status;
	}
	
	public int deleteCientifico(Cientifico cientifico) {
		String query = "delete from cientifico "
				     + "where dni = '" + cientifico.getDni()+"';";
		int status = 0;
		System.out.println(query);
		try {
			Statement st = this.connection.createStatement();
			st.executeUpdate(query);
			System.out.println("Cliente eliminado existosamente.");

			query = "delete from asignado_a where dni_cientifico = '"+cientifico.getDni()+"';";
			st = this.connection.createStatement();
			st.executeUpdate(query);
			System.out.println("Proyectos desasignados correctamente");
			
			status = 200;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			status = 400;
		}
		
		return status;
	}
	
	
	public ArrayList<Proyecto> getAllProyectos(){
		ArrayList<Proyecto> proyectos = new ArrayList<Proyecto>();
		java.sql.ResultSet results = null;
		java.sql.ResultSet resultsProyecto = null;
		String query = "select * from proyecto;";;
		Proyecto proyecto;
		String id;
		String nombre;
		int horas;
		
		try {
			Statement st = this.connection.createStatement();
			results = st.executeQuery(query);
			
			while(results.next()){
				id = results.getString("id");
				nombre = results.getString("nombre");
				horas = Integer.parseInt(results.getString("horas"));
				
				proyecto = new Proyecto(id, nombre, horas);
				proyectos.add(proyecto);
			}
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return proyectos;
	}
	
	public int insertProyecto(Proyecto proyecto) {
		String query = "insert into proyecto(id, nombre, horas) values('"+proyecto.getId()+"', '"+proyecto.getNombre()+"', "+proyecto.getHoras()+");";
		int status = 0;
		try {
			Statement st = this.connection.createStatement();
			st.executeUpdate(query);
			System.out.println("Proyecto insertado exitosamente.");

			status = 200;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			status = 400;
		}
		
		return status;
	}
	
	public int updateProyecto(Proyecto proyecto) {
		String query = "update proyecto "
				     + "set nombre = '"+proyecto.getNombre()+"', horas = "+proyecto.getHoras()+
				      " where id = '"+proyecto.getId()+"';";
		int status = 0;
		System.out.println(query);
		try {
			Statement st = this.connection.createStatement();
			st.executeUpdate(query);
			System.out.println("Proyecto actualizado exitosamente.");
			
			status = 200;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			status = 400;
		}
		
		return status;
	}
	
	public int deleteProyecto(Proyecto proyecto) {
		String query = "delete from proyecto "
				     + "where id = '" + proyecto.getId()+"';";
		int status = 0;
		System.out.println(query);
		try {
			Statement st = this.connection.createStatement();
			st.executeUpdate(query);
			System.out.println("Proyecto eliminado existosamente.");

			query = "delete from asignado_a where id_proyecto = '"+proyecto.getId()+"';";
			st = this.connection.createStatement();
			st.executeUpdate(query);
			System.out.println("Cientificos desasignados correctamente");
			
			status = 200;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			status = 400;
		}
		
		return status;
	}
}
