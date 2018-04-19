import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;

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
		setForeground(new java.awt.Color(117,132,178));
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
				if(LocalTimer == 5){
					frame.setBackground(new java.awt.Color(0,0,0));
					label_top.setForeground(new java.awt.Color(255,255,255, 150));
					label_top.setBounds(400, 140, 150, 150);
					label_bottom.setBounds(400, 199, 150, 150);
				}
				else if(LocalTimer == 18){
					label_top.setBounds(-550, 300, 150, 150);
					label_bottom.setBounds(-550, 300, 150, 150);
					//label.setIcon(new ImageIcon(Test.class.getResource("art/IntroAnimation/6.jpg")));
				}
				else if(LocalTimer == 26){
					frame.setBackground(new java.awt.Color(10,10,10));
					//label.setIcon(new ImageIcon(Test.class.getResource("art/black.jpg")));
				}
				else if(LocalTimer == 31){
					frame.setBackground(new java.awt.Color(20,20,20));
					//label.setIcon(new ImageIcon(Test.class.getResource("art/IntroAnimation/10.jpg")));
				}
				else if(LocalTimer == 32){
					frame.setBackground(new java.awt.Color(30,30,30));
					//label.setIcon(new ImageIcon(Test.class.getResource("art/IntroAnimation/11.jpg")));
				}
				else if(LocalTimer == 33){
					frame.setBackground(new java.awt.Color(40,60,80));
					//label.setIcon(new ImageIcon(Test.class.getResource("art/IntroAnimation/12.jpg")));
				}
				else if(LocalTimer == 34){ //35 = 3.5 seconds
					frame.setBackground(new java.awt.Color(59,68,91));
					//label.setIcon(new ImageIcon(Test.class.getResource("art/IntroAnimation/13.jpg")));
				}
				else if(LocalTimer == 35){
					frame.removeAll();
					frame.revalidate();
					//frame.repaint();
					Test.MenuVar = 2; 
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
		frame.repaint();
		
		ActionListener action = new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				frame.setBackground(new java.awt.Color(0,0,0));
				frame.removeAll();
				frame.revalidate();
				frame.repaint();
				Test.MenuVar = ((Integer)((JButton)e.getSource()).getClientProperty("MenuSwitch")); 
				if((Integer)((JButton)e.getSource()).getClientProperty("ButtonNum") == 1){
					//DevRegister Code
					UserAccount newDev = new UserAccount("dev", Dev_NameTF.getText(),Dev_EmailTF.getText(), Dev_PasswordTF.getText(),Dev_TeamTF.getText());
					if (newDev.createAccount(newDev)) {				
						Test.UserID = newDev.queryForId(newDev.username);
						Test.loggedIn = true;
						Test.MenuVar = 3; 
					}
				}
				if((Integer)((JButton)e.getSource()).getClientProperty("ButtonNum") == 2){
					//DevLogIn Code
					Login newLogin = new Login(Dev_EmailTF.getText(), Dev_PasswordTF.getText());
					if (newLogin.authenticateUser(newLogin) == true) {
						Test.UserID = newLogin.queryForId(newLogin.getUserName());
						Test.loggedIn = true;
						Test.MenuVar = 3; 
					}
					else {
						Test.loggedIn = false;
						Test.MenuVar = 1; 
					}
				}
				if((Integer)((JButton)e.getSource()).getClientProperty("ButtonNum") == 3){
					//ManRegister Code
					UserAccount newMgr = new UserAccount("mgr", Man_NameTF.getText(),C.getText(), Man_PasswordTF.getText(),Man_TeamTF.getText());
					if (newMgr.createAccount(newMgr)) {				
						Test.UserID = newMgr.queryForId(newMgr.username);
						Test.loggedIn = true;
						//Test.MenuVar = ?; 
					}	
				}
				if((Integer)((JButton)e.getSource()).getClientProperty("ButtonNum") == 4){
					//ManLogIn Code
					Login newLogin = new Login(C.getText(), Man_PasswordTF.getText());
					if (newLogin.authenticateUser(newLogin) == true) {
						Test.UserID = newLogin.queryForId(newLogin.getUserName());
						Test.loggedIn = true;
						//Test.MenuVar = ; 
					}
					else {
						Test.loggedIn = false;
						Test.MenuVar = 1; 
					}
					
				}
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
		frame.repaint();
		
		ActionListener action = new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				frame.setBackground(new java.awt.Color(0,0,0));
				frame.removeAll();
				frame.revalidate();
				frame.repaint();
				Test.MenuVar = ((Integer)((JButton)e.getSource()).getClientProperty("MenuSwitch"));
				//log in code
				Login newLogin = new Login(EmailTF.getText(), PasswordTF.getText());
				if (newLogin.authenticateUser(newLogin) == true) {
					Test.UserID = newLogin.queryForId(newLogin.getUserName());
					Test.loggedIn = true;
					//Test.MenuVar = ?
				}
				else {
					Test.loggedIn = false;
					Test.MenuVar = 1; //?
				}
			}
		};
		button_LogIn.addActionListener(action);
		frame.setOpaque(true);
	}
}

