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
		//ImageIcon icon_corner = new ImageIcon(Test.class.getResource("art/IntroAnimation/1.png"));
		//setIconImage(icon_corner.getImage());
		getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(117,132,178)));
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
	//This class is used for GridBagLayout. This is used in both the developer and manager dashboards.
	public SetGrid(double x, double y, GridBagConstraints Grid){
		Grid.weightx = x;
		Grid.weighty = y;
	}
	public SetGrid(int x, int y, int width, int height, GridBagConstraints Grid){
		Grid.gridx = x;
		Grid.gridy = y;
		Grid.ipadx = width;
		Grid.ipady = height;
	}
	public SetGrid(int x, int y, int width, int height, GridBagConstraints Grid, Component Comp, JPanel frame){
		Grid.gridx = x;
		Grid.gridy = y;
		Grid.ipadx = width;
		Grid.ipady = height;
		frame.add(Comp, Grid);
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

class AddToFrame{
	public AddToFrame(JPanel frame, Component[] Comp){
		for(int i = 0; i < Comp.length; i++)
			frame.add(Comp[i]);
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
					case 6:	frame.setBackground(new java.awt.Color(0,0,0,50)); 
							label_top.setBackground(new java.awt.Color(255,255,255, 50));
							label_bottom.setBackground(new java.awt.Color(255,255,255, 50));
							break;
					case 7:	frame.setBackground(new java.awt.Color(0,0,0,75)); 
							label_top.setBackground(new java.awt.Color(255,255,255, 75));
							label_bottom.setBackground(new java.awt.Color(255,255,255, 75));
							break;
					case 8:	label_top.setBackground(new java.awt.Color(255,255,255, 100));
							label_bottom.setBackground(new java.awt.Color(255,255,255, 100));
							break;
					case 9:	label_top.setBackground(new java.awt.Color(255,255,255, 150));
							label_bottom.setBackground(new java.awt.Color(255,255,255, 150));
							break;
					case 10:label_top.setBackground(new java.awt.Color(255,255,255, 200));
							label_bottom.setBackground(new java.awt.Color(255,255,255, 200));
							break;
					case 11:label_top.setBackground(new java.awt.Color(255,255,255, 255));
							label_bottom.setBackground(new java.awt.Color(255,255,255, 255));
							break;
					case 13: //From 13-16 we are doing some small animation
							label_top.setBounds(405, 145, 150, 150);
							label_bottom.setBounds(405, 204, 150, 150);
							break;
					case 14:label_top.setBounds(410, 140, 150, 150);
							label_bottom.setBounds(410, 209, 150, 150);
							break;
					case 15:label_top.setBounds(405, 145, 150, 150);
							label_bottom.setBounds(405, 204, 150, 150);
							break;
					case 16:label_top.setBounds(400, 140, 150, 150);
							label_bottom.setBounds(400, 199, 150, 150);
							break;
					case 17: //From 17-20 is fading out "TM", with 20 setting the labels offscreen
							label_top.setBackground(new java.awt.Color(255,255,255, 175));
							label_bottom.setBackground(new java.awt.Color(255,255,255, 175));
							break;
					case 18:label_top.setBackground(new java.awt.Color(255,255,255, 100));
							label_bottom.setBackground(new java.awt.Color(255,255,255, 100));
							break;	
					case 19:label_top.setBackground(new java.awt.Color(255,255,255, 50));
							label_bottom.setBackground(new java.awt.Color(255,255,255, 50));
							break;	
					case 20:label_top.setBounds(-550, 300, 150, 150);
							label_bottom.setBounds(-550, 300, 150, 150);
							break;
					case 22: //From 22-27 we are transitioning into the register screen so it's not an abrupt transition
							frame.setBackground(new java.awt.Color(10,10,10));
							break;
					case 23:frame.setBackground(new java.awt.Color(20,20,20));
							break;
					case 24:frame.setBackground(new java.awt.Color(30,30,30));
							break;
					case 25:frame.setBackground(new java.awt.Color(40,60,80));
							break;
					case 26:frame.setBackground(new java.awt.Color(59,68,91));
							break;
					case 27: //27 = 2.7 seconds for this intro.
							frame.removeAll();
							frame.revalidate();
							Test.MenuVar = 1; 
							break;
					default:break;
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
	Component[] Comp = {DevTO, ManTO, Dev_NameTF, Dev_EmailTF, Dev_TeamTF, Dev_PasswordTF, Man_NameTF, Man_EmailTF,
						Man_TeamTF, Man_PasswordTF, button_DevRegister, button_DevLogin, button_ManRegister, button_ManLogin};
	
	public RegisterScreen(JPanel frame){
		frame.setBackground(new java.awt.Color(59,68,91));
		new AddToFrame(frame, Comp);
		frame.setLayout(null);
	
		ActionListener RepaintScreen = new ActionListener(){ 
			public void actionPerformed(ActionEvent event){
				frame.repaint(500);
				frame.revalidate();
			}
		};
		Timer RepaintTimer = new Timer(100, RepaintScreen); //100 milliseconds
		RepaintTimer.start();
		ActionListener action = new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				frame.setBackground(new java.awt.Color(0,0,0));
				frame.removeAll();
				frame.revalidate();
				frame.repaint(2500);
				if((Integer)((JButton)e.getSource()).getClientProperty("ButtonNum") == 1){
					//DevRegister Code
					/*
					UserAccount newDev = new UserAccount("dev", Dev_NameTF.getText(),Dev_EmailTF.getText(), Dev_PasswordTF.getText(),Dev_TeamTF.getText());
					if (newDev.createAccount(newDev)) {				
						Test.userID = newDev.queryForId(newDev.username);
						Test.login = true;
						Test.MenuVar = 3; 
					
					}*/
				}
				if((Integer)((JButton)e.getSource()).getClientProperty("ButtonNum") == 2){
					//DevLogIn Code
					/*
					Login newLogin = new Login(Dev_EmailTF.getText(), Dev_PasswordTF.getText());
					if (newLogin.authenticateUser(newLogin) == true) {
						Test.userID = newLogin.queryForId(newLogin.getUserName());
						Test.login = true;
						Test.MenuVar = 3; 
					}
					else {
						Test.login = false;
						Test.MenuVar = 1; 
					}
					*/
				}
				if((Integer)((JButton)e.getSource()).getClientProperty("ButtonNum") == 3){
					//ManRegister Code
					/*
					UserAccount newMgr = new UserAccount("mgr", Man_NameTF.getText(),Man_EmailTF.getText(), Man_PasswordTF.getText(),Man_TeamTF.getText());
					if (newMgr.createAccount(newMgr)) {				
						Test.userID = newMgr.queryForId(newMgr.username);
						Test.login = true;
						Test.MenuVar = 4; 
					}	
					*/
				}
				if((Integer)((JButton)e.getSource()).getClientProperty("ButtonNum") == 4){
					//ManLogIn Code
					/*
					Login newLogin = new Login(Man_EmailTF.getText(), Man_PasswordTF.getText());
					if (newLogin.authenticateUser(newLogin) == true) {
						Test.userID = newLogin.queryForId(newLogin.getUserName());
						Test.login = true;
						Test.MenuVar = 4; 
					}
					else {
						Test.login = false;
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
	JButton button_GoBack = new AbsoluteTextButton("Register", 412,360, 138,25, false, 2, 2);
	JButton button_ChangePassword = new AbsoluteTextButton("Change Password", 412,400, 138,25, false, 2, 3);
	JTextField UsernameTF = new AbsoluteTextField("Name",360,150, 240,30);
	JTextField PasswordTF = new AbsoluteTextField("Password", 360,200, 240,30);
	Component[] Comp = {LogInTO, button_LogIn, button_GoBack, button_ChangePassword, UsernameTF, PasswordTF};
	
	public LogInScreen(JPanel frame){
		frame.setBackground(new java.awt.Color(59,68,91));
		new AddToFrame(frame, Comp);
		frame.setLayout(null);
		ActionListener RepaintScreen = new ActionListener(){ 
			public void actionPerformed(ActionEvent event){
				frame.repaint(500);
				frame.revalidate();
			}
		};
		Timer RepaintTimer = new Timer(100, RepaintScreen); //100 milliseconds
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
					String String_Username = UsernameTF.getText();
					String String_Password = PasswordTF.getText();
					if(String_Username.equals("Dev"))
						Test.MenuVar = 3; 
					else if(String_Username.equals("Man"))
						Test.MenuVar = 4; 
					else
						Test.MenuVar = 3;

					//log in code
					/*
					Login newLogin = new Login(String_Username, String_Password);
					if (newLogin.authenticateUser(newLogin) == true) {
						Test.userID = newLogin.queryForId(String_Username);
						Test.login = true;
						String role = newLogin.getUserRole(Test.userID);
						System.out.println("Logged in as "+role);
						switch (role) {
							case "developer":
								Test.MenuVar = 3; 
								break;
							case "manager":
								Test.MenuVar = 4; 
								break;		
						}
					}
					else {
						Test.login = false;
						//Test.MenuVar = 1;
						//Display "Bad Username/Password"
					}
					*/
				}
				if((Integer)((JButton)e.getSource()).getClientProperty("ButtonNum") == 2){
					RepaintTimer.stop();
					Test.MenuVar = 2; //Register
				}
				if((Integer)((JButton)e.getSource()).getClientProperty("ButtonNum") == 3){
					RepaintTimer.stop();
					Test.MenuVar = 5; //Change Password
				}
			}
		};
		button_LogIn.addActionListener(action);
		button_GoBack.addActionListener(action);
		button_ChangePassword.addActionListener(action);
		frame.setOpaque(true);
	}
}

