package dkeep.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class MapCreator extends JPanel{

	private char activeChar;
	private String title;
	private int width, height;
	private JFrame frame;
	private JPanel panel;
	private JTextField textField;
	private JComboBox comboBox;
	private JButton btnPlay, btnOgre, btnHero, btnWall, btnDoor, btnKey, btnFloor;
	private JLabel lblWarning;

	
	public MapCreator(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		activeChar = 'H';
	}

	public void init(){

		frame = new JFrame(title);     
		frame.setContentPane(this);
		frame.setSize(width, height);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		setLayout(null);

		
		textField = new JTextField();
		textField.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		textField.setBounds(146, 25, 55, 20);
		add(textField);
		textField.setColumns(10);

	
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunken", "Suspicious"}));
		comboBox.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		comboBox.setBounds(143, 56, 90, 20);
		add(comboBox);

		lblWarning = new JLabel("");
		lblWarning.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		lblWarning.setBounds(27, 101, 230, 33);
		add(lblWarning);
		initButtons();

	}
	
	public void initButtons() {
		btnOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeChar = 'O';
			}
		});
		
		btnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				activeChar = 'k';
			}
		});
		
		btnDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeChar = 'I';
			}
		});
		
		btnWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeChar = 'X';
			}
		});
		
		btnFloor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeChar = ' ';
			}
		});
		
		btnHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeChar = 'H';

			}
		});
		

	}
}
