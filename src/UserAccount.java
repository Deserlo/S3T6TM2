import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccount  {
	private String role;
	private String fname;
	String username;
	private String password;
	private String team;

	public UserAccount(String role, String fname, String username, String password, String team) {
		this.role = role;
		this.fname = fname;
		this.username = username;
		this.password = password;
		this.team = team;
	}
	
	public UserAccount(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public void addNewAccount(UserAccount newAccount) {
		switch (newAccount.role) {
		case "dev": //dev can only join a team
			if (checkIfTeamExists(newAccount.team)==true) {
				addNewDev(newAccount.username, newAccount.fname, newAccount.team, newAccount.password); 		
				break;
			}
			else {
				break;
			}
		case "mgr": //only manager can create Team
			if (checkIfTeamExists(newAccount.team)==false) {
				addNewMgr(newAccount.username, newAccount.fname, newAccount.team, newAccount.password);
				break;
			}
			else {
				break;
			}
		}
	
	}
	
	private int queryForMgrID(String team) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
        try { 
		   DBConnection db = new DBConnection();
    	   conn = db.ConnectDB();
    	   String queryForID = "SELECT mgrID FROM Team WHERE teamName = ?";
           stmt = conn.prepareStatement(queryForID);
           stmt.setString(1, team);
			rs = stmt.executeQuery();
			while(rs.next()) {
				 int ID = rs.getInt(1);
				 	return ID;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try {rs.close(); } catch (SQLException ignore) {}
			if (stmt != null) try {stmt.close(); } catch (SQLException ignore) {}
			if (conn != null) try {conn.close(); } catch (SQLException ignore) {}
		}
        return 0;
	}
	
	
	public int queryForId(String username) {
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
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (rs != null) try {rs.close(); } catch (SQLException ignore) {}
				if (stmt != null) try {stmt.close(); } catch (SQLException ignore) {}
				if (conn != null) try {conn.close(); } catch (SQLException ignore) {}
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
    	   String queryForName = "SELECT teamName FROM Team WHERE teamName = ?";
           stmt = conn.prepareStatement(queryForName);
           stmt.setString(1, teamName);
			rs = stmt.executeQuery();
			String name = rs.getString(2);
				 if (name.equals(teamName)) {
				    return true;
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try {rs.close(); } catch (SQLException ignore) {}
			if (stmt != null) try {stmt.close(); } catch (SQLException ignore) {}
			if (conn != null) try {conn.close(); } catch (SQLException ignore) {}
		}     
        System.out.println("Team not yet created.");
        return false;
	}
	
	private void createNewTeam(int mgrID, String team) {
		String sql ="INSERT INTO Team (mgrID, teamName)" + " VALUES (?,?)";	
		DBConnection newUser = new DBConnection();
		Connection conn = newUser.ConnectDB();
		System.out.println("creating new team with manager'S ID into Team table..");
		try {
			PreparedStatement prepstmt = conn.prepareStatement(sql);
			prepstmt.setInt(1, mgrID);
			prepstmt.setString(2, team);
			prepstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) try {conn.close(); } catch (SQLException ignore) {}
		}	
		System.out.println("new team created..");
		
	}
	
	
	private void addNewDev(String username, String fname, String team, String password) { 
		String sqluser ="INSERT INTO User (userName, fname, team,pwd, mgrID)" + " VALUES (?,?, ?, ?, ?)";	
		DBConnection newUser = new DBConnection();
		Connection conn = newUser.ConnectDB();
		System.out.println("adding new user(dev) into User table..");
		int mgrID = queryForMgrID(team);
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) try {conn.close(); } catch (SQLException ignore) {}
		}
	}
	
	private void addNewMgr(String username, String fname, String team, String password ) { 		
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
	} finally {
		if (conn != null) try {conn.close(); } catch (SQLException ignore) {}
	}   
		System.out.println("new manager added..");
		createNewTeam(id, team);
	}


	
	public boolean authenticateUser(UserAccount account) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean login = false;
		System.out.println("authenticating user "+account.username+ "...");;
        try { 
		   DBConnection db = new DBConnection();
    	   conn = db.ConnectDB();
    	   String query = "SELECT userName, pwd FROM user WHERE userName =? and pwd=?";
           stmt = conn.prepareStatement(query);
           stmt.setString(1, account.username);
           stmt.setString(2, account.password);
		   rs = stmt.executeQuery();
		   while(rs.next()) {
		        String checkUser = rs.getString("userName");
		        String checkPass = rs.getString("pwd");
		        System.out.println(checkUser);
		        System.out.println(checkPass);
		        if((checkUser.equals(account.username)) && (checkPass.equals(account.password))) {
			           login = true;
			           System.out.println("login authorized.");
			    }
			    else {
			          login = false;       
			        }
		    }
		   System.out.println("please check login credentials.");
        }  catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        return login;
	}
}
	



