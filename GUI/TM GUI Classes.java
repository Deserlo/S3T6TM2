import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;

class TM_Frame extends JFrame{
	public TM_Frame(String title){
		setTitle(title);
		setVisible(true);
		//setLayout(new BorderLayout());
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
		setBorder(javax.swing.BorderFactory.createEmptyBorder());
		setFont(new Font("Helvetica",Font.ITALIC, 20));
    }
}
class CPanel extends JPanel{
	public CPanel(){
		setOpaque(false);
	}
}
class SButton extends JButton{
	public SButton(String s){
		setText(s);
		//setContentAreaFilled(false);
		setFocusPainted(false);
		setBorderPainted(false);
		setForeground(new java.awt.Color(117,132,178));
		setBackground(new java.awt.Color(70,81,108));
	}
}
class CButton extends JButton{
	public CButton(String s){
		setText(s);
		//setContentAreaFilled(false);
		setFocusPainted(false);
		setBorderPainted(false);
		setForeground(new java.awt.Color(117,132,178));
		setBackground(new java.awt.Color(0,0,0));
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
				}
				if((Integer)((JButton)e.getSource()).getClientProperty("ButtonNum") == 2){
					//DevLogIn Code
				}
				if((Integer)((JButton)e.getSource()).getClientProperty("ButtonNum") == 3){
					//ManRegister Code
				}
				if((Integer)((JButton)e.getSource()).getClientProperty("ButtonNum") == 4){
					//ManLogIn Code
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
			}
		};
		button_LogIn.addActionListener(action);
		frame.setOpaque(true);
	}
}
class DevHoursScreen{
	JLabel LogInTO = new AbsoluteLabel("Logging a Task", 430,-5,255,130, 32);
	JButton button_StartTask = new AbsoluteTextButton("Start", 360,360, 160,45, false, 1, 1);
	JButton button_StopTask = new AbsoluteTextButton("Stop", 560,360, 160,45, false, 1, 2);
	JTextField TaskTF = new AbsoluteTextField("Task name",420,200, 240,30);
	JTextField ProjectTF = new AbsoluteTextField("Project name", 420,150, 240,30);
	public DevHoursScreen(JPanel frame){
		frame.setBackground(new java.awt.Color(59,68,91));
		frame.add(LogInTO); frame.add(button_StartTask);
		frame.add(button_StopTask);
		frame.add(TaskTF); frame.add(ProjectTF);
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
					//Start Task Code
				}
				if((Integer)((JButton)e.getSource()).getClientProperty("ButtonNum") == 2){
					//Stop Task Code
				}
			}
		};
		button_StartTask.addActionListener(action);
		button_StopTask.addActionListener(action);
		frame.setOpaque(true);
	}
}


class SidePanel extends JPanel{
	JButton Hours = new SButton("<html><font face = helvetica size = 6> Hours </font></html>");
	JButton Projects = new SButton("<html><font face = helvetica size = 6> Projects </font></html>");
	JButton Reports = new SButton("<html><font face = helvetica size = 6> Reports </font></html>");
	JButton LogOut = new SButton("<html><font face = helvetica size = 6> Log Out </font></html>");
	JPanel pan1 = new CPanel();
	JPanel pan2 = new CPanel();
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
		add(pan1,c);
		
		c.gridy=4;
		add(pan2,c);
		
		c.gridy=5;
		add(LogOut,c);
		
		setBackground(new java.awt.Color(70,81,108));

		//FOCUS LISTENER CODE
		Hours.addFocusListener(new FocusListener(){
            public void focusGained(FocusEvent e){
				Hours.setForeground(new java.awt.Color(73,210,146));
            }
            public void focusLost(FocusEvent e){
                Hours.setForeground(new java.awt.Color(117,132,178));
            }
        });
		
		Projects.addFocusListener(new FocusListener(){
            public void focusGained(FocusEvent e){
				Projects.setForeground(new java.awt.Color(73,210,146));
            }
            public void focusLost(FocusEvent e){
                Projects.setForeground(new java.awt.Color(117,132,178));
            }
        });
		
		Reports.addFocusListener(new FocusListener(){
            public void focusGained(FocusEvent e){
				Reports.setForeground(new java.awt.Color(73,210,146));
            }
            public void focusLost(FocusEvent e){
                Reports.setForeground(new java.awt.Color(117,132,178));
            }
        });
		
		//ACTION LISTENR CODE
		Hours.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				main.remove(((BorderLayout)main.getLayout()).getLayoutComponent(BorderLayout.CENTER));
				main.add(s[0]);
				main.revalidate();
				main.repaint();
			}
		});
		
		Projects.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				main.remove(((BorderLayout)main.getLayout()).getLayoutComponent(BorderLayout.CENTER));
				main.add(s[1]);
				main.revalidate();
				main.repaint();
			}
		});
		
		Reports.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				main.remove(((BorderLayout)main.getLayout()).getLayoutComponent(BorderLayout.CENTER));
				main.add(s[2]);
				main.revalidate();
				main.repaint();
			}
		});
		
		LogOut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				f.removeAll();
				f.add(s[3]);
				f.revalidate();
				f.repaint();
			}
		});
		
	}
}

class Hours extends JPanel{
	JLabel LogATask = new Header("Logging a Task");
	JButton Start = new CButton("Start");
	JButton Stop = new CButton("Stop");
	JTextField TaskName = new PTextField("Task Name");
	JTextField ProjectName = new PTextField("Project Name");
	JTextArea Description = new PTextArea("Description");
	JPanel[] clear = new JPanel[3];

	
	public Hours(){
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		add(LogATask,c);
		
		c.gridx=1;
		c.gridy=1;
		add(TaskName,c);
		
		c.gridx = 2;
		add(ProjectName,c);
		
		c.gridx = 3;
		add(Description);
		
		for(JPanel pan : clear){
			pan = new CPanel();
			c.gridx++;
			add(pan);
		}
		
		c.gridx = 8;
		add(Start,c);
		
		c.gridy = 4;
		add(Stop,c);
		setBackground(new java.awt.Color(70,81,108));

	}
}