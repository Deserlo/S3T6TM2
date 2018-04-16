import java.sql.*;

public class DBConnection {	
	Connection conn;
	PreparedStatement stmt;
	ResultSet rs;
	public DBConnection() {
		conn = null;
		stmt = null;
		rs = null;
	}

	public Connection ConnectDB() {
		//athena db server:
		/*
		String DB_URL = "jdbc:mysql://athena.ecs.csus.edu:3306/test_tm";
		String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
		String user = "tm_user";
		String pass = "tm_team6";
		*/
		
		//Local Host
		String DB_URL = "jdbc:mysql://localhost:3306/tm";
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String user = "root";
		String pass = "root";

		Connection conn = null;
		   try{
		      Class.forName(JDBC_DRIVER).newInstance();
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, user, pass);
		      System.out.println("Connected to  database successfully..."); 
		   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
		   }
			  return conn;
    }
	
}