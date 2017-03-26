package dkeep.gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;

public class MapCreator extends JPanel implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private char activeChar;
	private JPanel panel;
	private JFrame frame = new JFrame();
	private String title;
	private JButton btnOgre, btnKey, btnHero, btnWall, btnFloor, btnSave;
	private JButton btnDoor;
	private EditorMap map;
	private Game game;
	private boolean heroWasCreated;
	private float ogreCounter;
	private JSlider slider;

	public int x, y;
	double mult;
	private int intMult;

	class Slider implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {

			map = new EditorMap(slider.getValue(), slider.getValue());
			game = new Game("Editor");
			game.levels[0] = map;
			game.levelPositionArray = 0;
			game.gameLogic.currentMap = map;

			mult = 500/slider.getValue();
			intMult = (int)mult;

			map.resetMap(game.gameLogic);
			Assets.init();
			repaint();
		}
	}

	/**
	 * Initializes a new Window with MapCreator components.
	 */
	public MapCreator(String string) {

		heroWasCreated = false;
		activeChar = 0;
		title = string;
		ogreCounter = 0;

		frame = new JFrame(title);     
		frame.setContentPane(this);
		frame.setSize(800, 600);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		setLayout(null);

		panel = new JPanel(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);

				game.drawStructures(g, mult);

				for(int i = 0; i < map.getMap().length; i++){
					for(int j = 0; j < map.getMap().length; j++){
						if(map.getMap()[j][i] == 'H'){
							g.drawImage(Assets.heroFront, game.gameLogic.hero.getX() * intMult - 10, game.gameLogic.hero.getY() * intMult - 55, intMult + 14, intMult + 30, null);
						} else if(map.getMap()[j][i] == 'O'){
							g.drawImage(Assets.ogreFront, i * intMult - 10, j * intMult - 55, intMult + 14, intMult + 30, null);
						}

					}
				}
			}
		};

		panel.addMouseListener(this);
		panel.setBounds(250, 30, 500, 500);
		frame.getContentPane().add(panel);
		panel.setFocusable(true);

		JLabel lblMapArea = new JLabel("Number of Tiles");
		lblMapArea.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		lblMapArea.setBounds(12, 12, 109, 16);
		add(lblMapArea);
		panel.requestFocusInWindow();

		slider = new JSlider();
		slider.addChangeListener(new Slider());
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(1);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 10));
		slider.setMaximum(15);
		slider.setMinimum(5);
		slider.setValue(1);
		slider.setBounds(12, 30, 226, 43);
		add(slider);

		Assets.init();

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
		btnOgre.setBounds(30, 80, 85, 100);
		frame.getContentPane().add(btnOgre, BorderLayout.WEST);



		btnKey = new JButton();

		ImageIcon key = new ImageIcon(Assets.key);
		Image imgKey = key.getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_DEFAULT);  
		key = new ImageIcon(imgKey);

		btnKey.setIcon(key);
		btnKey.setBounds(30, 220, 85, 100);
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

		btnHero.setBounds(125, 80, 85, 100);
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

		btnWall.setBounds(30, 360, 85, 100);
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

		btnFloor.setBounds(125, 360, 85, 100);
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
		btnDoor.setBounds(125, 220, 85, 100);
		add(btnDoor);

		btnSave = new JButton("Save Changes");
		btnSave.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		btnSave.setBounds(10, 512, 181, 23);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (map.checkMap(game.gameLogic, slider.getValue(), slider.getValue()) && heroWasCreated) {

					Assets.init();
					game.setVisible(true);
					game.init();

				} else {
					DialogBox box = new DialogBox("ERROR!", 300, 100, "MapNotValid");
					box.setLocationRelativeTo(null);
					box.setVisible(true);
				}
			}
		});
		add(btnSave);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
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

		tempX = Math.round((e.getX())/(500/slider.getValue()));
		tempY = Math.round((e.getY())/(500/slider.getValue()));

		x = (int)tempX;
		y = (int)tempY;


		boolean isPlaceable = checkPlaceable();

		if(isPlaceable){
			if(activeChar == 'H'){
				map.place(x, y, activeChar);
				game.gameLogic.hero.setX(x);
				game.gameLogic.hero.setY(y);
				heroWasCreated = true;
			} else if (activeChar == 'O') {

				if(ogreCounter < 5){
					ogreCounter++;
					map.place(x, y, activeChar);
					game.gameLogic.createOgre(x, y, slider.getValue(), slider.getValue());
				}
			}
			else
				map.place(x, y, activeChar);
		}


		panel.repaint(); 
	}

	private boolean checkPlaceable() {

		if(activeChar == 'H' || activeChar == 'O' || activeChar == 'k' || activeChar == ' '){
			if(map.getMap()[y][x] == 'X')
				return false;
		}

		if(activeChar == 'H' || activeChar == 'O' || activeChar == 'k' || activeChar == 'X'){
			if(map.getMap()[y][x] != ' ')
				return false;
		}

		return true;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}	
}