package dkeep.gui;
import dkeep.logic.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;

public class MapCreator extends JPanel{
	private static EditorMap map;
	private GameLogic gameLogic;
	private String title;
	private JButton ogre, hero, wall, lever, door; 
	private int width, height;
	private JFrame frame;
	private JPanel panel;
	public MapCreator(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		ogre = new JButton("OGRE");
		hero = new JButton("HERO");
		wall = new JButton("WALL");
		lever = new JButton("LEVER");
		door = new JButton("DOOR");
		width = 2;
		height = 2;
		gameLogic = new GameLogic();
		gameLogic.changeCurrentMap(map);
		Assets.init(gameLogic.currentMap);
		display();
		repaint();
		gameLogic.changeCurrentMap(map);
		refreshMap(2,2);
		if (map == null)
			//System.out.println("MERDA");
	}
	
	public void refreshMap(int x, int y){
		map = new EditorMap(x, y);	
		gameLogic.changeCurrentMap(map);
		
	}
	
	public void display() {
		if (height != 0 && width != 0) {
			
		}
	}
	
	
	public void init(){
		JFrame frame = new JFrame();
		frame.setContentPane(this);
		frame.setSize(1200,700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		


		panel = new JPanel();
		panel.setBounds(0, 0, 700, 700);
		frame.getContentPane().add(panel);
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		ogre.setBounds(10, 10, 50, 50);
		
		hero.setBounds(110, 10, 50, 50);

		wall.setBounds(210, 10, 50, 50);

		lever.setBounds(310, 10, 50, 50);

		door.setBounds(410, 10, 50, 50);
		
		add(ogre);
		add(hero);
		add(lever);
		add(wall);
		add(door);

		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
	}
} 
