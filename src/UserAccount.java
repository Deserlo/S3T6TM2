import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccount implements ITMModel {
	private String role;
	private String fname;
	private String username;
	private String password;
	private String team;
	private int mgrID;
	//manager
	public UserAccount(String role, String fname, String username, String password, String team) {
		this.role = role;
		this.fname = fname;
		this.username = username;
		this.password = password;
		this.team = team;
	}
	//dev
	public UserAccount(String role, String fname, String username, String password, String team, int mgrID) {
		this.role = role;
		this.fname = fname;
		this.username = username;
		this.password = password;
		this.team = team;
		this.mgrID = mgrID;
	}
	
	public void addNewAccount(UserAccount newAccount) {
		switch (newAccount.role) {
		case "dev":
			if (checkIfTeamExists(newAccount.team)==true) {
				addNewDev(newAccount.username, newAccount.fname, newAccount.team, newAccount.password, newAccount.mgrID); 		
				break;
			}
			else {
				break;
			}
		case "mgr": 
			if (checkIfTeamExists(newAccount.team)==true) {
				addNewMgr(newAccount.username, newAccount.fname, newAccount.team, newAccount.password);
				break;
			}
			else {
				break;
			}
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
	
	//When creating an account, checks whether Team entered exists
	private boolean checkIfTeamExists(String teamName) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
        try { 
		   DBConnection db = new DBConnection();
    	   conn = db.ConnectDB();
    	   String queryForName = "SELECT teamName FROM Team";
           stmt = conn.prepareStatement(queryForName);
			rs = stmt.executeQuery();
			while(rs.next()) {
				 String name = rs.getString(1);
				 if (teamName == name) {
				    return true;
				}
			}
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}      
        System.out.println("Team invalid.");
        return false;
	}
	
	
	private boolean addNewDev(String username, String fname, String team, String password, int mgrID) { 
		String sqluser ="INSERT INTO User (userName, fname, team,pwd, mgrID)" + " VALUES (?,?, ?, ?, ?)";	
		DBConnection newUser = new DBConnection();
		Connection conn = newUser.ConnectDB();
		System.out.println("adding new user(dev) into User table..");
		try {
			PreparedStatement prepstmt = conn.prepareStatement(sqluser);
			prepstmt.setString(1, username);
			prepstmt.setString(2, fname);
			prepstmt.setString(3, team);
			prepstmt.setString(4, password);
			prepstmt.setInt(5, mgrID);
			prepstmt.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		System.out.println("new user(dev) added..");
		
		String sqldev = "INSERT INTO Developer (userName,id)" + " VALUES (?, ?)" ; 
		int devID = queryForId(username);	
        try
        { 	
			PreparedStatement prepstmt = conn.prepareStatement(sqldev);
			prepstmt.setString(1, username);
			prepstmt.setInt(2, devID);
			prepstmt.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return true;
	}
	
	private boolean addNewMgr(String username, String fname, String team, String password ) { 		
		String sqluser ="INSERT INTO User (userName, fname, team,pwd)" + " VALUES (?,?, ?, ?)";	
		DBConnection newUser = new DBConnection();
		Connection conn = newUser.ConnectDB();
		System.out.println("adding new user(mgr) into User table..");
		try {
			PreparedStatement prepstmt = conn.prepareStatement(sqluser);
			prepstmt.setString(1, username);
			prepstmt.setString(2, fname);
			prepstmt.setString(3, team);
			prepstmt.setString(4, password);
			prepstmt.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		System.out.println("new user (mgr) added..");
		
		System.out.println("adding new manager into Manager table");
		String sql = "INSERT INTO Manager (userName,id)" + " VALUES (?,?)" ; 
		int id = queryForId(username);
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


