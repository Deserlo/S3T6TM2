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
	JPanel pan1 = new CPanel();
	JPanel pan2 = new CPanel();
	JPanel pan3 = new CPanel();
	
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
	JPanel pan1 = new CPanel();
	JPanel pan2 = new CPanel();
	JPanel pan3 = new CPanel();
	JPanel pan4 = new CPanel();
	
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
				
		setOpaque(true);
		setBackground(Color.gray);
		
	}
}
	
	
class TM_SidePanel extends JPanel{
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