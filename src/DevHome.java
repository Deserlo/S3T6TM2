import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DevHome {
	ArrayList<Project> Projects = new ArrayList<Project>();
	public DevHome() {
		retrieveDevProjects(Test.userID);
	}	
	public void retrieveDevProjects(int devID){//project name, last logged taskname, taskduration
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		 try { 
			   DBConnection db = new DBConnection();
	    	   conn = db.ConnectDB();
	    	   String query = "SELECT P.ProjName , T.taskName, T.duration " +  
	    					   "FROM Projects P, Task T" +
	    					   "WHERE  P.ProjNo = T.ProjNo and t.devID = ? " +
	    					   "having max(t.end)"+
	    			     	   "GROUP BY P.ProjName;" ;
	           stmt = conn.prepareStatement(query);
	           stmt.setInt(1, devID);
			   rs = stmt.executeQuery();
			   while  (rs.next()) {
					String name = rs.getString(1);
					String taskName = rs.getString(2);
					String duration = rs.getString(3);
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
	
	public class Project {
		String projName;
		String lastTask;
		String taskDuration;	
		public Project(String projName, String lastTask, String taskDuration ) {
			this.projName = projName;
			this.lastTask = lastTask;
			this.taskDuration = taskDuration;
		}
	}
	public ArrayList<Project> DevProjectsAndLatestTasks(){
		return Projects;
	}

	
	public static void main(String[] args){
		DevHome dev = new DevHome();
		System.out.println(dev.DevProjectsAndLatestTasks());

	}
	

}
