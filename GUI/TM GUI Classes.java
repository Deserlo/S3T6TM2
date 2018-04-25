import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;

/*
************************************************************************************
************************************************************************************
******************************ABSTRACTION CLASSES***********************************
************************************************************************************
************************************************************************************
*/
class TM_Frame extends JFrame{
	public TM_Frame(String title){
		setTitle(title);
		setVisible(true);
		//setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class PTextField extends JTextField{ //pulled from stack overflow 
	public PTextField(final String promptText){
		/*This class creates a text field that has a default string in the text field.
		  If the user clicks on that text field then the string disappears.
		  Then the default string reappears if the text is empty.*/
		super(promptText);
		addFocusListener(new FocusListener(){
		    public void focusLost(FocusEvent e){
			if(getText().isEmpty()){
			    setText(promptText);
			}
		    }
		    public void focusGained(FocusEvent e){
			if(getText().equals(promptText)){
			    setText("");
			}
		    }
		});
		setForeground(new java.awt.Color(117,132,178));
		setBackground(new java.awt.Color(70,81,108));
		setBorder(javax.swing.BorderFactory.createEmptyBorder());
		setColumns(10);
		setFont(new Font("Helvetica",Font.ITALIC, 20));
	}
}

class CScrollPane extends JScrollPane{
	public CScrollPane(){
		setBorder(null);
		getViewport().setOpaque(true);
		getViewport().setBackground(new java.awt.Color(70,81,108));
		getVerticalScrollBar().setForeground(new java.awt.Color(117,132,178));
		getVerticalScrollBar().setBackground(new java.awt.Color(70,81,108));
		getHorizontalScrollBar().setForeground(new java.awt.Color(117,132,178));
		getHorizontalScrollBar().setBackground(new java.awt.Color(70,81,108));
	}
}

class PTextArea extends JTextArea{ // pulled from stack overflow
	/*This class is similar to "PTextField".
	  This class creates a text area that has a default string in the area.
	  If the user clicks on that text field then the string disappears.
	  Then the default string reappears if the text is empty.*/
	public PTextArea(final String promptText){
		super(promptText);
		addFocusListener(new FocusListener(){
		    public void focusLost(FocusEvent e){
				if(getText().isEmpty()){
					setText(promptText);
				}
		    }
		    public void focusGained(FocusEvent e){
				if(getText().equals(promptText)){
					setText("");
				}
		    }
		});
		setForeground(new java.awt.Color(117,132,178));
		setBackground(new java.awt.Color(70,81,108));
		//setBorder(javax.swing.BorderFactory.createEmptyBorder());
		setFont(new Font("Helvetica",Font.ITALIC, 20));
	}
}

class CPanel extends JPanel{
	public CPanel(){
		setOpaque(false);
	}
}

class CButton extends JButton{
	public CButton(String s, boolean IsSidePanel, int ExtraValue){
		setText(s);
		//setContentAreaFilled(false); //Uncommenting this will cause the flash to disappear when clicking the button. We may want this for the side panel.
		setFocusPainted(false);
		setBorderPainted(false);
		setForeground(new java.awt.Color(117,132,178)); //(255,255,255)(117,132,178)
		if(IsSidePanel == true)
			setBackground(new java.awt.Color(59,68,91));
		else
			setBackground(new java.awt.Color(70,81,108));
		putClientProperty("ExtraValue", ExtraValue);
	}
}

class Header extends JLabel{
	public Header(String s){
		setText(s);
		setFont(new Font("Helvetica", Font.BOLD, 30));
		setForeground(new java.awt.Color(73,210,146));
	}
}

class SetGrid{
	public SetGrid(int x, int y, int width, int height, GridBagConstraints Grid){
		//This class is used for GridBagLayout. This is used in both the developer and manager dashboards.
		Grid.gridx = x;
		Grid.gridy = y;
		Grid.ipadx = width;
		Grid.ipady = height;
	}
}

//Absolute = Absolute Positioning on the screen. These require setLayout(null) for these 3 classes to work.
class AbsoluteTextButton extends JButton{ 
	public AbsoluteTextButton(String Words, int x, int y, int width, int height, boolean transparent, int MenuSwitch, int ButtonNum){
		setText(Words);
		setBorderPainted(false);
		if(transparent == false){
			setForeground(new java.awt.Color(117,132,178));
			setBackground(new java.awt.Color(70,81,108));
		}
		else{
			//setOpaque(false);
			setContentAreaFilled(false);
			setForeground(new java.awt.Color(255,255,255));
		}
		setBounds(x, y, width, height);
		putClientProperty("MenuSwitch", MenuSwitch);
		putClientProperty("ButtonNum", ButtonNum);
	}
}

class AbsoluteTextField extends JTextField{
	public AbsoluteTextField(String Words, int x, int y, int width, int height){
		setText(Words);
		setBorder(javax.swing.BorderFactory.createEmptyBorder());
		setBackground(new java.awt.Color(70,81,108));
		setForeground(new java.awt.Color(117,132,178));
		setBounds(x, y, width, height); 
	}
}

class AbsoluteLabel extends JLabel{
	public AbsoluteLabel(String Words, int x, int y, int width, int height, int FontSize){
		setText(Words);
		setBounds(x, y, width, height);
		setFont(new Font("Helvetica", Font.BOLD, FontSize));
		setForeground(new java.awt.Color(73,210,146));
	}
}

/*
************************************************************************************
************************************************************************************
***********************************SCREEN CLASSES***********************************
************************************************************************************
************************************************************************************
*/

class TM_GUI_Intro{
	static int LocalTimer = 0;
	public TM_GUI_Intro(JPanel frame){
		frame.setBackground(new java.awt.Color(0,0,0));
		frame.setOpaque(true);
		frame.setLayout(null);
		JLabel label_top; JLabel label_bottom;
		ImageIcon icon_top; ImageIcon icon_bottom;
		icon_top = new ImageIcon(Test.class.getResource("art/IntroAnimation/1.png"));
		label_top = new JLabel(icon_top);
		icon_bottom = new ImageIcon(Test.class.getResource("art/IntroAnimation/2.png"));
		label_bottom = new JLabel(icon_bottom);
		frame.add(label_top); frame.add(label_bottom);
		label_top.setBounds(-550, 300, 150, 150);
		label_bottom.setBounds(-550, 300, 150, 150);
	
		ActionListener action = new ActionListener(){ 
			public void actionPerformed(ActionEvent event){
				LocalTimer++;
				switch(LocalTimer){
					case 5: //From 5-11 we are just fading in "TM"
						frame.setBackground(new java.awt.Color(0,0,0,25)); //RGB-A, Alpha(0-255) 0 = Transparent
						label_top.setBackground(new java.awt.Color(255,255,255, 25)); 
						label_bottom.setBackground(new java.awt.Color(255,255,255, 25));
						label_top.setBounds(400, 140, 150, 150);
						label_bottom.setBounds(400, 199, 150, 150);
						break;
					case 6:
						frame.setBackground(new java.awt.Color(0,0,0,50)); 
						label_top.setBackground(new java.awt.Color(255,255,255, 50));
						label_bottom.setBackground(new java.awt.Color(255,255,255, 50));
						break;
					case 7:
						frame.setBackground(new java.awt.Color(0,0,0,75)); 
						label_top.setBackground(new java.awt.Color(255,255,255, 75));
						label_bottom.setBackground(new java.awt.Color(255,255,255, 75));
						break;
					case 8:
						label_top.setBackground(new java.awt.Color(255,255,255, 100));
						label_bottom.setBackground(new java.awt.Color(255,255,255, 100));
						break;
					case 9:
						label_top.setBackground(new java.awt.Color(255,255,255, 150));
						label_bottom.setBackground(new java.awt.Color(255,255,255, 150));
						break;
					case 10:
						label_top.setBackground(new java.awt.Color(255,255,255, 200));
						label_bottom.setBackground(new java.awt.Color(255,255,255, 200));
						break;
					case 11:
						label_top.setBackground(new java.awt.Color(255,255,255, 255));
						label_bottom.setBackground(new java.awt.Color(255,255,255, 255));
						break;
					case 13: //From 13-16 we are doing some small animation
						label_top.setBounds(405, 145, 150, 150);
						label_bottom.setBounds(405, 204, 150, 150);
						break;
					case 14:
						label_top.setBounds(410, 140, 150, 150);
						label_bottom.setBounds(410, 209, 150, 150);
						break;
					case 15:
						label_top.setBounds(405, 145, 150, 150);
						label_bottom.setBounds(405, 204, 150, 150);
						break;
					case 16:
						label_top.setBounds(400, 140, 150, 150);
						label_bottom.setBounds(400, 199, 150, 150);
						break;
					case 17: //From 17-20 is fading out "TM", with 20 setting the labels offscreen
						label_top.setBackground(new java.awt.Color(255,255,255, 175));
						label_bottom.setBackground(new java.awt.Color(255,255,255, 175));
						break;
					case 18:
						label_top.setBackground(new java.awt.Color(255,255,255, 100));
						label_bottom.setBackground(new java.awt.Color(255,255,255, 100));
						break;	
					case 19:
						label_top.setBackground(new java.awt.Color(255,255,255, 50));
						label_bottom.setBackground(new java.awt.Color(255,255,255, 50));
						break;	
					case 20:
						label_top.setBounds(-550, 300, 150, 150);
						label_bottom.setBounds(-550, 300, 150, 150);
						break;
					case 22: //From 22-27 we are transitioning into the register screen so it's not an abrupt transition
						frame.setBackground(new java.awt.Color(10,10,10));
						break;
					case 23:
						frame.setBackground(new java.awt.Color(20,20,20));
						break;
					case 24:
						frame.setBackground(new java.awt.Color(30,30,30));
						break;
					case 25:
						frame.setBackground(new java.awt.Color(40,60,80));
						break;
					case 26:
						frame.setBackground(new java.awt.Color(59,68,91));
						break;
					case 27: //27 = 2.7 seconds for this intro.
						frame.removeAll();
						frame.revalidate();
						Test.MenuVar = 2; 
						break;
					default:
						break;
				}
			}
		};
		Timer LocalTimer = new Timer(100, action); //100 milliseconds = 1 to globalvar = .1 second.
		LocalTimer.start();
	}
}

class RegisterScreen{ 
	JLabel DevTO = new AbsoluteLabel("Developer", 150,-10,195,130, 32);
	JLabel ManTO = new AbsoluteLabel("Manager", 680,-10,195,130, 32);
	JTextField Dev_NameTF = new AbsoluteTextField("Name", 110,120, 240,30);
	JTextField Dev_EmailTF = new AbsoluteTextField("Email", 110,160, 240,30);
	JTextField Dev_TeamTF = new AbsoluteTextField("Team", 110,200, 240,30);
	JTextField Dev_PasswordTF = new AbsoluteTextField("Password", 110,240, 240,30);
	JTextField Man_NameTF = new AbsoluteTextField("Name", 640,120, 240,30);
	JTextField Man_EmailTF = new AbsoluteTextField("Email", 640,160, 240,30);
	JTextField Man_TeamTF = new AbsoluteTextField("Team", 640,200, 240,30);
	JTextField Man_PasswordTF = new AbsoluteTextField("Password", 640,240, 240,30);
	JButton button_DevRegister = new AbsoluteTextButton("Register", 145,300, 160,45, false, 3, 1);
	JButton button_DevLogin = new AbsoluteTextButton("Login", 145,360, 160,45, false, 1, 2);
	JButton button_ManRegister = new AbsoluteTextButton("Register", 675,300, 160,45, false, 4, 3);
	JButton button_ManLogin = new AbsoluteTextButton("Login", 675,360, 160,45, false, 1, 4);
	
