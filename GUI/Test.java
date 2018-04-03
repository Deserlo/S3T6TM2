import java.awt.*;
import javax.swing.*;
import java.io.*;

public class Test{
	public static void main(String[] args){
		JFrame home = new TM_Frame("TM");
		JPanel log = new TM_LogIn();
		JPanel create = new TM_CreateAccount();
		              //switch the panel to change view
		home.getContentPane().add(create);
		home.setSize(384,216);
	}
}