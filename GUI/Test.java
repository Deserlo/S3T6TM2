import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Test{
	public static int MenuVar = 1;
	public static void main(String[] args){
		JFrame home = new TM_Frame("TM");
		
		//Screens
		JPanel Intro = new CPanel();
		JPanel RegisterScreen = new CPanel();
		JPanel LogInScreen = new CPanel();
		
		//Container
		Container contentpan = (JPanel)home.getContentPane();
		contentpan.setLayout(new BorderLayout());		
	
		//Switch Value here by changing MenuVar
		int OldValue = -1;
		while(true){
			if(OldValue != MenuVar){
				OldValue = MenuVar;
				switch(MenuVar){
					case 0:
						System.out.println("In case " + MenuVar);
						new TM_GUI_Intro(Intro);
						contentpan.add(Intro);
						break;
					case 1: 
						System.out.println("In case " + MenuVar);
						new LogInScreen(LogInScreen);
						contentpan.add(LogInScreen);
						//contentpan.add(manager, BorderLayout.WEST);
						//contentpan.add(mtask, BorderLayout.CENTER);
						contentpan.revalidate();
						break;
					case 2: 
						System.out.println("In case " + MenuVar);
						new RegisterScreen(RegisterScreen);
						contentpan.add(RegisterScreen);
						contentpan.revalidate();
						break;						
					default:
						break;
				}
			}
			home.setSize(960,540); //16:9 Resolution
		}
		
	}
}
