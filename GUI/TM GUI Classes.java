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
	JTextField UsernameT = new JTextField();
	JPasswordField PasswordT = new JPasswordField();
	JLabel Username = new JLabel("Username: ");
	JLabel Password = new JLabel("Password: ");
	JButton Log = new JButton("Log In");
	JButton Create = new JButton("Create Account");
	JPanel pan1 = new CPanel();
	JPanel pan2 = new CPanel();
	JPanel pan3 = new CPanel();
	
	public TM_LogIn(){
		
		UsernameT.setColumns(10);
		PasswordT.setColumns(10);
		setLayout(new GridLayout(3,1,2,2));
		pan1.add(Username);
		pan1.add(UsernameT);
		add(pan1);
		pan2.add(Password);
		pan2.add(PasswordT);
		add(pan2);
		pan3.add(Create);
		pan3.add(Log);
		add(pan3);
		
		setOpaque(true);
		setBackground(Color.gray);	
		//setSize(384,216);

	}
}

class TM_CreateAccount extends JPanel{
	JTextField NameT = new JTextField();
	JTextField UsernameT = new JTextField();
	JTextField Password1T = new JTextField();
	JTextField Password2T = new JTextField();
	JLabel Name = new JLabel("Name: ");
	JLabel Username = new JLabel("Username: ");
	JLabel Password = new JLabel("Password: ");
	JLabel Password2 = new JLabel("Verify Password: ");
	JButton Create = new JButton("Create Account");
	JButton Cancel = new JButton("Cancel");
	JPanel pan1 = new CPanel();
	JPanel pan2 = new CPanel();
	JPanel pan3 = new CPanel();
	JPanel pan4 = new CPanel();
	JPanel pan5 = new CPanel();
	
	public TM_CreateAccount(){
		
		NameT.setColumns(10);
		UsernameT.setColumns(10);
		Password1T.setColumns(10);
		Password2T.setColumns(10);
		setLayout(new GridLayout(5,1,2,2));
		pan1.add(Name);
		pan1.add(NameT);
		add(pan1);
		pan2.add(Username);
		pan2.add(UsernameT);
		add(pan2);
		pan3.add(Password);
		pan3.add(Password1T);
		add(pan3);
		pan4.add(Password2);
		pan4.add(Password2T);
		add(pan4);
		pan5.add(Create);
		pan5.add(Cancel); 
		add(pan5);
				
		setOpaque(true);
		setBackground(Color.gray);
		
	}
}
	
	
class TM_SidePanel extends JPanel{
	JButton back;
	JButton task;
	JButton edit;
	
	public TM_SidePanel(){
		setLayout(new GridLayout(5,1,2,2));
		JPanel buffer = new CPanel();
		
		setOpaque(true);
		setBackground(Color.gray);
	}
}

class CPanel extends JPanel{
	public CPanel(){
		setOpaque(false);
	}
}

class IButton extends JButton{
	public IButton(ImageIcon I){
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setIcon(I);
	}
}