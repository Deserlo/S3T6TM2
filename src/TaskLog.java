

public class TaskLog {
	String taskName;
	String projectName;
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
	public TaskLog(String start, String end) {
		this.start = start;
		this.end = end;
	}
		
	public void startTask(TaskLog entry) {
		//insert statements into Task table
		//eg. INSERT INTO Task (taskName, projNo, devID, timeBudget, start, end, duration, description) VALUES
		//("task A", 1, 2, 4.5, '2018-04-12 09:00:00', '2018-04-12 12:00:00', 3.0, "this is task A");
	}
	
	public void stopTask(String taskName) {
		//update db with timestamp for end
		//calcDuration
		//update db with duration
	}
	
	public void describeTask(String taskName) {
		//update db with description
	}
	
	private int taskID(String taskName) {
		return 99;
		
	}
	
	private float calcDuration(String start, String end) {
		return 99;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
