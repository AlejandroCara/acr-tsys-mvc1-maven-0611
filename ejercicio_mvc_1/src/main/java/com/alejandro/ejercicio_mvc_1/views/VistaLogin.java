package com.alejandro.ejercicio_mvc_1.views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VistaLogin extends JFrame{
	
	private final int WINDOW_WIDTH = 280;
	private final int WINDOW_HEIGHT = 250;
	
	private JPanel contentPane;
	private JLabel usuarioLabel;
	private JLabel passwdLabel;
	private JTextField usuarioTF; 
	private JPasswordField passwdTF;
	private JButton loginBtn;
	
	public VistaLogin() {
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setResizable(false);
		
		contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        usuarioLabel = new JLabel("Usuario:");
        usuarioLabel.setSize(81, 22);
        usuarioLabel.setLocation(20, 30);
        contentPane.add(usuarioLabel);
        
        usuarioTF = new JTextField("root");
        usuarioTF.setSize(233, 22);
        usuarioTF.setLocation(20, 54);
        contentPane.add(usuarioTF);
        
        passwdLabel = new JLabel("Contraseña:");
        passwdLabel.setSize(81, 22);
        passwdLabel.setLocation(20, 92);
        contentPane.add(passwdLabel);
        
        passwdTF = new JPasswordField("root");
        passwdTF.setSize(233, 22);
        passwdTF.setLocation(20, 114);
        contentPane.add(passwdTF); 
        
        loginBtn = new JButton("Login");
        loginBtn.setSize(233, 22);
        loginBtn.setLocation(20, 159);
        contentPane.add(loginBtn); 
	}

	public JTextField getUsuarioTF() {
		return usuarioTF;
	}

	public JPasswordField getPasswdTF() {
		return passwdTF;
	}

	public JButton getLoginBtn() {
		return loginBtn;
	}
	
}
