package com.alejandro.ejercicio_mvc_3.AppMain;

import com.alejandro.ejercicio_mvc_3.controllers.Controller;
import com.alejandro.ejercicio_mvc_3.dbcon.MyConnectionManager;
import com.alejandro.ejercicio_mvc_3.models.Cientifico;
import com.alejandro.ejercicio_mvc_3.models.Proyecto;
import com.alejandro.ejercicio_mvc_3.views.VistaCientificos;
import com.alejandro.ejercicio_mvc_3.views.VistaFormularioCientifico;
import com.alejandro.ejercicio_mvc_3.views.VistaFormularioProyecto;
import com.alejandro.ejercicio_mvc_3.views.VistaLogin;
import com.alejandro.ejercicio_mvc_3.views.VistaProyectos;
import com.alejandro.ejercicio_mvc_3.views.VistaSelectVista;

public class App {
	public static void main(String[] args) {
		VistaLogin login = new VistaLogin();
		VistaSelectVista seleccionarVista = new VistaSelectVista();
		VistaCientificos vistaCientificos = new VistaCientificos();
		VistaFormularioCientifico vistaFormularioCientifico = new VistaFormularioCientifico();
		VistaProyectos vistaProyectos = new VistaProyectos();
		VistaFormularioProyecto vistaFormularioProyecto = new VistaFormularioProyecto();
		MyConnectionManager cm = new MyConnectionManager();
		Cientifico cientifico = new Cientifico();
		Proyecto proyecto = new Proyecto();
		Controller controller = new Controller(login, seleccionarVista, vistaCientificos, vistaFormularioCientifico,
				vistaProyectos, vistaFormularioProyecto, cm, cientifico, proyecto);
		controller.iniciarVistaLogin();
	}
}
