package com.alejandro.ejercicio_mvc_3.views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VistaFormularioCientifico extends JFrame{
	
	private final int WINDOW_WIDTH = 310;
	private final int WINDOW_HEIGHT = 324;
	
	private JLabel labelDniCientifico;
	private JLabel labelNomApelsCientifico;
	private JLabel labelProyectosCientifico;
	
	private JTextField textFieldDniCientifico;
	private JTextField textFieldNomApelsCientifico;
	private JTextField textFieldProyectosCientifico;
	
	private JButton btnGuardar;
	private JButton btnGuardarCambios;
	private JButton btnEliminar;
	
	private JPanel contentPane;
	
	public VistaFormularioCientifico() {
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setResizable(false);
		
		contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        labelDniCientifico = new JLabel("DNI (sin letra):");
        labelDniCientifico.setSize(150, 19);
        labelDniCientifico.setLocation(20, 22);
        contentPane.add(labelDniCientifico);
        
        textFieldDniCientifico = new JTextField();
        textFieldDniCientifico.setSize(252, 22);
        textFieldDniCientifico.setLocation(20, 41);
        contentPane.add(textFieldDniCientifico);
        
        labelNomApelsCientifico = new JLabel("Nomre y apellidos: ");
        labelNomApelsCientifico.setSize(150, 19);
        labelNomApelsCientifico.setLocation(20, 74);
        contentPane.add(labelNomApelsCientifico);
        
        textFieldNomApelsCientifico = new JTextField();
        textFieldNomApelsCientifico.setSize(252, 22);
        textFieldNomApelsCientifico.setLocation(20, 92);
        contentPane.add(textFieldNomApelsCientifico);
        
        labelProyectosCientifico = new JLabel("Id proyectos (separados por ,): ");
        labelProyectosCientifico.setSize(252, 19);
        labelProyectosCientifico.setLocation(20, 125);
        contentPane.add(labelProyectosCientifico);
        
        textFieldProyectosCientifico = new JTextField();
        textFieldProyectosCientifico.setSize(252, 22);
        textFieldProyectosCientifico.setLocation(20, 143);
        contentPane.add(textFieldProyectosCientifico);
        
        btnGuardar = new JButton("Guardar");
        btnGuardar.setSize(176, 30);
        btnGuardar.setLocation(57, 235);
        contentPane.add(btnGuardar);
        
        btnGuardarCambios = new JButton("Guardar");
        btnGuardarCambios.setSize(176, 30);
        btnGuardarCambios.setLocation(57, 235);
        contentPane.add(btnGuardarCambios);
        btnGuardarCambios.setVisible(false);
        
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setSize(176, 30);
        btnEliminar.setLocation(57, 194);
        btnEliminar.setVisible(false);
        contentPane.add(btnEliminar);
	}

	public String getDniCientifico() {
		return textFieldDniCientifico.getText();
	}

	public void setDniCientifico(String dniCientifico) {
		textFieldDniCientifico.setText(dniCientifico);
	}

	public String getNomApelsCientifico() {
		return textFieldNomApelsCientifico.getText();
	}

	public void setNomApelsCientifico(String nomApelsCientifico) {
		textFieldNomApelsCientifico.setText(nomApelsCientifico);
	}

	public String getProyectosCientifico() {
		return textFieldProyectosCientifico.getText();
	}

	public void setProyectosCientifico(String proyectosCientifico) {
		textFieldProyectosCientifico.setText(proyectosCientifico);;
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