class ChangePasswordScreen{
	JLabel TitleTO = new AbsoluteLabel("Change Password", 345,-5,355,130, 32);
	JTextField UsernameTF = new AbsoluteTextField("Name",360,150, 240,30);
	JTextField OldPasswordTF = new AbsoluteTextField("Old Password", 360,200, 240,30);
	JTextField NewPasswordTF = new AbsoluteTextField("New Password", 360,250, 240,30);
	JButton button_ChangePassword = new AbsoluteTextButton("Change Password", 400,300, 160,45, false, 2, 1);
	JButton button_GoBack = new AbsoluteTextButton("Go Back", 440,370, 85,25, false, 2, 2);
	Component[] Comp = {TitleTO, UsernameTF, OldPasswordTF, NewPasswordTF, button_ChangePassword, button_GoBack};
	
	public ChangePasswordScreen(JPanel frame){
		frame.setBackground(new java.awt.Color(59,68,91));
		new AddToFrame(frame, Comp);
		frame.setLayout(null);
		
		ActionListener RepaintScreen = new ActionListener(){ 
			public void actionPerformed(ActionEvent event){
				frame.repaint(500);
				frame.revalidate();
			}
		};
		Timer RepaintTimer = new Timer(100, RepaintScreen); //100 milliseconds
		RepaintTimer.start();
		
		ActionListener action = new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				if((Integer)((JButton)e.getSource()).getClientProperty("ButtonNum") == 1){
					System.out.println("Change Password Successful");
				}
				if((Integer)((JButton)e.getSource()).getClientProperty("ButtonNum") == 2){
					frame.setBackground(new java.awt.Color(0,0,0));
					frame.removeAll();
					frame.revalidate();
					frame.repaint();
					RepaintTimer.stop();
					Test.MenuVar = 1; //Login Screen
				}
			}
		};
		button_ChangePassword.addActionListener(action);
		button_GoBack.addActionListener(action);
		frame.setOpaque(true);
	}
}

