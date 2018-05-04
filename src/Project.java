import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;


public class Project {
	String projName;
	int budgetHours;
	int mgrID;
	Set<String>Devs; 
	
	public Project(String projName, int budgetHours, int mgrID, Set<String> Devs) {
		this.projName = projName;
		this.budgetHours = budgetHours;
		this.mgrID = mgrID;
		this.Devs = Devs;
	}
	
	public boolean createProject(Project P) {
		 PreparedStatement addProject = null;
		 String addStmt = "INSERT INTO Project (projName, mgrID, timeBudget) VALUES (?,?,?);";
		 DBConnection db = new DBConnection();
		 db.conn = db.ConnectDB();
		 try {
		        addProject = db.conn.prepareStatement(addStmt);
		        addProject.setString(1, P.projName);
		        addProject.setInt(2, P.mgrID);
		        addProject.setInt(3, P.budgetHours);
		        addProject.execute();        
		 } catch (SQLException e ) {
			 e.printStackTrace();		        
		 } finally {
			 if (db.rs != null) try {db.rs.close(); } catch (SQLException ignore) {}
			 if (db.stmt != null) try {db.stmt.close(); } catch (SQLException ignore) {}
			 if (db.conn != null) try {db.conn.close();} catch (SQLException ignore) {}
		 }
		 return true;
		
	}
	
	public boolean assignProject(String devName, String projNme) {
		 PreparedStatement assignProject = null;
		 PreparedStatement getID = null;
		 PreparedStatement getDevID = null;
		 String assignStmt = "INSERT INTO Works_on (projNo, devID) VALUES (?,?);";
		 String idQuery = "SELECT projNo from Project WHERE projName = ?;";
		 String devIDQuery = "SELECT id from Developer WHERE userName = ?;";
		 DBConnection db = new DBConnection();
		 db.conn = db.ConnectDB();
		 try {
		        db.conn.setAutoCommit(false);
		        getID = db.conn.prepareStatement(idQuery);
		        assignProject = db.conn.prepareStatement(assignStmt);
		        getDevID = db.conn.prepareStatement(devIDQuery);
		        assignProject.execute();		        
		        getID.setString(1,  projName);
		        db.rs = getID.executeQuery();
		        if (db.rs.next()){
		        	int projNo = db.rs.getInt(1);
		        	assignProject.setInt(1, projNo);
		        }
		        getDevID.setString(1, devName);
		        db.rs = getDevID.executeQuery();
		        if (db.rs.next()) {
		        	int devID = db.rs.getInt(1);
		        	assignProject.setInt(2, devID);
		        }
			        assignProject.execute(); // inserts new row in works_on table with projNo and devID assigned to work on it	
		        db.conn.commit();
		 } catch (SQLException e ) {
			 e.printStackTrace();
		        if (db.conn != null) {
		            try {
		                System.err.print("Transaction is being rolled back");
		                db.conn.rollback();
		            } catch(SQLException excep) {
		                excep.printStackTrace();
		            }
		        }
		        
		 } finally {
			 if (db.rs != null) try {db.rs.close(); } catch (SQLException ignore) {}
			 if (db.stmt != null) try {db.stmt.close(); } catch (SQLException ignore) {}
			 if (db.conn != null) try {db.conn.setAutoCommit(true);} catch (SQLException ignore) {}
		 }
		 return true;	
	}
		

	public static void main(String args[]) {
		//Project(String projName, int budgetHours, int mgrID, Set<String> Devs)
		String devUsername = "nec@enimnonnisi.com";
		String devUsernamee = "tellus@ultrices.com";
		Set<String> devNames = new HashSet<String>();
		devNames.add(devUsernamee);
		devNames.add(devUsername);
		Project newProject = new Project("testNewProject", 80, 27, devNames);
		newProject.createProject(newProject);
		
		
	}
	
}
