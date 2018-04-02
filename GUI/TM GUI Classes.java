import java.awt.*;
import javax.swing.*;
import java.io.*;

class TM_Frame extends JFrame{
	public TM_Frame(String title){
		setTitle(title);
		setSize(1280,720);
		setVisible(true);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class TM_LogIn extends JPanel{
	JTextField Username = new JTextField();
	JPasswordField Password = new JPasswordField();
	JLabel User = new JLabel("Username: ");
	JLabel Pass = new JLabel("Password: ");
	JButton Log = new JButton("Log In");
	JButton Create = new JButton("Create Account");
	JPanel pan = new JPanel();
	JPanel pan1 = new JPanel();
	JPanel pan2 = new JPanel();
	JPanel pan3 = new JPanel();


	public TM_LogIn(){
		
		Username.setColumns(10);
		Password.setColumns(10);
		pan.setLayout(new GridLayout(3,1,2,2));
		pan1.add(User);
		pan1.add(Username);
		pan.add(pan1);
		pan2.add(Pass);
		pan2.add(Password);
		pan.add(pan2);
		pan3.add(Create);
		pan3.add(Log);
		pan.add(pan3);
		add(pan);
		
		//setSize(384,216);

	}
}
		
		