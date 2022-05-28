package Frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import javax.security.auth.Refreshable;
import javax.security.auth.callback.CallbackHandler;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import accountSaver.Main;


public class addAccount extends JPanel implements ActionListener {

	Font defaultFont = new Font("Serif", Font.BOLD, 12);
	Font titleFont = new Font("Serif", Font.BOLD, 30);
	
	JPanel header, center, south;
	JPanel infoCenter;
	JLabel welcome, infoLabel;
	
	JComboBox<String> accounttype;
	JLabel id, pass;
	JTextField idField, passField;
	JButton submit;
	
	
	boolean change = false;
	
	public addAccount() {
		// TODO Auto-generated constructor stub
		this.setLayout(new BorderLayout());
		welcome = new JLabel("Fill the form to add your new Account");
		infoLabel = new JLabel(String.format("Current Working CWD : %s", System.getProperty("user.dir")));

		welcome.setFont(titleFont);
		infoLabel.setFont(defaultFont);
		
		header = new JPanel(new FlowLayout(FlowLayout.CENTER));
		center = new JPanel(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		
		infoCenter = new JPanel(new FlowLayout(FlowLayout.LEFT));
		header.add(welcome);
		infoCenter.add(infoLabel);
		
		
		
		center.setBorder(new EmptyBorder(10,10,10,10));
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		center.add(infoCenter, gbc);
		
		
		String[] type = {"Facebook", "Twitter"};
		accounttype = new JComboBox<String>(type);
		
		id = new JLabel("Email");
		pass = new JLabel("Password");
		
		idField = new JTextField();
		passField = new JTextField();
		
		gbc.gridy = 1;
		center.add(accounttype,gbc);
		
		gbc.gridwidth = 1;
		gbc.gridy = 2;
		center.add(id,gbc);
		
		gbc.gridx = 1;
		center.add(idField,gbc);
		
		gbc.gridy = 3;
		gbc.gridx = 0;
		center.add(pass,gbc);
		
		gbc.gridx = 1;
		center.add(passField,gbc);
		
		submit = new JButton("Add");
		submit.addActionListener(this);
		south = new JPanel();
		south.add(submit);
		
		
		add(header, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		
//		setVisible(true);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setLocationRelativeTo(null);
//		setTitle("Add account");
//		setResizable(false);
//		setSize(500,300);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == submit) {
			Vector<String> content = new Vector<String>();
			int conf = JOptionPane.showConfirmDialog(null, "Are you sure to add this account data?", "Add Account Confirmation", JOptionPane.YES_NO_OPTION);
			String path = "accMemo.txt";
			if (conf == JOptionPane.YES_OPTION) {
				File accMemo = null;
				accMemo = new File(path);
				
//				boolean unavailable = true;
//				try {
//					unavailable = accMemo.createNewFile();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//					if () {
						Scanner read = null;
						try {
							read = new Scanner(accMemo);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						while (read.hasNextLine()) {
							content.add(read.nextLine());
						}
//					}
				String newContent = "";
				for (String string : content) {
					newContent += String.format("%s\n", string);
				}
				newContent += String.format("%s,%s,%s\n", accounttype.getSelectedItem().toString(), idField.getText(), passField.getText());
				try {
					FileWriter fw = new FileWriter(accMemo);
					fw.write(newContent);
					fw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
				change = true;
			} else JOptionPane.showMessageDialog(null, "Failed to add the account", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}



	
				


	
	
	

