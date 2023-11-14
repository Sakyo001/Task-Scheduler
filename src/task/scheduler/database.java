
package task.scheduler;


import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author johnroel
 */
class database {
      public static Connection connectDb(){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/task-scheduler", "root", "");
            return connect;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        
    }
    
    
}
