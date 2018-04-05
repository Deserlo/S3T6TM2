import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccount {
	String role;
	String username;
	String password;
	int mgrID;
	//manager
	public UserAccount(String role, String username, String password) {
		this.role = role;
		this.username = username;
		this.password = password;
	}
	//dev
	public UserAccount(String role, String username, String password, int mgrID) {
		this.role = role;
		this.username = username;
		this.password = password;
		this.mgrID = mgrID;
	}
	public void addNewAccount(UserAccount newAccount) {
		switch (newAccount.role) {
		case "dev":
			if (checkIfMgrIDExists(newAccount.mgrID)==true) {
				addNewUser(newAccount.username, newAccount.password);
				addNewDev(newAccount.username, newAccount.mgrID); 		
				break;
			}
			else {
				break;
			}
		case "mgr":  
			addNewUser(newAccount.username, newAccount.password);
			addNewMgr(newAccount.username);
			break;
		}	
	}
	
	
	private int queryForId(String username) {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
	       try { 
			   DBConnection id = new DBConnection();
	    	   conn = id.ConnectDB();
	    	   String queryForId = "SELECT id FROM User WHERE userName =?";
	           stmt = conn.prepareStatement(queryForId);
	           stmt.setString(1, username);
			   rs = stmt.executeQuery();
			   while(rs.next()) {
				   int ID = rs.getInt(1);
				   return ID;
			   }	
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}      
	       System.out.println("query for ID failed");
	       return 0;
	}
	
	//When new Developer creates an account, checks whether Mgr ID entered exists
	private boolean checkIfMgrIDExists(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
        try { 
		   DBConnection db = new DBConnection();
    	   conn = db.ConnectDB();
    	   String queryForId = "SELECT id FROM Manager";
           stmt = conn.prepareStatement(queryForId);
			rs = stmt.executeQuery();
			while(rs.next()) {
				 int ID = rs.getInt(1);
				 if (ID ==id) {
				    return true;
				}
			}
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}      
        System.out.println("Manager ID invalid.");
        return false;
	}
	
	
	private boolean addNewDev(String username, int mgrID) { //adds new tuple into Developer table
		DBConnection newDev = new DBConnection();
		Connection conn = newDev.ConnectDB();
		String sql = "INSERT INTO Developer (userName,id, mgrID)" + " VALUES (?, ?, ?)" ; 
		int devID = queryForId(username);
		System.out.println("adding new developer into Developer table..");
        try
        { 	
			PreparedStatement prepstmt = conn.prepareStatement(sql);
			prepstmt.setString(1, username);
			prepstmt.setInt(2, devID);
			prepstmt.setInt(3, mgrID);
			prepstmt.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        System.out.println("new developer added..");
		return true;
	}
	
	private boolean addNewMgr(String username) { //adds new tuple into Manager table
		DBConnection newMgr = new DBConnection();
		Connection conn = newMgr.ConnectDB();
		String sql = "INSERT INTO Manager (userName,id)" + " VALUES (?,?)" ; 
		int id = queryForId(username);
		System.out.println("adding new manager into Manager table");
        try
        { 	
			PreparedStatement prepstmt = conn.prepareStatement(sql);
			prepstmt.setString(1, username);
			prepstmt.setInt(2, id);
			prepstmt.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("new manager added..");
		return true;
	}

	private boolean addNewUser(String username, String password) { //adds new tuple into User table
		String sql ="INSERT INTO User (userName, pwd)" + " VALUES (?,?)";	
		DBConnection newUser = new DBConnection();
		Connection conn = newUser.ConnectDB();
		System.out.println("adding new user into User table..");
		try {
			PreparedStatement prepstmt = conn.prepareStatement(sql);
			prepstmt.setString(1, username);
			prepstmt.setString(2, password);
			prepstmt.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("new user added..");
		return true;
	}
	
	public boolean authenticateUser(String username, String password) {
		DBConnection authUser = new DBConnection();
		Connection conn = authUser.ConnectDB();
	    System.out.println("Checking whether user exists in DB and verifying password");
	    ////query statement here
	    //System.out.println("Credentials verified...");
	    //System.out.println("Unable to verify please re-enter username and password...");
	    return true;
	}
}


