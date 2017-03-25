package dkeep.gui;

import dkeep.logic.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;

public class MapCreator extends JPanel implements MouseListener{
	private char activeChar;
	private boolean hasSaved;
	private JPanel panel;
	private JFrame frame = new JFrame();
	private String title;
	private JButton btnOgre, btnKey, btnHero, btnWall, btnFloor, btnSave;
	private JButton btnDoor;
	private EditorMap map;
	private GuardMap testMap;
	private Game game;
	private boolean heroWasCreated;
	// O SLIDER TEM DE VIR PARA AQUI

	public int x, y;

	private int mult;

	/**
	 * Initializes a new Window with MapCreator components.
	 */

	public MapCreator(String string) {

		// VALORES DO SLIDER AQUI
		map = new EditorMap(10,10);
		heroWasCreated = false;
		activeChar = 0;
		title = string;
		mult = 500/10;
		int intMult = (int)mult;

		frame = new JFrame(title);     
		frame.setContentPane(this);
		frame.setSize(800, 600);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		setLayout(null);

		panel = new JPanel(){
			@Override
			public void paintComponent(Graphics g) {
				game.gameLogic.currentMap = map;

				super.paintComponent(g); 
				game.drawStructures(g, mult);

				for(int i = 0; i < map.getMap().length; i++){
					for(int j = 0; j < map.getMap().length; j++){
						if(map.getMap()[j][i] == 'H'){
							g.drawImage(Assets.heroFront, x * intMult - 10 , y * intMult - intMult, intMult + 14, intMult + 40, null);
						} for (int k = 0; k < game.gameLogic.ogres.size(); k++) {
							if(game.gameLogic.ogres.get(k).getX() == i && game.gameLogic.ogres.get(k).getClubY() == j){
								g.drawImage(Assets.ogreFront, i * intMult - 10, j * intMult - intMult, intMult + 14, intMult + 40, null);
							}

						}
					}
				}
			}
		};
		panel.addMouseListener(this);
		panel.setBounds(250, 30, 500, 500);
		frame.getContentPane().add(panel);
		panel.setFocusable(true);
		panel.requestFocusInWindow();

		Assets.init();

		game = new Game("Editor");
		game.levels[0] = map;
		game.levelPositionArray = 0;

		hasSaved = false;

	}
	/**
	 * Adds buttons and respective listeners. They modify the activeChar that will modify the EditorMap in question.
	 */

	public void init() {

		btnOgre = new JButton();

		ImageIcon ogre = new ImageIcon(Assets.ogreFront);
		Image imgOgre = ogre.getImage().getScaledInstance(64, 90, java.awt.Image.SCALE_SMOOTH);

		ogre = new ImageIcon(imgOgre);

		btnOgre.setIcon(ogre);
		btnOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeChar = 'O';
			}
		});
		btnOgre.setBounds(40, 70, 85, 100);
		frame.getContentPane().add(btnOgre, BorderLayout.WEST);



		btnKey = new JButton();

		ImageIcon key = new ImageIcon(Assets.key);
		Image imgKey = key.getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_DEFAULT);  
		key = new ImageIcon(imgKey);

		btnKey.setIcon(key);
		btnKey.setBounds(40, 210, 85, 100);
		btnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				activeChar = 'k';
			}
		});
		frame.getContentPane().add(btnKey, BorderLayout.WEST);



		btnHero = new JButton();

		ImageIcon hero = new ImageIcon(Assets.heroFront);
		Image imgHero = hero.getImage().getScaledInstance(64, 90, java.awt.Image.SCALE_SMOOTH);  
		hero = new ImageIcon(imgHero);

		btnHero.setIcon(hero);

		btnHero.setBounds(135, 70, 85, 100);
		btnHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeChar = 'H';

			}
		});
		frame.getContentPane().add(btnHero, BorderLayout.WEST);


		btnWall = new JButton();

		ImageIcon wall = new ImageIcon(Assets.wall);
		Image imgWall = wall.getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);  

		wall = new ImageIcon(imgWall);

		btnWall.setIcon(wall);

		btnWall.setBounds(40, 350, 85, 100);
		btnWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeChar = 'X';
			}
		});
		frame.getContentPane().add(btnWall, BorderLayout.WEST);


		btnFloor = new JButton();

		ImageIcon floor = new ImageIcon(Assets.floor);
		Image imgFloor = floor.getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);  

		floor = new ImageIcon(imgFloor);

		btnFloor.setIcon(floor);

		btnFloor.setBounds(135, 350, 85, 100);
		btnFloor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeChar = ' ';
			}
		});
		frame.getContentPane().add(btnFloor, BorderLayout.WEST);


		btnDoor = new JButton();

		ImageIcon door = new ImageIcon(Assets.door);
		Image imgDoor = door.getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);  

		door = new ImageIcon(imgDoor);

		btnDoor.setIcon(door);

		btnDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeChar = 'I';
			}
		});
		btnDoor.setBounds(135, 210, 85, 100);
		add(btnDoor);

		btnSave = new JButton("Save Changes");
		btnSave.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		btnSave.setBounds(10, 512, 181, 23);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (map.checkMap(game.gameLogic, 10 ,10 ) && heroWasCreated) {
					//INICIAR JOGO
					hasSaved = true;
					System.out.print("ENDED");
					frame.getDefaultCloseOperation();
				}
			}
		});
		add(btnSave);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		btnCancel.setBounds(10, 540, 181, 23);
		add(btnCancel);

		game.levelPositionArray = 1;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		double tempX, tempY;

		tempX = Math.floor((e.getX())/(500/10));
		tempY = Math.floor((e.getY())/(500/10));

		x = (int)tempX;
		y = (int)tempY;


		if(activeChar == 'H'){
			if (heroWasCreated ) {
				game.gameLogic.hero.setX(x);
				game.gameLogic.hero.setY(y);
			}
			else {
				map.place(x, y, activeChar);
				heroWasCreated = true;
			}
		} 
		else if (activeChar == 'O') {
			//VALORES DO SLIDER AQUI, MUY IMPORTANTE PARA O MOVIMENTO
			game.gameLogic.createOgre(x, y, 10, 10);
		}
		else
			map.place(x, y, activeChar);


		panel.repaint(); 
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}	
}