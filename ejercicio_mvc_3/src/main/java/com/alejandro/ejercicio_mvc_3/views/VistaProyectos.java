package com.alejandro.ejercicio_mvc_3.views;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import com.alejandro.ejercicio_mvc_3.models.Cientifico;
import com.alejandro.ejercicio_mvc_3.models.Proyecto;

public class VistaProyectos extends JFrame{

	private final int WINDOW_WIDTH = 500;
	private final int WINDOW_HEIGHT = 400;
	
	private JPanel contentPane;
	private JList<Proyecto> listViewProyectos;
    private DefaultListModel<Proyecto> listModel;
    private JButton btnNuevoProyecto;
    private JButton btnEditarProyecto;
    private JButton btnEliminarProyecto;
    private JButton btnAtras;
	
	public VistaProyectos() {
		this.setSize(517, 400);
		this.setResizable(false);
		
		contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        listModel = new DefaultListModel();
        
        listViewProyectos = new JList(listModel);
        listViewProyectos.setSize(320, 330);
        listViewProyectos.setLocation(20, 20);
        listViewProyectos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        contentPane.add(listViewProyectos);
        
        btnNuevoProyecto = new JButton("Añadir Proyecto");
        btnNuevoProyecto.setSize(141, 30);
        btnNuevoProyecto.setLocation(350, 20);
        contentPane.add(btnNuevoProyecto);
        
        btnEditarProyecto = new JButton("Editar Proyecto");
        btnEditarProyecto.setSize(141, 30);
        btnEditarProyecto.setLocation(350, 53);
        btnEditarProyecto.setEnabled(false);
        contentPane.add(btnEditarProyecto);
        
        btnEliminarProyecto = new JButton("Eliminar Proyecto");
        btnEliminarProyecto.setSize(141, 30);
        btnEliminarProyecto.setLocation(350, 86);
        btnEliminarProyecto.setEnabled(false);
        contentPane.add(btnEliminarProyecto);
        
        btnAtras = new JButton("Atras");
        btnAtras.setSize(141, 30);
        btnAtras.setLocation(350, 320);
        contentPane.add(btnAtras);
	}
	
	public JButton getBtnNuevoProyecto() {
		return this.btnNuevoProyecto;
	}
	
	public JList getListViewProyecto() {
		return this.listViewProyectos;
	}
	
	public JButton getBtnEditarProyecto() {
		return btnEditarProyecto;
	}
	
	public JButton getBtnEliminarProyecto() {
		return btnEliminarProyecto;
	}
	
	public JButton getBtnAtras() {
		return btnAtras;
	}
	
	//Añadir un nuevo proyecto a la lista que se muetra en la vista
	public void addProyecto(Proyecto proyecto) {
		listModel.addElement(proyecto);
	}
	
	public void limppiarModel() {
		listModel.clear();
	}
}
