package dkeep.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import dkeep.logic.GameLogic;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu {

	private JFrame frame;
	private JTextField textField;
	private JTextArea mapArea;
	private JComboBox comboBox;
	private JLabel lblTip;
	private JButton btnUp, btnDown, btnLeft, btnRight, btnQuit, btnNewGame;
	private GameLogic gameLogic = new GameLogic();
	private Game game = new Game("Dungeon Escape", 600, 400);

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


	public void update(char key) {

		lblTip.setText("Press one of the move button to advence!");

		boolean lost = false;

		gameLogic.hero.move(gameLogic.currentMap, key);
		gameLogic.guard.move();

		for(int i = 0; i < gameLogic.ogres.size(); i++){
			gameLogic.ogres.get(i).moveOgre(gameLogic);
			gameLogic.ogres.get(i).moveClub(gameLogic, gameLogic.ogres.get(i).getX(), gameLogic.ogres.get(i).getY());
		}

		mapArea.setText(gameLogic.currentMap.drawMap(gameLogic));

		lost = gameLogic.checkPresence();

		if(lost == true){
			changeMessage(2);
			btnUp.setEnabled(false);
			btnDown.setEnabled(false);
			btnLeft.setEnabled(false);
			btnRight.setEnabled(false);
			return;
		}

		if((gameLogic.hero.getY() == 0 && gameLogic.hero.getX() == 5) || (gameLogic.hero.getY() == 0 && gameLogic.hero.getX() == 6)){
			gameLogic.createCharacters(2, (String)comboBox.getSelectedItem(), Integer.parseInt(textField.getText()));
			mapArea.setText(gameLogic.currentMap.drawMap(gameLogic));
			changeMessage(1);
		}
	}

	public void changeMessage(int i) {
		if(i == 2){
			lblTip.setText("You got caught!");
		} else if (i == 1){
			lblTip.setText("Next Level!\n Try to escape the ogres!");
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		/*
		 * Initialization of all of the frame's components
		 */

		frame = new JFrame();
		frame.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		frame.setTitle("Dungeon Escape");
		frame.setBounds(100, 100, 610, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton buttonPlay = new JButton("Jogar");
		buttonPlay.setHorizontalAlignment(SwingConstants.LEFT);
		buttonPlay.setFont(new Font("Tahoma", Font.BOLD, 20));
		buttonPlay.setForeground(Color.WHITE);
		buttonPlay.setOpaque(false);
		buttonPlay.setContentAreaFilled(false);
		buttonPlay.setBorderPainted(false);
		buttonPlay.setBounds(250, 350,120,40);
		frame.getContentPane().add(buttonPlay);

		btnNewGame = new JButton("New Game");
		btnNewGame.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));

		btnQuit = new JButton("Quit");
		btnQuit.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));

		btnUp = new JButton("Up");
		btnUp.setEnabled(false);
		btnUp.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));

		btnDown = new JButton("Down");
		btnDown.setEnabled(false);
		btnDown.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));

		btnLeft = new JButton("Left");
		btnLeft.setEnabled(false);
		btnLeft.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));

		btnRight = new JButton("Right");
		btnRight.setEnabled(false);
		btnRight.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));

		JLabel lblNumOgres = new JLabel("Number of Ogres");
		lblNumOgres.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));

		JLabel lblGuardsPersonality = new JLabel("Guard's Personality");
		lblGuardsPersonality.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));

		textField = new JTextField();
		textField.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		textField.setColumns(10);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunken", "Suspicious"}));

		mapArea = new JTextArea();
		mapArea.setEditable(false);
		mapArea.setFont(new Font("Courier New", Font.PLAIN, 25));

		lblTip = new JLabel("Press \"New Game\" to start!");
		lblTip.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));

		/*
		 * Button Listeners
		 */

		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);				
			}
		});

		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				update('w');
			}
		});

		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				update('s');
			}
		});

		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				update('a');
			}
		});


		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				update('d');

			}
		});

		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				frame.setVisible(false);
				game.start();
				
				//window.drawPanel();

				/*if(Integer.parseInt(textField.getText()) >= 1 && Integer.parseInt(textField.getText()) <= 5){

					game.createCharacters(1, (String)comboBox.getSelectedItem(), Integer.parseInt(textField.getText()));

					mapArea.setText(game.currentMap.drawMap(game));

					btnUp.setEnabled(true);
					btnDown.setEnabled(true);
					btnLeft.setEnabled(true);
					btnRight.setEnabled(true);
				} else {
					lblTip.setText("Invalid number of ogres. Enter a number from 1 to 5 to continue");
				}*/

			}
		});

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap(15, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblGuardsPersonality)
												.addComponent(lblNumOgres))
										.addGap(18)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(textField, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
												.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(12)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(mapArea, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblTip))
										.addGap(26)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(btnNewGame, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
														.addGap(49))
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(btnUp, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
														.addGap(63))
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(btnDown, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
														.addGap(63))
												.addGroup(groupLayout.createSequentialGroup()
														.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
																.addComponent(btnQuit, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
																.addGroup(groupLayout.createSequentialGroup()
																		.addComponent(btnLeft, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(btnRight, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
														.addGap(12)))))
						.addContainerGap())
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNumOgres)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblGuardsPersonality)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(18)
										.addComponent(mapArea, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(lblTip))
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(37)
										.addComponent(btnNewGame)
										.addGap(66)
										.addComponent(btnUp)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnLeft)
												.addComponent(btnRight))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnDown)
										.addPreferredGap(ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
										.addComponent(btnQuit)))
						.addContainerGap())
				);
		frame.getContentPane().setLayout(groupLayout);
	}
}
