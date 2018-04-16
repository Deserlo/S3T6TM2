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
	
	public boolean createAccount(UserAccount newAccount) {
		Statement stmtUser = new Statement("User");	
		boolean accountCreated = false;
		switch (newAccount.role) {
		case "dev": //dev can only join a team
			if (checkName(newAccount.username)==false && checkTeam(newAccount.team)==true) {
				Statement stmtDev = new Statement("Developer");		
				insertUser(newAccount, stmtUser);
				insertDev(newAccount, stmtDev);
				accountCreated = true;
				break;
			}
			else {
				accountCreated = false;
				break;
			}
		case "mgr": //only manager can create Team
			if (checkName(newAccount.username)==false && checkTeam(newAccount.team)==false ) {
				insertUser(newAccount, stmtUser);
				Statement stmtMgr = new Statement("Manager");
				Statement stmtTeam = new Statement("Team");
				insertMgr(newAccount, stmtMgr);
				createNewTeam(newAccount, stmtTeam);
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
		Query q = new Query("team", "mgrID","teamName",team);
		String t = q.genQuery(q);
		int id = q.getID(q.name, t);
		return id;
	}
	
	private int queryForId(String username) {
		Query q = new Query("user", "id","userName",username);
		String t = q.genQuery(q);
		int id = q.getID(q.name, t);
		return id;
	}
		
	//When creating an account, checks whether Team entered exists
	private boolean checkTeam (String team) {
		boolean teamExists = false;
		Query q = new Query("Team", "teamName","teamName",team);
		String t = q.genQuery(q);
	    String a = q.getName(q.name, t);
	    if (a.equals(team)) {
	    	teamExists = true;
	    	System.out.println("Team exists.");
	    }
	    return teamExists;	
	}
	
	private boolean checkName (String username) {
		boolean nameExists = true;
		Query q = new Query("User", "userName","userName",username);
		String t = q.genQuery(q);
	    String a = q.getName(q.name, t);
	    if (a.equals(username)) {
	    	nameExists = true;
	    	System.out.println("username already exists.");
	    }
	    else {
	    	nameExists = false;
	    }
	    return nameExists;	
	}
	
	
	private void insertUser(UserAccount ua, Statement s) {
		ua.mgrID = queryForMgrID(ua.team);
		ua.prepUserCols(s);	
		if(ua.mgrID ==0) {//removes mgrID field from insert statement
			s.colNames.remove(4);
		}
		String sql = ua.genTaskStmt(s);
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
	
	private void createNewTeam(UserAccount ua, Statement s) {
		int mgrID = ua.queryForId(ua.username);
		ua.prepTeamCols(s);	
		String sql = ua.genTaskStmt(s);
		DBConnection newTeam = new DBConnection();
		newTeam.conn = newTeam.ConnectDB();
		System.out.println("creating new team with manager'S ID into Team table..");
		try {
			newTeam.stmt = newTeam.conn.prepareStatement(sql);
			newTeam.stmt.setInt(1, mgrID);
			newTeam.stmt.setString(2, ua.team);
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
		
	private void prepUserCols(Statement s) {
		//must match db column names
		s.addCols("userName");
		s.addCols("fname");
		s.addCols("team");
		s.addCols("pwd");
		s.addCols("mgrID");	
	}
	
	private void prepTeamCols(Statement s) {
		//must match db column names
		s.addCols("teamName");
		s.addCols("mgrID");	
	}
	
	private String genTaskStmt(Statement s) {
	    String sql = s.genInsertStmtStr(s);
	    return sql;
	}
		
	private void insertDev(UserAccount ua, Statement s) { 
		s.addCols("userName");
		s.addCols("id");
		DBConnection newUser = new DBConnection();
		newUser.conn = newUser.ConnectDB();	
		int devID = ua.queryForId(ua.username);	
		String sql = ua.genTaskStmt(s);
        try{ 	
			newUser.stmt = newUser.conn.prepareStatement(sql);
			newUser.stmt.setString(1, ua.username);
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
	
	private void insertMgr(UserAccount ua, Statement s) { 		
		//String sqluser ="INSERT INTO User (userName, fname, team,pwd)" + " VALUES (?,?, ?, ?)";
		s.addCols("userName");
		s.addCols("id");	
		DBConnection newUser = new DBConnection();
		newUser.conn = newUser.ConnectDB();
		int id = ua.queryForId(ua.username);	
		String sql = ua.genTaskStmt(s);
		System.out.println("adding new manager into Manager table");
        try{ 	
        	newUser.stmt = newUser.conn.prepareStatement(sql);
			newUser.stmt.setString(1, ua.username);
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
	
	
	public static void main(String[] args) {

		//UserAccount(String role, String fname, String username, String password, String team)
		UserAccount test = new UserAccount("dev","jobe","jobe","11fsd11","lakers");
		UserAccount test1 = new UserAccount("mgr","bill","bill","11fsd11","lakers");
	    test.createAccount(test);
	    test.createAccount(test1);
	    //test.createNewTeam(test, stmtTeam);


	}

	
}
	