	public RegisterScreen(JPanel frame){
		frame.setBackground(new java.awt.Color(59,68,91));
		frame.add(DevTO); frame.add(ManTO);
		frame.add(Dev_NameTF); frame.add(Dev_EmailTF);
		frame.add(Dev_TeamTF); frame.add(Dev_PasswordTF);
		frame.add(Man_NameTF); frame.add(Man_EmailTF);
		frame.add(Man_TeamTF); frame.add(Man_PasswordTF);
		frame.add(button_DevRegister); frame.add(button_DevLogin);
		frame.add(button_ManRegister); frame.add(button_ManLogin);
		frame.setLayout(null);
	
		ActionListener RepaintScreen = new ActionListener(){ 
			public void actionPerformed(ActionEvent event){
				frame.repaint(500);
				frame.revalidate();
			}
		};
		Timer RepaintTimer = new Timer(100, RepaintScreen); //100 milliseconds = 1 to globalvar = .1 second.
		RepaintTimer.start();
		ActionListener action = new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				frame.setBackground(new java.awt.Color(0,0,0));
				frame.removeAll();
				frame.revalidate();
				frame.repaint(2500);
				if((Integer)((JButton)e.getSource()).getClientProperty("ButtonNum") == 1){
					//DevRegister Code
					/*UserAccount newDev = new UserAccount("dev", Dev_NameTF.getText(),Dev_EmailTF.getText(), Dev_PasswordTF.getText(),Dev_TeamTF.getText());
					if (newDev.createAccount(newDev)) {				
						Test.UserID = newDev.queryForId(newDev.username);
						Test.loggedIn = true;
						Test.MenuVar = 3; 
					}*/
				}
				if((Integer)((JButton)e.getSource()).getClientProperty("ButtonNum") == 2){
					//DevLogIn Code
					/*Login newLogin = new Login(Dev_EmailTF.getText(), Dev_PasswordTF.getText());
					if (newLogin.authenticateUser(newLogin) == true) {
						Test.UserID = newLogin.queryForId(newLogin.getUserName());
						Test.loggedIn = true;
						Test.MenuVar = 3; 
					}
					else {
						Test.loggedIn = false;
						Test.MenuVar = 1; 
					}*/
				}
				if((Integer)((JButton)e.getSource()).getClientProperty("ButtonNum") == 3){
					//ManRegister Code
					/*UserAccount newMgr = new UserAccount("mgr", Man_NameTF.getText(),C.getText(), Man_PasswordTF.getText(),Man_TeamTF.getText());
					if (newMgr.createAccount(newMgr)) {				
						Test.UserID = newMgr.queryForId(newMgr.username);
						Test.loggedIn = true;
						//Test.MenuVar = ?; 
					}*/	
				}
				if((Integer)((JButton)e.getSource()).getClientProperty("ButtonNum") == 4){
					//ManLogIn Code
					/*Login newLogin = new Login(C.getText(), Man_PasswordTF.getText());
					if (newLogin.authenticateUser(newLogin) == true) {
						Test.UserID = newLogin.queryForId(newLogin.getUserName());
						Test.loggedIn = true;
						//Test.MenuVar = ; 
					}
					else {
						Test.loggedIn = false;
						Test.MenuVar = 1; 
					}
					*/
				}
				RepaintTimer.stop();
				Test.MenuVar = ((Integer)((JButton)e.getSource()).getClientProperty("MenuSwitch")); 
			}
		};
		button_DevRegister.addActionListener(action);
		button_DevLogin.addActionListener(action);
		button_ManRegister.addActionListener(action);
		button_ManLogin.addActionListener(action);
		frame.setOpaque(true);
	}
}

