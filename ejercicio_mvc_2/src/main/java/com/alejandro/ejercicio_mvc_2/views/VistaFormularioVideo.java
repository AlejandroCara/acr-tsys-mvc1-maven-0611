package com.alejandro.ejercicio_mvc_2.views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VistaFormularioVideo extends JFrame{

	private final int WINDOW_WIDTH = 260;
	private final int WINDOW_HEIGHT = 334;
	
	private JLabel tituloVideoLabel;
	private JLabel directorVideoLabel;
	private JLabel cliIdVideoLabel;
	private JTextField idVideoTF;
	private JTextField tituloVideoTF;
	private JTextField directorVideoTF;
	private JTextField cliIdVideoTF;
	private JButton guardarBtn;
	private JButton guardarCambiosBtn;
	private JButton eliminarBtn;
	private JPanel contentPane;
	
	public VistaFormularioVideo() {
		this.setSize(449, 334);
		this.setResizable(false);
		
		contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        idVideoTF = new JTextField();
        idVideoTF.setSize(390, 22);
        idVideoTF.setLocation(20, 41);
        idVideoTF.setVisible(false);
        contentPane.add(idVideoTF);
        
        tituloVideoLabel = new JLabel("Titulo: ");
        tituloVideoLabel.setSize(150, 19);
        tituloVideoLabel.setLocation(20, 22);
        contentPane.add(tituloVideoLabel);
        
        tituloVideoTF = new JTextField();
        tituloVideoTF.setSize(390, 22);
        tituloVideoTF.setLocation(20, 41);
        contentPane.add(tituloVideoTF);
        
        directorVideoLabel = new JLabel("Director: ");
        directorVideoLabel.setSize(150, 19);
        directorVideoLabel.setLocation(20, 74);
        contentPane.add(directorVideoLabel);
        
        directorVideoTF = new JTextField();
        directorVideoTF.setSize(390, 22);
        directorVideoTF.setLocation(20, 92);
        contentPane.add(directorVideoTF);
        
        cliIdVideoLabel = new JLabel("Id cliente: ");
        cliIdVideoLabel.setSize(150, 19);
        cliIdVideoLabel.setLocation(20, 125);
        contentPane.add(cliIdVideoLabel);
        
        cliIdVideoTF = new JTextField();
        cliIdVideoTF.setSize(390, 22);
        cliIdVideoTF.setLocation(20, 143);
        contentPane.add(cliIdVideoTF);
        
        guardarBtn = new JButton("Guardar");
        guardarBtn.setSize(176, 30);
        guardarBtn.setLocation(136, 241);
        contentPane.add(guardarBtn);
        
        guardarCambiosBtn = new JButton("Guardar");
        guardarCambiosBtn.setSize(176, 30);
        guardarCambiosBtn.setLocation(136, 241);
        contentPane.add(guardarCambiosBtn);
        guardarCambiosBtn.setVisible(false);
        
        eliminarBtn = new JButton("Eliminar");
        eliminarBtn.setSize(176, 30);
        eliminarBtn.setLocation(136, 200);
        eliminarBtn.setVisible(false);
        contentPane.add(eliminarBtn);
	}
	
	
	public void setIdVideo(int idVideo) {
		this.idVideoTF.setText(String.valueOf(idVideo));
	}
	
	public void setTituloVideo(String tituloVideo) {
		this.tituloVideoTF.setText(tituloVideo);
	}



	public void setDirectorVideo(String directorvideo) {
		this.directorVideoTF.setText(directorvideo);
	}



	public void setIdClienteVideo(int idClientevideo) {
		this.cliIdVideoTF.setText(String.valueOf(idClientevideo));
	}


	public String getIdVideo() {
		return idVideoTF.getText();
	}
	
	public String getTituloVideo() {
		return tituloVideoTF.getText();
	}



	public String getDirectorVideo() {
		return directorVideoTF.getText();
	}



	public String getIdClienteVideo() {
		return cliIdVideoTF.getText();
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
