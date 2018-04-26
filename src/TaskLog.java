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
		int taskID = getTaskID(taskName);
		if (checkIfAssigned(t.devID,  t.projectName)==false || taskID!=0)  {
			System.out.println("checkIfAssigned/project name false or task already started");
			return false;
		}
		else {
			t.projNo = getProjID(t.projectName);	
			String sql = insertSqlTask();
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
		int taskID = getTaskID(taskName);
		if (checkIfAssigned(devID,  projName)==false || taskID==0 || checkIfTaskComplete(taskID)==true)  {
			System.out.println("stop fail, checkIfAssigned or taskName false or task already completed");	
			taskStopped = false;
		}
		else {
			String oldDescription = getDescription(taskID);
			String updatedDescription = oldDescription + ", " + moreDescription;
			DBConnection db = new DBConnection();
			db.conn = db.ConnectDB();
			try {
				db.stmt = db.conn.prepareStatement(stopSqlTask());
				db.stmt.setString(1, Stop());
				db.stmt.setString(2, updatedDescription);
				db.stmt.setInt(3, taskID);
				db.stmt.setInt(4, getProjID(projName));
				db.stmt.execute();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			inputDuration(taskName, taskID);
		}
		return taskStopped;
	}
	
	private int getTaskID(String taskName) {
		Query q = new Query("Task", "taskID","taskName",taskName);
		String t = q.generateQueryString(q);
	    int id = q.getID(q.name, t);
	    return id;		}
	
	private int getProjID(String projName) {
		Query queryForProjNo = new Query("Project", "ProjNo", "ProjName", projectName);
		String t = queryForProjNo.generateQueryString(queryForProjNo);
		int pNo = queryForProjNo.getID(queryForProjNo.name, t);
		return pNo;}
	
	private String getDescription(int taskID) {
		String sql = getDesc();
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
			db.stmt = db.conn.prepareStatement(updateSqlDuration());
			db.stmt.setDouble(1, durationInHours);
			db.stmt.setInt(2, taskID);
			db.stmt.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return date2.getTime() - date1.getTime();}
	
	//checks that a task entered by is for an assigned project by getting projNo and checking works_on table
	private boolean checkIfAssigned(int devID, String projName) {
			boolean isAssigned = false;
			String sql = checkSqlProject();
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
		String sql = "SELECT end from task where taskID= ? AND duration is null;";
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
	
	public static String insertSqlTask() {
		return "INSERT INTO TASK (taskName, projNo, devID, start, description) VALUES (?, ? ,?,?,?);";	}
		
	public static String updateSqlDuration() {
		return  "UPDATE Task SET duration = ? WHERE taskID = ?;";	}
		
	public static String stopSqlTask() {
		return  "UPDATE Task SET end = ? , description = ? WHERE taskID = ? and projNo = ? and duration is null;";	}
	
	public static String checkSqlProject() {
		return "SELECT P.ProjName FROM PROJECT P, WORKS_ON W WHERE P.projNo = W.projNo and W.devID = ?";	}
	
	public static String getDesc() {
		return "SELECT description from task where taskID=?;";	}
	
	public static void main(String[] args) {	
		TaskLog newtask1 = new TaskLog();
		float diff = newtask1.calcDuration("nametask");
	    double diffInHours = diff / ((double) 1000 * 60 * 60);
	    System.out.println(diffInHours);
	    System.out.println("Hours " + (int)diffInHours);
	    System.out.println("Minutes " + (diffInHours - (int)diffInHours)*60 );

	}

}
