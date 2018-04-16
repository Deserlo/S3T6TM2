import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

public class Statement {
	String tableName;
	ArrayList<String> colNames = new ArrayList<String>();
	public Statement(String tableName) {
		this.tableName = tableName;
	}
	
	public int getColNamesSize() {
		return colNames.size();	
	}
	void addCols(String colName) {
		colNames.add(colName);
	}
	
	//prepares insert statement using helper functions colLin & valLine
	//eg. INSERT INTO Task (taskName, projNo, devID, start, description) VALUES (?, ?, ?,?,?);
	public String genInsertStmtStr(Statement s) {
		String insert = "INSERT INTO " + s.tableName;
		String columns = " " + colLine(s.colNames) + ")";
		String values = " VALUES " + "(" + valLine(getColNamesSize())+ ");";
		System.out.println(insert + columns + values);
		return insert + columns + values;
	}
	
	public String genUpdateStmtStr(Statement s) {
		return null;
	}
	
	private String colLine(ArrayList<String> s) {
		int i = 0;
		String c = "( ";
		while (i < getColNamesSize()) {
			if (i == getColNamesSize()-1) {
				c = c+s.get(i);
				i++;
			}
			else
				c = c + s.get(i)+ ",";
				i++;
		}
		return c;
	}
	
	private String valLine(int size) {
		int i = 0;
		String v="";
		while (i != getColNamesSize()) {
			if (i == getColNamesSize()-1) {
				v = v+ "?";
				i++;
			}
			else {
				v = v+ "?,";	
				i++;
			}
		}
		return v;
		
		
	}
	public static void main(String[] args) {
		Statement newStatement = new Statement("User");
		UserAccount newAcc = new UserAccount("dev", "eva", "enaj58", "1234", "lakers");
		//INSERT INTO User (userName, fname, team,pwd, mgrID)
		//must match db columnn names
		newStatement.addCols("userName");
		newStatement.addCols("fname");
		newStatement.addCols("team");
		newStatement.addCols("pwd");
		newStatement.addCols("mgrID");
	    String sql = newStatement.genInsertStmtStr(newStatement);
	    System.out.println(sql);
	}

}
