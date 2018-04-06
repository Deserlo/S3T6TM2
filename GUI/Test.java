import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Test{
	public static int MenuVar = 0;
	public static void main(String[] args){
		JFrame home = new TM_Frame("TM");
		JPanel log = new TM_LogIn();
		JPanel create = new TM_CreateAccount();
		JPanel manager = new TM_MSidePanel();
		JPanel developer = new TM_DSidePanel();
		JPanel mtask = new TM_MTask();
		JPanel Intro = new CPanel();
		              //switch the panel to change view
		Container contentpan = (JPanel)home.getContentPane();
		contentpan.setLayout(new BorderLayout());		
	
		int oldValue = -1;
		while(true){
			if(oldValue != MenuVar){
				oldValue = MenuVar;
				switch(MenuVar){
					case 0:
						System.out.println("In case " + MenuVar);
						new TM_GUI_Intro(Intro);
						contentpan.add(Intro);
						break;
					case 1: 
						System.out.println("In case " + MenuVar);
						contentpan.add(manager, BorderLayout.WEST);
						contentpan.add(mtask, BorderLayout.CENTER);
						contentpan.revalidate();
						break;
					default:
						break;
				}
			}
			home.setSize(960,540); //16:9 Resolution
			if(oldValue == 99)
			{ break; } //to fix compiler error.
		}
		
	}
}
