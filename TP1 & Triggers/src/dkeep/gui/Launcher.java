package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Launcher {

	private JFrame frmDungeonEscape;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Launcher window = new Launcher();
					window.frmDungeonEscape.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Launcher() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDungeonEscape = new JFrame();
		frmDungeonEscape.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		frmDungeonEscape.setTitle("Dungeon Escape");
		frmDungeonEscape.setBounds(100, 100, 610, 450);
		frmDungeonEscape.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		
		JLabel lblNumOgres = new JLabel("Number of Ogres");
		lblNumOgres.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		
		JLabel lblGuardsPersonality = new JLabel("Guard's Personality");
		lblGuardsPersonality.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		
		textField = new JTextField();
		textField.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunken", "Suspicious"}));
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		
		JButton btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnUp.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		
		JButton btnDown = new JButton("Down");
		btnDown.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		
		JButton btnLeft = new JButton("Left");
		btnLeft.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		
		JButton btnRight = new JButton("Right");
		btnRight.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		GroupLayout groupLayout = new GroupLayout(frmDungeonEscape.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(11, Short.MAX_VALUE)
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
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(25)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(19)
											.addComponent(btnLeft, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnRight, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
											.addGap(12))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(btnNewGame, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
											.addGap(49))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(btnUp, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
											.addGap(63))))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnDown, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
									.addGap(63))))
						.addComponent(btnQuit, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumOgres)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGuardsPersonality)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
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
							.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
							.addComponent(btnQuit))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		frmDungeonEscape.getContentPane().setLayout(groupLayout);
	}
}
