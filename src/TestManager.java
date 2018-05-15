import java.lang.reflect.Array;
import java.util.Set;
import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLExecution; 
import java.util.ArrayList;
import java.util.Arrays; 

public class TestManager
{ 
    //user session info 
    int mgrID = Test.userID; 
    boolean loggedIn = Test.login; //check logged in status
    
    //returns list of manager's developers by fname
    public String[] Devs(int mgrID)
    { 
        String[] allDevs = {}; 
        Connection conn = null; 
        PreparedStatement stmt = null;
        ResultSet = null; 
        
        try
        { 
            DBConnection db = new DBConnection(); 
            conn = db.ConnectDB();
            String query = "SELECT fname, mgrID" + 
                           "FROM Manager, User" + 
                           "WHERE Manager.id = User.mgrID AND mgrID = ?; ";
            stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
            stmt.setInt(1, mgrID);
            rs = stmt.executeQuery();
            int i = 0;
            if(rs.last())
            {
                int rows = rs.getRow();
                String results = new String[rows]; 
                System.out.println("Number of results: " + rows);
                // Move to beginning
                rs.beforeFirst();
                while(rs.next())
                {
                    String DevName = rs.getString(1);
                    results[i] = DevName;
                    i++;
                }
                return results;
            }
        }
        catch(SQLException e)
        { 
            e.printStackTrace();
        }
        finally
        { 
            if(rs != null) try{rs.close();} catch (SQLException ignore) {}
            if(stmt != null) try {stmt.close();} catch (SQLException ignore) {}
            if(conn != null) try {conn.close();} catch (SQLException ignore){}
        }
        return allDevs;
    }
    
    // returns list of project's manager is leading by projName
    public String[] Projects(int mgrID)
    { 
        String[] emptyProjects = {}; 
        Connection conn = null; 
        PreparedStatement stmt = null;
        ResultSet = null; 
        
        try
        { 
            DBConnection db = new DBConnection(); 
            conn = db.ConnectDB();
            String query = "SELECT projName, mgrID" + 
                           "FROM Manager, Project" + 
                           "WHERE Manager.id = Project.mgrID AND mgrID = ?; ";
            stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
            stmt.setInt(1, mgrID);
            rs = stmt.executeQuery();
            int i = 0;
            if(rs.last())
            {
                int rows = rs.getRow();
                String results = new String[rows]; 
                System.out.println("Number of results: " + rows);
                // Move to beginning
                rs.beforeFirst();
                while(rs.next())
                {
                    String DevName = rs.getString(1);
                    results[i] = DevName;
                    i++;
                }
                return results;
            }
        }
        catch(SQLException e)
        { 
            e.printStackTrace();
        }
        finally
        { 
            if(rs != null) try{rs.close();} catch (SQLException ignore) {}
            if(stmt != null) try {stmt.close();} catch (SQLException ignore) {}
            if(conn != null) try {conn.close();} catch (SQLException ignore){}
        }
        return emptyProjects;
    }
    
    public String[] getManDevsNames(int mgrID)
    {
    }
}
