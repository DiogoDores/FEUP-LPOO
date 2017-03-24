package dkeep.gui;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapCreator extends JPanel {
	private char activeChar;
	private boolean hasSaved;
	private JFrame frame = new JFrame();
	private String title;
	private int width, height;
	private JButton btnOgre, btnKey, btnHero, btnWall, btnFloor, btnSave;
	private JButton btnDoor;
	private EditorMap map;
	private Game game;

	public MapCreator(String string, int i, int j) {
		map = new EditorMap(2,2);
		activeChar = 'H';
		title = string;
		width = i; 
		height = j;
		frame = new JFrame(title);     
		frame.setContentPane(this);
		frame.setSize(582, 423);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		hasSaved = false;
		setLayout(null);
		game = new Game("Editor", 200, 200);
		init();
		update();
		game.gameLogic.changeCurrentMap(map);
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
		
		JPanel panel = new JPanel();
		panel.setBounds(181, 33, 344, 259);
		add(panel);
	}

	public void update() {
		while(!hasSaved) {
			
		}
	}
}