class LogInScreen{
	JLabel LogInTO = new AbsoluteLabel("Log In", 430,-5,195,130, 32);
	JButton button_LogIn = new AbsoluteTextButton("Log In", 400,300, 160,45, false, 2, 1);
	JButton button_GoBack = new AbsoluteTextButton("Go Back", 440,370, 85,25, false, 2, 2);
	JTextField EmailTF = new AbsoluteTextField("Email",360,200, 240,30);
	JTextField PasswordTF = new AbsoluteTextField("Password", 360,150, 240,30);
	
	public LogInScreen(JPanel frame){
		frame.setBackground(new java.awt.Color(59,68,91));
		frame.add(LogInTO); frame.add(button_LogIn); frame.add(button_GoBack);
		frame.add(EmailTF); frame.add(PasswordTF);
		frame.setLayout(null);
		ActionListener RepaintScreen = new ActionListener(){ 
			public void actionPerformed(ActionEvent event){
				frame.repaint(500);
				frame.revalidate();
			}
		};
		Timer RepaintTimer = new Timer(100, RepaintScreen); //100 milliseconds = 1 to globalvar = .1 second.
		RepaintTimer.start();
		
		ActionListener action = new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				frame.setBackground(new java.awt.Color(0,0,0));
				frame.removeAll();
				frame.revalidate();
				frame.repaint();
				if((Integer)((JButton)e.getSource()).getClientProperty("ButtonNum") == 1){
					RepaintTimer.stop();
					
					/* EXAMPLE 3=developer dash, 4= manager dash*/
					String String_Email = EmailTF.getText();
					if(String_Email.equals("Dev"))
						Test.MenuVar = 3; 
					else if(String_Email.equals("Man"))
						Test.MenuVar = 4; 
					else
						Test.MenuVar = 3;

					//log in code
					/*Login newLogin = new Login(EmailTF.getText(), PasswordTF.getText());
					if (newLogin.authenticateUser(newLogin) == true) {
						Test.UserID = newLogin.queryForId(newLogin.getUserName());
						Test.loggedIn = true;
						//Test.MenuVar = 3 //if dev
						//Test.MenuVar = 4 //if manager
					}
					else {
						Test.loggedIn = false;
						//Display "Bad Username/Password"
					}*/
				}
				if((Integer)((JButton)e.getSource()).getClientProperty("ButtonNum") == 2){
					RepaintTimer.stop();
					Test.MenuVar = 2; //Go Back
				}
			}
		};
		button_LogIn.addActionListener(action);
		button_GoBack.addActionListener(action);
		frame.setOpaque(true);
	}
}

