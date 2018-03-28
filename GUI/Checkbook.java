/*Lab Number: Semester Project
  Bryant Lu
  Section Number: 1*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
public class Checkbook implements ActionListener{
	static String[] columnName = {"Date","Trans. Type","Check No.","Trans. Description","Payment/Debit(-)","Deposit/Credit(+)","Balance"};
	static transaction[] dataT = new transaction[100];
	static int counter;
	static String [][] data = new String[counter][7];
	static int i;
	static double balance;
	static JFrame frame;
	static CardLayout contentPaneLayout;
	static Container contentPane;
	static JButton[] mainB = new JButton[8];
	static JTextField[] mainF = new JTextField[2];
	static JButton[] createB = new JButton[2];
	static JTextField[] createF = new JTextField[2];
	static JButton[] loadB = new JButton[2];
	static JTextField loadF = new JTextField("",15);
	static JButton[] addB = new JButton[2];
	static JComboBox addMain2 = new JComboBox();
	static JTextField[] addMainF = new JTextField[5];
	static JButton[] searchB = new JButton[2];
	static JTextField searchF = new JTextField("",15);
	static JButton[] sortB = new JButton[2];
	static JButton[] deleteB = new JButton[2];
	static JTable mainT;
	static JScrollPane tempD;
	static JScrollPane scrollPaneD;
	static String fileName;
	public void actionPerformed (ActionEvent e){
		Object source = e.getSource();
		String s =(String) addMain2.getSelectedItem();
		
		//main menu actions
		if(source==mainB[0]){
			contentPaneLayout.show(contentPane,"Create Account");
		}
		if(source==mainB[1]){
			contentPaneLayout.show(contentPane,"Load Account");
		}
		if(source==mainB[2]){
			contentPaneLayout.show(contentPane,"New Transaction");
		}
		if(source==mainB[3]){
			contentPaneLayout.show(contentPane,"Search Menu");
		}
		if(source==mainB[4]){
			contentPaneLayout.show(contentPane,"Sort Menu");
		}
		if(source==mainB[5]){
			data = new String[counter][7];
			for(i=0; i<counter; i++){
				data[i][0]=dataT[i].date;
				data[i][1]=dataT[i].type;
				if(dataT[i].checkNo==0){
					data[i][2]="";
				}
				else{
					data[i][2]=Integer.toString(dataT[i].checkNo);
				}
				data[i][3]=dataT[i].description;
				if(dataT[i].payment==0){
					data[i][4]="";
				}
				else{
					data[i][4]=String.format("%.2f",dataT[i].payment);
				}
				if(dataT[i].deposit==0){
					data[i][5]="";
				}
				else{
					data[i][5]=String.format("%.2f",dataT[i].deposit);
				}
				if(dataT[i].balance==0){
					data[i][6]="";
				}
				else{
					data[i][6]=String.format("%.2f",dataT[i].balance);
				}
			}
			mainT = new JTable(data,columnName);
			tempD = new JScrollPane(mainT);
			scrollPaneD.setViewport(tempD.getViewport());
			contentPaneLayout.show(contentPane,"View and Delete");
		}
		if(source==mainB[6]){
			fileName=mainF[0].getText()+".ser";
			try{
				FileOutputStream f = new FileOutputStream(new File(fileName));
				ObjectOutputStream o = new ObjectOutputStream(f);
				for(i=0; i<counter; i++){
					o.writeObject(dataT[i]);
				}
				o.close();
				f.close();
			} catch (Exception out){}
		}
		if(source==mainB[7]){
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}
		
		//move back to main menu
		if(source==createB[1]){
			contentPaneLayout.show(contentPane,"Main Menu");
		}
		if(source==loadB[1]){
			contentPaneLayout.show(contentPane,"Main Menu");
		}
		if(source==addB[1]){
			contentPaneLayout.show(contentPane,"Main Menu");
		}
		if(source==searchB[1]){
			contentPaneLayout.show(contentPane,"Main Menu");
		}
		if(source==sortB[1]){
			contentPaneLayout.show(contentPane,"Main Menu");
		}
		if(source==deleteB[1]){
			contentPaneLayout.show(contentPane,"Main Menu");
		}
		
		//create account
		if(source==createB[0]){
			mainF[0].setText(createF[0].getText());
			double bal = Double.valueOf(createF[1].getText());
			mainF[1].setText(String.format("%.2f",bal));
			contentPaneLayout.show(contentPane,"Main Menu");
		}
		
		//load from a file
		if(source==loadB[0]){
			try{
				fileName=loadF.getText()+".ser";
				mainF[0].setText(loadF.getText());
				FileInputStream fi = new FileInputStream(fileName);
				ObjectInputStream oi = new ObjectInputStream(fi);
				i=0;
				while(true){
					dataT[i] = (transaction)oi.readObject();
					mainF[1].setText(String.format("%.2f",dataT[i].balance));
					i++;
					counter=i;
				}			
			} catch(EOFException EOF){ 
			} catch(Exception in){}
			contentPaneLayout.show(contentPane,"Main Menu");
			loadF.setText("");
		}
				
		//set editable textfield for new transaction
		switch (s){
			case "Deposit":
				addMainF[1].setText("");
				addMainF[1].setEditable(false);
				addMainF[3].setText("");
				addMainF[3].setEditable(false);
				addMainF[2].setEditable(true);
				addMainF[4].setEditable(true);
				break;
			case "Automatic Deposit":
				addMainF[1].setText("");
				addMainF[1].setEditable(false);
				addMainF[3].setText("");
				addMainF[3].setEditable(false);
				addMainF[2].setEditable(true);
				addMainF[4].setEditable(true);
				break;
			case "ATM Withdrawal":
				addMainF[1].setText("");
				addMainF[1].setEditable(false);
				addMainF[4].setText("");
				addMainF[4].setEditable(false);
				addMainF[2].setEditable(true);
				addMainF[3].setEditable(true);
				break;
			case "Check":
				addMainF[4].setText("");
				addMainF[4].setEditable(false);
				addMainF[1].setEditable(true);
				addMainF[2].setEditable(true);
				addMainF[3].setEditable(true);
				break;
			case "Debit Card":
				addMainF[1].setText("");
				addMainF[1].setEditable(false);
				addMainF[4].setText("");
				addMainF[4].setEditable(false);
				addMainF[2].setEditable(true);
				addMainF[3].setEditable(true);
				break;
			case "Other":
				addMainF[1].setText("");
				addMainF[1].setEditable(false);
				addMainF[2].setEditable(true);
				addMainF[3].setEditable(true);
				addMainF[4].setEditable(true);
				break;
		}
		
		//save new transaction buttons
		if(source==addB[0]){
			if(!addMainF[0].getText().equals("")){
				balance = Double.valueOf(mainF[1].getText());
				transaction temp = new transaction();
				temp.date = addMainF[0].getText();
				temp.type = (String) addMain2.getSelectedItem();
				if(addMainF[1].getText().equals("")){
					temp.checkNo = 0;
				}
				else{
					temp.checkNo = Integer.valueOf(addMainF[1].getText());
				}
				temp.description = addMainF[2].getText();
				if(addMainF[3].getText().equals("")){
					temp.payment = 0;
				}
				else{
					temp.payment = Double.valueOf(addMainF[3].getText());
					balance -= temp.payment;
				}
				if(addMainF[4].getText().equals("")){
					temp.deposit = 0;
				}
				else{
					temp.deposit = Integer.valueOf(addMainF[4].getText());
					balance += temp.deposit;
				}
				temp.balance=balance;			
				mainF[1].setText(Double.toString(temp.balance));
				dataT[counter]=temp;
				counter++;
				for(i = 0; i<5; i++){
					addMainF[i].setText("");
				}
				addMain2.setSelectedItem("Deposit");
			}
		}
		
		//delete transaction code
		if(source==deleteB[0]){
			int table = mainT.getSelectedRow();
			for(i=table; i<counter; i++){
				dataT[i] = dataT[i+1];
			}
			counter--;
			mainF[1].setText(String.format("%.2f",dataT[counter-1].balance));
			data = new String[counter][7];
			for(i=0; i<counter; i++){
				data[i][0]=dataT[i].date;
				data[i][1]=dataT[i].type;
				if(dataT[i].checkNo==0){
					data[i][2]="";
				}
				else{
					data[i][2]=Integer.toString(dataT[i].checkNo);
				}
				data[i][3]=dataT[i].description;
				if(dataT[i].payment==0){
					data[i][4]="";
				}
				else{
					data[i][4]=String.format("%.2f",dataT[i].payment);
				}
				if(dataT[i].deposit==0){
					data[i][5]="";
				}
				else{
					data[i][5]=String.format("%.2f",dataT[i].deposit);
				}
				if(dataT[i].balance==0){
					data[i][6]="";
				}
				else{
					data[i][6]=String.format("%.2f",dataT[i].balance);
				}
			}
			mainT = new JTable(data,columnName);
			tempD = new JScrollPane(mainT);
			scrollPaneD.setViewport(tempD.getViewport());
		}		
		
	}
	public static void main(String[] args){
		counter = 0;
		i = 0;
		ActionListener AL = new Checkbook();
		frame = new JFrame("Lu's CheckBook");
		frame.setVisible(true);
		frame.setSize(1000,300);	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane=(JPanel)frame.getContentPane();
		contentPane.setLayout(contentPaneLayout=new CardLayout());
		//main menu code
		JPanel main = new JPanel();
		contentPane.add("Main Menu",main);
		contentPaneLayout.show(contentPane,"Main Menu");
		main.setLayout(new BorderLayout());
		JPanel mainP = new JPanel();
		mainP.setLayout(new GridLayout (4,1,2,2));
		JLabel mainL = new JLabel("<html><font size=6>Use The Buttons Below To Manage Transactions</font></html>",JLabel.CENTER);
		JPanel main1 = new JPanel();
		JPanel main2 = new JPanel();
		JPanel mainBlank1 = new JPanel();
		JPanel mainBlank2 = new JPanel();
		main2.setLayout(new GridLayout(2,4,2,2));
		main1.setLayout(new FlowLayout()); //use for account text box and balance
		main.add(mainL,BorderLayout.NORTH);
		main.add(mainP,BorderLayout.CENTER);
		mainP.add(main1);
		mainP.add(mainBlank1);
		mainP.add(mainBlank2);
		mainP.add(main2); 
		mainB[0] = new JButton("Create a New Account");
		mainB[1] = new JButton("Load Trans from a file");
		mainB[2] = new JButton("Add New Transactions");
		mainB[3] = new JButton("Search Transactions");
		mainB[4] = new JButton("Sort Transactions");
		mainB[5] = new JButton("View/Delete Transactions");
		mainB[6] = new JButton("Save Trans to a file");
		mainB[7] = new JButton("Exit");
		for(i = 0; i<8; i++){   //adds the buttons and action listener
			mainB[i].addActionListener(AL);
			main2.add(mainB[i]);
		}
		JLabel acc = new JLabel("Account Name:");
		JLabel bal = new JLabel("Balance:"); //label for textfields
		mainF[0] = new JTextField("",15);
		mainF[0].setEditable(false);
		mainF[0].setHorizontalAlignment(JTextField.LEFT);
		mainF[1] = new JTextField("",15);
		mainF[1].setEditable(false);
		mainF[1].setText("0.00");
		mainF[1].setHorizontalAlignment(JTextField.RIGHT);
		main1.add(acc);
		main1.add(mainF[0]);
		main1.add(bal);
		main1.add(mainF[1]);
		
		//create a new account code
		JPanel createMain = new JPanel();
		contentPane.add("Create Account", createMain);
		JPanel createMainP = new JPanel();
		createMain.setLayout(new BorderLayout());
		createMain.add(createMainP,BorderLayout.CENTER);
		createMain.add(createMainP,BorderLayout.CENTER);
		createMainP.setLayout(new GridLayout(4,1,2,2));
		JPanel createBlank = new JPanel();
		JPanel createMain1 = new JPanel();
		JPanel createMain2 = new JPanel();
		JPanel createMain3 = new JPanel();
		JLabel createL = new JLabel("<html><font size=6>Create a New Account</font></html>", JLabel.CENTER);
		createMain.add(createL,BorderLayout.NORTH);
		createMainP.add(createBlank);
		createMainP.add(createMain1);
		createMainP.add(createMain2);
		createMainP.add(createMain3);
		JLabel cAcc = new JLabel("Account Name:");
		JLabel Ibal = new JLabel("Initial Balance:");
		createB[0] = new JButton("Create");
		createB[1] = new JButton("Cancel");
		for(i = 0; i<2; i++){   //adds the buttons and action listener
			createB[i].addActionListener(AL);
			createMain3.add(createB[i]);
		}
		createF[0] = new JTextField("",15);
		createF[1] = new JTextField("",15);
		createMain1.add(cAcc);
		createMain1.add(createF[0]);
		createMain2.add(Ibal);
		createMain2.add(createF[1]);
		
		//load file code
		JPanel loadMain = new JPanel();
		contentPane.add("Load Account",loadMain);
		loadMain.setLayout(new BorderLayout());
		JPanel loadMainP = new JPanel();
		JPanel loadBlank = new JPanel();
		loadMainP.setLayout(new GridLayout(3,1,2,2));
		JLabel load = new JLabel("<html><font size=6>Load Transactions From a File</font></html>", JLabel.CENTER);
		JPanel loadMain1 = new JPanel();
		JPanel loadMain2 = new JPanel();
		loadMain.add(load,BorderLayout.NORTH);
		loadMain.add(loadMainP,BorderLayout.CENTER);
		loadMainP.add(loadBlank);
		loadMainP.add(loadMain1);
		loadMainP.add(loadMain2);
		JLabel lAcc = new JLabel("Account Name:");
		loadB[0] = new JButton("Load");
		loadB[1] = new JButton("Cancel");
		for(i = 0; i<2; i++){   //adds the buttons and action listener
			loadB[i].addActionListener(AL);
			loadMain2.add(loadB[i]);
		}
		loadMain1.add(lAcc);
		loadMain1.add(loadF);
		
		//new transaction code
		JPanel addMain = new JPanel();
		contentPane.add("New Transaction",addMain);
		addMain.setLayout(new BorderLayout());
		JPanel addMain1 = new JPanel();
		addMain1.setLayout(new GridLayout(6,1,2,2));
		addMain.add(addMain1,BorderLayout.CENTER);
		JPanel addMainB = new JPanel();
		addMain.add(addMainB,BorderLayout.SOUTH);
		JPanel[] addMainx = new JPanel[6];
		for(i = 0; i<6; i++){
			addMainx[i] = new JPanel();
			addMainx[i].setLayout(new GridLayout(1,2));
			addMain1.add(addMainx[i]);
		}
		JLabel[] addMainL = new JLabel[6];
		addMainL[0] = new JLabel("Date",JLabel.RIGHT);
		addMainL[1] = new JLabel("Trans. Type",JLabel.RIGHT);
		addMainL[2] = new JLabel("Check No.",JLabel.RIGHT);
		addMainL[3] = new JLabel("Trans. Description",JLabel.RIGHT);
		addMainL[4] = new JLabel("Payment/Debit(-)",JLabel.RIGHT);
		addMainL[5] = new JLabel("Deposit/Credit(+)",JLabel.RIGHT);
		for(i = 0; i<6; i++){
			addMainx[i].add(addMainL[i]);
		}
		for(i = 0; i<5; i++){
			addMainF[i] = new JTextField("",20);
		}
		addMain2.addItem("Deposit");
		addMain2.addItem("Automatic Deposit");
		addMain2.addItem("ATM Withdrawal");
		addMain2.addItem("Check");
		addMain2.addItem("Debit Card");
		addMain2.addItem("Other");
		addMain2.addActionListener(AL);
		addMainx[0].add(addMainF[0]);
		addMainx[1].add(addMain2);
		for(i = 1; i<5; i++){
			addMainF[i].setText("");
		}
		for(i = 1; i<5; i++){
			addMainx[i+1].add(addMainF[i]);
		}
		addB[0] = new JButton("Save New Transaction");
		addB[1] = new JButton("Top Menu");
		for(i = 0; i<2;i++){
			addB[i].addActionListener(AL);
			addMainB.add(addB[i]);
		}
		
		//search code
		JPanel searchMain = new JPanel();
		contentPane.add("Search Menu",searchMain);
		searchMain.setLayout(new BorderLayout());
		JPanel searchMain1 = new JPanel();
		JPanel searchMain2 = new JPanel();
		JPanel searchMain3 = new JPanel();
		searchMain3.setLayout(new GridLayout(2,1,2,2));
		JLabel searchMainL = new JLabel("Search Transactions by Transaction Date/Type/Check No./Description" ,JLabel.CENTER);
		searchMain.add(searchMainL,BorderLayout.NORTH);
		JScrollPane scrollPaneS = new JScrollPane();
		searchMain.add(scrollPaneS,BorderLayout.CENTER);
		JTable searchMainT = new JTable(data,columnName);
		JScrollPane tempS = new JScrollPane(searchMainT);
		scrollPaneS.setViewport(tempS.getViewport());
		JLabel searchL = new JLabel("Search String");
		searchMain1.add(searchL);
		searchMain1.add(searchF);
		searchB[0] = new JButton("Search");
		searchB[1] = new JButton("Top Menu");
		for(i = 0; i<2;i++){
			searchB[i].addActionListener(AL);
			searchMain2.add(searchB[i]);
		}
		searchMain3.add(searchMain1);
		searchMain3.add(searchMain2);
		searchMain.add(searchMain3,BorderLayout.SOUTH);
		
		//sort transaction code
		JPanel sortMain = new JPanel();
		contentPane.add("Sort Menu",sortMain);
		sortMain.setLayout(new BorderLayout());
		JPanel sortMain1 = new JPanel();
		JPanel sortMain2 = new JPanel();
		JLabel sortMainL = new JLabel("<html><font size=6>Sort Transaction</font></html>",JLabel.CENTER);
		sortMain.add(sortMainL,BorderLayout.NORTH);
		JRadioButton[] sortRB = new JRadioButton[2];
		sortRB[0] = new JRadioButton("By Type");
		sortRB[1] = new JRadioButton("By Date");
		ButtonGroup sortBG = new ButtonGroup();
		for(i = 0; i<2;i++){
			sortRB[i].addActionListener(AL);
			sortBG.add(sortRB[i]);
			sortMain1.add(sortRB[i]);
		}
		sortMain.add(sortMain1,BorderLayout.CENTER);
		sortB[0] = new JButton("Sort");
		sortB[1] = new JButton("Top Menu");
		for(i = 0; i<2;i++){
			sortB[i].addActionListener(AL);
			sortMain2.add(sortB[i]);
		}
		sortMain.add(sortMain2,BorderLayout.SOUTH);
		
		//delete/view transaction code
		JPanel deleteMain = new JPanel();
		contentPane.add("View and Delete",deleteMain);
		deleteMain.setLayout(new BorderLayout());
		JPanel deleteMainB = new JPanel();
		deleteMain.add(deleteMainB,BorderLayout.SOUTH);
		JLabel deleteL = new JLabel("Transactions Currently In The Checkbook",JLabel.CENTER);
		deleteMain.add(deleteL,BorderLayout.NORTH);
		scrollPaneD = new JScrollPane();
		deleteMain.add(scrollPaneD,BorderLayout.CENTER);
		mainT = new JTable(data,columnName);
		tempD = new JScrollPane(mainT);
		scrollPaneD.setViewport(tempD.getViewport());
		deleteB[0] = new JButton("Delete Selected");
		deleteB[1] = new JButton("Top Menu");
		for(i = 0; i<2;i++){
			deleteB[i].addActionListener(AL);
			deleteMainB.add(deleteB[i]);
		}
		
		frame.setVisible(true);
	}
}

class transaction implements Serializable{
	String date;
	String type;
	int checkNo;
	String description;
	double payment;
	double deposit;
	double balance;
	
}