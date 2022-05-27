package accountSaver;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import Frame.addAccount;
import Frame.executeLogin;



public class Main extends JFrame {

	
//	ChromeOptions options = new ChromeOptions()
////			.addPreference("browser.startup.page", 0)
////		     .addPreference("browser.startup.homepage", "https://www.google.co.uk")
//		     .setAcceptInsecureCerts(true)
//		     .setHeadless(true);
	 
	
	 JTabbedPane tp = new JTabbedPane();
			
	public Main() {
		 
		

		
		tp.add(new executeLogin(), "Execute Login");
			tp.add(new addAccount(), "Add new account");
			
		JButton	refresh = new JButton("Change");
		refresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				refresh();
			}
		});
			
		add(tp, BorderLayout.CENTER);
		add(refresh, BorderLayout.SOUTH);
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
