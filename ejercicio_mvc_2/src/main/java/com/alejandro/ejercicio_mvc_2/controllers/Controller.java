package com.alejandro.ejercicio_mvc_2.controllers;

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

import com.alejandro.ejercicio_mvc_2.dbcon.MyConnectionManager;
import com.alejandro.ejercicio_mvc_2.models.Cliente;
import com.alejandro.ejercicio_mvc_2.models.Video;
import com.alejandro.ejercicio_mvc_2.views.VistaCliente;
import com.alejandro.ejercicio_mvc_2.views.VistaFormularioCliente;
import com.alejandro.ejercicio_mvc_2.views.VistaFormularioVideo;
import com.alejandro.ejercicio_mvc_2.views.VistaLogin;
import com.alejandro.ejercicio_mvc_2.views.VistaSelectVista;
import com.alejandro.ejercicio_mvc_2.views.VistaVideos;

public class Controller {
	
	private final String DATABSE = "mvc_clientes";
	
	private VistaCliente vistaClientes;
	private VistaLogin vistaLogin;
	private VistaFormularioCliente vistaFormulario;
	private Cliente cliente;
	private Video video;
	private MyConnectionManager cm;
	private VistaSelectVista seleccionarVista;
	private VistaVideos vistaVideos;
	private VistaFormularioVideo formularioVideo;
	private ActionListener nuevoCliente;
	private ActionListener editarCliente;
	private ActionListener updateCliente;
	private ActionListener eliminarCliente;
	private ActionListener loginActionListener;
	private ActionListener guardarCliente;
	private ActionListener abrirVistaClientes;
	private ActionListener abrirVistaVideos;
	private ActionListener nuevoVideo;
	private ActionListener editarVideo;
	private ActionListener eliminarVideo;
	private ActionListener guardarVideo;
	private ActionListener updateVideo;
	private ActionListener atras;
	private ListSelectionListener seleccionarCliente;
	private ListSelectionListener seleccionarVideo;
	private WindowListener onVistaFormularioClose;
	private WindowListener onVistaFormularioVideoClose;

	
	public Controller(VistaLogin vista, VistaCliente vistaClientes, VistaFormularioCliente vistaFormulario, VistaSelectVista seleccionarVista, 
						VistaVideos vistaVideos, VistaFormularioVideo formularioVideo, Cliente cliente, Video video, MyConnectionManager cm) {
		this.vistaLogin = vista;
		this.cliente = cliente;
		this.video = video;
		this.vistaFormulario = vistaFormulario;
		this.vistaClientes = vistaClientes;
		this.cm = cm;
		this.seleccionarVista = seleccionarVista;
		this.vistaVideos = vistaVideos;
		this.formularioVideo = formularioVideo;
		
		instanciarActionListeners();
		
		//Añadir listener al boton de la vista login
		this.vistaLogin.getLoginBtn().addActionListener(loginActionListener);
		
		//Añadir listener a los botones de la vista para seleccionar vista
		this.seleccionarVista.getVistaClienteBtn().addActionListener(abrirVistaClientes);
		this.seleccionarVista.getVistaVideoButton().addActionListener(abrirVistaVideos);
		
		//Añadir listeners a los botones de la vista clientes
		this.vistaClientes.getNewClienteBtn().addActionListener(nuevoCliente);
		this.vistaClientes.getClientesListView().addListSelectionListener(seleccionarCliente);
		this.vistaClientes.getEditClienteBtn().addActionListener(editarCliente);
		this.vistaClientes.getDeleteClientBtn().addActionListener(eliminarCliente);
		this.vistaClientes.getAtrasBtn().addActionListener(atras);
		
		//Añadir listener a los botones de la vista formaulario cliente
		this.vistaFormulario.addWindowListener(onVistaFormularioClose);
		this.vistaFormulario.getGuardarBtn().addActionListener(guardarCliente);
		this.vistaFormulario.getGuardarCambiosBtn().addActionListener(updateCliente);
		this.vistaFormulario.getEliminarBtn().addActionListener(eliminarCliente);
		
		//Añadir listener a los botones de la vista videos
		this.vistaVideos.getNewVideoBtn().addActionListener(nuevoVideo);
		this.vistaVideos.getEditVideoBtn().addActionListener(editarVideo);
		this.vistaVideos.getDeleteVideoBtn().addActionListener(eliminarVideo);
		this.vistaVideos.getVideosListView().addListSelectionListener(seleccionarVideo);
		this.vistaVideos.getAtrasBtn().addActionListener(atras);
		
		//Añadir listeners a los botones de la vista formulario video
		this.formularioVideo.addWindowListener(onVistaFormularioVideoClose);
		this.formularioVideo.getGuardarBtn().addActionListener(guardarVideo);
		this.formularioVideo.getGuardarCambiosBtn().addActionListener(updateVideo);
		this.formularioVideo.getEliminarBtn().addActionListener(eliminarVideo);
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
	
	public void iniciarVistaVideos() {
		vistaVideos.setTitle("Gestor de Videos");
		vistaVideos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vistaVideos.setLocationRelativeTo(null);	
		showVideos();
		vistaVideos.setVisible(true);
	}
	
	public void iniciarVistaSeleccionarVista() {
		seleccionarVista.setTitle("Seleccionar Vista");
		seleccionarVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		seleccionarVista.setLocationRelativeTo(null);	
		seleccionarVista.setVisible(true);
	}
	
	public void iniciarVistaFormularioVideo() {
		formularioVideo.setTitle("Formulario Video");
		formularioVideo.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		formularioVideo.setLocationRelativeTo(null);	
		formularioVideo.getGuardarBtn().setVisible(true); //mostrar el boton para añadir nuevo cliente a la db
		formularioVideo.setVisible(true);
	}
	
	public void iniciarVistaFormularioVideo(Video video) {
		formularioVideo.setTitle("Formulario Video");
		formularioVideo.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		formularioVideo.setLocationRelativeTo(null);
		formularioVideo.getEliminarBtn().setVisible(true); //mostrar el boton de borrar
		formularioVideo.getGuardarBtn().setVisible(false); //ocultar el boton para añadir nuevo video a la db
		formularioVideo.getGuardarCambiosBtn().setVisible(true); //mostar el boton para hacer update
		
		//Rellenar los campos del focmulario con los del video seleccionado
		formularioVideo.setIdVideo(video.getId());
		formularioVideo.setTituloVideo(video.getTitulo());
		formularioVideo.setDirectorVideo(video.getDirector());
		formularioVideo.setIdClienteVideo(video.getCli_id());
		
		formularioVideo.setVisible(true);
	}
	
	private void instanciarActionListeners() {
		
		loginActionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				String inUser = vistaLogin.getUsuarioTF().getText();
				String inPwd = vistaLogin.getPasswdTF().getText();
				
				if(cm.startConnectionDB(inUser, inPwd, DATABSE) == 200) {
					vistaLogin.setVisible(false);
					iniciarVistaSeleccionarVista();
					//iniciarVistaClientes();
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
		seleccionarCliente = new ListSelectionListener() {
			
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
				
				limpiarFormularioCliente();
				
				iniciarVistaClientes();
			}
		};
		
		guardarCliente = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = vistaFormulario.getNombreClienteTF().getText();
				String apellido = vistaFormulario.getApellidoClienteTF().getText();
				String direccion = vistaFormulario.getDireccionClienteTF().getText();
				String dni = vistaFormulario.getDniClienteTF().getText();
				String fecha = vistaFormulario.getFechaClienteTF().getText();
				
				cliente.clearInfo();
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
					
					limpiarFormularioCliente();
					iniciarVistaClientes();
				} else {
					JOptionPane.showConfirmDialog(null, "Los datos son incorrectos");
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
					
					limpiarFormularioCliente();
					iniciarVistaClientes();
				} else {
					JOptionPane.showConfirmDialog(null, "Los datos son incorrectos");
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
					vistaFormulario.getGuardarCambiosBtn().setVisible(false);
					
					limpiarFormularioCliente();
					vistaFormulario.setVisible(false);
					iniciarVistaClientes();
				} else {
					JOptionPane.showConfirmDialog(null, "Los datos son incorrectos");
				}
			}
		};
		
		abrirVistaClientes = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				seleccionarVista.setVisible(false);
				iniciarVistaClientes();
			}
		};
		
		abrirVistaVideos = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				seleccionarVista.setVisible(false);
				iniciarVistaVideos();
			}
		};
		
		nuevoVideo = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				vistaVideos.setVisible(false);
				iniciarVistaFormularioVideo();
			}
		};
		
		guardarVideo = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String titulo = formularioVideo.getTituloVideo();
				String director = formularioVideo.getDirectorVideo();
				int idCliente = Integer.parseInt(formularioVideo.getIdClienteVideo());
				
				video.clearInfo();
				video.setTitulo(titulo);
				video.setDirector(director);
				video.setCli_id(idCliente);
				
				int status = cm.insertVideo(video);
				
				if(status == 200) {
					//Deshabilitar los botones editar y eliminar hasta que se vuelta a seleccionar video
					vistaVideos.getEditVideoBtn().setEnabled(false); 
					vistaVideos.getDeleteVideoBtn().setEnabled(false);
					formularioVideo.setVisible(false);
					formularioVideo.getEliminarBtn().setVisible(false);
					formularioVideo.getGuardarCambiosBtn().setVisible(false);
					
					limpiarFormularioVideo();
					iniciarVistaVideos();
				} else {
					JOptionPane.showConfirmDialog(null, "Los datos son incorrectos");
				}				
			}
		};
		
		//Listener que al cerrar el formulario de video vuelva a abrir la vista de videos
		onVistaFormularioVideoClose = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//Deshabilitar los botones editar y eliminar hasta que se vuelta a seleccionar cliente
				vistaVideos.getEditVideoBtn().setEnabled(false);
				vistaVideos.getDeleteVideoBtn().setEnabled(false);
				formularioVideo.getEliminarBtn().setEnabled(false);
				
				limpiarFormularioVideo();
				
				iniciarVistaVideos();
			}
		}; 
		
		/*Listener para que cuando se selecciona un elemento de la lista de habiliten
		 *los botones de editar y eliminar*/
		seleccionarVideo = new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(vistaVideos.getVideosListView().getSelectedValue() != null) {
					vistaVideos.getEditVideoBtn().setEnabled(true);
					vistaVideos.getDeleteVideoBtn().setEnabled(true);
				}
			}
		};
		
		editarVideo = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Video video = (Video)vistaVideos.getVideosListView().getSelectedValue();
				vistaVideos.setVisible(false);
				iniciarVistaFormularioVideo(video);
			}
		};
		
		eliminarVideo = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Video video = (Video)vistaVideos.getVideosListView().getSelectedValue();
				
				int status = cm.deleteVideo(video);
				
				if(status == 200) {
					//Deshabilitar los botones editar y eliminar hasta que se vuelta a seleccionar cliente
					vistaVideos.getEditVideoBtn().setEnabled(false);
					vistaVideos.getDeleteVideoBtn().setEnabled(false);
					formularioVideo.getEliminarBtn().setVisible(false);
					formularioVideo.getGuardarCambiosBtn().setVisible(false);
					
					limpiarFormularioVideo();;
					formularioVideo.setVisible(false);
					iniciarVistaVideos();
				} else {
					JOptionPane.showConfirmDialog(null, "Los datos son incorrectos");
				}
			}
		};
		
		updateVideo = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(formularioVideo.getIdVideo());
				String titulo = formularioVideo.getTituloVideo();
				String director = formularioVideo.getDirectorVideo();
				int idCliente = Integer.parseInt(formularioVideo.getIdClienteVideo());
				
				video.clearInfo();
				video.setId(id);
				video.setTitulo(titulo);
				video.setDirector(director);
				video.setCli_id(idCliente);
				
				
				int status = cm.updateVideo(video);
				
				if(status == 200) {
					//Deshabilitar los botones editar y eliminar hasta que se vuelta a seleccionar cliente
					vistaVideos.getEditVideoBtn().setEnabled(false); 
					vistaVideos.getDeleteVideoBtn().setEnabled(false);
					formularioVideo.setVisible(false);
					formularioVideo.getEliminarBtn().setVisible(false);
					formularioVideo.getGuardarCambiosBtn().setVisible(false);
					
					limpiarFormularioVideo();
					iniciarVistaVideos();
				} else {
					JOptionPane.showConfirmDialog(null, "Los datos son incorrectos");
				}
				
			}
		};
		
		//Listener para volver a la selección de vista al pultas atras
		atras = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					vistaClientes.setVisible(false);
					vistaVideos.setVisible(false);
					iniciarVistaSeleccionarVista();
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
	
	//Listar los clientes devueltos de la base de datos en la vistaClientes
	private void showVideos() {
		ArrayList<Video> videos = cm.getAllVideos();
		
		vistaVideos.limppiarModel();
		
		for (Video video : videos) {
			vistaVideos.addVideoLista(video);
		}
	}
	
	private void limpiarFormularioCliente() {
		//Vaciar los campos del formulario
		vistaFormulario.setIdClienteTF(0);
		vistaFormulario.setNombreClienteTF("");
		vistaFormulario.setApellidoClienteTF("");
		vistaFormulario.setDireccionClienteTF("");
		vistaFormulario.setDniClienteTF("");
		vistaFormulario.setFechaClienteTF("");
	}
	
	private void limpiarFormularioVideo() {
		//Vaciar los campos del formulario
		formularioVideo.setIdVideo(0);
		formularioVideo.setTituloVideo("");
		formularioVideo.setDirectorVideo("");
		formularioVideo.setIdClienteVideo(0);
	}
}
