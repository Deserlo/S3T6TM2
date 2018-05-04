import java.sql.SQLException;

public class Query {
	String tableName;
	String colName;
	String colName1;
	String name;
	public Query (String tableName, String colName,String colName1, String name ) {
		this.tableName = tableName;
		this.colName = colName;
		this.colName1 = colName1;
		this.name = name;
	}
	public Query() {}

	public String generateQueryString(Query q) {
		String select = "SELECT " + q.colName;
		String from = " FROM " + q.tableName;
		String where = " WHERE " + q.colName1 + "=?";
		return select + from + where;
	}
	public int getID(String name, String query) {
		DBConnection id = new DBConnection();
    	id.conn = id.ConnectDB();   
       try { 
    	   id.stmt = id.conn.prepareStatement(query);
           id.stmt.setString(1, name);
		   id.rs = id.stmt.executeQuery();
		   while(id.rs.next()) {
			   int ID = id.rs.getInt(1);
			   return ID;
		   }	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (id.rs != null) try {id.rs.close(); } catch (SQLException ignore) {}
			if (id.stmt != null) try {id.stmt.close(); } catch (SQLException ignore) {}
			if (id.conn != null) try {id.conn.close(); } catch (SQLException ignore) {}
		}     
       return 0;
	}
	
	public String getName(String name, String query) {
		DBConnection id = new DBConnection();
		id.conn = id.ConnectDB(); 
    	String aname = "";
        try { 
    	   id.stmt = id.conn.prepareStatement(query);
           id.stmt.setString(1, name);
		   id.rs = id.stmt.executeQuery();
		   while(id.rs.next()) {
			   aname = id.rs.getString(1);
			   return aname;
		   }	
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (id.rs != null) try {id.rs.close(); } catch (SQLException ignore) {}
			if (id.stmt != null) try {id.stmt.close(); } catch (SQLException ignore) {}
			if (id.conn != null) try {id.conn.close(); } catch (SQLException ignore) {}
		}     
		return aname;
	}
	
	public boolean checkIfExists(int id, String query) {
		boolean exists = false;
		DBConnection db = new DBConnection();
		db.conn = db.ConnectDB();
		try { 
	    	   db.stmt = db.conn.prepareStatement(query);
	           db.stmt.setInt(1, id);
			   db.rs = db.stmt.executeQuery();
			   while(db.rs.next()) {
				   exists = true;
			   }	
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if (db.rs != null) try {db.rs.close(); } catch (SQLException ignore) {}
				if (db.stmt != null) try {db.stmt.close(); } catch (SQLException ignore) {}
				if (db.conn != null) try {db.conn.close(); } catch (SQLException ignore) {}
			}     
		return exists;
	}
	
	
	public boolean checkIfNameExists(String name, String query) {
		boolean exists = false;
		DBConnection db = new DBConnection();
		db.conn = db.ConnectDB();
		try { 
	    	   db.stmt = db.conn.prepareStatement(query);
	           db.stmt.setString(1, name);
			   db.rs = db.stmt.executeQuery();
			   while(db.rs.next()) {
				   exists = true;
			   }	
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if (db.rs != null) try {db.rs.close(); } catch (SQLException ignore) {}
				if (db.stmt != null) try {db.stmt.close(); } catch (SQLException ignore) {}
				if (db.conn != null) try {db.conn.close(); } catch (SQLException ignore) {}
			}     
		return exists;
	}
	
	

	public static void main(String[] args) {
		//eg.
		//select id from user where userName = "email@example.com"
		Query q = new Query("User", "id","userName","email@example.com");
		String t = q.generateQueryString(q);
	    int id = q.getID(q.name, t);
	    System.out.println(id);
	    //eg.
	    //select userName from user where id = 1
	    Query qa = new Query("User", "userName","id","1");
		String ta = qa.generateQueryString(qa);
	    String a = qa.getName(qa.name, ta);
	    System.out.println(a);

	}

}
