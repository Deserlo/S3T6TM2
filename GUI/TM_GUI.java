//main.java
/*
	This is the main GUI file for TM. Program execution start should be this file.
	This needs to link to other files. For example, CreateAccount() code needs to call to a different file.
*/

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
//import java.util.*;
import java.io.*;

public class TM_GUI{
	//Settings, have to be global
	static boolean IntroOn = true;	
	
	//Global Variables that need to be made local
	static int GlobalTimer = 0;
	static int MenuScreen = 1;
	
    public static void main(String[] args) {
		//GetSettings(); //Get from a text file, set global booleans.
		if(IntroOn == false){
			GlobalTimer += 35;
		}
		//Local Variables
		//Frame = window. Creating a new frame will open a new window. So only one frame.
		JFrame frame;
		frame = new JFrame("TM");
		frame.setLayout(new BorderLayout()); 
		
		//Initializing everything here
		JPanel panel = new JPanel();
		JButton button_DevButton = new JButton("Developer");
		JButton button_ManagerButton = new JButton("Manager");
		ImageIcon icon;
		ImageIcon icon_empty;
		ImageIcon icon_SidePanel;
		ImageIcon icon_SidePanel_Back;
		ImageIcon icon_SidePanel_Clock;
		ImageIcon icon_SidePanel_Projects;
		ImageIcon icon_SidePanel_Reports;
		JLabel label; //background
		JLabel label_DevIcon;
		JLabel label_DevIcon2;
		JLabel label_SidePanel;
		
		//Setting icons to graphic within jar file. TM_GUI.class.getResource(filedestination) is needed for jar files, can't just call to file location.
		//This will work outside of jar file, if in the directory. For example folder: "art", and file "black.jpg" within that folder.
		icon = new ImageIcon(TM_GUI.class.getResource("art/black.jpg"));
		icon_empty = new ImageIcon(TM_GUI.class.getResource("art/iconprofile.png"));
		icon_SidePanel = new ImageIcon(TM_GUI.class.getResource("art/SidePanel/DarkGray.jpg"));
		icon_SidePanel_Back = new ImageIcon(TM_GUI.class.getResource("art/SidePanel/BackButton.png"));
		icon_SidePanel_Clock = new ImageIcon(TM_GUI.class.getResource("art/SidePanel/ClockButton.png"));
		icon_SidePanel_Projects = new ImageIcon(TM_GUI.class.getResource("art/SidePanel/ProjectButton.png"));
		icon_SidePanel_Reports = new ImageIcon(TM_GUI.class.getResource("art/SidePanel/ReportsButton.png"));
		
		//Labels(non-buttons)
		label = new JLabel(icon);
		label_DevIcon = new JLabel(icon_empty);
		label_DevIcon2 = new JLabel(icon_empty);
		label_SidePanel = new JLabel(icon_SidePanel);
		
		//buttons with graphics instead of text.
		JButton button_SidePanel_Back = new JButton(icon_SidePanel_Back);
		button_SidePanel_Back.setOpaque(false);
		button_SidePanel_Back.setContentAreaFilled(false);
		button_SidePanel_Back.setBorderPainted(false);
		JButton button_SidePanel_Clock = new JButton(icon_SidePanel_Clock);
		button_SidePanel_Clock.setOpaque(false);
		button_SidePanel_Clock.setContentAreaFilled(false);
		button_SidePanel_Clock.setBorderPainted(false);
		JButton button_SidePanel_Projects = new JButton(icon_SidePanel_Projects);
		button_SidePanel_Projects.setOpaque(false);
		button_SidePanel_Projects.setContentAreaFilled(false);
		button_SidePanel_Projects.setBorderPainted(false);
		JButton button_SidePanel_Reports = new JButton(icon_SidePanel_Reports);
		button_SidePanel_Reports.setOpaque(false);
		button_SidePanel_Reports.setContentAreaFilled(false);
		button_SidePanel_Reports.setBorderPainted(false);
		
		//load in buttons, and labels, but place offscreen. Can not load in objects during action function.
		button_DevButton.setBounds(-250,400,95,30); 
		button_ManagerButton.setBounds(-250,400,95,30);
		button_SidePanel_Back.setBounds(-250,400,95,30);
		button_SidePanel_Clock.setBounds(-250,400,95,30);
		button_SidePanel_Projects.setBounds(-250,400,95,30);
		button_SidePanel_Reports.setBounds(-250,400,95,30);
		label_DevIcon.setBounds(-250,400,95,30);
		label_DevIcon2.setBounds(-250,400,95,30);
		label_SidePanel.setBounds(-250,400,95,30);
		
		//Add everything to frame/the window.
		//Last added to frame = very back
		//first added to frame = very front.
		frame.add(button_DevButton);
		frame.add(button_ManagerButton);
		frame.add(label_DevIcon);
		frame.add(label_DevIcon2);
		frame.add(button_SidePanel_Back);
		frame.add(button_SidePanel_Clock);
		frame.add(button_SidePanel_Projects);
		frame.add(button_SidePanel_Reports);	
		frame.add(label_SidePanel);
		frame.add(label); //background
		
		//All actions go in this function.
		ActionListener action = new ActionListener(){ 
			public void actionPerformed(ActionEvent event)
            {
				GlobalTimer++;
				System.out.println("" + GlobalTimer);
				if(GlobalTimer < 35){
					if(GlobalTimer == 5){
						label.setIcon(new ImageIcon(TM_GUI.class.getResource("art/IntroAnimation/1.jpg")));
					}
					else if(GlobalTimer == 6){
						label.setIcon(new ImageIcon(TM_GUI.class.getResource("art/IntroAnimation/2.jpg")));
					}
					else if(GlobalTimer == 7){
						label.setIcon(new ImageIcon(TM_GUI.class.getResource("art/IntroAnimation/3.jpg")));
					}
					else if(GlobalTimer == 8){
						label.setIcon(new ImageIcon(TM_GUI.class.getResource("art/IntroAnimation/4.jpg")));
					}
					else if(GlobalTimer == 9){
						label.setIcon(new ImageIcon(TM_GUI.class.getResource("art/IntroAnimation/5.jpg")));
					}
					
					else if(GlobalTimer == 12){
						label.setIcon(new ImageIcon(TM_GUI.class.getResource("art/IntroAnimation/6.jpg")));
					}
					else if(GlobalTimer == 13){
						label.setIcon(new ImageIcon(TM_GUI.class.getResource("art/IntroAnimation/8.jpg")));
					}
					else if(GlobalTimer == 14){
						label.setIcon(new ImageIcon(TM_GUI.class.getResource("art/IntroAnimation/7.jpg")));
					}
					else if(GlobalTimer == 16){
						label.setIcon(new ImageIcon(TM_GUI.class.getResource("art/IntroAnimation/6.jpg")));
					}
					else if(GlobalTimer == 17){
						label.setIcon(new ImageIcon(TM_GUI.class.getResource("art/IntroAnimation/9.jpg")));
					}
					else if(GlobalTimer == 18){
						label.setIcon(new ImageIcon(TM_GUI.class.getResource("art/IntroAnimation/6.jpg")));
					}
					else if(GlobalTimer == 19){
						label.setIcon(new ImageIcon(TM_GUI.class.getResource("art/IntroAnimation/5.jpg")));
					}
					else if(GlobalTimer == 22){
						label.setIcon(new ImageIcon(TM_GUI.class.getResource("art/IntroAnimation/4.jpg")));
					}
					else if(GlobalTimer == 23){
						label.setIcon(new ImageIcon(TM_GUI.class.getResource("art/IntroAnimation/3.jpg")));
					}
					else if(GlobalTimer == 24){
						label.setIcon(new ImageIcon(TM_GUI.class.getResource("art/IntroAnimation/2.jpg")));
					}
					else if(GlobalTimer == 25){
						label.setIcon(new ImageIcon(TM_GUI.class.getResource("art/IntroAnimation/1.jpg")));
					}
					else if(GlobalTimer == 26){
						label.setIcon(new ImageIcon(TM_GUI.class.getResource("art/black.jpg")));
					}
					else if(GlobalTimer == 31){
						label.setIcon(new ImageIcon(TM_GUI.class.getResource("art/IntroAnimation/10.jpg")));
					}
					else if(GlobalTimer == 32){
						label.setIcon(new ImageIcon(TM_GUI.class.getResource("art/IntroAnimation/11.jpg")));
					}
					else if(GlobalTimer == 33){
						label.setIcon(new ImageIcon(TM_GUI.class.getResource("art/IntroAnimation/12.jpg")));
					}
					else if(GlobalTimer == 34){ //35 = 3.5 seconds
						label.setIcon(new ImageIcon(TM_GUI.class.getResource("art/IntroAnimation/13.jpg")));
					}
				}
				else{
					//Menu Screen
					//1: Start screen. 2: Developer Dashboard 3: Manager Dashboard
					switch(MenuScreen){
						case 1: button_DevButton.setBounds(300,400,190,60);
								button_ManagerButton.setBounds(790,400,190,60);
								label_DevIcon.setBounds(300,200,190,200);
								label_DevIcon2.setBounds(790,200,190,200);
								label.setIcon(new ImageIcon(TM_GUI.class.getResource("art/Gray.jpg")));
								button_DevButton.addActionListener(new ActionListener(){
									public void actionPerformed(ActionEvent e){
										//Place buttons of current menu offscreen.
										//Alternatively we could place these buttons offscreen calls at the start of the case
										button_DevButton.setBounds(-250,400,95,30); 
										button_ManagerButton.setBounds(-250,400,95,30);
										label_DevIcon.setBounds(-250,400,95,30);
										label_DevIcon2.setBounds(-250,400,95,30);
										MenuScreen = 2;
									}
								});
								button_ManagerButton.addActionListener(new ActionListener(){
									public void actionPerformed(ActionEvent e){
										button_DevButton.setBounds(-250,400,95,30); 
										button_ManagerButton.setBounds(-250,400,95,30);
										label_DevIcon.setBounds(-250,400,95,30);
										label_DevIcon2.setBounds(-250,400,95,30);
										MenuScreen = 3;
									}
								});
								break;
						case 2:	label_SidePanel.setBounds(0,0, 150, 720);
								button_SidePanel_Back.setBounds(0, 556, 150, 144);
								button_SidePanel_Clock.setBounds(0, 0, 150, 144);
								button_SidePanel_Projects.setBounds(0, 144, 150, 144);
								button_SidePanel_Reports.setBounds(0, 288, 150, 144);
								button_SidePanel_Back.addActionListener(new ActionListener(){
									public void actionPerformed(ActionEvent e){
										label_SidePanel.setBounds(-250,400,95,30); 
										button_SidePanel_Back.setBounds(-250,400,95,30);
										button_SidePanel_Clock.setBounds(-250,400,95,30);
										button_SidePanel_Projects.setBounds(-250,400,95,30);
										button_SidePanel_Reports.setBounds(-250,400,95,30);
										MenuScreen = 1;
									}
								});
								button_SidePanel_Back.addMouseListener(new java.awt.event.MouseAdapter() {
									public void mouseEntered(java.awt.event.MouseEvent evt) {
										button_SidePanel_Back.setIcon(new ImageIcon(TM_GUI.class.getResource("art/SidePanel/BackButtonHover.png")));
									}
									public void mouseExited(java.awt.event.MouseEvent evt) {
										button_SidePanel_Back.setIcon(new ImageIcon(TM_GUI.class.getResource("art/SidePanel/BackButton.png")));
									}
								});
								button_SidePanel_Clock.addMouseListener(new java.awt.event.MouseAdapter() {
									public void mouseEntered(java.awt.event.MouseEvent evt) {
										button_SidePanel_Clock.setIcon(new ImageIcon(TM_GUI.class.getResource("art/SidePanel/ClockButtonHover.png")));
									}
									public void mouseExited(java.awt.event.MouseEvent evt) {
										button_SidePanel_Clock.setIcon(new ImageIcon(TM_GUI.class.getResource("art/SidePanel/ClockButton.png")));
									}
								});
								button_SidePanel_Projects.addMouseListener(new java.awt.event.MouseAdapter() {
									public void mouseEntered(java.awt.event.MouseEvent evt) {
										button_SidePanel_Projects.setIcon(new ImageIcon(TM_GUI.class.getResource("art/SidePanel/ProjectButtonHover.png")));
									}
									public void mouseExited(java.awt.event.MouseEvent evt) {
										button_SidePanel_Projects.setIcon(new ImageIcon(TM_GUI.class.getResource("art/SidePanel/ProjectButton.png")));
									}
								});
								button_SidePanel_Reports.addMouseListener(new java.awt.event.MouseAdapter() {
									public void mouseEntered(java.awt.event.MouseEvent evt) {
										button_SidePanel_Reports.setIcon(new ImageIcon(TM_GUI.class.getResource("art/SidePanel/ReportsButtonHover.png")));
									}
									public void mouseExited(java.awt.event.MouseEvent evt) {
										button_SidePanel_Reports.setIcon(new ImageIcon(TM_GUI.class.getResource("art/SidePanel/ReportsButton.png")));
									}
								});
								break;
						case 3:	label_SidePanel.setBounds(0,0, 150, 720);
								button_SidePanel_Back.setBounds(0, 556, 150, 144);
								button_SidePanel_Back.addActionListener(new ActionListener(){
									public void actionPerformed(ActionEvent e){
										label_SidePanel.setBounds(-250,400,95,30); 
										button_SidePanel_Back.setBounds(-250,400,95,30);
										MenuScreen = 1;
									}
								});
								break;
						default:
								break;
					}
				}
			}			
		};
		
		Timer LocalTimer = new Timer(100, action); //100 milliseconds = 10 to globalvar = 1 second.
		LocalTimer.start();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280,720); //<-Window size. Keep this size unless we agree to change. 
		frame.setVisible(true);
    }
}