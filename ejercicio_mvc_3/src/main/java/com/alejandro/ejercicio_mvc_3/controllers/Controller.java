package com.alejandro.ejercicio_mvc_3.controllers;

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

import com.alejandro.ejercicio_mvc_3.dbcon.MyConnectionManager;
import com.alejandro.ejercicio_mvc_3.models.Cientifico;
import com.alejandro.ejercicio_mvc_3.models.Proyecto;
import com.alejandro.ejercicio_mvc_3.views.VistaCientificos;
import com.alejandro.ejercicio_mvc_3.views.VistaFormularioCientifico;
import com.alejandro.ejercicio_mvc_3.views.VistaFormularioProyecto;
import com.alejandro.ejercicio_mvc_3.views.VistaLogin;
import com.alejandro.ejercicio_mvc_3.views.VistaProyectos;
import com.alejandro.ejercicio_mvc_3.views.VistaSelectVista;

public class Controller {

	private final String DATABASE = "mvc_cientificos";

	private VistaLogin vistaLogin;
	private VistaSelectVista vistaSeleccionarVista;
	private MyConnectionManager cm;
	private VistaCientificos vistaCientificos;
	private VistaFormularioCientifico vistaFormularioCientifico;
	private VistaProyectos vistaProyectos;
	private VistaFormularioProyecto vistaFormularioProyecto;
	private Cientifico cientifico;
	private Proyecto proyecto;

	private ActionListener actionListenerLogin;
	private ActionListener actionListenerAbrirVistaCientificos;
	private ActionListener actionListenerAbrirVistaProyectos;
	private ActionListener actionListenerNuevoCientifico;
	private ActionListener actionListenerEditarCientifico;
	private ActionListener actionListenerEliminarcientifico;
	private ActionListener actionListenerActualizarCientifico;
	private ActionListener actionListenerGuardarCientifico;
	private ActionListener actionListenerNuevoProyecto;
	private ActionListener actionListenerEditarProyecto;
	private ActionListener actionListenerEliminarProyecto;
	private ActionListener actionListenerActualizarPryecto;
	private ActionListener actionListenerGuardarProyecto;
	private ActionListener actionListenerVolverSeleccionVista;

	private ListSelectionListener ListSelectionListenerSeleccionarCientifico;
	private ListSelectionListener listSelectionListenerSeleccionarProyecto;

	private WindowListener winbdowListenerOnFormularioCientificoClose;
	private WindowListener winbdowListenerOnFormularioProyectoClose;

	public Controller(VistaLogin vistaLogin, VistaSelectVista vistaSeleccionarVista, VistaCientificos vistaCientificos,
			VistaFormularioCientifico vistaFormularioCientifico, VistaProyectos vistaProyectos,
			VistaFormularioProyecto vistaFormularioProyecto, MyConnectionManager cm, Cientifico cientifico,
			Proyecto proyecto) {
		
		this.vistaLogin = vistaLogin;
		this.vistaSeleccionarVista = vistaSeleccionarVista;
		this.vistaCientificos = vistaCientificos;
		this.vistaFormularioCientifico = vistaFormularioCientifico;
		this.vistaProyectos = vistaProyectos;
		this.vistaFormularioProyecto = vistaFormularioProyecto;
		this.proyecto = proyecto;
		this.cm = cm;
		this.cientifico = cientifico;

		instanciarActionListeners();

		// Añadir el listener al boton de la vista de login
		this.vistaLogin.getLoginBtn().addActionListener(actionListenerLogin);

		// Añadir listener a los botones de la vista para seleccionar vista
		this.vistaSeleccionarVista.getVistaCientificosBtn().addActionListener(actionListenerAbrirVistaCientificos);
		this.vistaSeleccionarVista.getVistaProyectosButton().addActionListener(actionListenerAbrirVistaProyectos);

		// Añadir listener a los botones de la vista cientificos
		this.vistaCientificos.getBtnNuevoCientifico().addActionListener(actionListenerNuevoCientifico);
		this.vistaCientificos.getListViewCientificos().addListSelectionListener(ListSelectionListenerSeleccionarCientifico);
		this.vistaCientificos.getBtnEditarCientifico().addActionListener(actionListenerEditarCientifico);
		this.vistaCientificos.getBtnEliminarCientifico().addActionListener(actionListenerEliminarcientifico);
		this.vistaCientificos.getBtnAtras().addActionListener(actionListenerVolverSeleccionVista);

		// Añadir listener a los botones de la vista formulario cientifico
		this.vistaFormularioCientifico.getBtnGuardar().addActionListener(actionListenerGuardarCientifico);
		this.vistaFormularioCientifico.getBtnGuardarCambios().addActionListener(actionListenerActualizarCientifico);
		this.vistaFormularioCientifico.getBtnEliminar().addActionListener(actionListenerEliminarcientifico);
		this.vistaFormularioCientifico.addWindowListener(winbdowListenerOnFormularioCientificoClose);
		
		//Añadir listener a los botones de la vista proyectos
		this.vistaProyectos.getBtnNuevoProyecto().addActionListener(actionListenerNuevoProyecto);
		this.vistaProyectos.getBtnEditarProyecto().addActionListener(actionListenerEditarProyecto);
		this.vistaProyectos.getBtnEliminarProyecto().addActionListener(actionListenerEliminarProyecto);
		this.vistaProyectos.getListViewProyecto().addListSelectionListener(listSelectionListenerSeleccionarProyecto);
		this.vistaProyectos.getBtnAtras().addActionListener(actionListenerVolverSeleccionVista);
		
		//Añadir listeners a los botones de la vista formulario proyecto
		this.vistaFormularioProyecto.getBtnGuardar().addActionListener(actionListenerGuardarProyecto);
		this.vistaFormularioProyecto.getBtnEliminar().addActionListener(actionListenerEliminarProyecto);
		this.vistaFormularioProyecto.getBtnGuardarCambios().addActionListener(actionListenerActualizarPryecto);
		this.vistaFormularioProyecto.addWindowListener(winbdowListenerOnFormularioProyectoClose);
	}

