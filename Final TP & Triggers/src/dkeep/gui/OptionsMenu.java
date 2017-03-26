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
import javax.swing.JButton;

public class OptionsMenu extends JPanel{

	private JFrame frame;
	private JTextField textField;
	private JLabel lblTypeOfGuard;
	private JComboBox comboBox;
	private JButton btnSaveChanges;
	private JButton btnCancel;
	
	private static int numMechas;
	private static String guardType;
	public static boolean wasSetUp = false;

	public OptionsMenu(){
		numMechas = 1;
		guardType = "Rookie";
	}

	/**
	 * Initializes frame's components.
	 */
	
	public void init(){
		frame = new JFrame("Settings");     
		frame.setContentPane(this);
		frame.setSize(300, 210);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		setLayout(null);

		JLabel lblNumberOfMechas = new JLabel("Number of Mechas");
		lblNumberOfMechas.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		lblNumberOfMechas.setBounds(30, 27, 109, 17);
		add(lblNumberOfMechas);

		textField = new JTextField();
		textField.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		textField.setBounds(150, 25, 55, 20);
		add(textField);
		textField.setColumns(10);

		lblTypeOfGuard = new JLabel("Type of Guard");
		lblTypeOfGuard.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		lblTypeOfGuard.setBounds(30, 59, 109, 14);
		add(lblTypeOfGuard);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunken", "Suspicious"}));
		comboBox.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		comboBox.setBounds(150, 56, 90, 20);
		add(comboBox);

		btnSaveChanges = new JButton("Save Changes");
		btnSaveChanges.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		btnSaveChanges.setBounds(30, 110, 130, 25);
		add(btnSaveChanges);

		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		btnCancel.setBounds(30, 145, 130, 25);
		add(btnCancel);
		
		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(Integer.parseInt(textField.getText()) >= 1 && Integer.parseInt(textField.getText()) <= 5){
						saveSettings();
						frame.dispose();
						wasSetUp = true;
					} else {
						DialogBox dialog = new DialogBox("ERROR!", 300, 100, "Invalid Num");
						dialog.setVisible(true);
					}
				} catch (NumberFormatException e){
					DialogBox dialog = new DialogBox("ERROR!", 300, 100, "Invalid Num");
					dialog.setVisible(true);
				} 
			}
		});
		
		

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				wasSetUp = true;
			}
		});
		
	}

	/**
	 * Saves settings present on the menu, and modifies the values on the active game.
	 */
	
	private void saveSettings() {
		numMechas = Integer.parseInt(textField.getText());
		guardType = (String)comboBox.getSelectedItem();
		Game.changeSettings(this);
		wasSetUp = true;
	}

	public static int getNumMechas(){
		return numMechas;
	}

	public static String getGuardType(){
		return guardType;
	}

	public static boolean getWasSetUp(){
		return wasSetUp;
	}

}
