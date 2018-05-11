import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Test{
	public static int MenuVar = 0;
	public static int userID = -1;
	public static boolean login = false;
	static String Data[][] = {{"No project info to display."," "," "}};
	static JPanel main;	
	static JPanel devPR;
	static int counter; // counter = # of projects
	static JButton[] ButtonActual = new CButton[counter];
	
	public static void main(String[] args){
		JFrame home = new TM_Frame("TM");
		
		//Screens
		JPanel Intro = new CPanel();
		JPanel RegisterScreen = new CPanel();
		JPanel LogInScreen = new CPanel();
		JPanel ChangePasswordScreen = new CPanel();
		
		//Dashboard Screen
		JPanel center;
		JPanel side;
		
		//Container
		Container contentpan = (JPanel)home.getContentPane();
	
		//Switch Value here by changing MenuVar
		int OldValue = -1;
		while(true){
			if(OldValue != MenuVar){
				OldValue = MenuVar;
				switch(MenuVar){
					case 0:	System.out.println("In case " + MenuVar);
							new TM_GUI_Intro(Intro, home);
							contentpan.add(Intro);
							break;
					case 1: System.out.println("In case " + MenuVar);
							new LogInScreen(LogInScreen, home);
							contentpan.add(LogInScreen);
							contentpan.revalidate();
							break;
					case 2: System.out.println("In case " + MenuVar);
							new RegisterScreen(RegisterScreen);
							contentpan.add(RegisterScreen);
							contentpan.revalidate();
							break;		
					case 3: System.out.println("In case " + MenuVar);
							contentpan.removeAll();
							main = new CPanel();
							center = new HoursDev(home.getContentPane(), main, new JPanel[5], Data);
							side = new SidePanel(home.getContentPane(), main, new JPanel[5], Data);
							main.setLayout(new BorderLayout());
							main.add(side,BorderLayout.WEST);
							main.add(center,BorderLayout.CENTER);
							contentpan.add(main);
							contentpan.revalidate();
							break;
					case 4:	System.out.println("In case " + MenuVar);
							contentpan.removeAll();
							main = new CPanel();
							center = new HoursManager(home.getContentPane(), main, new JPanel[5], Data);
							side = new SidePanel(home.getContentPane(), main, new JPanel[5],Data);
							main.setLayout(new BorderLayout());
							main.add(side,BorderLayout.WEST);
							main.add(center,BorderLayout.CENTER);
							contentpan.add(main);
							contentpan.revalidate();
							break;
					case 5:	System.out.println("In case " + MenuVar);
							new ChangePasswordScreen(ChangePasswordScreen);
							contentpan.add(ChangePasswordScreen);
							contentpan.revalidate();
							break;
				}
			} home.setSize(960,540); //16:9 Resolution
		}
	}
}