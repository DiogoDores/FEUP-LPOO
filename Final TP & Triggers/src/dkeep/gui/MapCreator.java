package dkeep.gui;
import dkeep.logic.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

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
		
		repaint();
		
		gameLogic.changeCurrentMap(map);
		refreshMap(2,2);
	}
	
	public void refreshMap(int x, int y){
		map = new EditorMap(x, y);
		gameLogic.changeCurrentMap(map);
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

		//g.drawImage(hero, gameLogic.hero.getY() * 49 , gameLogic.hero.getX() * 49 - 49, 64, 90, null);
		//g.drawImage(guard,  gameLogic.guard.getY() * 49, gameLogic.guard.getX() * 49 - 49, 64, 90, null);


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
