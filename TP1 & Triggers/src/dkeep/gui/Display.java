package dkeep.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display {

	private  JFrame frame;
	private JPanel panel;
	
	private String title;
	private int width, height;
	
	public Display (String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		
		createDisplay();
	}
	
	private void createDisplay(){
		
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(width, height));
		panel.setMaximumSize(new Dimension(width, height));
		panel.setMinimumSize(new Dimension(width, height));
		panel.setFocusable(false);
		
		frame.add(panel);
		frame.pack();
	}
	
	public JPanel getpanel(){
		return panel;
	}
	
	public JFrame getFrame(){
		return frame;
	}
}