class SidePanel extends JPanel{
	JButton Hours = new CButton("<html><font face = helvetica size = 6> Hours </font></html>", true, 0);
	JButton Projects = new CButton("<html><font face = helvetica size = 6> Projects </font></html>", true, 1);
	JButton Reports = new CButton("<html><font face = helvetica size = 6> Reports </font></html>", true, 2);
	JButton LogOut = new CButton("<html><font face = helvetica size = 6> Log Out </font></html>", true, 3);
	JPanel pan_empty1 = new CPanel();
	JPanel pan_empty2 = new CPanel();
	public SidePanel(Container f, JPanel main, JPanel[] s){
		//NOTE ABOUT PARAMETERS:
		
		/*main will be the panel that side panel lies on
		  array s will contain all the different center panels
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
		c.gridx=0;
		c.gridy=0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx=1.0;
		c.weighty=1.0;
		c.ipadx = 80;
		c.ipady = 40;
		add(Hours,c);
		
		c.gridy=1;
		add(Projects,c);
		
		c.gridy=2;
		add(Reports,c);
		
		c.gridy=3;
		add(pan_empty1,c);
		
		c.gridy=4;
		add(pan_empty2,c);
		
		c.gridy=5;
		add(LogOut,c);
		
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
				main.remove(((BorderLayout)main.getLayout()).getLayoutComponent(BorderLayout.CENTER));
				if(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")) == 3){
					//LogOut
					main.setBackground(new java.awt.Color(0,0,0));
					main.removeAll();
					main.revalidate();
					main.repaint();
					Test.MenuVar = 2;
				}
				else
					main.add(s[((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue"))]);
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
	JPanel pan_empty1 = new CPanel();
	JPanel pan_empty2 = new CPanel();
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
				}
				if(((Integer)((JButton)e.getSource()).getClientProperty("ExtraValue")) == 2){
					//Stop Code goes here
					// name.getText() is the command to get the string;
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
	JButton log = new CButton("Log Task",false,0);
	
	public ProjectsDev(String[][] Data){
		table = new JTable(Data, Cnames);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//c.fill = GridBagConstraints.HORIZONTAL;
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
		temp = new JScrollPane(table);
		Scroll.setViewport(temp.getViewport());
		add(Scroll,c);
		
		c.ipadx = 100;
		c.ipady = 20;
		c.gridy = 2;
		add(log,c);
		
		
		setBackground(new java.awt.Color(59,68,91));
	}
}
class Reports extends JPanel{
	JLabel ReportsLabel = new Header("Reports");
	public Reports(){
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx=1.0;
		c.weighty=1.0;
		c.ipadx = 100;
		c.ipady = 20;
		
		c.gridx = 0;
		c.gridy = 0;
		add(ReportsLabel,c);

		
		
		setBackground(new java.awt.Color(59,68,91));
	}
}
