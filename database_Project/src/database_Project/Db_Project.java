package database_Project;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Db_Project {
	public static void main(String[]args ){
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver Loaded");
            Connection con = DriverManager.getConnection(url,"shoaib","103219");
            System.out.println("Conection Established");
            con.close();
        }
        catch(ClassNotFoundException e){
            System.out.println(e);
        }
        catch(SQLException e){
            System.out.println("connection Not Established");
        }
    }
}