class SidePanel extends JPanel{ //SidePanel works for both Developer and Manager
	JButton Hours = new CButton("<html><font face = helvetica size = 5> Hours </font></html>", true, 0);
	JButton Projects = new CButton("<html><font face = helvetica size = 5> Projects </font></html>", true, 1);
	JButton Reports = new CButton("<html><font face = helvetica size = 5> Reports </font></html>", true, 2);
	JButton LogOut = new CButton("<html><font face = helvetica size = 5> Log Out </font></html>", true, 3);
	
	public SidePanel(Container f, JPanel main, JPanel[] s){
		//NOTE ABOUT PARAMETERS:
		/*main will be the panel that side panel lies on
		  array s(center_panels) will contain all the different center panels
		  this allows the buttons to change the panels while keeping itself
		  intact*/
		  
		/*ARRAY indexing:
		  0 = hours
		  1 = projects
		  2 = reports
		  3 = log in
		*/
		
		//GRAPHICS CODE
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx=1.0;
		c.weighty=1.0;
		
		new SetGrid(0,0,60,15, c);
		add(new CPanel(),c);
		new SetGrid(1,0,9,15, c);
		add(new CPanel(),c);
		new SetGrid(0,1,60,25, c);
		add(Hours,c);
		new SetGrid(1,1,9,25, c);
		add(new CPanel(),c);
		new SetGrid(0,2,60,25, c);
		add(Projects,c);
		new SetGrid(0,3,60,25, c);
		add(Reports,c);
		new SetGrid(0,4,60,200, c);
		add(new CPanel(),c);
		new SetGrid(0,5,60,25, c);
		add(LogOut,c);
		new SetGrid(0,6,60,15, c);
		add(new CPanel(),c);
		
		/*bar.setOpaque(true);
		bar.setBackground(new java.awt.Color(117,132,178));
		c.ipadx = 10;
		c.ipady = 600;
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		add(bar,c);*/
		
		setBackground(new java.awt.Color(59,68,91));

		//FOCUS LISTENER CODE
		FocusListener focusCode = new FocusListener(){
			public void focusGained(FocusEvent e){
				if(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")) == 0)
					Hours.setForeground(new java.awt.Color(73,210,146));
				if(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")) == 1)
					Projects.setForeground(new java.awt.Color(73,210,146));
				if(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")) == 2)
					Reports.setForeground(new java.awt.Color(73,210,146));
				if(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")) == 3)
					LogOut.setForeground(new java.awt.Color(73,210,146));
			}
			public void focusLost(FocusEvent e){
				if(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")) == 0)
					Hours.setForeground(new java.awt.Color(117,132,178));
				if(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")) == 1)
					Projects.setForeground(new java.awt.Color(117,132,178));
				if(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")) == 2)
					Reports.setForeground(new java.awt.Color(117,132,178));
				if(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")) == 3)
					LogOut.setForeground(new java.awt.Color(117,132,178));
			}
		};
		Hours.addFocusListener(focusCode);
		Projects.addFocusListener(focusCode);
		Reports.addFocusListener(focusCode);
		LogOut.addFocusListener(focusCode);

		//ACTION LISTENER CODE
		ActionListener action = new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				if(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")) == 3){
					//LogOut
					main.setBackground(new java.awt.Color(0,0,0));
					main.removeAll();
					main.revalidate();
					main.repaint(2500);
					Test.MenuVar = 2;
				}
				else{
					main.remove(((BorderLayout)main.getLayout()).getLayoutComponent(BorderLayout.CENTER));
					main.add(s[((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue"))]);
				}
				main.revalidate();
				main.repaint();
			}
		};
		Hours.addActionListener(action);
		Projects.addActionListener(action);
		Reports.addActionListener(action);
		LogOut.addActionListener(action);
	}
}

//DEVELOPER SCREENS
class HoursDev extends JPanel{
	JTable table;
	String[] Cnames = {"Project","Task","Hours"};
	JScrollPane temp;
	JScrollPane Scroll = new CScrollPane();
	JButton log = new CButton("<html><font face = helvetica size = 4>Log Task</font></html>",false,1);
	
	public HoursDev(String[][] Data, Container f, JPanel main, JPanel[] s){
		table = new JTable(Data, Cnames);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.weightx=1.0;
		c.weighty=1.0;
		
		table.setEnabled(false);
		table.setBackground(new java.awt.Color(117,132,178));
		temp = new JScrollPane(table);
		temp.getViewport().setBackground(new java.awt.Color(59,68,91));
		Scroll.setViewport(temp.getViewport());
		new SetGrid(0,1,450,325, c);
		add(Scroll,c);
		new SetGrid(0,2,100,20, c);
		add(log,c);
		
		setBackground(new java.awt.Color(59,68,91));
		
		ActionListener action = new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				if(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")) == 1){
					main.remove(((BorderLayout)main.getLayout()).getLayoutComponent(BorderLayout.CENTER));
					main.add(s[0]);
					main.revalidate();
					main.repaint();
				}
			}
		};
		log.addActionListener(action);
	}
}

class LogTaskDev extends JPanel{
	JLabel LogATask = new Header("Logging a Task");
	JButton Start = new CButton("<html><font face = helvetica size = 4>Start</font></html>", false, 1);
	JButton Stop = new CButton("<html><font face = helvetica size = 4>Stop</font></html>", false, 2);
	JTextField TaskName = new PTextField("Task Name");
	JTextField ProjectName = new PTextField("Project Name");
	JTextArea Description = new PTextArea("Description");
	JScrollPane temp = new JScrollPane(Description);
	JScrollPane Scroll = new CScrollPane();
	JPanel[] clear = new JPanel[3];
	
	public LogTaskDev(){
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx=1.0;
		c.weighty=1.0;
		new SetGrid(3,0,100,20, c);
		add(LogATask,c);
		new SetGrid(3,1,100,20, c);
		add(TaskName,c);
		new SetGrid(3,2,100,20, c);
		add(ProjectName,c);
		new SetGrid(3,3,100,60, c);
		Scroll.setViewport(temp.getViewport());
		add(Scroll,c);
		
		for(JPanel pan : clear){
			pan = new CPanel();
			c.gridx++;
			add(pan);
		}
		
		c.fill = GridBagConstraints.NONE;
		new SetGrid(3,4,100,20, c);
		add(Start,c);
		new SetGrid(4,4,100,20, c);
		add(Stop,c);
		
		ActionListener action = new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				if(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")) == 1){
					//Start Code goes here
					String String_TaskName = TaskName.getText();
					String String_ProjectName = ProjectName.getText();
					String String_Description = Description.getText();
					
					//Example
					System.out.println("" + String_Description);
				}
				if(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")) == 2){
					//Stop Code goes here
					String String_TaskName = TaskName.getText();
					String String_ProjectName = ProjectName.getText();
					String String_Description = Description.getText();
					
					//Example
					System.out.println("" + String_TaskName);
				}
			}
		};
		Start.addActionListener(action);
		Stop.addActionListener(action);
		setBackground(new java.awt.Color(59,68,91));
	}
}

class ProjectsDev extends JPanel{
	JLabel ProjectsLabel = new Header("Projects");
	JTable table;
	String[] Cnames = {"Project","Budget","Tasks"};
	JScrollPane temp;
	JScrollPane Scroll = new CScrollPane();
	JButton log = new CButton("<html><font face = helvetica size = 4>Log Task</font></html>",false,1);
	
	public ProjectsDev(String[][] Data, Container f, JPanel main, JPanel[] s){
		table = new JTable(Data, Cnames);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.weightx=1.0;
		c.weighty=1.0;

		new SetGrid(0,0,100,20, c);
		add(ProjectsLabel,c);

		new SetGrid(0,1,450,325, c);
		table.setEnabled(false);
		table.setBackground(new java.awt.Color(117,132,178));
		temp = new JScrollPane(table);
		temp.getViewport().setBackground(new java.awt.Color(59,68,91));
		Scroll.setViewport(temp.getViewport());
		add(Scroll,c);
		
		new SetGrid(0,2,100,20, c);
		add(log,c);
		
		setBackground(new java.awt.Color(59,68,91));
		
		ActionListener action = new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				if(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")) == 1){
					main.remove(((BorderLayout)main.getLayout()).getLayoutComponent(BorderLayout.CENTER));
					main.add(s[0]);
					main.revalidate();
					main.repaint();
				}
			}
		};
		log.addActionListener(action);
	}
}

class ReportsDev extends JPanel{
	JLabel ReportsLabel = new Header("Reports");
	int count = 0;
	JPanel Columns = new CPanel();
	JPanel Columns2 = new CPanel();
	JPanel Main = new CPanel();
	JScrollPane temp;
	JScrollPane Scroll = new CScrollPane();
	JButton[] Buttons;
	JPanel pr;
	
	public ReportsDev(String[][] Data, JPanel main, Container f, JPanel[] s){
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		//Filling Buttons
		Buttons = new CButton[40];
		for(int x = 0 ; x < Buttons.length ; x++){
			Buttons[x] = new CButton("Some Project",false, 50);//<html><font face = helvetica size = 4> </font></html>
			Buttons[x].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					pr = new ProjectReportDev(Data, ((JButton)e.getSource()).getText(), f, main, s);
					main.remove(((BorderLayout)main.getLayout()).getLayoutComponent(BorderLayout.CENTER));
					main.add(pr,BorderLayout.CENTER);
					main.revalidate();
					main.repaint();
					System.out.println("button pressed");
				}
			});
		}
		int boxSizes = Buttons.length / 2 + 1;
		
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx=1.0;
		c.weighty=1.0;

		new SetGrid(0,0,100,20, c);
		add(ReportsLabel,c);

		Columns.setLayout(new GridBagLayout());
		Columns2.setLayout(new GridBagLayout());
		
		GridBagConstraints cs1 = new GridBagConstraints();
		GridBagConstraints cs2 = new GridBagConstraints();

		cs2.ipady = 30;
		cs2.ipadx = 125;
		cs2.gridx = 0;
		new SetGrid(0,0,125,30, cs1);
		cs1.insets = new Insets(20,20,0,0);
		cs2.insets = new Insets(20,20,0,0);

		
		Main.setLayout(new GridLayout(1,2,30	,10));
		Main.add(Columns);
		Main.add(Columns2);
		
		count = 0;
		for(int x = 0; x < Buttons.length; x++){
			if(count%2==0){
				Columns.add(Buttons[x],cs1);
				cs1.gridy++;
			}
			else{
				Columns2.add(Buttons[x],cs2);
				cs1.gridy++;

			}
			count++;
		}
		
		temp = new JScrollPane(Main);
		temp.getViewport().setBackground(new java.awt.Color(59,68,91));
		Scroll.setViewport(temp.getViewport());

		new SetGrid(0,1,550,350, c);
		add(Scroll,c);
		
		setBackground(new java.awt.Color(59,68,91));
	}
}

class ProjectReportDev extends JPanel{
	JLabel ProjectsLabel;
	JTable table;
	String[] Cnames = {"Task","Time","Description"};
	JScrollPane temp;
	JScrollPane Scroll = new CScrollPane();
	JButton log = new CButton("<html><font face = helvetica size = 4>Log Task</font></html>",false,1);
	
