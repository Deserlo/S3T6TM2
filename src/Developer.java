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
	int columns = 3;
	public Developer() {}
	
	public String[][] getDevHours(int devID){
		String[][] noProjects = {{"No projects to display!"," "," "}};
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		 try { 
			   DBConnection db = new DBConnection();
	    	   conn = db.ConnectDB();
	    	   String query =  "SELECT P.ProjName, T.taskName, T.duration " + 
	    	   		"FROM Project P, Task T, Works_on W " + 
	    	   		"WHERE  P.ProjNo = W.ProjNo and  W.devID = T.devID " + 
	    	   		"and T.devID = ?  and T.duration is not null " + 
	    	   		"GROUP BY P.ProjName " + 
	    	   		"HAVING MAX(T.end);" ;
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
		return noProjects; 
	}	
	
	
	public String[][] getDevProjects(int devID){
			String[][] noProjects = {{"No projects to display!"," "," "}};
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			 try { 
				   DBConnection db = new DBConnection();
		    	   conn = db.ConnectDB();
		    	   // # of ? in query indicates #of parameters to set in stmt.setInt(1, devID)
		    	   // if 2 ? then stmt.setInt (2, someIntVar) would be next line or stmt.setString (2, someStringVar) if parameter was string
		    	   String query =  "SELECT P.ProjName, P.timeBudget, T.taskName " + 
		    	   		"FROM Project P, Task T, Works_on W " + 
		    	   		"WHERE  P.ProjNo = W.ProjNo and  W.devID = T.devID " + 
		    	   		"and T.devID = ?  and T.duration is not null " + 
		    	   		"GROUP BY P.ProjName " + 
		    	   		"HAVING MAX(T.end);";
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
							String pName = rs.getString(1);
							float pBudget = rs.getFloat(2);
							String pLastTask = rs.getString(3);
											
							results[i] = new String[] {pName, String.valueOf(pBudget), pLastTask};
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
			return noProjects; 
		}	

	
	public String[] getAllDevReports(int devID){
		String[] noReports = {};
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		 try { 
			   DBConnection db = new DBConnection();
	    	   conn = db.ConnectDB();
	    	   String query =  "SELECT P.ProjName " + 
	    	   		"FROM Project P,  Works_on W " + 
	    	   		"WHERE  P.ProjNo = W.ProjNo and  W.devID = ?; ";
	           stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	           stmt.setInt(1, devID);
			   rs = stmt.executeQuery();
			   int i=0;
			   if (rs.last()) {
				    int rows = rs.getRow();
				    String [] results =new String[rows];
				    System.out.println("number of results: " + rows);
				    // Move to beginning
				    rs.beforeFirst();
				    while  (rs.next()) {
						String projName = rs.getString(1);
						results[i] = projName;
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
		return noReports; 
	}	
	//task, time, description for a selected projectName
	public String[][] getDevReport(int devID, String projName){
		String[][] noReportInfoToDisplay ={{}};
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		 try { 
			   DBConnection db = new DBConnection();
	    	   conn = db.ConnectDB();
	    	   String query =  "SELECT T.taskName, T.duration, T.description " + 
	    	   		"FROM Task T, Works_on W, Project P " + 
	    	   		"WHERE  P.ProjNo = W.ProjNo and W.devID = T.devID " + 
	    	   		"and T.devID = ? and T.duration is not null and P.projName = ?;"; 
	           stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	           stmt.setInt(1, devID);
	           stmt.setString(2, projName);
			   rs = stmt.executeQuery();
			   int i=0;
			   if (rs.last()) {
				    int rows = rs.getRow();
				    String [][] results = new String[rows][columns];
				    System.out.println("number of results: " + rows);
				    // Move to beginning
				    rs.beforeFirst();
				    while  (rs.next()) {
						String tName = rs.getString(1);
						float tDuration = rs.getFloat(2);
						String tDescription = rs.getString(3);				
						results[i] = new String[] {tName, String.valueOf(tDuration), tDescription};
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
		return noReportInfoToDisplay; 
	}

}
