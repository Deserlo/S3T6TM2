import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;

class TM_Frame extends JFrame{
	public TM_Frame(String title){
		setTitle(title);
		setSize(960,540);
		setVisible(true);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
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


class CButton extends JButton{
	public CButton(ImageIcon I){
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
