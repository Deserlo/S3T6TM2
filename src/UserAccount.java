import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccount  {
	String role;
	String fname;
	String username;
	String password;
	String team;
	int mgrID;

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
	
	public boolean createAccount(UserAccount newAccount) {	
		boolean accountCreated = false;
		switch (newAccount.role) {
		case "dev": //dev can only join a team
			if (checkName(newAccount.username)==false && checkTeam(newAccount.team)==true) {	
				insertUser(newAccount);
				insertDev(newAccount.username, newAccount.role);
				accountCreated = true;
				break;
			}
			else {
				accountCreated = false;
				break;
			}
		case "mgr": //only manager can create Team
			if (checkName(newAccount.username)==false && checkTeam(newAccount.team)==false ) {
				insertUser(newAccount);
				insertMgr(newAccount.username, newAccount.role);
				createNewTeam(newAccount.username, newAccount.team);
				accountCreated = true;
				break;
			}
			else {
				accountCreated = false;
				break;
			}
		}
		return accountCreated;
	}
	
	private int queryForMgrID(String team) {
		//String queryForID = "SELECT mgrID FROM Team WHERE teamName = ?";
		Query q = new Query("Team", "mgrID","teamName",team);
		String t = q.generateQueryString(q);
		int id = q.getID(q.name, t);
		return id;
	}
	
	public int queryForId(String username) {
		Query q = new Query("User", "id","userName",username);
		String t = q.generateQueryString(q);
		int id = q.getID(q.name, t);
		return id;
	}
		
	//When creating an account, checks whether Team entered exists
	private boolean checkTeam (String team) {
		boolean teamExists = false;
		Query q = new Query("Team", "teamName","teamName",team);
		String t = q.generateQueryString(q);
	    String a = q.getName(q.name, t);
	    if (a.equals(team)) {
	    	teamExists = true;
	    	System.out.println("Team exists.");
	    }
	    return teamExists;	
	}
	//checks that username is unique
	private boolean checkName (String username) {
		boolean nameExists = true;
		Query q = new Query("User", "userName","userName",username);
		String t = q.generateQueryString(q);
	    String a = q.getName(q.name, t);
	    if (a.equals(username)) {
	    	nameExists = true;
	    	System.out.println("username already exists.");
	    }
	    else {
	    	nameExists = false;
	    	System.out.println("username ok.");
	    }
	    return nameExists;	
	}
	
	private void insertUser(UserAccount ua) {
		ua.mgrID = queryForMgrID(ua.team);
		String sql = insertSqlUser(ua.role);
		DBConnection db = new DBConnection();
		db.conn = db.ConnectDB();
		try {
			db.stmt = db.conn.prepareStatement(sql);
			db.stmt.setString(1, ua.username);
			db.stmt.setString(2, ua.fname);
			db.stmt.setString(3, ua.team);
			db.stmt.setString(4, ua.password);
			if (ua.mgrID !=0)
				db.stmt.setInt(5, ua.mgrID);
			db.stmt.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void createNewTeam(String username, String team) {
		int mgrID = queryForId(username);
		DBConnection newTeam = new DBConnection();
		newTeam.conn = newTeam.ConnectDB();
		System.out.println("creating new team with manager'S ID into Team table..");
		try {
			newTeam.stmt = newTeam.conn.prepareStatement("INSERT INTO Team (teamName, mgrID) VALUES (?,?);");
			newTeam.stmt.setString(1, team);
			newTeam.stmt.setInt(2, mgrID);
			newTeam.stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (newTeam.rs != null) try {newTeam.rs.close(); } catch (SQLException ignore) {}
			if (newTeam.stmt != null) try {newTeam.stmt.close(); } catch (SQLException ignore) {}
			if (newTeam.conn != null) try {newTeam.conn.close(); } catch (SQLException ignore) {}
		}	
		System.out.println("new team created..");
	}
				
	private void insertDev(String username, String role) { 
		DBConnection newUser = new DBConnection();
		newUser.conn = newUser.ConnectDB();	
		int devID = queryForId(username);
		String sql = insertSqlRole("Developer");
        try{ 	
			newUser.stmt = newUser.conn.prepareStatement(sql);
			newUser.stmt.setString(1, username);
			newUser.stmt.setInt(2, devID);
			newUser.stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (newUser.rs != null) try {newUser.rs.close(); } catch (SQLException ignore) {}
			if (newUser.stmt != null) try {newUser.stmt.close(); } catch (SQLException ignore) {}
			if (newUser.conn != null) try {newUser.conn.close(); } catch (SQLException ignore) {}		
		}
	}
	
	public void insertMgr(String username, String role) { 		
		String sql = insertSqlRole("Manager");
		DBConnection newUser = new DBConnection();
		newUser.conn = newUser.ConnectDB();
		int id = queryForId(username);	
		System.out.println("adding new manager into Manager table");
        try{ 	
        	newUser.stmt = newUser.conn.prepareStatement(sql);
			newUser.stmt.setString(1, username);
			newUser.stmt.setInt(2, id);
			newUser.stmt.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
	} finally {
		if (newUser.rs != null) try {newUser.rs.close(); } catch (SQLException ignore) {}
		if (newUser.stmt != null) try {newUser.stmt.close(); } catch (SQLException ignore) {}
		if (newUser.conn != null) try {newUser.conn.close(); } catch (SQLException ignore) {}
	}   
		System.out.println("new manager added..");
	}
	
	public boolean updatePassword(String userName, String oldPass, String newPass) {
		boolean b = false;
		Login auth = new Login(userName, oldPass);
		if (auth.authenticateUser(auth)==true) {
			DBConnection update = new DBConnection();
			update.conn = update.ConnectDB();	
			try {
				update.stmt = update.conn.prepareStatement("UPDATE User SET pwd = ? WHERE userName=?;");
				update.stmt.setString(1, newPass);
				update.stmt.setString(2, userName);
				update.stmt.execute();
				b = true;
			} catch (SQLException e1) {
				e1.printStackTrace();
		} finally {
			if (update.rs != null) try {update.rs.close(); } catch (SQLException ignore) {}
			if (update.stmt != null) try {update.stmt.close(); } catch (SQLException ignore) {}
			if (update.conn != null) try {update.conn.close(); } catch (SQLException ignore) {}
		}   
		}
		return b;
	}
	
	
	public static String insertSqlUser(String userRole) {
		String sql="";
		if (userRole.equals("dev")) 
			sql = "INSERT INTO User (userName, fname, team,pwd, mgrID) VALUES (?, ?, ?,?,?);";
		else
			sql = "INSERT INTO User (userName, fname, team,pwd) VALUES (?, ?, ?,?);";
		return sql;
	}
	public static String insertSqlRole(String table) {
		return "INSERT INTO "+ table +" (userName, id) VALUES (?,?);"; }
	
	
}
	



