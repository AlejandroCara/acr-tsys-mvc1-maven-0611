package com.alejandro.ejercicio_mvc_2.views;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import com.alejandro.ejercicio_mvc_2.models.Video;

public class VistaVideos extends JFrame{
	private final int WINDOW_WIDTH = 500;
	private final int WINDOW_HEIGHT = 400;
	
	private JPanel contentPane;
	private JList<Video> videosListView;
    private DefaultListModel<Video> listModel;
    private JButton newVideoBtn;
    private JButton editVideoBtn;
    private JButton deleteVideoBtn;
	
	public VistaVideos() {
		this.setSize(716, 400);
		this.setResizable(false);
		
		contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        listModel = new DefaultListModel();
        
        videosListView = new JList(listModel);
        videosListView.setSize(536, 330);
        videosListView.setLocation(20, 20);
        videosListView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        contentPane.add(videosListView);
        
        newVideoBtn = new JButton("Añadir Video");
        newVideoBtn.setSize(124, 30);
        newVideoBtn.setLocation(566, 20);
        contentPane.add(newVideoBtn);
        
        editVideoBtn = new JButton("Editar Video");
        editVideoBtn.setSize(124, 30);
        editVideoBtn.setLocation(566, 54);
        editVideoBtn.setEnabled(false);
        contentPane.add(editVideoBtn);
        
        deleteVideoBtn = new JButton("Eliminar Video");
        deleteVideoBtn.setSize(124, 30);
        deleteVideoBtn.setLocation(566, 87);
        deleteVideoBtn.setEnabled(false);
        contentPane.add(deleteVideoBtn);
	}
	
	public JButton getNewVideoBtn() {
		return this.newVideoBtn;
	}
	
	public JList getVideosListView() {
		return this.videosListView;
	}
	
	public JButton getEditVideoBtn() {
		return editVideoBtn;
	}
	
	public JButton getDeleteVideoBtn() {
		return deleteVideoBtn;
	}
	
	//Añadir un nuevo cliente a la lista que se muetra en la vista
	public void addVideoLista(Video video) {
		listModel.addElement(video);
	}
	
	public void limppiarModel() {
		listModel.clear();
	}
}
