package accountSaver;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Frame.addAccount;
import Frame.executeLogin;



public class Main extends JFrame {

	
	JTabbedPane tp = new JTabbedPane();
	
	public Main() {

			tp.add(new executeLogin(), "Execute Login");
			tp.add(new addAccount(), "Add new account");
			
//		JButton	test = new JButton("Change");
//		test.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				refresh();
//			}
//		});
			
		tp.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				refresh();
			}
		});
			
		add(tp, BorderLayout.CENTER);
//		add(test, BorderLayout.SOUTH);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Logging With Selenium");
		setResizable(false);
		setSize(500,300);
	}
	
	private void refresh() {
		// TODO Auto-generated method stub
		this.remove(tp);
		
		tp.removeAll();
		tp.add(new executeLogin(), "Execute Login");
		tp.add(new addAccount(), "Add new account");
		tp.repaint();
		tp.revalidate();
		
		this.add(tp);
		this.repaint();
		this.revalidate();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}
	

	

}
