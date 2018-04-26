import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class Developer {
	//user session info
	int devID = Test.userID;
	boolean loggedIn = Test.login; //check logged in status	
	int screen = 1;
	//int screen = Test.MenuVar;
	int columns = 3;
	public Developer() {}
	//home:    list of projects with project name, last logged taskname, taskduration
	//project: list of projects with project name, time budget, last logged task
	
	public String queryForDisplayingTableInfo(int screen){
		String query="";
		switch(screen) {
			case 1: query = "SELECT P.ProjName , T.taskName, (T.duration) FROM Project P, Task T, Works_on W  WHERE  P.ProjNo = W.ProjNo and  W.devID = T.devID and T.devID = ? and T.duration is not null;" ;
			break;
			case 2:query = "";
			break;
			case 3: query = "";
			break;
		}
		return query;
	}
	public String[][] DevProjects(int devID){
		String[][] emptyArray = {{}};
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		 try { 
			   DBConnection db = new DBConnection();
	    	   conn = db.ConnectDB();
	    	   String query =  queryForDisplayingTableInfo(screen); 
	           stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	           stmt.setInt(1, devID);
			   rs = stmt.executeQuery();
			   int i=0;
			   if (rs.last()) {
				    int rows = rs.getRow();
				    String [][] results =new String[rows][columns];
				    System.out.println("number of results: " + rows);
				    // Move to beginning
				    rs.beforeFirst();
				    while  (rs.next()) {
						String name = rs.getString(1);
						String taskName = rs.getString(2);
						float duration = rs.getFloat(3);				
						results[i] = new String[] {name, taskName,String.valueOf(duration)};
						i++;
					}
					return results;
				}
			   	  
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (rs != null) try {rs.close(); } catch (SQLException ignore) {}
				if (stmt != null) try {stmt.close(); } catch (SQLException ignore) {}
				if (conn != null) try {conn.close(); } catch (SQLException ignore) {}
			}
		return emptyArray; 
	}	
	
	public static void main(String[]args) {
		Developer newDev = new Developer();
		String [][] Test = newDev.DevProjects(2);
		System.out.println(Test[0][0]);
		System.out.println(Test[0][1]);
		System.out.println(Test[0][2]);
		System.out.println(Test[1][0]);
		System.out.println(Test[1][1]);
		System.out.println(Test[1][2]);      	
	}
}
