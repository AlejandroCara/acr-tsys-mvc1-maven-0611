package com.alejandro.ejercicio_mvc_1.AppMain;

import com.alejandro.ejercicio_mvc_1.controllers.Controller;
import com.alejandro.ejercicio_mvc_1.dbconnection.MyConnectionManager;
import com.alejandro.ejercicio_mvc_1.models.Cliente;
import com.alejandro.ejercicio_mvc_1.views.VistaCliente;
import com.alejandro.ejercicio_mvc_1.views.VistaFormularioCliente;
import com.alejandro.ejercicio_mvc_1.views.VistaLogin;

public class App {
	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		VistaCliente vistaClientes = new VistaCliente();
		VistaLogin login = new VistaLogin();
		VistaFormularioCliente formulario = new VistaFormularioCliente();
		MyConnectionManager cm = new MyConnectionManager();
		Controller controller = new Controller(login, vistaClientes, formulario, cliente, cm);
		controller.iniciarVistaLogin();
	}
}
