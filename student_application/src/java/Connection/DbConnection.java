/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mvinoth
 */
 public class DbConnection {

    public static Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    Statement st=null;
    Connection con=null;
     ResultSet rs=null;
   public  DbConnection()
     {
         try
         {
           Class.forName("com.mysql.jdbc.Driver");
           con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student_application","root","admin");
           st=con.createStatement();
         }
         catch(Exception e)
         {
             System.out.println("Class Error"+e);
         }
     }
    public ResultSet Select(String query)
     {
        
        try {
            rs = st.executeQuery(query);
        } catch (SQLException ex) {
           System.out.println("Select Query Error"+ex);
        }
         return rs;
        
        
     }
 public  int Insert(String query)
     {
         int i=0;
        try {
            i=st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("Select Query Error"+ex);
        }
        return i;
     }
  public  int Update(String query)
     {
         int i=0;
        try {
            i=st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("Select Query Error"+ex);
        }
        return i;
     }    
    
}
