package com.alejandro.ejercicio_mvc_3.views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VistaSelectVista extends JFrame{

	private final int WINDOW_WIDTH = 368;
	private final int WINDOW_HEIGHT = 142;
	
	private JPanel contentPane;
	private JLabel selectVistaLabel;
	private JButton vistaCientificosBtn;
	private JButton vistaProyectosBtn;
	
	public VistaSelectVista() {
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setResizable(false);
		
		contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        selectVistaLabel = new JLabel("Selecciona que vista abrir: ");
        selectVistaLabel.setSize(156, 25);
        selectVistaLabel.setLocation(98, 11);
        contentPane.add(selectVistaLabel);
        
        vistaCientificosBtn = new JButton("Vista Cientificos");
        vistaCientificosBtn.setSize(136, 30);
        vistaCientificosBtn.setLocation(24, 62);
        contentPane.add(vistaCientificosBtn);
        
        vistaProyectosBtn = new JButton("Vista Proyectos");
        vistaProyectosBtn.setSize(136, 30);
        vistaProyectosBtn.setLocation(170, 62);
        contentPane.add(vistaProyectosBtn);
	}
	
	public JButton getVistaCientificosBtn() {
		return vistaCientificosBtn;
	}
	
	public JButton getVistaProyectosButton() {
		return vistaProyectosBtn;
	}
	
}
