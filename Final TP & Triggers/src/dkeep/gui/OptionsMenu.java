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

	private String title;
	private int width, height;
	private JFrame frame;
	private JTextField textField;
	private JLabel lblTypeOfGuard;
	private JComboBox comboBox;
	private JButton btnSaveChanges;
	private JButton btnCancel;
	private JLabel lblWarning;

	private int numMechas = 1;
	private String guardType = "Rookie";

	public OptionsMenu(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
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

		JLabel lblNumberOfMechas = new JLabel("Number of Mechas");
		lblNumberOfMechas.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		lblNumberOfMechas.setBounds(27, 27, 109, 17);
		add(lblNumberOfMechas);

		textField = new JTextField();
		textField.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		textField.setBounds(146, 25, 55, 20);
		add(textField);
		textField.setColumns(10);

		lblTypeOfGuard = new JLabel("Type of Guard");
		lblTypeOfGuard.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		lblTypeOfGuard.setBounds(27, 59, 109, 14);
		add(lblTypeOfGuard);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunken", "Suspicious"}));
		comboBox.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		comboBox.setBounds(143, 56, 90, 20);
		add(comboBox);

		btnSaveChanges = new JButton("Save Changes");
		btnSaveChanges.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		btnSaveChanges.setBounds(27, 153, 111, 23);
		add(btnSaveChanges);

		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		btnCancel.setBounds(146, 153, 111, 23);
		add(btnCancel);

		lblWarning = new JLabel("");
		lblWarning.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		lblWarning.setBounds(27, 101, 230, 33);
		add(lblWarning);

		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Integer.parseInt(textField.getText()) > 1 || Integer.parseInt(textField.getText()) < 5){
					numMechas = Integer.parseInt(textField.getText());
					guardType = (String)comboBox.getSelectedItem();
					frame.dispose();
				}
			}
		});


		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
	}

	public int getNumMechas(){
		return numMechas;
	}

	public String getGuardType(){
		return guardType;
	}
}
