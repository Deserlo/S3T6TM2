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
	public String genQuery(Query q) {
		String select = "SELECT " + q.colName;
		String from = " FROM " + q.tableName;
		String where = " WHERE " + q.colName1 + "=?";
		System.out.println(select + from + where);
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
       System.out.println("query for ID failed");
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (id.rs != null) try {id.rs.close(); } catch (SQLException ignore) {}
			if (id.stmt != null) try {id.stmt.close(); } catch (SQLException ignore) {}
			if (id.conn != null) try {id.conn.close(); } catch (SQLException ignore) {}
		}     
       System.out.println("query for name failed");
		return aname;
	}
	
	

	public static void main(String[] args) {
		Query q = new Query("user", "id","userName","email@example.com");
		String t = q.genQuery(q);
	    int id = q.getID(q.name, t);
	    System.out.println(id);
	    
	    Query qa = new Query("user", "userName","id","1");
		String ta = qa.genQuery(qa);
	    String a = qa.getName(qa.name, ta);
	    System.out.println(a);

	}

}