	public ProjectReportDev(String[][] Data,String Project, Container f, JPanel main, JPanel[] s){
		ProjectsLabel = new Header("Project " + Project + " Report");
		table = new JTable(Data, Cnames);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.weightx=1.0;
		c.weighty=1.0;

		new SetGrid(0,0,100,20, c);
		add(ProjectsLabel,c);
		new SetGrid(0,1,450,325, c);
		table.setEnabled(false);
		table.setBackground(new java.awt.Color(117,132,178));
		temp = new JScrollPane(table);
		temp.getViewport().setBackground(new java.awt.Color(59,68,91));
		Scroll.setViewport(temp.getViewport());
		add(Scroll,c);
		new SetGrid(0,2,100,20, c);
		add(log,c);
		
		setBackground(new java.awt.Color(59,68,91));

		ActionListener action = new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				if(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")) == 1){
					main.remove(((BorderLayout)main.getLayout()).getLayoutComponent(BorderLayout.CENTER));
					main.add(s[0]);
					main.revalidate();
					main.repaint();
				}
			}
		};
		log.addActionListener(action);
	}
}

//MANAGER SCREENS
class HoursManager extends JPanel{
	JLabel LogATask = new Header("Assign a Task");
	JButton Start = new CButton("<html><font face = helvetica size = 4>Start</font></html>", false, 1);
	JButton Stop = new CButton("<html><font face = helvetica size = 4>Stop</font></html>", false, 2);
	JTextField TaskName = new PTextField("Task Name");
	JTextField DeveloperName = new PTextField("Developer To Assign Task");
	JTextArea Notes = new PTextArea("Notes To Give Developer");
	JScrollPane temp = new JScrollPane(Notes);
	JScrollPane Scroll = new CScrollPane();
	JPanel[] clear = new JPanel[3];
	
