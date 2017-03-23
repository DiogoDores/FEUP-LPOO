package dkeep.gui;
import dkeep.logic.*;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dkeep.logic.GameLogic;

public class Game extends JPanel implements KeyListener {
	GameMap[] levels;
	int levelPositionArray;
	private JPanel panel;
	private JFrame f;
	private BufferedImage guard = Assets.guardFront;
	private BufferedImage hero = Assets.heroFront;

	private int mapWidth, mapHeight;

	private GameLogic gameLogic = new GameLogic();
	public int width, height;
	public String title;

	public Game(String title, int width, int height){
		System.out.println("MERDA1");
		this.title = title;
		this.width = width;
		this.height = height;
		levelPositionArray = 1;
		levels = new GameMap[3];
		levels[1] = new GuardMap();
		levels[2] = new OgreMap();
//		levels[0] = new EditorMap(2,2);
		System.out.println("MERDA1");
	}

	public void init(){
		System.out.println("MERDA3");
		gameLogic.createCharacters(1, "Rookie"/*(String)comboBox.getSelectedItem()*/, /*Integer.parseInt(textField.getText())*/ 1);
		mapWidth = gameLogic.currentMap.getMap().length * 50 + 12;
		mapHeight = gameLogic.currentMap.getMap()[0].length * 50 + 37;
		Assets.init(gameLogic.currentMap);
		display();
		repaint();
		System.out.println("MERDA4");
	}

	private void display() { 

		f = new JFrame("Prison Escape");     
		f.setContentPane(this);
		f.setSize(mapWidth, mapHeight);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setLocationRelativeTo(null);

		panel = new JPanel();
		panel.setBounds(0, 0, mapWidth, mapHeight);
		f.getContentPane().add(panel);
		panel.addKeyListener(this);
		panel.setFocusable(true);
		panel.requestFocusInWindow();
	}

	private void moveEntities(char key){

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
		}
		else if (levelPositionArray == 2) {
			
		}

		gameLogic.hero.move(gameLogic.currentMap, key);
		gameLogic.guard.move();

		boolean lost = gameLogic.checkPresence();

		if(lost){
			System.exit(0);
		}
		
		System.out.print(gameLogic.currentMap.checkWin(gameLogic));
		if (gameLogic.currentMap.checkWin(gameLogic)){
			levelPositionArray++;
			gameLogic.changeCurrentMap(levels[levelPositionArray]);
			gameLogic.currentMap.resetPositions(gameLogic);
		}
		this.repaint();
		
	

	}
	
	

	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // Clears board

		drawStructures(g);

		g.drawImage(hero, gameLogic.hero.getY() * 49 , gameLogic.hero.getX() * 49 - 49, 64, 90, null);
		g.drawImage(guard,  gameLogic.guard.getY() * 49, gameLogic.guard.getX() * 49 - 49, 64, 90, null);


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
					g.drawImage(Assets.closedLever, x * 50, y * 50, 50, 50, null);
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
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

}
