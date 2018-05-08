import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskLog {
	String taskName;
	String projectName;
	int projNo;
	String description;
	String start;
	String end;
	int devID;
	public TaskLog() {
		taskName = "";
		projectName = "";
		description = "";
		start = "";
	};
	
	public TaskLog(String taskName, String projectName, String description,int devID) {
		this.taskName = taskName;
		this.projectName = projectName;
		this.description = description;
		this.devID = devID;
	}
		
	public String Start() {	
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = 
		new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String start = sdf.format(dt);
		return start;
	}
	
	public String Stop() {
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = 
		new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String stop = sdf.format(dt);
		return stop;
	}
	
	public boolean insertTask(TaskLog t) {
		int taskID = getTaskID(t.taskName, t.devID);
		if (checkIfAssigned(t.devID,  t.projectName)==false || taskID!=0)  {
			return false;
		}
		else {
			t.projNo = getProjID(t.projectName, t.devID);	
			String sql = "INSERT INTO Task (taskName, projNo, devID, start, description) VALUES (?, ? ,?,?,?);";
			DBConnection db = new DBConnection();
			db.conn = db.ConnectDB();
			try {
				db.stmt = db.conn.prepareStatement(sql);
				db.stmt.setString(1, t.taskName);
				db.stmt.setInt(2, t.projNo);
				db.stmt.setInt(3, t.devID);
				db.stmt.setString(4, t.Start());
				db.stmt.setString(5, t.description);
				db.stmt.execute();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return true;
	}
			
	public boolean stopTask(int devID, String taskName, String projName, String moreDescription) {
		boolean taskStopped = true;
		int taskID = getTaskID(taskName, devID);
		if (checkIfAssigned(devID,  projName)==false || taskID==0 || checkIfTaskComplete(taskID)==true)  {
			System.out.println("stop fail, either you are trying to log a task under an unassigned project, stopping a task which does not exist, or task has already been completed");	
			taskStopped = false;
		}
		else {
			String oldDescription = getDescription(taskID);
			String updatedDescription = oldDescription + ", " + moreDescription;
			DBConnection db = new DBConnection();
			db.conn = db.ConnectDB();
			try {
				db.stmt = db.conn.prepareStatement("UPDATE Task SET end = ? , description = ? WHERE taskID = ? and projNo = ? and duration is null;");
				db.stmt.setString(1, Stop());
				db.stmt.setString(2, updatedDescription);
				db.stmt.setInt(3, taskID);
				db.stmt.setInt(4, getProjID(projName, devID));
				db.stmt.execute();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			inputDuration(taskName, taskID);
		}
		return taskStopped;
	}
	
	//returns id of task that has been started but not finished
	private int getTaskID(String taskName, int devID) {
		String sql = "select T.taskID from Task T where T.taskName = ? and T.devID=? and T.duration is null";
		DBConnection db = new DBConnection();
		db.conn = db.ConnectDB();
		int id = 0;
		try {
			 db.stmt = db.conn.prepareStatement(sql);
	         db.stmt.setString(1, taskName);
	         db.stmt.setInt(2, devID);
			 db.rs = db.stmt.executeQuery();
			 while(db.rs.next()) {
				  id = db.rs.getInt(1);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (db.rs != null) try {db.rs.close(); } catch (SQLException ignore) {}
			if (db.stmt != null) try {db.stmt.close(); } catch (SQLException ignore) {}
			if (db.conn != null) try {db.conn.close(); } catch (SQLException ignore) {}
		}
	    return id;		
	}
	//returns id of project given a project name and unique dev ID
	private int getProjID(String projName, int devID) {
		String sql = "select P.projNo from Project P, Works_on W where P.projName = ? and W.projNo = P.projNo and W.devID = ?;";
		DBConnection db = new DBConnection();
		db.conn = db.ConnectDB();
		int pNo = 0;
		try {
			 db.stmt = db.conn.prepareStatement(sql);
	         db.stmt.setString(1, projName);
	         db.stmt.setInt(2, devID);
			 db.rs = db.stmt.executeQuery();
			 while(db.rs.next()) {
				  pNo = db.rs.getInt(1);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (db.rs != null) try {db.rs.close(); } catch (SQLException ignore) {}
			if (db.stmt != null) try {db.stmt.close(); } catch (SQLException ignore) {}
			if (db.conn != null) try {db.conn.close(); } catch (SQLException ignore) {}
		}
		return pNo;
	}
	
	private String getDescription(int taskID) {
		String sql = "SELECT description from Task where taskID=?;";	
		String description = "";
		DBConnection db = new DBConnection();
    	db.conn = db.ConnectDB();   
       try { 
    	   db.stmt = db.conn.prepareStatement(sql);
           db.stmt.setInt(1, taskID);
		   db.rs = db.stmt.executeQuery();
		   while(db.rs.next()) {
			   description = db.rs.getString(1);
			   return description;
		   }	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (db.rs != null) try {db.rs.close(); } catch (SQLException ignore) {}
			if (db.stmt != null) try {db.stmt.close(); } catch (SQLException ignore) {}
			if (db.conn != null) try {db.conn.close(); } catch (SQLException ignore) {}
		}  
       return description;
	}
	
	
	private String getStart(String taskname) {
		Query queryStart = new Query("Task", "start", "taskName", taskname);
		String query = queryStart.generateQueryString(queryStart);
	    String start = queryStart.getName(queryStart.name, query);
		return start ;}
	
	private String getStop(String taskname) {
		Query queryEnd = new Query("Task", "end", "taskName", taskname);
		String query = queryEnd.generateQueryString(queryEnd);
	    String end = queryEnd.getName(queryEnd.name, query);
		return end ;	
	}
	
	public void inputDuration(String taskname, int taskID) {
		DBConnection db = new DBConnection();
		db.conn = db.ConnectDB();
		double duration = calcDuration(taskname);
		double durationInHours = duration / ((double) 1000 * 60 * 60);
		try {
			db.stmt = db.conn.prepareStatement("UPDATE Task SET duration = ? WHERE taskID = ?;");
			db.stmt.setDouble(1, durationInHours);
			db.stmt.setInt(2, taskID);
			db.stmt.execute();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}				
	}
	
	private float calcDuration(String taskname) {
		start = getStart(taskname);
		end = getStop(taskname);
		Date date1 = null;
		Date date2 = null;
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date1 = formatter.parse(start);
			date2 = formatter.parse(end);
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		return date2.getTime() - date1.getTime();}
	
	//checks that a task entered by is for an assigned project by getting projNo and checking works_on table
	private boolean checkIfAssigned(int devID, String projName) {
			boolean isAssigned = false;
			String sql = "SELECT P.ProjName FROM Project P, Works_on W WHERE P.projNo = W.projNo and W.devID = ?";	;
			DBConnection db = new DBConnection();
	    	db.conn = db.ConnectDB();   
	       try { 
	    	   db.stmt = db.conn.prepareStatement(sql);
	           db.stmt.setInt(1, devID);
			   db.rs = db.stmt.executeQuery();
			   while(db.rs.next()) {
				   String pName = db.rs.getString(1);
				   if (projName.equals(pName))
					   isAssigned = true;
			   }	
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (db.rs != null) try {db.rs.close(); } catch (SQLException ignore) {}
				if (db.stmt != null) try {db.stmt.close(); } catch (SQLException ignore) {}
				if (db.conn != null) try {db.conn.close(); } catch (SQLException ignore) {}
			}   	
		return isAssigned;		
	}
	
	private boolean checkIfTaskComplete(int taskID) {
		String sql = "SELECT end from Task where taskID= ? AND duration is null;";
		DBConnection db = new DBConnection();
    	db.conn = db.ConnectDB();   
       try { 
    	   db.stmt = db.conn.prepareStatement(sql);
           db.stmt.setInt(1, taskID);
		   db.rs = db.stmt.executeQuery();
		   if (db.rs.next()) {
			   return false;
		   }	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (db.rs != null) try {db.rs.close(); } catch (SQLException ignore) {}
			if (db.stmt != null) try {db.stmt.close(); } catch (SQLException ignore) {}
			if (db.conn != null) try {db.conn.close(); } catch (SQLException ignore) {}
		}   	
       return true;		
	}			
}