	private void instanciarActionListeners() {
		actionListenerLogin = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String inUser = vistaLogin.getUsuarioTF().getText();
				String inPwd = vistaLogin.getPasswdTF().getText();

				if (cm.startConnectionDB(inUser, inPwd, DATABASE) == 200) {
					vistaLogin.setVisible(false);
					iniciarVistaSeleccionarVista();

				} else {
					JOptionPane.showConfirmDialog(null, "Los datos de login son incorrectos");
				}

			}
		};

		actionListenerAbrirVistaCientificos = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vistaSeleccionarVista.setVisible(false);
				iniciarVistaCientificos();
			}
		};
		
		//Listener que abre el formulario para introducir datos de un nuevo cientifico
		actionListenerNuevoCientifico = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vistaCientificos.setVisible(false);
				iniciarVistaFormularioCientifico();
			}
		};
		
		//Listener que guarda los datos del cientifico al pulsar guardar en el formulario de nuevo cientifico
		actionListenerGuardarCientifico = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String dni = vistaFormularioCientifico.getDniCientifico();
				String nomapels = vistaFormularioCientifico.getNomApelsCientifico();
				String proyectos = vistaFormularioCientifico.getProyectosCientifico();
				String[] proyectosArray = proyectos.split(",");

				cientifico.clearInfo();
				cientifico.setDni(dni);
				cientifico.setNomapels(nomapels);
				System.out.println("Insertar");
				if (!proyectos.trim().equals("")) {
					for (int i = 0; i < proyectosArray.length; i++) {
						String id = proyectosArray[i].trim();
						cientifico.getProyectos().add(new Proyecto(id));
					}
				}

				if(dni.trim().length() == 8 && !nomapels.trim().equals("")) {
					int status = cm.insertCientifico(cientifico);

					if (status == 200) {
						// Deshabilitar los botones editar y eliminar hasta que se vuelta a seleccionar
						vistaCientificos.getBtnEditarCientifico().setEnabled(false);
						vistaCientificos.getBtnEliminarCientifico().setEnabled(false);
						vistaFormularioCientifico.getBtnEliminar().setVisible(false);
						vistaFormularioCientifico.setVisible(false);

						limpiarVistaFormularioCientifico();
						iniciarVistaCientificos();
					} else {
						JOptionPane.showConfirmDialog(null, "Los datos son incorrectos");
					}
				}
			}
		};

		/*
		 * Listener para que cuando se selecciona un elemento de la lista de habiliten
		 * los botones de editar y eliminar
		 */
		ListSelectionListenerSeleccionarCientifico = new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (vistaCientificos.getListViewCientificos().getSelectedValue() != null) {
					vistaCientificos.getBtnEditarCientifico().setEnabled(true);
					vistaCientificos.getBtnEliminarCientifico().setEnabled(true);
				}
			}
		};
		
		//Listener que abre el formulario de cientifico con los datos del cientifico seleccionado
		actionListenerEditarCientifico = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cientifico = (Cientifico) vistaCientificos.getListViewCientificos().getSelectedValue();
				vistaCientificos.setVisible(false);
				iniciarVistaFormularioCientifico(cientifico);
			}
		};
		
		//Listener que al pulsar guardar en el formulario de editar cientifico guarda a este en base de datos
		actionListenerActualizarCientifico = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String dni = vistaFormularioCientifico.getDniCientifico();
				String nomapels = vistaFormularioCientifico.getNomApelsCientifico();
				String proyectos = vistaFormularioCientifico.getProyectosCientifico();
				String[] proyectosArray = proyectos.split(",");

				cientifico.clearInfo();
				cientifico.setDni(dni);
				cientifico.setNomapels(nomapels);

				if (!proyectos.trim().equals("")) {
					for (int i = 0; i < proyectosArray.length; i++) {
						String id = proyectosArray[i].trim();
						cientifico.getProyectos().add(new Proyecto(id));
					}
				}

				int status = cm.updateCientifico(cientifico);

				if (status == 200) {
					// Deshabilitar los botones editar y eliminar hasta que se vuelta a seleccionar
					vistaCientificos.getBtnEditarCientifico().setEnabled(false);
					vistaCientificos.getBtnEliminarCientifico().setEnabled(false);
					vistaFormularioCientifico.getBtnEliminar().setVisible(false);
					vistaFormularioCientifico.getBtnGuardarCambios().setVisible(false);
					vistaFormularioCientifico.setVisible(false);

					limpiarVistaFormularioCientifico();
					;
					iniciarVistaCientificos();
				} else {
					JOptionPane.showConfirmDialog(null, "Los datos son incorrectos");
				}

			}
		};
		
		/*Listener que elimina al cientifico de la base de datos al pulsar eliminar en el formulario de editar cientifico
		 * o en la vista general de cientificos*/
		actionListenerEliminarcientifico = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cientifico = (Cientifico) vistaCientificos.getListViewCientificos().getSelectedValue();

				int status = cm.deleteCientifico(cientifico);

				if (status == 200) {
					// Deshabilitar los botones editar y eliminar hasta que se vuelta a seleccionar
					vistaCientificos.getBtnEditarCientifico().setEnabled(false);
					vistaCientificos.getBtnEliminarCientifico().setEnabled(false);
					vistaFormularioCientifico.getBtnEliminar().setVisible(false);
					vistaFormularioCientifico.getBtnGuardarCambios().setVisible(false);
					vistaFormularioCientifico.setVisible(false);

					limpiarVistaFormularioCientifico();
					iniciarVistaCientificos();
				} else {
					JOptionPane.showConfirmDialog(null, "Error al borrar");
				}
			}
		};
		
		//Listener que al cerrar el formulario de cientifico vuelve a mostrar la vista general de cientificos
		winbdowListenerOnFormularioCientificoClose = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//Deshabilitar los botones editar y eliminar hasta que se vuelta a seleccionar un elemento de la lista
				vistaCientificos.getBtnEditarCientifico().setEnabled(false);
				vistaCientificos.getBtnEliminarCientifico().setEnabled(false);
				vistaFormularioCientifico.getBtnEliminar().setVisible(false);
				vistaFormularioCientifico.getBtnGuardarCambios().setVisible(false);
				vistaFormularioCientifico.setVisible(false);

				limpiarVistaFormularioCientifico();
				iniciarVistaCientificos();
			}
		};
		
		//Listener que abre la lista de proyectos al pulsar en eel selector de vistas
		actionListenerAbrirVistaProyectos = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vistaSeleccionarVista.setVisible(false);
				iniciarVistaProyectos();
			}
		};
		
		//Listener que abre el formulario de proyecto para instertar datos de un nuevo proyecto
		actionListenerNuevoProyecto = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vistaProyectos.setVisible(false);
				iniciarVistaFormularioProyecto();
			}
		};
		
		//Listener que abre el formulario de proyecto con los datos del proyecto seleccionado
		actionListenerEditarProyecto = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				proyecto = (Proyecto) vistaProyectos.getListViewProyecto().getSelectedValue();
				vistaProyectos.setVisible(false);
				iniciarVistaFormularioProyecto(proyecto);
			}
		};
		
		/*Listener que elimina el proyecto de la base de datos al pulsar eliminar tanto en el formulario
		 * de editar proyecto como en la vista general de proyectos*/
		actionListenerEliminarProyecto = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				proyecto = (Proyecto) vistaProyectos.getListViewProyecto().getSelectedValue();
				int status = cm.deleteProyecto(proyecto);

				if (status == 200) {
					// Deshabilitar los botones editar y eliminar hasta que se vuelta a seleccionar un elemento de la lista
					vistaProyectos.getBtnEditarProyecto().setEnabled(false);
					vistaProyectos.getBtnEliminarProyecto().setEnabled(false);
					vistaFormularioProyecto.getBtnEliminar().setVisible(false);
					vistaFormularioProyecto.getBtnGuardarCambios().setVisible(false);
					vistaFormularioProyecto.setVisible(false);

					limpiarVistaFormularioProyecto();
					iniciarVistaProyectos();
				} else {
					JOptionPane.showConfirmDialog(null, "Error al borrar");
				}
			}
		};
		
		//Listener que desbloquea los botones de editar y eliminar al seleccionar un elemento de la lista
		listSelectionListenerSeleccionarProyecto = new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (vistaProyectos.getListViewProyecto().getSelectedValue() != null) {
					vistaProyectos.getBtnEditarProyecto().setEnabled(true);
					vistaProyectos.getBtnEliminarProyecto().setEnabled(true);
				}
			}
		};
		
		//Listener que cuando se pulsa guardar al crear un nuevo proyeto, este se guarde en based de datos
		actionListenerGuardarProyecto = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String id = vistaFormularioProyecto.getIdProyecto();
				String nombre = vistaFormularioProyecto.getNombreProyecto();
				int horas = Integer.valueOf(vistaFormularioProyecto.getHorasProyecto());

				proyecto.clearInfo();
				proyecto.setId(id);
				proyecto.setNombre(nombre);
				proyecto.setHoras(horas);
				
				if(!nombre.trim().equals("")) {
					int status = cm.insertProyecto(proyecto);

					if (status == 200) {
						// Deshabilitar los botones editar y eliminar hasta que se vuelta a seleccionar un elemento de la lista
						vistaProyectos.getBtnEditarProyecto().setEnabled(false);
						vistaProyectos.getBtnEliminarProyecto().setEnabled(false);
						vistaFormularioProyecto.getBtnEliminar().setVisible(false);
						vistaFormularioProyecto.setVisible(false);

						limpiarVistaFormularioProyecto();
						iniciarVistaProyectos();
					} else {
						JOptionPane.showConfirmDialog(null, "Los datos son incorrectos");
					}
				}
				
			}
		};
		
		//Listener para cuando se pulsa guardar al editar un proyecto, este se actualize en base de datos
		actionListenerActualizarPryecto = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String id = vistaFormularioProyecto.getIdProyecto();
				String nombre = vistaFormularioProyecto.getNombreProyecto();
				int horas = Integer.valueOf(vistaFormularioProyecto.getHorasProyecto());

				proyecto.clearInfo();
				proyecto.setId(id);
				proyecto.setNombre(nombre);
				proyecto.setHoras(horas);

				if(!nombre.trim().equals("")) {
					int status = cm.updateProyecto(proyecto);

					if (status == 200) {
						// Deshabilitar los botones editar y eliminar hasta que se vuelta a seleccionar un elemento de la lista
						vistaProyectos.getBtnEditarProyecto().setEnabled(false);
						vistaProyectos.getBtnEliminarProyecto().setEnabled(false);
						vistaFormularioProyecto.getBtnEliminar().setVisible(false);
						vistaFormularioProyecto.setVisible(false);

						limpiarVistaFormularioProyecto();
						iniciarVistaProyectos();
					} else {
						JOptionPane.showConfirmDialog(null, "Los datos son incorrectos");
					}
				}

			}
		};
		
		//Listener para que cuando se cierre el formulario de proyecto se vuelva a la vista proyectos
		winbdowListenerOnFormularioProyectoClose = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//Deshabilitar los botones editar y eliminar hasta que se vuelta a seleccionar un elemento de la lista
				vistaProyectos.getBtnEditarProyecto().setEnabled(false);
				vistaProyectos.getBtnEliminarProyecto().setEnabled(false);
				vistaFormularioProyecto.getBtnEliminar().setVisible(false);
				vistaFormularioProyecto.getBtnGuardarCambios().setVisible(false);
				vistaFormularioProyecto.setVisible(false);

				limpiarVistaFormularioProyecto();;
				iniciarVistaProyectos();
			}
		};
		
		//Listener para volver a la selección de vista al pultas atras
		actionListenerVolverSeleccionVista = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vistaProyectos.setVisible(false);
				vistaCientificos.setVisible(false);
				iniciarVistaSeleccionarVista();
			}
		};
	}

	// Inicia la vista para hacer login
	public void iniciarVistaLogin() {
		vistaLogin.setTitle("Login");
		vistaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vistaLogin.setLocationRelativeTo(null);
		vistaLogin.setVisible(true);
	}

	public void iniciarVistaSeleccionarVista() {
		vistaSeleccionarVista.setTitle("Seleccionar Vista");
		vistaSeleccionarVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vistaSeleccionarVista.setLocationRelativeTo(null);
		vistaSeleccionarVista.setVisible(true);
	}

	// Inicia vista principal de cientificos
	public void iniciarVistaCientificos() {
		vistaCientificos.setTitle("Gestor de Cientificos");
		vistaCientificos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vistaCientificos.setLocationRelativeTo(null);
		showCientificos();
		vistaCientificos.setVisible(true);
	}

	// Inicia vista principal de proyectos
	public void iniciarVistaProyectos() {
			vistaProyectos.setTitle("Gestor de Proyectos");
			vistaProyectos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			vistaProyectos.setLocationRelativeTo(null);	
			showProyectos();
			vistaProyectos.setVisible(true);
	}

	private void showCientificos() {
		ArrayList<Cientifico> cientificos = cm.getAllCientificos();

		vistaCientificos.limppiarModel();

		for (Cientifico cientifico : cientificos) {
			vistaCientificos.addCientifico(cientifico);
		}
	}
	
	private void showProyectos() {
		ArrayList<Proyecto> proyectos = cm.getAllProyectos();

		vistaProyectos.limppiarModel();

		for (Proyecto proyecto : proyectos) {
			vistaProyectos.addProyecto(proyecto);
		}
	}

	private void iniciarVistaFormularioCientifico() {
		vistaFormularioCientifico.setTitle("Formulario Cientifico");
		vistaFormularioCientifico.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		vistaFormularioCientifico.setLocationRelativeTo(null);
		vistaFormularioCientifico.getBtnGuardar().setVisible(true); // mostrar el boton para añadir nuevo cientifico a la
																	// db
		vistaFormularioCientifico.setVisible(true);
	}

	private void iniciarVistaFormularioCientifico(Cientifico cientifico) {
		vistaFormularioCientifico.setTitle("Formulario Cientifico");
		vistaFormularioCientifico.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		vistaFormularioCientifico.setLocationRelativeTo(null);
		vistaFormularioCientifico.getBtnGuardar().setVisible(false); // mostrar el boton para añadir nuevo proyecto a la
																		// db
		vistaFormularioCientifico.getBtnGuardarCambios().setVisible(true);
		vistaFormularioCientifico.getBtnEliminar().setVisible(true);

		vistaFormularioCientifico.setDniCientifico(cientifico.getDni());
		vistaFormularioCientifico.setNomApelsCientifico(cientifico.getNomapels());

		String proyectos = "";

		if (cientifico.getProyectos() != null && cientifico.getProyectos().size() > 0) {
			for (Proyecto proyecto : cientifico.getProyectos()) {
				proyectos += proyecto.getId() + ",";
			}
		}

		vistaFormularioCientifico.setProyectosCientifico(proyectos);

		vistaFormularioCientifico.setVisible(true);
	}
	
	private void iniciarVistaFormularioProyecto() {
		vistaFormularioProyecto.setTitle("Formulario Proyecto");
		vistaFormularioProyecto.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		vistaFormularioProyecto.setLocationRelativeTo(null);
		vistaFormularioProyecto.getBtnGuardar().setVisible(true); // mostrar el boton para añadir nuevo proyecto a la
																	// db
		vistaFormularioProyecto.setVisible(true);
	}

	private void iniciarVistaFormularioProyecto(Proyecto proyecto) {
		vistaFormularioProyecto.setTitle("Formulario Cientifico");
		vistaFormularioProyecto.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		vistaFormularioProyecto.setLocationRelativeTo(null);
		vistaFormularioProyecto.getBtnGuardar().setVisible(false); // mostrar el boton para añadir nuevo proyecto a la
																		// db
		vistaFormularioProyecto.getBtnGuardarCambios().setVisible(true);
		vistaFormularioProyecto.getBtnEliminar().setVisible(true);

		vistaFormularioProyecto.setIdProyecto(proyecto.getId());;
		vistaFormularioProyecto.setNombreProyecto(proyecto.getNombre());
		vistaFormularioProyecto.setHorasProyecto(proyecto.getHoras());

		vistaFormularioProyecto.setVisible(true);
	}
	
	private void limpiarVistaFormularioCientifico() {
		vistaFormularioCientifico.setDniCientifico("");
		vistaFormularioCientifico.setNomApelsCientifico("");
		vistaFormularioCientifico.setProyectosCientifico("");
	}
	
	private void limpiarVistaFormularioProyecto() {
		vistaFormularioProyecto.setIdProyecto("");;
		vistaFormularioProyecto.setNombreProyecto("");
		vistaFormularioProyecto.setHorasProyecto(0);
	}
}
