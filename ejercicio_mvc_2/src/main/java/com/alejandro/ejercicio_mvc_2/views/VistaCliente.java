package com.alejandro.ejercicio_mvc_2.views;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import com.alejandro.ejercicio_mvc_2.models.Cliente;

public class VistaCliente extends JFrame{

	private final int WINDOW_WIDTH = 500;
	private final int WINDOW_HEIGHT = 400;
	
	private JPanel contentPane;
	private JList<Cliente> clientesListView;
    private DefaultListModel<Cliente> listModel;
    private JButton newClienteBtn;
    private JButton editClientBtn;
    private JButton deleteClientBtn;
	
	public VistaCliente() {
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setResizable(false);
		
		contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        listModel = new DefaultListModel();
        
        clientesListView = new JList(listModel);
        clientesListView.setSize(320, 330);
        clientesListView.setLocation(20, 20);
        clientesListView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        contentPane.add(clientesListView);
        
        newClienteBtn = new JButton("Añadir Cliente");
        newClienteBtn.setSize(124, 30);
        newClienteBtn.setLocation(350, 20);
        contentPane.add(newClienteBtn);
        
        editClientBtn = new JButton("Editar Cliente");
        editClientBtn.setSize(124, 30);
        editClientBtn.setLocation(350, 53);
        editClientBtn.setEnabled(false);
        contentPane.add(editClientBtn);
        
        deleteClientBtn = new JButton("Eliminar Cliente");
        deleteClientBtn.setSize(124, 30);
        deleteClientBtn.setLocation(350, 86);
        deleteClientBtn.setEnabled(false);
        contentPane.add(deleteClientBtn);
	}
	
	public JButton getNewClienteBtn() {
		return this.newClienteBtn;
	}
	
	public JList getClientesListView() {
		return this.clientesListView;
	}
	
	public JButton getEditClienteBtn() {
		return editClientBtn;
	}
	
	public JButton getDeleteClientBtn() {
		return deleteClientBtn;
	}
	
	//Añadir un nuevo cliente a la lista que se muetra en la vista
	public void addClienteLista(Cliente cliente) {
		listModel.addElement(cliente);
	}
	
	public void limppiarModel() {
		listModel.clear();
	}
}
