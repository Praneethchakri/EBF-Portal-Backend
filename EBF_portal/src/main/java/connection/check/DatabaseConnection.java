package connection.check;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * A Java MySQL SELECT statement example.
 * Demonstrates the use of a SQL SELECT statement against a
 * MySQL database, called from a Java program.
 * 
 */
public class DatabaseConnection{

  public static void main(String[] args)
  {
    try
    {
    	
    	InputStream input = new FileInputStream("C:\\Users\\Praneeth\\Downloads\\workspace\\EBF_portal\\src\\main\\resources\\application.properties"); 
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
      // create our mysql database connection
      String myDriver = "com.mysql.cj.jdbc.Driver";
//      String myUrl = "jdbc:mysql://localhost/Mysql";
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection
    		  (prop.getProperty("spring.datasource.url"),prop.getProperty("spring.datasource.username"), 
    				  prop.getProperty("spring.datasource.password"));
      
      // our SQL SELECT query. 
      // if you only need a few columns, specify them by name instead of using "*"
      String query = "SELECT * FROM Employee";

      // create the java statement
      Statement st = conn.createStatement();
      
      // execute the query, and get a java resultset
      ResultSet rs = st.executeQuery(query);
      
      // iterate through the java resultset
      while (rs.next())
      {
        int id = rs.getInt("Emp_id");
        String firstName = rs.getString("name");
        String lastName = rs.getString("surname");
        String address = rs.getString("Address");
//        String city = rs.getString("city");
        String email = rs.getString("email");
        
        // print the results
        System.out.format("%s, %s, %s,%s ,%s \n", id, firstName, lastName, address,email);
      }
      st.close();
    }
    catch (Exception e)
    {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
    }
    
  }
}