	public HoursManager(){
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx=1.0;
		c.weighty=1.0;
		new SetGrid(3,0,100,20, c);
		add(LogATask,c);
		new SetGrid(3,1,100,20, c);
		add(TaskName,c);
		new SetGrid(3,2,100,20, c);
		add(DeveloperName,c);
		new SetGrid(3,3,100,60, c);
		Scroll.setViewport(temp.getViewport());
		add(Scroll,c);
		
		for(JPanel pan : clear){
			pan = new CPanel();
			c.gridx++;
			add(pan);
		}
		
		c.fill = GridBagConstraints.NONE;
		new SetGrid(3,4,100,20, c);
		add(Start,c);
		new SetGrid(4,4,100,20, c);
		add(Stop,c);
		
		ActionListener action = new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				if(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")) == 1){
					//Start Code goes here
					String String_TaskName = TaskName.getText();
					String String_DeveloperName = DeveloperName.getText();
					String String_Notes = Notes.getText();
					
					//Example
					System.out.println("" + String_Notes);
				}
				if(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")) == 2){
					//Stop Code goes here
					String String_TaskName = TaskName.getText();
					String String_DeveloperName = DeveloperName.getText();
					String String_Notes = Notes.getText();
					
					//Example
					System.out.println("" + String_TaskName);
				}
			}
		};
		Start.addActionListener(action);
		Stop.addActionListener(action);
		setBackground(new java.awt.Color(59,68,91));
	}
}

