package dkeep.gui;
import dkeep.logic.*;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dkeep.logic.GameLogic;

public class Game extends JPanel implements KeyListener {
	GameMap[] levels;
	public int levelPositionArray;
	public JPanel panel;
	public JFrame f;
	public BufferedImage guard = Assets.guardFront;
	public BufferedImage hero = Assets.heroFront;
	public BufferedImage[] ogresSprite = new BufferedImage[4];

	private int mapWidth, mapHeight;


	public char[][] getMap() {
		return levels[levelPositionArray].getMap();
	}
	public GameLogic gameLogic = new GameLogic();
	public int width, height;
	public String title;
	private static String guardType;
	private static int numMechas;

	public Game(String title2, int width, int height){

		levelPositionArray = 1;
		levels = new GameMap[3];
		levels[1] = new GuardMap();
		levels[2] = new OgreMap();
		levels[0] = new EditorMap(2,2);
		title = title2;

	}


	public void init(){

		/*if(guardType == null || numMechas == 0){
			guardType = "Rookie";
			numMechas = 1;
		}*/
		gameLogic.currentMap = levels[levelPositionArray];
		if (title != "Editor")
			gameLogic.createCharacters(1, guardType, numMechas);
		mapWidth = gameLogic.currentMap.getMap().length * 50 + 12;
		mapHeight = gameLogic.currentMap.getMap()[0].length * 50 + 37;
		Assets.init();

		display();
		repaint();
	}

