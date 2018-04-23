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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class PTextField extends JTextField{ // pulled from stack overflow
	public PTextField(final String proptText){
        super(proptText);
        addFocusListener(new FocusListener(){
            public void focusLost(FocusEvent e){
                if(getText().isEmpty()){
                    setText(proptText);
                }
            }
            public void focusGained(FocusEvent e){
                if(getText().equals(proptText)){
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
		getViewport().		setBackground(new java.awt.Color(70,81,108));

		getVerticalScrollBar().setForeground(new java.awt.Color(117,132,178));
		getVerticalScrollBar().setBackground(new java.awt.Color(70,81,108));
		getHorizontalScrollBar().setForeground(new java.awt.Color(117,132,178));
		getHorizontalScrollBar().setBackground(new java.awt.Color(70,81,108));
	}
}

class PTextArea extends JTextArea{ // pulled from stack overflow
	public PTextArea(final String proptText){
        super(proptText);
        addFocusListener(new FocusListener(){
            public void focusLost(FocusEvent e){
                if(getText().isEmpty()){
                    setText(proptText);
                }
            }
            public void focusGained(FocusEvent e){
                if(getText().equals(proptText)){
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
		//setContentAreaFilled(false);
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
	public Header (String s){
		setText(s);
		setFont(new Font("Helvetica", Font.BOLD, 30));
		setForeground(new java.awt.Color(73,210,146));
	}
}

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
		JLabel label_top;
		ImageIcon icon_top;
		JLabel label_bottom;
		ImageIcon icon_bottom;
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
						frame.setBackground(new java.awt.Color(0,0,0)); 
						label_top.setBackground(new java.awt.Color(255,255,255, 25)); //RGB-A, Alpha(0-255) 0 = Transparent
						label_bottom.setBackground(new java.awt.Color(255,255,255, 25));
						label_top.setBounds(400, 140, 150, 150);
						label_bottom.setBounds(400, 199, 150, 150);
						break;
					case 6:
						label_top.setBackground(new java.awt.Color(255,255,255, 50));
						label_bottom.setBackground(new java.awt.Color(255,255,255, 50));
						break;
					case 7:
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
					case 13:
						label_top.setBounds(405, 140, 150, 150);
						label_bottom.setBounds(395, 199, 150, 150);
						break;
					case 14:
						label_top.setBounds(410, 140, 150, 150);
						label_bottom.setBounds(390, 199, 150, 150);
						break;
					case 15:
						label_top.setBounds(405, 140, 150, 150);
						label_bottom.setBounds(395, 199, 150, 150);
						break;
					case 16:
						label_top.setBounds(400, 140, 150, 150);
						label_bottom.setBounds(400, 199, 150, 150);
						break;
					case 17:
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
					case 22:
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
					case 27: //27 = 2.7 seconds.
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
	JLabel ManTO = new AbsoluteLabel("Manager", 700,-10,195,130, 32);
	JTextField Dev_NameTF = new AbsoluteTextField("Name", 110,120, 240,30);
	JTextField Dev_EmailTF = new AbsoluteTextField("Email", 110,160, 240,30);
	JTextField Dev_TeamTF = new AbsoluteTextField("Team", 110,200, 240,30);
	JTextField Dev_PasswordTF = new AbsoluteTextField("Password", 110,240, 240,30);
	JTextField Man_NameTF = new AbsoluteTextField("Name", 660,120, 240,30);
	JTextField Man_EmailTF = new AbsoluteTextField("Email", 660,160, 240,30);
	JTextField Man_TeamTF = new AbsoluteTextField("Team", 660,200, 240,30);
	JTextField Man_PasswordTF = new AbsoluteTextField("Password", 660,240, 240,30);
	JButton button_DevRegister = new AbsoluteTextButton("Register", 140,300, 160,45, false, 1, 1);
	JButton button_DevLogin = new AbsoluteTextButton("Login", 140,360, 160,45, false, 1, 2);
	JButton button_ManRegister = new AbsoluteTextButton("Register", 690,300, 160,45, false, 3, 3);
	JButton button_ManLogin = new AbsoluteTextButton("Login", 690,360, 160,45, false, 1, 4);
	
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
		frame.revalidate();
		frame.repaint(500);
		
		ActionListener action = new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				frame.setBackground(new java.awt.Color(0,0,0));
				frame.removeAll();
				frame.revalidate();
				frame.repaint(500);
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
	JTextField EmailTF = new AbsoluteTextField("Email",360,200, 240,30);
	JTextField PasswordTF = new AbsoluteTextField("Password", 360,150, 240,30);
	
	public LogInScreen(JPanel frame){
		frame.setBackground(new java.awt.Color(59,68,91));
		frame.add(LogInTO); frame.add(button_LogIn);
		frame.add(EmailTF); frame.add(PasswordTF);
		frame.setLayout(null);
		frame.revalidate();
		frame.repaint(500);
		
		ActionListener action = new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				frame.setBackground(new java.awt.Color(0,0,0));
				frame.removeAll();
				frame.revalidate();
				frame.repaint();
				Test.MenuVar = 3; //Developer atm
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
		};
		button_LogIn.addActionListener(action);
		frame.setOpaque(true);
	}
}

class SidePanel extends JPanel{
	JButton Hours = new CButton("<html><font face = helvetica size = 5> Hours </font></html>", true, 0);
	JButton Projects = new CButton("<html><font face = helvetica size = 5> Projects </font></html>", true, 1);
	JButton Reports = new CButton("<html><font face = helvetica size = 5> Reports </font></html>", true, 2);
	JButton LogOut = new CButton("<html><font face = helvetica size = 5> Log Out </font></html>", true, 3);
	//ImageIcon icon_line;
	
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
		//icon_line = new ImageIcon(Test.class.getResource("art/Line.png"));
		
		//GRAPHICS CODE
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx=0;
		c.gridy=0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx=1.0;
		c.weighty=1.0;
		
		c.ipadx = 60;
		c.ipady = 15;
		c.gridy=0;
		add(new CPanel(),c);
		
		c.ipadx = 9;
		c.gridx=1;add(new CPanel(),c);
		//c.gridx=2; add(new CPanel(),c);
		c.ipadx = 60; c.ipady = 25;
		c.gridx=0;
		c.gridy=1;
		add(Hours,c);
		
		c.ipadx = 9;
		c.gridx=1;add(new CPanel(),c);
		//c.gridx=2; add(new CPanel(),c);
		c.ipadx = 60;

		c.gridx=0; c.gridy=2;
		add(Projects,c);
		
		/*c.ipadx = 9;
		c.gridx=1;add(new CPanel(),c);
		c.gridx=2; add(new JLabel(icon_line), c);
		c.ipadx = 60;*/
		c.gridx=0; c.gridy=3;
		add(Reports,c);
		
		c.ipady = 200;
		c.gridy=4;
		add(new CPanel(),c);
		
		c.ipady = 25;
		c.gridy=5;
		add(LogOut,c);
		
		c.ipady = 15;
		c.gridy=6;
		add(new CPanel(),c);
		
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
					//main.remove(((BorderLayout)main.getLayout()).getLayoutComponent(BorderLayout.CENTER));
					main.setBackground(new java.awt.Color(0,0,0));
					main.setVisible(false);
					main.setEnabled(false);
					main.revalidate();
					main.repaint();
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

class Hours extends JPanel{
	JLabel LogATask = new Header("Logging a Task");
	JButton Start = new CButton("Start", false, 1);
	JButton Stop = new CButton("Stop", false, 2);
	JTextField TaskName = new PTextField("Task Name");
	JTextField ProjectName = new PTextField("Project Name");
	JTextArea Description = new PTextArea("Description");
	JScrollPane temp = new JScrollPane(Description);
	JScrollPane Scroll = new CScrollPane();
	JPanel[] clear = new JPanel[3];
	
	public Hours(){
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx=1.0;
		c.weighty=1.0;
		c.ipadx = 100;
		c.ipady = 20;
		
		c.gridx = 3;
		c.gridy = 0;
		add(LogATask,c);
		
		c.gridx=3;
		c.gridy=1;
		add(TaskName,c);
		
		c.gridx = 3;
		c.gridy=2;
		add(ProjectName,c);
		
		c.gridx = 3;
		c.gridy = 3;
		c.ipady =  60;
		Scroll.setViewport(temp.getViewport());
		add(Scroll,c);
		
		for(JPanel pan : clear){
			pan = new CPanel();
			c.gridx++;
			add(pan);
		}
		
		c.fill = GridBagConstraints.NONE;
		c.gridx = 3;
		c.gridy = 4;
		c.ipady = 20;
		add(Start,c);
		
		c.gridx = 4;
		c.gridy = 4;
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
	JButton log = new CButton("Log Task",false,1);
	
	public ProjectsDev(String[][] Data, Container f, JPanel main, JPanel[] s){
		table = new JTable(Data, Cnames);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.weightx=1.0;
		c.weighty=1.0;
		c.ipadx = 100;
		c.ipady = 20;
		
		c.gridx = 0;
		c.gridy = 0;
		add(ProjectsLabel,c);
		
		c.ipadx = 450;
		c.ipady = 325;
		c.gridy = 1;
		table.setEnabled(false);
		table.setBackground(new java.awt.Color(117,132,178));
		temp = new JScrollPane(table);
		temp.getViewport().setBackground(new java.awt.Color(59,68,91));
		Scroll.setViewport(temp.getViewport());
		add(Scroll,c);
		
		c.ipadx = 100;
		c.ipady = 20;
		c.gridy = 2;
		add(log,c);
		
		setBackground(new java.awt.Color(59,68,91));
		
		//
		//
		// NEED ACTIONLISTENER;
		// link button to Hours panel
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
		int y = 40;
		Buttons = new CButton[y];
		for(int x = 0 ; x < Buttons.length ; x++){
			Buttons[x] = new CButton("Some Project",false, 50);
		}
		for(int x = 0 ; x < Buttons.length ; x++){
			Buttons[x] = new CButton("Some Project",false, 50);
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
		c.ipadx = 100;
		c.ipady = 20;
		
		c.gridx = 0;
		c.gridy = 0;
		add(ReportsLabel,c);

		Columns.setLayout(new GridBagLayout());
		Columns2.setLayout(new GridBagLayout());
		
		GridBagConstraints cs1 = new GridBagConstraints();
		GridBagConstraints cs2 = new GridBagConstraints();
		
		cs1.ipady = 30;
		cs2.ipady = 30;
		cs1.ipadx = 125;
		cs2.ipadx = 125;
		cs1.gridx = 0;
		cs2.gridx = 0;
		cs1.gridy = 0;
		cs2.gridx = 0;
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

		c.ipadx = 550;
		c.ipady = 350;
		c.gridy = 1;
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
	JButton log = new CButton("Log Task",false,1);
	
	public ProjectReportDev(String[][] Data,String Project, Container f, JPanel main, JPanel[] s){
		ProjectsLabel = new Header("Project " + Project + " Report");
		table = new JTable(Data, Cnames);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.weightx=1.0;
		c.weighty=1.0;
		c.ipadx = 100;
		c.ipady = 20;
		
		c.gridx = 0;
		c.gridy = 0;
		add(ProjectsLabel,c);
		
		c.ipadx = 450;
		c.ipady = 325;
		c.gridy = 1;
		table.setEnabled(false);
		table.setBackground(new java.awt.Color(117,132,178));
		temp = new JScrollPane(table);
		temp.getViewport().setBackground(new java.awt.Color(59,68,91));
		Scroll.setViewport(temp.getViewport());
		add(Scroll,c);
		
		c.ipadx = 100;
		c.ipady = 20;
		c.gridy = 2;
		add(log,c);
		
		setBackground(new java.awt.Color(59,68,91));
		
		//
		//
		// NEED ACTIONLISTENER;
		// link button to Hours panel
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

