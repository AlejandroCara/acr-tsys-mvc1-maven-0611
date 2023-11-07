package com.alejandro.ejercicio_mvc_1.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.alejandro.ejercicio_mvc_1.dbconnection.MyConnectionManager;
import com.alejandro.ejercicio_mvc_1.models.Cliente;
import com.alejandro.ejercicio_mvc_1.views.VistaCliente;
import com.alejandro.ejercicio_mvc_1.views.VistaFormularioCliente;
import com.alejandro.ejercicio_mvc_1.views.VistaLogin;

public class Controller {
	
	private final String DATABSE = "mvc_clientes";
	
	private VistaCliente vistaClientes;
	private VistaLogin vistaLogin;
	private VistaFormularioCliente vistaFormulario;
	private Cliente cliente;
	private MyConnectionManager cm;
	private ActionListener nuevoCliente;
	private ActionListener editarCliente;
	private ActionListener updateCliente;
	private ActionListener eliminarCliente;
	private ActionListener loginActionListener;
	private ActionListener guardarCliente;
	private ListSelectionListener listSelectionListener;
	private WindowListener onVistaFormularioClose;

	
	public Controller(VistaLogin vista, VistaCliente vistaClientes, VistaFormularioCliente vistaFormulario, Cliente cliente, MyConnectionManager cm) {
		this.vistaLogin = vista;
		this.cliente = cliente;
		this.vistaFormulario = vistaFormulario;
		this.vistaClientes = vistaClientes;
		this.cm = cm;
		
		instanciarActionListeners();
		
		//Añadir listener al boton de la vista login
		this.vistaLogin.getLoginBtn().addActionListener(loginActionListener);
		
		//Añadir listeners a los botones de la vista clientes
		this.vistaClientes.getNewClienteBtn().addActionListener(nuevoCliente);
		this.vistaClientes.getClientesListView().addListSelectionListener(listSelectionListener);
		this.vistaClientes.getEditClienteBtn().addActionListener(editarCliente);
		this.vistaClientes.getDeleteClientBtn().addActionListener(eliminarCliente);
		
		//Añadir listener a los botones de la vista formaulario
		this.vistaFormulario.addWindowListener(onVistaFormularioClose);
		this.vistaFormulario.getGuardarBtn().addActionListener(guardarCliente);
		this.vistaFormulario.getGuardarCambiosBtn().addActionListener(updateCliente);
		this.vistaFormulario.getEliminarBtn().addActionListener(eliminarCliente);
	}
	
	//Inicia vista principal de clientes
	public void iniciarVistaClientes() {
		vistaClientes.setTitle("Gestor de Clientes");
		vistaClientes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vistaClientes.setLocationRelativeTo(null);	
		showClientes();
		vistaClientes.setVisible(true);
	}
	
	//Inicia la vista para hacer login
	public void iniciarVistaLogin() {
		vistaLogin.setTitle("Login");
		vistaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vistaLogin.setLocationRelativeTo(null);	
		vistaLogin.setVisible(true);
	}
	
	public void iniciarVistaFormularioCliente() {
		vistaFormulario.setTitle("Formulario Cliente");
		vistaFormulario.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		vistaFormulario.setLocationRelativeTo(null);	
		vistaFormulario.getGuardarBtn().setVisible(true); //mostrar el boton para añadir nuevo cliente a la db
		vistaFormulario.setVisible(true);
	}
	
	public void iniciarVistaFormularioCliente(Cliente cliente) {
		vistaFormulario.setTitle("Formulario Cliente");
		vistaFormulario.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		vistaFormulario.setLocationRelativeTo(null);	
		vistaFormulario.getEliminarBtn().setVisible(true); //mostrar el boton de borrar
		vistaFormulario.getGuardarBtn().setVisible(false); //ocultar el boton para añadir nuevo cliente a la db
		vistaFormulario.getGuardarCambiosBtn().setVisible(true); //mostar el boton para hacer update
		
		//Rellenar los campos del focmulario con los del cliente seleccionado
		vistaFormulario.setIdClienteTF(cliente.getId());
		vistaFormulario.setNombreClienteTF(cliente.getNombre());
		vistaFormulario.setApellidoClienteTF(cliente.getApellido());
		vistaFormulario.setDireccionClienteTF(cliente.getDireccion());
		vistaFormulario.setDniClienteTF(cliente.getDni());
		vistaFormulario.setFechaClienteTF(cliente.getFecha());
		
		
		vistaFormulario.setVisible(true);
	}
	
