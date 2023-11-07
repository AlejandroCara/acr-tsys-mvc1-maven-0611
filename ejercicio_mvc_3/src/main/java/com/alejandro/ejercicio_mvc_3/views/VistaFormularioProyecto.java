package com.alejandro.ejercicio_mvc_3.views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class VistaFormularioProyecto extends JFrame{
	
	private final int WINDOW_WIDTH = 310;
	private final int WINDOW_HEIGHT = 324;

	private JLabel labelIdProyecto;
	private JLabel labelNombreProyecto;
	private JLabel labelHorasProyecto;
	
	private JTextField textFieldIdProyecto;
	private JTextField textFieldNombreProyecto;
	
	private JSpinner spinnerHorasProyecto;
	
	private JButton btnGuardar;
	private JButton btnGuardarCambios;
	private JButton btnEliminar;
	
	private JPanel contentPane;
	
	public VistaFormularioProyecto() {
		this.setSize(310, 339);
		this.setResizable(false);
		
		contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        labelNombreProyecto = new JLabel("ID (4 caracteres):");
        labelNombreProyecto.setSize(150, 19);
        labelNombreProyecto.setLocation(20, 22);
        contentPane.add(labelNombreProyecto);
        
        textFieldIdProyecto = new JTextField();
        textFieldIdProyecto.setSize(252, 22);
        textFieldIdProyecto.setLocation(20, 41);
        contentPane.add(textFieldIdProyecto);
        
        labelNombreProyecto = new JLabel("Nombre:");
        labelNombreProyecto.setSize(150, 19);
        labelNombreProyecto.setLocation(20, 85);
        contentPane.add(labelNombreProyecto);
        
        textFieldNombreProyecto = new JTextField();
        textFieldNombreProyecto.setSize(252, 22);
        textFieldNombreProyecto.setLocation(20, 105);
        contentPane.add(textFieldNombreProyecto);
        
        labelHorasProyecto = new JLabel("Horas: ");
        labelHorasProyecto.setSize(150, 19);
        labelHorasProyecto.setLocation(20, 150);
        contentPane.add(labelHorasProyecto); 
          
        spinnerHorasProyecto = new JSpinner();
        spinnerHorasProyecto.setSize(252, 22);
        spinnerHorasProyecto.setLocation(20, 168);
        contentPane.add(spinnerHorasProyecto);
        
        btnGuardar = new JButton("Guardar");
        btnGuardar.setSize(176, 30);
        btnGuardar.setLocation(57, 258);
        contentPane.add(btnGuardar);
        
        btnGuardarCambios = new JButton("Guardar");
        btnGuardarCambios.setSize(176, 30);
        btnGuardarCambios.setLocation(57, 258);
        contentPane.add(btnGuardarCambios);
        btnGuardarCambios.setVisible(false);
        
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setSize(176, 30);
        btnEliminar.setLocation(57, 217);
        btnEliminar.setVisible(false);
        contentPane.add(btnEliminar);
	}

	public String getIdProyecto() {
		return textFieldIdProyecto.getText();
	}

	public void setIdProyecto(String idProyecto) {
		this.textFieldIdProyecto.setText(idProyecto);;
	}

	public String getNombreProyecto() {
		return textFieldNombreProyecto.getText();
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.textFieldNombreProyecto.setText(nombreProyecto);
	}

	public int getHorasProyecto() {
		return (int) spinnerHorasProyecto.getValue();
	}

	public void setHorasProyecto(int spinnerHorasProyecto) {
		this.spinnerHorasProyecto.setValue(spinnerHorasProyecto);;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JButton getBtnGuardarCambios() {
		return btnGuardarCambios;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}
	
	
}
