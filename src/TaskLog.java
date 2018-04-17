import java.sql.SQLException;

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
	public TaskLog(String taskName, String projectName, int devID) {
		
	}
	
	public TaskLog(String taskName, String projectName, String description,int devID) {
		this.taskName = taskName;
		this.projectName = projectName;
		this.description = description;
		this.devID = devID;
	}
		
	public String startTask() {	
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = 
		new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String start = sdf.format(dt);
		return start;
	}
	
	public void insertTask(TaskLog t) {
		t.projNo = getProjID(t.projectName);
		String sql = insertSqlTask();
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
	
	private int getTaskID(String taskName) {
		Query q = new Query("Task", "id","taskName",taskName);
		String t = q.generateQueryString(q);
	    int id = q.getID(q.name, t);
	    System.out.println(id);
	    return id;		
	}
	private int getProjID(String projName) {
		Query queryForProjNo = new Query("Project", "ProjNo", "ProjName", projectName);
		String t = queryForProjNo.generateQueryString(queryForProjNo);
		int pNo = queryForProjNo.getID(queryForProjNo.name, t);
		return pNo;}
	
	private int getDevID(String userName) {
		Query queryID = new Query("User", "id", "userName", userName);
		String s = queryID.generateQueryString(queryID);
		int devID = queryID.getID(queryID.name,s);	
		return devID;}
	
	private float calcDuration(String start, String end) {
		return 99;}
	
	public static String insertSqlTask() {
		return "INSERT INTO TASK (taskName, projNo, devID, start, description) VALUES (?, ? ,?,?,?);";	}
	
	public static void main(String[] args) {	
		TaskLog newtask = new TaskLog("A","ExProject1","this is task description",2);
		newtask.insertTask(newtask);		
		TaskLog newtask1 = new TaskLog("AA","ExProject1","this ishhh task description",2);
		newtask1.insertTask(newtask1);
	}

}