	private void instanciarActionListeners() {
		
		loginActionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				String inUser = vistaLogin.getUsuarioTF().getText();
				String inPwd = vistaLogin.getPasswdTF().getText();
				
				if(cm.startConnectionDB(inUser, inPwd, DATABSE) == 200) {
					vistaLogin.setVisible(false);
					iniciarVistaClientes();
				} else {
					JOptionPane.showConfirmDialog(null, "Los datos de login son incorrectos");
				}
				
			}
		};
		
		nuevoCliente = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				vistaClientes.setVisible(false);
				iniciarVistaFormularioCliente();
			}
		};
		
		/*Listener para que cuando se selecciona un elemento de la lista de habiliten
		 *los botones de editar y eliminar*/
		listSelectionListener = new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(vistaClientes.getClientesListView().getSelectedValue() != null) {
					vistaClientes.getEditClienteBtn().setEnabled(true);
					vistaClientes.getDeleteClientBtn().setEnabled(true);
				}
			}
		};
		
		//Listener que hace que cuando se cierra el formulario de cliente se reabra la vista de clientes
		onVistaFormularioClose = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//Deshabilitar los botones editar y eliminar hasta que se vuelta a seleccionar cliente
				vistaClientes.getEditClienteBtn().setEnabled(false); 
				vistaClientes.getDeleteClientBtn().setEnabled(false);
				vistaFormulario.getEliminarBtn().setVisible(false);
				
				limpiarFormulario();
				
				iniciarVistaClientes();
			}
		};
		
		guardarCliente = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(vistaFormulario.getIdClienteTF().getText());
				String nombre = vistaFormulario.getNombreClienteTF().getText();
				String apellido = vistaFormulario.getApellidoClienteTF().getText();
				String direccion = vistaFormulario.getDireccionClienteTF().getText();
				String dni = vistaFormulario.getDniClienteTF().getText();
				String fecha = vistaFormulario.getFechaClienteTF().getText();
				
				cliente.clearInfo();
				cliente.setId(id);
				cliente.setNombre(nombre);
				cliente.setApellido(apellido);
				cliente.setDireccion(direccion);
				cliente.setDni(dni);
				cliente.setFecha(fecha);
				
				int status = cm.insertCliente(cliente);
				
				if(status == 200) {
					//Deshabilitar los botones editar y eliminar hasta que se vuelta a seleccionar cliente
					vistaClientes.getEditClienteBtn().setEnabled(false); 
					vistaClientes.getDeleteClientBtn().setEnabled(false);
					vistaFormulario.setVisible(false);
					vistaFormulario.getEliminarBtn().setVisible(false);
					
					limpiarFormulario();
					iniciarVistaClientes();
				} else {
					JOptionPane.showConfirmDialog(null, "Los datos de login son incorrectos");
				}				
			}
		};
		
		editarCliente = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Cliente cliente = (Cliente)vistaClientes.getClientesListView().getSelectedValue();
				vistaClientes.setVisible(false);
				iniciarVistaFormularioCliente(cliente);
			}
		};
		
		updateCliente = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(vistaFormulario.getIdClienteTF().getText());
				String nombre = vistaFormulario.getNombreClienteTF().getText();
				String apellido = vistaFormulario.getApellidoClienteTF().getText();
				String direccion = vistaFormulario.getDireccionClienteTF().getText();
				String dni = vistaFormulario.getDniClienteTF().getText();
				String fecha = vistaFormulario.getFechaClienteTF().getText();
				
				cliente.clearInfo();
				cliente.setId(id);
				cliente.setNombre(nombre);
				cliente.setApellido(apellido);
				cliente.setDireccion(direccion);
				cliente.setDni(dni);
				cliente.setFecha(fecha);
				
				int status = cm.updateCliente(cliente);
				
				if(status == 200) {
					//Deshabilitar los botones editar y eliminar hasta que se vuelta a seleccionar cliente
					vistaClientes.getEditClienteBtn().setEnabled(false); 
					vistaClientes.getDeleteClientBtn().setEnabled(false);
					vistaFormulario.setVisible(false);
					vistaFormulario.getEliminarBtn().setVisible(false);
					
					limpiarFormulario();
					iniciarVistaClientes();
				} else {
					JOptionPane.showConfirmDialog(null, "Los datos de login son incorrectos");
				}
				
			}
		};
		
		eliminarCliente = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Cliente cliente = (Cliente)vistaClientes.getClientesListView().getSelectedValue();
				
				int status = cm.deleteCliente(cliente);
				
				if(status == 200) {
					//Deshabilitar los botones editar y eliminar hasta que se vuelta a seleccionar cliente
					vistaClientes.getEditClienteBtn().setEnabled(false); 
					vistaClientes.getDeleteClientBtn().setEnabled(false);
					vistaFormulario.getEliminarBtn().setVisible(false);
					
					limpiarFormulario();
					vistaFormulario.setVisible(false);
					iniciarVistaClientes();
				} else {
					JOptionPane.showConfirmDialog(null, "Los datos de login son incorrectos");
				}
			}
		};
	}
	
	//Listar los clientes devueltos de la base de datos en la vistaClientes
	private void showClientes() {
		ArrayList<Cliente> clientes = cm.getAllClientes();
		
		vistaClientes.limppiarModel();
		
		for (Cliente cliente : clientes) {
			vistaClientes.addClienteLista(cliente);
		}
	}
	
	private void limpiarFormulario() {
		//Vaciar los campos del formulario
		vistaFormulario.setIdClienteTF(0);
		vistaFormulario.setNombreClienteTF("");
		vistaFormulario.setApellidoClienteTF("");
		vistaFormulario.setDireccionClienteTF("");
		vistaFormulario.setDniClienteTF("");
		vistaFormulario.setFechaClienteTF("");
	}
}
