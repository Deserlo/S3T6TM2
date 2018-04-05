import java.awt.*;
import javax.swing.*;
import java.io.*;

public class Test{
	public static void main(String[] args){
		JFrame home = new TM_Frame("TM");
		JPanel log = new TM_LogIn();
		JPanel create = new TM_CreateAccount();
		JPanel manager = new TM_MSidePanel();
		JPanel developer = new TM_DSidePanel();
		JPanel mtask = new TM_MTask();
		              //switch the panel to change view
		Container contentpan = (JPanel)home.getContentPane();
		contentpan.setLayout(new BorderLayout());
		contentpan.add(manager, BorderLayout.WEST);
		contentpan.add(mtask, BorderLayout.CENTER);
		home.setSize(960,540);
		//home.setSize(384,216);
	}
}