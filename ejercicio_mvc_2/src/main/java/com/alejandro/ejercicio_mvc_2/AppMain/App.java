package com.alejandro.ejercicio_mvc_2.AppMain;

import com.alejandro.ejercicio_mvc_2.controllers.Controller;
import com.alejandro.ejercicio_mvc_2.dbcon.MyConnectionManager;
import com.alejandro.ejercicio_mvc_2.models.Cliente;
import com.alejandro.ejercicio_mvc_2.models.Video;
import com.alejandro.ejercicio_mvc_2.views.VistaCliente;
import com.alejandro.ejercicio_mvc_2.views.VistaFormularioCliente;
import com.alejandro.ejercicio_mvc_2.views.VistaFormularioVideo;
import com.alejandro.ejercicio_mvc_2.views.VistaLogin;
import com.alejandro.ejercicio_mvc_2.views.VistaSelectVista;
import com.alejandro.ejercicio_mvc_2.views.VistaVideos;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		Video video = new Video();
		VistaCliente vistaClientes = new VistaCliente();
		VistaLogin login = new VistaLogin();
		VistaFormularioCliente formulario = new VistaFormularioCliente();
		VistaSelectVista seleccionVista = new VistaSelectVista();
		VistaVideos vistaVideos = new VistaVideos();
		VistaFormularioVideo formularioVideo = new VistaFormularioVideo();
		MyConnectionManager cm = new MyConnectionManager();
		Controller controller = new Controller(login, vistaClientes, formulario, seleccionVista, vistaVideos, formularioVideo, cliente, video, cm);
		controller.iniciarVistaLogin();
	}
}
