package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Graphics; 

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
 
public class MainMenu extends JPanel{

	private JFrame frame;
	private JButton btnNewGame, btnExit, btnSettings, btnCreateMap;
	private Game game;
	private DialogBox dialog;
	private OptionsMenu options;
	BufferedImage menuBackground = ImageLoader.loadImage("/MenuBackground.png");
	private MapCreator mapCreator;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {

		frame = new JFrame("Main Menu");     
		frame.setContentPane(this);
		frame.setSize(1200, 700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		repaint();

		btnCreateMap = new JButton("Create Map");
		btnCreateMap.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		btnCreateMap.setBounds(43, 488, 112, 29);
		add(btnCreateMap);

		btnNewGame = new JButton("New Game");
		btnNewGame.setBounds(43, 437, 112, 29);
		btnNewGame.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));

		btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		btnExit.setBounds(43, 591, 112, 29);
		add(btnExit);

		btnSettings = new JButton("Settings");
		btnSettings.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		btnSettings.setBounds(44, 539, 110, 28);
		add(btnSettings);

		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(!OptionsMenu.getWasSetUp()){
					OptionsMenu options = new OptionsMenu();
				} 
				Assets.init();
				frame.dispose();
				game = new Game("Prison Escape");
				game.setVisible(true);
				game.init(); 
			}
		});

		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				System.exit(0);
			}	
		});

		btnCreateMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				mapCreator = new MapCreator();
				mapCreator.setVisible(true);
				mapCreator.init();
			}
		});

		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				options = new OptionsMenu();
				options.setVisible(true);
				options.init();
			}
		});


		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnNewGame);

	}

	@Override
	public void paintComponent(Graphics g) {		
		super.paintComponent(g); // Clears board
		g.drawImage(menuBackground, 0, 0, 1200, 700, null);
	}
}