class ProjectsManager extends JPanel{
	JLabel ProjectsLabel = new Header("Current Ongoing Projects");
	JTable table;
	String[] Cnames = {"Project","Budget","People"};
	JScrollPane temp;
	JScrollPane Scroll = new CScrollPane();
	JButton log = new CButton("<html><font face = helvetica size = 4>Add Project</font></html>",false,1);
	
	public ProjectsManager(String[][] Data, Container f, JPanel main, JPanel[] s){
		table = new JTable(Data, Cnames);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.weightx=1.0;
		c.weighty=1.0;
		
		//new SetGrid(0,0,40,20, c);
		//add(new CPanel(),c);
		new SetGrid(0,0,100,20, c);
		add(ProjectsLabel,c);

		new SetGrid(0,1,450,325, c);
		table.setEnabled(false);
		table.setBackground(new java.awt.Color(117,132,178));
		temp = new JScrollPane(table);
		temp.getViewport().setBackground(new java.awt.Color(59,68,91));
		Scroll.setViewport(temp.getViewport());
		add(Scroll,c);
		
		new SetGrid(0,2,100,20, c);
		add(log,c);
		
		setBackground(new java.awt.Color(59,68,91));
		
		ActionListener action = new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				if(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")) == 1){
					main.remove(((BorderLayout)main.getLayout()).getLayoutComponent(BorderLayout.CENTER));
					main.add(s[3]);
					main.revalidate();
					main.repaint();
				}
			}
		};
		log.addActionListener(action);
	}
}

class AddProjectManager extends JPanel{
	JLabel ProjectsLabel = new Header("Add a project");
	JTextField ProjectName = new PTextField("Project name");
	JTextField BudgetHours = new PTextField("Budget hours");
	JTextField PeopleOnProject = new PTextField("People on the project");
	JTextArea Notes = new PTextArea("Notes");
	JScrollPane temp = new JScrollPane(Notes);
	JScrollPane Scroll = new CScrollPane();
	JButton AddProject = new CButton("<html><font face = helvetica size = 4>Add Project</font></html>",false,1);
	
	public AddProjectManager(String[][] Data, Container f, JPanel main, JPanel[] s){
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.weightx=1.0;
		c.weighty=1.0;

		new SetGrid(0,0,100,20, c);
		add(ProjectsLabel,c);
		new SetGrid(1,0,100,20, c);
		add(new CPanel(),c);
		new SetGrid(0,1,100,20, c);
		add(ProjectName,c);
		new SetGrid(1,1,100,20, c);
		add(PeopleOnProject,c);
		new SetGrid(0,2,100,20, c);
		add(BudgetHours,c);
		new SetGrid(0,3,220,140, c);
		Scroll.setViewport(temp.getViewport());
		add(Scroll,c);
		new SetGrid(0,4,20,20, c);
		add(new CPanel(),c);
		new SetGrid(1,4,40,20, c);
		add(AddProject,c);
		
		setBackground(new java.awt.Color(59,68,91));
		
		ActionListener action = new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				if(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")) == 1){
					//Add Project Code
				}
			}
		};
		AddProject.addActionListener(action);
	}
}

class ReportsManager extends JPanel{
	JLabel ProjectsLabel = new Header("Projects");
	JLabel DevelopersLabel = new Header("Developers");
	int count = 0;
	JPanel Columns = new CPanel();
	JPanel Columns2 = new CPanel();
	JPanel Main = new CPanel();
	JPanel Main2 = new CPanel();
	JScrollPane temp;
	JScrollPane temp2;
	JScrollPane Scroll_Projects = new CScrollPane();
	JScrollPane Scroll_Developers = new CScrollPane();
	JButton[] Buttons_Projects;
	JButton[] Buttons_Developers;
	JPanel pr;
	
