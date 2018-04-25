import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Test{
	public static int MenuVar = 4;
	public static int userID = -1;
	public static boolean login = false;
	static boolean m = false;
	static String Data[][] = {{"a","b","c"},{"a","b","c"},{"a","b","c"}};
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
		
		//Dashboard Screen
		main = new CPanel();
		JPanel[] center_panels;
		JPanel Log;
		JPanel center;
		JPanel side;
		JPanel main_hours;
		JPanel main_projects;
		JPanel main_reports;
		JPanel main_addprojects;
		
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
						m = false;
						System.out.println("In case " + MenuVar);
						contentpan.removeAll();
						main = new CPanel();
						center_panels = new JPanel[5];
						Log = new CPanel();
						center = new HoursDev(home.getContentPane(), main, center_panels, Data);
						side = new SidePanel(home.getContentPane(), main, center_panels, Data, m);
						/*main_hours = new LogTaskDev();
						center_panels[0]=main_hours;
						main_projects = new ProjectsDev(Data, home.getContentPane(), main, center_panels);
						center_panels[1]=main_projects;
						main_reports = new ReportsDev(Data, main, home.getContentPane(), center_panels);
						center_panels[2]=main_reports;*/
						main.setLayout(new BorderLayout());
						main.add(side,BorderLayout.WEST);
						main.add(center,BorderLayout.CENTER);
						
						contentpan.add(main);
						contentpan.revalidate();
						break;
					case 4:
						m = true;
						System.out.println("In case " + MenuVar);
						contentpan.removeAll();
						main = new CPanel();
						center_panels = new JPanel[5];
						Log = new CPanel();
						center = new HoursManager(home.getContentPane(), main, center_panels, Data);
						side = new SidePanel(home.getContentPane(), main, center_panels,Data, m);
						//main_hours = new HoursManager();
						/*center_panels[0]= center;
						main_projects = new ProjectsManager(Data, home.getContentPane(), main, center_panels);
						center_panels[1]=main_projects;
						main_reports = new ReportsManager(Data, main, home.getContentPane(), center_panels);
						center_panels[2]=main_reports;
						main_addprojects = new AddProjectManager(Data, home.getContentPane(), main, center_panels);
						center_panels[3]=main_addprojects;*/
						main.setLayout(new BorderLayout());
						main.add(side,BorderLayout.WEST);
						main.add(center,BorderLayout.CENTER);
						
						contentpan.add(main);
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