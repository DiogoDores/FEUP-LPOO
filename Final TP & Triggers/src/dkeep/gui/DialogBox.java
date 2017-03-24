package dkeep.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;

public class DialogBox extends JDialog{

	private JLabel txtPleaseChangeYour;
	
	/**
	 * Create the dialog.
	 */
	public DialogBox(String title, int width, int height, String typeOfDialog) {
		setTitle(title);
		setBounds(100, 100, width, height);
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
			}
		}
		
		txtPleaseChangeYour = new JLabel();
		txtPleaseChangeYour.setHorizontalAlignment(SwingConstants.CENTER);
		txtPleaseChangeYour.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		getContentPane().add(txtPleaseChangeYour, BorderLayout.NORTH);
		
		if(typeOfDialog == "Settings"){
			txtPleaseChangeYour.setText("Please change your Settings before you create a New Game!");
		} else if(typeOfDialog == "Invalid Num"){
			txtPleaseChangeYour.setText("Invalid number of Mechas! Please try again!");
		} else if(typeOfDialog == "GameWon"){
			add(new JLabel(new ImageIcon(ImageLoader.loadImage("/GameWon.png"))));
			pack();
		} else if(typeOfDialog == "GameLost"){
			add(new JLabel(new ImageIcon(ImageLoader.loadImage("/GameLost.png"))));
			pack();
		}
		
	}
}
