import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

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
	JPanel pan1 = new JPanel();
	JPanel pan2 = new JPanel();
	JPanel pan3 = new JPanel();


	public TM_LogIn(){
		
		Username.setColumns(10);
		Password.setColumns(10);
		setLayout(new GridLayout(3,1,2,2));
		pan1.add(User);
		pan1.add(Username);
		add(pan1);
		pan2.add(Pass);
		pan2.add(Password);
		add(pan2);
		pan3.add(Create);
		pan3.add(Log);
		add(pan3);
		
		pan1.setOpaque(false);
		pan2.setOpaque(false);
		pan3.setOpaque(false);

		setOpaque(true);
		setBackground(Color.gray);	
		//setSize(384,216);

	}
}

class TM_CreateAccount extends JPanel{
	JTextField Username = new JTextField();
	JTextField Password1 = new JTextField();
	JTextField Password2 = new JTextField();
	JLabel User = new JLabel("Username: ");
	JLabel Pass = new JLabel("Password: ");
	JLabel Pass2 = new JLabel("Verify Password: ");
	JButton Create = new JButton("Create Account");
	JButton Cancel = new JButton("Cancel");
	JPanel pan1 = new JPanel();
	JPanel pan2 = new JPanel();
	JPanel pan3 = new JPanel();
	JPanel pan4 = new JPanel();
	
	public TM_CreateAccount(){
		
		Username.setColumns(10);
		Password1.setColumns(10);
		Password2.setColumns(10);
		setLayout(new GridLayout(4,1,2,2));
		pan1.add(User);
		pan1.add(Username);
		add(pan1);
		pan2.add(Pass);
		pan2.add(Password1);
		add(pan2);
		pan3.add(Pass2);
		pan3.add(Password2);
		add(pan3);
		pan4.add(Create);
		pan4.add(Cancel);
		add(pan4);
		
		pan1.setOpaque(false);
		pan2.setOpaque(false);
		pan3.setOpaque(false);
		pan4.setOpaque(false);
		
		setOpaque(true);
		setBackground(Color.gray);
		
	}
}
	
	
class TM_SidePanel extends JPanel{
	public TM_SidePanel(){
		setLayout(new Layout
		
		setOpaque(true);
		setBackground(Color.gray);
	}
}
