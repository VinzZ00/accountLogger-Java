package Frame;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import objectPack.account;
import objectPack.facebook;
import objectPack.twitter;

public class executeLogin extends JPanel implements ActionListener{

	Font defaultFont = new Font("Serif", Font.BOLD, 12);
	Font titleFont = new Font("Serif", Font.BOLD, 30);
	
	JPanel north, center, south;
	JLabel title, chooseLabel;
	JComboBox<String> emailsel;
	JButton run, addAccount;
	Vector<account> accountList = new Vector<account>();
	Vector<String> comboBoxContent = new Vector<String>();
	File file = new File(".\\accMemo.txt");
	public executeLogin() {
		this.setLayout(new BorderLayout());
		// TODO Auto-generated constructor stub
		
		Scanner filereader = null;
		try {
			filereader = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (filereader.hasNextLine()) {
			String[] content = filereader.nextLine().split(",");
			
			for (String string : content) {
				System.out.println(string);
			}
			if (content[0].equals("Facebook")) {
				accountList.add(new facebook(content[1], content[2]));
			} else if (content[0].equals("Twitter")) {
				accountList.add(new twitter(content[1], content[2]));
			}
		}
		
		title = new JLabel("ready to go?");
		title.setFont(titleFont);
		
		north = new JPanel();
		north.add(title);
		
		chooseLabel = new JLabel("Choose which account");
		
		comboBoxContent.add("None");
		for (account account : accountList) {
			System.out.println(account.getEmail() + "test");
			comboBoxContent.add(account.getEmail());
		}
		
		for (String string : comboBoxContent) {
			System.out.println("test" + string);
		}
		
		emailsel = new JComboBox<String>(comboBoxContent);
		emailsel.setSelectedIndex(0);
		
		emailsel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (emailsel.getSelectedIndex() != 0) {
					run.setEnabled(true);
				} else {
					run.setEnabled(false);
				}
			}
		});
		
		
		center = new JPanel(new GridLayout(1,2));
		center.setBorder(new EmptyBorder(10,10,10,10));
		center.add(chooseLabel);
		center.add(emailsel);
		
		run = new JButton("Start");
		run.setEnabled(false);
		
		south = new JPanel(new GridLayout(2,1,0,10));
		south.setBorder(new EmptyBorder(10,10,10,10));
		south.add(run);
		
		add(north, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
