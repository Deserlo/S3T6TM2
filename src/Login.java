import java.sql.SQLException;

public class Login {
	
	private boolean login;
	private String username;
	private String password;
	private int UserID;
	
	public Login(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUserName() {
		return this.username;
	}
	public boolean getLogin() {
		return this.login;
	}
	public void setUserID(int currentUserID) {
		UserID = currentUserID;	
	}
	public void SetLoginStatus(boolean activeLogin) {
		login = activeLogin;
	}
	public void setUserName(String name) {
		username = name;	
	}
	
	public int queryForId(String username) {
		Query q = new Query("user", "id","userName",username);
		String t = q.generateQueryString(q);
		int id = q.getID(q.name, t);
		return id;
	}
	//checks developer table to see if user exists and assigns user role
	//if user doesn't exist in developer table, checks manager table
	public String getUserRole(int id) {
		String role = "";
		Query q = new Query("developer", "id","id","id");
		String devQuery = q.generateQueryString(q);
		//SELECT id FROM developer WHERE id=?
		if (q.checkIfExists(id, devQuery)==true)
			role = "developer";
		else {
			q.tableName = "manager";
			String mgrQuery = q.generateQueryString(q);
			if (q.checkIfExists(id, mgrQuery)==true)
				role = "manager";
		}
		return role;
	}
	
	public boolean authenticateUser(Login existingAccount) {
		boolean login = false;
		System.out.println("authenticating user "+existingAccount.username+ "...");
		DBConnection db = new DBConnection();
    	db.conn = db.ConnectDB();
        try { 
    	   String query = "SELECT userName, pwd FROM User WHERE userName =? and pwd=?";
           db.stmt = db.conn.prepareStatement(query);
           db.stmt.setString(1, existingAccount.username);
           db.stmt.setString(2, existingAccount.password);
		   db.rs = db.stmt.executeQuery();
		   while(db.rs.next()) {
		        String checkUser = db.rs.getString("userName");
		        String checkPass = db.rs.getString("pwd");
		        if((checkUser.equals(existingAccount.username)) && (checkPass.equals(existingAccount.password))) {
			           login = true;
			           System.out.println("login authorized.");
			    }
			    else {
			          login = false; 
			        }
		    }
        }  catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
    	} finally {
    		if (db.rs != null) try {db.rs.close(); } catch (SQLException ignore) {}
    		if (db.stmt != null) try {db.stmt.close(); } catch (SQLException ignore) {}
    		if (db.conn != null) try {db.conn.close(); } catch (SQLException ignore) {}
    	} 
        if (login == false)
        	System.out.println("please check login credentials.");
        return login;
	}
}

