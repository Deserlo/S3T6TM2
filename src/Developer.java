import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Developer {
	//user session info
	int devID = Test.userID;
	boolean loggedIn = Test.login;
	
	ArrayList<Project> Projects = new ArrayList<Project>();
	TaskLog taskName = new TaskLog();
	public Developer() {
		
	}
	//switch statement here based on menu screen
	//home:     list of projects with project name, last logged taskname, taskduration
	//project: list of projects with project name, time budget, last logged task
	//selected task: taskName, projName, description, start time
	public void retrieveDevProjects(int devID){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		 try { 
			   DBConnection db = new DBConnection();
	    	   conn = db.ConnectDB();
	    	   //need to fix query..
	    	   String query = "SELECT P.ProjName , T.taskName, T.duration FROM Project P, Task T, Works_on W  WHERE  P.ProjNo = W.ProjNo and W.devID = T.devID and T.devID = ?" ;    					  
	           stmt = conn.prepareStatement(query);
	           stmt.setInt(1, devID);
			   rs = stmt.executeQuery();
			   while  (rs.next()) {
					String name = rs.getString(1);
					String taskName = rs.getString(2);
					float duration = rs.getFloat(3);
					Project proj = new Project(name, taskName, duration);
					Projects.add(proj);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (rs != null) try {rs.close(); } catch (SQLException ignore) {}
				if (stmt != null) try {stmt.close(); } catch (SQLException ignore) {}
				if (conn != null) try {conn.close(); } catch (SQLException ignore) {}
			}  
	}

	public ArrayList<Project> DevProjects(){
		return Projects;
	}
	
	public TaskLog selection() {
		//returns a selected task with taskName, projName, description, start time
		return null;
		
	}
	
}
