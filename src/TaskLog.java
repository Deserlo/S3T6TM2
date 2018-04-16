import java.sql.SQLException;

public class TaskLog {
	String table;
	String taskName;
	String projectName;
	int projNo;
	String description;
	String start;
	String end;
	int devID;
	public TaskLog() {
		table = "Task";
		taskName = "";
		projectName = "";
		description = "";
		start = "";
	};
	public TaskLog(String taskName, String projectName, String description,int devID) {
		table = "Task";
		this.taskName = taskName;
		this.projectName = projectName;
		this.description = description;
		this.devID = devID;
	}
	public TaskLog(String start, String end) {
		table = "Task";
		this.start = start;
		this.end = end;
	}
		
	public String startTask() {	
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = 
		new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String start = sdf.format(dt);
		return start;
	}
	
	public void insertTask(TaskLog t, Statement s) {
		t.projNo = projID(t.projectName);
		t.prepareCols(s);	
		String sql = t.genTaskStmt(s);
		DBConnection db = new DBConnection();
		db.conn = db.ConnectDB();
		try {
			db.stmt = db.conn.prepareStatement(sql);
			db.stmt.setString(1, t.taskName);
			db.stmt.setInt(2, t.projNo);
			db.stmt.setInt(3, t.devID);
			db.stmt.setString(4, t.startTask());
			db.stmt.setString(5, t.description);
			db.stmt.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public String stopTask() {
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = 
		new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String stop = sdf.format(dt);
		return stop;
		//update db with timestamp for end
		//calcDuration
		//update db with duration
	}
	
	public void describeTask(String taskName) {
		//get task id and update description
		
	}
	
	private int taskID(String taskName) {
		Query q = new Query("Task", "id","taskName",taskName);
		String t = q.genQuery(q);
	    int id = q.getID(q.name, t);
	    System.out.println(id);
	    return id;		
	}
	private int projID(String projName) {
		Query queryForProjNo = new Query("Project", "ProjNo", "ProjName", projectName);
		String t = queryForProjNo.genQuery(queryForProjNo);
		int pNo = queryForProjNo.getID(queryForProjNo.name, t);
		return pNo;
	}
	
	private int devID(String userName) {
		Query queryForDevID = new Query("User", "id", "userName", userName);
		String s = queryForDevID.genQuery(queryForDevID);
		int devID = queryForDevID.getID(queryForDevID.name,s);	
		return devID;
	}
	
	private float calcDuration(String start, String end) {
		return 99;
	}
	
	private void prepareCols(Statement s) {	
		s.addCols("taskName");
		s.addCols("projNo");
		s.addCols("devID");
		s.addCols("start");
		s.addCols("description");		
	}
	
	private String genTaskStmt(Statement s) {
	    String sql = s.genInsertStmtStr(s);
	    return sql;
	}
	
	public static void main(String[] args) {	
		Statement stmtTask = new Statement("Task");
		TaskLog newtask = new TaskLog("butt","ExProject1","this is task description",2);
	    newtask.insertTask(newtask,stmtTask);	
	}

}