	public void display() { 

		if (title != "Editor") {
			f = new JFrame("Prison Escape");     
			f.setContentPane(this);
			f.setSize(mapWidth + 300, mapHeight);
			f.setVisible(true);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setResizable(false);
			f.addKeyListener(this);
			f.setLocationRelativeTo(null);
			f.requestFocusInWindow();


			JButton btnUp = new JButton();
			btnUp.setIcon(new ImageIcon (Assets.upArrow));
			btnUp.setBounds(0, 0, 25, 25);
			f.add(btnUp);

			JButton btnDown = new JButton();
			btnDown.setIcon(new ImageIcon (Assets.downArrow));
			btnDown.setBounds(40, mapHeight - 50, 25, 25);
			f.add(btnDown);

			JButton btnLeft = new JButton();
			btnLeft.setIcon(new ImageIcon (Assets.leftArrow));
			btnLeft.setBounds(10, 234, 89, 23);
			f.add(btnLeft);

			JButton btnRight = new JButton();
			btnRight.setIcon(new ImageIcon (Assets.rightArrow));
			btnRight.setBounds(10, 234, 89, 23);
			f.add(btnRight);

			btnUp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					moveEntities('w');
					f.requestFocusInWindow();
				}
			});

			btnDown.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					moveEntities('s');
					f.requestFocusInWindow();
				}
			});
			btnLeft.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					moveEntities('a');
					f.requestFocusInWindow();
				}
			});
			btnRight.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					moveEntities('d');
					f.requestFocusInWindow();
				}
			});
		}
	}

	private void moveEntities(char key){

		char ogreMove;

		char guardMove = gameLogic.guard.getGuardMove();

		if(key == 'w'){
			hero = Assets.heroBack;
		} else if (key == 'a'){
			hero = Assets.heroRight;
		} else if (key == 's'){
			hero = Assets.heroFront;
		} else if (key == 'd'){
			hero = Assets.heroLeft;
		}

		if (levelPositionArray == 1) {
			if(guardMove == 'w'){
				guard = Assets.guardBack;
			} else if (guardMove == 'a'){
				guard = Assets.guardRight;
			} else if (guardMove == 's'){
				guard = Assets.guardFront;
			} else if (guardMove == 'd'){
				guard = Assets.guardLeft;
			}
			gameLogic.guard.move();

		}
		else if (levelPositionArray == 2) {			
			for(int i = 0; i < gameLogic.ogres.size(); i++){

				ogreMove = gameLogic.ogre.createRandomMove();

				if(ogreMove == 'w'){
				} else if (ogreMove == 'a'){
					ogresSprite[i] = Assets.ogreRight;
				} else if (ogreMove == 's'){
					ogresSprite[i] = Assets.ogreFront;
				} else if (ogreMove == 'd'){
					ogresSprite[i] = Assets.ogreLeft;
				}

				gameLogic.ogres.get(i).moveOgre(gameLogic, ogreMove);
				gameLogic.ogres.get(i).moveClub(gameLogic, gameLogic.ogres.get(i).getX(), gameLogic.ogres.get(i).getY());
			}
		}

		gameLogic.hero.move(gameLogic.currentMap, key);

		boolean lost = gameLogic.checkPresence();

		if(lost){
			DialogBox box = new DialogBox("You Lost...", 400, 400, "GameLost");
			box.setLocationRelativeTo(null);
			box.setVisible(true);
			setVisible(false);
		}

		if (gameLogic.currentMap.checkWin(gameLogic)){
			levelPositionArray++;
			gameLogic.createOgres(numMechas);
			if (levelPositionArray == 3) {
				DialogBox box = new DialogBox("You Won!", 400, 400, "GameWon");
				box.setLocationRelativeTo(null);
				box.setVisible(true);
				setVisible(false);
			}
			else {
				gameLogic.changeCurrentMap(levels[levelPositionArray]);
				gameLogic.currentMap.resetPositions(gameLogic);
				mapWidth = gameLogic.currentMap.getMap().length * 50 + 12;
				mapHeight = gameLogic.currentMap.getMap()[0].length * 50 + 37;
			}
		}
		this.repaint();

	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // Clears board
		drawStructures(g);

		g.drawImage(hero, gameLogic.hero.getY() * 49 , gameLogic.hero.getX() * 49 - 49, 64, 90, null);

		if(gameLogic.currentMap.getName() == "GuardMap"){
			g.drawImage(guard,  gameLogic.guard.getY() * 49, gameLogic.guard.getX() * 49 - 49, 64, 90, null);
		}
		else {
			for(int i = 0; i < gameLogic.ogres.size(); i++){
				g.drawImage(Assets.club, gameLogic.ogres.get(i).getClubY()* 50, gameLogic.ogres.get(i).getClubX()* 50, 50, 50, null);
				g.drawImage(ogresSprite[i], gameLogic.ogres.get(i).getY()* 49, gameLogic.ogres.get(i).getX()* 49 - 40, 54, 80, null);
			}
		}

	}

	public void drawStructures(Graphics g) {
		char[][] mapToDraw = gameLogic.currentMap.getMap();

		for (int x = 0; x < mapToDraw.length; x++) {
			for(int y = 0; y < mapToDraw[x].length; y++){
				if(mapToDraw[y][x] == 'X'){
					if(x == 0){
						if(y == 0)
							g.drawImage(Assets.topLeftWall, x * 50, y * 50, 50, 50, null);
						else if(y == mapToDraw.length - 1)
							g.drawImage(Assets.bottomLeftWall, x * 50, y * 50, 50, 50, null);
						else 
							g.drawImage(Assets.leftWall, x * 50, y * 50, 50, 50, null);
					} else if(x == mapToDraw[y].length - 1){
						if(y == 0)
							g.drawImage(Assets.topRightWall, x * 50, y * 50, 50, 50, null);
						else if(y == mapToDraw.length -1)
							g.drawImage(Assets.bottomRightWall, x * 50, y * 50, 50, 50, null);
						else 
							g.drawImage(Assets.rightWall, x * 50, y * 50, 50, 50, null);
					} else if (y == 0){
						g.drawImage(Assets.topWall, x * 50, y * 50, 50, 50, null);
					} else if (y == mapToDraw.length - 1){
						g.drawImage(Assets.bottomWall, x * 50, y * 50, 50, 50, null);
					} else {
						g.drawImage(Assets.wall, x * 50, y * 50, 50, 50, null);
					}
				} else if (mapToDraw[y][x] == ' '){
					g.drawImage(Assets.floor, x * 50, y * 50, 50, 50, null);
				} else if (mapToDraw[y][x] == 'k'){
					if(gameLogic.currentMap.getName() == "GuardMap")
						g.drawImage(Assets.closedLever, x * 50, y * 50, 50, 50, null);
					else if (gameLogic.currentMap.getName() == "OgreMap")
						g.drawImage(Assets.key, x * 50, y * 50, 50, 50, null);
				} else if (mapToDraw[y][x] == 'I'){
					g.drawImage(Assets.door, x * 50, y * 50, 50, 50, null);
				} else {
					g.drawImage(Assets.openDoor, x * 50, y * 50, 50, 50, null);
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		char moveKey;

		switch(e.getKeyCode()){
		case KeyEvent.VK_W:
			moveKey = 'w';
			moveEntities(moveKey);
			repaint(); 
			break;
		case KeyEvent.VK_A:
			moveKey = 'a';
			moveEntities(moveKey);
			repaint();
			break;
		case KeyEvent.VK_S:
			moveKey = 's';
			moveEntities(moveKey);
			repaint();
			break;
		case KeyEvent.VK_D:
			moveKey = 'd';
			moveEntities(moveKey);
			repaint();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	public static void changeSettings(OptionsMenu optionsMenu) {
		numMechas = OptionsMenu.getNumMechas();
		guardType = OptionsMenu.getGuardType();
	}


}
