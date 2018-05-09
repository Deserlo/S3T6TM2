import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class Manager {
	//user session info
	 int mgrID = Test.userID;
	 boolean loggedIn =Test.login; //check logged in status
	 
	 
	 public String[][] getMgrHours(int mgrID){
			String[][] noProjects = null;
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			 try { 
				   DBConnection db = new DBConnection();
		    	   conn = db.ConnectDB();
		    	   String query =  "SELECT P.projName, T.taskName as LastTask, P.progress as Progress " + 
		    	   		"FROM Project P, Task T " + 
		    	   		"WHERE  P.projNo = T.projNo and P.mgrID = ? " + 
		    	   		"GROUP BY P.ProjName; ";
		           stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		           stmt.setInt(1, mgrID);
				   rs = stmt.executeQuery();
				   int i=0;
				   if (rs.last()) {
					    int rows = rs.getRow();
					    String [][] results =new String[rows][3];
					    // Move to beginning
					    rs.beforeFirst();
					    while  (rs.next()) {
							String name = rs.getString(1);
							String taskName = rs.getString(2);
							float progress = rs.getFloat(3) * 100;				
							results[i] = new String[] {name, taskName,String.valueOf(progress + "%")};
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
		
	//returns list of manager's developers by fname
	public String[] getMgrDevNames(int mgrID){
		String[] emptySet = {};
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		 try { 
			   DBConnection db = new DBConnection();
	    	   conn = db.ConnectDB();
	    	   String query =  "SELECT U.Fname " + 
	    	   		"FROM User U " + 
	    	   		"WHERE  U.mgrID=?; ";
	           stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	           stmt.setInt(1, mgrID);
			   rs = stmt.executeQuery();
			   int i=0;
			   if (rs.last()) {
				    int rows = rs.getRow();
				    String [] results =new String[rows];
				    // Move to beginning
				    rs.beforeFirst();
				    while  (rs.next()) {
						String devName = rs.getString(1);
						results[i] = devName;
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
		return emptySet; 	
	}
	
	//returns list of projects manager is leading by projName
	public String[] getMgrProjects(int mgrID)
    { 
        String[] emptyProjects = {}; 
        Connection conn = null; 
        PreparedStatement stmt = null;
        ResultSet rs = null;   
        try
        { 
            DBConnection db = new DBConnection(); 
            conn = db.ConnectDB();
            String query = "SELECT projName " + 
                           "FROM Manager, Project " + 
                           "WHERE Manager.id = Project.mgrID AND mgrID = ?; ";
            stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
            stmt.setInt(1, mgrID);
            rs = stmt.executeQuery();
            int i = 0;
            if(rs.last())
            {
                int rows = rs.getRow();
                String[] results = new String[rows]; 
                // Move to beginning
                rs.beforeFirst();
                while(rs.next())
                {
                    String projName = rs.getString(1);
                    results[i] = projName;
                    i++;
                }
                return results;
            }
        }
        catch(SQLException e)
        { 
            e.printStackTrace();
        }
        finally
        { 
            if(rs != null) try{rs.close();} catch (SQLException ignore) {}
            if(stmt != null) try {stmt.close();} catch (SQLException ignore) {}
            if(conn != null) try {conn.close();} catch (SQLException ignore){}
        }
        return emptyProjects;
    }
	
	//returns list of tasks for a selected project by taskName
	public String[][] TasksForProjName(String projName){
		  String[][] noTasks = {{"No tasks completed yet.", "", ""}}; 
	        Connection conn = null; 
	        PreparedStatement stmt = null;
	        ResultSet rs = null;   
	        try
	        { 
	            DBConnection db = new DBConnection(); 
	            conn = db.ConnectDB();
	            String query = "SELECT T.taskName as Task, T.duration as Time, T.description as Description " + 
	            		"FROM Task T, Project P " + 
	            		"WHERE T.projNo = P.projNo and P.projName = ?; ";
	            stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
	            stmt.setString(1, projName);
	            rs = stmt.executeQuery();
	            int i = 0;
	            if(rs.last())
	            {
	                int rows = rs.getRow();
	                String [][] results =new String[rows][3];
	                // Move to beginning
	                rs.beforeFirst();
	                while(rs.next())
	                {
						String name = rs.getString(1);
						float duration = rs.getFloat(2);
						String description = rs.getString(3);					
						results[i] = new String[] {name,String.valueOf(duration), description};
						i++;
	                }
	                return results;
	            }
	        }
	        catch(SQLException e)
	        { 
	            e.printStackTrace();
	        }
	        finally
	        { 
	            if(rs != null) try{rs.close();} catch (SQLException ignore) {}
	            if(stmt != null) try {stmt.close();} catch (SQLException ignore) {}
	            if(conn != null) try {conn.close();} catch (SQLException ignore){}
	        }
	        return noTasks;
	}
	
	//returns list of completed tasks for a selected developer
	public String[][] TasksForDeveloper(String devName)  { 
        String[][] noTasks = {{"No tasks completed yet.", "", ""}}; 
        Connection conn = null; 
        PreparedStatement stmt = null;
        ResultSet rs = null;   
        try
        { 
            DBConnection db = new DBConnection(); 
            conn = db.ConnectDB();
            String query = "SELECT T.taskName as Task, T.duration as Time, T.description as Description " + 
            		"FROM Task T, User U " + 
            		"WHERE T.devID = U.id and U.fname = ?; ";
            stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
            stmt.setString(1, devName);
            rs = stmt.executeQuery();
            int i = 0;
            if(rs.last())
            {
                int rows = rs.getRow();
                String [][] results =new String[rows][3];

                // Move to beginning
                rs.beforeFirst();
                while(rs.next())
                {
					String name = rs.getString(1);
					float duration = rs.getFloat(2);
					String description = rs.getString(3);
									
					results[i] = new String[] {name,String.valueOf(duration), description};
					i++;

                }
                return results;
            }
        }
        catch(SQLException e)
        { 
            e.printStackTrace();
        }
        finally
        { 
            if(rs != null) try{rs.close();} catch (SQLException ignore) {}
            if(stmt != null) try {stmt.close();} catch (SQLException ignore) {}
            if(conn != null) try {conn.close();} catch (SQLException ignore){}
        }
        return noTasks;
    }
	
	
	//{"Project","Budget","People"}
	public String[][] getProjectsMgr(int mgrID)  { 
        String[][] noProjects = null;
		PreparedStatement getPeople = null;
        Connection conn = null; 
        PreparedStatement stmt = null;
        ResultSet rs = null;   
        try
        { 
            DBConnection db = new DBConnection(); 
            conn = db.ConnectDB();
            String query = "SELECT P.projNo, P.projName, P.timeBudget " + 
            		"FROM Project P " + 
            		"WHERE P.mgrID = ? ";
            stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
            stmt.setInt(1, mgrID);
            rs = stmt.executeQuery();
            int i = 0;
            if(rs.last())
            {
                int rows = rs.getRow();
                String [][] results =new String[rows][3];
                // Move to beginning
                rs.beforeFirst();
                while(rs.next()){
					int projNo = rs.getInt(1);
					String projName = rs.getString(2);
					float timeBudget = rs.getFloat(3);
					getPeople = conn.prepareStatement("Select U.fname "+
													"FROM User U, Works_on W " + 
													"WHERE U.id = W.devID and W.projNo = ?; ");
					getPeople.setInt(1, projNo);
					db.rs = getPeople.executeQuery();
					String people = "";
					int x = 0;
					if (db.rs.last()) {
						int numPeople = db.rs.getRow();	
						db.rs.beforeFirst();
						while (db.rs.next()){
							String person = db.rs.getString(1);
							if (x!=numPeople-1)
								people += person + ", ";
							else
								people += person;
							x++;
						}
						results[i] = new String[] {projName, String.valueOf(timeBudget), people};
						i++;
					}		
                }
                return results;
            }
        }
        catch(SQLException e)
        { 
            e.printStackTrace();
        }
        finally
        { 
            if(rs != null) try{rs.close();} catch (SQLException ignore) {}
            if(stmt != null) try {stmt.close();} catch (SQLException ignore) {}
            if(conn != null) try {conn.close();} catch (SQLException ignore){}
        }
        return noProjects;
    }	
}
