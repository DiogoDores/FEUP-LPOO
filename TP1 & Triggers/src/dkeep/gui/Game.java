package dkeep.gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dkeep.logic.GameLogic;

public class Game extends JPanel implements KeyListener {

	private JPanel panel;
	private JFrame f;
	private BufferedImage guard = Assets.guardFront;
	private BufferedImage hero = Assets.heroFront;

	private GameLogic gameLogic = new GameLogic();
	public int width, height;
	public String title;

	public Game(String title, int width, int height){

		this.title = title;
		this.width = width;
		this.height = height;

	}

	public void init(){
		gameLogic.createCharacters(1, "Rookie"/*(String)comboBox.getSelectedItem()*/, /*Integer.parseInt(textField.getText())*/ 1);
		Assets.init(gameLogic.currentMap);
		display();
		repaint();
	}

	private void display() { 

		f = new JFrame("Prison Escape");     
		f.setContentPane(this);
		f.setSize(700,700);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setBounds(0, 0, 700, 700);
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

		if(guardMove == 'w'){
			guard = Assets.guardBack;
		} else if (guardMove == 'a'){
			guard = Assets.guardRight;
		} else if (guardMove == 's'){
			guard = Assets.guardFront;
		} else if (guardMove == 'd'){
			guard = Assets.guardLeft;
		}

		gameLogic.hero.move(gameLogic.currentMap, key);
		gameLogic.guard.move();

		boolean lost = gameLogic.checkPresence();

		if(lost){
			System.exit(0);
		}

		this.repaint();

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // Clears board

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
					g.drawImage(Assets.floor, x * 50, y * 50, 50, 50, null);
				}
			}
		}

		g.drawImage(hero, gameLogic.hero.getY() * 49 , gameLogic.hero.getX() * 49 - 49, 64, 90, null);
		g.drawImage(guard,  gameLogic.guard.getY() * 49, gameLogic.guard.getX() * 49 - 49, 64, 90, null);


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

<<<<<<< HEAD
		int fps = 60;
		double timePerUpdate = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();

		while(running){

			now = System.nanoTime();
			delta += (now - lastTime) / timePerUpdate;
			lastTime = now;

			if(delta >= 1){
				update();
				render();
				delta--;
			}
		}

		stop();
	}

	public KeyManager getKeyManager(){
		return keyManager;
	}

	public synchronized void start(){

		if(running)
			return;
		running = true;

		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop(){

		if(!running)
			return;
		running = false;

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	


=======
	@Override
	public void keyTyped(KeyEvent e) {}
>>>>>>> branch 'master' of https://github.com/DiogoDores/LPOO1617_T2G5

}
