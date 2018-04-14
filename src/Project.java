import java.util.Set;

public class Project {
	String projName;
	String lastTask;
	float taskDuration;	
	int budgetHours;
	int mgrID;
	Set<Integer>DevIDs; 
	
	public Project(String projName, String lastTask, float taskDuration ) {
		this.projName = projName;
		this.lastTask = lastTask;
		this.taskDuration = taskDuration;
	}
	public Project(String projName, int budgetHours, int mgrID) {
		this.projName = projName;
		this.budgetHours = budgetHours;
		this.mgrID = mgrID;
	}
	
	public void addProject(Project P) {
		//insert into tm.Project
	}
	
	public void assignProject(int mgrID, Set<Integer> devIDs) {
		//insert into tm.works_on
		
	}
	private int projNo(String projName) {
		return 99;
		
	}
}
