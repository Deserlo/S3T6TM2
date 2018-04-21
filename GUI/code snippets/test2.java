import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;

public class test2{
	public static void main(String[] args){
		JFrame frame = new TM_Frame("TM");
		JPanel main = new CPanel();
		JPanel main1 = new Hours();
		JPanel main2 = new CPanel();
		JPanel main3 = new CPanel();
		JPanel[] s = new JPanel[4];
		JPanel Log = new CPanel();
		JPanel center = new CPanel();
		JPanel side = new SidePanel(frame.getContentPane(), main, s);
		JLabel test = new JLabel("test");
		
		s[0]=main1;
		s[1]=main2;
		s[2]=main3;

		
		main.setLayout(new BorderLayout());
		main.add(side,BorderLayout.WEST);
		center.add(test);
		main.add(center,BorderLayout.CENTER);
		
		
		frame.getContentPane().add(main);
		
		frame.setSize(940,540);
	}
}