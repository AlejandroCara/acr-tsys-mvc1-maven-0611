package com.alejandro.ejercicio_mvc_1.dbconnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alejandro.ejercicio_mvc_1.models.Cliente;

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
	

	public int insertCliente(Cliente cliente) {
		String query = "insert into cliente(nombre, apellido, direccion, dni, fecha) "
				+ "values('"+cliente.getNombre()+"', '"+cliente.getApellido()+"', '"+cliente.getDireccion()+"', '"+cliente.getDni()+"', "
						+ "'"+cliente.getFecha()+"');";
		int status = 0;
		try {
			Statement st = this.connection.createStatement();
			st.executeUpdate(query);
			System.out.println("Query ejecutada exitosamente.");
			status = 200;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			status = 400;
		}
		
		return status;
	}
	
	public ArrayList<Cliente> getAllClientes(){
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		java.sql.ResultSet results = null;
		String query = "select * from cliente";
		int id;
		Cliente cliente;
		String nombre;
		String apellido;
		String direccion;
		String dni;
		String fecha;
		
		try {
			Statement st = this.connection.createStatement();
			results = st.executeQuery(query);
			
			while(results.next()){
				id = Integer.parseInt(results.getString("id"));
				nombre = results.getString("nombre");
				apellido = results.getString("apellido");
				direccion = results.getString("direccion");
				dni = results.getString("dni");
				fecha = results.getString("fecha");
				
				cliente = new Cliente(id, nombre, apellido, direccion, dni, fecha);
				clientes.add(cliente);
			}
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return clientes;
	}

	
	public int updateCliente(Cliente cliente) {
		String query = "update cliente "
				     + "set nombre = '"+cliente.getNombre()+"', apellido = '"+cliente.getApellido()+"', direccion = '"+cliente.getDireccion()+"', "
				     + "dni = '"+cliente.getDni()+"', fecha = '"+cliente.getFecha()+"'"
				     + "where id = " + cliente.getId()+";";
		int status = 0;
		System.out.println(query);
		try {
			Statement st = this.connection.createStatement();
			st.executeUpdate(query);
			System.out.println("Cliente actualizado exitosamente.");
			status = 200;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			status = 400;
		}
		
		return status;
	}
	
	public int deleteCliente(Cliente cliente) {
		String query = "delete from cliente "
				     + "where id = " + cliente.getId()+";";
		int status = 0;
		System.out.println(query);
		try {
			Statement st = this.connection.createStatement();
			st.executeUpdate(query);
			System.out.println("Cliente eliminado existosamente.");
			status = 200;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			status = 400;
		}
		
		return status;
	}
}
