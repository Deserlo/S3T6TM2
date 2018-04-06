import java.sql.*;

public class DBConnection {	

	public Connection ConnectDB() {
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		//need to hide DB server information 
		String DB_URL = "jdbc:mysql://athena.ecs.csus.edu:3306/tm";
		String user = "";
		String pass = "";
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
