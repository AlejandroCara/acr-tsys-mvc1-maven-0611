package com.alejandro.ejercicio_mvc_3.views;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import com.alejandro.ejercicio_mvc_3.models.Cientifico;

public class VistaCientificos extends JFrame{

	private final int WINDOW_WIDTH = 500;
	private final int WINDOW_HEIGHT = 400;
	
	private JPanel contentPane;
	private JList<Cientifico> listViewCientificos;
    private DefaultListModel<Cientifico> listModel;
    private JButton btnNuevoCientifico;
    private JButton btnEditarCientifico;
    private JButton btnEliminarCientifico;
    private JButton btnAtras;
	
	public VistaCientificos() {
		this.setSize(517, 400);
		this.setResizable(false);
		
		contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        listModel = new DefaultListModel();
        
        listViewCientificos = new JList(listModel);
        listViewCientificos.setSize(320, 330);
        listViewCientificos.setLocation(20, 20);
        listViewCientificos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        contentPane.add(listViewCientificos);
        
        btnNuevoCientifico = new JButton("Añadir Cientifico");
        btnNuevoCientifico.setSize(141, 30);
        btnNuevoCientifico.setLocation(350, 20);
        contentPane.add(btnNuevoCientifico);
        
        btnEditarCientifico = new JButton("Editar Cientifico");
        btnEditarCientifico.setSize(141, 30);
        btnEditarCientifico.setLocation(350, 53);
        btnEditarCientifico.setEnabled(false);
        contentPane.add(btnEditarCientifico);
        
        btnEliminarCientifico = new JButton("Eliminar Cientifico");
        btnEliminarCientifico.setSize(141, 30);
        btnEliminarCientifico.setLocation(350, 86);
        btnEliminarCientifico.setEnabled(false);
        contentPane.add(btnEliminarCientifico);
        
        btnAtras = new JButton("Atras");
        btnAtras.setSize(141, 30);
        btnAtras.setLocation(350, 320);
        contentPane.add(btnAtras);
	}
	
	public JButton getBtnNuevoCientifico() {
		return this.btnNuevoCientifico;
	}
	
	public JList getListViewCientificos() {
		return this.listViewCientificos;
	}
	
	public JButton getBtnEditarCientifico() {
		return btnEditarCientifico;
	}
	
	public JButton getBtnEliminarCientifico() {
		return btnEliminarCientifico;
	}
	
	public JButton getBtnAtras() {
		return btnAtras;
	}
	
	//Añadir un nuevo cientifico a la lista que se muetra en la vista
	public void addCientifico(Cientifico cientifico) {
		listModel.addElement(cientifico);
	}
	
	public void limppiarModel() {
		listModel.clear();
	}
}
