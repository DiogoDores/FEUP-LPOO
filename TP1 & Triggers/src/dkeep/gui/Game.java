package dkeep.gui;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import dkeep.logic.GameLogic;
import dkeep.logic.GameMap;

public class Game implements Runnable {

	private Display display;
	private Thread thread;
	private BufferStrategy buffer;
	private Graphics g;
	private BufferedImage guard = Assets.guardFront;
	private BufferedImage hero = Assets.heroFront;

	private KeyManager keyManager;
	private GameLogic gameLogic = new GameLogic();
	private char key;

	public int width, height;
	private boolean running = false;
	public String title;

	public Game(String title, int width, int height){

		this.title = title;
		this.width = width;
		this.height = height;

		System.out.println(width + "   " + height);

		keyManager = new KeyManager();

	}

	private void init(){
		this.display = new Display(title, width, height);
		this.display.getFrame().addKeyListener(keyManager);
		gameLogic.createCharacters(1, "Rookie"/*(String)comboBox.getSelectedItem()*/, /*Integer.parseInt(textField.getText())*/ 1);
		Assets.init(gameLogic.currentMap);
	}

	private void update(){
		keyManager.update();

		char guardMove = gameLogic.guard.getGuardMove();

		if(keyManager.up){
			key = 'a';
			hero = Assets.heroBack;
		} else if (keyManager.left){
			key = 'w';
			hero = Assets.heroRight;
		} else if (keyManager.down){
			key = 'd';
			hero = Assets.heroFront;
		} else if (keyManager.right){
			key = 's';
			hero = Assets.heroLeft;
		}

		if(guardMove == 'w'){
			guard = Assets.guardRight;
		} else if (guardMove == 'a'){
			guard = Assets.guardBack;
		} else if (guardMove == 's'){
			guard = Assets.guardLeft;
		} else if (guardMove == 'd'){
			guard = Assets.guardFront;
		}

		//gameLogic.hero.move(gameLogic.currentMap, key);
		//gameLogic.guard.move();
	}

	private void render(){
		buffer = display.getCanvas().getBufferStrategy();

		if(buffer == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}

		g = buffer.getDrawGraphics();

		//Clearing the objects
		g.clearRect(0, 0, width, height);

		//Drawing the objects

		drawImageMap();
		g.drawImage(guard, gameLogic.guard.getX() * 32, gameLogic.guard.getY() * 32, 64, 90, null);
		g.drawImage(hero, gameLogic.hero.getX(), gameLogic.hero.getY(), 64, 90, null);

		//End drawing
		buffer.show();
		g.dispose();
	}

	private void drawImageMap() {

		for(int i = 0; i < Assets.structures.length; i++){
			for(int j = 0; j < Assets.structures[i].length; j++){
				if(Assets.structures[i][j] != null){
					g.drawImage(Assets.structures[i][j], i * 32, j * 32, null);
				}
			}
		}
	}

	@Override
	public void run() {
		init();

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

}
