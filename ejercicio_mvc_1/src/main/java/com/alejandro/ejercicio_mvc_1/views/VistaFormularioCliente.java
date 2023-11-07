package com.alejandro.ejercicio_mvc_1.views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VistaFormularioCliente extends JFrame{
	
	private final int WINDOW_WIDTH = 260;
	private final int WINDOW_HEIGHT = 400;
	
	private JLabel nombreClienteLabel;
	private JLabel apellidoClienteLabel;
	private JLabel direccionClienteLabel;
	private JLabel dniClienteLabel;
	private JLabel fechaClienteLabel;
	private JTextField idClienteTF;
	private JTextField nombreClienteTF;
	private JTextField apellidoClienteTF;
	private JTextField direccionClienteTF;
	private JTextField dniClienteTF;
	private JTextField fechaClienteTF;
	private JButton guardarBtn;
	private JButton guardarCambiosBtn;
	private JButton eliminarBtn;
	private JPanel contentPane;
	
	public VistaFormularioCliente() {
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setResizable(false);
		
		contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        idClienteTF = new JTextField();
        idClienteTF.setSize(190, 22);
        idClienteTF.setLocation(20, 41);
        idClienteTF.setVisible(false);
        contentPane.add(idClienteTF);
        
        nombreClienteLabel = new JLabel("Nombre: ");
        nombreClienteLabel.setSize(150, 19);
        nombreClienteLabel.setLocation(20, 22);
        contentPane.add(nombreClienteLabel);
        
        nombreClienteTF = new JTextField();
        nombreClienteTF.setSize(190, 22);
        nombreClienteTF.setLocation(20, 41);
        contentPane.add(nombreClienteTF);
        
        apellidoClienteLabel = new JLabel("Apellido: ");
        apellidoClienteLabel.setSize(150, 19);
        apellidoClienteLabel.setLocation(20, 74);
        contentPane.add(apellidoClienteLabel);
        
        apellidoClienteTF = new JTextField();
        apellidoClienteTF.setSize(190, 22);
        apellidoClienteTF.setLocation(20, 92);
        contentPane.add(apellidoClienteTF);
        
        direccionClienteLabel = new JLabel("Dirección: ");
        direccionClienteLabel.setSize(150, 19);
        direccionClienteLabel.setLocation(20, 125);
        contentPane.add(direccionClienteLabel);
        
        direccionClienteTF = new JTextField();
        direccionClienteTF.setSize(190, 22);
        direccionClienteTF.setLocation(20, 143);
        contentPane.add(direccionClienteTF);
        
        dniClienteLabel = new JLabel("DNI: (sin letra)");
        dniClienteLabel.setSize(150, 19);
        dniClienteLabel.setLocation(20, 176);
        contentPane.add(dniClienteLabel);
        
        dniClienteTF = new JTextField();
        dniClienteTF.setSize(190, 22);
        dniClienteTF.setLocation(20, 195);
        contentPane.add(dniClienteTF);
        
        fechaClienteLabel = new JLabel("Fecha de Nacimiento: (año-mes-dia)");
        fechaClienteLabel.setSize(225, 19);
        fechaClienteLabel.setLocation(20, 228);
        contentPane.add(fechaClienteLabel);
        
        fechaClienteTF = new JTextField();
        fechaClienteTF.setSize(190, 22);
        fechaClienteTF.setLocation(20, 246);
        contentPane.add(fechaClienteTF);
        
        guardarBtn = new JButton("Guardar");
        guardarBtn.setSize(176, 30);
        guardarBtn.setLocation(30, 315);
        contentPane.add(guardarBtn);
        
        guardarCambiosBtn = new JButton("Guardar");
        guardarCambiosBtn.setSize(176, 30);
        guardarCambiosBtn.setLocation(30, 315);
        contentPane.add(guardarCambiosBtn);
        guardarCambiosBtn.setVisible(false);
        
        eliminarBtn = new JButton("Eliminar");
        eliminarBtn.setSize(176, 30);
        eliminarBtn.setLocation(30, 279);
        eliminarBtn.setVisible(false);
        contentPane.add(eliminarBtn);
	}
	
	
	public void setIdClienteTF(int idCliente) {
		this.idClienteTF.setText(String.valueOf(idCliente));
	}
	
	public void setNombreClienteTF(String nombreCliente) {
		this.nombreClienteTF.setText(nombreCliente);
	}



	public void setApellidoClienteTF(String apellidoCliente) {
		this.apellidoClienteTF.setText(apellidoCliente);
	}



	public void setDireccionClienteTF(String direccionCliente) {
		this.direccionClienteTF.setText(direccionCliente);
	}



	public void setDniClienteTF(String dniCliente) {
		this.dniClienteTF.setText(dniCliente);
	}



	public void setFechaClienteTF(String fechaCliente) {
		this.fechaClienteTF.setText(fechaCliente);
	}


	public JTextField getIdClienteTF() {
		return idClienteTF;
	}
	
	public JTextField getNombreClienteTF() {
		return nombreClienteTF;
	}



	public JTextField getApellidoClienteTF() {
		return apellidoClienteTF;
	}



	public JTextField getDireccionClienteTF() {
		return direccionClienteTF;
	}



	public JTextField getDniClienteTF() {
		return dniClienteTF;
	}



	public JTextField getFechaClienteTF() {
		return fechaClienteTF;
	}



	public JButton getEliminarBtn() {
		return eliminarBtn;
	}



	public JButton getGuardarBtn() {
		return guardarBtn;
	}
	
	public JButton getGuardarCambiosBtn() {
		return guardarCambiosBtn;
	}
	
}
