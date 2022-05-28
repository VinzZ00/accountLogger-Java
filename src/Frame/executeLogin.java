package Frame;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

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
			
//			for (String string : content) {
//				System.out.println(string);
//			}
			if (content[0].equals("Facebook")) {
				accountList.add(new facebook(content[1], content[2]));
			} else if (content[0].equals("Twitter")) {
				accountList.add(new twitter(content[1], content[2]));
			}
		}
		
		int fb = 0, twitter = 0;
		for (account account : accountList) {
			if (account instanceof facebook) {
				fb++;
			} else if (account instanceof twitter) {
				twitter++;
			}
		}
		
		System.out.printf("FB: %d\nTwitter: %d\n", fb, twitter);
		
		
		title = new JLabel("ready to go?");
		title.setFont(titleFont);
		
		north = new JPanel();
		north.add(title);
		
		chooseLabel = new JLabel("Choose which account");
		
		comboBoxContent.add("None");
		for (account account : accountList) {
			String subclass = "None";
			if (account instanceof facebook) {
				subclass = "Facebook";
			} else if (account instanceof twitter) {
				subclass = "Twitter";
			}
//			System.out.println(account.getEmail() + "test");
			comboBoxContent.add(String.format("%s from %s", account.getEmail(), subclass));
		}
		
//		for (String string : comboBoxContent) {
//			System.out.println("test" + string);
//		}
		
		emailsel = new JComboBox<String>(comboBoxContent);
//		emailsel.setSelectedIndex(0);
		
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
		run.addActionListener(this);
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
		if (e.getSource() == run) {
			System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");
			WebDriver chrome = new ChromeDriver();
			chrome.manage().window().maximize();
			String url = null;
			
			int ind = emailsel.getSelectedIndex()-1;
			System.out.println("ini index ke " + ind);
			System.out.println(accountList.get(ind) instanceof facebook);
			if (accountList.get(ind) instanceof facebook) {
				System.out.println("ini facebook");
				facebook fb = (facebook) accountList.get(ind);
				url = fb.url;
				chrome.get(url);
				WebElement we = chrome.findElement(By.cssSelector(fb.emailSelector));
				we.sendKeys(accountList.get(ind).getEmail());
				we = chrome.findElement(By.xpath(fb.passwordXpath));
				we.sendKeys(accountList.get(ind).getPassword());
				we = chrome.findElement(By.xpath(fb.buttonFullXpath));
				we.click();
			} else if (accountList.get(ind) instanceof twitter) {
				System.out.println("ini twitter");
				twitter tw = (twitter) accountList.get(ind);
				url = tw.url;
				chrome.get(url);
				WebElement we;
				chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				we = chrome.findElement(By.xpath(tw.emailSelector));
				we.sendKeys(accountList.get(ind).getEmail());
				chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				we = chrome.findElement(By.xpath(tw.emailButtonXpath));
				we.click();
				chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				
				we = chrome.findElement(By.xpath(tw.passwordXpath));
				we.sendKeys(accountList.get(ind).getPassword());
				we = chrome.findElement(By.xpath(tw.buttonFullXpath));
				we.click();
			}
		}
	}

}