	public ReportsManager(String[][] Data, JPanel main, Container f, JPanel[] s){
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		//Filling Buttons
		Buttons_Projects = new CButton[40];
		for(int x = 0 ; x < Buttons_Projects.length ; x++){
			Buttons_Projects[x] = new CButton("Some Project",false, 50);//<html><font face = helvetica size = 4> </font></html>
			Buttons_Projects[x].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					pr = new ManagerReports_ByProject(Data,  f, main, s);
					main.remove(((BorderLayout)main.getLayout()).getLayoutComponent(BorderLayout.CENTER));
					main.add(pr,BorderLayout.CENTER);
					main.revalidate();
					main.repaint();
				}
			});
		}
		int boxSizes = Buttons_Projects.length / 2 + 1;
		
		Buttons_Developers = new CButton[40];
		for(int x = 0 ; x < Buttons_Developers.length ; x++){
			Buttons_Developers[x] = new CButton("Developer Name",false, 50);//<html><font face = helvetica size = 4> </font></html>
			Buttons_Developers[x].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					pr = new ManagerReports_ByDev(Data,  f, main, s);
					main.remove(((BorderLayout)main.getLayout()).getLayoutComponent(BorderLayout.CENTER));
					main.add(pr,BorderLayout.CENTER);
					main.revalidate();
					main.repaint();
				}
			});
		}
		int boxSizes2 = Buttons_Developers.length / 2 + 1;
		
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx=1.0;
		c.weighty=1.0;

		new SetGrid(0,0,100,20, c);
		add(ProjectsLabel,c);
		new SetGrid(1,0,100,20, c);
		add(new CPanel(),c);
		new SetGrid(2,0,100,20, c);
		add(DevelopersLabel,c);
		
		Columns.setLayout(new GridBagLayout());
		Columns2.setLayout(new GridBagLayout());
		
		GridBagConstraints cs1 = new GridBagConstraints();
		GridBagConstraints cs2 = new GridBagConstraints();

		new SetGrid(0,0,125,30, cs1);
		cs1.insets = new Insets(20,20,0,0);
		Main.setLayout(new GridLayout(1,2,30,10));
		Main.add(Columns);
		count = 0;
		for(int x = 0; x < Buttons_Projects.length; x++){
			if(count%2==0){
				Columns.add(Buttons_Projects[x],cs1);
				cs1.gridy++;
			}
			count++;
		}
		temp = new JScrollPane(Main);
		temp.getViewport().setBackground(new java.awt.Color(59,68,91));
		Scroll_Projects.setViewport(temp.getViewport());
		new SetGrid(0,1,350,350, c);
		add(Scroll_Projects,c);
		
		new SetGrid(1,1,20,350, c);
		add(new CPanel(),c);
		
		new SetGrid(0,0,125,30, cs2);
		cs2.insets = new Insets(20,20,0,0);
		Main2.setLayout(new GridLayout(1,2,30,10));
		Main2.add(Columns2);
		count = 0;
		for(int x = 0; x < Buttons_Developers.length; x++){
			if(count%2==0){
				Columns2.add(Buttons_Developers[x],cs2);
				cs2.gridy++;
			}
			count++;
		}
		temp = new JScrollPane(Main2);
		temp.getViewport().setBackground(new java.awt.Color(59,68,91));
		Scroll_Developers.setViewport(temp.getViewport());
		new SetGrid(2,1,350,350, c);
		add(Scroll_Developers,c);
		
		setBackground(new java.awt.Color(59,68,91));
	}
}

class ManagerReports_ByDev extends JPanel{
	JLabel ProjectsLabel = new Header("Developer <name> Report");
	JTable table;
	String[] Cnames = {"Task","Time","Description"};
	JScrollPane temp;
	JScrollPane Scroll = new CScrollPane();
	JButton log = new CButton("<html><font face = helvetica size = 4>Close Report</font></html>",false,1);
	
	public ManagerReports_ByDev(String[][] Data, Container f, JPanel main, JPanel[] s){
		table = new JTable(Data, Cnames);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.weightx=1.0;
		c.weighty=1.0;

		new SetGrid(0,0,100,20, c);
		add(ProjectsLabel,c);

		new SetGrid(0,1,450,325, c);
		table.setEnabled(false);
		table.setBackground(new java.awt.Color(117,132,178));
		temp = new JScrollPane(table);
		temp.getViewport().setBackground(new java.awt.Color(59,68,91));
		Scroll.setViewport(temp.getViewport());
		add(Scroll,c);
		
		new SetGrid(0,2,100,20, c);
		add(log,c);
		
		setBackground(new java.awt.Color(59,68,91));
		
		ActionListener action = new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				if(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")) == 1){
					main.remove(((BorderLayout)main.getLayout()).getLayoutComponent(BorderLayout.CENTER));
					main.add(s[2]);
					main.revalidate();
					main.repaint();
				}
			}
		};
		log.addActionListener(action);
	}
}

class ManagerReports_ByProject extends JPanel{
	JLabel ProjectsLabel = new Header("Project <name> Report");
	JTable table;
	String[] Cnames = {"Task","Time","Description"};
	JScrollPane temp;
	JScrollPane Scroll = new CScrollPane();
	JButton log = new CButton("<html><font face = helvetica size = 4>Close Report</font></html>",false,1);
	
	public ManagerReports_ByProject(String[][] Data, Container f, JPanel main, JPanel[] s){
		table = new JTable(Data, Cnames);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.weightx=1.0;
		c.weighty=1.0;

		new SetGrid(0,0,100,20, c);
		add(ProjectsLabel,c);

		new SetGrid(0,1,450,325, c);
		table.setEnabled(false);
		table.setBackground(new java.awt.Color(117,132,178));
		temp = new JScrollPane(table);
		temp.getViewport().setBackground(new java.awt.Color(59,68,91));
		Scroll.setViewport(temp.getViewport());
		add(Scroll,c);
		
		new SetGrid(0,2,100,20, c);
		add(log,c);
		
		setBackground(new java.awt.Color(59,68,91));
		
		ActionListener action = new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				if(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")) == 1){
					main.remove(((BorderLayout)main.getLayout()).getLayoutComponent(BorderLayout.CENTER));
					main.add(s[2]);
					main.revalidate();
					main.repaint();
				}
			}
		};
		log.addActionListener(action);
	}
}