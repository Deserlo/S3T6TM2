import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
		//setIconImage(icon_corner.getImage()); //can probably easily animate on first Login
		JFrame.setDefaultLookAndFeelDecorated(true);
		getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(117,132,178)));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//pulled from https://stackoverflow.com/questions/11232131/centering-a-jframe
		int x = (int)((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 960) / 2);
		int y = (int)((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 540) / 2);
		setLocation(x, y);
	}
}

class PComboBox<String> extends JComboBox<String>{
	/*This class creates a drop down list.
	  If the user clicks on that drop down list then the initial text disappears
	  This is only used in the manager side for a list of the developers.*/
	public PComboBox(final String promptText, String[] ListOfNames){
		insertItemAt(promptText, 0);
		setSelectedItem(promptText);
		addFocusListener(new FocusListener(){
		    public void focusLost(FocusEvent e){
				if(getSelectedItem().equals(promptText))
					insertItemAt(promptText, 0);
		    }
		    public void focusGained(FocusEvent e){
			    if(getSelectedItem().equals(promptText))
					removeItemAt(0);
		    }
		});
		setForeground(new java.awt.Color(117,132,178));
		setBackground(new java.awt.Color(70,81,108));
		for(int i = 0; i < ListOfNames.length; i++)
			addItem(ListOfNames[i]);
		setEditable(false);
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
	public AbsoluteTextButton(String Words, int x, int y, int width, int height, int MenuSwitch, int ButtonNum){
		setText(Words);
		//if(!ToolTipWords.equals("blank"))
			//setToolTipText(ToolTipWords);
		setBorderPainted(false);
		setForeground(new java.awt.Color(117,132,178));
		setBackground(new java.awt.Color(70,81,108));
		setBounds(x, y, width, height);
		putClientProperty("MenuSwitch", MenuSwitch);
		putClientProperty("ButtonNum", ButtonNum);
	}
}

class AbsoluteTextField extends JTextField{
	public String Password;
	private int PasswordLength;
	public AbsoluteTextField(String Words, int x, int y, int width, int height){
		setText(Words);
		addFocusListener(new FocusListener(){
		    public void focusLost(FocusEvent e){
				if(getText().isEmpty()){
					setText(Words);
				}
				if(Words.equals("Password") || Words.equals("Old Password") || Words.equals("New Password")){ 
					Password = getText(); //There is a JPasswordField but this is /probably/ simpler.
					PasswordLength = getText().length();
					setText("");
					for(int i = 0; i < PasswordLength; i++)
						setText(getText() + "*");
				}
		    }
		    public void focusGained(FocusEvent e){
				if(getText().equals(Words)){
					setText("");
				}
				if(Words.equals("Password") || Words.equals("Old Password") || Words.equals("New Password")){
					setText(Password);
				}
		    }
		});
		setBorder(javax.swing.BorderFactory.createEmptyBorder());
		setBackground(new java.awt.Color(70,81,108));
		setForeground(new java.awt.Color(117,132,178));
		setBounds(x, y, width, height); 
	}
	public String getPassword(){
		return Password;
	}
}

class AbsoluteLabel extends JLabel{
	public AbsoluteLabel(String Words, int x, int y, int width, int height, int FontSize){
		setText(Words);
		setBounds(x, y, width, height);
		setFont(new Font("Helvetica", Font.BOLD, FontSize));
		setForeground(new java.awt.Color(73,210,146));
	}
	public AbsoluteLabel(String Words, int x, int y, int width, int height, int FontSize, boolean ErrorLabel){
		setText(Words);
		setBounds(x, y, width, height);
		setFont(new Font("Helvetica", Font.BOLD, FontSize));
		if(ErrorLabel)
			setForeground(new java.awt.Color(194,56,61));
		else{
			setForeground(new java.awt.Color(255,255,255));
			setFont(new Font("Helvetica", ~Font.BOLD, FontSize));
		}
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
	JLabel TitleTO = new AbsoluteLabel("TM", 330,200,440,430, 120, false);
	JLabel DescriptionTO = new AbsoluteLabel("An Agile Task Managing Application", 330,160,640,330, 18, false);
	static int LocalTimer = 0;
	public TM_GUI_Intro(JPanel frame, JFrame window){
		frame.setBackground(new java.awt.Color(0,0,0));//,0
		frame.setOpaque(true);
		frame.setLayout(null);
		frame.add(TitleTO); frame.add(DescriptionTO);
		TitleTO.setBounds(-550, 300, 500, 500);
		DescriptionTO.setBounds(-550, 300, 500, 500);
		ActionListener action = new ActionListener(){ 
			public void actionPerformed(ActionEvent event){
				LocalTimer++;
				switch(LocalTimer){
					case 4: TitleTO.setBounds(390, -10, 500, 500);
							/*window.dispose(); //CTRL-C
							window.setUndecorated(true);
							window.setBackground(new java.awt.Color(0,0,0,0));//window.setOpacity(0.5f);
							window.setVisible(true);*/
							break;
					case 8: //From 8-12 we are fading in the description
							DescriptionTO.setForeground(new java.awt.Color(255,255,255, 30));
							DescriptionTO.setBounds(340, 60, 500, 500);
							break;
					case 9:	DescriptionTO.setForeground(new java.awt.Color(255,255,255, 70));
							break;
					case 10:DescriptionTO.setForeground(new java.awt.Color(255,255,255, 140));
							break;
					case 11:DescriptionTO.setForeground(new java.awt.Color(255,255,255, 200));
							break;
					case 12:DescriptionTO.setForeground(new java.awt.Color(255,255,255, 255));
							break;
					case 15:DescriptionTO.setForeground(new java.awt.Color(255,255,255, 200));
							TitleTO.setForeground(new java.awt.Color(255,255,255, 200));
							break;
					case 16:DescriptionTO.setForeground(new java.awt.Color(255,255,255, 140));
							TitleTO.setForeground(new java.awt.Color(255,255,255, 140));
							break;
					case 17: //From 22-27 we are transitioning into the register screen so it's not an abrupt transition
							frame.setBackground(new java.awt.Color(10,10,10));
							DescriptionTO.setForeground(new java.awt.Color(255,255,255, 70));
							TitleTO.setForeground(new java.awt.Color(255,255,255, 70));
							break;
					case 18:frame.setBackground(new java.awt.Color(20,20,20));
							DescriptionTO.setForeground(new java.awt.Color(255,255,255, 30));
							TitleTO.setForeground(new java.awt.Color(255,255,255, 30));
							break;
					case 19:frame.setBackground(new java.awt.Color(30,30,30));
							DescriptionTO.setForeground(new java.awt.Color(255,255,255, 0));
							TitleTO.setForeground(new java.awt.Color(255,255,255, 0));
							break;
					case 20:frame.setBackground(new java.awt.Color(40,60,80));
							break;
					case 21:frame.setBackground(new java.awt.Color(59,68,91));
							break;
					case 22: //22 = 2.2 seconds for this intro.
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
	AbsoluteTextField Dev_PasswordTF = new AbsoluteTextField("Password", 110,240, 240,30);
	JTextField Man_NameTF = new AbsoluteTextField("Name", 640,120, 240,30);
	JTextField Man_EmailTF = new AbsoluteTextField("Email", 640,160, 240,30);
	JTextField Man_TeamTF = new AbsoluteTextField("Team", 640,200, 240,30);
	AbsoluteTextField Man_PasswordTF = new AbsoluteTextField("Password", 640,240, 240,30);
	JButton button_DevRegister = new AbsoluteTextButton("Register", 145,300, 160,45, 3, 1);
	JButton button_DevLogin = new AbsoluteTextButton("Login", 145,360, 160,45, 1, 2);
	JButton button_ManRegister = new AbsoluteTextButton("Register", 675,300, 160,45, 4, 3);
	JButton button_ManLogin = new AbsoluteTextButton("Login", 675,360, 160,45, 1, 4);
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
				frame.setBackground(new java.awt.Color(59,68,91));
				frame.removeAll();
				frame.revalidate();
				frame.repaint(2500);
				if((Integer)((JButton)e.getSource()).getClientProperty("ButtonNum") == 1){
					//DevRegister Code
					
					UserAccount newDev = new UserAccount("dev", Dev_NameTF.getText(),Dev_EmailTF.getText(), Dev_PasswordTF.getPassword(),Dev_TeamTF.getText());
					if (newDev.createAccount(newDev)) {				
						Test.userID = newDev.queryForId(newDev.username);
						Test.login = true;
					}
				}
				if((Integer)((JButton)e.getSource()).getClientProperty("ButtonNum") == 2){
					//DevLogIn Code
					
					Login newLogin = new Login(Dev_EmailTF.getText(), Dev_PasswordTF.getPassword());
					if (newLogin.authenticateUser(newLogin) == true) {
						Test.userID = newLogin.queryForId(newLogin.getUserName());
						Test.login = true; 
					}
					else {
						Test.login = false;
					}
					
				}
				if((Integer)((JButton)e.getSource()).getClientProperty("ButtonNum") == 3){
					//ManRegister Code
					
					UserAccount newMgr = new UserAccount("mgr", Man_NameTF.getText(),Man_EmailTF.getText(), Man_PasswordTF.getPassword(),Man_TeamTF.getText());
					if (newMgr.createAccount(newMgr)) {				
						Test.userID = newMgr.queryForId(newMgr.username);
						Test.login = true;
					}	
					
				}
				if((Integer)((JButton)e.getSource()).getClientProperty("ButtonNum") == 4){
					//ManLogIn Code
					
					Login newLogin = new Login(Man_EmailTF.getText(), Man_PasswordTF.getPassword());
					if (newLogin.authenticateUser(newLogin) == true) {
						Test.userID = newLogin.queryForId(newLogin.getUserName());
						Test.login = true; 
					}
					else {
						Test.login = false;
					}
					
					
				}
				RepaintTimer.stop();
				Test.MenuVar = 1; //All buttons go back to login screen.
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
	JLabel ErrorTO = new AbsoluteLabel("Error: Wrong Username/Password Combo", 330,210,440,130, 16, true);
	JButton button_LogIn = new AbsoluteTextButton("Log In", 400,300, 160,45, 2, 1);
	JButton button_GoBack = new AbsoluteTextButton("Register", 412,360, 138,25, 2, 2);
	JButton button_ChangePassword = new AbsoluteTextButton("Change Password", 412,400, 138,25, 2, 3);
	JTextField UsernameTF = new AbsoluteTextField("Email",360,150, 240,30);
	AbsoluteTextField PasswordTF = new AbsoluteTextField("Password", 360,200, 240,30);
	Component[] Comp = {LogInTO, button_LogIn, button_GoBack, button_ChangePassword, UsernameTF, PasswordTF};
	int LocalTimer = 0;
	
	public LogInScreen(JPanel frame, JFrame window){
		frame.setBackground(new java.awt.Color(59,68,91));
		new AddToFrame(frame, Comp);
		frame.setLayout(null);
		ActionListener RepaintScreen = new ActionListener(){ 
			public void actionPerformed(ActionEvent event){
				LocalTimer++;
				frame.repaint(500);
				frame.revalidate();
				switch(LocalTimer){
					case 0: 	LogInTO.setForeground(new java.awt.Color(73,210,146, 0));
								button_LogIn.setBackground(new java.awt.Color(70,81,108, 0));
								button_GoBack.setBackground(new java.awt.Color(70,81,108, 0));
								button_ChangePassword.setBackground(new java.awt.Color(70,81,108, 0));
								UsernameTF.setBackground(new java.awt.Color(70,81,108, 0));
								PasswordTF.setBackground(new java.awt.Color(70,81,108, 0));
								break;
					case 1: 	LogInTO.setForeground(new java.awt.Color(73,210,146, 70));
								button_LogIn.setBackground(new java.awt.Color(70,81,108, 70));
								button_GoBack.setBackground(new java.awt.Color(70,81,108, 70));
								button_ChangePassword.setBackground(new java.awt.Color(70,81,108, 70));
								UsernameTF.setBackground(new java.awt.Color(70,81,108, 70));
								PasswordTF.setBackground(new java.awt.Color(70,81,108, 70));
								window.setTitle("TM              L");
								break;
					case 2: 	LogInTO.setForeground(new java.awt.Color(73,210,146, 130));
								button_LogIn.setBackground(new java.awt.Color(70,81,108, 130));
								button_GoBack.setBackground(new java.awt.Color(70,81,108, 130));
								button_ChangePassword.setBackground(new java.awt.Color(70,81,108, 130));
								UsernameTF.setBackground(new java.awt.Color(70,81,108, 130));
								PasswordTF.setBackground(new java.awt.Color(70,81,108, 130));
								window.setTitle("TM              LO");
								break;
					case 3: 	LogInTO.setForeground(new java.awt.Color(73,210,146, 170));
								button_LogIn.setBackground(new java.awt.Color(70,81,108, 170));
								button_GoBack.setBackground(new java.awt.Color(70,81,108, 170));
								button_ChangePassword.setBackground(new java.awt.Color(70,81,108, 170));
								UsernameTF.setBackground(new java.awt.Color(70,81,108, 170));
								PasswordTF.setBackground(new java.awt.Color(70,81,108, 170));
								window.setTitle("TM              LOG");
								break;
					case 4: 	LogInTO.setForeground(new java.awt.Color(73,210,146, 200));
								button_LogIn.setBackground(new java.awt.Color(70,81,108, 200));
								button_GoBack.setBackground(new java.awt.Color(70,81,108, 200));
								button_ChangePassword.setBackground(new java.awt.Color(70,81,108, 200));
								UsernameTF.setBackground(new java.awt.Color(70,81,108, 200));
								PasswordTF.setBackground(new java.awt.Color(70,81,108, 200));
								window.setTitle("TM              LOGI");
								break;
					case 5: 	LogInTO.setForeground(new java.awt.Color(73,210,146, 255));
								button_LogIn.setBackground(new java.awt.Color(70,81,108, 255));
								button_GoBack.setBackground(new java.awt.Color(70,81,108, 255));
								button_ChangePassword.setBackground(new java.awt.Color(70,81,108, 255));
								UsernameTF.setBackground(new java.awt.Color(70,81,108, 255));
								PasswordTF.setBackground(new java.awt.Color(70,81,108, 255));
								window.setTitle("TM              LOGIN");
								break;
					case 6:		window.setTitle("TM              LOGIN S");
								break;
					case 7:		window.setTitle("TM              LOGIN SC");
								break;
					case 8:		window.setTitle("TM              LOGIN SCRE");
								break;
					case 9:		window.setTitle("TM              LOGIN SCREE");
								break;
					case 10:	window.setTitle("TM              LOGIN SCREEN");
								LocalTimer = 501;
								break;
					case 25: 	ErrorTO.setForeground(new java.awt.Color(194,56,61, 220));
								break;
					case 26: 	ErrorTO.setForeground(new java.awt.Color(194,56,61, 200));
								break;
					case 27: 	ErrorTO.setForeground(new java.awt.Color(194,56,61, 170));
								break;
					case 28: 	ErrorTO.setForeground(new java.awt.Color(194,56,61, 130));
								break;
					case 29: 	ErrorTO.setForeground(new java.awt.Color(194,56,61, 70));
								break;
					case 30: 	ErrorTO.setForeground(new java.awt.Color(194,56,61, 255));
								frame.remove(ErrorTO); LocalTimer = 500;
								break;
					default:	break;
				}
			}
		};
		Timer RepaintTimer = new Timer(100, RepaintScreen); //100 milliseconds
		RepaintTimer.start();
		
		ActionListener action = new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				String String_Username = UsernameTF.getText();
				String String_Password = PasswordTF.getPassword();
				if((Integer)((JButton)e.getSource()).getClientProperty("ButtonNum") == 1){
					//BACK END TEAM
					Login newLogin = new Login(String_Username, String_Password);
					if(newLogin.authenticateUser(newLogin) == false)  {
						Test.login = false;
						//Test.MenuVar = 1;
						frame.add(ErrorTO);
						LocalTimer = 11;
					}
					else {
						if (newLogin.authenticateUser(newLogin) == true) { 
							Test.userID = newLogin.queryForId(String_Username);
							Test.login = true;
							String role = newLogin.getUserRole(Test.userID);
							System.out.println("Logged in as "+role);
							switch (role) {
								case "developer":
									RemoveEverything(frame, window);
									RepaintTimer.stop();
									Test.MenuVar = 3; 
									break;
								case "manager":
									RemoveEverything(frame, window);
									RepaintTimer.stop();
									Test.MenuVar = 4; 
									break;		
							}
						}

					}
				  }
				if((Integer)((JButton)e.getSource()).getClientProperty("ButtonNum") == 2){
					RemoveEverything(frame, window);
					RepaintTimer.stop();
					Test.MenuVar = 2; //Register
				}
				if((Integer)((JButton)e.getSource()).getClientProperty("ButtonNum") == 3){
					RemoveEverything(frame, window);
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
	private void RemoveEverything(JPanel frame, JFrame window){
		window.setTitle("TM");
		frame.setBackground(new java.awt.Color(59,68,91));
		frame.removeAll();
		frame.revalidate();
		frame.repaint();
	}
}

class ChangePasswordScreen{
	JLabel TitleTO = new AbsoluteLabel("Change Password", 345,-5,355,130, 32);
	JTextField UsernameTF = new AbsoluteTextField("Email",360,150, 240,30);
	AbsoluteTextField OldPasswordTF = new AbsoluteTextField("Old Password", 360,200, 240,30);
	AbsoluteTextField NewPasswordTF = new AbsoluteTextField("New Password", 360,250, 240,30);
	JButton button_ChangePassword = new AbsoluteTextButton("Change Password", 400,300, 160,45, 2, 1);
	JButton button_GoBack = new AbsoluteTextButton("Go Back", 440,370, 85,25, 2, 2);
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
					String String_Username = UsernameTF.getText();
					String String_OldPassword = OldPasswordTF.getPassword();
					String String_NewPassword = NewPasswordTF.getPassword();
					//BACK END TEAM
					UserAccount ua = new UserAccount(String_Username, String_OldPassword);
					if (ua.updatePassword(String_Username, String_OldPassword, String_NewPassword)==true) {
						System.out.println("Change Password Successful");
					}
				}
				if((Integer)((JButton)e.getSource()).getClientProperty("ButtonNum") == 2){
					frame.setBackground(new java.awt.Color(59,68,91));
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
		
		setBackground(new java.awt.Color(59,68,91));

		//ACTION LISTENER CODE
		ActionListener action = new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				SwitchFocus(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")));
				if(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")) == 3){
					//LogOut
					main.setBackground(new java.awt.Color(59,68,91));
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
		//backend code
		Developer dev = new Developer();
		String hours[][] = dev.getDevHours(Test.userID);
		if (hours ==null) {
			table = new JTable(Data, Cnames);
		}
		else {
			table = new JTable(hours, Cnames);
		}
		//end be code
		//
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
					
					TaskLog task = new TaskLog(String_TaskName, String_ProjectName, String_Description, Test.userID);
					if (task.insertTask(task)) {
						System.out.println("Task "+ String_TaskName + " started.");
					}
					else {
						System.out.println("Error with starting task "+String_TaskName);
					}
					
					
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
					
					TaskLog task = new TaskLog(String_TaskName, String_ProjectName, String_Description, Test.userID);
					if (task.stopTask(Test.userID, task.taskName, task.projectName, task.description)) {
						System.out.println("Task "+ String_TaskName + " stopped.");
					}
					else {
						System.out.println("Error with stopping task "+String_TaskName);
					}
					
					
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
		//be code
		Developer dev = new Developer();
		String DevProjects[][] = dev.getDevProjects(Test.userID);
		if (DevProjects==null) {
			table = new JTable(Data, Cnames);
		}
		else {
			table = new JTable(DevProjects, Cnames);
		}
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
		//Buttons[x] = new CButton(/*BACK END TEAM: "" + ProjectName[x]*/"Some Project",false, 50);
		//back end code
		Developer dev = new Developer();
		String ProjectName[]=dev.getAllDevReports(Test.userID);
		Buttons = new CButton[ProjectName.length];
		//Buttons = new CButton[40];
		for(int x = 0 ; x < Buttons.length ; x++){
			Buttons[x] = new CButton(ProjectName[x],false, 50);
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
		//be code
		ProjectsLabel = new Header("Project " + Project + " Report");
		Developer dev = new Developer();
		String ProjectReport[][] = dev.getDevReport(Test.userID, Project);
		table = new JTable(ProjectReport, Cnames);

		//end be code
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
		Manager mgr = new Manager();
		String[][] hours= mgr.getMgrHours(Test.userID);
		if (hours == null) {
			table = new JTable(Data, Cnames);		
		}
		else {
			table = new JTable(hours, Cnames);
		}

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
	Manager mgr = new Manager();
	String DevList[]= mgr.getMgrDevNames(Test.userID); 
	//String DevList[]={"james","jingle","heimer","schmidt","and josh"};        
	JComboBox<String> PeopleOnProject = new PComboBox<>("Developer List", DevList);
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
					//   BACK END TEAM
					String String_ProjectName = ProjectName.getText();
					int budgetHours = Integer.parseInt(BudgetHours.getText());
					int devNo = PeopleOnProject.getSelectedIndex();
					Project P = new Project(String_ProjectName, budgetHours, Test.userID, DevList[devNo]);
					P.createProject(P);
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
		Manager mgr = new Manager();
		String projects[][] = mgr.getProjectsMgr(Test.userID);
		if (projects==null) {
			table = new JTable(Data, Cnames);
		}
		else {
			table = new JTable(projects, Cnames);
		}
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
		Manager mgr = new Manager();
		String ProjectName[] = mgr.getMgrProjects(Test.userID);
		//Filling Buttons
		Buttons_Projects = new CButton[ProjectName.length];
		for(int x = 0 ; x < Buttons_Projects.length ; x++){
			Buttons_Projects[x] = new CButton(ProjectName[x],false, 50);
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
		Manager man = new Manager();
		String [] developerName = man.getMgrDevNames(Test.userID);
		Buttons_Developers = new CButton[developerName.length];
		for(int x = 0 ; x < Buttons_Developers.length ; x++){
			Buttons_Developers[x] = new CButton(developerName[x],false, 50);//<html><font face = helvetica size = 4> </font></html>
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
		
		for(int x = 0; x < Buttons_Projects.length; x++){
				Columns.add(Buttons_Projects[x],cs1);
				cs1.gridy++;
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

		for(int x = 0; x < Buttons_Developers.length; x++){
				Columns2.add(Buttons_Developers[x],cs2);
				cs2.gridy++;
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
		Manager mgr = new Manager();
		String devReport[][] = mgr.TasksForDeveloper(dev);
		table = new JTable(devReport, Cnames);
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
		Manager mgr = new Manager();
		String projReport[][] = mgr.TasksForProjName(project);
		table = new JTable(projReport, Cnames);
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