class SidePanel extends JPanel{ //SidePanel works for both Developer and Manager
	JButton Hours = new CButton("<html><font face = helvetica size = 5> Hours </font></html>", true, 0);
	JButton Projects = new CButton("<html><font face = helvetica size = 5> Projects </font></html>", true, 1);
	JButton Reports = new CButton("<html><font face = helvetica size = 5> Reports </font></html>", true, 2);
	JButton LogOut = new CButton("<html><font face = helvetica size = 5> Log Out </font></html>", true, 3);
	
	public SidePanel(Container f, JPanel main, JPanel[] s, String[][] Data){
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

		new SetGrid(1.0,1.0,c);
		new SetGrid(0,0,60,15,c,new CPanel(),this);
		new SetGrid(1,0,9,15,c,new CPanel(),this);
		new SetGrid(0,1,60,25,c,Hours,this);
		new SetGrid(1,1,9,25,c,new CPanel(),this);
		new SetGrid(0,2,60,25,c,Projects,this);
		new SetGrid(0,3,60,25,c,Reports,this);
		new SetGrid(0,4,60,200,c,new CPanel(),this);
		new SetGrid(0,5,60,25,c,LogOut,this);
		new SetGrid(0,6,60,15,c,new CPanel(),this);
		/*bar.setOpaque(true);
		bar.setBackground(new java.awt.Color(117,132,178));
		c.ipadx = 10;
		c.ipady = 600;
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		add(bar,c);*/
		
		setBackground(new java.awt.Color(59,68,91));

		//ACTION LISTENER CODE
		ActionListener action = new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				SwitchFocus(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")));
				if(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")) == 3){
					//LogOut
					main.setBackground(new java.awt.Color(0,0,0));
					main.removeAll();
					main.revalidate();
					main.repaint(2500);
					Test.MenuVar = 1;
				}
				else{
					if(Test.MenuVar == 3){
						//BACK END TEAM
						//Add code to update Data[][] content here for developer hours table
						//Developer dev = new Developer();
						//s[0] = new HoursDev(f, main, s, dev.getDevHours(Test.userID));
						//s[0] = new HoursDev(f, main, s, dev.getDevProjects(Test.userID));
						//s[1] = new ProjectsDev(f, main, s, dev.getDevReports(Test.userID));
						//Data = GetDataForHours();
						s[0] = new HoursDev(f, main, s, Data);
						//Add code to update Data[][] content here for developer project table
						s[1] = new ProjectsDev(f, main, s, Data); 
						//Add code to update Data[][] content here for developer reports table
						s[2] = new ReportsDev(f, main, s, Data);
					}
					else if(Test.MenuVar == 4){
						//BACK END TEAM
						//Add code to update Data[][] content here for manager hours table
						s[0] = new HoursManager(f, main, s, Data);
						//Add code to update Data[][] content here for manager projects table
						s[1] = new ProjectsManager(f, main, s, Data);
						//Add code to update Data[][] content here for manager reports table
						s[2] = new ReportsManager(f, main, s, Data);
					}
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
	private void SwitchFocus(int ButtonNum){
		switch(ButtonNum){
			case 0:	Hours.setForeground(new java.awt.Color(73,210,146));
					Projects.setForeground(new java.awt.Color(117,132,178));
					Reports.setForeground(new java.awt.Color(117,132,178));
					LogOut.setForeground(new java.awt.Color(117,132,178));
					break;
			case 1:	Hours.setForeground(new java.awt.Color(117,132,178));
					Projects.setForeground(new java.awt.Color(73,210,146));
					Reports.setForeground(new java.awt.Color(117,132,178));
					LogOut.setForeground(new java.awt.Color(117,132,178));
					break;
			case 2:	Hours.setForeground(new java.awt.Color(117,132,178));
					Projects.setForeground(new java.awt.Color(117,132,178));
					Reports.setForeground(new java.awt.Color(73,210,146));
					LogOut.setForeground(new java.awt.Color(117,132,178));
					break;
			case 3:	Hours.setForeground(new java.awt.Color(117,132,178));
					Projects.setForeground(new java.awt.Color(117,132,178));
					Reports.setForeground(new java.awt.Color(117,132,178));
					LogOut.setForeground(new java.awt.Color(73,210,146));
					break;
		}
	}
}

//DEVELOPER SCREENS
class HoursDev extends JPanel{
	JTable table;
	String[] Cnames = {"Project","Task","Hours"};
	JScrollPane temp;
	JScrollPane Scroll = new CScrollPane();
	JButton log = new CButton("<html><font face = helvetica size = 4>Log Task</font></html>",false,1);
	
	public HoursDev(Container f, JPanel main, JPanel[] s, String[][] Data){
		//Developer dev = new Developer();
		//table = new JTable(dev.getDevHours(Test.userID), Cnames);
		table = new JTable(Data, Cnames);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		new SetGrid(1.0,1.0,c);
		
		table.setEnabled(false);
		table.setBackground(new java.awt.Color(117,132,178));
		temp = new JScrollPane(table);
		temp.getViewport().setBackground(new java.awt.Color(59,68,91));
		Scroll.setViewport(temp.getViewport());
		new SetGrid(0,1,450,325,c,Scroll,this);
		new SetGrid(0,2,100,20,c,log,this);
		
		setBackground(new java.awt.Color(59,68,91));
		
		ActionListener action = new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				if(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")) == 1){
					main.remove(((BorderLayout)main.getLayout()).getLayoutComponent(BorderLayout.CENTER));
					s[4] = new LogTaskDev();
					main.add(s[4]);
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
		new SetGrid(1.0,1.0,c);
		new SetGrid(3,0,100,20,c,LogATask,this);
		new SetGrid(3,1,100,20,c,TaskName,this);
		new SetGrid(3,2,100,20,c,ProjectName,this);
		
		Scroll.setViewport(temp.getViewport());
		new SetGrid(3,3,100,60,c,Scroll,this);
		
		for(JPanel pan : clear){
			pan = new CPanel();
			c.gridx++;
			add(pan);
		}
		
		c.fill = GridBagConstraints.NONE;
		new SetGrid(3,4,100,20,c,Start,this);
		new SetGrid(4,4,100,20,c,Stop,this);
		
		ActionListener action = new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				if(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")) == 1){
					String String_TaskName = TaskName.getText();
					String String_ProjectName = ProjectName.getText();
					String String_Description = Description.getText();
					
					//BACK END TEAM
					//Start Task Code 
					/*
					TaskLog task = new TaskLog(String_TaskName, String_ProjectName, String_Description, Test.userID);
					if (task.insertTask(task)) {
						System.out.println("Task "+ String_TaskName + " started.");
					}
					else {
						System.out.println("Error with starting task "+String_TaskName);
					}
					*/
					
					//Example
					//AddTaskToDatabase(String_TaskName, String_ProjectName, String_Description);
					System.out.println("" + String_Description);
				}
				if(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")) == 2){
					
					String String_TaskName = TaskName.getText();
					String String_ProjectName = ProjectName.getText();
					String String_Description = Description.getText();
					
					//BACK END TEAM
					//Stop Task Code
					/*
					TaskLog task = new TaskLog(String_TaskName, String_ProjectName, String_Description, Test.userID);
					if (task.stopTask(Test.userID, task.taskName, task.projectName, task.description)) {
						System.out.println("Task "+ String_TaskName + " stopped.");
					}
					else {
						System.out.println("Error with stopping task "+String_TaskName);
					}
					*/
					
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
	
	public ProjectsDev(Container f, JPanel main, JPanel[] s, String[][] Data){
		table = new JTable(Data, Cnames);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		new SetGrid(1.0,1.0,c);
		new SetGrid(0,0,100,20,c,ProjectsLabel,this);

		new SetGrid(0,1,450,325, c);
		table.setEnabled(false);
		table.setBackground(new java.awt.Color(117,132,178));
		temp = new JScrollPane(table);
		temp.getViewport().setBackground(new java.awt.Color(59,68,91));
		Scroll.setViewport(temp.getViewport());
		add(Scroll,c);
		
		new SetGrid(0,2,100,20,c,log,this);

		setBackground(new java.awt.Color(59,68,91));
		
		ActionListener action = new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				if(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")) == 1){
					main.remove(((BorderLayout)main.getLayout()).getLayoutComponent(BorderLayout.CENTER));
					s[4] = new LogTaskDev();
					main.add(s[4]);
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
	
	public ReportsDev(Container f, JPanel main, JPanel[] s, String[][] Data){
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		//Filling Buttons
		
		//back end code
		//Developer dev = new Developer();
		//String ProjectName[]=dev.getAllDevReports(Test.userID);
		//Buttons = new CButton[ProjectName.length];
		//for(int x = 0 ; x < Buttons.length ; x++){
		//	Buttons[x] = new CButton(ProjectName[x],false, 50);
		//
		
		Buttons = new CButton[40];
		for(int x = 0 ; x < Buttons.length ; x++){
			Buttons[x] = new CButton(/*BACK END TEAM: "" + ProjectName[x]*/"Some Project",false, 50);
			Buttons[x].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					//BACK END TEAM
					//Data for Dev Reports goes here
					pr = new ProjectReportDev(f, main, s, Data, ((JButton)e.getSource()).getText());
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
		new SetGrid(1.0,1.0,c);
		new SetGrid(0,0,100,20,c,ReportsLabel,this);

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
		new SetGrid(0,1,550,350,c,Scroll,this);
		
		setBackground(new java.awt.Color(59,68,91));
	}
}

class ProjectReportDev extends JPanel{
	JLabel ProjectsLabel;
	JTable table;
	String[] Cnames = {"Task","Time","Description"};
	JScrollPane temp;
	JScrollPane Scroll = new CScrollPane();
	JButton log = new CButton("<html><font face = helvetica size = 4>Close Report</font></html>",false,1);
	
	public ProjectReportDev(Container f, JPanel main, JPanel[] s, String[][] Data, String Project){
		ProjectsLabel = new Header("Project " + Project + " Report");
		table = new JTable(Data, Cnames);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		new SetGrid(1.0,1.0,c);
		new SetGrid(0,0,100,20,c,ProjectsLabel,this);
		
		table.setEnabled(false);
		table.setBackground(new java.awt.Color(117,132,178));
		temp = new JScrollPane(table);
		temp.getViewport().setBackground(new java.awt.Color(59,68,91));
		Scroll.setViewport(temp.getViewport());
		new SetGrid(0,1,450,325,c,Scroll,this);
		new SetGrid(0,2,100,20,c,log,this);
		
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

//MANAGER SCREENS
class HoursManager extends JPanel{
	JTable table;
	String[] Cnames = {"Project","Activity","Progress"};
	JScrollPane temp;
	JScrollPane Scroll = new CScrollPane();
	JButton log = new CButton("<html><font face = helvetica size = 4>Add Project</font></html>",false,1);
	
	public HoursManager(Container f, JPanel main, JPanel[] s, String[][] Data){
		table = new JTable(Data, Cnames);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		new SetGrid(1.0,1.0,c);
		
		table.setEnabled(false);
		table.setBackground(new java.awt.Color(117,132,178));
		temp = new JScrollPane(table);
		temp.getViewport().setBackground(new java.awt.Color(59,68,91));
		Scroll.setViewport(temp.getViewport());
		new SetGrid(0,1,450,325,c,Scroll,this);
		new SetGrid(0,2,100,20,c,log,this);

		setBackground(new java.awt.Color(59,68,91));
		
		ActionListener action = new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				if(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")) == 1){
					s[3] = new AddProjectManager();
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
	
	public AddProjectManager(){
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		new SetGrid(1.0,1.0,c);
		new SetGrid(0,0,100,20,c,ProjectsLabel,this);
		new SetGrid(1,0,100,20,c,new CPanel(),this);
		new SetGrid(0,1,100,20,c,ProjectName,this);
		new SetGrid(1,1,100,20,c,PeopleOnProject,this);
		new SetGrid(0,2,100,20,c,BudgetHours,this);
		Scroll.setViewport(temp.getViewport());
		new SetGrid(0,3,220,140,c,Scroll,this);
		new SetGrid(0,4,20,20,c,new CPanel(),this);
		new SetGrid(1,4,40,20,c,AddProject,this);
		
		setBackground(new java.awt.Color(59,68,91));
		
		ActionListener action = new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				if(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")) == 1){
					/*   BACK END TEAM
					Add Project Code goes here
					However it gets added into the database*/
				}
			}
		};
		AddProject.addActionListener(action);
	}
}

class ProjectsManager extends JPanel{
	JLabel ProjectsLabel = new Header("Projects");
	JTable table;
	String[] Cnames = {"Project","Budget","People"};
	JScrollPane temp;
	JScrollPane Scroll = new CScrollPane();
	JButton log = new CButton("<html><font face = helvetica size = 4>Add Project</font></html>",false,1);
	
	public ProjectsManager(Container f, JPanel main, JPanel[] s, String[][] Data){
		table = new JTable(Data, Cnames);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		new SetGrid(1.0,1.0,c);
		new SetGrid(0,0,100,20,c,ProjectsLabel,this);
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
					s[4] = new AddProjectManager();
					main.remove(((BorderLayout)main.getLayout()).getLayoutComponent(BorderLayout.CENTER));
					main.add(s[4]);
					main.revalidate();
					main.repaint();
				}
			}
		};
		log.addActionListener(action);
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
	
	public ReportsManager(Container f, JPanel main, JPanel[] s, String[][] Data){
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		//Filling Buttons
		Buttons_Projects = new CButton[40];
		for(int x = 0 ; x < Buttons_Projects.length ; x++){
			Buttons_Projects[x] = new CButton(/*BACK END TEAM: "" + ProjectName[x]*/"Some Project",false, 50);
			Buttons_Projects[x].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					//BACK END TEAM
					//update Data[][] here
					//Data = 
					pr = new ManagerReports_ByProject(f, main, s, Data, ((JButton)e.getSource()).getText());
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
			Buttons_Developers[x] = new CButton(/*BACK END TEAM: "" + DeveloperName[x]*/"Developer Name",false, 50);//<html><font face = helvetica size = 4> </font></html>
			Buttons_Developers[x].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					//BACK END TEAM
					//update Data[][] here
					pr = new ManagerReports_ByDev(f, main, s, Data, ((JButton)e.getSource()).getText());
					main.remove(((BorderLayout)main.getLayout()).getLayoutComponent(BorderLayout.CENTER));
					main.add(pr,BorderLayout.CENTER);
					main.revalidate();
					main.repaint();
				}
			});
		}
		int boxSizes2 = Buttons_Developers.length / 2 + 1;
		
		//c.fill = GridBagConstraints.HORIZONTAL;
		new SetGrid(1.0,1.0,c);
		new SetGrid(0,0,100,20,c,ProjectsLabel,this);
		new SetGrid(1,0,100,20,c,new CPanel(),this);
		new SetGrid(2,0,100,20,c,DevelopersLabel,this);
		
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
		new SetGrid(0,1,350,350,c,Scroll_Projects,this);
		new SetGrid(1,1,20,350,c,new CPanel(),this);
		
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
		new SetGrid(2,1,350,350,c,Scroll_Developers,this);
		
		setBackground(new java.awt.Color(59,68,91));
	}
}

class ManagerReports_ByDev extends JPanel{
	JLabel ProjectsLabel;
	JTable table;
	String[] Cnames = {"Task","Time","Description"};
	JScrollPane temp;
	JScrollPane Scroll = new CScrollPane();
	JButton log = new CButton("<html><font face = helvetica size = 4>Close Report</font></html>",false,1);
	
	public ManagerReports_ByDev(Container f, JPanel main, JPanel[] s, String[][] Data, String dev){
		ProjectsLabel = new Header("Developer " + dev + " Report");
		table = new JTable(Data, Cnames);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		new SetGrid(1.0,1.0,c);
		new SetGrid(0,0,100,20,c,ProjectsLabel,this);
		new SetGrid(0,1,450,325,c);
		table.setEnabled(false);
		table.setBackground(new java.awt.Color(117,132,178));
		temp = new JScrollPane(table);
		temp.getViewport().setBackground(new java.awt.Color(59,68,91));
		Scroll.setViewport(temp.getViewport());
		add(Scroll,c);
		new SetGrid(0,2,100,20,c,log,this);
		
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
	JLabel ProjectsLabel;
	JTable table;
	String[] Cnames = {"Task","Time","Description"};
	JScrollPane temp;
	JScrollPane Scroll = new CScrollPane();
	JButton log = new CButton("<html><font face = helvetica size = 4>Close Report</font></html>",false,1);
	
	public ManagerReports_ByProject(Container f, JPanel main, JPanel[] s, String[][] Data,String project){
		ProjectsLabel = new Header("Project " + project + " Report");
		table = new JTable(Data, Cnames);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		new SetGrid(1.0,1.0,c);
		new SetGrid(0,0,100,20,c,ProjectsLabel,this);
		new SetGrid(0,1,450,325,c);
		table.setEnabled(false);
		table.setBackground(new java.awt.Color(117,132,178));
		temp = new JScrollPane(table);
		temp.getViewport().setBackground(new java.awt.Color(59,68,91));
		Scroll.setViewport(temp.getViewport());
		add(Scroll,c);
		new SetGrid(0,2,100,20,c,log,this);
		
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