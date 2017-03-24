package dkeep.gui;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Graphics;

public class MapCreator extends JFrame {
	private char activeChar;
	private boolean hasSaved;
	private JFrame frame = new JFrame();
	private JFrame frame_1;
	private String title;
	private int width, height;
	private JButton btnOgre, btnKey, btnHero, btnWall, btnFloor, btnSave;
	private JButton btnDoor;
	private EditorMap map;
	private Game game;
	private JPanel panel;

	public MapCreator(String string, int i, int j) {
		map = new EditorMap(2,2);
		activeChar = 'H';
		title = string;
		width = i; 
		height = j;
		frame_1 = new JFrame(title);     

		frame_1.setSize(582, 423);
		frame_1.setVisible(true);
		frame_1.setResizable(false);
		frame_1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame_1.setLocationRelativeTo(null);
		hasSaved = false;
		getContentPane().setLayout(null);
		game = new Game("Editor", 200, 200);
		init();
		game.gameLogic.changeCurrentMap(map);
	}

	public void init() {
		btnOgre = new JButton("Ogre");
		btnOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeChar = 'O';
			}
		});
		frame_1.getContentPane().setLayout(null);
		btnOgre.setBounds(10, 20, 100, 30);
		frame_1.getContentPane().add(btnOgre);

		btnKey = new JButton("Key");
		btnKey.setBounds(10, 70, 100, 30);
		btnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				activeChar = 'k';
			}
		});
		frame_1.getContentPane().add(btnKey);

		btnHero = new JButton("Hero");
		btnHero.setBounds(10, 120, 100, 30);
		btnHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeChar = 'H';

			}
		});
		frame_1.getContentPane().add(btnHero);

		btnWall = new JButton("Wall");
		btnWall.setBounds(10, 213, 100, 30);
		btnWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeChar = 'X';
			}
		});
		frame_1.getContentPane().add(btnWall);

		btnFloor = new JButton("Floor");
		btnFloor.setBounds(10, 172, 100, 30);
		btnFloor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeChar = ' ';
			}
		});
		frame_1.getContentPane().add(btnFloor);
		
		JPanel panel_1 = new JPanel(){
			@Override
			public void paintComponent(Graphics g) {
				System.out.print(game.gameLogic.currentMap.getMap()[0][0]);

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

	
		};
		panel_1.setBounds(136, 20, 415, 318);
		frame_1.getContentPane().add(panel_1);


		btnDoor = new JButton("Door");
		btnDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeChar = 'I';
			}
		});
		btnDoor.setBounds(10, 270, 100, 30);
		getContentPane().add(btnDoor);

		btnSave = new JButton("Save Changes");
		btnSave.setBounds(195, 360, 181, 23);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hasSaved = true;
			}
		});
		getContentPane().add(btnSave);
	}	
}