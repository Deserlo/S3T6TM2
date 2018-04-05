import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

class TM_Frame extends JFrame{
	public TM_Frame(String title){
		setTitle(title);
		//setSize(1024,768);
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
	JButton CreateAccount = new JButton("Create Account");
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
		pan3.add(CreateAccount);
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
	JButton CreateAccount = new JButton("Create Account");
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
		pan5.add(CreateAccount);
		pan5.add(Cancel); 
		add(pan5);
				
		setOpaque(true);
		setBackground(Color.gray);
		
	}
}
	
	
class TM_MSidePanel extends JPanel{
	JButton Exit;
	JButton Task;
	JButton Accounts;
	JButton Report;
	JButton Edit;
	
	public TM_MSidePanel(){
		setLayout(new GridLayout(5,1,2,2));
		Exit = new IButton(new ImageIcon(TM_GUI.class.getResource("art/SidePanel/BackButton.png")));
		add(Exit);
		
		//need to finish when new icons are created
		
		setOpaque(true);
		setBackground(Color.darkGray);
	}
}

class TM_MTask extends JPanel{
	JTextField TaskNameT = new JTextField();
	JTextArea DescriptionT = new JTextArea();
	JLabel TaskName = new JLabel("TaskName: ");
	JLabel Description = new JLabel("Description: ");
	JLabel Priority = new JLabel("Priority : ");
	JLabel Assign = new JLabel("Assign to Developer: ");
	JLabel Header = new JLabel("<html><font size = 12>Create a Task</font><html>",JLabel.CENTER);
	JRadioButton Low = new JRadioButton("Low");
	JRadioButton Medium = new JRadioButton("Medium");
	JRadioButton High = new JRadioButton("High");
	JRadioButton NA = new JRadioButton("N/A");
	ButtonGroup PriorityG = new ButtonGroup();
	JButton CreateTask = new JButton("Create Task");
	JComboBox Developers = new JComboBox();
	JPanel pan1 = new CPanel();
	JPanel pan1a = new CPanel();
	JPanel pan1b = new CPanel();
	JPanel pan2 = new CPanel();
	JPanel pan3 = new CPanel();
	JPanel pan4 = new CPanel();
	JPanel pan5 = new CPanel();
	JPanel pan6 = new CPanel();
	JPanel buffer = new CPanel();
	JPanel buffer2 = new CPanel();
	
	public TM_MTask(){
		setLayout(new GridLayout(7,1));
		//pan1.setLayout(new GridLayout(1,2,2,2));
		//pan2.setLayout(new GridLayout(1,3,2,2));
		//pan3.setLayout(new GridLayout(1,3,2,2));
		//pan4.setLayout(new GridLayout(1,2,2,2));
		//pan5.setLayout(new GridLayout(1,2,2,2));

		add(Header);
		
		TaskNameT.setColumns(10);
		//TaskNameT.setPreferredSize(new Dimension(128,72));
		//pan1a.add(TaskName);
		//pan1b.add(TaskNameT);
		pan1.add(TaskName);
		pan1.add(TaskNameT);
		add(pan1);
		
		pan2.add(Priority);
		pan2.add(Low);
		pan2.add(Medium);
		add(pan2);
		
		pan3.add(buffer);
		pan3.add(High);
		pan3.add(NA);
		add(pan3);
		
		DescriptionT.setColumns(15);
		DescriptionT.setRows(3);
		pan4.add(Description);
		pan4.add(DescriptionT);
		add(pan4);
		
		pan5.add(Assign);
		pan5.add(Developers);
		add(pan5);
		
		pan6.add(CreateTask);
		add(pan6);
		
		setOpaque(true);
		setBackground(Color.gray);	
	}
}

class TM_MEdit extends JPanel{
}

class TM_MReport extends JPanel{
}

class TM_MAccounts extends JPanel{
}

class TM_DSidePanel extends JPanel{
	JButton Exit;
	JButton Task;
	JButton Edit;
	JButton Time;
	JButton Report;
	
	public TM_DSidePanel(){
		setLayout(new GridLayout(5,1,2,2));
		
		//need to finish when new icons are created
		
		setOpaque(true);
		setBackground(Color.darkGray);
	}
}
	

class TM_DTask extends JPanel{
}

class TM_DEdit extends JPanel{
}

class TM_DTime extends JPanel{
}

class TM_DReport extends JPanel{
}
	
	
class CPanel extends JPanel{
	public CPanel(){
		setOpaque(false);
	}
}

class CRadioButton extends JRadioButton{
	public CRadioButton(){
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setForeground(Color.white);
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

