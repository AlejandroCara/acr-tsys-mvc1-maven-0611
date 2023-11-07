package com.alejandro.ejercicio_mvc_2.views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VistaSelectVista extends JFrame{

	private final int WINDOW_WIDTH = 302;
	private final int WINDOW_HEIGHT = 142;
	
	private JPanel contentPane;
	private JLabel selectVistaLabel;
	private JButton vistaClientesBtn;
	private JButton vistaVideosBtn;
	
	public VistaSelectVista() {
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setResizable(false);
		
		contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        selectVistaLabel = new JLabel("Selecciona que vista abrir: ");
        selectVistaLabel.setSize(156, 25);
        selectVistaLabel.setLocation(74, 11);
        contentPane.add(selectVistaLabel);
        
        vistaClientesBtn = new JButton("Vista Clientes");
        vistaClientesBtn.setSize(113, 30);
        vistaClientesBtn.setLocation(24, 62);
        contentPane.add(vistaClientesBtn);
        
        vistaVideosBtn = new JButton("Vista Videos");
        vistaVideosBtn.setSize(118, 30);
        vistaVideosBtn.setLocation(147, 62);
        contentPane.add(vistaVideosBtn);
	}
	
	public JButton getVistaClienteBtn() {
		return vistaClientesBtn;
	}
	
	public JButton getVistaVideoButton() {
		return vistaVideosBtn;
	}
	
}
