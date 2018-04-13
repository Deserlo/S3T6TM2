import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Test{
	public static int MenuVar = 2;
	public static void main(String[] args){
		JFrame home = new TM_Frame("TM");
		
		//Screens
		JPanel Intro = new CPanel();
		JPanel RegisterScreen = new CPanel();
		JPanel LogInScreen = new CPanel();
		JPanel DevHoursScreen = new CPanel();
		
		//DevScreen
		JPanel main = new CPanel();
		JPanel main1 = new Hours();
		JPanel main2 = new CPanel();
		JPanel main3 = new CPanel();
		JPanel[] s = new JPanel[4];
		JPanel Log = new CPanel();
		JPanel center = new CPanel();
		JPanel side = new SidePanel(home.getContentPane(), main, s);
		JLabel test = new JLabel("test");
		s[0]=main1;
		s[1]=main2;
		s[2]=main3;

		//Container
		Container contentpan = (JPanel)home.getContentPane();
	
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
						contentpan.revalidate();
						break;
					case 2: 
						System.out.println("In case " + MenuVar);
						new RegisterScreen(RegisterScreen);
						contentpan.add(RegisterScreen);
						contentpan.revalidate();
						break;		
					case 3: 
						System.out.println("In case " + MenuVar);
						contentpan.removeAll();
						//new DevHoursScreen(DevHoursScreen);
						//contentpan.add(DevHoursScreen);
						main.setLayout(new BorderLayout());
						main.add(side,BorderLayout.WEST);
						center.add(test);
						main.add(center,BorderLayout.CENTER);
						home.getContentPane().add(main);
						contentpan.revalidate();
						break;
					case 4:
						System.out.println("In case " + MenuVar);
						contentpan.revalidate();
					default:
						break;
				}
			}
			home.setSize(960,540); //16:9 Resolution
		}
	}
}
