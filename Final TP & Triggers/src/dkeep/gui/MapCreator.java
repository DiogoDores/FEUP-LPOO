package dkeep.gui;

import dkeep.logic.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Graphics;

public class MapCreator extends JPanel {
	private char activeChar;
	private boolean hasSaved;
	private JFrame frame = new JFrame();
	private String title;
	public int width, height;
	private JButton btnOgre, btnKey, btnHero, btnWall, btnFloor, btnSave;
	private JButton btnDoor;
	private EditorMap map;
	private GuardMap testMap;
	private Game game;
	
	public MapCreator(String string, int i, int j) {
		map = new EditorMap(5,5);
		width = 5; height = 5;
		
		activeChar = 'H';
		title = string;
		width = i; 
		height = j;
		
		frame = new JFrame(title);     
		frame.setContentPane(this);
		frame.setSize(1000, 700);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		
		game = new Game("Editor", 200, 200);
		game.levelPositionArray = 0;
		game.levels[0] = map;
		game.init();
		game.display();

		game.panel.setBounds(181, 33, 344, 259);
		game.panel.setVisible(true);

		frame.getContentPane().add(game.panel);
		frame.add(game.panel);
		
		hasSaved = false;
		//setLayout(null);
		map.drawMap(game.gameLogic);
		init();
		game.gameLogic.currentMap.drawMap(game.gameLogic);

	}

	public void init() {

		btnOgre = new JButton("Ogre");
		btnOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeChar = 'O';
			}
		});
		btnOgre.setBounds(10, 20, 100, 30);
		frame.getContentPane().add(btnOgre, BorderLayout.WEST);

		btnKey = new JButton("Key");
		btnKey.setBounds(10, 70, 100, 30);
		btnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				activeChar = 'k';
			}
		});
		frame.getContentPane().add(btnKey, BorderLayout.WEST);

		btnHero = new JButton("Hero");
		btnHero.setBounds(10, 120, 100, 30);
		btnHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeChar = 'H';

			}
		});
		frame.getContentPane().add(btnHero, BorderLayout.WEST);
		btnWall = new JButton("Wall");
		btnWall.setBounds(10, 170, 100, 30);
		btnWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeChar = 'X';
			}
		});
		frame.getContentPane().add(btnWall, BorderLayout.WEST);

		btnFloor = new JButton("Floor");
		btnFloor.setBounds(10, 220, 100, 30);
		btnFloor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeChar = ' ';
			}
		});
		frame.getContentPane().add(btnFloor, BorderLayout.WEST);


		btnDoor = new JButton("Door");
		btnDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeChar = 'I';
			}
		});
		btnDoor.setBounds(10, 270, 100, 30);
		add(btnDoor);

		btnSave = new JButton("Save Changes");
		btnSave.setBounds(195, 360, 181, 23);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hasSaved = true;
			}
		});
		add(btnSave);
		game.levelPositionArray = 1;
	
		this.repaint();
	
	}

	

	@Override
	public void paintComponent(Graphics g) {
		game.gameLogic.currentMap = map;
		
		super.paintComponent(g); 
		game.drawStructures(g);
		g.drawImage(game.hero, game.gameLogic.hero.getY() * 49 , game.gameLogic.hero.getX() * 49 - 49, 64, 90, null);

		if(game.gameLogic.currentMap.getName() == "GuardMap"){
			g.drawImage(game.guard,  game.gameLogic.guard.getY() * 49, game.gameLogic.guard.getX() * 49 - 49, 64, 90, null);
		}
		else {
			for(int i = 0; i < game.gameLogic.ogres.size(); i++){
				g.drawImage(Assets.club, game.gameLogic.ogres.get(i).getClubY()* 50, game.gameLogic.ogres.get(i).getClubX()* 50, 50, 50, null);
				g.drawImage(game.ogresSprite[i], game.gameLogic.ogres.get(i).getY()* 49, game.gameLogic.ogres.get(i).getX()* 49 - 40, 54, 80, null);
			}
		}
		

	}
	

}
