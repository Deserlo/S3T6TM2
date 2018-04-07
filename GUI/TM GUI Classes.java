import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;

class TM_Frame extends JFrame{
	public TM_Frame(String title){
		setTitle(title);
		//setSize(1024,768);
		setVisible(true);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class TM_LogIn extends JPanel{
	JTextField UsernameT = new JTextField();
	JPasswordField PasswordT = new JPasswordField();
	JLabel Username = new JLabel("Username: ");
	JLabel Password = new JLabel("Password: ");
	JButton Log = new JButton("Log In");
	JButton CreateAccount = new JButton("Create Account");
	JPanel pan1 = new CPanel();
	JPanel pan2 = new CPanel();
	JPanel pan3 = new CPanel();
	
	public TM_LogIn(){
		
		UsernameT.setColumns(10);
		PasswordT.setColumns(10);
		setLayout(new GridLayout(3,1,2,2));
		pan1.add(Username);
		pan1.add(UsernameT);
		add(pan1);
		pan2.add(Password);
		pan2.add(PasswordT);
		add(pan2);
		pan3.add(CreateAccount);
		pan3.add(Log);
		add(pan3);
		
		setOpaque(true);
		setBackground(Color.gray);	
		//setSize(384,216);

	}
}

class TM_CreateAccount extends JPanel{
	JTextField NameT = new JTextField();
	JTextField UsernameT = new JTextField();
	JTextField Password1T = new JTextField();
	JTextField Password2T = new JTextField();
	JLabel Name = new JLabel("Name: ");
	JLabel Username = new JLabel("Username: ");
	JLabel Password = new JLabel("Password: ");
	JLabel Password2 = new JLabel("Verify Password: ");
	JButton CreateAccount = new JButton("Create Account");
	JButton Cancel = new JButton("Cancel");
	JPanel pan1 = new CPanel();
	JPanel pan2 = new CPanel();
	JPanel pan3 = new CPanel();
	JPanel pan4 = new CPanel();
	JPanel pan5 = new CPanel();
	
	public TM_CreateAccount(){
		
		NameT.setColumns(10);
		UsernameT.setColumns(10);
		Password1T.setColumns(10);
		Password2T.setColumns(10);
		setLayout(new GridLayout(5,1,2,2));
		pan1.add(Name);
		pan1.add(NameT);
		add(pan1);
		pan2.add(Username);
		pan2.add(UsernameT);
		add(pan2);
		pan3.add(Password);
		pan3.add(Password1T);
		add(pan3);
		pan4.add(Password2);
		pan4.add(Password2T);
		add(pan4);
		pan5.add(CreateAccount);
		pan5.add(Cancel); 
		add(pan5);
				
		setOpaque(true);
		setBackground(Color.gray);
		
	}
}
	
	
class TM_MSidePanel extends JPanel{
	JButton Exit;
	JButton Task;
	JButton Accounts;
	JButton Report;
	JButton Edit;
	
	public TM_MSidePanel(){
		setLayout(new GridLayout(5,1,2,2));
		Exit = new IButton(new ImageIcon(Test.class.getResource("art/SidePanel/BackButton.png")));
		add(Exit);
		
		//need to finish when new icons are created
		
		setOpaque(true);
		setBackground(Color.darkGray);
	}
}

class TM_MTask extends JPanel{
	JTextField TaskNameT = new JTextField();
	JTextArea DescriptionT = new JTextArea();
	JLabel TaskName = new JLabel("TaskName: ");
	JLabel Description = new JLabel("Description: ");
	JLabel Priority = new JLabel("Priority : ");
	JLabel Assign = new JLabel("Assign to Developer: ");
	JLabel Header = new JLabel("<html><font size = 12>Create a Task</font><html>",JLabel.CENTER);
	JRadioButton Low = new CRadioButton("Low");
	JRadioButton Medium = new CRadioButton("Medium");
	JRadioButton High = new CRadioButton("High");
	JRadioButton NA = new CRadioButton("N/A");
	ButtonGroup PriorityG = new ButtonGroup();
	JButton CreateTask = new JButton("Create Task");
	JComboBox Developers = new JComboBox();
	JPanel pan1 = new CPanel();
	JPanel pan1a = new CPanel();
	JPanel pan1b = new CPanel();
	JPanel pan2 = new CPanel();
	JPanel pan3 = new CPanel();
	JPanel pan4 = new CPanel();
	JPanel pan5 = new CPanel();
	JPanel pan6 = new CPanel();
	JPanel buffer = new CPanel();
	JPanel buffer2 = new CPanel();
	
	public TM_MTask(){
		setLayout(new GridLayout(7,1));
		//pan1.setLayout(new GridLayout(1,2,2,2));
		//pan2.setLayout(new GridLayout(1,3,2,2));
		//pan3.setLayout(new GridLayout(1,3,2,2));
		//pan4.setLayout(new GridLayout(1,2,2,2));
		//pan5.setLayout(new GridLayout(1,2,2,2));

		add(Header);
		
		TaskNameT.setColumns(10);
		//TaskNameT.setPreferredSize(new Dimension(128,72));
		//pan1a.add(TaskName);
		//pan1b.add(TaskNameT);
		pan1.add(TaskName);
		pan1.add(TaskNameT);
		add(pan1);
		
		pan2.add(Priority);
		pan2.add(Low);
		pan2.add(Medium);
		add(pan2);
		
		pan3.add(buffer);
		pan3.add(High);
		pan3.add(NA);
		add(pan3);
		
		PriorityG.add(Low);
		PriorityG.add(Medium);
		PriorityG.add(High);
		PriorityG.add(NA);
		
		DescriptionT.setColumns(15);
		DescriptionT.setRows(3);
		pan4.add(Description);
		pan4.add(DescriptionT);
		add(pan4);
		
		pan5.add(Assign);
		pan5.add(Developers);
		add(pan5);
		
		pan6.add(CreateTask);
		add(pan6);
		
		setOpaque(true);
		setBackground(Color.gray);	
	}
}

class TM_MEdit extends JPanel{
}

class TM_MReport extends JPanel{
}

class TM_MAccounts extends JPanel{
}

class TM_DSidePanel extends JPanel{
	JButton Exit;
	JButton Task;
	JButton Edit;
	JButton Time;
	JButton Report;
	
	public TM_DSidePanel(){
		setLayout(new GridLayout(5,1,2,2));
		
		//need to finish when new icons are created
		
		setOpaque(true);
		setBackground(Color.darkGray);
	}
}
	

class TM_DTask extends JPanel{
}

class TM_DEdit extends JPanel{
}

class TM_DTime extends JPanel{
}

class TM_DReport extends JPanel{
}
	
	
class CPanel extends JPanel{
	public CPanel(){
		setOpaque(false);
	}
}

class CRadioButton extends JRadioButton{
	public CRadioButton(String title){
		super(title);
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		//setForeground(Color.white);
	}
}

class IButton extends JButton{
	public IButton(ImageIcon I){
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setIcon(I);
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
		icon_top = new ImageIcon(Test.class.getResource("art/IntroAnimation2/1.png"));
		label_top = new JLabel(icon_top);
		icon_bottom = new ImageIcon(Test.class.getResource("art/IntroAnimation2/2.png"));
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
	JLabel DevTO = new JLabel("Developer");
	JLabel ManTO = new JLabel("Manager");
	JTextField Dev_NameTF = new JTextField("Name");
	JTextField Dev_EmailTF = new JTextField("Email");
	JTextField Dev_TeamTF = new JTextField("Team");
	JTextField Dev_PasswordTF = new JTextField("Password");
	JTextField Man_NameTF = new JTextField("Name");
	JTextField Man_EmailTF = new JTextField("Email");
	JTextField Man_TeamTF = new JTextField("Team");
	JTextField Man_PasswordTF = new JTextField("Password");
	JButton button_DevRegister = new JButton("Register");
	JButton button_DevLogin = new JButton("Login");
	JButton button_ManRegister = new JButton("Register");
	JButton button_ManLogin = new JButton("Login");
	
	
	public RegisterScreen(JPanel frame){
		//frame.setBackground(new java.awt.Color(53,53,53));
		frame.setBackground(new java.awt.Color(59,68,91));
		frame.add(DevTO); frame.add(ManTO);
		frame.add(Dev_NameTF); frame.add(Dev_EmailTF);
		frame.add(Dev_TeamTF); frame.add(Dev_PasswordTF);
		frame.add(Man_NameTF); frame.add(Man_EmailTF);
		frame.add(Man_TeamTF); frame.add(Man_PasswordTF);
		frame.add(button_DevRegister); frame.add(button_DevLogin);
		frame.add(button_ManRegister); frame.add(button_ManLogin);
		frame.setLayout(null);
		
		//JLabels/Text Objects
		DevTO.setBounds(150,-10,195,130);
		DevTO.setFont(new Font("Helvetica", Font.BOLD, 32));
		DevTO.setForeground(new java.awt.Color(73,210,146));
		ManTO.setBounds(700,-10,195,130);
		ManTO.setFont(new Font("Helvetica", Font.BOLD, 32));
		ManTO.setForeground(new java.awt.Color(73,210,146));
		
		//Text Fields
		Dev_NameTF.setBounds(110,120, 240,30); 
		Dev_EmailTF.setBounds(110,160, 240,30);
		Dev_TeamTF.setBounds(110,200, 240,30); 
		Dev_PasswordTF.setBounds(110,240, 240,30); 		
		Man_NameTF.setBounds(660,120, 240,30); 
		Man_EmailTF.setBounds(660,160, 240,30);
		Man_TeamTF.setBounds(660,200, 240,30); 
		Man_PasswordTF.setBounds(660,240, 240,30); 	
		Dev_NameTF.setBackground(new java.awt.Color(70,81,108));
		Dev_NameTF.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		Dev_EmailTF.setBackground(new java.awt.Color(70,81,108));
		Dev_EmailTF.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		Dev_TeamTF.setBackground(new java.awt.Color(70,81,108));
		Dev_TeamTF.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		Dev_PasswordTF.setBackground(new java.awt.Color(70,81,108));
		Dev_PasswordTF.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		Man_NameTF.setBackground(new java.awt.Color(70,81,108));
		Man_NameTF.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		Man_EmailTF.setBackground(new java.awt.Color(70,81,108));
		Man_EmailTF.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		Man_TeamTF.setBackground(new java.awt.Color(70,81,108));
		Man_TeamTF.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		Man_PasswordTF.setBackground(new java.awt.Color(70,81,108));
		Man_PasswordTF.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		
		//Buttons
		button_DevRegister.setBounds(140,300, 160,45); 
		button_DevLogin.setBounds(140,360, 160,45); 
		button_ManRegister.setBounds(690,300, 160,45); 
		button_ManLogin.setBounds(690,360, 160,45); 
		button_DevRegister.setBorderPainted(false);
		button_DevLogin.setBorderPainted(false);
		button_ManRegister.setBorderPainted(false);
		button_ManLogin.setBorderPainted(false);
		button_DevRegister.setForeground(new java.awt.Color(117,132,178));
		button_DevLogin.setForeground(new java.awt.Color(117,132,178));
		button_ManRegister.setForeground(new java.awt.Color(117,132,178));
		button_ManLogin.setForeground(new java.awt.Color(117,132,178));
		button_DevRegister.setBackground(new java.awt.Color(70,81,108));
		button_DevLogin.setBackground(new java.awt.Color(70,81,108));
		button_ManRegister.setBackground(new java.awt.Color(70,81,108));
		button_ManLogin.setBackground(new java.awt.Color(70,81,108));
		
		button_DevRegister.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.setBackground(new java.awt.Color(0,0,0));
				frame.removeAll();
				frame.revalidate();
				frame.repaint();
				Test.MenuVar = 1; 
			}
		});
		button_DevLogin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.setBackground(new java.awt.Color(0,0,0));
				frame.removeAll();
				frame.revalidate();
				frame.repaint();
				Test.MenuVar = 1; 
			}
		});
		button_ManRegister.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.setBackground(new java.awt.Color(0,0,0));
				frame.removeAll();
				frame.revalidate();
				frame.repaint();
				Test.MenuVar = 1; 
			}
		});
		button_ManLogin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.setBackground(new java.awt.Color(0,0,0));
				frame.removeAll();
				frame.revalidate();
				frame.repaint();
				Test.MenuVar = 1; 
			}
		});
		frame.setOpaque(true);
		
	}
}
